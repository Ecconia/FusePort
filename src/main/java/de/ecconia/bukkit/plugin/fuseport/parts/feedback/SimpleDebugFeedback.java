package de.ecconia.bukkit.plugin.fuseport.parts.feedback;

import java.util.List;

import org.bukkit.ChatColor;

import de.ecconia.bukkit.plugin.fuseport.parts.players.FPPlayer;

public class SimpleDebugFeedback extends Feedback
{
	private static final ChatColor HC = ChatColor.GOLD; //Highlight color
	private static final ChatColor TC = ChatColor.GRAY; //Text color 
	
	private String feedback;
	private boolean firstArg;
	
	public SimpleDebugFeedback(FPPlayer sender, String messageKey)
	{
		super(sender, messageKey);
		firstArg = true;
		feedback = "[" + HC + "FusePort" + ChatColor.WHITE + "] " + TC + messageKey + " {";
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
	public Feedback a(String text)
	{
		addSeperator();
		
		feedback += "\"" + HC + text + TC + "\"";
		
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
				feedback += HC + list.get(i) + TC;
			}
		}
		
		feedback += "]";
		
		return this;
	}

	@Override
	public Feedback a(int number)
	{
		addSeperator();
		
		feedback += HC + String.valueOf(number) + TC;
		
		return this;
	}
	
	@Override
	public Feedback a(FPPlayer player)
	{
		addSeperator();
		
		feedback += HC + player.getName() + TC;
		
		return this;
	}
}
