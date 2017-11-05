package de.ecconia.bukkit.plugin.fuseport.parts.request;

import de.ecconia.bukkit.plugin.fuseport.parts.PartHolder;
import de.ecconia.bukkit.plugin.fuseport.parts.players.FPPlayer;

public class TPRequest extends Request
{
	public TPRequest(PartHolder parts, FPPlayer sender, FPPlayer receiver)
	{
		super(parts, sender, receiver);

		sender.feedback("request.create.sender.tp").a(sender).a(receiver).send();
		receiver.feedback("request.create.receiver.tp").a(sender).a(receiver).send();
	}

	@Override
	public void abortByTp()
	{
		sender.feedback("request.overwrite.tp.by-tp.sender").a(sender).a(receiver).send();
		receiver.feedback("request.overwrite.tp.by-tp.receiver").a(sender).a(receiver).send();
	}

	@Override
	public void abortByTph()
	{
		sender.feedback("request.overwrite.tp.by-tph.sender").a(sender).a(receiver).send();
		receiver.feedback("request.overwrite.tp.by-tph.receiver").a(sender).a(receiver).send();
	}

	@Override
	public void abortBySenderDisconnect()
	{
		receiver.feedback("request.leave.sender.tp").a(sender).a(receiver).send();
	}

	@Override
	public void abortByReceiverDisconnect()
	{
		sender.feedback("request.leave.receiver.tp").a(sender).a(receiver).send();
	}

	@Override
	public void accept()
	{
		sender.feedback("request.accept.before-tp.tp.sender").a(sender).a(receiver).send();
		receiver.feedback("request.accept.before-tp.tp.receiver").a(sender).a(receiver).send();
		parts.getTeleporter().teleportSenderToPlayer(sender, receiver);
		sender.feedback("request.accept.after-tp.tp.sender").a(sender).a(receiver).send();
		receiver.feedback("request.accept.after-tp.tp.receiver").a(sender).a(receiver).send();
	}

	@Override
	public void deny()
	{
		sender.feedback("request.deny.tp.sender").a(sender).a(receiver).send();
		receiver.feedback("request.deny.tp.receiver").a(sender).a(receiver).send();
	}
}
