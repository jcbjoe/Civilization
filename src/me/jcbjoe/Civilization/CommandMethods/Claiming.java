package me.jcbjoe.Civilization.CommandMethods;

import java.util.ArrayList;
import java.util.List;

import me.jcbjoe.Civilization.Main;
import me.jcbjoe.Civilization.settingsManager;
import net.milkbowl.vault.economy.EconomyResponse;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Claiming {

	static List<String> emptyList = new ArrayList<String>();
	static List<String> emptyClaims = new ArrayList<String>();
	
	public static boolean claim(CommandSender sender, Command cmd, String desc,
			String[] args, Main plugin){
		if(sender instanceof Player){
			Player player = (Player)sender;
			int ChunkX = player.getLocation().getChunk().getX();
			int ChunkZ = player.getLocation().getChunk().getZ();
			String ChunkName = "X"+ChunkX+"Z"+ChunkZ;
			if(plugin.chunks.containsKey(ChunkName)){
				String owner = plugin.chunks.get(ChunkName);
				if(owner.equals(player.getName())){
					player.sendMessage(plugin.prefix+"You already own this claim!");
					return true;
				}else{
					if(settingsManager.chunk.getString("ClaimedChunks."+ChunkName+".type") == "admin"){
						player.sendMessage(plugin.prefix+"This chunk has been protected by a admin!");
						return true;
					}
					player.sendMessage(plugin.prefix+"This claim belongs to: "+ChatColor.RED+owner+"!");
					return true;
				}
			}else{
				if(plugin.playerdata.containsKey(player.getName())){
					int claimsAmount = plugin.playerdata.get(player.getName());
					int claimsAmountRank;
					if(settingsManager.player.contains("Players."+sender.getName()+".customMax")){
						claimsAmountRank = plugin.getConfig().getInt("ranks."+Main.permission.getPrimaryGroup(player)) + settingsManager.player.getInt("Players."+player.getName()+".customMax");
					}else{
						claimsAmountRank = plugin.getConfig().getInt("ranks."+Main.permission.getPrimaryGroup(player));
					}
					if(claimsAmountRank == 0){
						claimsAmountRank = plugin.getConfig().getInt("ranks.default");
					}
					if(claimsAmount < claimsAmountRank){
						int price = plugin.getConfig().getInt("Chunk cost");
						if(plugin.getConfig().getBoolean("Increase Price")){
							float percentageAmount = (float)plugin.getConfig().getDouble("Percentage increase");
							float percentage = (price / 100)*percentageAmount;
							float total = price + (percentage*claimsAmount);
							if(Main.economy.getBalance(player.getName()) >= total){
								EconomyResponse r = Main.economy.withdrawPlayer(player.getName(), total);
								if(r.transactionSuccess()) {
									claimsAmount++;
									List<String> claims = settingsManager.player.getStringList("Players."+player.getName()+".chunks");
									claims.add(ChunkName);
									settingsManager.player.set("Players."+player.getName()+".chunks", claims);
									plugin.playerdata.put(player.getName(), claimsAmount);
									settingsManager.player.set("Players."+player.getName()+".claims", claimsAmount);
									settingsManager.savePlayerFile();
									claimChunk(ChunkName, player, plugin);
									player.sendMessage(plugin.prefix+"Chunk successfully claimed!");
									return true;
								} else {
									sender.sendMessage(String.format("An error occured: %s", r.errorMessage));
									return true;
								}
							}else{
								player.sendMessage(plugin.prefix+"You do not have enough money to purchase this chunk!");
								player.sendMessage("You have: "+Main.economy.getBalance(player.getName())+". and you need: "+total);
								return true;
							}
						}else{
							if(Main.economy.getBalance(player.getName()) >= price){
								EconomyResponse r = Main.economy.withdrawPlayer(player.getName(), price);
								if(r.transactionSuccess()) {
									claimsAmount++;
									List<String> claims = settingsManager.player.getStringList("Players."+player.getName()+".chunks");
									claims.add(ChunkName);
									settingsManager.player.set("Players."+player.getName()+".chunks", claims);
									plugin.playerdata.put(player.getName(), claimsAmount);
									settingsManager.player.set("Players."+player.getName()+".claims", claimsAmount);
									settingsManager.savePlayerFile();
									claimChunk(ChunkName, player, plugin);
									player.sendMessage(plugin.prefix+"Chunk successfully claimed!");
									return true;
								} else {
									sender.sendMessage(String.format("An error occured: %s", r.errorMessage));
									return true;
								}
							}else{
								player.sendMessage(plugin.prefix+"You do not have enough money to purchase this chunk!");
								player.sendMessage("You have: "+Main.economy.getBalance(player.getName())+". and you need: "+price);
								return true;
							}
						}
						
					}else{
						player.sendMessage(plugin.prefix+"You are at your limit of: "+claimsAmountRank);
						return true;
					}
				}else{
					int price = plugin.getConfig().getInt("Chunk cost");
					if(Main.economy.getBalance(player.getName()) >= price){
						EconomyResponse r = Main.economy.withdrawPlayer(player.getName(), price);
						if(r.transactionSuccess()) {
							List<String> claims = emptyClaims;
							claims.add(ChunkName);
							settingsManager.player.set("Players."+player.getName()+".chunks", claims);
							plugin.playerdata.put(player.getName(), 1);
							settingsManager.player.set("Players."+player.getName()+".claims", 1);
							settingsManager.savePlayerFile();
							claimChunk(ChunkName, player, plugin);
							player.sendMessage(plugin.prefix+"Chunk successfully claimed!");
							return true;
						}else{
							player.sendMessage(plugin.prefix+"You do not have enough money to purchase this chunk!");
							player.sendMessage("You have: "+Main.economy.getBalance(player.getName())+". and you need: "+price);
							return true;
						}
					}
				
				}
			}
		}
		return true;
	}
	
	public static void claimChunk(String ChunkName, Player player,Main plugin){
		plugin.chunks.put(ChunkName, player.getName());
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".owner", player.getName());
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".type", "normal");
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".trusted", emptyList);
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.trusted.place", true);
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.trusted.destroy", true);
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.trusted.interact", true);
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.trusted.enter", true);
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.all.place", false);
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.all.destroy", false);
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.all.interact", false);
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.all.enter", true);
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.greetingEnabled", false);
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.farewellEnabled", false);
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.greeting", "Entering "+player.getName()+"'s plot");
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.farewell", "Leaving "+player.getName()+"'s plot");
		settingsManager.saveChunkFile();
	}
	
	public static void adminclaimChunk(String ChunkName, Player player,Main plugin){
		plugin.chunks.put(ChunkName, player.getName());
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".owner", player.getName());
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".type", "admin");
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".trusted", emptyList);
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.trusted.place", true);
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.trusted.destroy", true);
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.trusted.interact", true);
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.trusted.enter", true);
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.all.place", false);
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.all.destroy", false);
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.all.interact", false);
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.all.enter", true);
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.greetingEnabled", false);
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.farewellEnabled", false);
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.greeting", "Entering "+player.getName()+"'s plot");
		settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.farewell", "Leaving "+player.getName()+"'s plot");
		settingsManager.saveChunkFile();
	}

	public static boolean adminclaim(CommandSender sender, Command cmd,
			String desc, String[] args, Main plugin) {
		if(sender instanceof Player){
			Player player = (Player)sender;
			int ChunkX = player.getLocation().getChunk().getX();
			int ChunkZ = player.getLocation().getChunk().getZ();
			String ChunkName = "X"+ChunkX+"Z"+ChunkZ;
			if(plugin.chunks.containsKey(ChunkName)){
				String owner = plugin.chunks.get(ChunkName);
				if(owner.equals(player.getName())){
					player.sendMessage(plugin.prefix+"You already own this claim!");
					return true;
				}else{
					if(settingsManager.chunk.getString("ClaimedChunks."+ChunkName+".type") == "admin"){
						player.sendMessage(plugin.prefix+"This chunk has been protected by a admin!");
						return true;
					}
					player.sendMessage(plugin.prefix+"This claim belongs to: "+ChatColor.RED+owner+"!");
					return true;
				}
			}else{
				if(settingsManager.player.contains("Players."+player.getName()+".chunks")){
					List<String> claims = settingsManager.player.getStringList("Players."+player.getName()+".chunks");
					claims.add(ChunkName);
					settingsManager.player.set("Players."+player.getName()+".chunks", claims);
					settingsManager.savePlayerFile();
					adminclaimChunk(ChunkName, player, plugin);
					player.sendMessage(plugin.prefix+"Admin chunk successfully claimed!");
					return true;
				}else{
					List<String> claims = emptyClaims;
					claims.add(ChunkName);
					settingsManager.player.set("Players."+player.getName()+".chunks", claims);
					settingsManager.savePlayerFile();
					adminclaimChunk(ChunkName, player, plugin);
					player.sendMessage(plugin.prefix+"Admin chunk successfully claimed!");
					return true;
				}
			}
		}
		return false;
	}
}
