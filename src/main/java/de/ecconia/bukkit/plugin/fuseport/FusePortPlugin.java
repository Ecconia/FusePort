package de.ecconia.bukkit.plugin.fuseport;

import org.bukkit.plugin.java.JavaPlugin;

import de.ecconia.bukkit.plugin.fuseport.command.commands.TeleportExec;
import de.ecconia.bukkit.plugin.fuseport.parts.PartHolder;

public class FusePortPlugin extends JavaPlugin
{
	private PartHolder partHolder;
	@Override
	public void onEnable()
	{
		partHolder = new PartHolder(this);
		
		getCommand("tp").setExecutor(new TeleportExec(this));
	}
	
	public PartHolder getPartHolder()
	{
		return partHolder;
	}
}
