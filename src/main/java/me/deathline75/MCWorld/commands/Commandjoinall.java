package me.deathline75.MCWorld.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.deathline75.main.IMCWorldCommand;
import me.deathline75.main.PlayerWorld;

public class Commandjoinall implements IMCWorldCommand{

	@Override
	public boolean executeCMD(CommandSender sender, String label, String[] args) {
		if(args.length == 0){
			try{
				for(Player p:Bukkit.getServer().getOnlinePlayers()){
					p.teleport(PlayerWorld.getWorld(sender).getSpawnLocation());
					p.sendMessage("[MCWorld] You have been teleported by: " + sender.getName());
				}
			}
			catch(NullPointerException e){
				sender.sendMessage(ChatColor.RED + "You have not selected a world!");
			}
		}
		if(args.length == 1){
			try{
				for(Player p:Bukkit.getServer().getOnlinePlayers()){
					p.teleport(Bukkit.getServer().getWorld(args[0]).getSpawnLocation());
					p.sendMessage("[MCWorld] You have been teleported by: " + sender.getName());
				}
			}
			catch(NullPointerException e){
				sender.sendMessage(ChatColor.RED + "You have not selected a world!");
			}
		}
		if(args.length > 1){
			try{
				String worldname = "";
				for(String s:args){
					if(s != args[args.length - 1]){
						worldname += (s + " ");
					}
					else{
						worldname += (s);
					}
				}
				for(Player p:Bukkit.getServer().getOnlinePlayers()){
					p.teleport(Bukkit.getServer().getWorld(worldname).getSpawnLocation());
					p.sendMessage("[MCWorld] You have been teleported by: " + sender.getName());
				}
			}
			catch(Exception e){
				sender.sendMessage(ChatColor.RED + "Error has occured. Please ensure you have the world loaded.");
			}
		}
		return true;
	}
	
	@Override
	public String[] getHelp() {
		// TODO Auto-generated method stub
		return new String[]{
			ChatColor.GRAY + "Usage: /mcw joinall (World Name)", 
			ChatColor.DARK_AQUA + "Description: Teleports all players to the world's spawn point.",
			ChatColor.DARK_RED + "Notes: Ensure the world is loaded before teleporting."
		};
	}

}
