package online.vitreusmc.vitreusSocial.time.milestones;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import online.vitreusmc.vitreusSocial.VitreusSocial;
import online.vitreusmc.vitreusSocial.time.util.TimeStatistic;

public class PlayerJoinListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		int hoursPlayed = TimeStatistic.getHours(player);
		JavaPlugin plugin = JavaPlugin.getPlugin(VitreusSocial.class);
		
		if (hoursPlayed >= 24) {
			player.addAttachment(plugin, "milestone.24", true);
		} else if (hoursPlayed >= 12) {
			player.addAttachment(plugin, "milestone.12", true);
		}
	}
	
}
