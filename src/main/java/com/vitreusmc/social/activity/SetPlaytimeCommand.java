package com.vitreusmc.social.activity;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import com.vitreusmc.lib.database.entities.VitreusPlayer;
import com.vitreusmc.social.VitreusSocial;

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
		
		updateDatabase(targetPlayer);
		
		return true;
	}

	private void updateDatabase(Player target) {
		VitreusSocial plugin = JavaPlugin.getPlugin(VitreusSocial.class);
		Datastore datastore = plugin.getDatastore();
		BasicDAO<VitreusPlayer, String> playerDAO = new BasicDAO<>(VitreusPlayer.class, datastore);
		Query<VitreusPlayer> query = playerDAO.createQuery().field("uuid").equal(target.getUniqueId());
		VitreusPlayer vitreusPlayer = playerDAO.find(query).get();
		
		vitreusPlayer.setMinutesPlayed(target.getStatistic(Statistic.PLAY_ONE_TICK) / 20 / 60);
		playerDAO.save(vitreusPlayer);
	}

}
