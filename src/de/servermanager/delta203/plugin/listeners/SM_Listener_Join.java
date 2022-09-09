package de.servermanager.delta203.plugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import de.servermanager.delta203.api.AceLoungeAPI;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class SM_Listener_Join implements Listener {

	@EventHandler(priority = EventPriority.HIGH)
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		sendScoreboard(p);
	}
	
	@SuppressWarnings("deprecation")
	public void sendScoreboard(Player p) {
		Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
		
		PermissionGroup[] pg = PermissionsEx.getUser(p).getGroups();
		String prefix = pg[0].getPrefix().replace("&", "§");
		String groupName = pg[0].getName();
		String teamName = sortIDToPrefix(pg[0].getRank()) + groupName;
		ChatColor color = prefixToColor(prefix);
		
		//p.sendMessage(groupName + " " + getJoinPower(p) + " " + usergroups.toString());
		
		Team team;
		try {
			if(sb.getTeam(teamName) == null) sb.registerNewTeam(teamName);
		}catch(Exception ex) {
			sb.registerNewTeam(teamName);
		}
		team = sb.getTeam(teamName);
		
		team.setPrefix(prefix);
		team.addPlayer(p);
		p.setDisplayName(team.getPrefix() + p.getName());
		
		if(AceLoungeAPI.serverVersion.equals("v1_18_R2")) team.setColor(color);
		
		p.setScoreboard(sb);
	}
	
	private String sortIDToPrefix(int id) {
		if(id == 100) return "0000";
		else if(id == 9) return "0001"; //sr dev
		else if(id == 8) return "0002";
		else if(id == 7) return "0003";	//sr mod
		else if(id == 6) return "0004";
		else if(id == 5) return "0005";	//sup
		else if(id == 4) return "0006";
		else if(id == 3) return "0007";	//yt
		else if(id == 2) return "0008";	//vip
		else if(id == 1) return "0009";	//premi
		else return "zzzzz";
	}
	
	private ChatColor prefixToColor(String prefix) {
		if(prefix.startsWith("§4")) return ChatColor.DARK_RED;
		else if(prefix.startsWith("§b")) return ChatColor.AQUA;
		else if(prefix.startsWith("§c")) return ChatColor.RED;
		else if(prefix.startsWith("§1")) return ChatColor.DARK_BLUE;
		else if(prefix.startsWith("§3")) return ChatColor.DARK_AQUA;
		else if(prefix.startsWith("§5")) return ChatColor.DARK_PURPLE;
		else if(prefix.startsWith("§e")) return ChatColor.YELLOW;
		else if(prefix.startsWith("§6")) return ChatColor.GOLD;
		return ChatColor.GREEN;
	}
}
