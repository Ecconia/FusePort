package de.ecconia.bukkit.plugin.fuseport;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

public class LeaveListener implements Listener
{
	private FusePortPlugin plugin;
	
	public LeaveListener(FusePortPlugin plugin)
	{
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority=EventPriority.MONITOR)
	public void onPlayerDisconnect(PlayerQuitEvent event)
	{
		String playername = event.getPlayer().getName();
		plugin.getPartHolder().getPlayerCache().disconnect(playername);
	}
}
