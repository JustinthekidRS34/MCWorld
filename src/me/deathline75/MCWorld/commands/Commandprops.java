package me.deathline75.MCWorld.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import me.deathline75.main.IMCWorldCommand;
import me.deathline75.main.PlayerWorld;

public class Commandprops implements IMCWorldCommand{

	public boolean executeCMD(CommandSender sender, String label, String[] args) {
		if(PlayerWorld.getWorld(sender) != null){
			if(args.length == 0){
				sender.sendMessage(ChatColor.GREEN + "[MCWorld] Avaliable properties:");
				sender.sendMessage(ChatColor.YELLOW + "| ticksperanimalspawn | tickspermobspawn | mobspawn | animalspawn | difficulty | animalspawnlimit | mobspawnlimit | weatherduration | thundering | thunderduration | storm | fulltime | time | pvp | spawnlocation | keepspawninmemory | autosave |");
				sender.sendMessage(ChatColor.YELLOW + "For more information, visit the official Bukkit page.");
				return true;
			}
			
		}
		return false;
	}

}
