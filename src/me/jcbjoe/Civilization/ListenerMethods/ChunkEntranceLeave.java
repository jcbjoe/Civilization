package me.jcbjoe.Civilization.ListenerMethods;

import java.util.List;

import me.jcbjoe.Civilization.Main;
import me.jcbjoe.Civilization.settingsManager;

import org.bukkit.event.player.PlayerMoveEvent;

public class ChunkEntranceLeave {

	public static boolean ChunkChange(PlayerMoveEvent event, Main plugin) {
		if(event.getFrom().getChunk() != event.getTo().getChunk()){
			String ChunkNameFrom = "X"+event.getFrom().getChunk().getX()+"Z"+event.getFrom().getChunk().getZ();
			if(plugin.chunks.containsKey(ChunkNameFrom)){
				if(settingsManager.chunk.getBoolean("ClaimedChunks."+ChunkNameFrom+".flags.farewellEnabled")){
					String farewell = settingsManager.chunk.getString("ClaimedChunks."+ChunkNameFrom+".flags.farewell");
					event.getPlayer().sendMessage(farewell);
				}
			}
			String ChunkNameTo = "X"+event.getTo().getChunk().getX()+"Z"+event.getTo().getChunk().getZ();
			if(plugin.chunks.containsKey(ChunkNameTo)){
				List<String> trusted = plugin.trusted.get(ChunkNameTo);
				if(trusted.contains(event.getPlayer().getName())){
					if(settingsManager.chunk.getBoolean("ClaimedChunks."+ChunkNameTo+".flags.trusted.enter") == false){
						event.setCancelled(true);
						event.getPlayer().sendMessage(plugin.prefix+"You cannot enter this claim!");
						return true;
					}
				}
				if(settingsManager.chunk.getBoolean("ClaimedChunks."+ChunkNameTo+".flags.all.enter") == false){
					event.setCancelled(true);
					event.getPlayer().sendMessage(plugin.prefix+"You cannot enter this claim1");
					return true;
				}
				if(settingsManager.chunk.getBoolean("ClaimedChunks."+ChunkNameTo+".flags.greetingEnabled")){
					String greeting = settingsManager.chunk.getString("ClaimedChunks."+ChunkNameTo+".flags.greeting");
					event.getPlayer().sendMessage(greeting);
				}
			}
		}
		return false;
	}
}
