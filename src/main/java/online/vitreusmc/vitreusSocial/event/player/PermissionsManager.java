package online.vitreusmc.vitreusSocial.event.player;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import online.vitreusmc.vitreusConnect.VitreusDB;
import online.vitreusmc.vitreusConnect.object.FlagList;
import online.vitreusmc.vitreusConnect.object.VitreusPlayer;
import online.vitreusmc.vitreusConnect.object.FlagList.PlayerFlag;
import online.vitreusmc.vitreusSocial.VitreusSocial;

public class PermissionsManager {

	private static VitreusSocial plugin = JavaPlugin.getPlugin(VitreusSocial.class);
	private static VitreusDB database = plugin.getDatabase();
	
	public static void attachPermissions(Player player) {
		VitreusPlayer dbPlayer;
		FlagList flags;
		
		if (!database.checkPlayer(player)) {
			database.createPlayer(player);
		}
		
		dbPlayer = database.getPlayer(player);
		flags = dbPlayer.getFlags();
		
		if (flags.contains(PlayerFlag.TWELVE_HOUR_MILESTONE)) {
			player.addAttachment(plugin, "milestones.12", true);
		}
		
		if (flags.contains(PlayerFlag.TWENTY_FOUR_HOUR_MILESTONE)) {
			player.addAttachment(plugin, "milestones.24", true);
		}
		
		if (flags.contains(PlayerFlag.PATREON)) {
			player.addAttachment(plugin, "patreon", true);
		}
	}
	
}
