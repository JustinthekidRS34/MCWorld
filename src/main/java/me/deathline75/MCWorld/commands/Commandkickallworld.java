package me.deathline75.MCWorld.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.deathline75.main.IMCWorldCommand;
import me.deathline75.main.PlayerWorld;

public class Commandkickallworld implements IMCWorldCommand{

	@Override
	public boolean executeCMD(CommandSender sender, String label, String[] args) {
		if(args.length == 0){
			try{
			for(Player p:PlayerWorld.getWorld(sender).getPlayers()){
				p.kickPlayer("[MCWorld] An operator is having maintanance to the server.");
			}
			}
			catch(NullPointerException e){
				sender.sendMessage(ChatColor.RED + "You have not selected a world!");
			}
		}
		if(args.length == 1){
			try{
			for(Player p:Bukkit.getServer().getWorld(args[0]).getPlayers()){
				p.kickPlayer("[MCWorld] An operator is having maintanance to the server.");
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
				for(Player p:Bukkit.getServer().getWorld(worldname).getPlayers()){
					p.kickPlayer("[MCWorld] An operator is having maintanance to the server.");
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
			ChatColor.GRAY + "Usage: /mcw kickallworld (World Name)", 
			ChatColor.DARK_AQUA + "Description: Kicks everyone that is in the world out of the server.",
			ChatColor.DARK_RED + "Notes: The kicked message is '[MCWorld] An operator is having maintanance to the server.'"
		};
	}

}
