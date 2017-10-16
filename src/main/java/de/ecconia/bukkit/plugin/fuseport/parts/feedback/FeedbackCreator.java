package de.ecconia.bukkit.plugin.fuseport.parts.feedback;

import java.io.File;
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

public class FeedbackCreator 
{
	private PluginBase plugin;
	private static final String[] langFiles = {"en"};
	private Map<String, Knot> languages;
	
	public FeedbackCreator(PluginBase plugin)
	{
		this.plugin = plugin;
		saveAllLangFiles();
		loadLangFiles();
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
	
	public Feedback feedbackFromMessage(String messageKey, FPPlayer sender)
	{
		//TODO: Give real feedback and not some dump
		messageKey = languages.get("en").getMessageFromKey(messageKey);
		return new SimpleDebugFeedback(sender, messageKey);
	}
	
	//TODO: extract from this class completly
	//TODO: add extra attributes to the keys, so that one can even more hack them :P
	private class Knot
	{
		private String name;
		private String value;
		private Map<String, Knot> childs = new HashMap<>();
		
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
					childs.put(key, new Knot(key, (MemorySection) obj));
				}
				else if(obj instanceof String)
				{
					if("text".equals(key))
					{
						value = (String) obj;
					}
					else
					{
						childs.put(key, new Knot(key, (String) obj));
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
			if(childs.containsKey(firstKey))
			{
				String ret = childs.get(firstKey).getKey(keys);
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
			Knot[] childValues = childs.values().toArray(new Knot[0]);
			for(int i = 0; i < childs.size(); i++)
			{
				childValues[i].print("", i == childs.size()-1);
			}
		}
		
		private void print(String suffix, boolean last)
		{
			String splitter = last ? "└─" : "├─";
			System.out.println(suffix + splitter + name + ": " + (value == null ? "" : value));
			suffix += last ? "  " : "│ ";
			Knot[] childValues = childs.values().toArray(new Knot[0]);
			for(int i = 0; i < childs.size(); i++)
			{
				childValues[i].print(suffix, i == childs.size()-1);
			}
		}
	}
}
