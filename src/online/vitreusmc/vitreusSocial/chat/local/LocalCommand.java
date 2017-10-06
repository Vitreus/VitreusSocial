package online.vitreusmc.vitreusSocial.chat.local;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class LocalCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player;
		
		if (!(sender instanceof Player)) {
			return false;
		}
		
		player = (Player) sender;
		player.setMetadata("true", new MetadataValue() {
			
			@Override
			public Object value() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void invalidate() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Plugin getOwningPlugin() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String asString() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public short asShort() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public long asLong() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public int asInt() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public float asFloat() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public double asDouble() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public byte asByte() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public boolean asBoolean() {
				// TODO Auto-generated method stub
				return false;
			}
		});
		
		return true;
	}

}
