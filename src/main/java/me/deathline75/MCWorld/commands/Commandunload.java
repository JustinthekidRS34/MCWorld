package me.deathline75.MCWorld.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.deathline75.main.IMCWorldCommand;
import me.deathline75.main.PlayerWorld;

public class Commandunload implements IMCWorldCommand{

	@Override
	public boolean executeCMD(CommandSender sender, String label, String[] args) {
		if(args.length == 0){
			try{
			Bukkit.getServer().unloadWorld(PlayerWorld.getWorld(sender), true);
			PlayerWorld.removeSender(sender);
			sender.sendMessage(ChatColor.RED + "You have unloaded the world.");
			}
			catch(NullPointerException e){
				sender.sendMessage(ChatColor.RED + "You have not selected a world!");
			}
		}
		if(args.length == 1){
			Bukkit.getServer().unloadWorld(Bukkit.getServer().getWorld(args[0]), true);
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
				Bukkit.getServer().unloadWorld(Bukkit.getServer().getWorld(worldname), true);
				player.sendMessage(ChatColor.RED + "You have unloaded the world.");
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
			ChatColor.GRAY + "Usage: /mcw unload (World Name)", 
			ChatColor.DARK_AQUA + "Description: Unloads a world",
			ChatColor.DARK_RED + "Notes: Ensure the world is loaded before unloading the world."
		};
	}

}
