package online.vitreusmc.vitreusSocial.chat.format;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import online.vitreusmc.vitreusSocial.chat.color.teams.ColorTeam;
import online.vitreusmc.vitreusSocial.chat.color.teams.ColorTeamsController;
import online.vitreusmc.vitreusSocial.chat.message.Message;
import online.vitreusmc.vitreusSocial.chat.message.MessageEvent;

public class GlobalMessageFormatter implements Listener {

	@EventHandler
	public void onMessage(MessageEvent event) {
		Message message = event.getMessage();
		boolean local = false;
		Entity author = event.getAuthor();
		
		
		if (!(event.getAuthor() instanceof Player)) {
			return;
		}
		
		if (author.hasMetadata("chat.local")) {
			local = author.getMetadata("chat.local").get(0).asBoolean();			
		} else {
			local = false;
		}
				
		if (!local) {
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
