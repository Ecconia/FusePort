package de.ecconia.bukkit.plugin.fuseport.command.commands;

import java.util.HashSet;
import java.util.Set;

import de.ecconia.bukkit.plugin.fuseport.FPPlayer;
import de.ecconia.bukkit.plugin.fuseport.FusePortPlugin;
import de.ecconia.bukkit.plugin.fuseport.RequestAnswer;
import de.ecconia.bukkit.plugin.fuseport.command.FPCommand;

public class TeleportExec extends FPCommand
{
	private static final String FLAG_DIRECT = "direct";
	private static final String FLAG_REQUEST = "request";
	
	public TeleportExec(FusePortPlugin plugin)
	{
		super(plugin);
		Set<String> allowedFlags = new HashSet<>();
		allowedFlags.add(FLAG_REQUEST);
		allowedFlags.add(FLAG_DIRECT);
		setAllowedFlags(allowedFlags);
	}
	
	@Override
	protected void onCommand(SortedCommand sCommand, FPPlayer sender)
	{
		//TODO: remove after tests
		sender.feedback("feedback.command.parsing.sortedCommand").a(sCommand.toString()).send();
	
		if(sCommand.isSet(FLAG_REQUEST) && sCommand.isSet(FLAG_DIRECT))
		{
			sender.feedback("feedback.command.parse.tp.cannotCombineFlags").a(FLAG_DIRECT).a(FLAG_REQUEST).send();
			return;
		}
		
		//TODO: Extract the actions to methods, since others want to access them to.
		int argAmount = sCommand.argAmount();
		if(argAmount == 1)
		{
			//TODO: Check permissions
			FPPlayer playerTpTo = sender.getPlayer(sCommand.getArg(0));
			if(playerTpTo == null)
			{
				return;
			}
			
			RequestAnswer preferredAnswer;
			//TODO: Bypass permission
			//preferredAnswer = false ? RequestAnswer.ACCEPT : playerTpTo.tpPreference(sender);
			preferredAnswer = playerTpTo.tpPreference(sender);
			
			if(preferredAnswer == RequestAnswer.ACCEPT)
			{
				//Wuuuhiii TP me!
				plugin.getPartHolder().getTeleporter().teleportSenderToPlayer(sender, playerTpTo);
				sender.feedback("feedback.command.exec.meToOther.accept").a(sender.getName()).a(playerTpTo.getName()).send();
			}
			else if(preferredAnswer == RequestAnswer.PROMPT)
			{
				//Derp implement Requests!
				sender.feedback("feedback.command.exec.meToOther.promt.implementation404").a(sender.getName()).a(playerTpTo.getName()).send();
				sender.feedback("feedback.command.exec.meToOther.promt.send").a(sender.getName()).a(playerTpTo.getName()).send();
			}
			else if(preferredAnswer == RequestAnswer.BLOCK)
			{
				//No go home, you cannot to there.
				sender.feedback("feedback.command.exec.meToOther.deny").a(sender.getName()).a(playerTpTo.getName()).send();
			}
			else
			{
				//Pretend request
				sender.feedback("feedback.command.exec.meToOther.promt.implementation404").a(sender.getName()).a(playerTpTo.getName()).send();
				sender.feedback("feedback.command.exec.meToOther.promt.sendfake").a(sender.getName()).a(playerTpTo.getName()).send();
			}
		}
		else if(argAmount == 2)
		{
			//TODO: Check permissions
			FPPlayer playerToTp = sender.getPlayer(sCommand.getArg(0));
			FPPlayer playerTpTo = sender.getPlayer(sCommand.getArg(1));
			if(playerToTp == null || playerTpTo == null)
			{
				return;
			}
			//TODO: Check Request default setting
			//TODO: Check if one person is yourself, redirect to /tp or /tph.
			sender.feedback("feedback.command.exec.otherToOther").a(playerToTp.getName()).a(playerTpTo.getName()).send();
		}
		else
		{
			sender.feedback("feedback.command.exec.tp.help").a(argAmount).send();
		}
	}
}