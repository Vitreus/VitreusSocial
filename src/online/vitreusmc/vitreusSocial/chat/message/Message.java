package online.vitreusmc.vitreusSocial.chat.message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;


public class Message {
	
	private Entity author;
	private String label;
	private String content;
	private String message;
	private String seperator = ChatColor.GRAY + "" + ChatColor.BOLD + ": " + ChatColor.RESET;
	private List<Entity> recipients = new ArrayList<Entity>();
	private boolean local = false;
	
	public Message(Player author, String content) {
		this.author = author;
		this.label = author.getName();
		this.content = content;
		
		updateMessage();
	}
	
	public Message(Entity author, String content) {
		this.author = author;
		this.label = author.getCustomName();
		this.content = content;
		
		updateMessage();
	}
	
	public void send() {
		for (Entity recipient : recipients) {
			recipient.sendMessage(message);
		}
		
		if (!local) {
			Bukkit.getServer().getLogger().log(Level.INFO, author.getName() + ": " + content);			
		}
	}
	
	public void addRecipient(Entity recipient) {
		this.recipients.add(recipient);
	}
	
	public void addRecipients(Collection<Entity> recipients) {
		this.recipients.addAll(recipients);
	}
	
	public void removeRecipient(Entity recipient) {
		this.recipients.remove(recipient);
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
		updateMessage();
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
		updateMessage();
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<Entity> getRecipients() {
		return recipients;
	}
	
	public void setRecipients(List<Entity> recipients) {
		this.recipients = recipients;
	}
	
	public String getSeperator() {
		return seperator;
	}
	
	public void setSeperator(String seperator) {
		this.seperator = seperator;
	}
	
	public void updateMessage() {
		this.message = label + seperator + content;
	}
	
	public boolean isLocal() {
		return local;
	}
	
	public void setLocal(boolean local) {
		this.local = local;
	}
}
