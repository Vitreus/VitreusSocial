package com.vitreusmc.social.activity;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.vitreusmc.lib.common.Milestone;

public class MilestoneEvent extends Event implements Cancellable {

	public static HandlerList HANDLERS = new HandlerList();
	public boolean isCancelled = false;
	
	private Player player;
	private Milestone milestone;
	
	public MilestoneEvent(Player player, Milestone milestone) {
		this.player = player;
		this.milestone = milestone;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Milestone getMilestone() {
		return milestone;
	}
	
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}
	
	public static HandlerList getHandlerList() {
		return HANDLERS;
	}

	@Override
	public boolean isCancelled() {
		return isCancelled;
	}

	@Override
	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

}
