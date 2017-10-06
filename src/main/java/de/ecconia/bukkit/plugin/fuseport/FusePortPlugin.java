package de.ecconia.bukkit.plugin.fuseport;

import org.bukkit.plugin.java.JavaPlugin;

import de.ecconia.bukkit.plugin.fuseport.command.commands.FusePortExec;
import de.ecconia.bukkit.plugin.fuseport.command.commands.TeleportExec;
import de.ecconia.bukkit.plugin.fuseport.parts.PartHolder;

public class FusePortPlugin extends JavaPlugin
{
	private PartHolder partHolder;
	@Override
	public void onEnable()
	{
		partHolder = new PartHolder(this);
		
		new LeaveListener(this);
		
		getCommand("tp").setExecutor(new TeleportExec(this));
		getCommand("fuseport").setExecutor(new FusePortExec(this));
	}
	
	public PartHolder getPartHolder()
	{
		return partHolder;
	}
}
