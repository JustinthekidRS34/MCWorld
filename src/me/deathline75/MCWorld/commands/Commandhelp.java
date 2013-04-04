package me.deathline75.MCWorld.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import me.deathline75.main.IMCWorldCommand;
import me.deathline75.main.NewMCWorldListener;

public class Commandhelp implements IMCWorldCommand{

	@Override
	public boolean executeCMD(CommandSender sender, String label, String[] args) {
		if(args.length == 0){
			sender.sendMessage(ChatColor.YELLOW + "-------------------------");
			sender.sendMessage("Avaliable Commands for MCWorld");
			sender.sendMessage(ChatColor.GRAY + "| select | unload | tp | props | help | list | setwarp | warp | unloadnosave | playerlist | kickallworld | listwarp | help |");
			sender.sendMessage("For help on specific command, type in /mcw help [command]");
			sender.sendMessage(ChatColor.YELLOW + "-------------------------");
		}else if(args.length == 1){
			if(NewMCWorldListener.commandList.containsKey(args[0].toLowerCase())){
				sender.sendMessage(ChatColor.YELLOW + "-------------------------");
				sender.sendMessage(NewMCWorldListener.commandList.get(args[0].toLowerCase()).getHelp());
				sender.sendMessage(ChatColor.YELLOW + "-------------------------");
			}else{
				sender.sendMessage(ChatColor.YELLOW + "-------------------------");
				sender.sendMessage("Avaliable Commands for MCWorld");
				sender.sendMessage(ChatColor.GRAY + "| select | unload | tp | props | help | list | setwarp | warp | unloadnosave | playerlist | kickallworld | listwarp | help |");
				sender.sendMessage("For help on specific command, type in /mcw help [command]");
				sender.sendMessage(ChatColor.YELLOW + "-------------------------");
			}
		}else{
			sender.sendMessage(ChatColor.YELLOW + "-------------------------");
			sender.sendMessage("Avaliable Commands for MCWorld");
			sender.sendMessage(ChatColor.GRAY + "| select | unload | tp | props | help | list | setwarp | warp | unloadnosave | playerlist | kickallworld | listwarp | help |");
			sender.sendMessage("For help on specific command, type in /mcw help [command]");
			sender.sendMessage(ChatColor.YELLOW + "-------------------------");
		}
		return true;
	}

	@Override
	public String[] getHelp() {
		// TODO Auto-generated method stub
		return new String[]{
			ChatColor.GRAY + "Usage: /mcw help (command)", 
			ChatColor.DARK_AQUA + "Description: Gives information on commands.",
			ChatColor.DARK_RED + "Notes: Why are you finding /mcw help help?"
		};
	}

}
