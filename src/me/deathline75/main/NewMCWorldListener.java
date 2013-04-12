package me.deathline75.main;

import java.util.HashMap;
import java.util.Map;

import me.deathline75.MCWorld.commands.Commandcreatef;
import me.deathline75.MCWorld.commands.Commandhelp;
import me.deathline75.MCWorld.commands.Commandjoinall;
import me.deathline75.MCWorld.commands.Commandkickallworld;
import me.deathline75.MCWorld.commands.Commandlist;
import me.deathline75.MCWorld.commands.Commandlistwarp;
import me.deathline75.MCWorld.commands.Commandplayerlist;
import me.deathline75.MCWorld.commands.Commandprops;
import me.deathline75.MCWorld.commands.Commandselect;
import me.deathline75.MCWorld.commands.Commandsetwarp;
import me.deathline75.MCWorld.commands.Commandtp;
import me.deathline75.MCWorld.commands.Commandunload;
import me.deathline75.MCWorld.commands.Commandunloadnosave;
import me.deathline75.MCWorld.commands.Commandwarp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class NewMCWorldListener extends MCWorldListener{

	public NewMCWorldListener(MCWorld var1) {
		super(var1);
		// TODO Auto-generated constructor stub
	}
	
	public static Map<String, IMCWorldCommand> commandList = new HashMap<String, IMCWorldCommand>();
	
	@EventHandler
	public void onPlayerJoinGame(PlayerJoinEvent e){
		e.getPlayer().sendMessage("[MCWorld] You are currently in World: "+ e.getPlayer().getWorld().getName());
	}
	
	//All commands will be moved to new classes when the plugin is stable.
	//Because I am lazy.
	//And school is kind of terrible.
	@Override
	public boolean onCommand(CommandSender sender, String label ,String[] args, Command cmd){
		mcworld.log.info("[MCWorld] "+sender.getName() + " just used the command: /mcw "+ label);
		if(commandList.containsKey(label)){
			boolean b = commandList.get(label).executeCMD(sender, label, args);
			if(!b){
				sender.sendMessage("Unknown command. Type /mcw help for commands.");
			}
			return true;
		}else{
			sender.sendMessage("Unknown command. Type /mcw help for commands.");
		}
		return false;
	}	
	
	static{
		commandList.put("props", new Commandprops());
		commandList.put("select", new Commandselect());
		commandList.put("create", new Commandselect());
		commandList.put("load", new Commandselect());
		commandList.put("unloadnosave", new Commandunloadnosave());
		commandList.put("playerlist", new Commandplayerlist());
		commandList.put("kickallworld", new Commandkickallworld());
		commandList.put("joinall", new Commandjoinall());
		commandList.put("list", new Commandlist());
		commandList.put("listwarp", new Commandlistwarp());
		commandList.put("setwarp", new Commandsetwarp());
		commandList.put("warp", new Commandwarp());
		commandList.put("unload", new Commandunload());
		commandList.put("tp", new Commandtp());
		commandList.put("help", new Commandhelp());
		commandList.put("createf", new Commandcreatef());
	}
	
}
