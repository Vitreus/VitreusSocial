package online.vitreusmc.vitreusSocial.event.milestone;

import java.util.Random;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import online.vitreusmc.vitreusSocial.VitreusSocial;

public class MilestoneMessenger {

	private static VitreusSocial plugin = JavaPlugin.getPlugin(VitreusSocial.class);
	private static Server server = plugin.getServer();
	
	public static void congratulatePlayer(Player player, Milestone milestone) {
		String message = generateCongradulationsMessage(player, milestone);
		server.broadcastMessage(message);
	}
	
	private static String generateCongradulationsMessage(Player player, Milestone milestone) {
		Random random = new Random();
		String message;
		
		switch (random.nextInt(4)) {
			case 1:
				message = String.format("Wow-zers! %s has finally reached their %s! That's some flame right there!", player.getName(), translateMilestone(milestone));
				break;
			case 2:
				message = String.format("%s has achieved an achievement most signifigant, their %s!", player.getName(), translateMilestone(milestone));
				break;
			case 3:
				message = String.format("%s is a master of time, space, and Minecraft. They have acheived their %s. Try and beat that!", player.getName(), translateMilestone(milestone));
				break;
			default:
				message = String.format("Congratulations! %s has reached their %s!", player.getName(), translateMilestone(milestone));
				break;
		}
		
		return ChatColor.GOLD + message;
	}
	
	private static String translateMilestone(Milestone milestone) {
		
		switch (milestone) {
			case TWELVEHOUR:
				return "Twelve Hour Milestone";
			case TWENTYFOURHOUR:
				return "Twenty Four Hour Milestone";
			default:
				return "Milestone";
		}
	}
}
