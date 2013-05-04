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

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.TravelAgent;
import org.bukkit.World.Environment;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityCreatePortalEvent;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

public class NewMCWorldListener extends MCWorldListener {

    public NewMCWorldListener(MCWorld var1) {
	super(var1);
	// TODO Auto-generated constructor stub
    }

    public static Map<String, IMCWorldCommand> commandList = new HashMap<String, IMCWorldCommand>();

    @EventHandler
    public void onPlayerJoinGame(PlayerJoinEvent e) {
	e.getPlayer().sendMessage(
		"[MCWorld] You are currently in World: "
			+ e.getPlayer().getWorld().getName());
    }

    @EventHandler
    public void onPlayerCreatePortal(EntityCreatePortalEvent e) {
    }

    @EventHandler
    public void onPlayerEnterPortal(EntityPortalEnterEvent e){
    }
    
    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent e){
	TravelAgent ta = e.getPortalTravelAgent();
	 e.useTravelAgent(true);
	ta.setCanCreatePortal(true);
	if (e.getPlayer().getWorld().getEnvironment() == Environment.NORMAL) {
	    if(e.getCause()== TeleportCause.END_PORTAL){
		    Location tploc = new Location
			    (Bukkit.getWorld(e.getPlayer().getWorld().getName() + "_the_end")
			    , 0
			    , 72
			    , 0);
		    e.setTo(tploc);
	    }else if(e.getCause() == TeleportCause.NETHER_PORTAL){
		    Location tploc = new Location
			    (Bukkit.getWorld(e.getPlayer().getWorld().getName() + "_nether")
			    , e.getPlayer().getLocation().getX()/8
			    , e.getPlayer().getLocation().getY()/8
			    , e.getPlayer().getLocation().getZ()/8);
		    ta.findOrCreate(tploc);
		    e.setTo(ta.findOrCreate(tploc));
	    }
	}
	else if(e.getPlayer().getWorld().getEnvironment() == Environment.NETHER){
	    Location tploc = new Location(Bukkit.getWorld(e.getPlayer().getWorld().getName().substring(0, e.getPlayer().getWorld().getName().length() - 7))
		    , e.getPlayer().getLocation().getX()*8
		    , e.getPlayer().getLocation().getY()*8
		    , e.getPlayer().getLocation().getZ()*8);
	   ta.findOrCreate(tploc);
	    e.setTo(ta.findOrCreate(tploc));
	}
	else if(e.getPlayer().getWorld().getEnvironment() == Environment.THE_END){
	   Location tploc = (Bukkit.getWorld(e.getPlayer().getWorld().getName().substring(0, e.getPlayer().getWorld().getName().length() - 8)).getSpawnLocation());
	   e.setTo(tploc);
	   e.useTravelAgent(false);
	}

	e.setPortalTravelAgent(ta);
    }
    
    @EventHandler
    public void onEntityPortal(EntityPortalEvent e) {
	TravelAgent ta = e.getPortalTravelAgent();
	ta.setCanCreatePortal(true);
	if (e.getEntity().getWorld().getEnvironment() == Environment.NORMAL) {
	    Location tploc = new Location(Bukkit.getWorld(e.getEntity().getWorld().getName() + "_nether"), e.getTo().getX(), e.getTo().getY(), e.getTo().getZ());
	    ta.findOrCreate(tploc);
	    e.setTo(ta.findOrCreate(tploc));
	    e.getEntity().teleport(tploc);
	}
	else if(e.getEntity().getWorld().getEnvironment() == Environment.NETHER){
	    Location tploc = new Location(Bukkit.getWorld(e.getEntity().getWorld().getName().substring(0, e.getEntity().getWorld().getName().length() - 7)), e.getTo().getX(), e.getTo().getY(), e.getTo().getZ());
	    ta.findOrCreate(tploc);
	    e.setTo(ta.findOrCreate(tploc));
	}

	e.setPortalTravelAgent(ta);
    }

    @Override
    public boolean onCommand(CommandSender sender, String label, String[] args,
	    Command cmd) {
	mcworld.log.info("[MCWorld] " + sender.getName()
		+ " just used the command: /mcw " + label);
	if (commandList.containsKey(label)) {
	    boolean b = commandList.get(label).executeCMD(sender, label, args);
	    if (!b) {
		sender.sendMessage("Unknown command. Type /mcw help for commands.");
	    }
	    return true;
	} else {
	    sender.sendMessage("Unknown command. Type /mcw help for commands.");
	}
	return false;
    }

    static {
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
