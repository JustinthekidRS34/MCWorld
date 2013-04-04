package me.deathline75.MCWorld.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.deathline75.main.IMCWorldCommand;
import me.deathline75.main.MCWorld;

public class Commandwarp implements IMCWorldCommand {

	@Override
	public boolean executeCMD(CommandSender sender, String label, String[] args) {
		if(sender instanceof Player){
			Player player = (Player)sender;
			if(args.length == 1){
				String[] stuff = MCWorld.props.getString(args[0], "").split(",");
				if(stuff[0] == ""){
					sender.sendMessage("Invalid warp name");
				}
				else{
					World world = Bukkit.getServer().getWorld(stuff[0]);
					float X = Float.parseFloat(stuff[1]);
					float Y = Float.parseFloat(stuff[2]);
					float Z = Float.parseFloat(stuff[3]);
					float yaw = Float.parseFloat(stuff[4]);
					float pitch = Float.parseFloat(stuff[5]);
					Location location = new Location(world, X, Y, Z, yaw, pitch);
					player.teleport(location);
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
