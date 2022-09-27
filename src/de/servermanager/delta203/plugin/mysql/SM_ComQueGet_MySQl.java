package de.servermanager.delta203.plugin.mysql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SM_ComQueGet_MySQl {
	
	public static void insertDatas(String command) {
		try {
			PreparedStatement ps = AceLoungeMySQl.con.prepareStatement("INSERT INTO AB_CommandQuery (SenderUUID,Command) VALUES (?,?)");
			ps.setString(1, "Console");
			ps.setString(2, command);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
