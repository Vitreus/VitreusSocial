package online.vitreusmc.vitreusSocial.event.player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import online.vitreusmc.vitreusSocial.chat.color.teams.ColorTeamsController;

public class ListColorSetter {

	
	public static void set(Player player) {
		ChatColor color = ColorTeamsController.get().getPlayerColor(player);
		player.setPlayerListName(color + player.getName());
	}
	
}
