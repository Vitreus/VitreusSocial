package com.vitreusmc.social.chat;

import java.util.ArrayList;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import com.vitreusmc.lib.database.entities.VitreusPlayer;

import net.md_5.bungee.api.ChatColor;

public class GlobalMessage extends Message {
	
	public GlobalMessage(Entity author, String content) {
		super(author, content, new ArrayList<Entity>(), null);

		Datastore datastore = getPlugin().getDatastore();
		BasicDAO<VitreusPlayer, String> playerDAO = new BasicDAO<>(VitreusPlayer.class, datastore);
		
		ChatColor color = ChatColor.WHITE;
		
		if (author instanceof Player) {
			VitreusPlayer vitreusPlayer = playerDAO.findOne("uuid", author.getUniqueId());
			color = vitreusPlayer.getColor();
		}

		getPlugin().getServer().getOnlinePlayers()
			.forEach(player -> addRecepient(player));
		
		setFormat(color + "%s" + ChatColor.RESET + ChatColor.GRAY + ChatColor.BOLD + ": " + ChatColor.RESET + "%s");
	}

}
