package me.deathline75.MCWorld.commands;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import me.deathline75.main.IMCWorldCommand;

public class Commandlist implements IMCWorldCommand {

	@Override
	public boolean executeCMD(CommandSender sender, String label, String[] args) {
		if(label.equalsIgnoreCase("list")){
			if(args.length == 0){
				ArrayList<String> worldName = new ArrayList<String>();
				for(File f:Bukkit.getWorldContainer().listFiles()){
					if(f.isDirectory()){
						for(File f2: f.listFiles()){
							if(f2.getName().equalsIgnoreCase("level.dat")){
								if(Bukkit.getWorld(f.getName()) != null){
									worldName.add(ChatColor.GREEN + f.getName());
								}
								else{
									worldName.add(ChatColor.RED + f.getName());
								}
								break;
							}
							else{
								continue;
							}
						}
					}
				}
				sender.sendMessage(ChatColor.BOLD + (ChatColor.AQUA + "Currently Found Worlds:"));
				for(String s: worldName){
					sender.sendMessage(s);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public String[] getHelp() {
		// TODO Auto-generated method stub
		return new String[]{
			ChatColor.GRAY + "Usage: /mcw list", 
			ChatColor.DARK_AQUA + "Description: Lists all the worlds found in the server directory.",
			ChatColor.DARK_RED + "Notes: GREEN represents loaded world while RED represents unloaded world."
		};
	}

}
