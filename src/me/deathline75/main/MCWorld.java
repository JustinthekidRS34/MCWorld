package me.deathline75.main;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Java15Compat;

public class MCWorld extends JavaPlugin{
	
	public final Logger log = Logger.getLogger("Minecraft");
	public static MCWorld mcworld;
	private MCWorldListener listener = new MCWorldListener(this);
	private NewMCWorldListener newlistener = new NewMCWorldListener(this);
	
	@Override
	public void onEnable(){
		PluginDescriptionFile pdf = this.getDescription();
		log.info("[MCWorld] v." + pdf.getVersion() + " has been enabled.");
		this.getServer().getPluginManager().registerEvents(listener, this);
		this.getServer().getPluginManager().registerEvents(newlistener, this);
	}
	
	@Override
	public void onDisable(){
		PluginDescriptionFile pdf = this.getDescription();
		log.info("[MCWorld] v." + pdf.getVersion() + " has been disabled.");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(label.equalsIgnoreCase("mcw")){
			if(args.length > 0){
				if(sender.isOp()){
					String label2 = args[0];
					newlistener.onCommand(sender, label2 ,(String[])Java15Compat.Arrays_copyOfRange(args, 1, args.length), command);
				}
				else{
					sender.sendMessage("You do not have the permission to use that command.");
				}
			}
			else if(args.length == 0){
				PluginDescriptionFile pdf = this.getDescription();
				sender.sendMessage(ChatColor.YELLOW + "-------------------------");
				sender.sendMessage(ChatColor.GREEN + "MCWorld Version " + pdf.getVersion());
				sender.sendMessage("Avaliable Commands for MCWorld");
				sender.sendMessage(ChatColor.GRAY + "| select | unload | tp | props | disable | help | list |");
				sender.sendMessage(ChatColor.YELLOW + "-------------------------");
			}
			return true;
		}
		return false;
	}
}
