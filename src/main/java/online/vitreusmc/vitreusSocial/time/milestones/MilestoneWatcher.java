package online.vitreusmc.vitreusSocial.time.milestones;

import java.util.Collection;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import online.vitreusmc.vitreusSocial.time.util.TimeStatistic;

public class MilestoneWatcher implements Runnable {

	@Override
	public void run() {
		Server server = Bukkit.getServer();
		Collection<? extends Player> players = server.getOnlinePlayers();
		
		for (Player player : players) {
			int hours = TimeStatistic.getHours(player);
			
			if (hours == 12 && !player.hasPermission("milestone.12")) {
				MilestoneEvent event = new MilestoneEvent(player, Milestone.TWELVEHOUR);
				server.getPluginManager().callEvent(event);
			} else if (hours == 24 && !player.hasPermission("milestone.24")) {
				MilestoneEvent event = new MilestoneEvent(player, Milestone.TWENTYFOURHOUR);
				server.getPluginManager().callEvent(event);
			}
		}
	}
	
}
