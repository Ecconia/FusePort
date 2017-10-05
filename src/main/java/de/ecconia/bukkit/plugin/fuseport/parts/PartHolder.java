package de.ecconia.bukkit.plugin.fuseport.parts;

import de.ecconia.bukkit.plugin.fuseport.FusePortPlugin;
import de.ecconia.bukkit.plugin.fuseport.parts.feedback.FeedbackCreator;
import de.ecconia.bukkit.plugin.fuseport.parts.players.PlayerCache;
import de.ecconia.bukkit.plugin.fuseport.parts.teleport.Teleporter;

public class PartHolder
{
	private PlayerCache playerCache;
	private FeedbackCreator feedbackCreator;
	private Teleporter teleport;
	
	public PartHolder(FusePortPlugin plugin)
	{
		playerCache = new PlayerCache(plugin);
		feedbackCreator = new FeedbackCreator();
		teleport = new Teleporter();
	}
	
	public FeedbackCreator getFeedbackCreator()
	{
		return feedbackCreator;
	}
	
	public PlayerCache getPlayerCache()
	{
		return playerCache;
	}
	
	public Teleporter getTpCommandHandler()
	{
		return teleport;
	}
}
