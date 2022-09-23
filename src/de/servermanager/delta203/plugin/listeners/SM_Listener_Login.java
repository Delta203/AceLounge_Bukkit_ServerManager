package de.servermanager.delta203.plugin.listeners;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import de.dytanic.cloudnet.ext.bridge.server.BridgeServerHelper;
import de.servermanager.delta203.api.AceLoungeAPI;
import de.servermanager.delta203.plugin.utils.SM_PermissionManager;

public class SM_Listener_Login implements Listener {

	/*
	 * 
	 * Login Listener
	 * Kicke Nonpremis
	 * Teammitglieder kicken Prmeis...
	 * 
	 * Setze die permissions für 1.18 spieler
	 * Update CloudNet Permissions
	 * 
	 */
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onLogin(PlayerLoginEvent e) {
		SM_PermissionManager.updateRankAndJoinPower(e.getPlayer());
		int maxPlayers = Bukkit.getMaxPlayers();
		
		/* kicke den spieler nur wenn lobbyphase */
		if(AceLoungeAPI.serverVersion.equals("v1_8_R3")) {
			maxPlayers = BridgeServerHelper.getMaxPlayers();
			String motd = BridgeServerHelper.getMotd();
			if(motd.equalsIgnoreCase("setup") || motd.equalsIgnoreCase("ingame") || motd.equalsIgnoreCase("restart")) {
				return;
			}
		}
		/* kicke den spieler nur wenn lobbyphase */
		
		if(Bukkit.getOnlinePlayers().size() >= maxPlayers) {
			
			if(AceLoungeAPI.getJoinPower(e.getPlayer()) == 0) { // only for O(n) 1
				e.disallow(Result.KICK_OTHER, "§cDer Server ist voll. Du benötigst den §6§lPremium §r§cRang §8(§ehttps://shop.AceLounge.de/§8) §cum den Server trotzdem betreten zu können.");
				return;
			}
			
			if(AceLoungeAPI.getJoinPower(e.getPlayer()) == 1) { // premi kicks nonpremis
				ArrayList<Player> nonpremis = new ArrayList<>();
				for(Player all : Bukkit.getOnlinePlayers()) {
					if(AceLoungeAPI.getJoinPower(all) == 0) {
						nonpremis.add(all);
					}
				}
				
				if(nonpremis.size() != 0) {
					Player target = nonpremis.get(new Random().nextInt(nonpremis.size()));
					target.kickPlayer("§cDu wurdest vom Server gekickt um einem §6§lPremium §r§cSpieler §8(§ehttps://shop.AceLounge.de/§8) §cden Platz frei zu machen.");
					e.allow();
				}else {
					e.disallow(Result.KICK_OTHER, "§c§lDer Server ist voll!");
				}
				return;
			}
			else if(AceLoungeAPI.getJoinPower(e.getPlayer()) == 2) { // vip kicks premi & nonpremi
				ArrayList<Player> nonpremis = new ArrayList<>();
				for(Player all : Bukkit.getOnlinePlayers()) {
					if(AceLoungeAPI.getJoinPower(all) == 0) {
						nonpremis.add(all);
					}
				}
				if(nonpremis.size() != 0) {
					Player target = nonpremis.get(new Random().nextInt(nonpremis.size()));
					target.kickPlayer("§cDu wurdest vom Server gekickt um einem §6§lPremium §r§cSpieler §8(§ehttps://shop.AceLounge.de/§8) §cden Platz frei zu machen.");
					e.allow();
				}else {
					ArrayList<Player> premis = new ArrayList<>();
					for(Player all : Bukkit.getOnlinePlayers()) {
						if(AceLoungeAPI.getJoinPower(all) == 1) {
							premis.add(all);
						}
					}
					if(premis.size() != 0) {
						Player target = premis.get(new Random().nextInt(premis.size()));
						target.kickPlayer("§cDu wurdest vom Server gekickt um einem §e§lVIP §r§cSpieler §8(§ehttps://shop.AceLounge.de/§8) §cden Platz frei zu machen.");
						e.allow();
					}else {
						e.disallow(Result.KICK_OTHER, "§c§lDer Server ist voll!");
					}
				}
				return;
			}else if(AceLoungeAPI.getJoinPower(e.getPlayer()) == 3) { // yt kicks vip, premi, non
				ArrayList<Player> nonpremis = new ArrayList<>();
				for(Player all : Bukkit.getOnlinePlayers()) {
					if(AceLoungeAPI.getJoinPower(all) == 0) {
						nonpremis.add(all);
					}
				}
				if(nonpremis.size() != 0) {
					Player target = nonpremis.get(new Random().nextInt(nonpremis.size()));
					target.kickPlayer("§cDu wurdest vom Server gekickt um einem §6§lPremium §r§cSpieler §8(§ehttps://shop.AceLounge.de/§8) §cden Platz frei zu machen.");
					e.allow();
				}else {
					ArrayList<Player> premis = new ArrayList<>();
					for(Player all : Bukkit.getOnlinePlayers()) {
						if(AceLoungeAPI.getJoinPower(all) == 1) {
							premis.add(all);
						}
					}
					if(premis.size() != 0) {
						Player target = premis.get(new Random().nextInt(premis.size()));
						target.kickPlayer("§cDu wurdest vom Server gekickt um einem §e§lVIP §r§cSpieler §8(§ehttps://shop.AceLounge.de/§8) §cden Platz frei zu machen.");
						e.allow();
					}else {
						ArrayList<Player> vips = new ArrayList<>();
						for(Player all : Bukkit.getOnlinePlayers()) {
							if(AceLoungeAPI.getJoinPower(all) == 2) {
								vips.add(all);
							}
						}
						if(vips.size() != 0) {
							Player target = vips.get(new Random().nextInt(vips.size()));
							target.kickPlayer("§cDu wurdest vom Server gekickt um einem §5§lYouTuber/Streamer§c, §4§lTeammitglied §cden Platz frei zu machen.");
							e.allow();
						}else {
							e.disallow(Result.KICK_OTHER, "§c§lDer Server ist voll!");
						}
					}
				}
				return;
			}else if(AceLoungeAPI.getJoinPower(e.getPlayer()) >= 4) { // team kicks everyone
				ArrayList<Player> nonpremis = new ArrayList<>();
				for(Player all : Bukkit.getOnlinePlayers()) {
					if(AceLoungeAPI.getJoinPower(all) == 0) {
						nonpremis.add(all);
					}
				}
				if(nonpremis.size() != 0) {
					Player target = nonpremis.get(new Random().nextInt(nonpremis.size()));
					target.kickPlayer("§cDu wurdest vom Server gekickt um einem §6§lPremium §r§cSpieler §8(§ehttps://shop.AceLounge.de/§8) §cden Platz frei zu machen.");
					e.allow();
				}else {
					ArrayList<Player> premis = new ArrayList<>();
					for(Player all : Bukkit.getOnlinePlayers()) {
						if(AceLoungeAPI.getJoinPower(all) == 1) {
							premis.add(all);
						}
					}
					if(premis.size() != 0) {
						Player target = premis.get(new Random().nextInt(premis.size()));
						target.kickPlayer("§cDu wurdest vom Server gekickt um einem §e§lVIP §r§cSpieler §8(§ehttps://shop.AceLounge.de/§8) §cden Platz frei zu machen.");
						e.allow();
					}else {
						ArrayList<Player> vips = new ArrayList<>();
						for(Player all : Bukkit.getOnlinePlayers()) {
							if(AceLoungeAPI.getJoinPower(all) == 2) {
								vips.add(all);
							}
						}
						if(vips.size() != 0) {
							Player target = vips.get(new Random().nextInt(vips.size()));
							target.kickPlayer("§cDu wurdest vom Server gekickt um einem §5§lYouTuber/Streamer§c, §4§lTeammitglied §cden Platz frei zu machen.");
							e.allow();
						}else {
							ArrayList<Player> yts = new ArrayList<>();
							for(Player all : Bukkit.getOnlinePlayers()) {
								if(AceLoungeAPI.getJoinPower(all) == 3) {
									yts.add(all);
								}
							}
							if(yts.size() != 0) {
								Player target = yts.get(new Random().nextInt(yts.size()));
								target.kickPlayer("§cDu wurdest vom Server gekickt um einem §4§lTeammitglied §cden Platz frei zu machen.");
								e.allow();
							}else {
								e.disallow(Result.KICK_OTHER, "§c§lDer Server ist voll!");
							}
						}
					}
				}
				return;
			}
			/* Normally useless call */
			else {
				e.disallow(Result.KICK_OTHER, "§cDer Server ist voll. Du benötigst den §6§lPremium §r§cRang §8(§ehttps://shop.AceLounge.de/§8) §cum den Server trotzdem betreten zu können.");
			}
		}
	}
}
