package de.ecconia.bukkit.plugin.fuseport.parts.feedback;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.configuration.MemorySection;

//TODO: add extra attributes to the keys, so that one can even more hack them :P
public class TreeElement<T>
{
	private String name;
	private T value;
	private Map<String, TreeElement<T>> childreen = new HashMap<>();
	
	public TreeElement(MemorySection section)
	{
		this("root", section);
	}
	
	@SuppressWarnings({ "unchecked"})
	public TreeElement(String name, MemorySection section)
	{
		this.name = name;
		
		Map<String, Object> kids = section.getValues(false);
		
		for(Entry<String, Object> element : kids.entrySet())
		{
			String key = element.getKey();
			Object obj = element.getValue();
			
			if(obj instanceof MemorySection)
			{
				childreen.put(key, new TreeElement<T>(key, (MemorySection) obj));
			}
			else 
			{
				try
				{
					T castedValue = (T) obj;
					if("data".equals(key))
					{
						value = castedValue;
					}
					else if(value == null)
					{
						value = castedValue;
					}
				}
				catch(ClassCastException e)
				{
					//Ignore its the wrong type
				}
			}
		}
	}
		
	public T getKey(String key)
	{
		//TODO: Either block or ignore wrong usage... Such as keys: "asdf..asdf" or "" <- these just never happen on correct usage hopefully
		//TODO: Investigate LinkedList
		List<String> keys = Arrays.asList(key.split("\\."));
		return getNextKey(keys);
	}
	
	private T getNextKey(List<String> keys)
	{
		if(keys.size() == 0)
		{
			return value;
		}
		
		String firstKey = keys.remove(0);
		if(childreen.containsKey(firstKey))
		{
			T ret = (T) childreen.get(firstKey).getNextKey(keys);
			if(ret != null)
			{
				return ret;
			}
		}
		
		return value;
	}

	public void print()
	{	
		System.out.println("[FusePort] TreeElement-Print:");
		printValue(value, "", name + ": ");
		
		@SuppressWarnings("unchecked")
		TreeElement<T>[] childValues = childreen.values().toArray(new TreeElement[0]);
		for(int i = 0; i < childreen.size(); i++)
		{
			childValues[i].print("", i == childreen.size()-1);
		}
	}
	
	private void print(String prefix, boolean last)
	{
		String splitter = last ? "└─" : "├─";
		printValue(value, prefix, splitter + name + ": ");
		prefix += last ? "  " : "│ ";
		
		@SuppressWarnings("unchecked")
		TreeElement<T>[] childValues = childreen.values().toArray(new TreeElement[0]);
		for(int i = 0; i < childreen.size(); i++)
		{
			childValues[i].print(prefix, i == childreen.size()-1);
		}
	}
	
	private void printValue(Object value, String prefix, String formattedName)
	{
		if(value == null)
		{
			System.out.println(prefix + formattedName);
		}
		else
		{
			if(value instanceof List)
			{
				@SuppressWarnings("unchecked")
				List<String> values = (List<String>) value;
				System.out.println(prefix + formattedName);
				for(String entry : values)
				{
					System.out.println(prefix + "   -" + entry);
				}
			}
			else if(value instanceof String)
			{
				System.out.println(prefix + formattedName + (String) value);
			}
			else
			{
				System.out.println("[FusePort] _Print_Tree_ Unsupported object type.");
			}
		}
	}
	
}
