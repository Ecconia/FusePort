package de.ecconia.bukkit.plugin.fuseport.modules;

import de.ecconia.bukkit.plugin.fuseport.FusePortPlugin;
import de.ecconia.bukkit.plugin.fuseport.modules.feedback.FeedbackModule;
import de.ecconia.bukkit.plugin.fuseport.modules.players.PlayerCache;
import de.ecconia.bukkit.plugin.fuseport.modules.teleport.Teleporter;

public class ModuleHolder
{
	private PlayerCache playerCache;
	private FeedbackModule feedbackModul;
	private Teleporter teleport;
	
	public ModuleHolder(FusePortPlugin plugin)
	{
		playerCache = new PlayerCache(plugin);
		feedbackModul = new FeedbackModule();
		teleport = new Teleporter();
	}
	
	public FeedbackModule getFeedbackModul()
	{
		return feedbackModul;
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
