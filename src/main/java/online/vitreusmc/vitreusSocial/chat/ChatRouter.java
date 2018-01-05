package online.vitreusmc.vitreusSocial.chat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import online.vitreusmc.vitreusSocial.VitreusSocial;
import online.vitreusmc.vitreusSocial.chat.message.Message;
import online.vitreusmc.vitreusSocial.chat.message.MessageEvent;

public class ChatRouter implements Listener {
	
	private static JavaPlugin plugin = JavaPlugin.getPlugin(VitreusSocial.class);
	
	@EventHandler
	public void onMessage(MessageEvent event) {
		Message message = event.getMessage();
		route(message);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		try {
			event.getPlayer().getMetadata("chat.local").get(0).asBoolean();
		} catch (Exception exception) {
			event.getPlayer().setMetadata("chat.local", new FixedMetadataValue(plugin, false));
		}
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
			recipients.addAll((Collection<Player>) Bukkit.getOnlinePlayers());
		}
		
		message.addRecipients(recipients);
		message.send();
	}
}
