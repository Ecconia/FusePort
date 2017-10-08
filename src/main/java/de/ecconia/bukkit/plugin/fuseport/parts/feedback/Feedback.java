package de.ecconia.bukkit.plugin.fuseport.parts.feedback;

import java.util.List;

import de.ecconia.bukkit.plugin.fuseport.parts.players.FPPlayer;

public abstract class Feedback
{
	protected FPPlayer sender;
	
	public Feedback(FPPlayer sender, String messageKey)
	{
		this.sender = sender;
	}
	
	public abstract Feedback a(String text);
	
	public abstract Feedback a(List<String> list);
	
	public abstract Feedback a(int value);
	
	public abstract void send();
}
