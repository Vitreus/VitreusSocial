package com.vitreusmc.social.activity;

import java.util.Collection;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.mongodb.morphia.dao.BasicDAO;

import com.vitreusmc.lib.common.Milestone;
import com.vitreusmc.lib.database.entities.VitreusPlayer;
import com.vitreusmc.social.VitreusSocial;

import net.md_5.bungee.api.ChatColor;

public class ActivityMonitor extends BukkitRunnable implements Listener {

	private static final String IDLE_KICK_MESSAGE = ChatColor.GRAY + "" + ChatColor.BOLD + "You have been kicked for being idle";
	private static final String IDLE_NOTIFICATION_MESSAGE = "%s" + ChatColor.GRAY + " is now " + ChatColor.RESET + ChatColor.GRAY + ChatColor.BOLD + "%s" + ChatColor.RESET + ChatColor.GRAY + "!";
	private static final String IDLE_SUFFIX = ChatColor.GRAY + " [Idle]";
	private static final int IDLE_START_TIME = 5;
	private static final int IDLE_FINISH_TIME = 60;
	
	private static VitreusSocial plugin = JavaPlugin.getPlugin(VitreusSocial.class);
	private static BasicDAO<VitreusPlayer, String> playerDAO = new BasicDAO<>(VitreusPlayer.class, plugin.getDatastore());
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		setActive(player, true);
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		setActive(player, false);
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		setActive(player, true);
	}
	
	@Override
	public void run() {
		Collection<? extends Player> onlinePlayers = plugin.getServer().getOnlinePlayers();
		
		onlinePlayers.forEach(player -> {
			checkIdle(player);
			checkMilestone(player);
		});
	}
	
	private static void checkIdle(Player player) {
		MetadataValue idleMeta;
		
		idleMeta = player.getMetadata("idle").get(0);
		idleMeta = new FixedMetadataValue(plugin, idleMeta.asInt() + 1);
		
		player.setMetadata("idle", idleMeta);
				
		if (idleMeta.asInt() == IDLE_START_TIME)
			setIdle(player, false);
		else if (idleMeta.asInt() >= IDLE_FINISH_TIME) {
			setActive(player, true);
			player.kickPlayer(IDLE_KICK_MESSAGE);
		}
	}
	
	private static void checkMilestone(Player player) {
		int ticksPlayed = player.getStatistic(Statistic.PLAY_ONE_TICK);
		VitreusPlayer vitreusPlayer = playerDAO.findOne("uuid", player.getUniqueId());
		
		for (Milestone milestone : Milestone.values()) {
				
			if (ticksPlayed >= milestone.getTicksRequired() && !vitreusPlayer.getMilestones().contains(milestone)) {
				MilestoneEvent event = new MilestoneEvent(player, milestone);
				plugin.getServer().getPluginManager().callEvent(event);
			}
		}
	}
	
	public static void setIdle(Player player, boolean silent) {
		
		if (player.getScoreboardTags().contains("idle"))
			return;
		
		player.addScoreboardTag("idle");
		player.setPlayerListName(player.getDisplayName() + IDLE_SUFFIX);
		
		if (silent)
			return;
		else
			plugin.getServer().broadcastMessage(String.format(IDLE_NOTIFICATION_MESSAGE, player.getDisplayName(), "Idle"));
	}
	
	public static void setActive(Player player, boolean silent) {		
		player.setMetadata("idle", new FixedMetadataValue(plugin, 0));
				
		if (!player.getScoreboardTags().contains("idle"))
			return;
		
		player.removeScoreboardTag("idle");
		player.setPlayerListName(player.getDisplayName());
		
		if (silent)
			return;
		else
			plugin.getServer().broadcastMessage(String.format(IDLE_NOTIFICATION_MESSAGE, player.getDisplayName(), "Active"));
	}
	
}
