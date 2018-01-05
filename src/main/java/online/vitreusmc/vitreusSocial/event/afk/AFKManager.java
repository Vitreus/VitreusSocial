package online.vitreusmc.vitreusSocial.event.afk;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import online.vitreusmc.vitreusSocial.VitreusSocial;

public class AFKManager {

	public static JavaPlugin plugin = JavaPlugin.getPlugin(VitreusSocial.class);
	public static Server server = Bukkit.getServer();
	
	public static boolean isAFK(Player player) {
		
		try {
			boolean afk = player.getMetadata("idle.afk").get(0).asBoolean();
			
			if (afk) {
				return true;
			} else {
				return false;
			}
		} catch (Exception exception) {
			return false;
		}
	}
	
	public static void setAFK(Player player, boolean afk, boolean silent) {
		String name = player.getPlayerListName();		
		
		if (afk) {
			
			if (!silent) {
				server.broadcastMessage(ChatColor.ITALIC + player.getPlayerListName() + ChatColor.GRAY + " has gone AFK");				
			}

			if (!name.contains(" [AFK]")) {
				player.setPlayerListName(player.getPlayerListName() + ChatColor.GRAY + " [AFK]");				
			}
			
			player.setMetadata("idle.afk", new FixedMetadataValue(plugin, true));			
		}
		
		if (!afk) {
			
			if (name.contains(" [AFK]")) {
				player.setPlayerListName(name.substring(0, name.indexOf(" [AFK]")));
			}

			if (!silent) {
				server.broadcastMessage(ChatColor.ITALIC + player.getPlayerListName() + ChatColor.GRAY + " is no longer AFK");
			}
			
			player.setMetadata("idle.afk", new FixedMetadataValue(plugin, false));	
		}

	}
}
