package de.ecconia.bukkit.plugin.fuseport.parts;

import de.ecconia.bukkit.plugin.fuseport.FusePortPlugin;
import de.ecconia.bukkit.plugin.fuseport.parts.feedback.FeedbackCreator;
import de.ecconia.bukkit.plugin.fuseport.parts.players.PlayerCache;
import de.ecconia.bukkit.plugin.fuseport.parts.request.RequestPart;
import de.ecconia.bukkit.plugin.fuseport.parts.teleport.Teleporter;

public class PartHolder
{
	private PlayerCache playerCache;
	private FeedbackCreator feedbackCreator;
	private Teleporter teleport;
	private RequestPart requestPart;
	
	public PartHolder(FusePortPlugin plugin)
	{
		playerCache = new PlayerCache(plugin);
		feedbackCreator = new FeedbackCreator();
		teleport = new Teleporter();
		requestPart = new RequestPart();
	}
	
	public FeedbackCreator getFeedbackCreator()
	{
		return feedbackCreator;
	}
	
	public PlayerCache getPlayerCache()
	{
		return playerCache;
	}
	
	public Teleporter getTeleporter()
	{
		return teleport;
	}
	
	public RequestPart getRequestPart()
	{
		return requestPart;
	}
}
