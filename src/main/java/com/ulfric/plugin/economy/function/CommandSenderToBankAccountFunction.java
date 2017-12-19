package com.ulfric.plugin.economy.function;

import java.util.UUID;

import org.bukkit.command.CommandSender;

import com.ulfric.commons.bukkit.command.CommandSenderHelper;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.plugin.economy.Economy;
import com.ulfric.plugin.locale.function.CommandSenderFunction;

public class CommandSenderToBankAccountFunction extends CommandSenderFunction {

	@Inject
	private Economy economy;

	public CommandSenderToBankAccountFunction() {
		super("bank");
	}

	@Override
	public Object apply(CommandSender sender) {
		UUID uniqueId = CommandSenderHelper.getUniqueId(sender);
		return uniqueId == null ? null : economy.getBankAccount(uniqueId);
	}

}
