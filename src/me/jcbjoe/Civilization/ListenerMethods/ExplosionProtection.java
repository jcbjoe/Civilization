package me.jcbjoe.Civilization.ListenerMethods;

import me.jcbjoe.Civilization.Main;

import org.bukkit.block.Block;
import org.bukkit.event.entity.EntityExplodeEvent;

public class ExplosionProtection {

	public static boolean protectClaim(EntityExplodeEvent event, Main plugin) {
		for(Block blocks : event.blockList()){
			int ChunkX = blocks.getLocation().getChunk().getX();
			int ChunkZ = blocks.getLocation().getChunk().getZ();
			String ChunkName = "X"+ChunkX+"Z"+ChunkZ;
			if(plugin.chunks.containsKey(ChunkName)){
				return true;
			}
		}
		return false;
		
	}

}
