package me.deathline75.main;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

public class MCWorldListener implements Listener{
	
	private MCWorld mcworld = MCWorld.mcworld;
	private Logger log = mcworld.log;
	
	public void onCommand(CommandSender sender, String label ,String[] args, Command cmd){
		if(label == ""){
			
		}
		log.info("[MCWorld] "+sender.getName() + " just used the command: /mcw "+ label);
	}
}
