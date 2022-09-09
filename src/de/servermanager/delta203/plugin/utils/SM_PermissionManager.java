package de.servermanager.delta203.plugin.utils;

import org.bukkit.entity.Player;

import de.servermanager.delta203.api.AceLoungeAPI;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class SM_PermissionManager {

	/**
	 * Update CloudNet permissions
	 * @param p
	 */
	public static void updateRankAndJoinPower(Player p) {
		// Update group + joinpower
		@SuppressWarnings("deprecation")
		PermissionGroup[] pg = PermissionsEx.getUser(p).getGroups();
		String groupName = pg[0].getName();
		int joinPower = pg[0].getRank();
		AceLoungeAPI.setRang(p.getUniqueId().toString(), groupName);
		AceLoungeAPI.setJoinpower(p.getUniqueId().toString(), joinPower);
		
		System.out.println("=> Update MySQl: " + p.getName() + ", group: " + groupName + ", joinpower: " + joinPower);
	}
	
	/*
	 * 
	 * Eigenes Permissionsystem für
	 * den 1.18.2 Citybuild Server
	 * 
	 * ABGELÖST DURCH PEX
	 * 
	 */
}
