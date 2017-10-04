package de.ecconia.bukkit.plugin.fuseport.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
		
		SortedCommand sCommand = new SortedCommand(args, allowedFlags, allowShortFlags);
		
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
			List<String> removedFlags = sCommand.getRemovedFlags();
			if(removedFlags.isEmpty())
			{
				return true;
			}
			//TODO: Feedback Framework
			StringBuilder s = new StringBuilder("Not required flags: ");
			for(String flag : removedFlags)
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
		private ArrayList<String> removedFlags;
		
		public SortedCommand(String[] args, Set<String> allowedFlags, boolean allowShortFlags)
		{
			flags = new HashSet<>();
			arguments = new ArrayList<>();
			removedFlags = new ArrayList<>();
			
			Map<String, String> shortAllowedFlags = new HashMap<>();
			if(allowShortFlags)
			{
				for(String flag : allowedFlags)
				{
					shortAllowedFlags.put(flag.substring(0, 1), flag);
				}
			}
			
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
					
					if(allowedFlags.contains(arg))
					{
						flags.add(arg);
						continue;
					}
					if(allowShortFlags && arg.length() == 1 && shortAllowedFlags.containsKey(arg))
					{
						flags.add(shortAllowedFlags.get(arg));
						continue;
					}
					removedFlags.add(arg);
					continue;
				}
				arguments.add(arg);
			}
		}
		
		public List<String> getRemovedFlags()
		{
			return removedFlags;
		}
		
		public boolean isSet(String flag)
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
			
			b.append("Flags: [");
			for(String flag : flags)
			{
				b.append(flag);
				b.append(", ");
			}
			b.append("], ");
			
			b.append("RemovedFlags: [");
			for(String arg : removedFlags)
			{
				b.append(arg);
				b.append(", ");
			}
			b.append("], ");
			
			b.append("Args: [");
			for(String arg : arguments)
			{
				b.append(arg);
				b.append(", ");
			}
			b.append(']');
			
			b.append('}');
			return b.toString();
		}
	}
}