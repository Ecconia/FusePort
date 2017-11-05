package de.ecconia.bukkit.plugin.fuseport.parts.feedback;

import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginBase;

import de.ecconia.bukkit.plugin.fuseport.parts.players.FPPlayer;

//TODO: Clean this mess up (sort classes)
public class FeedbackCreator 
{
	private PluginBase plugin;
	private static final String[] langFiles = {"en"};
	private Map<String, Knot> languages;
	private ArgsKnot arguments;
	
	public FeedbackCreator(PluginBase plugin)
	{
		this.plugin = plugin;
		loadMessageArguments();
		saveAllLangFiles();
		loadLangFiles();
	}
	
	private void loadMessageArguments()
	{
		FileConfiguration argFile = YamlConfiguration.loadConfiguration(new InputStreamReader(plugin.getResource("arguments.yml")));
		arguments = new ArgsKnot(argFile);
		arguments.print();
	}
	
	private void saveAllLangFiles()
	{
		for(String lang : langFiles)
		{
			String path = "languages" + File.separator + lang + ".yml";
			if(!(new File(plugin.getDataFolder(), path)).exists())
			{
				plugin.saveResource(path, false);
			}
		}
	}
	
	private void loadLangFiles()
	{
		languages = new HashMap<>();
		for(String lang : langFiles)
		{
			FileConfiguration data = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "languages" + File.separator + lang + ".yml"));
			
			Knot langTree = new Knot(data);
			langTree.print();
			languages.put(lang, langTree);
		}
	}
	
	public Feedback createFeedback(String messageKey, FPPlayer sender)
	{
		//TODO: Give real feedback and not some dump
		messageKey = languages.get("en").getMessageFromKey(messageKey);
		return new SimpleDebugFeedback(sender, messageKey);
	}
	
	private class ArgsKnot
	{
		private final String name;
		private List<String> argumentList;
		private final Map<String, ArgsKnot> childreen = new HashMap<>();
		
		public ArgsKnot(MemorySection section)
		{
			this("root", section);
		}
		
		@SuppressWarnings("unchecked")
		public ArgsKnot(String name, MemorySection section)
		{
			this.name = name;
			
			Map<String, Object> kids = section.getValues(false);
			
			for(Entry<String, Object> element : kids.entrySet())
			{
				String key = element.getKey();
				Object obj = element.getValue();
				
				if(obj instanceof MemorySection)
				{
					childreen.put(key, new ArgsKnot((MemorySection) obj));
				}
				else if(obj instanceof List)
				{
					if("args".equals(key))
					{
						argumentList = (List<String>) obj;
					}
				}
			}
		}
		
		public void print()
		{
			System.out.println(name + ":");
			if(argumentList != null)
			{
				for(String arg : argumentList)
				{
					System.out.println("- " + arg);
				}
			}
			//TODO: Get rid of this!
			@SuppressWarnings("unchecked")
			Entry<String, ArgsKnot>[] childreenArray = childreen.entrySet().toArray(new Entry[0]);
			for(int i = 0; i < childreenArray.length; i++)
			{
				childreenArray[i].getValue().print(childreenArray[i].getKey(), "", i == childreen.size()-1);
			}
		}
		
		private void print(String name, String prefix, boolean last)
		{
			String splitter = last ? "└─" : "├─";
			System.out.println(prefix + splitter + name + ":");
			if(argumentList != null)
			{
				for(String arg : argumentList)
				{
					System.out.println(prefix + " - " + arg);
				}
			}
			prefix += last ? "  " : "│ ";
			//TODO: Get rid of this!
			@SuppressWarnings("unchecked")
			Entry<String, ArgsKnot>[] childreenArray = childreen.entrySet().toArray(new Entry[0]);
			for(int i = 0; i < childreenArray.length; i++)
			{
				childreenArray[i].getValue().print(childreenArray[i].getKey(), prefix, i == childreen.size()-1);
			}
		}
	}
	
	//TODO: extract from this class completly
	//TODO: add extra attributes to the keys, so that one can even more hack them :P
	private class Knot
	{
		private String name;
		private String value;
		private Map<String, Knot> childreen = new HashMap<>();
		
		public Knot(MemorySection section)
		{
			this("root", section);
		}
		
		private Knot(String name, MemorySection section)
		{
			this.name = name;
			
			Map<String, Object> kids = section.getValues(false);
			
			for(Entry<String, Object> element : kids.entrySet())
			{
				String key = element.getKey();
				Object obj = element.getValue();
				
				if(obj instanceof MemorySection)
				{
					childreen.put(key, new Knot(key, (MemorySection) obj));
				}
				else if(obj instanceof String)
				{
					if("text".equals(key))
					{
						value = (String) obj;
					}
					else
					{
						childreen.put(key, new Knot(key, (String) obj));
					}
				}
			}
		}
		
		private Knot(String name, String value)
		{
			this.name = name;
			this.value = value;
		}
		
		public String getMessageFromKey(String key)
		{
			//TODO: Either block or ignore wrong usage... Such as keys: "asdf..asdf" or "" <- these just never happen on correct usage hopefully
			//TODO: Investigate LinkedList
			List<String> keys = new ArrayList<>(Arrays.asList(key.split("\\.")));
			String ret = getKey(keys);
			if(ret == null)
			{
				return key;
			}
			return ret;
		}
		
		private String getKey(List<String> keys)
		{
			if(keys.size() == 0)
			{
				return value;
			}
			
			String firstKey = keys.remove(0);
			if(childreen.containsKey(firstKey))
			{
				String ret = childreen.get(firstKey).getKey(keys);
				if(ret == null)
				{
					return value;
				}
				return ret;
			}
			else
			{
				return value;
			}
		}
		
		public void print()
		{
			System.out.println(name + ": " + (value == null ? "" : value));
			Knot[] childValues = childreen.values().toArray(new Knot[0]);
			for(int i = 0; i < childreen.size(); i++)
			{
				childValues[i].print("", i == childreen.size()-1);
			}
		}
		
		private void print(String prefix, boolean last)
		{
			String splitter = last ? "└─" : "├─";
			System.out.println(prefix + splitter + name + ": " + (value == null ? "" : value));
			prefix += last ? "  " : "│ ";
			Knot[] childValues = childreen.values().toArray(new Knot[0]);
			for(int i = 0; i < childreen.size(); i++)
			{
				childValues[i].print(prefix, i == childreen.size()-1);
			}
		}
	}
}
