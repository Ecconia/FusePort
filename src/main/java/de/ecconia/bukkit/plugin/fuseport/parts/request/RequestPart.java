package de.ecconia.bukkit.plugin.fuseport.parts.request;

import java.util.List;

import de.ecconia.bukkit.plugin.fuseport.parts.players.FPPlayer;

//TODO: Add methods to track all teleportations, to remove requests.
public class RequestPart
{
	private RequestStorage requests;
	
	public RequestPart()
	{
		requests = new RequestStorage();
	}
	
	public void sendTPRequest(FPPlayer from, FPPlayer to)
	{
		Request request = new TPRequest(from, to);
		
		//TODO: Config query
		if(true)
		{
			//TODO: Config: allow multiple tp requests
			List<Request> senderRequests = requests.getRequestBySender(from);
			if(senderRequests.size() > 0)
			{
				List<Request> abortedRequests = requests.deleteSender(from);
				for(Request aborted : abortedRequests)
				{
					aborted.abortByTp();
				}
			}
		}
		
		requests.newRequest(request);
	}
	
	public void sendTPHRequest(FPPlayer from, FPPlayer to)
	{
		Request request = new TPHRequest(from, to);
		
		//TODO: Config query
		if(true)
		{
			List<Request> senderRequests = requests.getRequestBySender(from);
			if(senderRequests.size() > 0 && (senderRequests.get(0) instanceof TPRequest))
			{
				List<Request> abortedRequests = requests.deleteSender(from);
				for(Request aborted : abortedRequests)
				{
					aborted.abortByTph();
				}
			}
		}
		
		requests.newRequest(request);
	}
	
	public void accept(FPPlayer sender, FPPlayer who)
	{
		Request request = requests.getRequestBySenderAndReceiver(who, sender);
		if(request == null)
		{
			//TODO: Feedback: No request from that person pending.
		}
		else
		{
			request.accept();
		}
	}
	
	public void deny(FPPlayer sender, FPPlayer who)
	{
		Request request = requests.getRequestBySenderAndReceiver(who, sender);
		if(request == null)
		{
			//TODO: Feedback: No request from that person pending.
		}
		else
		{
			request.deny();
		}
	}
	
	public void disconnected(FPPlayer player)
	{
		List<Request> abortedRequests = requests.getRequestBySender(player);
		for(Request request : abortedRequests)
		{
			request.abortBySenderDisconnect();
		}
		
		abortedRequests = requests.getRequestByReceiver(player);
		for(Request request : abortedRequests)
		{
			request.abortByReceiverDisconnect();
		}
	}
}
