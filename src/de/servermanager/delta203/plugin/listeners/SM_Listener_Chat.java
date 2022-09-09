package de.servermanager.delta203.plugin.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.servermanager.delta203.api.AceLoungeAPI;
import de.servermanager.delta203.plugin.mysql.SM_Extras_GetMySQl;

public class SM_Listener_Chat implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		
		String title = "";
		for(String titles : SM_Extras_GetMySQl.getActiveExtrasNameFromGroup(p.getUniqueId().toString(), "Titel")) {
			title = titles;
			break;
		}
		
		if(AceLoungeAPI.getNameByUUID(p.getUniqueId().toString()).equals(p.getName())) { // -> player is not nicked
			if(!title.equals("")) {
				e.setFormat(p.getDisplayName() + " §8(" + title.replace("&", "§") + "§8) §7» §f%2$s");
				return;
			}
		}
		
		e.setFormat(p.getDisplayName() + " §7» §f%2$s");
	}
}
