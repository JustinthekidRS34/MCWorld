package me.deathline75.main;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerWorld {
	private static Map<CommandSender, World> world = new HashMap<CommandSender, World>();
	
	public PlayerWorld(CommandSender sender, World w){
		if(world.containsKey(sender)){
			world.remove(sender);
		}
		PlayerWorld.world.put(sender, w);
	}
	public PlayerWorld(Server sender, World world){
		this((CommandSender)sender, world);
	}
	public PlayerWorld(Player sender, World world){
		this((CommandSender)sender, world);
	}
	
	public static World getWorld(CommandSender c){
		return world.get(c);
	}
	
	public static void removeSender(CommandSender c){
		world.remove(c);
	}
}
