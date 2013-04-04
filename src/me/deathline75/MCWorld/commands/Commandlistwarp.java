package me.deathline75.MCWorld.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import me.deathline75.main.IMCWorldCommand;
import me.deathline75.main.MCWorld;

public class Commandlistwarp implements IMCWorldCommand{

	@Override
	public boolean executeCMD(CommandSender sender, String label, String[] args) {
		sender.sendMessage(ChatColor.GOLD + "List of avaliable warps: [World/Co-ordidates X/Y/Z/Yaw/Pitch]");
		sender.sendMessage(ChatColor.GRAY + "---");
		for(String warpname: MCWorld.props.getKeys(true)){
			String[] s = MCWorld.props.getString(warpname).split(",");
			Double x = Double.parseDouble(s[1]);
			Double y = Double.parseDouble(s[2]);
			Double z = Double.parseDouble(s[3]);
			Double yaw = Double.parseDouble(s[4]);
			Double pitch = Double.parseDouble(s[5]);
			sender.sendMessage((ChatColor.GREEN + warpname) + ChatColor.AQUA + ": " + s[0] + "/" + x.intValue() + "/" + y.intValue() + "/" + z.intValue() +"/" + yaw.intValue() + "/" + pitch.intValue());
			sender.sendMessage(ChatColor.GRAY + "---");
		}
		return true;
	}

	@Override
	public String[] getHelp() {
		// TODO Auto-generated method stub
		return new String[]{
			ChatColor.GRAY + "Usage: /mcw listwarp", 
			ChatColor.DARK_AQUA + "Description: Lists all the warps avaliable for use.",
			ChatColor.DARK_RED + "Notes: Ensure the world for the warp is loaded before teleporting to the specified warp."
		};
	}

}
