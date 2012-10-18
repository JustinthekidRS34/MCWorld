package me.deathline75.main;

import java.io.File;
import java.util.logging.Logger;

import me.deathline75.main.util.MCWorldProperties;

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
	
	private File dirfileforwarps = new File(this.getDataFolder(), "MCWorld");
	private File fileforwarps = new File(this.getDataFolder(), "warps.properties");
	
	private MCWorldProperties props = new MCWorldProperties(this.dirfileforwarps, this.fileforwarps);
	
	@Override
	public void onEnable(){
		PluginDescriptionFile pdf = this.getDescription();
		log.info("[MCWorld] v." + pdf.getVersion() + " has been enabled.");
		this.getServer().getPluginManager().registerEvents(listener, this);
		this.getServer().getPluginManager().registerEvents(newlistener, this);
		this.props.load();
	}
	
	@Override
	public void onDisable(){
		PluginDescriptionFile pdf = this.getDescription();
		log.info("[MCWorld] v." + pdf.getVersion() + " has been disabled.");
		this.props.save();
	}
	
	public MCWorldProperties getProps(){
		return this.props;
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
				sender.sendMessage(ChatColor.GRAY + "| select | unload | tp | props | disable | help | list | setwarp | warp |");
				sender.sendMessage(ChatColor.YELLOW + "-------------------------");
			}
			return true;
		}
		return false;
	}
}
