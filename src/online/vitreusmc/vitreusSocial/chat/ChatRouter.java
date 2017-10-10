package online.vitreusmc.vitreusSocial.chat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import online.vitreusmc.vitreusSocial.chat.color.teams.ColorTeamsController;
import online.vitreusmc.vitreusSocial.chat.message.Message;
import online.vitreusmc.vitreusSocial.chat.message.MessageEvent;

public class ChatRouter implements Listener {
	
	@EventHandler
	public void onMessage(MessageEvent event) {
		Message message = event.getMessage();
		route(message);
	}
	
	public void route(Message message) {
		Entity author = message.getAuthor();
		boolean localchat;
		
		if (author.hasMetadata("chat.local")) {
			localchat = author.getMetadata("chat.local").get(0).asBoolean();			
		} else {
			localchat = false;
		}
			
		if (message.isLocal() || localchat) {
			sendMessage(message, true);
		} else {
			sendMessage(message, false);
		}
	}
	
	public static void sendMessage(Message message, boolean local) {
		List<Entity> recipients = new ArrayList<Entity>();
		Entity author = message.getAuthor();
		
		if (local) {
			List<Entity> nearbyEntities = author.getNearbyEntities(100, 100, 100);

			for (Entity entity : nearbyEntities) {
				if (entity instanceof Player) {
					recipients.add(entity);
				}
			}
			
			if (recipients.size() < 1) {
				author.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Nobody is close enough to hear you.");
			} else {
				recipients.add(author);
			}
		} else {
			recipients.addAll((Collection<Entity>) Bukkit.getOnlinePlayers());
		}
		
		message.addRecipients(recipients);
		message.send();
	}
}
