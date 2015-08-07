package me.jcbjoe.Civilization.CommandMethods;

import me.jcbjoe.Civilization.Main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RegenClaim {

	public static boolean regenerate(CommandSender sender, Command cmd,
			String desc, String[] args, Main plugin) {
		Player player = (Player)sender;
		int ChunkX = player.getLocation().getChunk().getX();
		int ChunkZ = player.getLocation().getChunk().getZ();
		String ChunkName = "X"+ChunkX+"Z"+ChunkZ;
		if(plugin.chunks.containsKey(ChunkName)){
			player.sendMessage(plugin.prefix+"This chunk is claimed! To regenerate it it must be free.");
			return true;
		}else{
			player.getWorld().regenerateChunk(ChunkX, ChunkZ);
			player.sendMessage(plugin.prefix+"Chunk successfully regenerated!");
			return true;
		}
	}

}
