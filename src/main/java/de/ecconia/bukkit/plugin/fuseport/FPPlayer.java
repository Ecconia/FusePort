package de.ecconia.bukkit.plugin.fuseport;

import org.bukkit.entity.Player;

import de.ecconia.bukkit.plugin.fuseport.parts.feedback.FeedbackCreator.Feedback;

public class FPPlayer
{
	private FusePortPlugin plugin;
	private Player mcPlayer;
	
	public FPPlayer(FusePortPlugin plugin, Player mcPlayer)
	{
		this.plugin = plugin;
		this.mcPlayer = mcPlayer;
	}
	
	public Feedback feedback(String messageKey)
	{
		return plugin.getPartHolder().getFeedbackCreator().feedbackFromMessage(messageKey, mcPlayer);
	}
	
	public String getName()
	{
		return mcPlayer.getName();
	}
	
	public FPPlayer getPlayer(String playername)
	{
		FPPlayer playerQuery = plugin.getPartHolder().getPlayerCache().getPlayerFromName(playername);
		
		//TODO: Maybe move this to the others? Out of player? (Its nice here thou)
		if(playerQuery == null)
		{
			feedback("feedback.command.parsing.playernotonline").a(playername).send();
		}
		
		return playerQuery;
	}
	
	//TODO: Preferences for other forms of tp (tph tpp tpstp) currently only: tp
	public RequestAnswer tpPreference(FPPlayer player)
	{
		//TODO: Check if personal preference
		//TODO: Check for level preference
		return RequestAnswer.ACCEPT;
	}
	
	public Player getMCPlayer()
	{
		return mcPlayer;
	}
}
