package de.ecconia.bukkit.plugin.fuseport.command.commands;

import de.ecconia.bukkit.plugin.fuseport.FusePortPlugin;
import de.ecconia.bukkit.plugin.fuseport.command.FPCommand;
import de.ecconia.bukkit.plugin.fuseport.parts.players.FPPlayer;

public class AcceptExec extends FPCommand
{
	public AcceptExec(FusePortPlugin plugin)
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
			plugin.getPartHolder().getRequestPart().accept(sender, player);
		}
		else
		{
			sender.feedback("feedback.command.accept.parse.wrongArgs").send();
		}
	}

}
