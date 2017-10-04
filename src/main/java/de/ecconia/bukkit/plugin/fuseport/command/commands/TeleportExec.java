package de.ecconia.bukkit.plugin.fuseport.command.commands;

import java.util.HashSet;
import java.util.Set;

import de.ecconia.bukkit.plugin.fuseport.FPPlayer;
import de.ecconia.bukkit.plugin.fuseport.FusePortPlugin;
import de.ecconia.bukkit.plugin.fuseport.command.FPCommand;

public class TeleportExec extends FPCommand
{
	
	public TeleportExec(FusePortPlugin plugin)
	{
		super(plugin);
		Set<String> allowedFlags = new HashSet<>();
		allowedFlags.add("request");
		allowedFlags.add("direct");
		setAllowedFlags(allowedFlags);
	}
	
	@Override
	protected void onCommand(SortedCommand sCommand, FPPlayer player)
	{
		//TODO: remove after tests
		player.feedback("feedback.command.parsing.sortedcommand").a(sCommand.toString()).send();
		player.feedback("testy.test.test").a("Dog").a("Cat").a("Mouse").send();
	}
}
