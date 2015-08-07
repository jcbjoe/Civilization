package me.jcbjoe.Civilization.ListenerMethods;

import java.util.List;

import me.jcbjoe.Civilization.Main;
import me.jcbjoe.Civilization.settingsManager;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

public class Interaction {

	public static boolean interact(PlayerInteractEvent event, Main plugin) {
		Player player = event.getPlayer();
		int PChunkX = player.getLocation().getChunk().getX();
		int PChunkZ = player.getLocation().getChunk().getZ();
		String ChunkName = "X"+PChunkX+"Z"+PChunkZ;
		if(plugin.chunks.containsKey(ChunkName)){
			String owner = plugin.chunks.get(ChunkName);
			if(owner.equals(player.getName())|| plugin.override.contains(player.getName())){
				return false;
			}
			if(settingsManager.chunk.getBoolean("ClaimedChunks."+ChunkName+".flags.all.interact")){
				return false;
			}
			List<String> trusted = plugin.trusted.get(ChunkName);
			if(trusted == null){
				player.sendMessage(plugin.prefix+"Trusted players cannot interact here!");
				return true;
			}
			if(trusted.isEmpty()){
				player.sendMessage(plugin.prefix+"Trusted players cannot interact here!");
				return true;
			}
			if(trusted.contains(player.getName())){
				if(settingsManager.chunk.getBoolean("ClaimedChunks."+ChunkName+".flags.trusted.interact")){
					return false;
				}else{
					player.sendMessage(plugin.prefix+"Trusted players cannot place blocks here!");
					return true;
				}
			}else{
				player.sendMessage(plugin.prefix+"You cannot interact here!");
				player.sendMessage(ChatColor.GOLD+"This claim belongs to: "+ChatColor.RED+owner);
				return true;
			}
		}
		return false;
	}

}
