package de.ecconia.bukkit.plugin.fuseport.parts.feedback;

import java.util.List;

import org.bukkit.ChatColor;

import de.ecconia.bukkit.plugin.fuseport.parts.players.FPPlayer;

public class SimpleDebugFeedback extends Feedback
{
	private String feedback;
	private boolean firstArg;
	
	public SimpleDebugFeedback(FPPlayer sender, String messageKey)
	{
		super(sender, messageKey);
		firstArg = true;
		feedback = "[" + ChatColor.GOLD + "FusePort" + ChatColor.WHITE + "] " + ChatColor.GRAY + messageKey + ChatColor.GRAY + "{";
	}
	
	@Override
	public void send()
	{
		feedback += "}";
		sender.getMCPlayer().sendMessage(feedback);
	}

	private void addSeperator()
	{
		if(!firstArg)
		{
			feedback += ", ";
		}
		else
		{
			firstArg = false;
		}
	}
	
	@Override
	public Feedback a(String a)
	{
		addSeperator();
		
		feedback += "\"" + ChatColor.GOLD + a + ChatColor.GRAY + "\"";
		
		return this;
	}
	
	@Override
	public Feedback a(List<String> list)
	{
		addSeperator();
		
		feedback += "[";
		
		if(list.size() > 0)
		{
			feedback += list.get(0);
			for(int i = 1; i < list.size(); i++)
			{
				addSeperator();
				feedback += ChatColor.GOLD + list.get(i) + ChatColor.GRAY;
			}
		}
		
		feedback += "]";
		
		return this;
	}

	@Override
	public Feedback a(int argAmount)
	{
		addSeperator();
		
		feedback += ChatColor.GOLD + String.valueOf(argAmount) + ChatColor.GRAY;
		
		return this;
	}
}
