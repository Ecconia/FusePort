package de.ecconia.bukkit.plugin.fuseport;

import org.bukkit.plugin.java.JavaPlugin;

import de.ecconia.bukkit.plugin.fuseport.command.commands.TeleportExec;
import de.ecconia.bukkit.plugin.fuseport.modules.FeedbackModule;
import de.ecconia.bukkit.plugin.fuseport.modules.PlayerCache;

public class FusePortPlugin extends JavaPlugin
{
	private PlayerCache playerCache;
	private FeedbackModule feedbackModul;
	
	@Override
	public void onEnable()
	{
		playerCache = new PlayerCache(this);
		feedbackModul = new FeedbackModule();
		
		getCommand("tp").setExecutor(new TeleportExec(this));
	}
	
	public PlayerCache getPlayerCache()
	{
		return playerCache;
	}
	
	public FeedbackModule getFeedbackModul()
	{
		return feedbackModul;
	}
}
