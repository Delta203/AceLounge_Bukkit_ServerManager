package de.servermanager.delta203.plugin.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SM_Extras_GetMySQl {

	/*
	 * 
	 * Wir nur für die
	 * Titel im Chat
	 * Anzeige genutzt
	 * 
	 */
	
	public static ArrayList<String> getExtrasNameFromGroup(String uuid, String group) {
		ArrayList<String> result = new ArrayList<>();
		try {
			PreparedStatement ps = AceLoungeMySQl.con.prepareStatement("SELECT Name FROM Lobby_Extras WHERE SpielerUUID = ? AND Gruppe = ?");
			ps.setString(1, uuid);
			ps.setString(2, group);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) { // -> normally 1 call
				result.add(rs.getString("Name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static boolean extraIsActiveFromName(String uuid, String group, String name) {
		try {
			PreparedStatement ps = AceLoungeMySQl.con.prepareStatement("SELECT Active FROM Lobby_Extras WHERE SpielerUUID = ? AND Gruppe = ? AND Name = ?");
			ps.setString(1, uuid);
			ps.setString(2, group);
			ps.setString(3, name);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) { // -> normally 1 call
				if(rs.getInt("Active") == 1) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static ArrayList<String> getActiveExtrasNameFromGroup(String uuid, String group) {
		ArrayList<String> result = new ArrayList<>();
		for(String all : getExtrasNameFromGroup(uuid, group)) {
			if(extraIsActiveFromName(uuid, group, all)) {
				result.add(all);
			}
		}
		return result;
	}
}
