package de.ecconia.bukkit.plugin.fuseport.parts.feedback;

import java.io.File;
import java.io.InputStreamReader;
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
	private Map<String, TreeElement<String>> languages;
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
			
			TreeElement<String> langTree = new TreeElement<String>(data);
			langTree.print();
			languages.put(lang, langTree);
		}
	}
	
	public Feedback createFeedback(String messageKey, FPPlayer sender)
	{
		//TODO: Give real feedback and not some dump
		messageKey = languages.get("en").getKey(messageKey);
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
}
