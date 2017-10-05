package online.vitreusmc.vitreusSocial.chatColor.ui;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;

public class ColorSelectorTextComponent {

	public static TextComponent create() {
		TextComponent message = new TextComponent();
			message.setColor(ChatColor.GRAY);
		TextComponent messageTitle = new TextComponent();
			messageTitle.setColor(ChatColor.GREEN);
			messageTitle.addExtra("Click to set your chat color:");
		TextComponent messageDarkBlueSelector = new TextComponent();
			messageDarkBlueSelector.setColor(ChatColor.DARK_BLUE);
			messageDarkBlueSelector.addExtra("Dark Blue");
			messageDarkBlueSelector.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/color dark_blue"));
		TextComponent messageBlueSelector = new TextComponent();
			messageBlueSelector.setColor(ChatColor.BLUE);
			messageBlueSelector.addExtra("Blue");
			messageBlueSelector.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/color blue"));
		TextComponent messageDarkAquaSelector = new TextComponent();
			messageDarkAquaSelector.setColor(ChatColor.DARK_AQUA);
			messageDarkAquaSelector.addExtra("Dark Aqua");
			messageDarkAquaSelector.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/color dark_aqua"));
		TextComponent messageAquaSelector = new TextComponent();
			messageAquaSelector.setColor(ChatColor.AQUA);
			messageAquaSelector.addExtra("Aqua");
			messageAquaSelector.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/color aqua"));
		TextComponent messageRedSelector = new TextComponent();
			messageRedSelector.setColor(ChatColor.RED);
			messageRedSelector.addExtra("Red");
			messageRedSelector.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/color red"));
		TextComponent messageGoldSelector = new TextComponent();
			messageGoldSelector.setColor(ChatColor.GOLD);
			messageGoldSelector.addExtra("Gold");
			messageGoldSelector.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/color gold"));
		TextComponent messageDarkGraySelector = new TextComponent();
			messageDarkGraySelector.setColor(ChatColor.DARK_GRAY);
			messageDarkGraySelector.addExtra("Dark Gray");
			messageDarkGraySelector.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/color dark_gray"));
		TextComponent messageGraySelector = new TextComponent();
			messageGraySelector.setColor(ChatColor.GRAY);
			messageGraySelector.addExtra("Gray");
			messageGraySelector.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/color gray"));
		TextComponent messageBlackSelector = new TextComponent();
			messageBlackSelector.setColor(ChatColor.BLACK);
			messageBlackSelector.addExtra("Black");
			messageBlackSelector.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/color black"));
		TextComponent messageGreenSelector = new TextComponent();
			messageGreenSelector.setColor(ChatColor.GREEN);
			messageGreenSelector.addExtra("Green");
			messageGreenSelector.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/color green"));
		TextComponent messageDarkGreenSelector = new TextComponent();
			messageDarkGreenSelector.setColor(ChatColor.DARK_GREEN);
			messageDarkGreenSelector.addExtra("Dark Green");
			messageDarkGreenSelector.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/color dark_green"));
		TextComponent messageLightPurpleSelector = new TextComponent();
			messageLightPurpleSelector.setColor(ChatColor.LIGHT_PURPLE);
			messageLightPurpleSelector.addExtra("Light Purple");
			messageLightPurpleSelector.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/color light_purple"));
		TextComponent messageWhiteSelector = new TextComponent();
			messageWhiteSelector.setColor(ChatColor.WHITE);
			messageWhiteSelector.addExtra("White");
			messageWhiteSelector.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/color white"));
		TextComponent messageYellowSelector = new TextComponent();
			messageYellowSelector.setColor(ChatColor.YELLOW);
			messageYellowSelector.addExtra("Yellow");
			messageYellowSelector.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/color yellow"));
		TextComponent messageDarkPurpleSelector = new TextComponent();
			messageDarkPurpleSelector.setColor(ChatColor.DARK_PURPLE);
			messageDarkPurpleSelector.addExtra("Dark Purple");
			messageDarkPurpleSelector.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/color dark_purple"));
		TextComponent messageDarkRedSelector = new TextComponent();
			messageDarkRedSelector.setColor(ChatColor.DARK_RED);
			messageDarkRedSelector.addExtra("Dark Red");
			messageDarkRedSelector.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/color dark_red"));
		
		message.addExtra(messageTitle);
		message.addExtra("\n");
		message.addExtra("| ");
		message.addExtra(messageDarkGreenSelector);
		message.addExtra("\n");
		message.addExtra("| ");
		message.addExtra(messageGreenSelector);
		message.addExtra("\n");
		message.addExtra("| ");
		message.addExtra(messageDarkBlueSelector);
		message.addExtra("\n");
		message.addExtra("| ");
		message.addExtra(messageBlueSelector);
		message.addExtra("\n");
		message.addExtra("| ");
		message.addExtra(messageDarkAquaSelector);
		message.addExtra("\n");
		message.addExtra("| ");
		message.addExtra(messageAquaSelector);
		message.addExtra("\n");
		message.addExtra("| ");
		message.addExtra(messageDarkPurpleSelector);
		message.addExtra("\n");
		message.addExtra("| ");
		message.addExtra(messageLightPurpleSelector);
		message.addExtra("\n");
		message.addExtra("| ");
		message.addExtra(messageDarkRedSelector);
		message.addExtra("\n");
		message.addExtra("| ");
		message.addExtra(messageRedSelector);
		message.addExtra("\n");
		message.addExtra("| ");
		message.addExtra(messageGoldSelector);
		message.addExtra("\n");
		message.addExtra("| ");
		message.addExtra(messageYellowSelector);
		message.addExtra("\n");
		message.addExtra("| ");
		message.addExtra(messageBlackSelector);
		message.addExtra("\n");
		message.addExtra("| ");
		message.addExtra(messageDarkGraySelector);
		message.addExtra("\n");
		message.addExtra("| ");
		message.addExtra(messageGraySelector);
		message.addExtra("\n");
		message.addExtra("| ");
		message.addExtra(messageWhiteSelector);
		
		return message;
	}
	
}
