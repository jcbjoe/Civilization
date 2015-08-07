package me.jcbjoe.Civilization.CommandMethods;

import java.util.List;

import me.jcbjoe.Civilization.Main;
import me.jcbjoe.Civilization.settingsManager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Info {

	public static boolean infoCommand(CommandSender sender, Command cmd,
			String desc, String[] args, Main plugin) {
		Player player = (Player)sender;
		int ChunkX = player.getLocation().getChunk().getX();
		int ChunkZ = player.getLocation().getChunk().getZ();
		String ChunkName = "X"+ChunkX+"Z"+ChunkZ;
		if(plugin.chunks.containsKey(ChunkName)){
			String owner = plugin.chunks.get(ChunkName);
			player.sendMessage(ChatColor.GOLD+"===== "+ChatColor.AQUA+ChunkName+ChatColor.GOLD+" =====");
			player.sendMessage(ChatColor.RED+"Status: "+ChatColor.DARK_RED+"Claimed");
			player.sendMessage(ChatColor.RED+"Owner: "+ChatColor.DARK_RED+owner);
			return true;
		}else{
			player.sendMessage(ChatColor.GOLD+"===== "+ChatColor.AQUA+ChunkName+ChatColor.GOLD+" =====");
			player.sendMessage(ChatColor.RED+"Status: "+ChatColor.DARK_RED+"Unclaimed");
			return true;
		}
	}

	public static boolean me(CommandSender sender, Command cmd, String desc,
			String[] args, Main plugin) {
		sender.sendMessage(ChatColor.GOLD+"===== "+ChatColor.AQUA+sender.getName()+ChatColor.GOLD+" =====");
		String claimlist = "";
		if(settingsManager.player.contains("Players."+sender.getName())){
			List<String> claims = settingsManager.player.getStringList("Player"+sender.getName()+".chunks");
			int times = 0;
			for(String claimname : claims){
				times++;
				if(claims.size() == times){
					claimlist = claimlist+claimname+"";
				}else{
					claimlist = claimlist+claimname+", ";
				}
			}
		}else{
			claimlist = "no claims";
		}
		int claimsAmount = plugin.playerdata.get(sender.getName());
		int claimsAmountRank;
		if(plugin.getConfig().contains("ranks."+Main.permission.getPrimaryGroup((Player)sender))){
			claimsAmountRank = plugin.getConfig().getInt("ranks."+Main.permission.getPrimaryGroup((Player)sender));
		}else{
			claimsAmountRank = plugin.getConfig().getInt("ranks.default");
		}
		sender.sendMessage(ChatColor.RED+"Claims: "+ChatColor.DARK_RED+claimlist);
		sender.sendMessage(ChatColor.RED+"Amount of claims: "+ChatColor.DARK_RED+claimsAmount);
		sender.sendMessage(ChatColor.RED+"Maximum claims: "+ChatColor.DARK_RED+claimsAmountRank);
		return true;
	}

}
