package online.vitreusmc.vitreusSocial.chat;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import online.vitreusmc.vitreusSocial.chat.message.Message;
import online.vitreusmc.vitreusSocial.chat.message.MessageEvent;
import online.vitreusmc.vitreusSocial.chat.message.MessageFactory;

public class ChatInterceptor implements Listener {

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		Message message = MessageFactory.serializeMessage(event);
		
		Bukkit.getPluginManager().callEvent(new MessageEvent(message));

		event.setCancelled(true);
	}
	
}
