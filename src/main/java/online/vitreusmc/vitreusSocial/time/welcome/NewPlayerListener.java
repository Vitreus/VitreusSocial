package online.vitreusmc.vitreusSocial.time.welcome;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.md_5.bungee.api.ChatColor;

public class NewPlayerListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		if (player.hasPlayedBefore()) {
			return;
		}
		
		event.setJoinMessage(ChatColor.LIGHT_PURPLE + "It's " + ChatColor.BOLD + ChatColor.GOLD + player.getName() + "'s " + ChatColor.RESET + ChatColor.LIGHT_PURPLE + "first time on Vitreus! Give them a " + ChatColor.RED + ChatColor.ITALIC + "Smoldering-Hot " + ChatColor.RESET + ChatColor.LIGHT_PURPLE + "welcome!" );
	}
	
}
