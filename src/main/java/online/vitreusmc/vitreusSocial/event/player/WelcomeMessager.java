package online.vitreusmc.vitreusSocial.event.player;

import java.util.Random;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import online.vitreusmc.vitreusSocial.VitreusSocial;

public class WelcomeMessager {

	private static JavaPlugin plugin = JavaPlugin.getPlugin(VitreusSocial.class);
	private static Server server = plugin.getServer();
	
	public static void welcomePlayer(Player player) {
		String message = generateWelcomeMessage(player);
		server.broadcastMessage(message);
	}
	
	private static String generateWelcomeMessage(Player player) {
		Random random = new Random();
		String message;
		
		switch (random.nextInt(4)) {
			case 1:
				message = String.format("Hey, hi, hoy, hoi, hello there! Welcome to Vitreus %s!", player.getName());
				break;
			case 2:
				message = String.format("It's %s's first time playing on Vitreus! Give them a piping hot welcome!", player.getName());
				break;
			case 3:
				message = String.format("What's up? Are you the new person? Well, welcome to Vitreus %s!", player.getName());
				break;
			default:
				message = String.format("Welcome to Vitreus %s!", player.getName());
				break;
		}
		
		return ChatColor.GOLD + message;
	}
	
}
