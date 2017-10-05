package de.ecconia.bukkit.plugin.fuseport.modules;

import de.ecconia.bukkit.plugin.fuseport.FusePortPlugin;
import de.ecconia.bukkit.plugin.fuseport.modules.feedback.FeedbackModule;
import de.ecconia.bukkit.plugin.fuseport.modules.players.PlayerCache;
import de.ecconia.bukkit.plugin.fuseport.modules.teleport.TPCommandHandler;

public class ModuleHolder
{
	private PlayerCache playerCache;
	private FeedbackModule feedbackModul;
	private TPCommandHandler tpCommandHandler;
	
	public ModuleHolder(FusePortPlugin plugin)
	{
		playerCache = new PlayerCache(plugin);
		feedbackModul = new FeedbackModule();
		tpCommandHandler = new TPCommandHandler();
	}
	
	public FeedbackModule getFeedbackModul()
	{
		return feedbackModul;
	}
	
	public PlayerCache getPlayerCache()
	{
		return playerCache;
	}
	
	public TPCommandHandler getTpCommandHandler()
	{
		return tpCommandHandler;
	}
}
