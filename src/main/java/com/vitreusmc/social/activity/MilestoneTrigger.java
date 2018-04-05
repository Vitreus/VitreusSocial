package com.vitreusmc.social.activity;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import com.vitreusmc.lib.common.Milestone;
import com.vitreusmc.lib.database.entities.VitreusPlayer;
import com.vitreusmc.social.VitreusSocial;

public class MilestoneTrigger implements Listener {

	private static VitreusSocial plugin = JavaPlugin.getPlugin(VitreusSocial.class);
	
	@EventHandler
	public void onMilestoneEvent(MilestoneEvent event) {
		Datastore datastore = plugin.getDatastore();
		BasicDAO<VitreusPlayer, String>  playerDAO = new BasicDAO<>(VitreusPlayer.class, datastore);
		
		Milestone milestone = event.getMilestone();
		Player player = event.getPlayer();

		VitreusPlayer vitreusPlayer = playerDAO.findOne("uuid", player.getUniqueId());
		
		vitreusPlayer.getMilestones().add(milestone);
		playerDAO.save(vitreusPlayer);
		
		MilestoneMessenger.congratulatePlayer(player, milestone);
	}
	
}
