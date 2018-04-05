package com.vitreusmc.social.connection;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class NewPlayerJoinEvent extends PlayerEvent {

	private static HandlerList HANDLERS = new HandlerList();
	
	public NewPlayerJoinEvent(Player who) {
		super(who);
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}
	
	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
	
}
