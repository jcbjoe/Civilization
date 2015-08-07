package me.jcbjoe.Civilization.CommandMethods;

import java.util.List;

import me.jcbjoe.Civilization.Main;
import me.jcbjoe.Civilization.settingsManager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TrustUnTrust {

	public static boolean trust(CommandSender sender, Command cmd, String desc,
			String[] args, Main plugin) {
		if(args.length != 2){
			sender.sendMessage(plugin.prefix+"Format: /civilization trust (Player)");
			return true;
		}
		Player player = (Player)sender;
		int PChunkX = player.getLocation().getChunk().getX();
		int PChunkZ = player.getLocation().getChunk().getZ();
		String ChunkName = "X"+PChunkX+"Z"+PChunkZ;
		if(plugin.chunks.containsKey(ChunkName)){
			String owner = plugin.chunks.get(ChunkName);
			if(owner.equals(player.getName())){
				List<String> list = plugin.trusted.get(ChunkName);
				if(list.contains(args[1])){
					player.sendMessage(ChatColor.RED+args[1]+ChatColor.GOLD+" is already trusted on your claim");
					return true;
				}
				list.add(args[1]);
				plugin.trusted.put(ChunkName, list);
				settingsManager.chunk.set("ClaimedChunks."+ChunkName+".trusted", list);
				settingsManager.saveChunkFile();
				player.sendMessage(plugin.prefix+"Successfully trusted: "+ChatColor.RED+args[1]+ChatColor.GOLD+" to your chunk!");
				return true;
			}else{
				player.sendMessage(plugin.prefix+"You need to be the owner of this claim to trust players!");
			}
		}else{
			player.sendMessage(plugin.prefix+"The chunk you are standing in is not claimed!");
		}
		return false;
	}
	
	public static boolean untrust(CommandSender sender, Command cmd, String desc,
			String[] args, Main plugin) {
		if(args.length != 2){
			sender.sendMessage(plugin.prefix+"Format: /civilization untrust (Player)");
			return true;
		}
		Player player = (Player)sender;
		int PChunkX = player.getLocation().getChunk().getX();
		int PChunkZ = player.getLocation().getChunk().getZ();
		String ChunkName = "X"+PChunkX+"Z"+PChunkZ;
		if(plugin.chunks.containsKey(ChunkName)){
			String owner = plugin.chunks.get(ChunkName);
			if(owner.equals(player.getName())){
				List<String> list = plugin.trusted.get(ChunkName);
				if(list == null){
					player.sendMessage(ChatColor.RED+args[1]+ChatColor.GOLD+" is not trusted on your claim");
					return true;
				}
				if(!list.contains(args[1])){
					player.sendMessage(ChatColor.RED+args[1]+ChatColor.GOLD+" is not trusted on your claim");
					return true;
				}
				list.remove(args[1]);
				plugin.trusted.remove(ChunkName, list);
				settingsManager.chunk.set("ClaimedChunks."+ChunkName+".trusted", list);
				settingsManager.saveChunkFile();
				player.sendMessage(plugin.prefix+"Successfully untrusted: "+ChatColor.RED+args[1]+ChatColor.GOLD+" to your chunk!");
				return true;
			}else{
				player.sendMessage(plugin.prefix+"You need to be the owner of this claim to trust players!");
			}
		}else{
			player.sendMessage(plugin.prefix+"The chunk you are standing in is not claimed!");
		}
		return false;
	}

}
