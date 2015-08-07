package me.jcbjoe.Civilization.CommandMethods;

import java.util.List;

import me.jcbjoe.Civilization.Main;
import me.jcbjoe.Civilization.settingsManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnClaim {

	public static boolean unClaimChunk(CommandSender sender, Command cmd,
			String desc, String[] args, Main plugin) {
		
		Player player = (Player)sender;
		int PChunkX = player.getLocation().getChunk().getX();
		int PChunkZ = player.getLocation().getChunk().getZ();
		String ChunkName = "X"+PChunkX+"Z"+PChunkZ;
		if(plugin.chunks.containsKey(ChunkName)){
			String owner = plugin.chunks.get(ChunkName);
			if(owner.equals(player.getName())){
				settingsManager.chunk.set("ClaimedChunks."+ChunkName, null);
				int claimsAmount = plugin.playerdata.get(player.getName());
				settingsManager.saveChunkFile();
				if(claimsAmount == 1){
					plugin.playerdata.remove(player.getName());
					settingsManager.player.set(player.getName(), null);
				}else{
					List<String> claims = settingsManager.player.getStringList("Players."+player.getName()+".chunks");
					claims.remove(ChunkName);
					settingsManager.player.set("Players."+player.getName()+".chunks", claims);
					plugin.playerdata.put(player.getName(), claimsAmount-1);
					settingsManager.player.set(player.getName(), claimsAmount-1);
				}
				plugin.chunks.remove(ChunkName);
				settingsManager.savePlayerFile();
				player.sendMessage(plugin.prefix+"Claim successfully removed!");
				return true;
			}else{
				player.sendMessage(plugin.prefix+"You do not own this chunk/claim!");
				return true;
			}
		}else{
			player.sendMessage(plugin.prefix+"This chunk is not claimed!");
			return true;
		}
	}

	public static boolean unClaimAll(CommandSender sender, Command cmd,
			String desc, String[] args, Main plugin) {
		Player player = (Player)sender;
		if(plugin.playerdata.get(player.getName()) == null){
			player.sendMessage(plugin.prefix+"You do not have any claims to unclaim!");
			return true;
		}
		int claimsAmount = plugin.playerdata.get(player.getName());
		if(claimsAmount == 0){
			player.sendMessage(plugin.prefix+"You do not have any claims to unclaim!");
			return true;
		}else{
			List<String> claims = settingsManager.player.getStringList("Players."+player.getName()+".chunks");
			for(String ChunkNames : claims){
				plugin.chunks.remove(ChunkNames);
				plugin.trusted.remove(ChunkNames);
				settingsManager.chunk.set("ClaimedChunks."+ChunkNames, null);
				settingsManager.saveChunkFile();
			}
			settingsManager.player.set("Players."+player.getName(), null);
			plugin.playerdata.remove(player.getName());
			settingsManager.savePlayerFile();
			player.sendMessage(plugin.prefix+"All claims successfully removed!");
			return true;
		}
	}

	public static boolean forceunclaim(CommandSender sender, Command cmd,
			String desc, String[] args, Main plugin) {
		if(args.length !=2){
			
		}
		String playername = args[1];
				if(args.length != 2){
					sender.sendMessage(plugin.prefix+"Format: /civilization admin forceunclaim");
					return true;
				}
				Player player = (Player)sender;
				int PChunkX = player.getLocation().getChunk().getX();
				int PChunkZ = player.getLocation().getChunk().getZ();
				String ChunkName = "X"+PChunkX+"Z"+PChunkZ;
				if(plugin.chunks.containsKey(ChunkName)){

						settingsManager.chunk.set("ClaimedChunks."+ChunkName, null);
						int claimsAmount = plugin.playerdata.get(playername);
						settingsManager.saveChunkFile();
						if(claimsAmount == 1){
							plugin.playerdata.remove(playername);
							settingsManager.player.set(playername, null);
						}else{
							List<String> claims = settingsManager.player.getStringList("Players."+playername+".chunks");
							claims.remove(ChunkName);
							settingsManager.player.set("Players."+playername+".chunks", claims);
							plugin.playerdata.put(playername, claimsAmount-1);
							settingsManager.player.set(playername, claimsAmount-1);
						}
						plugin.chunks.remove(ChunkName);
						settingsManager.savePlayerFile();
						sender.sendMessage(plugin.prefix+"Claim successfully removed!");
						return true;
					
				}else{
					sender.sendMessage(plugin.prefix+"This chunk is not claimed!");
					return true;
				}
	}
	

}
