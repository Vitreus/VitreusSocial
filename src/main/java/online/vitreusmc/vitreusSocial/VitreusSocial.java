package online.vitreusmc.vitreusSocial;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import online.vitreusmc.vitreusConnect.VitreusDB;
import online.vitreusmc.vitreusSocial.chat.ChatInterceptor;
import online.vitreusmc.vitreusSocial.chat.ChatRouter;
import online.vitreusmc.vitreusSocial.chat.color.teams.ColorTeamsController;
import online.vitreusmc.vitreusSocial.chat.format.GlobalMessageFormatter;
import online.vitreusmc.vitreusSocial.chat.format.LocalMessageFormatter;
import online.vitreusmc.vitreusSocial.chat.global.GlobalCommand;
import online.vitreusmc.vitreusSocial.chat.local.LocalCommand;
import online.vitreusmc.vitreusSocial.command.ColorCommand;
import online.vitreusmc.vitreusSocial.command.PlayTimeCommand;
import online.vitreusmc.vitreusSocial.command.SetPlaytimeCommand;
import online.vitreusmc.vitreusSocial.trigger.AFKTrigger;
import online.vitreusmc.vitreusSocial.trigger.ConnectionTrigger;
import online.vitreusmc.vitreusSocial.trigger.MilestoneTrigger;

public class VitreusSocial extends JavaPlugin {

	private Server server;
	private JavaPlugin plugin;
	private Logger logger;
	private YamlConfiguration dbConfig = new YamlConfiguration();
	private BukkitScheduler scheduler;
	private VitreusDB database;

	private ColorTeamsController colorTeamsController;
	
	@Override
	public void onEnable() {
		this.server = getServer();
		this.plugin = this;
		this.logger = getLogger();
		this.scheduler = server.getScheduler();
		
		scheduler.runTaskTimerAsynchronously(this, new MilestoneTrigger(), 20 * 60, 20 * 60);
		scheduler.runTaskTimer(this, new AFKTrigger(), 20 * 60, 20 * 60);
		
		initialize();
		setupDatabase();
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
		AFKTrigger afkTrigger = new AFKTrigger();
		MilestoneTrigger milestoneTrigger = new MilestoneTrigger();
		
		server.getPluginManager().registerEvents(new ChatInterceptor(), this);
		server.getPluginManager().registerEvents(new GlobalMessageFormatter(), this);
		server.getPluginManager().registerEvents(new LocalMessageFormatter(), this);
		server.getPluginManager().registerEvents(new ConnectionTrigger(), this);
		server.getPluginManager().registerEvents(afkTrigger, this);
		server.getPluginManager().registerEvents(milestoneTrigger, this);
		afkTrigger.runTaskTimer(plugin, 20 * 60, 20 * 60);
		milestoneTrigger.runTaskTimerAsynchronously(plugin, 20 * 60, 20 * 60);
	}
	
	private void registerPostListeners() {
		server.getPluginManager().registerEvents(new ChatRouter(), this);
	}
	
	private void setupDatabase() {
		loadDatabaseConfig();
		
		database = new VitreusDB(dbConfig);
		database.connect();
	}
	
	private void loadDatabaseConfig() {
		File configFile = new File(getDataFolder().getParentFile().getParentFile(), "vmc-db.yml");
		
		try {
			if (configFile.createNewFile()) logger.log(Level.WARNING, "Database Configuration was just Created and needs to be Populated...");
			
			dbConfig.load(configFile);
			dbConfig.addDefault("db.name", "vitreus");
			dbConfig.addDefault("db.host", "localhost");
			dbConfig.addDefault("db.port", 27017);
			dbConfig.addDefault("db.user.authDBName", "auth");
			dbConfig.addDefault("db.user.name", "admin");
			dbConfig.addDefault("db.user.password", "password");
			dbConfig.options().copyDefaults(true);
			dbConfig.save(configFile);
		} catch (Exception exception) {
			logger.log(Level.SEVERE, "Database Configuration Failed to Load..." + exception.getMessage(), exception);
		}

	}
	
	public VitreusDB getDatabase() {
		return this.database;
	}
	
}
