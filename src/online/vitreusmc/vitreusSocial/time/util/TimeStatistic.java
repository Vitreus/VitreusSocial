package online.vitreusmc.vitreusSocial.time.util;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;

public class TimeStatistic {

	public static int getTicks(Player player) {
		return player.getStatistic(Statistic.PLAY_ONE_TICK);
	}
	
	public static int getSeconds(Player player) {
		return getTicks(player) / 20;
	}
	
	public static int getMinutes(Player player) {
		return getSeconds(player) / 60;
	}
	
	public static int getHours(Player player) {
		return getMinutes(player) / 60;
	}
	
}
