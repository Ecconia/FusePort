package de.ecconia.bukkit.plugin.fuseport.command.commands;

import de.ecconia.bukkit.plugin.fuseport.FusePortPlugin;
import de.ecconia.bukkit.plugin.fuseport.command.FPCommand;
import de.ecconia.bukkit.plugin.fuseport.parts.players.FPPlayer;

public class DenyExec extends FPCommand
{
	public DenyExec(FusePortPlugin plugin)
	{
		super(plugin);
	}
	
	@Override
	protected void onCommand(SortedCommand sCommand, FPPlayer sender)
	{
		int argAmount = sCommand.argAmount();
		if(argAmount == 1)
		{
			FPPlayer player = sender.getPlayer(sCommand.getArg(0));
			if(player == null)
			{
				return;
			}
			plugin.getPartHolder().getRequestPart().deny(sender, player);
		}
		else
		{
			sender.feedback("feedback.command.deny.parse.wrongArgs").send();
		}
	}

}
