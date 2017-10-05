package de.ecconia.bukkit.plugin.fuseport;

import org.bukkit.entity.Player;

import de.ecconia.bukkit.plugin.fuseport.parts.feedback.FeedbackCreator.Feedback;

public class FPPlayer
{
	private FusePortPlugin plugin;
	private Player player;
	
	public FPPlayer(FusePortPlugin plugin, Player player)
	{
		this.plugin = plugin;
		this.player = player;
	}
	
	public Feedback feedback(String messageKey)
	{
		return plugin.getPartHolder().getFeedbackCreator().feedbackFromMessage(messageKey, player);
	}
	
	public String getName()
	{
		return player.getName();
	}
	
	public FPPlayer getPlayer(String playername)
	{
		FPPlayer playerQuery = plugin.getPartHolder().getPlayerCache().getPlayerFromName(playername);
		
		if(playerQuery == null)
		{
			feedback("feedback.command.parsing.playernotonline").a(playername).send();
		}
		
		return playerQuery;
	}
}
