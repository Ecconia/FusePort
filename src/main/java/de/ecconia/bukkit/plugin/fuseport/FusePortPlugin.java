package de.ecconia.bukkit.plugin.fuseport;

import org.bukkit.plugin.java.JavaPlugin;

import de.ecconia.bukkit.plugin.fuseport.command.commands.TeleportExec;
import de.ecconia.bukkit.plugin.fuseport.modules.ModuleHolder;

public class FusePortPlugin extends JavaPlugin
{
	private ModuleHolder modHolder;
	@Override
	public void onEnable()
	{
		modHolder = new ModuleHolder(this);
		
		getCommand("tp").setExecutor(new TeleportExec(this));
	}
	
	public ModuleHolder getModHolder()
	{
		return modHolder;
	}
}
