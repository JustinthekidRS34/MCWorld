package me.deathline75.MCWorld.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.deathline75.main.IMCWorldCommand;
import me.deathline75.main.PlayerWorld;

public class Commandunloadnosave implements IMCWorldCommand{

	@Override
	public boolean executeCMD(CommandSender sender, String label, String[] args) {
		if(args.length == 0){
			try{
			Bukkit.getServer().unloadWorld(PlayerWorld.getWorld(sender), false);
			PlayerWorld.removeSender(sender);
			sender.sendMessage(ChatColor.RED + "You have unloaded th" +
					"e world without saving the world.");
			}
			catch(NullPointerException e){
				sender.sendMessage(ChatColor.RED + "You have not selected a world!");
			}
		}
		if(args.length == 1){
			Bukkit.getServer().unloadWorld(Bukkit.getServer().getWorld(args[0]),false);
			sender.sendMessage(ChatColor.RED + "You have unloaded the world without saving the world.");
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
				Bukkit.getServer().unloadWorld(Bukkit.getServer().getWorld(worldname), false);
				player.sendMessage(ChatColor.RED + "You have unloaded the world without saving the world.");
			}
			catch(Exception e){
				sender.sendMessage(ChatColor.RED + "Error has occured. Please ensure you have the world loaded.");
			}
		}
		return true;
	}

}
