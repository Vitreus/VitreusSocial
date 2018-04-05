package com.vitreusmc.social.activity;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import com.vitreusmc.lib.database.entities.VitreusPlayer;
import com.vitreusmc.social.VitreusSocial;

import net.md_5.bungee.api.ChatColor;

public class PlayTimeCommand implements CommandExecutor {

	private static VitreusSocial plugin = JavaPlugin.getPlugin(VitreusSocial.class);
	private static Datastore datastore = plugin.getDatastore();
	private static BasicDAO<VitreusPlayer, String> playerDAO = new BasicDAO<>(VitreusPlayer.class, datastore);
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player;
		VitreusPlayer vitreusPlayer;
		int minutesPlayed;
		
		if (!(sender instanceof Player)) {
			return false;
		}
		
		player = (Player) sender;
		vitreusPlayer = playerDAO.findOne("uuid", player.getUniqueId());
		minutesPlayed = vitreusPlayer.getMinutesPlayed();
		
		player.sendMessage(ChatColor.GREEN + "Your PlayTime: " + ChatColor.GOLD + ChatColor.BOLD + (minutesPlayed / 60) + ChatColor.RESET + ChatColor.GREEN + " Hours and " + ChatColor.GOLD + ChatColor.BOLD + (minutesPlayed % 60) + ChatColor.RESET + ChatColor.GREEN + " Minutes!");
		
		return true;
	}

}
