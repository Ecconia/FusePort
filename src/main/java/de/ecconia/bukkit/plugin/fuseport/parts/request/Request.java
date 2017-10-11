package de.ecconia.bukkit.plugin.fuseport.parts.request;

import de.ecconia.bukkit.plugin.fuseport.parts.players.FPPlayer;

public abstract class Request
{
	protected FPPlayer sender;
	protected FPPlayer receiver;
	
	protected Request(FPPlayer sender, FPPlayer receiver)
	{
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
	
	//TODO: Add feedback
	public void abortByTp()
	{
		
	}
	
	//TODO: Add feedback
	public void abortByTph()
	{
		
	}
	
	//TODO: Add feedback
	public void abortBySenderDisconnect()
	{
		
	}
	
	//TODO: Add feedback
	public void abortByReceiverDisconnect()
	{
		
	}

	//TODO: Add feedback
	public void accept() {
		
	}

	//TODO: Add feedback
	public void deny() {
		
	}
}
