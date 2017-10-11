package de.ecconia.bukkit.plugin.fuseport.parts.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.ecconia.bukkit.plugin.fuseport.parts.players.FPPlayer;

public class RequestStorage
{
	private Map<FPPlayer, List<Request>> requestsBySender = new HashMap<>();
	private Map<FPPlayer, List<Request>> requestsByReceiver = new HashMap<>();
	
	public void newRequest(Request request)
	{
		List<Request> senderRequests = getRequestBySender(request.getSender());
		senderRequests.add(request);
		
		List<Request> receiverRequests = getRequestBySender(request.getReceiver());
		receiverRequests.add(request);
	}
	
	public List<Request> deleteSender(FPPlayer sender)
	{
		List<Request> requests = requestsBySender.remove(sender);
		if(requests != null)
		{
			for(Request request : requests)
			{
				requestsByReceiver.get(request.getReceiver()).remove(request);
			}
		}
		return requests;
	}
	
	public void deleteRequest(Request request)
	{
		requestsByReceiver.get(request.getReceiver()).remove(request);
		requestsBySender.get(request.getSender()).remove(request);
	}
	
	public void deleteReceiver(FPPlayer receiver)
	{
		List<Request> requests = requestsByReceiver.remove(receiver);
		if(requests != null)
		{
			for(Request request : requests)
			{
				requestsBySender.get(request.getSender()).remove(request);
			}
		}
	}
	
	public List<Request> getRequestBySender(FPPlayer sender)
	{
		List<Request> senderRequests = requestsBySender.get(sender);
		if(senderRequests == null)
		{
			senderRequests = new ArrayList<>();
			requestsBySender.put(sender, senderRequests);
		}
		return senderRequests;
	}
	
	public List<Request> getRequestByReceiver(FPPlayer receiver)
	{
		List<Request> receiverRequests = requestsByReceiver.get(receiver);
		if(receiverRequests == null)
		{
			receiverRequests = new ArrayList<>();
			requestsByReceiver.put(receiver, receiverRequests);
		}
		return receiverRequests;
	}
}
