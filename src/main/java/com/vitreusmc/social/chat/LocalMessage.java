package com.vitreusmc.social.chat;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import com.vitreusmc.lib.database.entities.VitreusPlayer;

import net.md_5.bungee.api.ChatColor;

public class LocalMessage extends Message {

	public LocalMessage(Entity author, String content, int radius) {
		super(author, content, new ArrayList<Entity>(), null);

		Datastore datastore = getPlugin().getDatastore();
		BasicDAO<VitreusPlayer, String> playerDAO = new BasicDAO<>(VitreusPlayer.class, datastore);
		Collection<Entity> nearbyEntities = new ArrayList<>();
		ChatColor color = ChatColor.WHITE;
		
		author.getNearbyEntities(radius, radius, radius)
				.stream()
				.filter(e -> e instanceof Player)
				.forEach(e -> nearbyEntities.add((LivingEntity) e));
		
		if (author instanceof Player) {
			VitreusPlayer player = playerDAO.findOne("uuid", author.getUniqueId());
			color = player.getColor();
		}
		
		addRecepients(nearbyEntities);
		addRecepient(author);

		if (nearbyEntities.size() == 0)
			setFormat(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Nobody is around to hear you...");
		else
			setFormat(color + "%s" + ChatColor.GRAY + ChatColor.BOLD + " > " + ChatColor.RESET + color + ChatColor.ITALIC + "%s");
	}

}
