package de.ecconia.bukkit.plugin.fuseport.command.commands;

import de.ecconia.bukkit.plugin.fuseport.FusePortPlugin;
import de.ecconia.bukkit.plugin.fuseport.RequestAnswer;
import de.ecconia.bukkit.plugin.fuseport.command.FPCommand;
import de.ecconia.bukkit.plugin.fuseport.parts.players.FPPlayer;

//TODO: Subcommand framework...
public class FusePortExec extends FPCommand
{
	public FusePortExec(FusePortPlugin plugin)
	{
		super(plugin);
	}

	@Override
	protected void onCommand(SortedCommand sCommand, FPPlayer sender)
	{
		int args = sCommand.argAmount();
		if(args > 1)
		{
			String command = sCommand.getArg(0);
			if("block".equals(command))
			{
				if(args == 2)
				{
					FPPlayer player = sender.getPlayer(sCommand.getArg(1));
					if(player == null)
					{
						return;
					}
					sender.setPlayerPreference(player, RequestAnswer.BLOCK);
				}
				else
				{
					sender.feedback("command.fuseport.block.wrongargamount");
				}
			}
			else if("accept".equals(command))
			{
				if(args == 2)
				{
					FPPlayer player = sender.getPlayer(sCommand.getArg(1));
					if(player == null)
					{
						return;
					}
					sender.setPlayerPreference(player, RequestAnswer.ACCEPT);
				}
				else
				{
					sender.feedback("command.fuseport.accept.wrongargamount");
				}
			}
			else if("remove".equals(command))
			{
				if(args == 2)
				{
					FPPlayer player = sender.getPlayer(sCommand.getArg(1));
					if(player == null)
					{
						return;
					}
					sender.setPlayerPreference(player, null);
				}
				else
				{
					sender.feedback("command.fuseport.remove.wrongargamount");
				}
			}
			else if("promt".equals(command))
			{
				if(args == 2)
				{
					FPPlayer player = sender.getPlayer(sCommand.getArg(1));
					if(player == null)
					{
						return;
					}
					sender.setPlayerPreference(player, RequestAnswer.PROMPT);
				}
				else
				{
					sender.feedback("command.fuseport.promt.wrongargamount");
				}
			}
			else if("default".equals(command))
			{
				if(args == 2)
				{
					String arg = sCommand.getArg(1);
					if("accept".equals(arg))
					{
						sender.setDefaultPreference(RequestAnswer.ACCEPT);
					}
					else if("block".equals(arg))
					{
						sender.setDefaultPreference(RequestAnswer.BLOCK);
					}
					else if("prompt".equals(arg))
					{
						sender.setDefaultPreference(RequestAnswer.PROMPT);
					}
					else if("none".equals(arg))
					{
						sender.setDefaultPreference(null);
					}
					else
					{
						sender.feedback("command.fuseport.default.unknownPreference");
					}
				}
				else
				{
					sender.feedback("command.fuseport.default.wrongargamount");
				}
			}
			else 
			{
				sender.feedback("command.fuseport.parse.subcommand.notfound").send();
			}
		}
		else
		{
			sender.feedback("command.fuseport.parse.noargs").send();
		}
	}
}
