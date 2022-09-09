package de.servermanager.delta203.plugin.commands;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.servermanager.delta203.api.AceLoungeAPI;
import de.servermanager.delta203.plugin.ServerManager;

public class SM_Commands implements CommandExecutor {

	public static HashMap<Player, Integer> vanish = new HashMap<>();
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		/*
		 * Help
		 */
		if(cmd.getName().equalsIgnoreCase("help") || cmd.getName().equalsIgnoreCase("?")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(p.isOp()) return false;
				p.sendMessage(AceLoungeAPI.prefix + "§7| §6Informationen über das Netzwerk");
				p.sendMessage("§b/lobby /hub /l §3Kehre zur Lobby zurück");
				p.sendMessage("§b/friend §3Manage deine Freunde");
				p.sendMessage("§b/party §3Erstelle eine Party und spiele mit anderen Spielern");
				p.sendMessage("§b/report §3Melde einen Spieler");
				p.sendMessage("§b/bugreport §3Melde einen Spielfehler");
				p.sendMessage("§eWebseite: §6§lAceLounge.de");
				p.sendMessage("§eDiscord: §6§lAceLounge.de/discord");
				p.sendMessage("§eBewerben: §6§lAceLounge.de/apply");
				p.sendMessage("§eShop: §6§lshop.AceLounge.de");
				p.sendMessage("§eFür weitere Hilfen stehen wir Dir immer bereit!");
			}
		}
		/*
		 * Coins
		 */
		else if(cmd.getName().equalsIgnoreCase("coins")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				p.sendMessage(AceLoungeAPI.prefix + "§7Du hast §6" + AceLoungeAPI.getCoins(p.getUniqueId().toString()) + " Coins §7und §a" + AceLoungeAPI.getJetons(p.getUniqueId().toString()) + " Jetons");
			}
		}
		/*
		 * Regeln
		 */
		else if(cmd.getName().equalsIgnoreCase("rules") || cmd.getName().equalsIgnoreCase("regeln")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				p.sendMessage(AceLoungeAPI.prefix + "§7Du findest unter §6AceLounge.de/rules §7unsere Regeln.");
			}
		}
		/*
		 * Datenschutz
		 */
		else if(cmd.getName().equalsIgnoreCase("terms")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				p.sendMessage(AceLoungeAPI.prefix + "§7Du findest unter §6AceLounge.de/terms §7unsere Datenschutzerklärung.");
			}
		}
		/*
		 * Bewerben
		 */
		else if(cmd.getName().equalsIgnoreCase("apply") || cmd.getName().equalsIgnoreCase("bewerben")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				p.sendMessage(AceLoungeAPI.prefix + "§7Du kannst dich unter §6AceLounge.de/apply §7bewerben.");
			}
		}
		/*
		 * YouTuber
		 */
		else if(cmd.getName().equalsIgnoreCase("yt") || cmd.getName().equalsIgnoreCase("youtuber")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				p.sendMessage(AceLoungeAPI.prefix + "§7| §fAnforderungen für die VIP-Ränge:");
				p.sendMessage("");
				p.sendMessage("§e§lVIP§r§7:");
				p.sendMessage("§7Mindestens §e300 §7Abonnenten");
				p.sendMessage("§7Mindestens §e1 §7Video vom Server");
				p.sendMessage("§7Regelmäßig auf dem Server spielen und Videos machen");
				p.sendMessage("");
				p.sendMessage("§5§lContent-Creator§r§7:");
				p.sendMessage("§7Mindestens §d500 §7Abonnenten");
				p.sendMessage("§7Mindestens §d1 §7Video vom Server");
				p.sendMessage("§7Regelmäßig auf dem Server spielen und Videos machen");
				p.sendMessage("");
				p.sendMessage("§7Wenn du einen §6VIP-Rang §7beantragen möchtest, dann wende dich an ein Teammitglied.");
			}
		}
		/*
		 * Discord
		 */
		else if(cmd.getName().equalsIgnoreCase("discord")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				p.sendMessage(AceLoungeAPI.prefix + "§7Unseren Discord erreichst du unter §6AceLounge.de/discord§7.");
			}
		}
		/*
		 * Luck (Troll command)
		 */
		else if(cmd.getName().equalsIgnoreCase("luck")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				p.sendMessage("§8+§55 Karma");
			}
		}
		/*
		 * Troll (Troll command)
		 */
		else if(cmd.getName().equalsIgnoreCase("trololol")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				p.sendMessage("░░░░░░▄▄▄▄▀▀▀▀▀▀▀▀▄▄▄▄▄");
				p.sendMessage("░░░░░█░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▀▀▄");
				p.sendMessage("░░░░█░░░▒▒▒▒▒▒░░░░░░░░▒▒▒░█");
				p.sendMessage("░░░█░░░░░░▄██▀▄▄░░░░░▄▄▄░░░█");
				p.sendMessage("░▄▀▒▄▄▄▒░█▀▀▀▀▄▄█░░░██▄▄█░░█▄");
				p.sendMessage("█░▒█▒▄░▀▄▄▄▀░░░░░░░░█░░░▒▒▒▒█");
				p.sendMessage("█░▒█░█▀▄▄░░░░░█▀░░░░▀▄░░▄▀▀▀█");
				p.sendMessage("░█░▀▄░█▄░█▀▄▄░▀░▀▀░▄▄▀░░░░█░█");
				p.sendMessage("░░█░░░▀▄▀█▄▄░█▀▀▀▄▄▄▄▀▀█▀██░█");
				p.sendMessage("░░█░░░░██░░▀█▄▄▄█▄▄█▄▄▄█▄█░█");
				p.sendMessage("░░░█░░░░▀▀▄░█░░░█░▀▀█▀█▀██░█");
				p.sendMessage("░░░░▀▄░░░░░▀▀▄▄▄█▄▄▄█▄█▄▀░░█");
				p.sendMessage("░░░░░░▀▄▄░▒▒▒▒░░░░░░░░░░▒░░░█");
				p.sendMessage("░░░░░░░░░▀▀▀▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█");
			}
		}
		/*
		 * Vanish
		 */
		else if(cmd.getName().equalsIgnoreCase("vanish")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(!p.hasPermission("ab.ban")) {
					p.sendMessage("§cYou do not have permission to execute this command!");
					return false;
				}
				if(vanish.containsKey(p)) {
					Bukkit.getScheduler().cancelTask(vanish.get(p));
					vanish.remove(p);
					for(Player all : Bukkit.getOnlinePlayers()) all.showPlayer(p);
					p.sendMessage(AceLoungeAPI.prefix + "§cDu bist nicht mehr im vanish.");
				}else {
					p.sendMessage(AceLoungeAPI.prefix + "§aDu bist nun im vanish.");
					vanish.put(p, Bukkit.getScheduler().scheduleSyncRepeatingTask(ServerManager.getInstance(), new Runnable() {
						
						@Override
						public void run() {
							for(Player all : Bukkit.getOnlinePlayers()) all.hidePlayer(p);
						}
					}, 0, 20));
				}
			}
		}
		/*
		 * ping
		 */
		else if(cmd.getName().equalsIgnoreCase("ping")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				int ping = 0;
				if(AceLoungeAPI.serverVersion.equals("v1_18_R2")) ping = ((org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer)p).getHandle().e;
				else if(AceLoungeAPI.serverVersion.equals("v1_8_R3")) ping = ((org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer)p).getHandle().ping;
				p.sendMessage(AceLoungeAPI.prefix + "§7Dein Ping: §6" + ping + "ms");
			}
		}
		/*
		 * stop
		 */
		else if(cmd.getName().equalsIgnoreCase("stop")) {
			if(sender.hasPermission("*")) {
				sender.sendMessage("§cYou do not have permission to execute this command!");
				return false;
			}
			
			for(Player all : Bukkit.getOnlinePlayers()) {
				all.sendMessage(AceLoungeAPI.prefix + "§6Der §eServer §6startet neu!");
				AceLoungeAPI.sendOtherServer(all, AceLoungeAPI.lobbies[new Random().nextInt(AceLoungeAPI.lobbies.length)]);
			}
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(ServerManager.getInstance(), new Runnable() {
				
				@Override
				public void run() {
					for(Player all : Bukkit.getOnlinePlayers()) all.kickPlayer(AceLoungeAPI.prefix + "§6Der §eServer §6startet neu...");
				}
			}, 20);
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(ServerManager.getInstance(), () -> Bukkit.getServer().shutdown(), 40);
		}
		/*
		 * logindatas
		 */
		else if(cmd.getName().equalsIgnoreCase("logindatas")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				p.sendMessage(AceLoungeAPI.prefix + "§6Dein Benutzername ist: §e" + p.getName() + " §6und das Passwort ist: §a" + AceLoungeAPI.getPasswort(p.getUniqueId().toString()));
				p.sendMessage(AceLoungeAPI.prefix + "§7Du kannst ein Password mit §e/changepassword <passwort> §7ändern. Merke dir dein geändertes Passwort gut, denn es wird mit SHA-256 gehasht und in der Datenbank gespeichert.");
			}
		}
		/*
		 * changepassword
		 */
		else if(cmd.getName().equalsIgnoreCase("changepassword")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(args.length == 1) {
					String password = args[0];
					try {
						MessageDigest digest = MessageDigest.getInstance("SHA-256");
						byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
						StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
					    for (int i = 0; i < encodedhash.length; i++) {
					        String hex = Integer.toHexString(0xff & encodedhash[i]);
					        if(hex.length() == 1) hexString.append('0');
					        hexString.append(hex);
					    }
					    String hashedpassword = hexString.toString();
					    AceLoungeAPI.setPasswort(p.getUniqueId().toString(), hashedpassword);
					    p.sendMessage(AceLoungeAPI.prefix + "§aDu hast dein Passwort zu §e" + hashedpassword + " §ageändert.");
					    p.sendMessage(AceLoungeAPI.prefix + "§7Du kannst mit §e/logindatas §7dein gehashtes Passwort sehen und mit §e/changepassword §7jeder Zeit dein Passwort ändern.");
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
						p.sendMessage(AceLoungeAPI.prefix + "§cEs ist ein Fehler beim Passwort wechseln aufgetreten.");
					}
				}else {
					p.sendMessage(AceLoungeAPI.prefix + "§cDu musst ein Passwort angeben: §e/changepassword <passwort>");
				}
			}
		}
		return false;
	}
}