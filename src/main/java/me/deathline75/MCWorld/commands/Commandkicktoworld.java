package me.deathline75.MCWorld.commands;

import me.deathline75.main.IMCWorldCommand;
import me.deathline75.main.PlayerWorld;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commandkicktoworld implements IMCWorldCommand{

    @Override
    public boolean executeCMD(CommandSender sender, String label, String[] args) {
	if(args.length == 0){
	    sender.sendMessage(ChatColor.RED + "You need to specify a world for them to be sent to!");
	}
	if(args.length == 1){
		try{			    
		    World tpworld = Bukkit.getServer().getWorld(args[0]);
			for(Player p:PlayerWorld.getWorld(sender).getPlayers()){
				p.teleport(tpworld.getSpawnLocation());
				p.sendMessage(ChatColor.DARK_PURPLE + "You have been forced into this world via an admin.");
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
			    World tpworld = Bukkit.getServer().getWorld(worldname);
				for(Player p:PlayerWorld.getWorld(sender).getPlayers()){
					p.teleport(tpworld.getSpawnLocation());
					p.sendMessage(ChatColor.DARK_PURPLE + "You have been forced into this world via an admin.");
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
	return new String[]{
		ChatColor.GRAY + "Usage: /mcw kicktoworld (World Name)", 
		ChatColor.DARK_AQUA + "Description: Kicks everyone in your selected world into the world provided in command",
		ChatColor.DARK_RED + "Notes: Ensure that the specified world is loaded."
	};
    }

}
