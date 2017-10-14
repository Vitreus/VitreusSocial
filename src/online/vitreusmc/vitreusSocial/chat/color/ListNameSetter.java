package online.vitreusmc.vitreusSocial.chat.color;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import online.vitreusmc.vitreusSocial.chat.color.teams.ColorTeamsController;

public class ListNameSetter implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		ChatColor color = ColorTeamsController.get().getPlayerColor(player);
		
		player.setPlayerListName(color + player.getName());
	}
	
}
