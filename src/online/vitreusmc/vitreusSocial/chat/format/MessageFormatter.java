package online.vitreusmc.vitreusSocial.chat.format;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import online.vitreusmc.vitreusSocial.chat.message.Message;
import online.vitreusmc.vitreusSocial.chat.message.MessageEvent;

public class MessageFormatter implements Listener {

	@EventHandler
	public void onMessage(MessageEvent event) {
		Message message;
		Player player;
		ChatColor chatColor;
		
		if (!(event.getAuthor() instanceof Player)) {
			return;
		}
		
		message = event.getMessage();
		player = (Player) event.getAuthor();
		
		try {
			chatColor = player.getScoreboard().getEntryTeam(player.getName()).getColor();			
		} catch(NullPointerException exception) {
			chatColor = ChatColor.RESET;
		}

		message.setLabel(chatColor + player.getName() + ChatColor.RESET);
	}
	
}
