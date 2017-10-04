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
		player.feedback("feedback.command.parsing.sortedCommand").a(sCommand.toString()).send();
	
		//TODO: Extract the actions to methods, since others want to access them to.
		int argAmount = sCommand.argAmount();
		if(argAmount == 1)
		{
			FPPlayer playerTpTo = player.getPlayer(sCommand.getArg(0));
			if(playerTpTo == null)
			{
				return;
			}
			player.feedback("feedback.command.exec.meToOther").a(player.getName()).a(playerTpTo.getName()).send();
		}
		else if(argAmount == 2)
		{
			FPPlayer playerToTp = player.getPlayer(sCommand.getArg(0));
			FPPlayer playerTpTo = player.getPlayer(sCommand.getArg(1));
			if(playerToTp == null || playerTpTo == null)
			{
				return;
			}
			//TODO: Check if one person is yourself, redirect to /tp or /tph.
			player.feedback("feedback.command.exec.otherToOther").a(playerToTp.getName()).a(playerTpTo.getName()).send();
		}
		else
		{
			player.feedback("feedback.command.exec.tp.help").a(argAmount).send();
		}
	}
}