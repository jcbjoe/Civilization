package me.jcbjoe.Civilization.CommandMethods;

import java.util.ArrayList;
import java.util.List;

import me.jcbjoe.Civilization.Main;
import me.jcbjoe.Civilization.settingsManager;
import net.milkbowl.vault.economy.EconomyResponse;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CustomClaimMax {

	static List<String> emptyClaims = new ArrayList<String>();
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	public static boolean buying(CommandSender sender, Command cmd,
			String desc, String[] args, Main plugin) {
		if(args.length !=2){
			sender.sendMessage(plugin.prefix+"Format: /civilization buyclaims [amount]");
		}else{
			if(isInteger(args[1])){
				int cost = plugin.getConfig().getInt("Extra Chunks Cost");
				if(plugin.playerdata.containsKey(sender.getName())){
					if(Main.economy.getBalance(sender.getName()) >= cost){
						EconomyResponse r = Main.economy.withdrawPlayer(sender.getName(), cost);
						if(r.transactionSuccess()) {
							settingsManager.player.set("Players."+sender.getName()+".customMax", args[1]);
							settingsManager.savePlayerFile();
							sender.sendMessage(plugin.prefix+args[1]+" claims's have been bought and added to your max claims!");
							return true;
						} else {
							sender.sendMessage(String.format("An error occured: %s", r.errorMessage));
							return true;
						}
					}else{
						sender.sendMessage(plugin.prefix+"You do not have enough money to purchase more chunks!");
						sender.sendMessage("You have: "+Main.economy.getBalance(sender.getName())+". and you need: "+cost);
						return true;
					}
					
				}else{
					if(Main.economy.getBalance(sender.getName()) >= cost){
						EconomyResponse r = Main.economy.withdrawPlayer(sender.getName(), cost);
						if(r.transactionSuccess()) {
							settingsManager.player.set("Players."+sender.getName()+".chunks", emptyClaims);
							settingsManager.player.set("Players."+sender.getName()+".claims", 0);
							settingsManager.player.set("Players."+sender.getName()+".customMax", args[1]);
							settingsManager.savePlayerFile();
							sender.sendMessage(plugin.prefix+args[1]+" claims's have been bought and added to your max claims!");
							return true;
						} else {
							sender.sendMessage(String.format("An error occured: %s", r.errorMessage));
							return true;
						}
					}else{
						sender.sendMessage(plugin.prefix+"You do not have enough money to purchase more chunks!");
						sender.sendMessage("You have: "+Main.economy.getBalance(sender.getName())+". and you need: "+cost);
						return true;
					}
				}
			}else{
				sender.sendMessage(plugin.prefix+"The argument you entered needs to be a number!");
				return true;
			}
		}
		return false;
	}

}
