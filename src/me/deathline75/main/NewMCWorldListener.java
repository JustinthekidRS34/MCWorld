package me.deathline75.main;

import org.bukkit.ChatColor;
import org.bukkit.WorldCreator;
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
	
	@EventHandler
	public void onPlayerJoinGame(PlayerJoinEvent e){
		e.setJoinMessage(ChatColor.LIGHT_PURPLE + "Welcome "+ e.getPlayer().getDisplayName() + " to the server!");
		e.getPlayer().sendMessage("[MCWorld] You are currently in World: "+ e.getPlayer().getWorld().getName());
		if(!e.getPlayer().hasPlayedBefore()){
			
		}
	}
	
	@Override
	public void onCommand(CommandSender sender, String label ,String[] args, Command cmd){
		if(label.equalsIgnoreCase("select")){
			if(args.length == 0){
				try{
					sender.sendMessage((ChatColor.GREEN + "Currently selected world: " )+ ChatColor.YELLOW + PlayerWorld.getWorld(sender).getName());
				}
				catch(NullPointerException e){
					sender.sendMessage(ChatColor.RED + "You have not selected a world yet!");
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
					PlayerWorld playerworld = new PlayerWorld(sender, mcworld.getServer().createWorld(new WorldCreator(worldname)));
				}
				else{
					PlayerWorld playerworld = new PlayerWorld(sender, mcworld.getServer().createWorld(new WorldCreator(args[0])));
				}
				sender.sendMessage(ChatColor.GREEN + "You have selected and loaded the world.");
				return;
			}
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
				}
				catch(Exception e){
					sender.sendMessage(ChatColor.RED + "Error has occured. Please ensure you have the world loaded.");
				}
			}
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
		}
		return;
	}	
}
