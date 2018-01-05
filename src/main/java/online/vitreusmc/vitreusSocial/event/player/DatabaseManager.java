package online.vitreusmc.vitreusSocial.event.player;

import java.util.Date;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import online.vitreusmc.vitreusConnect.VitreusDB;
import online.vitreusmc.vitreusConnect.object.VitreusPlayer;
import online.vitreusmc.vitreusSocial.VitreusSocial;

public class DatabaseManager {

	private static VitreusSocial plugin = JavaPlugin.getPlugin(VitreusSocial.class);
	private static VitreusDB database = plugin.getDatabase();
	
	public static void registerPlayer(Player player) {
		
		if (database.checkPlayer(player)) {
			return;
		}
		
		database.createPlayer(player);
	}
	
	public static void updatePlayer(Player player) {
		VitreusPlayer vitreusPlayer;
		
		if (!database.checkPlayer(player)) {
			registerPlayer(player);
		}
		
		vitreusPlayer = database.getPlayer(player);
		vitreusPlayer.setLastOnline(new Date());
		database.savePlayer(vitreusPlayer);
	}
	
}
