package me.deathline75.main;

import org.bukkit.command.CommandSender;

public abstract interface IMCWorldCommand{
	
	public abstract boolean executeCMD(CommandSender sender,
			String label, String[] args); 
}
