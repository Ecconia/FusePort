package de.ecconia.bukkit.plugin.fuseport.command.commands;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.command.CommandSender;

import de.ecconia.bukkit.plugin.fuseport.command.FPCommand;

public class TeleportExec extends FPCommand
{
	
	public TeleportExec()
	{
		Set<String> allowedFlags = new HashSet<>();
		allowedFlags.add("request");
		allowedFlags.add("direct");
		setAllowedFlags(allowedFlags);
	}
	
	@Override
	protected void onCommand(SortedCommand sCommand, CommandSender sender)
	{
		//TODO: remove after tests
		sender.sendMessage(sCommand.toString());
	}
	
}
