package online.vitreusmc.vitreusSocial.time.afk;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import online.vitreusmc.vitreusSocial.VitreusSocial;


public class IdleCounter extends BukkitRunnable {

	Server server = Bukkit.getServer();
	JavaPlugin plugin = JavaPlugin.getPlugin(VitreusSocial.class);
			
	@Override
	public void run() {
		
		for (Player player : server.getOnlinePlayers()) {
			int idleTime = player.getMetadata("idle.time").get(0).asInt();
			
			player.setMetadata("idle.time", new FixedMetadataValue(plugin, idleTime + 1));
			
			if (idleTime > 60) {
				player.kickPlayer("You've been idle for too long!");
			}
		}
	}
}
