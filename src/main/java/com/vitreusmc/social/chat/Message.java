package com.vitreusmc.social.chat;

import java.util.Collection;

import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import com.vitreusmc.lib.common.text.Format;
import com.vitreusmc.social.VitreusSocial;

import net.md_5.bungee.api.ChatColor;

public class Message {

	private static VitreusSocial plugin = JavaPlugin.getPlugin(VitreusSocial.class);
	
	private Entity author;
	private String content;
	private Collection<Entity> recepients;
	private String format;
	
	public Message(Entity author, String content, Collection<Entity> recepients, String format) {
		this.author = author;
		this.content = content;
		this.recepients = recepients;
		this.format = format;
	}
	
	public void send() {
		MessageSendEvent event = new MessageSendEvent(this);
		plugin.getServer().getPluginManager().callEvent(event);
	}

	public String get() {
		content = Format.toFormatted(content);
		return String.format(format, author.getName(), content);
	}
	
	public static VitreusSocial getPlugin() {
		return plugin;
	}
	
	public Entity getAuthor() {
		return author;
	}

	public void setAuthor(Entity author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void addRecepient(Entity entity) {
		if (entity == null)
			return;
		else
			recepients.add(entity);
	}
	
	public void addRecepients(Collection<Entity> entities) {
		recepients.addAll(entities);
	}
	
	public void removeRecepient(Entity entity) {
		recepients.remove(entity);
	}
	
	public void removeRecepients(Collection<Entity> entities) {
		recepients.removeAll(entities);
	}

	public void hasRecepient(Entity entity) {
		recepients.contains(entity);
	}
	
	public void hasRecepients(Collection<Entity> entities) {
		recepients.containsAll(entities);
	}
	
	public Collection<Entity> getRecepients() {
		return recepients;
	}

	public void setRecepients(Collection<Entity> recepients) {
		this.recepients = recepients;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
}
