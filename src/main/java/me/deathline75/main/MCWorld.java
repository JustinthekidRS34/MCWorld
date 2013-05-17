package me.deathline75.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import me.deathline75.MCWorld.generator.FlatlandsGenerator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
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
	
	public static Map<String, Object> properties = new HashMap<String, Object>();
	
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
			mcworldProps.load(MCWorldPropsFile);
			mcworldProps.addDefault("loadWorldsOnStart", null);
			if(mcworldProps.getString("loadWorldsOnStart") != null){
			    for(String worldname: mcworldProps.getString("loadWorldsOnStart").split(",")){
				WorldCreator normal = new WorldCreator(worldname);
				Bukkit.getServer().createWorld(normal);
				WorldCreator nether = new WorldCreator(worldname + "_nether");
				nether.seed(normal.seed());
				nether.environment(Environment.NETHER);
				nether.generateStructures(normal.generateStructures());
				Bukkit.getServer().createWorld(nether);
				WorldCreator the_end = new WorldCreator(worldname + "_the_end");
				the_end.seed(normal.seed());
				the_end.environment(Environment.THE_END);
				the_end.generateStructures(normal.generateStructures());
				Bukkit.getServer().createWorld(the_end);
				World world = Bukkit.getServer().getWorld(worldname);
				world.getGenerator();
				try {
					MCWorld.getPropertiesFile().load(MCWorld.getProperties());
					if(MCWorld.getPropertiesFile().isConfigurationSection(worldname)){
						FileConfiguration f = MCWorld.getPropertiesFile();
						ConfigurationSection cs = f.getConfigurationSection(worldname);
						try {
							f.load(MCWorld.getProperties());
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvalidConfigurationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						world.setSpawnFlags(cs.getBoolean("mobspawn", true), cs.getBoolean("animalspawn", true));
						world.setAnimalSpawnLimit(cs.getInt("animalspawnlimit", 15));
						world.setAutoSave(cs.getBoolean("autosave", true));
						world.setDifficulty(Difficulty.getByValue(cs.getInt("difficulty", 1)));
						world.setKeepSpawnInMemory(cs.getBoolean("keepspawninmemory", true));
						world.setMonsterSpawnLimit(cs.getInt("mobspawnlimit", 70));
						world.setPVP(cs.getBoolean("pvp", false));
						world.setTicksPerAnimalSpawns(cs.getInt("ticksperanimalspawn", 400));
						world.setTicksPerMonsterSpawns(cs.getInt("tickspermobspawn", 1));
					}
					else{
						MCWorld.getPropertiesFile().createSection(worldname, properties);
						try {
							MCWorld.getPropertiesFile().save(MCWorld.getProperties());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    }
			}
			    
			mcworldProps.save(MCWorldPropsFile);
		    } catch (Exception e) {
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
		} catch (Exception e) {
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
	static{
		properties.put("ticksperanimalspawn", 400);
		properties.put("tickspermobspawn", 1);
		properties.put("mobspawn", true);
		properties.put("animalspawn", true);
		properties.put("mobspawnlimit", 70);
		properties.put("animalspawnlimit", 15);
		properties.put("difficulty", 1);
		properties.put("fulltime", 1);
		properties.put("pvp", false);
		properties.put("autosave", true);
		properties.put("keepspawninmemory", true);
	}
}
