package online.vitreusmc.vitreusSocial.command;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetPlaytimeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (args.length < 2) {
			return false;
		}
		
		String targetPlayerName = args[0];
		int minutesPlayed = Integer.parseInt(args[1]);
		Player targetPlayer = Bukkit.getPlayer(targetPlayerName);
		
		try {
			targetPlayer.setStatistic(Statistic.PLAY_ONE_TICK, (minutesPlayed * 60 * 20));			
		} catch (IllegalArgumentException exception) {
			sender.sendMessage("Command execution failed: \n" + exception.getMessage());
			return false;
		}
		
		return true;
	}

}
