package de.servermanager.delta203.plugin;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.servermanager.delta203.api.AceLoungeAPI;
import de.servermanager.delta203.plugin.commands.SM_Commands;
import de.servermanager.delta203.plugin.files.SM_ConfigYML;
import de.servermanager.delta203.plugin.listeners.SM_Listener_Chat;
import de.servermanager.delta203.plugin.listeners.SM_Listener_Join;
import de.servermanager.delta203.plugin.listeners.SM_Listener_Login;
import de.servermanager.delta203.plugin.listeners.SM_Listener_Quit;
import de.servermanager.delta203.plugin.mysql.AceLoungeMySQl;

public class ServerManager extends JavaPlugin {

	/*
	 * 
	 * Hauptklasse des ServerManagers
	 * Alles Pluginbezogende läuft über
	 * ServerManager.getInstance()
	 * 
	 * Nützliche AceLounge Funktionen sind
	 * in AceLoungeAPI
	 * 
	 * Für MySQl nutze
	 * AceLoungeMySQl
	 * 
	 */
	
	private static ServerManager plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		AceLoungeAPI.serverVersion = Bukkit.getServer().getClass().getPackage().getName().replace("org.bukkit.craftbukkit.", "");
		
		SM_ConfigYML.create();
		SM_ConfigYML.load();
		
		AceLoungeMySQl.connect();
		AceLoungeMySQl.createTable();
		
		registerListeners();
		registerCommands();
		
		Bukkit.broadcastMessage(AceLoungeAPI.prefix + "§aServerManager erfolgreich geladen.");	
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			
			@Override
			public void run() {
				Bukkit.getConsoleSender().sendMessage("§a###########################################");
				Bukkit.getConsoleSender().sendMessage("§a#");
				Bukkit.getConsoleSender().sendMessage("§a#	Server version: §e" + AceLoungeAPI.serverVersion);
				Bukkit.getConsoleSender().sendMessage("§a#	§eAceLounge.de §aMainsystem by §6Delta203");
				Bukkit.getConsoleSender().sendMessage("§a#");
				Bukkit.getConsoleSender().sendMessage("§a###########################################");
			}
		}, 20 * 3);
	}

	@Override
	public void onDisable() {
		for(Player all : Bukkit.getOnlinePlayers()) {
			all.sendMessage("§f[§eAceLounge§f] §6Der §eServer §6startet neu!");
			AceLoungeAPI.sendOtherServer(all, AceLoungeAPI.lobbies[new Random().nextInt(AceLoungeAPI.lobbies.length)]);
		}
	}
	
	/**
	 * Globale Plugin Instanz
	 * @return ServerManager
	 */
	public static ServerManager getInstance() {
		return plugin;
	}
	
	private void registerListeners() {
		Bukkit.getPluginManager().registerEvents(new SM_Listener_Chat(), this);
		Bukkit.getPluginManager().registerEvents(new SM_Listener_Join(), this);
		Bukkit.getPluginManager().registerEvents(new SM_Listener_Login(), this);
		Bukkit.getPluginManager().registerEvents(new SM_Listener_Quit(), this);
	}
	
	private void registerCommands() {
		getCommand("logindatas").setExecutor(new SM_Commands());
		getCommand("changepassword").setExecutor(new SM_Commands());
		getCommand("luck").setExecutor(new SM_Commands());
		getCommand("ping").setExecutor(new SM_Commands());
		getCommand("stop").setExecutor(new SM_Commands());
		getCommand("console").setExecutor(new SM_Commands());
		getCommand("icanhasbukkit").setExecutor(new SM_Commands());
		getCommand("trololol").setExecutor(new SM_Commands());
		getCommand("vanish").setExecutor(new SM_Commands());
		
		getCommand("help").setExecutor(new SM_Commands());
		getCommand("?").setExecutor(new SM_Commands());
		getCommand("coins").setExecutor(new SM_Commands());
		getCommand("diamonds").setExecutor(new SM_Commands());
		getCommand("rules").setExecutor(new SM_Commands());
		getCommand("regeln").setExecutor(new SM_Commands());
		getCommand("terms").setExecutor(new SM_Commands());
		getCommand("apply").setExecutor(new SM_Commands());
		getCommand("bewerben").setExecutor(new SM_Commands());
		getCommand("yt").setExecutor(new SM_Commands());
		getCommand("youtuber").setExecutor(new SM_Commands());
		getCommand("discord").setExecutor(new SM_Commands());
	}
}