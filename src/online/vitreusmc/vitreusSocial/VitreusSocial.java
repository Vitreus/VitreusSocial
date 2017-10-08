package online.vitreusmc.vitreusSocial;

import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import online.vitreusmc.vitreusSocial.admin.SetPlaytimeCommand;
import online.vitreusmc.vitreusSocial.chat.ChatInterceptor;
import online.vitreusmc.vitreusSocial.chat.ChatRouter;
import online.vitreusmc.vitreusSocial.chat.color.ColorCommand;
import online.vitreusmc.vitreusSocial.chat.color.teams.ColorTeamsController;
import online.vitreusmc.vitreusSocial.chat.format.GlobalMessageFormatter;
import online.vitreusmc.vitreusSocial.chat.format.LocalMessageFormatter;
import online.vitreusmc.vitreusSocial.chat.global.GlobalCommand;
import online.vitreusmc.vitreusSocial.chat.local.LocalCommand;
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
		registerPostListeners();
	}
	
	private void initialize() {
		this.colorTeamsController = ColorTeamsController.get();
	}
	
	private void registerCommands() {
		plugin.getCommand("playtime").setExecutor(new PlayTimeCommand());
		plugin.getCommand("color").setExecutor(new ColorCommand());
		plugin.getCommand("setplaytime").setExecutor(new SetPlaytimeCommand());
		plugin.getCommand("local").setExecutor(new LocalCommand());
		plugin.getCommand("global").setExecutor(new GlobalCommand());
	}
	
	private void registerListeners() {
		server.getPluginManager().registerEvents(new ChatInterceptor(), this);
		server.getPluginManager().registerEvents(new GlobalMessageFormatter(), this);
		server.getPluginManager().registerEvents(new LocalMessageFormatter(), this);
		server.getPluginManager().registerEvents(new MilestoneListener(), this);
		server.getPluginManager().registerEvents(new PlayerJoinListener(), this);
		server.getPluginManager().registerEvents(new NewPlayerListener(), this);
	}
	
	private void registerPostListeners() {
		server.getPluginManager().registerEvents(new ChatRouter(), this);
	}
	
}
