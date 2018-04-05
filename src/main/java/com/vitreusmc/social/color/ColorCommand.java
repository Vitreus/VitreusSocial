package com.vitreusmc.social.color;

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

public class ColorCommand implements CommandExecutor {

	private static VitreusSocial plugin = JavaPlugin.getPlugin(VitreusSocial.class);
	private static Datastore datastore = plugin.getDatastore();
	private static BasicDAO<VitreusPlayer, String> playerDAO = new BasicDAO<>(VitreusPlayer.class, datastore);
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player;
		
		if (!(sender instanceof Player)) {
			return false;
		}
		
		player = (Player) sender;
		
		if (args.length == 1) {
			changeColor(player, args[0]);
			player.sendMessage(ChatColor.GREEN + "Your chat color was changed! You're looking good my friend!");
		} else if (!label.equals("color")) {
			changeColor(player, label);
			player.sendMessage(ChatColor.GREEN + "Your chat color was changed! You're looking good my friend!");
		} else {
			player.spigot().sendMessage(ColorSelectorTextComponent.create());
		}
			
		return true;
	}
	
	private void changeColor(Player player, String color) {
		String playerName = player.getName();
		VitreusPlayer vitreusPlayer = playerDAO.findOne("uuid", player.getUniqueId());
				
		switch (color) {
			case "dark_blue": vitreusPlayer.setColor(ChatColor.DARK_BLUE); break;
			case "blue": vitreusPlayer.setColor(ChatColor.BLUE); break;
			case "dark_aqua": vitreusPlayer.setColor(ChatColor.DARK_AQUA); break;
			case "aqua": vitreusPlayer.setColor(ChatColor.AQUA); break;
			case "red": vitreusPlayer.setColor(ChatColor.RED); break;
			case "gold": vitreusPlayer.setColor(ChatColor.GOLD); break;
			case "dark_gray": vitreusPlayer.setColor(ChatColor.DARK_GRAY); break;
			case "gray": vitreusPlayer.setColor(ChatColor.GRAY); break;
			case "black": vitreusPlayer.setColor(ChatColor.BLACK); break;
			case "green": vitreusPlayer.setColor(ChatColor.GREEN); break;
			case "dark_green": vitreusPlayer.setColor(ChatColor.DARK_GREEN); break;
			case "light_purple": vitreusPlayer.setColor(ChatColor.LIGHT_PURPLE); break;
			case "white": vitreusPlayer.setColor(ChatColor.WHITE); break;
			case "yellow": vitreusPlayer.setColor(ChatColor.YELLOW); break;
			case "dark_purple": vitreusPlayer.setColor(ChatColor.DARK_PURPLE); break;
			case "dark_red": vitreusPlayer.setColor(ChatColor.DARK_RED); break;
			default: {
				player.sendMessage(ChatColor.RED + "Unknown Color!");
			}
		}
		
		playerDAO.save(vitreusPlayer);
		player.setDisplayName(vitreusPlayer.getColor() + playerName);
		player.setPlayerListName(vitreusPlayer.getColor() + playerName);
	}

}
