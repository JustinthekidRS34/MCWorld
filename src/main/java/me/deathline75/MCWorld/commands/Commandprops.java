package me.deathline75.MCWorld.commands;

import java.io.IOException;

import me.deathline75.main.IMCWorldCommand;
import me.deathline75.main.MCWorld;
import me.deathline75.main.PlayerWorld;

import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

public class Commandprops implements IMCWorldCommand{

	public boolean executeCMD(CommandSender sender, String label, String[] args) {
		if(PlayerWorld.getWorld(sender) != null){
			if(args.length == 0){
				sender.sendMessage(ChatColor.DARK_GRAY + "--------------------------------------------------");
				sender.sendMessage(ChatColor.GREEN + "[MCWorld] Avaliable properties:");
				sender.sendMessage(ChatColor.GRAY + "-------------------------");
				sender.sendMessage((ChatColor.RED + "Spawning Entities: ") + ChatColor.YELLOW + "(Int) ticksperanimalspawn, (Int) tickspermobspawn, (Boolean) mobspawn, (Boolean) animalspawn, (Int) animalspawnlimit, (Int) mobspawnlimit, (Int/String) difficulty");
				sender.sendMessage(ChatColor.GRAY + "-------------------------");
				sender.sendMessage((ChatColor.RED + "Weather & Time: ") + ChatColor.YELLOW + "(Int) weatherduration, (Boolean) thundering, (Int) thunderduration, (Boolean) storm, (Int) fulltime, (Int/String) time");
				sender.sendMessage(ChatColor.GRAY + "-------------------------");
				sender.sendMessage((ChatColor.RED + "Others: ") + ChatColor.YELLOW + "(Boolean) pvp, (x,y,z) spawnlocation, (Boolean) keepspawninmemory, (Boolean) autosave");
				sender.sendMessage(ChatColor.GRAY + "-------------------------");
				sender.sendMessage(ChatColor.GREEN + "For more information, visit the official Bukkit page.");
				sender.sendMessage(ChatColor.DARK_GRAY + "--------------------------------------------------");
				return true;
			}
			if(args.length == 2){
				String label2 = args[0].toLowerCase();
				World world = PlayerWorld.getWorld(sender);
				ConfigurationSection properties = MCWorld.getPropertiesFile().getConfigurationSection(PlayerWorld.getWorld(sender).getName());
				if(label2.equalsIgnoreCase( "ticksperanimalspawn")){
					try{
						world.setTicksPerAnimalSpawns(Integer.parseInt(args[1]));
						sender.sendMessage("The property)){ " + label2.toUpperCase() + " has been set to)){ " + args[1]);
						properties.set(label2, Integer.parseInt(args[1]));
					}catch(Exception e){
						sender.sendMessage(ChatColor.RED + "Error has occured. You may not have selected a world or provided an invalid input.");
						e.printStackTrace();
					}
					}
				if(label2.equalsIgnoreCase( "tickspermobspawn")){
					try{
						world.setTicksPerMonsterSpawns(Integer.parseInt(args[1]));
						sender.sendMessage("The property)){ " + label2.toUpperCase() + " has been set to)){ " + args[1]);
						properties.set(label2, Integer.parseInt(args[1]));
					}catch(Exception e){
						sender.sendMessage(ChatColor.RED + "Error has occured. You may not have selected a world or provided an invalid input.");
						e.printStackTrace();
					}
					}
				if(label2.equalsIgnoreCase( "mobspawn")){
					try{
						PlayerWorld.getWorld(sender).setSpawnFlags(Boolean.parseBoolean(args[1]), world.getAllowAnimals());
						sender.sendMessage("The property)){ " + label2.toUpperCase() + " has been set to)){ " + args[1]);
						properties.set(label2, Boolean.parseBoolean(args[1]));
					}catch(Exception e){
						sender.sendMessage(ChatColor.RED + "Error has occured. You may not have selected a world or provided an invalid input.");
						e.printStackTrace();
					}
					}
				if(label2.equalsIgnoreCase( "animalspawn")){
					try{
						PlayerWorld.getWorld(sender).setSpawnFlags(world.getAllowMonsters(),Boolean.parseBoolean(args[1]));
						sender.sendMessage("The property)){ " + label2.toUpperCase() + " has been set to)){ " + args[1]);
						properties.set(label2, Boolean.parseBoolean(args[1]));
					}catch(Exception e){
						sender.sendMessage(ChatColor.RED + "Error has occured. You may not have selected a world or provided an invalid input.");
						e.printStackTrace();
					}
					}
				if(label2.equalsIgnoreCase( "animalspawnlimit")){
					try{
						world.setAnimalSpawnLimit(Integer.parseInt(args[1]));
						sender.sendMessage("The property)){ " + label2.toUpperCase() + " has been set to)){ " + args[1]);
						properties.set(label2, Integer.parseInt(args[1]));
					}catch(Exception e){
						sender.sendMessage(ChatColor.RED + "Error has occured. You may not have selected a world or provided an invalid input.");
						e.printStackTrace();
					}
					}
				if(label2.equalsIgnoreCase( "mobspawnlimit")){
					try{
						world.setMonsterSpawnLimit(Integer.parseInt(args[1]));
						sender.sendMessage("The property)){ " + label2.toUpperCase() + " has been set to)){ " + args[1]);
						properties.set(label2, Integer.parseInt(args[1]));
					}catch(Exception e){
						sender.sendMessage(ChatColor.RED + "Error has occured. You may not have selected a world or provided an invalid input.");
						e.printStackTrace();
					}
					}
				if(label2.equalsIgnoreCase( "difficulty")){
					try{
						int difficulty = (Integer.parseInt(args[1]));
						if(difficulty < 3){
							world.setDifficulty(Difficulty.getByValue(difficulty));
							sender.sendMessage("The property)){ " + label2.toUpperCase() + " has been set to)){ " + world.getDifficulty().name());
							properties.set(label2, Integer.parseInt(args[1]));
						}
						else{
							sender.sendMessage(ChatColor.RED + "Error has occured. You have provided an invalid input.");
						}
					}
					catch(Exception e){
						if(args[1].equalsIgnoreCase("easy")){
							world.setDifficulty(Difficulty.EASY);
							sender.sendMessage("The property)){ " + label2.toUpperCase() + " has been set to)){ " + world.getDifficulty().name());
							properties.set(label2, 1);
							}
						else if(args[1].equalsIgnoreCase("normal")){
							world.setDifficulty(Difficulty.NORMAL);	
							sender.sendMessage("The property)){ " + label2.toUpperCase() + " has been set to)){ " + world.getDifficulty().name());
							properties.set(label2, 2);
							}
						else if(args[1].equalsIgnoreCase("hard")){
							world.setDifficulty(Difficulty.HARD);
							sender.sendMessage("The property)){ " + label2.toUpperCase() + " has been set to)){ " + world.getDifficulty().name());
							properties.set(label2, 3);
						}
						else if(args[1].equalsIgnoreCase("peaceful")){
							world.setDifficulty(Difficulty.PEACEFUL);
							sender.sendMessage("The property)){ " + label2.toUpperCase() + " has been set to)){ " + world.getDifficulty().name());
							properties.set(label2, 0);
						}
						else{
							sender.sendMessage(ChatColor.RED + "Error has occured. You may not have selected a world or provided an invalid input.");
							e.printStackTrace();
						}
					}
					}
				if(label2.equalsIgnoreCase( "weatherduration")){
					try{
						world.setWeatherDuration(Integer.parseInt(args[1]));
						sender.sendMessage("The property)){ " + label2.toUpperCase() + " has been set to)){ " + args[1]);
					}catch(Exception e){
						sender.sendMessage(ChatColor.RED + "Error has occured. You may not have selected a world or provided an invalid input.");
						e.printStackTrace();
					}
					}
				if(label2.equalsIgnoreCase( "thundering")){
					try{
						world.setThundering(Boolean.parseBoolean(args[1]));
						sender.sendMessage("The property)){ " + label2.toUpperCase() + " has been set to)){ " + args[1]);
					}catch(Exception e){
						sender.sendMessage(ChatColor.RED + "Error has occured. You may not have selected a world or provided an invalid input.");
						e.printStackTrace();
					}
					}
				if(label2.equalsIgnoreCase( "thunderduration")){
					try{
						world.setThunderDuration(Integer.parseInt(args[1]));
						sender.sendMessage("The property)){ " + label2.toUpperCase() + " has been set to)){ " + args[1]);
					}catch(Exception e){
						sender.sendMessage(ChatColor.RED + "Error has occured. You may not have selected a world or provided an invalid input.");
						e.printStackTrace();
					}
					}
				if(label2.equalsIgnoreCase( "storm")){
					try{
						world.setStorm(Boolean.parseBoolean(args[1]));
						sender.sendMessage("The property)){ " + label2.toUpperCase() + " has been set to)){ " + args[1]);
					}catch(Exception e){
						sender.sendMessage(ChatColor.RED + "Error has occured. You may not have selected a world or provided an invalid input.");
						e.printStackTrace();
					}
					}
				if(label2.equalsIgnoreCase( "fulltime")){
					try{
						world.setFullTime(Long.parseLong(args[1]));
						sender.sendMessage("The property)){ " + label2.toUpperCase() + " has been set to)){ " + args[1]);
						properties.set(label2, Integer.parseInt(args[1]));
					}catch(Exception e){
						sender.sendMessage(ChatColor.RED + "Error has occured. You may not have selected a world or provided an invalid input.");
						e.printStackTrace();
					}
					}
				if(label2.equalsIgnoreCase( "time")){
					try{
						world.setTime(Long.parseLong(args[1]));
						sender.sendMessage("The property)){ " + label2.toUpperCase() + " has been set to)){ " + args[1]);
					}catch(Exception e){
						if(args[1].equalsIgnoreCase("day")){
							world.setTime(0);
							sender.sendMessage("The property)){ " + label2.toUpperCase() + " has been set to)){ 0");
						}
						if(args[1].equalsIgnoreCase("midday")){
							world.setTime(6000);
							sender.sendMessage("The property)){ " + label2.toUpperCase() + " has been set to)){ 6000");
						}
						if(args[1].equalsIgnoreCase("night")){
							world.setTime(12000);
							sender.sendMessage("The property)){ " + label2.toUpperCase() + " has been set to)){ 12000");
						}
						if(args[1].equalsIgnoreCase("midnight")){
							world.setTime(18000);
							sender.sendMessage("The property)){ " + label2.toUpperCase() + " has been set to)){ 18000");
						}else{
						sender.sendMessage(ChatColor.RED + "Error has occured. You may not have selected a world or provided an invalid input.");
						e.printStackTrace();
						}
					}
					}
				if(label2.equalsIgnoreCase( "pvp")){
					try{
						world.setPVP(Boolean.parseBoolean(args[1]));
						sender.sendMessage("The property)){ " + label2.toUpperCase() + " has been set to)){ " + args[1]);
						properties.set(label2, Boolean.parseBoolean(args[1]));
					}catch(Exception e){
						sender.sendMessage(ChatColor.RED + "Error has occured. You may not have selected a world or provided an invalid input.");
						e.printStackTrace();
					}
					}
				if(label2.equalsIgnoreCase( "spawnlocation")){
					try{
						String[] coordinates = args[1].split(",");
						if(coordinates.length == 3){
							world.setSpawnLocation(Integer.parseInt(coordinates[0]),Integer.parseInt(coordinates[1]),Integer.parseInt(coordinates[2]));
							sender.sendMessage("The property)){ " + label2.toUpperCase() + " has been set to)){ " + args[1]);
						}
						else{
							sender.sendMessage(ChatColor.RED + "Error has occured. You may not have selected a world or provided an invalid input.");
						}
					}catch(Exception e){
						sender.sendMessage(ChatColor.RED + "Error has occured. You may not have selected a world or provided an invalid input.");
						e.printStackTrace();
					}
					}
				if(label2.equalsIgnoreCase( "keepspawninmemory")){
					try{
						world.setKeepSpawnInMemory(Boolean.parseBoolean(args[1]));
						sender.sendMessage("The property)){ " + label2.toUpperCase() + " has been set to)){ " + args[1]);
						properties.set(label2, Boolean.parseBoolean(args[1]));
					}catch(Exception e){
						sender.sendMessage(ChatColor.RED + "Error has occured. You may not have selected a world or provided an invalid input.");
						e.printStackTrace();
					}
					}
				if(label2.equalsIgnoreCase( "autosave")){
					try{
						world.setAutoSave(Boolean.parseBoolean(args[1]));
						sender.sendMessage("The property)){ " + label2.toUpperCase() + " has been set to)){ " + args[1]);
						properties.set(label2, Boolean.parseBoolean(args[1]));
					}catch(Exception e){
						sender.sendMessage(ChatColor.RED + "Error has occured. You may not have selected a world or provided an invalid input.");
						e.printStackTrace();
					}
					}
				else{
					sender.sendMessage(ChatColor.RED + "Invalid properties. Type /mcw props for the list.");
					}
				
				try {
					MCWorld.getPropertiesFile().save(MCWorld.getProperties());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
				}
				
			}
		sender.sendMessage(ChatColor.RED + "You have not selected a world yet!");
		return false;
	}
	
	@Override
	public String[] getHelp() {
		// TODO Auto-generated method stub
		return new String[]{
			ChatColor.GRAY + "Usage: /mcw props ([Property Name] (Value))", 
			ChatColor.DARK_AQUA + "Description: Checks/Alters the value of the property of the world.",
			ChatColor.DARK_RED + "Notes: Ensure that you have selected a world before using. Typing /mcw props will give the properties."
		};
	}

}
