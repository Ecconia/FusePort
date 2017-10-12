package de.ecconia.bukkit.plugin.fuseport.parts.request;

import de.ecconia.bukkit.plugin.fuseport.parts.players.FPPlayer;

public class TPRequest extends Request
{
	public TPRequest(FPPlayer sender, FPPlayer receiver)
	{
		super(sender, receiver);
		
		sender.feedback("notification.request.atSender.create.isTP").a(sender).a(receiver).send();
		receiver.feedback("notification.request.atReceiver.create.isTP").a(sender).a(receiver).send();
	}

	@Override
	public void abortByTp()
	{
		sender.feedback("notification.request.atSender.abort.byTP.isTP").a(sender).a(receiver).send();
		receiver.feedback("notification.request.atReceiver.abort.byTP.isTP").a(sender).a(receiver).send();
	}

	@Override
	public void abortByTph()
	{
		sender.feedback("notification.request.atSender.abort.byTPH.isTP").a(sender).a(receiver).send();
		receiver.feedback("notification.request.atReceiver.abort.byTPH.isTP").a(sender).a(receiver).send();
	}

	@Override
	public void abortBySenderDisconnect()
	{
		receiver.feedback("notification.request.atReceiver.abort.senderleave.isTP").a(sender).a(receiver).send();
	}

	@Override
	public void abortByReceiverDisconnect()
	{
		sender.feedback("notification.request.atSender.abort.receiverleave.isTP").a(sender).a(receiver).send();
	}

	@Override
	public void accept()
	{
		sender.feedback("notification.request.atSender.accept.isTP").a(sender).a(receiver).send();
		receiver.feedback("notification.request.atReceiver.accept.isTP").a(sender).a(receiver).send();
	}

	@Override
	public void deny()
	{
		sender.feedback("notification.request.atSender.deny.isTP").a(sender).a(receiver).send();
		receiver.feedback("notification.request.atReceiver.deny.isTP").a(sender).a(receiver).send();
	}
}
