package de.ecconia.bukkit.plugin.fuseport.parts.request;

import de.ecconia.bukkit.plugin.fuseport.parts.PartHolder;
import de.ecconia.bukkit.plugin.fuseport.parts.players.FPPlayer;

public abstract class Request
{
	protected PartHolder parts;
	protected FPPlayer sender;
	protected FPPlayer receiver;
	
	protected Request(PartHolder parts, FPPlayer sender, FPPlayer receiver)
	{
		this.parts = parts;
		this.sender = sender;
		this.receiver = receiver;
	}
	
	public FPPlayer getSender()
	{
		return sender;
	}
	
	public FPPlayer getReceiver()
	{
		return receiver;
	}
	
	public abstract void abortByTp();
	
	public abstract void abortByTph();
	
	public abstract void abortBySenderDisconnect();
	
	public abstract void abortByReceiverDisconnect();

	public abstract void accept();

	public abstract void deny();
}
