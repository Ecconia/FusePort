package de.ecconia.bukkit.plugin.fuseport.modules;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.CommandSender;

import de.ecconia.bukkit.plugin.fuseport.FPPlayer;
import de.ecconia.bukkit.plugin.fuseport.FusePortPlugin;

public class PlayerCache
{
	private Map<String, FPPlayer> players;
	private FusePortPlugin plugin;
	
	public PlayerCache(FusePortPlugin plugin)
	{
		players = new HashMap<>();
		this.plugin = plugin;
	}
	
	public FPPlayer getPlayerFromSender(CommandSender sender)
	{
		String playerName = sender.getName();
		FPPlayer player = players.get(playerName);
		
		if(player == null)
		{
			player = newPlayer(playerName);
		}
		
		player.updateCommandSender(sender);
		
		return player;
	}
	
	private FPPlayer newPlayer(String playerName)
	{
		FPPlayer player = new FPPlayer(plugin);
		players.put(playerName, player);
		return player;
	}
}
