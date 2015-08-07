package me.jcbjoe.Civilization.CommandMethods;

import me.jcbjoe.Civilization.Main;
import me.jcbjoe.Civilization.settingsManager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Flags {

	public static boolean flagCommand(CommandSender sender, Command cmd,
			String desc, String[] args, Main plugin) {
		Player player = (Player)sender;
		int PChunkX = player.getLocation().getChunk().getX();
		int PChunkZ = player.getLocation().getChunk().getZ();
		String ChunkName = "X"+PChunkX+"Z"+PChunkZ;
		if(plugin.chunks.containsKey(ChunkName)){
			String owner = plugin.chunks.get(ChunkName);
			if(owner.equals(player.getName())){
				if(args.length == 1){
					player.sendMessage(plugin.prefix+"Format: /civilization flag [flag] [all/trusted] [allow/deny]");
					return true;
					
				}
				if(args.length == 2){
					player.sendMessage(plugin.prefix+"Format: /civilization flag [flag] [all/trusted] [allow/deny]");
					return true;
				}
				if(args[1].equalsIgnoreCase("place")){
					if(args.length != 4){
						player.sendMessage(plugin.prefix+"Format: /civilization flag place [all/trusted] [allow/deny]");
						return true;
					}else{
						if(args[2].equalsIgnoreCase("all")){
							if(args[3].equalsIgnoreCase("allow") || args[3].equalsIgnoreCase("true")){
								settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.all.place", true);
								settingsManager.saveChunkFile();
								player.sendMessage(plugin.prefix+"Place flag set to allow for all!");
								return true;
							}
							if(args[3].equalsIgnoreCase("deny") || args[3].equalsIgnoreCase("false")){
								settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.all.place", false);
								settingsManager.saveChunkFile();
								player.sendMessage(plugin.prefix+"Place flag set to deny for all!");
								return true;
							}else{
								player.sendMessage(plugin.prefix+"Error: "+args[3]+" not accepted! Must be "+ChatColor.RED+"allow"+ChatColor.GOLD+" or "+ChatColor.RED+"deny");
								return true;
							}
						}
						if(args[2].equalsIgnoreCase("trusted")){
							if(args[3].equalsIgnoreCase("allow") || args[3].equalsIgnoreCase("true")){
								settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.trusted.place", true);
								settingsManager.saveChunkFile();
								player.sendMessage(plugin.prefix+"Place flag set to allow for trusted!");
								return true;
							}
							if(args[3].equalsIgnoreCase("deny") || args[3].equalsIgnoreCase("false")){
								settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.trusted.place", false);
								settingsManager.saveChunkFile();
								player.sendMessage(plugin.prefix+"Place flag set to deny for trusted!");
								return true;
							}else{
								player.sendMessage(plugin.prefix+"Error: "+args[3]+" not accepted! Must be "+ChatColor.RED+"allow"+ChatColor.GOLD+" or "+ChatColor.RED+"deny");
								return true;
							}
						}else{
							player.sendMessage(plugin.prefix+"Error: "+args[3]+" not accepted! Must be "+ChatColor.RED+"all"+ChatColor.GOLD+"or"+ChatColor.RED+"trusted");
							return true;
						}
					}
				}
				if(args[1].equalsIgnoreCase("destroy")){
					if(args.length != 4){
						player.sendMessage(plugin.prefix+"Format: /civilization flag destroy [all/trusted] [allow/deny]");
						return true;
					}else{
						if(args[2].equalsIgnoreCase("all")){
							if(args[3].equalsIgnoreCase("allow") || args[3].equalsIgnoreCase("true")){
								settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.all.destroy", true);
								settingsManager.saveChunkFile();
								player.sendMessage(plugin.prefix+"Destroy flag set to allow for all!");
								return true;
							}
							if(args[3].equalsIgnoreCase("deny") || args[3].equalsIgnoreCase("false")){
								settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.all.destroy", false);
								settingsManager.saveChunkFile();
								player.sendMessage(plugin.prefix+"Destroy flag set to deny for all!");
								return true;
							}else{
								player.sendMessage(plugin.prefix+"Error: "+args[3]+" not accepted! Must be "+ChatColor.RED+"allow"+ChatColor.GOLD+" or "+ChatColor.RED+"deny");
								return true;
							}
						}
						if(args[2].equalsIgnoreCase("trusted")){
							if(args[3].equalsIgnoreCase("allow") || args[3].equalsIgnoreCase("true")){
								settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.trusted.destroy", true);
								settingsManager.saveChunkFile();
								player.sendMessage(plugin.prefix+"Destroy flag set to allow for trusted!");
								return true;
							}
							if(args[3].equalsIgnoreCase("deny") || args[3].equalsIgnoreCase("false")){
								settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.trusted.destroy", false);
								settingsManager.saveChunkFile();
								player.sendMessage(plugin.prefix+"Destroy flag set to deny for trusted!");
								return true;
							}else{
								player.sendMessage(plugin.prefix+"Error: "+args[3]+" not accepted! Must be "+ChatColor.RED+"allow"+ChatColor.GOLD+" or "+ChatColor.RED+"deny");
								return true;
							}
						}else{
							player.sendMessage(plugin.prefix+"Error: "+args[3]+" not accepted! Must be "+ChatColor.RED+"all"+ChatColor.GOLD+"or"+ChatColor.RED+"trusted");
							return true;
						}
					}
				}
				if(args[1].equalsIgnoreCase("interact")){
					if(args.length != 4){
						player.sendMessage(plugin.prefix+"Format: /civilization flag interact [all/trusted] [allow/deny]");
						return true;
					}else{
						if(args[2].equalsIgnoreCase("all")){
							if(args[3].equalsIgnoreCase("allow") || args[3].equalsIgnoreCase("true")){
								settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.all.interact", true);
								settingsManager.saveChunkFile();
								player.sendMessage(plugin.prefix+"Interact flag set to allow for all!");
								return true;
							}
							if(args[3].equalsIgnoreCase("deny") || args[3].equalsIgnoreCase("false")){
								settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.all.interact", false);
								settingsManager.saveChunkFile();
								player.sendMessage(plugin.prefix+"Interact flag set to deny for all!");
								return true;
							}else{
								player.sendMessage(plugin.prefix+"Error: "+args[3]+" not accepted! Must be "+ChatColor.RED+"allow"+ChatColor.GOLD+" or "+ChatColor.RED+"deny");
								return true;
							}
						}
						if(args[2].equalsIgnoreCase("trusted")){
							if(args[3].equalsIgnoreCase("allow") || args[3].equalsIgnoreCase("true")){
								settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.trusted.interact", true);
								settingsManager.saveChunkFile();
								player.sendMessage(plugin.prefix+"Interact flag set to allow for trusted!");
								return true;
							}
							if(args[3].equalsIgnoreCase("deny") || args[3].equalsIgnoreCase("false")){
								settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.trusted.interact", false);
								settingsManager.saveChunkFile();
								player.sendMessage(plugin.prefix+"Interact flag set to deny for trusted!");
								return true;
							}else{
								player.sendMessage(plugin.prefix+"Error: "+args[3]+" not accepted! Must be "+ChatColor.RED+"allow"+ChatColor.GOLD+" or "+ChatColor.RED+"deny");
								return true;
							}
						}else{
							player.sendMessage(plugin.prefix+"Error: "+args[3]+" not accepted! Must be "+ChatColor.RED+"all"+ChatColor.GOLD+"or"+ChatColor.RED+"trusted");
							return true;
						}
					}
				}
				if(args[1].equalsIgnoreCase("entrance")){
					if(args.length != 4){
						player.sendMessage(plugin.prefix+"Format: /civilization flag entrance [all/trusted] [allow/deny]");
						return true;
					}else{
						if(args[2].equalsIgnoreCase("all")){
							if(args[3].equalsIgnoreCase("allow") || args[3].equalsIgnoreCase("true")){
								settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.all.enter", true);
								settingsManager.saveChunkFile();
								player.sendMessage(plugin.prefix+"Entrance flag set to allow for all!");
								return true;
							}
							if(args[3].equalsIgnoreCase("deny") || args[3].equalsIgnoreCase("false")){
								settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.all.enter", false);
								settingsManager.saveChunkFile();
								player.sendMessage(plugin.prefix+"Entrance flag set to deny for all!");
								return true;
							}else{
								player.sendMessage(plugin.prefix+"Error: "+args[3]+" not accepted! Must be "+ChatColor.RED+"allow"+ChatColor.GOLD+" or "+ChatColor.RED+"deny");
								return true;
							}
						}
						if(args[2].equalsIgnoreCase("trusted")){
							if(args[3].equalsIgnoreCase("allow") || args[3].equalsIgnoreCase("true")){
								settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.trusted.enter", true);
								settingsManager.saveChunkFile();
								player.sendMessage(plugin.prefix+"Entrance flag set to allow for trusted!");
								return true;
							}
							if(args[3].equalsIgnoreCase("deny") || args[3].equalsIgnoreCase("false")){
								settingsManager.chunk.set("ClaimedChunks."+ChunkName+".flags.trusted.enter", false);
								settingsManager.saveChunkFile();
								player.sendMessage(plugin.prefix+"Entrance flag set to deny for trusted!");
								return true;
							}else{
								player.sendMessage(plugin.prefix+"Error: "+args[3]+" not accepted! Must be "+ChatColor.RED+"allow"+ChatColor.GOLD+" or "+ChatColor.RED+"deny");
								return true;
							}
						}else{
							player.sendMessage(plugin.prefix+"Error: "+args[3]+" not accepted! Must be "+ChatColor.RED+"all"+ChatColor.GOLD+"or"+ChatColor.RED+"trusted");
							return true;
						}
					}
				}
				if(args[1].equalsIgnoreCase("greeting")){
					if(args[2].equalsIgnoreCase("enable")){
						if(args.length != 4){
							player.sendMessage(plugin.prefix+"Format: /civilization flag greeting enable [message]");
							return true;
						}else{
							String greeting = "";   
							for(int i = 4; i < args.length; i++){ 
							    String arg = args[i] + " ";
							    greeting = greeting + arg;
							}
							settingsManager.chunk.set("ClaimedChunks."+ChunkName+".greetingEnabled", true);
							settingsManager.chunk.set("ClaimedChunks."+ChunkName+".greeting", greeting);
							settingsManager.saveChunkFile();
							player.sendMessage(plugin.prefix+"The greeting for this claim has been enabled and set to:");
							player.sendMessage(ChatColor.GOLD+greeting);
							return true;
						}
					}
					if(args[2].equalsIgnoreCase("disable")){
						if(args.length != 3){
							player.sendMessage(plugin.prefix+"Format: /civilization flag greeting disable");
							return true;
						}else{
							settingsManager.chunk.set("ClaimedChunks."+ChunkName+".greetingEnabled", false);
							settingsManager.saveChunkFile();
							player.sendMessage(plugin.prefix+"The greeting for this claim has been disabled");
							return true;
						}
					}
				}
				if(args[1].equalsIgnoreCase("farewell")){
					if(args[2].equalsIgnoreCase("enable")){
						if(args.length != 4){
							player.sendMessage(plugin.prefix+"Format: /civilization flag farewell enable [message]");
							return true;
						}else{
							String greeting = "";   
							for(int i = 4; i < args.length; i++){ 
							    String arg = args[i] + " ";
							    greeting = greeting + arg;
							}
							settingsManager.chunk.set("ClaimedChunks."+ChunkName+".farewellEnabled", true);
							settingsManager.chunk.set("ClaimedChunks."+ChunkName+".farewell", greeting);
							settingsManager.saveChunkFile();
							player.sendMessage(plugin.prefix+"The farewell for this claim has been enabled and set to:");
							player.sendMessage(ChatColor.GOLD+greeting);
							return true;
						}
					}
					if(args[2].equalsIgnoreCase("disable")){
						if(args.length != 3){
							player.sendMessage(plugin.prefix+"Format: /civilization flag farewell disable");
							return true;
						}else{
							settingsManager.chunk.set("ClaimedChunks."+ChunkName+".farewellEnabled", false);
							settingsManager.saveChunkFile();
							player.sendMessage(plugin.prefix+"The farewell for this claim has been disabled");
							return true;
						}
					}
				}else{
					player.sendMessage(plugin.prefix+"Flag not recognised! Avaliable Flags:");
					player.sendMessage(ChatColor.GOLD+"place, destroy, interact, entrance, greeting, farewell");
					return true;
				}
			}else{
				player.sendMessage(plugin.prefix+"You are not the owner of this chunk");
				return true;
			}
			
		}else{
			player.sendMessage(plugin.prefix+"This chunk is not claimed");
			return true;
		}
		return false;
	}

}
