package com.vitreusmc.social.chat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import com.vitreusmc.social.VitreusSocial;

public class LocalCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player;
		JavaPlugin plugin = JavaPlugin.getPlugin(VitreusSocial.class);
		
		if (!(sender instanceof Player)) {
			return false;
		}
		
		player = (Player) sender;
		
		if (args[0].contentEquals("on") && args.length < 2) {
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Local Chat: " + ChatColor.GREEN + "Enabled!");
			player.addScoreboardTag("local");	
		} else if (args[0].contentEquals("off") && args.length < 2) {
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Local Chat: " + ChatColor.RED + "Disabled!");
			player.removeScoreboardTag("local");			
		} else if (args.length > 0) {
			String messageContent = "";
			LocalMessage message;
			
			for (String word : args) {
				messageContent += " " + word;
			}
			messageContent = messageContent.substring(1);
			
			message = new LocalMessage(player, messageContent, 100);
			message.send();
		}
		
		return true;
	}

}
