package de.ecconia.bukkit.plugin.fuseport.parts.feedback;

import java.util.List;

import de.ecconia.bukkit.plugin.fuseport.parts.players.FPPlayer;

public abstract class Feedback
{
	protected FPPlayer sender;
	
	//TODO: Change FPPalyer back to Player or CommandSender?
	//Maybe since then the feedback system can be used elsewhere
	public Feedback(FPPlayer sender, String messageKey)
	{
		this.sender = sender;
	}
	
	public abstract Feedback a(String text);

	public abstract Feedback a(FPPlayer player);

	public abstract Feedback a(List<String> list);
	
	public abstract Feedback a(int value);
	
	public abstract void send();
}
