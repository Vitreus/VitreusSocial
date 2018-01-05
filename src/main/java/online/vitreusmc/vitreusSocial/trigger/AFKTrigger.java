package online.vitreusmc.vitreusSocial.trigger;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import online.vitreusmc.vitreusSocial.VitreusSocial;
import online.vitreusmc.vitreusSocial.event.afk.AFKManager;

public class AFKTrigger extends BukkitRunnable implements Listener {
	
	Server server = Bukkit.getServer();
	JavaPlugin plugin = JavaPlugin.getPlugin(VitreusSocial.class);
			
	@Override
	public void run() {
		
		for (Player player : server.getOnlinePlayers()) {
			int idleTime;
			
			if (player.hasMetadata("idle.time")) {
				idleTime = player.getMetadata("idle.time").get(0).asInt();
			} else {
				idleTime = 0;
			}
			
			player.setMetadata("idle.time", new FixedMetadataValue(plugin, idleTime + 1));
			
			if (idleTime > 60) {
				player.kickPlayer("You've been idle for too long!");
			} else if (idleTime > 5 && !AFKManager.isAFK(player)) {
				AFKManager.setAFK(player, true, false);
			}
		}
	}
	
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
