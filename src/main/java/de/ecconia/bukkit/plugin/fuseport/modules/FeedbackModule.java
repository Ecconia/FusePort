package de.ecconia.bukkit.plugin.fuseport.modules;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class FeedbackModule 
{
	public FeedbackModule()
	{
	}
	
	public Feedback feedbackFromMessage(String messageKey, CommandSender sender)
	{
		Feedback feedback = new Feedback(sender, messageKey);
		return feedback;
	}
	
	public class Feedback
	{
		//TODO: Give real feedback and not some dump
		//TODO: Make Feedback an interface, and put the code here in SimpleFeedback
		private CommandSender sender;
		private String feedback;
		private boolean firstArg;
		
		private Feedback(CommandSender sender, String messageKey)
		{
			firstArg = true;
			this.sender = sender;
			feedback = "[" + ChatColor.GOLD + "FusePort" + ChatColor.WHITE + "] " + ChatColor.GRAY + messageKey + ChatColor.GRAY + "{";
		}
		
		public void send()
		{
			feedback += "}";
			sender.sendMessage(feedback);
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
		
		public Feedback a(String a)
		{
			addSeperator();
			
			feedback += "\"" + ChatColor.GOLD + a + ChatColor.GRAY + "\"";
			
			return this;
		}
		
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

		public Feedback a(int argAmount)
		{
			addSeperator();
			
			feedback += ChatColor.GOLD + String.valueOf(argAmount) + ChatColor.GRAY;
			
			return this;
		}
	}
}
