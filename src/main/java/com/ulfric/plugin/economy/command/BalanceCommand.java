package com.ulfric.plugin.economy.command;

import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.plugin.commands.Command;
import com.ulfric.plugin.commands.argument.Argument;
import com.ulfric.plugin.economy.BankAccount;
import com.ulfric.plugin.economy.Economy;

public class BalanceCommand extends Command {

	@Inject
	private Economy economy;

	@Argument(optional = true)
	protected BankAccount target;

	@Override
	public void run() {
		if (target == null) {
			target = economy.getBankAccount(uniqueId());
			tell("economy-balance-self");
		} else {
			tell("economy-balance-target");
		}
	}

}
