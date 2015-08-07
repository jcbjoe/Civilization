package me.jcbjoe.Civilization.ListenerMethods;

import java.util.List;

import me.jcbjoe.Civilization.Main;
import me.jcbjoe.Civilization.settingsManager;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockBreakPlace {


	public static boolean blockBreakHandler(BlockBreakEvent event, Main plugin) {
		Player player = event.getPlayer();
		int PChunkX = player.getLocation().getChunk().getX();
		int PChunkZ = player.getLocation().getChunk().getZ();
		String ChunkName = "X"+PChunkX+"Z"+PChunkZ;
		if(plugin.chunks.containsKey(ChunkName)){
			String owner = plugin.chunks.get(ChunkName);
			if(owner.equals(player.getName()) || plugin.override.contains(player.getName())){
				return false;
			}
			if(settingsManager.chunk.getBoolean("ClaimedChunks."+ChunkName+".flags.all.destroy")){
				return false;
			}
			List<String> trusted = plugin.trusted.get(ChunkName);
			if(trusted == null){
				player.sendMessage(plugin.prefix+"Trusted players cannot break blocks here!");
				return true;
			}
			if(trusted.isEmpty()){
				player.sendMessage(plugin.prefix+"Trusted players cannot break blocks here!");
				return true;
			}
			if(trusted.contains(player.getName())){
				if(settingsManager.chunk.getBoolean("ClaimedChunks."+ChunkName+".flags.trusted.destroy")){
					return false;
				}else{
					player.sendMessage(plugin.prefix+"Trusted players cannot break blocks here!");
					return true;
				}
			}else{
				player.sendMessage(plugin.prefix+"You cannot break here!");
				player.sendMessage(ChatColor.GOLD+"This claim belongs to: "+ChatColor.RED+owner);
				return true;
			}
		}
		return false;
	}
	
	public static boolean blockPlaceHandler(BlockPlaceEvent event, Main plugin) {
		Player player = event.getPlayer();
		int PChunkX = player.getLocation().getChunk().getX();
		int PChunkZ = player.getLocation().getChunk().getZ();
		String ChunkName = "X"+PChunkX+"Z"+PChunkZ;
		if(plugin.chunks.containsKey(ChunkName)){
			String owner = plugin.chunks.get(ChunkName);
			if(owner.equals(player.getName())|| plugin.override.contains(player.getName())){
				return false;
			}
			if(settingsManager.chunk.getBoolean("ClaimedChunks."+ChunkName+".flags.all.place")){
				return false;
			}
			List<String> trusted = plugin.trusted.get(ChunkName);
			if(trusted == null){
				player.sendMessage(plugin.prefix+"Trusted players cannot break blocks here!");
				return true;
			}
			if(trusted.isEmpty()){
				player.sendMessage(plugin.prefix+"Trusted players cannot break blocks here!");
				return true;
			}
			if(trusted.contains(player.getName())){
				if(settingsManager.chunk.getBoolean("ClaimedChunks."+ChunkName+".flags.trusted.place")){
					return false;
				}else{
					player.sendMessage(plugin.prefix+"Trusted players cannot place blocks here!");
					return true;
				}
			}else{
				player.sendMessage(plugin.prefix+"You cannot break here!");
				player.sendMessage(ChatColor.GOLD+"This claim belongs to: "+ChatColor.RED+owner);
				return true;
			}
		}
		return false;
	}
	
}
