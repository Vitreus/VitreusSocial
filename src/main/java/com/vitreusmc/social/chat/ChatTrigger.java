package com.vitreusmc.social.chat;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import com.vitreusmc.lib.common.Flag;
import com.vitreusmc.lib.database.entities.VitreusPlayer;
import com.vitreusmc.social.VitreusSocial;

public class ChatTrigger implements Listener {

	private static VitreusSocial plugin = JavaPlugin.getPlugin(VitreusSocial.class);

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {		
		Player player = event.getPlayer();
		String content = event.getMessage();
		Message message;
		
		event.setCancelled(true);
		
		if (player.getScoreboardTags().contains("local")) {
			message = new LocalMessage(player, content, 100);
		} else {
			message = new GlobalMessage(player, content);
		}
		
		plugin.getServer().getPluginManager().callEvent(new MessageSendEvent(message));
	}
	
	@EventHandler
	public void onMessageSend(MessageSendEvent event) {
		ConsoleCommandSender logger = plugin.getServer().getConsoleSender();
		Message message = event.getMessage();
		
		if (message instanceof GlobalMessage) {
			logger.sendMessage(message.get());
		}
		
		message.getRecepients().forEach(e -> e.sendMessage(message.get()));
	}
}
