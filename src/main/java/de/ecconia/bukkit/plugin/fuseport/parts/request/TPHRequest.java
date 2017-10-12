package de.ecconia.bukkit.plugin.fuseport.parts.request;

import de.ecconia.bukkit.plugin.fuseport.parts.PartHolder;
import de.ecconia.bukkit.plugin.fuseport.parts.players.FPPlayer;

public class TPHRequest extends Request
{
	public TPHRequest(PartHolder parts, FPPlayer sender, FPPlayer receiver)
	{
		super(parts, sender, receiver);

		sender.feedback("notification.request.atSender.create.isTPH").a(sender).a(receiver).send();
		receiver.feedback("notification.request.atReceiver.create.isTPH").a(sender).a(receiver).send();
	}

	@Override
	public void abortByTp()
	{
		sender.feedback("notification.request.atSender.abort.byTP.isTPH").a(sender).a(receiver).send();
		receiver.feedback("notification.request.atReceiver.abort.byTP.isTPH").a(sender).a(receiver).send();
	}

	@Override
	public void abortByTph()
	{
		sender.feedback("notification.request.atSender.abort.byTPH.isTPH").a(sender).a(receiver).send();
		receiver.feedback("notification.request.atReceiver.abort.byTPH.isTPH").a(sender).a(receiver).send();
	}

	@Override
	public void abortBySenderDisconnect()
	{
		receiver.feedback("notification.request.atReceiver.abort.senderleave.isTPH").a(sender).a(receiver).send();
	}

	@Override
	public void abortByReceiverDisconnect()
	{
		sender.feedback("notification.request.atSender.abort.receiverleave.isTPH").a(sender).a(receiver).send();
	}

	@Override
	public void accept()
	{
		
		sender.feedback("notification.request.atSender.accept.isTPH").a(sender).a(receiver).send();
		parts.getTeleporter().teleportPlayerToSender(receiver, sender);
		receiver.feedback("notification.request.atReceiver.accept.isTPH").a(sender).a(receiver).send();
	}

	@Override
	public void deny()
	{
		sender.feedback("notification.request.atSender.deny.isTPH").a(sender).a(receiver).send();
		receiver.feedback("notification.request.atReceiver.deny.isTPH").a(sender).a(receiver).send();
	}
}
