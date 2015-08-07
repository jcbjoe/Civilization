package me.jcbjoe.Civilization;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class settingsManager {

	private settingsManager() { }
	
	static settingsManager instance = new settingsManager();
	
	public static settingsManager getInstance() {
		return instance;
	}
	
	Plugin p;
	
	public static FileConfiguration chunk;
	static File chunkfile;
	
	public static FileConfiguration player;
	static File playerfile;
	
	public static void setup(Plugin p) {
		
		chunkfile = new File(p.getDataFolder(), "chunks.yml");
		
		if (!chunkfile.exists()) {
			try {
				chunkfile.createNewFile();
			}
			catch (IOException e) {
				Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create chunks.yml!");
			}
		}
		
		chunk = YamlConfiguration.loadConfiguration(chunkfile);
		
		playerfile = new File(p.getDataFolder(), "playerdata.yml");
		
		if (!playerfile.exists()) {
			try {
				playerfile.createNewFile();
			}
			catch (IOException e) {
				Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create playerdata.yml!");
			}
		}
		
		player = YamlConfiguration.loadConfiguration(playerfile);
	}
	
	public FileConfiguration getchunkdata() {
		
		return chunk;
	}
	
	public FileConfiguration getplayerdata() {
		
		return player;
	}
	
	public static void saveChunkFile() {
		try {
			chunk.save(chunkfile);
		}
		catch (IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save chunks.yml!");
		}
	}
	
	public static void savePlayerFile() {
		try {
			player.save(playerfile);
		}
		catch (IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save playerdata.yml!");
		}
	}
	
	public void reloadChunkFile() {
		chunk = YamlConfiguration.loadConfiguration(chunkfile);
	}
	
	public void reloadPlayerFile() {
		player = YamlConfiguration.loadConfiguration(playerfile);
	}
}