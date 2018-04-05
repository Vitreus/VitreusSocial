package com.vitreusmc.social.activity;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import com.vitreusmc.lib.database.entities.VitreusPlayer;
import com.vitreusmc.social.VitreusSocial;

import net.md_5.bungee.api.ChatColor;

public class SeenCommand implements CommandExecutor {

	private static VitreusSocial plugin = JavaPlugin.getPlugin(VitreusSocial.class);
	private static BasicDAO<VitreusPlayer, String> playerDAO = new BasicDAO<>(VitreusPlayer.class, plugin.getDatastore());
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		String searchTerm = args[0];
		Query<VitreusPlayer> query = playerDAO.createQuery().field("username").containsIgnoreCase(searchTerm);		
		List<VitreusPlayer> vitreusPlayers = playerDAO.find(query).asList();
		
		StringBuffer message = new StringBuffer(String.format(ChatColor.GREEN + "Last Seen: \"%s\"\n", searchTerm));
		
		if (!vitreusPlayers.isEmpty())
			vitreusPlayers.forEach(vitreusPlayer -> message.append(composeEntry(vitreusPlayer) + "\n"));
		
		sender.sendMessage(message.toString());
		
		return true;
	}
	
	private String composeEntry(VitreusPlayer vitreusPlayer) {
		long elapsedTime = new Date().getTime() - vitreusPlayer.getLastOnline().getTime();
		String elapsedTimeFormatted = String.format(ChatColor.GRAY + "Last seen " + ChatColor.GOLD + ChatColor.BOLD + "%d" + ChatColor.RESET + ChatColor.GOLD + " Days" + ChatColor.GRAY + ", " + ChatColor.GOLD + ChatColor.BOLD + "%d" + ChatColor.RESET + ChatColor.GOLD + " Hours" + ChatColor.GRAY + ", and " + ChatColor.GOLD + ChatColor.BOLD + "%d" + ChatColor.RESET + ChatColor.GOLD +" Minutes" + ChatColor.GRAY + " ago.",
				TimeUnit.MILLISECONDS.toDays(elapsedTime),
				TimeUnit.MILLISECONDS.toHours(elapsedTime) % 24,
				TimeUnit.MILLISECONDS.toMinutes(elapsedTime) % 60);	
		return String.format(ChatColor.GRAY + " | " + vitreusPlayer.getColor() + ChatColor.BOLD + "%s" + ChatColor.RESET + ChatColor.DARK_AQUA + " ▶ " + ChatColor.RESET + "%s", vitreusPlayer.getUsername(), elapsedTimeFormatted);
	}
}
