package me.deathline75.MCWorld.commands;

import java.io.IOException;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.deathline75.main.IMCWorldCommand;
import me.deathline75.main.MCWorld;

public class Commandsetwarp implements IMCWorldCommand {

	@Override
	public boolean executeCMD(CommandSender sender, String label, String[] args) {
		if(sender instanceof Player){
			Player player = (Player)sender;
			if(args.length == 1){
				MCWorld.props.set(args[0], player.getLocation().getWorld().getName() + "," +player.getLocation().getX() + ","+player.getLocation().getY() + ","+player.getLocation().getZ() + "," + player.getLocation().getYaw() + "," + player.getLocation().getPitch());
				sender.sendMessage("Warp: " + args[0] + " has been made.");
				try {
					MCWorld.props.save(MCWorld.getFileforwarps());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(args.length == 0){
				sender.sendMessage("Please specify a warp name.");
			}
		}
		else{
			sender.sendMessage("You are not in Minecraft!");
		}
		return true;
	}

}
