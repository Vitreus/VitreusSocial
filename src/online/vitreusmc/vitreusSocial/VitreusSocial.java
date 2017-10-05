package online.vitreusmc.vitreusSocial;

import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import online.vitreusmc.vitreusSocial.admin.SetPlaytimeCommand;
import online.vitreusmc.vitreusSocial.chatColor.ColorCommand;
import online.vitreusmc.vitreusSocial.chatColor.teams.ColorTeamsController;
import online.vitreusmc.vitreusSocial.chatFormatting.ChatListener;
import online.vitreusmc.vitreusSocial.time.PlayTimeCommand;
import online.vitreusmc.vitreusSocial.time.milestones.MilestoneListener;
import online.vitreusmc.vitreusSocial.time.milestones.MilestoneWatcher;
import online.vitreusmc.vitreusSocial.time.milestones.PlayerJoinListener;
import online.vitreusmc.vitreusSocial.time.welcome.NewPlayerListener;

public class VitreusSocial extends JavaPlugin {

	private Server server;
	private JavaPlugin plugin;
	private Logger logger;
	private FileConfiguration config;
	private BukkitScheduler scheduler;
	
	private ColorTeamsController colorTeamsController;
	
	@Override
	public void onEnable() {
		this.server = getServer();
		this.plugin = this;
		this.logger = getLogger();
		this.config = getConfig();
		this.scheduler = server.getScheduler();
		
		scheduler.runTaskTimerAsynchronously(this, new MilestoneWatcher(), 20 * 60, 20 * 60);
		
		initialize();
		registerCommands();
		registerListeners();
	}
	
	private void initialize() {
		this.colorTeamsController = ColorTeamsController.get();
	}
	
	private void registerCommands() {
		plugin.getCommand("playtime").setExecutor(new PlayTimeCommand());
		plugin.getCommand("color").setExecutor(new ColorCommand());
		plugin.getCommand("setplaytime").setExecutor(new SetPlaytimeCommand());
	}
	
	private void registerListeners() {
		server.getPluginManager().registerEvents(new ChatListener(), this);
		server.getPluginManager().registerEvents(new MilestoneListener(), this);
		server.getPluginManager().registerEvents(new PlayerJoinListener(), this);
		server.getPluginManager().registerEvents(new NewPlayerListener(), this);
	}
	
}
