package com.vitreusmc.social.connection;

import java.util.Date;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import com.vitreusmc.lib.common.Flag;
import com.vitreusmc.lib.common.Milestone;
import com.vitreusmc.lib.database.entities.VitreusPlayer;
import com.vitreusmc.social.VitreusSocial;

public class ConnectionTrigger implements Listener {

	private static VitreusSocial plugin = JavaPlugin.getPlugin(VitreusSocial.class);
	private static Datastore datastore = plugin.getDatastore();
	private static BasicDAO<VitreusPlayer, String> playerDAO = new BasicDAO<>(VitreusPlayer.class, datastore);
	
	@EventHandler
	public void playerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		checkNew(player);
		ensureDatabaseEntry(player);
		attachPermissions(player);
	}
	
	@EventHandler
	public void newPlayer(NewPlayerJoinEvent event) {
		Player player = event.getPlayer();
		WelcomeMessager.welcomePlayer(player);
	}
	
	@EventHandler
	public void playerDisconnect(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		Query<VitreusPlayer> query = playerDAO.createQuery().field("uuid").equal(player.getUniqueId());
		VitreusPlayer vitreusPlayer = playerDAO.find(query).get();
		
		vitreusPlayer.setMinutesPlayed(player.getStatistic(Statistic.PLAY_ONE_TICK) / 20 / 60);
		vitreusPlayer.setLastOnline(new Date());
		
		playerDAO.save(vitreusPlayer);
	}
	
	private void ensureDatabaseEntry(Player player) {
		VitreusPlayer vitreusPlayer;
		Query<VitreusPlayer> query = playerDAO.createQuery().field("uuid").equal(player.getUniqueId());
		
		if (!playerDAO.exists(query))
			vitreusPlayer = VitreusPlayer.create(player);
		else
			vitreusPlayer = playerDAO.findOne("uuid", player.getUniqueId());			
		
		vitreusPlayer.setMinutesPlayed(player.getStatistic(Statistic.PLAY_ONE_TICK) / 20 / 60);
		vitreusPlayer.setUsername(player.getName());
		
		playerDAO.save(vitreusPlayer);
	}
	
	private void checkNew(Player player) {
		if (!player.hasPlayedBefore()) {
			NewPlayerJoinEvent newPlayerEvent = new NewPlayerJoinEvent(player);
			plugin.getServer().getPluginManager().callEvent(newPlayerEvent);
		}
	}
	
	private void attachPermissions(Player player) {
		VitreusPlayer vitreusPlayer = playerDAO.findOne("uuid", player.getUniqueId());
		
		if (vitreusPlayer.getMilestones().contains(Milestone.HOUR_12)) {
			player.addAttachment(plugin, "milestone.12", true);
		}

		if (vitreusPlayer.getMilestones().contains(Milestone.HOUR_24)) {
			player.addAttachment(plugin, "milestone.24", true);
		}
		
		if (vitreusPlayer.getFlags().contains(Flag.PATREON)) {
			player.addAttachment(plugin, "patreon", true);
		}
	}
	
}
