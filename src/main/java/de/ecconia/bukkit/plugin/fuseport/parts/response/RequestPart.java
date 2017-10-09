package de.ecconia.bukkit.plugin.fuseport.parts.response;

import de.ecconia.bukkit.plugin.fuseport.parts.players.FPPlayer;

//TODO: Add methods to track all teleportations, to remove requests.
public class RequestPart
{
	public void sendTPRequest(FPPlayer from, FPPlayer to)
	{
		from.feedback("feedback.request.sendRequest.implementation404").a(from).a(to).send();
	}

	public void sendTPHRequest(FPPlayer from, FPPlayer to)
	{
		from.feedback("feedback.request.sendRequest.implementation404").a(from).a(to).send();
	}

	public void accept(FPPlayer sender, FPPlayer who)
	{
		sender.feedback("feedback.request.accept.implementation404").a(sender).a(who).send();
	}

	public void deny(FPPlayer sender, FPPlayer who)
	{
		sender.feedback("feedback.request.accept.implementation404").a(sender).a(who).send();
	}
	
	public void disconnected(FPPlayer player)
	{
		
	}
}
