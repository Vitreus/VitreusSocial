package online.vitreusmc.vitreusSocial.chat.message;

import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MessageFactory {

	public static Message serializeMessage(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		String content = event.getMessage();
		
		return new Message(player, content);
	}
	
}
