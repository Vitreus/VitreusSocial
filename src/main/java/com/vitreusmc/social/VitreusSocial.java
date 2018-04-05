package com.vitreusmc.social;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.mongodb.morphia.Datastore;

import com.vitreusmc.lib.database.DatastoreFactory;
import com.vitreusmc.social.activity.ActivityMonitor;
import com.vitreusmc.social.activity.IdleCommand;
import com.vitreusmc.social.activity.MilestoneTrigger;
import com.vitreusmc.social.activity.PlayTimeCommand;
import com.vitreusmc.social.activity.SeenCommand;
import com.vitreusmc.social.activity.SetPlaytimeCommand;
import com.vitreusmc.social.chat.ChatTrigger;
import com.vitreusmc.social.chat.GlobalCommand;
import com.vitreusmc.social.chat.LocalCommand;
import com.vitreusmc.social.color.ColorCommand;
import com.vitreusmc.social.color.ColorSetter;
import com.vitreusmc.social.connection.ConnectionTrigger;

public class VitreusSocial extends JavaPlugin {

	private Server server;
	private JavaPlugin plugin;
	private Logger logger;
	private YamlConfiguration DBConfig = new YamlConfiguration();
	private Datastore datastore;
	
	@Override
	public void onEnable() {
		this.server = getServer();
		this.plugin = this;
		this.logger = getLogger();
		
		setupDatabase();
		scheduleTasks();
		registerCommands();
		registerTriggers();
	}
	
	private void registerCommands() {
		plugin.getCommand("playtime").setExecutor(new PlayTimeCommand());
		plugin.getCommand("setplaytime").setExecutor(new SetPlaytimeCommand());
		plugin.getCommand("idle").setExecutor(new IdleCommand());
		plugin.getCommand("seen").setExecutor(new SeenCommand());
		plugin.getCommand("color").setExecutor(new ColorCommand());
		plugin.getCommand("local").setExecutor(new LocalCommand());
		plugin.getCommand("global").setExecutor(new GlobalCommand());
	}
	
	private void registerTriggers() {
		server.getPluginManager().registerEvents(new ChatTrigger(), this);
		server.getPluginManager().registerEvents(new ConnectionTrigger(), this);
		server.getPluginManager().registerEvents(new ActivityMonitor(), this);
		server.getPluginManager().registerEvents(new MilestoneTrigger(), this);
		server.getPluginManager().registerEvents(new ColorSetter(), this);
	}
	
	private void scheduleTasks() {
		new ActivityMonitor().runTaskTimer(plugin, 20 * 60, 20 * 60);
	}
	
	private void setupDatabase() {
		loadDatabaseConfig();
		datastore = DatastoreFactory.create(DBConfig);
	}
	
	private void loadDatabaseConfig() {
		File configFile = new File("./vit-db.yml");
		
		try {
			if (configFile.createNewFile()) logger.log(Level.WARNING, "Database Configuration was just Created and needs to be Populated...");
			
			DBConfig.load(configFile);
			DBConfig.addDefault("db.name", "vitreus");
			DBConfig.addDefault("db.host", "localhost");
			DBConfig.addDefault("db.port", 27017);
			DBConfig.addDefault("db.user.authDBName", "auth");
			DBConfig.addDefault("db.user.name", "admin");
			DBConfig.addDefault("db.user.password", "password");
			DBConfig.options().copyDefaults(true);
			DBConfig.save(configFile);
		} catch (Exception exception) {
			logger.log(Level.SEVERE, "Database Configuration Failed to Load..." + exception.getMessage(), exception);
		}

	}
	
	public Datastore getDatastore() {
		return this.datastore;
	}
	
}
