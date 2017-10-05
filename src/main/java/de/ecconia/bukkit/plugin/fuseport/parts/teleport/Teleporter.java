package de.ecconia.bukkit.plugin.fuseport.parts.teleport;

import de.ecconia.bukkit.plugin.fuseport.FPPlayer;

public class Teleporter
{
	public Teleporter() {}
	
	public void teleportSenderToPlayer(FPPlayer sender, FPPlayer player)
	{
		//TODO: Save Location to history
		teleportFPPlayerToFPPlayer(sender, player);
	}
	
	private void teleportFPPlayerToFPPlayer(FPPlayer object, FPPlayer target)
	{
		object.getMCPlayer().teleport(target.getMCPlayer());
	}
}
