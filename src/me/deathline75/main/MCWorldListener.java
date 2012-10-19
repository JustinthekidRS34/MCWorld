package me.deathline75.main;

import org.bukkit.ChatColor;
import org.bukkit.World.Environment;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MCWorldListener implements Listener{
	
	protected MCWorld mcworld;
	
	public MCWorldListener(MCWorld var1){
		this.mcworld = var1;
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e){
		String worldname = "";
		if(e.getPlayer().getWorld().getEnvironment() == Environment.NORMAL){
			worldname =ChatColor.AQUA + ("[" + e.getPlayer().getWorld().getName() + "] ");
		}
		if(e.getPlayer().getWorld().getEnvironment() == Environment.NETHER){
			worldname =ChatColor.DARK_RED + ("[" + e.getPlayer().getWorld().getName() + "] ");
		}
		if(e.getPlayer().getWorld().getEnvironment() == Environment.THE_END){
			worldname =ChatColor.YELLOW + ("[" + e.getPlayer().getWorld().getName() + "] ");
		}
		e.setFormat(worldname+e.getPlayer().getDisplayName()+ ": " + e.getMessage());
	}
	
	public boolean onCommand(CommandSender sender, String label ,String[] args, Command cmd){
		mcworld.log.info("[MCWorld] "+sender.getName() + " just used the command: /mcw "+ label);
		/*
		if(label.equalsIgnoreCase("create")){
			if(args[1].isEmpty()){
				sender.sendMessage(ChatColor.RED + "You did not set the world name!");
			}
			else{
				mcworld.getServer().createWorld(new WorldCreator(args[1]));
				this.world = new WorldCreator(args[1]).createWorld();
				sender.sendMessage(ChatColor.GREEN + "World: " + args[1] + " has been created and/or loaded and selected.");
			}
		}
		if(label.equalsIgnoreCase("unload")){
			if(args[1].isEmpty()){
				sender.sendMessage(ChatColor.RED + "You did not set the world name!");
			}
			else{
				mcworld.getServer().unloadWorld(args[1], true);
			}
		}
		if(label.equalsIgnoreCase("tp")){
			if(sender instanceof Player){
				Player player = (Player)sender;
				player.teleport(new Location(mcworld.getServer().getWorld(args[1]), mcworld.getServer().getWorld(args[1]).getSpawnLocation().getX(), mcworld.getServer().getWorld(args[1]).getSpawnLocation().getY(), mcworld.getServer().getWorld(args[1]).getSpawnLocation().getZ()), TeleportCause.COMMAND);
			}
			else{
				
			}
		}
		if(label.equalsIgnoreCase("props")){
			if(this.world == null){
				sender.sendMessage("You have not selected a world!");
			}
			if(args.length == 1){
				sender.sendMessage("Avaliable properties");
				sender.sendMessage("mobspawn, animalspawn, difficulty");
			}
			if(args.length == 3){
				if(args[1].equalsIgnoreCase("mobspawn")){
						boolean b = Boolean.parseBoolean(args[2]);
						world.setSpawnFlags(b, world.getAllowAnimals());
						sender.sendMessage("The flag has been set as: " + b);
				}
				if(args[1].equalsIgnoreCase("animalspawn")){
					boolean b = Boolean.parseBoolean(args[2]);
					world.setSpawnFlags(world.getAllowMonsters(), b);
					sender.sendMessage("The flag has been set as: " + b);
				}
				if(args[1].equalsIgnoreCase("difficulty")){
					world.setDifficulty(Difficulty.getByValue(Integer.parseInt(args[2])));
					sender.sendMessage("The flag has been set as: " + args[2]);
				}
			}
		}
		*/
		return false;
	}
}
