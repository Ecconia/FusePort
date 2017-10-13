package de.ecconia.bukkit.plugin.fuseport.parts.feedback;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginBase;

import de.ecconia.bukkit.plugin.fuseport.parts.players.FPPlayer;

public class FeedbackCreator 
{
	private PluginBase plugin;
	private static final String[] langFiles = {"en"};
	private Map<String, Object> languages;
	
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
		for(String lang : langFiles)
		{
			FileConfiguration data = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "languages" + File.separator + lang + ".yml"));
			
			Knot langTree = new Knot(data);
			langTree.print();
//			languages.put(lang, langTree);
		}
	}
	
	
	
	public Feedback feedbackFromMessage(String messageKey, FPPlayer sender)
	{
		//TODO: Give real feedback and not some dump
		return new SimpleDebugFeedback(sender, messageKey);
	}
	
	private class Knot
	{
		private Knot parent;
		private String name;
		private String value;
		private Map<String, Knot> childs = new HashMap<>();
		
		public Knot(MemorySection section)
		{
			this(null, "langroot", section);
		}
		
		private Knot(Knot parent, String name, MemorySection section)
		{
			this.parent = parent;
			this.name = name;
			
			Map<String, Object> kids = section.getValues(false);
			
			for(Entry<String, Object> element : kids.entrySet())
			{
				String key = element.getKey();
				Object obj = element.getValue();
				
				if(obj instanceof MemorySection)
				{
					childs.put(key, new Knot(this, key, (MemorySection) obj));
				}
				else if(obj instanceof String)
				{
					if("text".equals(key))
					{
						value = (String) obj;
					}
					else
					{
						childs.put(key, new Knot(this, key, (String) obj));
					}
				}
			}
		}
		
		private Knot(Knot parent, String name, String value)
		{
			this.parent = parent;
			this.name = name;
			this.value = value;
		}
		
		public void print()
		{
			print("", true);
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
