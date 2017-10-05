package online.vitreusmc.vitreusSocial.chatFormatting;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

	@EventHandler
	public void onChatMessage(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		ChatColor chatColor;
		
		try {
			chatColor = player.getScoreboard().getEntryTeam(player.getName()).getColor();			
		} catch(NullPointerException exception) {
			chatColor = ChatColor.RESET;
		}

		event.setFormat(chatColor + player.getName() + ChatColor.GRAY + ChatColor.BOLD + ": " + ChatColor.RESET + "%2$s");
	}
	
}
