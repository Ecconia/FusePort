package de.ecconia.bukkit.plugin.fuseport.parts.players;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import de.ecconia.bukkit.plugin.fuseport.FusePortPlugin;
import de.ecconia.bukkit.plugin.fuseport.RequestAnswer;
import de.ecconia.bukkit.plugin.fuseport.parts.feedback.Feedback;

public class FPPlayer
{
	private FusePortPlugin plugin;
	private Player mcPlayer;
	private RequestAnswer defaultRule;
	
	private Map<FPPlayer, RequestAnswer> playerRules = new HashMap<>();
	
	protected FPPlayer(FusePortPlugin plugin, Player mcPlayer)
	{
		this.plugin = plugin;
		this.mcPlayer = mcPlayer;
	}
	
	public Feedback feedback(String messageKey)
	{
		return plugin.getPartHolder().getFeedbackCreator().createFeedback(messageKey, this);
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
			feedback("command.player-not-online").a(playername).send();
		}
		return playerQuery;
	}
	
	public void setPlayerPreference(FPPlayer player, RequestAnswer setting)
	{
		if(setting == null)
		{
			playerRules.remove(player);
		}
		else
		{
			playerRules.put(player, setting);
		}
	}

	public void setDefaultPreference(RequestAnswer prompt)
	{
		defaultRule = prompt;
	}
	
	//TODO: Preferences for other forms of tp (tph tpp tpstp) currently only: tp
	public RequestAnswer tpPreference(FPPlayer player)
	{
		if(playerRules.containsKey(player))
		{
			System.out.println("pRule: " + playerRules.get(player));
			return playerRules.get(player);
		}
		if(defaultRule != null)
		{
			System.out.println("dRule: " + defaultRule);
			return defaultRule;
		}
		
		//TODO: Check for level preference
		//The default here will be the levelDefault.
		System.out.println("noRule: " + RequestAnswer.ACCEPT);
		return RequestAnswer.ACCEPT;
	}
	
	public Player getMCPlayer()
	{
		return mcPlayer;
	}
}
