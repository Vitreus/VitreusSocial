package online.vitreusmc.vitreusSocial.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import online.vitreusmc.vitreusSocial.chat.color.teams.ColorTeam;
import online.vitreusmc.vitreusSocial.chat.color.teams.ColorTeamsController;
import online.vitreusmc.vitreusSocial.chat.color.ui.ColorSelectorTextComponent;

public class ColorCommand implements CommandExecutor {

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
		ColorTeamsController controller = ColorTeamsController.get();
		
		switch (color) {
			case "dark_blue": {
				controller.setPlayerTeam(player, ColorTeam.DARK_BLUE);
				break;
			}
			case "blue": {
				controller.setPlayerTeam(player, ColorTeam.BLUE);
				break;
			}
			case "dark_aqua": {
				controller.setPlayerTeam(player, ColorTeam.DARK_AQUA);
				break;
			}
			case "aqua": {
				controller.setPlayerTeam(player, ColorTeam.AQUA);
				break;
			}
			case "red": {
				controller.setPlayerTeam(player, ColorTeam.RED);
				break;
			}
			case "gold": {
				controller.setPlayerTeam(player, ColorTeam.GOLD);
				break;
			}
			case "dark_gray": {
				controller.setPlayerTeam(player, ColorTeam.DARK_GRAY);
				break;
			}
			case "gray": {
				controller.setPlayerTeam(player, ColorTeam.GRAY);
				break;
			}
			case "black": {
				controller.setPlayerTeam(player, ColorTeam.BLACK);
				break;
			}
			case "green": {
				controller.setPlayerTeam(player, ColorTeam.GREEN);
				break;
			}
			case "dark_green": {
				controller.setPlayerTeam(player, ColorTeam.DARK_GREEN);
				break;
			}
			case "light_purple": {
				controller.setPlayerTeam(player, ColorTeam.LIGHT_PURPLE);
				break;
			}
			case "white": {
				controller.setPlayerTeam(player, ColorTeam.WHITE);
				break;
			}
			case "yellow": {
				controller.setPlayerTeam(player, ColorTeam.YELLOW);
				break;
			}
			case "dark_purple": {
				controller.setPlayerTeam(player, ColorTeam.DARK_PURPLE);
				break;
			}
			case "dark_red": {
				controller.setPlayerTeam(player, ColorTeam.DARK_RED);
				break;
			}
			default: {
				player.sendMessage(ChatColor.RED + "Unknown Color!");
			}
		}
		
		player.setPlayerListName(ColorTeamsController.get().getPlayerColor(player) + playerName);
	}

}
