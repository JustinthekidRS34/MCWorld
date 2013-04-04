package me.deathline75.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import me.deathline75.MCWorld.commands.Commandjoinall;
import me.deathline75.MCWorld.commands.Commandkickallworld;
import me.deathline75.MCWorld.commands.Commandlist;
import me.deathline75.MCWorld.commands.Commandplayerlist;
import me.deathline75.MCWorld.commands.Commandprops;
import me.deathline75.MCWorld.commands.Commandselect;
import me.deathline75.MCWorld.commands.Commandunloadnosave;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class NewMCWorldListener extends MCWorldListener{

	public NewMCWorldListener(MCWorld var1) {
		super(var1);
		// TODO Auto-generated constructor stub
	}
	
	private static Map<String, IMCWorldCommand> commandList = new HashMap<String, IMCWorldCommand>();
	
	@EventHandler
	public void onPlayerJoinGame(PlayerJoinEvent e){
		e.getPlayer().sendMessage("[MCWorld] You are currently in World: "+ e.getPlayer().getWorld().getName());
	}
	
	//All commands will be moved to new classes when the plugin is stable.
	//Because I am lazy.
	//And school is kind of terrible.
	@Override
	public boolean onCommand(CommandSender sender, String label ,String[] args, Command cmd){
		mcworld.log.info("[MCWorld] "+sender.getName() + " just used the command: /mcw "+ label);
		if(commandList.containsKey(label)){
			boolean b = commandList.get(label).executeCMD(sender, label, args);
			if(!b){
				sender.sendMessage("Unknown command. Type /mcw help for commands.");
			}
			return true;
		}
		
		if(label.equalsIgnoreCase("listwarp")){
			sender.sendMessage(ChatColor.GOLD + "List of avaliable warps: [World/Co-ordidates X/Y/Z/Yaw/Pitch]");
			sender.sendMessage(ChatColor.GRAY + "---");
			for(String warpname: MCWorld.props.getKeys(true)){
				String[] s = MCWorld.props.getString(warpname).split(",");
				Double x = Double.parseDouble(s[1]);
				Double y = Double.parseDouble(s[2]);
				Double z = Double.parseDouble(s[3]);
				Double yaw = Double.parseDouble(s[4]);
				Double pitch = Double.parseDouble(s[5]);
				sender.sendMessage((ChatColor.GREEN + warpname) + ChatColor.AQUA + ": " + s[0] + "/" + x.intValue() + "/" + y.intValue() + "/" + z.intValue() +"/" + yaw.intValue() + "/" + pitch.intValue());
				sender.sendMessage(ChatColor.GRAY + "---");
			}
		}
		
		if(label.equalsIgnoreCase("setwarp")){
			if(sender instanceof Player){
				Player player = (Player)sender;
				if(args.length == 1){
					MCWorld.props.set(args[0], player.getLocation().getWorld().getName() + "," +player.getLocation().getX() + ","+player.getLocation().getY() + ","+player.getLocation().getZ() + "," + player.getLocation().getYaw() + "," + player.getLocation().getPitch());
					sender.sendMessage("Warp: " + args[0] + " has been made.");
					try {
						MCWorld.props.save(MCWorld.getFileforwarps());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(args.length == 0){
					sender.sendMessage("Please specify a warp name.");
				}
			}
			else{
				sender.sendMessage("You are not in Minecraft!");
			}
			return true;
		}
		if(label.equalsIgnoreCase("warp")){
			if(sender instanceof Player){
				Player player = (Player)sender;
				if(args.length == 1){
					String[] stuff = MCWorld.props.getString(args[0], "").split(",");
					if(stuff[0] == ""){
						sender.sendMessage("Invalid warp name");
					}
					else{
						World world = mcworld.getServer().getWorld(stuff[0]);
						float X = Float.parseFloat(stuff[1]);
						float Y = Float.parseFloat(stuff[2]);
						float Z = Float.parseFloat(stuff[3]);
						float yaw = Float.parseFloat(stuff[4]);
						float pitch = Float.parseFloat(stuff[5]);
						Location location = new Location(world, X, Y, Z, yaw, pitch);
						player.teleport(location);
					}
				}
				else if(args.length == 0){
					sender.sendMessage("Please specify a warp name.");
				}
			}
			else{
				sender.sendMessage("You are not in Minecraft!");
			}
			return true;
		}
		
		if(label.equalsIgnoreCase("unload")){
			if(args.length == 0){
				try{
				mcworld.getServer().unloadWorld(PlayerWorld.getWorld(sender), true);
				PlayerWorld.removeSender(sender);
				sender.sendMessage(ChatColor.RED + "You have unloaded the world.");
				}
				catch(NullPointerException e){
					sender.sendMessage(ChatColor.RED + "You have not selected a world!");
				}
			}
			if(args.length == 1){
				mcworld.getServer().unloadWorld(mcworld.getServer().getWorld(args[0]), true);
				sender.sendMessage(ChatColor.RED + "You have unloaded the world.");
			}
			if(args.length > 1){
				try{
					Player player = (Player)sender;
					String worldname = "";
					for(String s:args){
						if(s != args[args.length - 1]){
							worldname += (s + " ");
						}
						else{
							worldname += (s);
						}
					}
					mcworld.getServer().unloadWorld(mcworld.getServer().getWorld(worldname), true);
					player.sendMessage(ChatColor.RED + "You have unloaded the world.");
				}
				catch(Exception e){
					sender.sendMessage(ChatColor.RED + "Error has occured. Please ensure you have the world loaded.");
				}
			}
			return true;
		}
		if(label.equalsIgnoreCase("tp")){
			if(args.length == 0){
				if(sender instanceof Player){
					try{
						Player player = (Player)sender;
						if(PlayerWorld.getWorld(sender).getPlayers().contains(sender)){
							player.teleport(PlayerWorld.getWorld(sender).getSpawnLocation());
						}
						player.teleport(PlayerWorld.getWorld(sender).getSpawnLocation());
					}
					catch(NullPointerException e){
						sender.sendMessage(ChatColor.RED + "You have not selected a world!");
					}
				}else{
					sender.sendMessage("You are not in Minecraft!");
				}
			}
			if(args.length == 1){
				if(sender instanceof Player){
					try{
						Player player = (Player)sender;
						player.teleport(mcworld.getServer().getWorld(args[0]).getSpawnLocation());
					}
					catch(Exception e){
						sender.sendMessage(ChatColor.RED + "Error has occured. Please ensure you have the world loaded.");
					}
				}else{
					sender.sendMessage("You are not in Minecraft!");
				}
			}
			if(args.length > 1){
				if(sender instanceof Player){
					try{
						Player player = (Player)sender;
						String worldname = "";
						for(String s:args){
							if(s != args[args.length - 1]){
								worldname += (s + " ");
							}
							else{
								worldname += (s);
							}
						}
						player.teleport(mcworld.getServer().getWorld(worldname).getSpawnLocation());
					}
					catch(Exception e){
						sender.sendMessage(ChatColor.RED + "Error has occured. Please ensure you have the world loaded.");
					}
				}else{
					sender.sendMessage("You are not in Minecraft!");
				}
			}
			return true;
		}
		return false;
	}	
	
	static{
		commandList.put("props", new Commandprops());
		commandList.put("select", new Commandselect());
		commandList.put("create", new Commandselect());
		commandList.put("load", new Commandselect());
		commandList.put("unloadnosave", new Commandunloadnosave());
		commandList.put("playerlist", new Commandplayerlist());
		commandList.put("kickallworld", new Commandkickallworld());
		commandList.put("joinall", new Commandjoinall());
		commandList.put("list", new Commandlist());
	}
	
}
