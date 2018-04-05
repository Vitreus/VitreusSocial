package com.vitreusmc.social.color;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import com.vitreusmc.lib.database.entities.VitreusPlayer;
import com.vitreusmc.social.VitreusSocial;

import net.md_5.bungee.api.ChatColor;

public class ColorSetter implements Listener {

	private static VitreusSocial plugin = JavaPlugin.getPlugin(VitreusSocial.class);
	private static Datastore datastore = plugin.getDatastore();
	private static BasicDAO<VitreusPlayer, String> playerDAO = new BasicDAO<>(VitreusPlayer.class, datastore);
	
	@EventHandler
	public static void playerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		VitreusPlayer vitreusPlayer = playerDAO.findOne("uuid", player.getUniqueId());
		
		ChatColor color = vitreusPlayer.getColor();
		player.setDisplayName(color + player.getName());
		player.setPlayerListName(player.getDisplayName());
	}
	
}
