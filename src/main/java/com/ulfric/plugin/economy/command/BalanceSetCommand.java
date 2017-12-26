package com.ulfric.plugin.economy.command;

import java.math.BigDecimal;

import com.ulfric.commons.naming.Name;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.plugin.commands.argument.Argument;
import com.ulfric.plugin.commands.permissions.Permission;
import com.ulfric.plugin.economy.BankAccount;
import com.ulfric.plugin.economy.Economy;
import com.ulfric.plugin.restrictions.command.Restricted;

@Name("set")
@Permission("economy-balance-set")
@Restricted("balance-set")
public class BalanceSetCommand extends BalanceCommand {

	@Inject
	private Economy economy;

	@Argument
	private BankAccount target;

	@Argument
	protected BigDecimal amount;

	@Override
	public void run() {
		target.setBalance(amount);
		tell("economy-balance-set");
	}

}
