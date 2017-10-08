package de.ecconia.bukkit.plugin.fuseport.parts.feedback;

import de.ecconia.bukkit.plugin.fuseport.parts.players.FPPlayer;

public class FeedbackCreator 
{
	public Feedback feedbackFromMessage(String messageKey, FPPlayer sender)
	{
		//TODO: Give real feedback and not some dump
		return new SimpleDebugFeedback(sender, messageKey);
	}
}
