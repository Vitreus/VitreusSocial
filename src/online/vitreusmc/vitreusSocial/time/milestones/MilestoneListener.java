package online.vitreusmc.vitreusSocial.time.milestones;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import online.vitreusmc.vitreusSocial.VitreusSocial;

public class MilestoneListener implements Listener {

	@EventHandler
	public void onMilestone(MilestoneEvent event) {
		
		if (event.isCancelled()) {
			return;
		}
		
		Player player = event.getPlayer();
		Server server = Bukkit.getServer();
		JavaPlugin plugin = JavaPlugin.getPlugin(VitreusSocial.class);
		String milestoneMessage;
		ChatColor chatColor;
		
		try {
			chatColor = player.getScoreboard().getEntryTeam(player.getName()).getColor();
		} catch (Exception exception) {
			chatColor = ChatColor.WHITE;
		}
		
		switch (event.getMilestone()) {
			case TWELVEHOUR: {
				player.addAttachment(plugin, "milestone.12", true);
				milestoneMessage = chatColor + player.getName() + ChatColor.GOLD + " has reached their" + ChatColor.BOLD + " 12 Hour " + ChatColor.RESET + ChatColor.GOLD + "Milestone! Congrats!";
				break;
			}
			case TWENTYFOURHOUR: {
				player.addAttachment(plugin, "milestone.24", true);
				milestoneMessage = ChatColor.GOLD + "Wow-wey! " + chatColor + player.getName() + ChatColor.GOLD + " has reached a" + ChatColor.BOLD + " 24 Hour " + ChatColor.RESET + ChatColor.GOLD + "Milestone! Congratulations, you're over the hill!";
				break;
			}
			default: {
				return;
			}
		}
		
		server.broadcastMessage(milestoneMessage);
	}
	
}
