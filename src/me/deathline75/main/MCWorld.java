package me.deathline75.main;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class MCWorld extends JavaPlugin{
	
	public final Logger log = Logger.getLogger("Minecraft");
	public static MCWorld mcworld;
	private MCWorldListener listener;
	
	@Override
	public void onEnable(){
		PluginDescriptionFile pdf = this.getDescription();
		log.info(mcworld.getName() + " v." + pdf.getVersion() + " has been enabled.");
		this.getServer().getPluginManager().registerEvents(listener, mcworld);
	}
	
	@Override
	public void onDisable(){
		PluginDescriptionFile pdf = this.getDescription();
		log.info(mcworld.getName() + " v." + pdf.getVersion() + " has been disabled.");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(label == "mcw"){
			if(args.length < 0){
				if(sender.isOp()){
					String label2 = args[0];
					listener.onCommand(sender, label2 ,args, command);
				}
				else{
					sender.sendMessage("You do not have the permission to use that command.");
				}
			}
			else if(args.length == 0){
				
			}
			return true;
		}
		return false;
	}
}
