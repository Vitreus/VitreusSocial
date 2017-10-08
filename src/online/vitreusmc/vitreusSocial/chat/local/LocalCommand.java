package online.vitreusmc.vitreusSocial.chat.local;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import online.vitreusmc.vitreusSocial.VitreusSocial;
import online.vitreusmc.vitreusSocial.chat.ChatRouter;
import online.vitreusmc.vitreusSocial.chat.color.teams.ColorTeamsController;
import online.vitreusmc.vitreusSocial.chat.format.GlobalMessageFormatter;
import online.vitreusmc.vitreusSocial.chat.format.LocalMessageFormatter;
import online.vitreusmc.vitreusSocial.chat.message.Message;
import online.vitreusmc.vitreusSocial.chat.message.MessageEvent;

public class LocalCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player;
		Server server = Bukkit.getServer();
		JavaPlugin plugin = JavaPlugin.getPlugin(VitreusSocial.class);
		
		if (!(sender instanceof Player)) {
			return false;
		}
		
		player = (Player) sender;
		
		if (args[0].contentEquals("on") && args.length < 2) {
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Local Chat: " + ChatColor.GREEN + "Enabled!");
			player.setMetadata("chat.local", new FixedMetadataValue(plugin, "true"));			
		} else if (args[0].contentEquals("off") && args.length < 2) {
			player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Local Chat: " + ChatColor.RED + "Disabled!");
			player.setMetadata("chat.local", new FixedMetadataValue(plugin, "false"));			
		} else if (args.length > 0) {
			String messageContent = "";
			MessageEvent messageEvent;
			Message message;
			
			for (String word : args) {
				messageContent += " " + word;
			}
			messageContent = messageContent.substring(1);
			
			message = new Message(player, messageContent);
			message.setLocal(true);
			
			LocalMessageFormatter.format(message);
			ChatRouter.sendMessage(message, true);
		}
		
		return true;
	}

}
