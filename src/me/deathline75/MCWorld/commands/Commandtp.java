package me.deathline75.MCWorld.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.deathline75.main.IMCWorldCommand;
import me.deathline75.main.PlayerWorld;

public class Commandtp implements IMCWorldCommand {

	@Override
	public boolean executeCMD(CommandSender sender, String label, String[] args) {
		if(args.length == 0){
			if(sender instanceof Player){
				if((sender.hasPermission("mcw.command.tp") || sender.hasPermission("mcw.command.tp.world."+PlayerWorld.getWorld(sender).getName())) && !sender.hasPermission("mcw.command.tp.world.!" + PlayerWorld.getWorld(sender))){
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
					sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this command.");
				}

			}else{
				sender.sendMessage("You are not in Minecraft!");
			}
		}
		if(args.length == 1){
			if(sender instanceof Player){
				try{
					if((sender.hasPermission("mcw.command.tp") || sender.hasPermission("mcw.command.tp.world."+args[0])) && !sender.hasPermission("mcw.command.tp.world.!" + args[0])){
					Player player = (Player)sender;
					player.teleport(Bukkit.getServer().getWorld(args[0]).getSpawnLocation());
					}
					else{
						sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this command.");
					}
				}
				catch(Exception e){
					sender.sendMessage(ChatColor.DARK_RED + "Error has occured. Please ensure you have the world loaded.");
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
					if((sender.hasPermission("mcw.command.tp") || sender.hasPermission("mcw.command.tp.world."+worldname.replace(' ', '_'))) && !sender.hasPermission("mcw.command.tp.world.!" + worldname.replace(' ', '_'))){
					player.teleport(Bukkit.getServer().getWorld(worldname).getSpawnLocation());
					}
					else{
						sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this command.");
					}
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
	
	@Override
	public String[] getHelp() {
		// TODO Auto-generated method stub
		return new String[]{
			ChatColor.GRAY + "Usage: /mcw tp (World Name)", 
			ChatColor.DARK_AQUA + "Description: Teleports to a world's spawn point",
			ChatColor.DARK_RED + "Notes: Ensure the world is loaded before teleporting to the world."
		};
	}

}
