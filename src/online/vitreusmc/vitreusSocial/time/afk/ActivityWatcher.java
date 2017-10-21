package online.vitreusmc.vitreusSocial.time.afk;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import online.vitreusmc.vitreusSocial.VitreusSocial;

public class ActivityWatcher implements Listener {

	private static JavaPlugin plugin = JavaPlugin.getPlugin(VitreusSocial.class);
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		refreshTime(player);
		AFKManager.setAFK(player, false, true);
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		refreshTime(player);
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		refreshTime(player);
	}
	
	private void refreshTime(Player player) {
		player.setMetadata("idle.time", new FixedMetadataValue(plugin, 0));
		
		if (AFKManager.isAFK(player)) {
			AFKManager.setAFK(player, false, false);
		}
	}
	
}
