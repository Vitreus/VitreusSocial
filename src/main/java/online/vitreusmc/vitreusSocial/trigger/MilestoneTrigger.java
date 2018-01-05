package online.vitreusmc.vitreusSocial.trigger;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import online.vitreusmc.vitreusConnect.VitreusDB;
import online.vitreusmc.vitreusConnect.object.FlagList.PlayerFlag;
import online.vitreusmc.vitreusConnect.object.VitreusPlayer;
import online.vitreusmc.vitreusSocial.VitreusSocial;
import online.vitreusmc.vitreusSocial.event.milestone.Milestone;
import online.vitreusmc.vitreusSocial.event.milestone.MilestoneEvent;
import online.vitreusmc.vitreusSocial.event.milestone.MilestoneMessenger;

public class MilestoneTrigger extends BukkitRunnable implements Listener {

	private static VitreusSocial plugin = JavaPlugin.getPlugin(VitreusSocial.class);
	private static Server server = plugin.getServer();
	private static Logger logger = plugin.getLogger();
	
	@Override
	public void run() {
		Collection<Player> players = (Collection<Player>) server.getOnlinePlayers();
		
		players.forEach(new Consumer<Player>() {
			@Override
			public void accept(Player player) {
				
				if (player.getStatistic(Statistic.PLAY_ONE_TICK) >= 20*60*60*12) {
					MilestoneEvent event = new MilestoneEvent(player, Milestone.TWELVEHOUR);
					server.getPluginManager().callEvent(event);
				}
				
				if (player.getStatistic(Statistic.PLAY_ONE_TICK) >= 20*60*60*24) {
					MilestoneEvent event = new MilestoneEvent(player, Milestone.TWENTYFOURHOUR);
					server.getPluginManager().callEvent(event);
				}
			}
		});
	}
	
	@EventHandler
	public void onMilestoneEvent(MilestoneEvent event) {
		VitreusDB database = plugin.getDatabase();
		Milestone milestone = event.getMilestone();
		Player player = event.getPlayer();
		VitreusPlayer vitreusPlayer = database.getPlayer(player);
		
		if (vitreusPlayer.hasFlag(PlayerFlag.TWELVE_HOUR_MILESTONE) || vitreusPlayer.hasFlag(PlayerFlag.TWENTY_FOUR_HOUR_MILESTONE)) {
			event.setCancelled(true);
			return;
		}
		
		switch (milestone) {
			case TWELVEHOUR:
				vitreusPlayer.addFlag(PlayerFlag.TWELVE_HOUR_MILESTONE);
				break;
			case TWENTYFOURHOUR:
				vitreusPlayer.addFlag(PlayerFlag.TWENTY_FOUR_HOUR_MILESTONE);
				break;
		}
		
		database.savePlayer(vitreusPlayer);
		MilestoneMessenger.congratulatePlayer(player, milestone);
	}	
	
}
