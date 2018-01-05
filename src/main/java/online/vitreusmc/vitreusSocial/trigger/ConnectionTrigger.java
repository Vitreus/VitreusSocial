package online.vitreusmc.vitreusSocial.trigger;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import online.vitreusmc.vitreusSocial.VitreusSocial;
import online.vitreusmc.vitreusSocial.event.player.DatabaseManager;
import online.vitreusmc.vitreusSocial.event.player.ListColorSetter;
import online.vitreusmc.vitreusSocial.event.player.NewPlayerJoinEvent;
import online.vitreusmc.vitreusSocial.event.player.PermissionsManager;
import online.vitreusmc.vitreusSocial.event.player.WelcomeMessager;

public class ConnectionTrigger implements Listener {

	private static JavaPlugin plugin = JavaPlugin.getPlugin(VitreusSocial.class);
	
	@EventHandler
	public void playerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		if (!player.hasPlayedBefore()) {
			NewPlayerJoinEvent newPlayerEvent = new NewPlayerJoinEvent(player);
			plugin.getServer().getPluginManager().callEvent(newPlayerEvent);
		}
		
		DatabaseManager.updatePlayer(player);
		PermissionsManager.attachPermissions(player);
		ListColorSetter.set(player);
	}
	
	@EventHandler
	public void newPlayer(NewPlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		WelcomeMessager.welcomePlayer(player);
		DatabaseManager.registerPlayer(player);
	}
	
}
