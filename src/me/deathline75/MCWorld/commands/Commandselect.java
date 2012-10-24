package me.deathline75.MCWorld.commands;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;

import me.deathline75.main.IMCWorldCommand;
import me.deathline75.main.MCWorld;
import me.deathline75.main.PlayerWorld;

public class Commandselect implements IMCWorldCommand{

	private static Map<String, String> properties = new HashMap<String, String>();
	
	@Override
	public boolean executeCMD(CommandSender sender, String label, String[] args) {
		if(label.equalsIgnoreCase("select")){
			if(args.length == 0){
				try{
					sender.sendMessage((ChatColor.GREEN + "Currently selected world: " )+ ChatColor.YELLOW + PlayerWorld.getWorld(sender).getName());
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
					if(MCWorld.getPropertiesFile().isConfigurationSection(worldname)){
						
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
		properties.put("ticksperanimalspawn", "400");
		properties.put("tickspermobspawn", "1");
		properties.put("mobspawn", "TRUE");
		properties.put("animalspawn", "TRUE");
		properties.put("mobspawnlimit", "70");
		properties.put("animalspawnlimit", "15");
		properties.put("difficulty", "1");
		properties.put("fulltime", "1");
		properties.put("pvp", "FALSE");
		properties.put("autosave", "TRUE");
		properties.put("keepspawninmemory", "TRUE");
	}

}
