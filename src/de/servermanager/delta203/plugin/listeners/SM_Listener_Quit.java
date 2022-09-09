package de.servermanager.delta203.plugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.servermanager.delta203.plugin.commands.SM_Commands;

public class SM_Listener_Quit implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		act(e.getPlayer());
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onKick(PlayerKickEvent e) {
		if(e.getReason().equalsIgnoreCase("disconnect.spam")) {
			e.setCancelled(true);
			return;
		}
		act(e.getPlayer());
	}

	private void act(Player p) {
		if(SM_Commands.vanish.containsKey(p)) {
			Bukkit.getScheduler().cancelTask(SM_Commands.vanish.get(p));
			SM_Commands.vanish.remove(p);
		}
	}
}
