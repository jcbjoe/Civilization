package me.jcbjoe.Civilization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	public static Economy economy = null;
	public String prefix = ChatColor.GOLD+"[Civilization] ";
	public static Permission permission = null;
	//chunk then ownername
	public HashMap<String, String> chunks = new HashMap<String, String>();
	//playername then amount of claims
	public HashMap<String, Integer> playerdata = new HashMap<String, Integer>();
	//trusted
	public HashMap<String, List<String>> trusted = new HashMap<String, List<String>>();
	//override
	public List<String> override = new ArrayList<String>();
	
	public void onEnable() {
		this.saveDefaultConfig();
		settingsManager.setup(this);
		System.out.println("[Civiliazation] Files loaded/created successfully!");
		
		if (settingsManager.chunk.getConfigurationSection("ClaimedChunks") != null){
			for(String chunkname : settingsManager.chunk.getConfigurationSection("ClaimedChunks").getKeys(false)){
				chunks.put(chunkname, settingsManager.chunk.getString("ClaimedChunks."+chunkname+".owner"));
				trusted.put(chunkname, settingsManager.chunk.getStringList("ClaimedChunks."+chunkname+".trusted"));
			}
		}
		System.out.println("[Civiliazation] Claimed Chunks Loaded into Memory!");
		if (settingsManager.player.getConfigurationSection("Players") != null){
			for(String playername : settingsManager.player.getConfigurationSection("Players").getKeys(false)){
				playerdata.put(playername, settingsManager.player.getInt("Players."+playername+".claims"));	
			}
		}
		System.out.println("[Civiliazation] PlayerData Loaded into Memory!");
		setupEconomy();
		setupPermissions();
		System.out.println("[Civiliazation] Vault Hooked Successfully!");
		this.getCommand("civilization").setExecutor(new CommandsExecutor(this));
		this.getServer().getPluginManager().registerEvents(new ListenerExecutor(this), this);
		System.out.println("[Civiliazation] Commands and Listeners enabled!");
	}
	
	
	private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
	
	private boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }
}
