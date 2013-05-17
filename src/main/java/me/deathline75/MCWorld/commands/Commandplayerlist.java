package me.deathline75.MCWorld.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.deathline75.main.IMCWorldCommand;
import me.deathline75.main.PlayerWorld;

public class Commandplayerlist implements IMCWorldCommand{

	@Override
	public boolean executeCMD(CommandSender sender, String label, String[] args) {
		if(args.length == 0){
			try{
				String players = "";
				for(Player p :PlayerWorld.getWorld(sender).getPlayers()){
					players += (p.getName() + ", ");
				}
				sender.sendMessage("Players in world - " + PlayerWorld.getWorld(sender).getName() + ":" + players);
			}
			catch(Exception e){
				sender.sendMessage(ChatColor.RED + "Error has occured. Please ensure you have the world loaded.");
			}
		}
		if(args.length == 1){
			try{
				String players = "";
				for(Player p :Bukkit.getServer().getWorld(args[0]).getPlayers()){
					players += (p.getName() + ", ");
				}
				sender.sendMessage("Players in world - " + args[0] + ":" + players);
			}
			catch(Exception e){
				sender.sendMessage(ChatColor.RED + "Error has occured. Please ensure you have the world loaded.");
			}
		}
		if(args.length > 1){
			try{
				String players = "";
				String worldname = "";
				for(String s:args){
					if(s != args[args.length - 1]){
						worldname += (s + " ");
					}
					else{
						worldname += (s);
					}
				}
				for(Player p :Bukkit.getServer().getWorld(worldname).getPlayers()){
					players += (p.getName() + ", ");
				}
				sender.sendMessage("Players in world - " + worldname + ":" + players);
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
			ChatColor.GRAY + "Usage: /mcw playerlist (World Name)", 
			ChatColor.DARK_AQUA + "Description: Lists all the players in a world.",
			ChatColor.DARK_RED + "Notes: Ensure the world is loaded before using the command."
		};
	}

}
