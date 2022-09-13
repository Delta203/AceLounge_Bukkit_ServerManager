package de.servermanager.delta203.api;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import com.viaversion.viaversion.api.Via;

import de.servermanager.delta203.plugin.ServerManager;
import de.servermanager.delta203.plugin.mysql.AceLoungeMySQl;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class AceLoungeAPI {

	/*
	 * 
	 * @AceLoungeAPI
	 * zuletzt geändert am 08.09.2022
	 * Enthält alle nützlichen Sachen
	 * für den Server
	 * 
	 */
	
	public static String serverVersion = "N/A";
	public static String prefix = "§f[§eAceLounge§f] §7";
	
	public static String[] lobbies = {"Lobby-1",};
	
	/**
	 * Bekomme die Minecraft Protocol
	 * Version ID eines Spielers
	 * -> Die Clientversion die der Spieler
	 *    Benutzt als ProtocolID
	 * https://wiki.vg/Protocol_version_numbers
	 * @param p
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static int getPlayerProtocolVersion(Player p) {
		return Via.getAPI().getPlayerVersion(p);
	}
	
	/**
	 * Sende einen Spieler auf
	 * auf einen anderen Server (BungeeCord)
	 * @param p
	 * @param server
	 */
	public static void sendOtherServer(Player p, String server) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		 
		try {
			out.writeUTF("Connect");
			out.writeUTF(server);
		}catch(IOException ex) {}
		p.sendPluginMessage(ServerManager.getInstance(), "BungeeCord", b.toByteArray());
	}
	
	/**
	 * JoinPower eines Spielers
	 * JoinPower basiert auf den Rang
	 * @param p
	 * @return
	 */
	public static int getJoinPower(Player p) {
		@SuppressWarnings("deprecation")
		PermissionGroup[] pg = PermissionsEx.getUser(p).getGroups();
		return pg[0].getRank();
	}
	
	/**
	 * Setze die JoinPower eines Spieler
	 * @param uuid
	 * @param input
	 */
	public static void setJoinpower(String uuid, int input) {
		try {
			PreparedStatement ps = AceLoungeMySQl.con.prepareStatement("UPDATE Spieler_Daten SET Joinpower = ? WHERE SpielerUUID = ?");
			ps.setInt(1, input);
			ps.setString(2, uuid);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Checke ob der Spieler registriert ist
	 * @param uuid
	 * @return
	 */
	public static boolean isRegistered(String uuid) {
		if(getCoins(uuid) != -1) return true;
		return false;
	}
	
	/**
	 * Get UUID by Name
	 * @param name
	 * @return
	 */
	public static String getUUIDByName(String name) {
		try {
			PreparedStatement ps = AceLoungeMySQl.con.prepareStatement("SELECT SpielerUUID FROM Spieler_Daten WHERE SpielerNAME = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getString("SpielerUUID");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Get Name by UUID
	 * @param uuid
	 * @return
	 */
	public static String getNameByUUID(String uuid) {
		try {
			PreparedStatement ps = AceLoungeMySQl.con.prepareStatement("SELECT SpielerNAME FROM Spieler_Daten WHERE SpielerUUID = ?");
			ps.setString(1, uuid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getString("SpielerNAME");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Password eines Spielers
	 * @param uuid
	 * @return
	 */
	public static String getPasswort(String uuid) {
		try {
			PreparedStatement ps = AceLoungeMySQl.con.prepareStatement("SELECT Passwort FROM Spieler_Daten WHERE SpielerUUID = ?");
			ps.setString(1, uuid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getString("Passwort");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Setze das Passwort eines Spielers
	 * @param uuid
	 * @param input
	 */
	public static void setPasswort(String uuid, String input) {
		try {
			PreparedStatement ps = AceLoungeMySQl.con.prepareStatement("UPDATE Spieler_Daten SET Passwort = ? WHERE SpielerUUID = ?");
			ps.setString(1, input);
			ps.setString(2, uuid);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Rang eines Spieler
	 * @param uuid
	 * @return
	 */
	public static String getRang(String uuid) {
		try {
			PreparedStatement ps = AceLoungeMySQl.con.prepareStatement("SELECT Rang FROM Spieler_Daten WHERE SpielerUUID = ?");
			ps.setString(1, uuid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getString("Rang");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Setze den Rang eines Spielers
	 * @param uuid
	 * @param input
	 */
	public static void setRang(String uuid, String input) {
		try {
			PreparedStatement ps = AceLoungeMySQl.con.prepareStatement("UPDATE Spieler_Daten SET Rang = ? WHERE SpielerUUID = ?");
			ps.setString(1, input);
			ps.setString(2, uuid);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Coins eines Spielers
	 * @param uuid
	 * @return
	 */
	public static int getCoins(String uuid) {
		try {
			PreparedStatement ps = AceLoungeMySQl.con.prepareStatement("SELECT Coins FROM Spieler_Daten WHERE SpielerUUID = ?");
			ps.setString(1, uuid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt("Coins");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * Setze die Coins eines Spielers
	 * @param uuid
	 * @param input
	 */
	public static void addCoins(String uuid, int input) {
		try {
			PreparedStatement ps = AceLoungeMySQl.con.prepareStatement("UPDATE Spieler_Daten SET Coins = ? WHERE SpielerUUID = ?");
			ps.setInt(1, getCoins(uuid) + input);
			ps.setString(2, uuid);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Jetons eines Spielers
	 * @param uuid
	 * @return
	 */
	public static int getJetons(String uuid) {
		try {
			PreparedStatement ps = AceLoungeMySQl.con.prepareStatement("SELECT Jetons FROM Spieler_Daten WHERE SpielerUUID = ?");
			ps.setString(1, uuid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt("Jetons");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * Setze die Jetons eines Spielers
	 * @param uuid
	 * @param input
	 */
	public static void addJetons(String uuid, int input) {
		try {
			PreparedStatement ps = AceLoungeMySQl.con.prepareStatement("UPDATE Spieler_Daten SET Jetons = ? WHERE SpielerUUID = ?");
			ps.setInt(1, getJetons(uuid) + input);
			ps.setString(2, uuid);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Diamanten eines Spielers
	 * @param uuid
	 * @return
	 */
	public static int getDiamonds(String uuid) {
		try {
			PreparedStatement ps = AceLoungeMySQl.con.prepareStatement("SELECT Diamanten FROM Spieler_Daten WHERE SpielerUUID = ?");
			ps.setString(1, uuid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt("Diamanten");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * Setze die Diamanten eines Spielers
	 * @param uuid
	 * @param input
	 */
	public static void addDiamonds(String uuid, int input) {
		try {
			PreparedStatement ps = AceLoungeMySQl.con.prepareStatement("UPDATE Spieler_Daten SET Diamanten = ? WHERE SpielerUUID = ?");
			ps.setInt(1, getDiamonds(uuid) + input);
			ps.setString(2, uuid);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
