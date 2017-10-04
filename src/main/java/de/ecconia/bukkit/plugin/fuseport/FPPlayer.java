package de.ecconia.bukkit.plugin.fuseport;

import org.bukkit.entity.Player;

import de.ecconia.bukkit.plugin.fuseport.modules.FeedbackModul.Feedback;

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
		return plugin.getFeedbackModul().feedbackFromMessage(messageKey, player);
	}
}
