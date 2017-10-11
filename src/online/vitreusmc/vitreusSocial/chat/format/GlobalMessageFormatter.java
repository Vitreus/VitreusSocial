package online.vitreusmc.vitreusSocial.chat.format;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import online.vitreusmc.vitreusSocial.chat.color.teams.ColorTeamsController;
import online.vitreusmc.vitreusSocial.chat.message.Message;
import online.vitreusmc.vitreusSocial.chat.message.MessageEvent;

public class GlobalMessageFormatter implements Listener {

	@EventHandler
	public void onMessage(MessageEvent event) {
		Message message = event.getMessage();
		Entity author = event.getAuthor();
		
		
		if (!(author instanceof Player)) {
			return;
		}
				
		if (!message.isLocal()) {
			format(message);			
		}
	}
	
	public static Message format(Message message) {
		Player player;
		ChatColor chatColor;

		player = (Player) message.getAuthor();
		chatColor = ColorTeamsController.get().getPlayerColor(player);

		message.setLabel(chatColor + player.getName() + ChatColor.RESET);
		
		return message;
	}
	
}
