package de.ecconia.bukkit.plugin.fuseport.parts.players;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.ecconia.bukkit.plugin.fuseport.FPPlayer;
import de.ecconia.bukkit.plugin.fuseport.FusePortPlugin;

public class PlayerCache
{
	//TODO: Disconnect Event to clean up the players... And prevent errors
	private Map<String, FPPlayer> players;
	private FusePortPlugin plugin;
	
	public PlayerCache(FusePortPlugin plugin)
	{
		players = new HashMap<>();
		this.plugin = plugin;
	}
	
	public FPPlayer getPlayerFromSender(CommandSender sender)
	{
		String playername = sender.getName();
		
		FPPlayer player = players.get(playername);
		if(player == null)
		{
			player = newPlayer(playername);
		}
		
		return player;
	}
	
	//TODO: add the searching Player for later checks.
	public FPPlayer getPlayerFromName(String playername)
	{
		//TODO: Hide players here.
		//TODO: Autocomplete poor playernames here.
		
		FPPlayer player = players.get(playername);
		if(player == null)
		{
			player = newPlayer(playername);
		}
		
		return player;
	}
	
	private FPPlayer newPlayer(String playername)
	{
		Player mcPlayer = getMCPlayerFromName(playername);
		if(mcPlayer == null)
		{
			return null;
		}
		
		FPPlayer player = new FPPlayer(plugin, mcPlayer);
		players.put(playername, player);
		
		return player;
	}
	
	@SuppressWarnings("deprecation")
	private Player getMCPlayerFromName(String playername)
	{
		return plugin.getServer().getPlayer(playername);
	}
}
