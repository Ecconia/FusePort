package de.ecconia.bukkit.plugin.fuseport;

import org.bukkit.plugin.java.JavaPlugin;

import de.ecconia.bukkit.plugin.fuseport.command.commands.TeleportExec;

public class FusePortPlugin extends JavaPlugin
{	
	@Override
	public void onEnable()
	{
		getCommand("tp").setExecutor(new TeleportExec());
	}
	
}
