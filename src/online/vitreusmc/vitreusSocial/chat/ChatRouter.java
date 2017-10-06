package online.vitreusmc.vitreusSocial.chat;

import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

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
		
		try {
			localchat = author.getMetadata("chat.local").get(0).asBoolean();
		} catch (Exception exception) {
			localchat = false;
		}
		
		if (localchat) {
			List<Entity> recipients = author.getNearbyEntities(100, 100, 100);
			
			message.addRecipients(recipients);
			message.send();
		} else {
			Collection<Entity> recipients = (Collection<Entity>) Bukkit.getOnlinePlayers();
			
			message.addRecipients(recipients);
			message.send();
		}
	}
}
