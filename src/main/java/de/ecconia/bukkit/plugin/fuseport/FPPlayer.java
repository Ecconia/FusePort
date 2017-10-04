package de.ecconia.bukkit.plugin.fuseport;

import org.bukkit.command.CommandSender;

import de.ecconia.bukkit.plugin.fuseport.modules.FeedbackModul.Feedback;

public class FPPlayer
{
	private FusePortPlugin plugin;
	private CommandSender sender;
	
	public FPPlayer(FusePortPlugin plugin)
	{
		this.plugin = plugin;
	}
	
	public Feedback feedback(String messageKey)
	{
		return plugin.getFeedbackModul().feedbackFromMessage(messageKey, sender);
	}
	
	public void updateCommandSender(CommandSender sender)
	{
		this.sender = sender;
	}
}
