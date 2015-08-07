package me.jcbjoe.Civilization;

import me.jcbjoe.Civilization.CommandMethods.Claiming;
import me.jcbjoe.Civilization.CommandMethods.CustomClaimMax;
import me.jcbjoe.Civilization.CommandMethods.Flags;
import me.jcbjoe.Civilization.CommandMethods.Info;
import me.jcbjoe.Civilization.CommandMethods.RegenClaim;
import me.jcbjoe.Civilization.CommandMethods.TrustUnTrust;
import me.jcbjoe.Civilization.CommandMethods.UnClaim;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandsExecutor implements CommandExecutor{

	private Main plugin;
	public CommandsExecutor(Main plugin){
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String desc,
			String[] args) {
		if(cmd.getName().equalsIgnoreCase("Civilization")){
			if(args[0].equalsIgnoreCase("claim")){
				if(sender.hasPermission("civilization.claim")){
					if(Claiming.claim(sender,cmd,desc,args,plugin) == true){
						return true;
					}
				}
			}
			if(args[0].equalsIgnoreCase("unclaim")){
				if(sender.hasPermission("civilization.unclaim")){
					if(UnClaim.unClaimChunk(sender,cmd,desc,args,plugin) == true){
						return true;
					}
				}
			}
			if(args[0].equalsIgnoreCase("trust")){
				if(sender.hasPermission("civilization.trust")){
					if(TrustUnTrust.trust(sender,cmd,desc,args,plugin) == true){
						return true;
					}
				}
			}
			if(args[0].equalsIgnoreCase("untrust")){
				if(sender.hasPermission("civilization.untrust")){
					if(TrustUnTrust.untrust(sender,cmd,desc,args,plugin) == true){
						return true;
					}
				}
			}
			if(args[0].equalsIgnoreCase("unclaimall")){
				if(sender.hasPermission("civilization.unclaimall")){
					if(UnClaim.unClaimAll(sender,cmd,desc,args,plugin) == true){
						return true;
					}
				}
			}
			if(args[0].equalsIgnoreCase("flag") ){
				if(sender.hasPermission("civilization.flag")){
					if(Flags.flagCommand(sender,cmd,desc,args,plugin) == true){
						return true;
					}
				}
			}
			if(args[0].equalsIgnoreCase("info") ){
				if(sender.hasPermission("civilization.info")){
					if(Info.infoCommand(sender,cmd,desc,args,plugin) == true){
						return true;
					}
				}
			}
			if(args[0].equalsIgnoreCase("me") ){
				if(sender.hasPermission("civilization.me")){
					if(Info.me(sender,cmd,desc,args,plugin) == true){
						return true;
					}
				}
			}
			if(args[0].equalsIgnoreCase("buyclaims") ){
				if(sender.hasPermission("civilization.buyclaims")){
					if(CustomClaimMax.buying(sender,cmd,desc,args,plugin) == true){
						return true;
					}
				}
			}
			//admin stuff
			if(args[0].equalsIgnoreCase("admin")){
				if(args[1].equalsIgnoreCase("override")){
					if(sender.hasPermission("civilization.override")){
						if(plugin.override.contains(sender.getName())){
							plugin.override.remove(sender.getName());
							sender.sendMessage(plugin.prefix+"Admin override toggled "+ChatColor.RED+"off");
							return true;
						}else{
							plugin.override.add(sender.getName());
							sender.sendMessage(plugin.prefix+"Admin override toggled "+ChatColor.GREEN+"on");
							return true;
						}
					}
				}
				if(args[1].equalsIgnoreCase("forceunclaim")){
					if(sender.hasPermission("civilization.forceunclaim")){
						if(UnClaim.forceunclaim(sender,cmd,desc,args,plugin) == true){
							return true;
						}
					}
				}
				if(args[1].equalsIgnoreCase("regenclaim")){
					if(sender.hasPermission("civilization.regenclaim")){
						if(RegenClaim.regenerate(sender,cmd,desc,args,plugin) == true){
							return true;
						}
					}
				}
				if(args[1].equalsIgnoreCase("adminclaim")){
					if(sender.hasPermission("civilization.adminclaim")){
						if(Claiming.adminclaim(sender,cmd,desc,args,plugin) == true){
							return true;
						}
					}
				}else{
				
				}
			}
		}
		return false;
	}

}
