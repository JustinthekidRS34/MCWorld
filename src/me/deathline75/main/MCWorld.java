package me.deathline75.main;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import me.deathline75.MCWorld.generator.FlatlandsGenerator;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Java15Compat;
import org.mcstats.Metrics;

public class MCWorld extends JavaPlugin{
	
	public final Logger log = Logger.getLogger("Minecraft");
	public static MCWorld mcworld = new MCWorld();
	private MCWorldListener listener = new MCWorldListener(this);
	private NewMCWorldListener newlistener = new NewMCWorldListener(this);
	
	private static File fileforwarps ;
	private static File worldPropertiesFile;
	private static File MCWorldPropsFile;
	
	private static FileConfiguration world;
	public static FileConfiguration props;
	public static FileConfiguration mcworldProps;
	
	@Override
	public void onEnable(){
		PluginDescriptionFile pdf = this.getDescription();
		log.info("[MCWorld] v." + pdf.getVersion() + " has been enabled.");
		this.getServer().getPluginManager().registerEvents(listener, this);
		this.getServer().getPluginManager().registerEvents(newlistener, this);
		setFileforwarps(new File(getDataFolder(), "warps.yml"));
		worldPropertiesFile = new File(getDataFolder(), "world.yml");
		MCWorldPropsFile = new File(getDataFolder(), "config.yml");
		if(!MCWorldPropsFile.exists()){
		    try {
			MCWorldPropsFile.createNewFile();
		    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		}
		if(!worldPropertiesFile.exists()){
			try {
				worldPropertiesFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(!getFileforwarps().exists()){
			try {
				getFileforwarps().createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		world = YamlConfiguration.loadConfiguration(worldPropertiesFile);
		props = YamlConfiguration.loadConfiguration(getFileforwarps());
		mcworldProps = YamlConfiguration.loadConfiguration(MCWorldPropsFile);
		try {
			world.load(worldPropertiesFile);
			props.load(getFileforwarps());
		} catch (IOException
				| InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("[MCWorld] Sending client info...");
		try {
		    Metrics metrics = new Metrics(this);
		    metrics.start();
		    log.info("[MCWorld] Metrics have started logging infomation! :D");
		} catch (IOException e) {
		    // Failed to submit the stats :-(
		}
	}
	
	@Override
	public void onDisable(){
		PluginDescriptionFile pdf = this.getDescription();
		log.info("[MCWorld] v." + pdf.getVersion() + " has been disabled.");
		try {
			world.save(worldPropertiesFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static FileConfiguration getPropertiesFile(){
		return world;
	}
	
	public static File getProperties(){
		return worldPropertiesFile;
	}
	
	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String uid){
		return new FlatlandsGenerator();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(label.equalsIgnoreCase("mcw")){
			if(args.length > 0){
				if(sender.isOp() || sender.hasPermission(new Permission("mcw.command." + args[0])) || sender.hasPermission("mcw.command.*")){
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
				sender.sendMessage(ChatColor.GRAY + "| select | unload | tp | tpI | props | help | list | setwarp | warp | unloadnosave | playerlist | kickallworld | listwarp | help | createf |");
				sender.sendMessage(ChatColor.YELLOW + "-------------------------");
			}
			return true;
		}
		return false;
	}

	public static File getFileforwarps() {
		return fileforwarps;
	}

	public static void setFileforwarps(File fileforwarps) {
		MCWorld.fileforwarps = fileforwarps;
	}
}
