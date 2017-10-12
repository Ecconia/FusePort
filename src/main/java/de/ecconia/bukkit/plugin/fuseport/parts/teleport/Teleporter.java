package de.ecconia.bukkit.plugin.fuseport.parts.teleport;

import de.ecconia.bukkit.plugin.fuseport.parts.players.FPPlayer;

public class Teleporter
{
	public Teleporter() {}
	
	public void teleportSenderToPlayer(FPPlayer sender, FPPlayer player)
	{
		//TODO: Save Location to history
		teleportFPPlayerToFPPlayer(sender, player);
	}
	
	public void teleportPlayerToSender(FPPlayer player, FPPlayer sender)
	{
		//TODO: Save Location to history
		teleportFPPlayerToFPPlayer(player, sender);
	}
	
	private void teleportFPPlayerToFPPlayer(FPPlayer object, FPPlayer target)
	{
		object.getMCPlayer().teleport(target.getMCPlayer());
	}
}
