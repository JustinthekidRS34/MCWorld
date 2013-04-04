package me.deathline75.MCWorld.commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

import me.deathline75.main.IMCWorldCommand;
import me.deathline75.main.MCWorld;
import me.deathline75.main.PlayerWorld;

public class Commandselect implements IMCWorldCommand{

	public static Map<String, Object> properties = new HashMap<String, Object>();
	
	@Override
	public boolean executeCMD(CommandSender sender, String label, String[] args) {
		if(label.equalsIgnoreCase("select") || label.equalsIgnoreCase("create") || label.equalsIgnoreCase("load")){
			if(args.length == 0){
				try{
					sender.sendMessage((ChatColor.GREEN + "Currently selected world: " )+ ChatColor.YELLOW + PlayerWorld.getWorld(sender).getName());
					return true;
				}
				catch(NullPointerException e){
					sender.sendMessage(ChatColor.RED + "You have not selected a world yet!");
					return false;
				}
			}
			if(args.length != 0){
				if(args.length != 1){
					String worldname = "";
					for(String s:args){
						if(s != args[args.length - 1]){
							worldname += (s + " ");
						}
						else{
							worldname += (s);
						}
					}
					PlayerWorld playerworld = new PlayerWorld(sender, Bukkit.getServer().createWorld(new WorldCreator(worldname)));
					playerworld.toString();
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
				else{
					PlayerWorld playerworld = new PlayerWorld(sender, Bukkit.getServer().createWorld(new WorldCreator(args[0])));
					playerworld.toString();
					if(MCWorld.getPropertiesFile().isConfigurationSection(args[0])){
						
					}
					else{
						MCWorld.getPropertiesFile().createSection(args[0], properties);
						try {
							MCWorld.getPropertiesFile().save(MCWorld.getProperties());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				sender.sendMessage(ChatColor.GREEN + "You have selected and loaded the world.");
				return true;
			}
		}
		return false;
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

	@Override
	public String[] getHelp() {
		// TODO Auto-generated method stub
		return new String[]{
			ChatColor.GRAY + "Usage: /mcw select|load|create [World Name]", 
			ChatColor.DARK_AQUA + "Description: Loads/Creates the world and selects the world",
			ChatColor.DARK_RED + "Notes: If it fails to detect the world, it will creaete a new world without consent."
		};
	}

}
