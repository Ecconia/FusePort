package de.ecconia.bukkit.plugin.fuseport.parts.request;

import de.ecconia.bukkit.plugin.fuseport.parts.PartHolder;
import de.ecconia.bukkit.plugin.fuseport.parts.players.FPPlayer;

public class TPHRequest extends Request
{
	public TPHRequest(PartHolder parts, FPPlayer sender, FPPlayer receiver)
	{
		super(parts, sender, receiver);

		sender.feedback("request.create.sender.tph").a(sender).a(receiver).send();
		receiver.feedback("request.create.receiver.tph").a(sender).a(receiver).send();
	}

	@Override
	public void abortByTp()
	{
		sender.feedback("request.overwrite.tph.by-tp.sender").a(sender).a(receiver).send();
		receiver.feedback("request.overwrite.tph.by-tp.receiver").a(sender).a(receiver).send();
	}

	@Override
	public void abortByTph()
	{
		sender.feedback("request.overwrite.tph.by-tph.sender").a(sender).a(receiver).send();
		receiver.feedback("request.overwrite.tph.by-tph.receiver").a(sender).a(receiver).send();
	}

	@Override
	public void abortBySenderDisconnect()
	{
		receiver.feedback("request.leave.sender.tph").a(sender).a(receiver).send();
	}

	@Override
	public void abortByReceiverDisconnect()
	{
		sender.feedback("request.leave.receiver.tph").a(sender).a(receiver).send();
	}

	@Override
	public void accept()
	{
		sender.feedback("request.accept.before-tp.tph.sender").a(sender).a(receiver).send();
		receiver.feedback("request.accept.before-tp.tph.receiver").a(sender).a(receiver).send();
		parts.getTeleporter().teleportPlayerToSender(receiver, sender);
		sender.feedback("request.accept.after-tp.tph.sender").a(sender).a(receiver).send();
		receiver.feedback("request.accept.after-tp.tph.receiver").a(sender).a(receiver).send();
	}

	@Override
	public void deny()
	{
		sender.feedback("request.deny.tph.sender").a(sender).a(receiver).send();
		receiver.feedback("request.deny.tph.receiver").a(sender).a(receiver).send();
	}
}
