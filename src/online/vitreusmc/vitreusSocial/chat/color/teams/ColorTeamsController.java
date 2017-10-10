package online.vitreusmc.vitreusSocial.chat.color.teams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class ColorTeamsController {

	private static ColorTeamsController instance;
	
	Team darkGreenTeam;
	Team greenTeam;
	Team darkBlueTeam;
	Team blueTeam;
	Team darkAquaTeam;
	Team aquaTeam;
	Team darkPurpleTeam;
	Team lightPurpleTeam;
	Team darkRedTeam;
	Team redTeam;
	Team goldTeam;
	Team yellowTeam;
	Team darkGrayTeam;
	Team blackTeam;
	Team grayTeam;
	Team whiteTeam;
	
	public ColorTeamsController() {
		
		if (instance != null) {
			return;
		} else {
			instance = this;
		}
				
		if (Bukkit.getScoreboardManager().getMainScoreboard().getTeam("whiteTeam") != null) {
			darkGreenTeam = Bukkit.getScoreboardManager().getMainScoreboard().getTeam("darkGreenTeam");
				darkGreenTeam.setColor(ChatColor.DARK_GREEN);
			greenTeam = Bukkit.getScoreboardManager().getMainScoreboard().getTeam("greenTeam");
				greenTeam.setColor(ChatColor.GREEN);
			darkBlueTeam = Bukkit.getScoreboardManager().getMainScoreboard().getTeam("darkBlueTeam");
				darkBlueTeam.setColor(ChatColor.DARK_BLUE);
			blueTeam = Bukkit.getScoreboardManager().getMainScoreboard().getTeam("blueTeam");
				blueTeam.setColor(ChatColor.BLUE);
			darkAquaTeam = Bukkit.getScoreboardManager().getMainScoreboard().getTeam("darkAquaTeam");
				darkAquaTeam.setColor(ChatColor.DARK_AQUA);
			aquaTeam = Bukkit.getScoreboardManager().getMainScoreboard().getTeam("aquaTeam");
				aquaTeam.setColor(ChatColor.AQUA);
			darkPurpleTeam = Bukkit.getScoreboardManager().getMainScoreboard().getTeam("darkPurpleTeam");
				darkPurpleTeam.setColor(ChatColor.DARK_PURPLE);
			lightPurpleTeam = Bukkit.getScoreboardManager().getMainScoreboard().getTeam("lightPurpleTeam");
				lightPurpleTeam.setColor(ChatColor.LIGHT_PURPLE);
			darkRedTeam = Bukkit.getScoreboardManager().getMainScoreboard().getTeam("darkRedTeam");
				darkRedTeam.setColor(ChatColor.DARK_RED);
			redTeam = Bukkit.getScoreboardManager().getMainScoreboard().getTeam("redTeam");
				redTeam.setColor(ChatColor.RED);
			goldTeam = Bukkit.getScoreboardManager().getMainScoreboard().getTeam("goldTeam");
				goldTeam.setColor(ChatColor.GOLD);
			yellowTeam = Bukkit.getScoreboardManager().getMainScoreboard().getTeam("yellowTeam");
				yellowTeam.setColor(ChatColor.YELLOW);
			darkGrayTeam = Bukkit.getScoreboardManager().getMainScoreboard().getTeam("darkGrayTeam");
				darkGrayTeam.setColor(ChatColor.DARK_GRAY);
			blackTeam = Bukkit.getScoreboardManager().getMainScoreboard().getTeam("blackTeam");
				blackTeam.setColor(ChatColor.BLACK);
			grayTeam = Bukkit.getScoreboardManager().getMainScoreboard().getTeam("grayTeam");
				grayTeam.setColor(ChatColor.GRAY);
			whiteTeam = Bukkit.getScoreboardManager().getMainScoreboard().getTeam("whiteTeam");
				whiteTeam.setColor(ChatColor.WHITE);
		} else {
			darkGreenTeam = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam("darkGreenTeam");
				darkGreenTeam.setColor(ChatColor.DARK_GREEN);
			greenTeam = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam("greenTeam");
				greenTeam.setColor(ChatColor.GREEN);
			darkBlueTeam = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam("darkBlueTeam");
				darkBlueTeam.setColor(ChatColor.DARK_BLUE);
			blueTeam = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam("blueTeam");
				blueTeam.setColor(ChatColor.BLUE);
			darkAquaTeam = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam("darkAquaTeam");
				darkAquaTeam.setColor(ChatColor.DARK_AQUA);
			aquaTeam = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam("aquaTeam");
				aquaTeam.setColor(ChatColor.AQUA);
			darkPurpleTeam = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam("darkPurpleTeam");
				darkPurpleTeam.setColor(ChatColor.DARK_PURPLE);
			lightPurpleTeam = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam("lightPurpleTeam");
				lightPurpleTeam.setColor(ChatColor.LIGHT_PURPLE);
			darkRedTeam = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam("darkRedTeam");
				darkRedTeam.setColor(ChatColor.DARK_RED);
			redTeam = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam("redTeam");
				redTeam.setColor(ChatColor.RED);
			goldTeam = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam("goldTeam");
				goldTeam.setColor(ChatColor.GOLD);
			yellowTeam = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam("yellowTeam");
				yellowTeam.setColor(ChatColor.YELLOW);
			darkGrayTeam = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam("darkGrayTeam");
				darkGrayTeam.setColor(ChatColor.DARK_GRAY);
			blackTeam = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam("blackTeam");
				blackTeam.setColor(ChatColor.BLACK);
			grayTeam = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam("grayTeam");
				grayTeam.setColor(ChatColor.GRAY);
			whiteTeam = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam("whiteTeam");
				whiteTeam.setColor(ChatColor.WHITE);
		}
	}
	
	public static ColorTeamsController get() {
		if (instance == null) {
			return new ColorTeamsController();
		} else {
			return instance;
		}
	}
	
	public boolean setPlayerTeam(Player player, ColorTeam colorTeam) {
		String playerName = player.getName();
		
		switch (colorTeam) {
			case DARK_GREEN: {
				darkGreenTeam.addEntry(playerName);
				break;
			}
			case GREEN: {
				greenTeam.addEntry(playerName);
				break;
			}
			case DARK_BLUE: {
				darkBlueTeam.addEntry(playerName);
				break;
			}
			case BLUE: {
				blueTeam.addEntry(playerName);
				break;
			}
			case DARK_AQUA: {
				darkAquaTeam.addEntry(playerName);
				break;
			}
			case AQUA: {
				aquaTeam.addEntry(playerName);
				break;
			}
			case DARK_PURPLE: {
				darkPurpleTeam.addEntry(playerName);
				break;
			}
			case LIGHT_PURPLE: {
				lightPurpleTeam.addEntry(playerName);
				break;
			}
			case DARK_RED: {
				darkRedTeam.addEntry(playerName);
				break;
			}
			case RED: {
				redTeam.addEntry(playerName);
				break;
			}
			case GOLD: {
				goldTeam.addEntry(playerName);
				break;
			}
			case YELLOW: {
				yellowTeam.addEntry(playerName);
				break;
			}
			case BLACK: {
				blackTeam.addEntry(playerName);
				break;
			}
			case DARK_GRAY: {
				darkGrayTeam.addEntry(playerName);
				break;
			}
			case GRAY: {
				grayTeam.addEntry(playerName);
				break;
			}
			case WHITE: {
				whiteTeam.addEntry(playerName);
				break;
			}
			default: {
				return false;
			}
		}
		
		return true;
	}
	
	public Team getPlayerTeam(Player player) {
		return player.getScoreboard().getEntryTeam(player.getName());
	}
	
	public ChatColor getPlayerColor(Player player) {
		ChatColor color;
		
		try {
			color = Bukkit.getServer().getScoreboardManager().getMainScoreboard().getEntryTeam(player.getName()).getColor();
		} catch (NullPointerException exception) {
			color = ChatColor.WHITE;
		}
		
		return color;
	}
}
