package online.vitreusmc.vitreusSocial.chat.message;

import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MessageFactory {

	public static Message serializeMessage(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		String content = event.getMessage();
		boolean local;
		Message message = new Message(player, content);
		
		if (player.hasMetadata("chat.local")) {
			local = player.getMetadata("chat.local").get(0).asBoolean();
		} else {
			local = false;
		}
		
		message.setLocal(local);
		return message;
	}
	
}
