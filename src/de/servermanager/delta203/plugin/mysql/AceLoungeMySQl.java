package de.servermanager.delta203.plugin.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import de.servermanager.delta203.plugin.ServerManager;
import de.servermanager.delta203.plugin.files.SM_ConfigYML;

public class AceLoungeMySQl {

	/*
	 * 
	 * MySQl Init Klasse
	 * Verbinden und Auflösen
	 * 
	 */
	
	public static Connection con;
	
	/**
	 * Stelle die MySQl Verbindung her
	 * @return
	 */
	public static boolean connect() {
		if(!isConnected()) {
			try {
				con = DriverManager.getConnection("jdbc:mysql://" + SM_ConfigYML.get().getString("MySQl_url") + ":" + SM_ConfigYML.get().getInt("MySQl_port") +"/" + SM_ConfigYML.get().getString("MySQl_database") + "?autoReconnect=true", SM_ConfigYML.get().getString("MySQl_user"), SM_ConfigYML.get().getString("MySQl_password"));
				System.out.println("========== ServerManager MySQl has been connected.");
				return true;
			} catch (final SQLException e) {
				Bukkit.getScheduler().scheduleSyncDelayedTask(ServerManager.getInstance(), () -> e.printStackTrace() ,20);
			}
		}
		return false;
	}
	
	/**
	 * Löse die MySQl Verbindung auf
	 * @return
	 */
	public static boolean disconnect() {
		if(isConnected()) {
			try {
				con.close();
				System.out.println("MySQl connection canceled.");
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * Prüfe ob eine MySQl Verbindung besteht
	 * @return
	 */
	public static boolean isConnected() {
		return(con != null);
	}
	
	/**
	 * Erstelle die Spielerdaten Tabelle
	 */
	public static void createTable() {
		try {
			con.prepareStatement("CREATE TABLE IF NOT EXISTS Spieler_Daten ("
					+ "SpielerUUID VARCHAR(100),"
					+ "SpielerNAME VARCHAR(100),"
					+ "Passwort VARCHAR(100),"
					+ "Coins INT(16),"
					+ "Jetons INT(16),"
					+ "OnlineTime LONG,"
					+ "Rang VARCHAR(100),"
					+ "Joinpower Int(16),"
					+ "RankEnd VARCHAR(100)"
					+ ")").executeUpdate();
			System.out.println("========== ServerManager MySQl table has been created.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
