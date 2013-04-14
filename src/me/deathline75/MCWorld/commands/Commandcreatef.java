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
import org.bukkit.WorldType;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Java15Compat;

import me.deathline75.main.IMCWorldCommand;
import me.deathline75.main.MCWorld;
import me.deathline75.main.PlayerWorld;

public class Commandcreatef implements IMCWorldCommand{

	public static Map<String, Object> properties = new HashMap<String, Object>();
	
	@Override
	public boolean executeCMD(CommandSender sender, String label, String[] arg) {
		String[] args = (String[])Java15Compat.Arrays_copyOfRange(arg, 1, arg.length);
		try{
			if(args.length != 0){
				String worldname = "";
				if(args.length != 1){
					worldname = "";
					for(String s:args){
						if(s != args[args.length - 1]){
							worldname += (s + " ");
						}
						else{
							worldname += (s);
						}
					}
				}
				else{
					worldname = args[0];
				}
					WorldCreator fworld = new WorldCreator(worldname);
					fworld.type(WorldType.FLAT);
					fworld.generator(arg[0]);
					PlayerWorld playerworld = new PlayerWorld(sender, Bukkit.getServer().createWorld(fworld));
					playerworld.toString();
					World world = Bukkit.getServer().getWorld(worldname);
					FixedMetadataValue generator = new FixedMetadataValue(MCWorld.mcworld, 
							arg[0]);
					world.setMetadata("generatorOptions", generator);
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
				
				sender.sendMessage(ChatColor.GREEN + "You have selected and loaded the world.");
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
			sender.sendMessage("An unexpected error has occurred while creating this superflat world. Please check your generator options.");
			return false;
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
			ChatColor.GRAY + "Usage: /mcw createf [Generator Options] [World Name]", 
			ChatColor.DARK_AQUA + "Description: Loads/Creates a superflat world with specified generator options and selects the world",
			ChatColor.DARK_RED + "Notes: This is incredibliy unstable due to Bukkit not implementing the feature."
		};
	}
}
