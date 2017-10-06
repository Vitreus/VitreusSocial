package online.vitreusmc.vitreusSocial.chat.message;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MessageEvent extends Event implements Cancellable {

	public static HandlerList HANDLERS = new HandlerList();
	public boolean isCancelled = false;
	
	private Message message;
	private Entity author;
	
	public MessageEvent(Message message) {
		this.message = message;
		this.author = message.getAuthor();
	}
	
	public Message getMessage() {
		return message;
	}
	
	public void setMessage(Message message) {
		this.message = message;
	}
	
	public Entity getAuthor() {
		return author;
	}
	
	public void setAuthor(Entity author) {
		this.author = author;
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
