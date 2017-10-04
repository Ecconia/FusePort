package de.ecconia.bukkit.plugin.fuseport.modules;

import java.util.List;

import org.bukkit.command.CommandSender;

public class FeedbackModul 
{
	public FeedbackModul()
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
		private CommandSender sender;
		private String feedback;
		private boolean firstArg;
		
		private Feedback(CommandSender sender, String messageKey)
		{
			firstArg = true;
			this.sender = sender;
			feedback = "[FusePort] " + messageKey + "{";
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
			
			feedback += "\"" + a + "\"";
			
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
					feedback += list.get(i);
				}
			}
			
			feedback += "]";
			
			return this;
		}

		public void send()
		{
			feedback += "}";
			sender.sendMessage(feedback);
		}
	}
}
