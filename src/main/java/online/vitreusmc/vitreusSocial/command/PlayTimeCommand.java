package online.vitreusmc.vitreusSocial.command;

import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class PlayTimeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player;
		int minutesPlayed;
		
		if (!(sender instanceof Player)) {
			return false;
		}
		
		player = (Player) sender;
		minutesPlayed = player.getStatistic(Statistic.PLAY_ONE_TICK) / 20 / 60;
		
		player.sendMessage(ChatColor.GREEN + "Your PlayTime: " + ChatColor.GOLD + ChatColor.BOLD + (minutesPlayed / 60) + ChatColor.RESET + ChatColor.GREEN + " Hours and " + ChatColor.GOLD + ChatColor.BOLD + (minutesPlayed % 60) + ChatColor.RESET + ChatColor.GREEN + " Minutes!");
		
		return true;
	}

}
