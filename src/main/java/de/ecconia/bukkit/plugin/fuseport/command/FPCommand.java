package de.ecconia.bukkit.plugin.fuseport.command;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract class FPCommand implements CommandExecutor
{
	private Set<String> allowedFlags;
	private boolean allowShortFlags;
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		//TODO: Remove: Test for Feedback framwork
		sender.sendMessage(sender.getName());
		
		SortedCommand sCommand = new SortedCommand(args);
		
		if(checkFlags(sCommand, sender))
		{
			onCommand(sCommand, sender);			
		}
		
		return true;
	}
	
	private boolean checkFlags(SortedCommand sCommand, CommandSender sender)
	{
		if(allowedFlags != null)
		{
			List<String> uselessFlags = sCommand.getUselessFlags(allowedFlags, allowShortFlags);
			if(uselessFlags.isEmpty())
			{
				return true;
			}
			//TODO: Feedback Framework
			StringBuilder s = new StringBuilder("UselessFlags: ");
			for(String flag : uselessFlags)
			{
				s.append(flag);
				s.append(", ");
			}
			sender.sendMessage(s.toString());
			return false;
		}
		return true;
	}
	
	protected abstract void onCommand(SortedCommand sCommand, CommandSender sender);
	
	protected void setAllowedFlags(Set<String> allowedFlags)
	{
		setAllowedFlags(allowedFlags, true);
	}
	
	protected void setAllowedFlags(Set<String> allowedFlags, boolean allowShortFlags)
	{
		this.allowedFlags = allowedFlags;
		this.allowShortFlags = allowShortFlags;
	}
	
	protected class SortedCommand
	{
		private Set<String> flags;
		private List<String> arguments;
		
		public SortedCommand(String[] args)
		{
			flags = new HashSet<>();
			arguments = new ArrayList<>();
			
			for(String arg : args)
			{
				if (arg.startsWith("-"))
				{
					arg = arg.substring(1);
					if (arg.startsWith("-"))
					{
						arg = arg.substring(1);
					}
					if(arg.equals(""))
					{
						arg = "-";
					}
					flags.add(arg);
					continue;
				}
				arguments.add(arg);
			}
		}
		
		public List<String> getUselessFlags(Set<String> allowedFlags, boolean allowShortFlags)
		{
			Set<Character> shortAllowedFlags = new HashSet<>();
			if(allowShortFlags)
			{
				for(String flag : allowedFlags)
				{
					shortAllowedFlags.add(flag.charAt(0));
				}
			}
			
			List<String> uselessFlags = new ArrayList<>();
			for(String flag : flags)
			{
				if(!(allowedFlags.contains(flag) || (allowShortFlags && flag.length() == 1 && shortAllowedFlags.contains(flag.charAt(0)))))
				{
					uselessFlags.add(flag);
				}
			}
			
			return uselessFlags;
		}
		
		public boolean isSet(String flag)
		{
			return isSet(flag, true);
		}
		
		public boolean isSet(String flag, boolean firstCharCheck)
		{
			return flags.contains(flag);
		}
		
		public int argAmount()
		{
			return arguments.size();
		}
		
		public String[] getArgs()
		{
			return arguments.toArray(new String[0]);
		}
		
		public String getArg(int index)
		{
			return arguments.get(index);
		}
		
		@Override
		public String toString() {
			StringBuilder b = new StringBuilder(getClass().getSimpleName());
			b.append('{');
			
			for(String flag : flags)
			{
				b.append(flag);
				b.append(", ");
			}
			
			b.append('|');
			
			for(String arg : arguments)
			{
				b.append(arg);
				b.append(", ");
			}
			
			b.append('}');
			return b.toString();
		}
	}
}
