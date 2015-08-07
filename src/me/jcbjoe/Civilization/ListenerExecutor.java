package me.jcbjoe.Civilization;

import me.jcbjoe.Civilization.ListenerMethods.BlockBreakPlace;
import me.jcbjoe.Civilization.ListenerMethods.ChunkEntranceLeave;
import me.jcbjoe.Civilization.ListenerMethods.ExplosionProtection;
import me.jcbjoe.Civilization.ListenerMethods.Interaction;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class ListenerExecutor implements Listener{
	
	private Main plugin;
	ListenerExecutor(Main plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onBlockBeak(BlockBreakEvent event){
		if(BlockBreakPlace.blockBreakHandler(event, plugin) == true){
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		if(BlockBreakPlace.blockPlaceHandler(event, plugin) == true){
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event){
		if(Interaction.interact(event, plugin) == true){
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void PlayerChangeChunk(PlayerMoveEvent event){
		ChunkEntranceLeave.ChunkChange(event, plugin);
	}
	@EventHandler
	public void Explosion(EntityExplodeEvent event){
		if(ExplosionProtection.protectClaim(event, plugin) == true){
			event.setCancelled(true);
		}
	}

}
