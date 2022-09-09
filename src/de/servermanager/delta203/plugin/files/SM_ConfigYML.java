package de.servermanager.delta203.plugin.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.servermanager.delta203.plugin.ServerManager;

public class SM_ConfigYML {

	private static File file = new File(ServerManager.getInstance().getDataFolder(), "config.yml");
	private static FileConfiguration config = YamlConfiguration.loadConfiguration(file);

	public static FileConfiguration get() {
		return config;
	}
	
	public static void load() {
		try {
			config.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static void save() {
		try {
			config.save(file);
		} catch (IOException e) {
	    	e.printStackTrace();
		}
	}

	public static void create() {
		if(!ServerManager.getInstance().getDataFolder().exists()) {
			ServerManager.getInstance().getDataFolder().mkdir();
		}
		
		try {
	        if(!file.exists()) {
	        	java.nio.file.Files.copy(ServerManager.getInstance().getResource("config.yml"), file.toPath());	
	        	config = YamlConfiguration.loadConfiguration(file);
	    	}else {
	    		config = YamlConfiguration.loadConfiguration(file);
	    	}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
