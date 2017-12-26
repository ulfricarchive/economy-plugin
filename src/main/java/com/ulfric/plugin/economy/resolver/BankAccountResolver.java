package com.ulfric.plugin.economy.resolver;

import java.util.UUID;

import com.ulfric.commons.bukkit.player.PlayerHelper;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.plugin.commands.argument.ResolutionRequest;
import com.ulfric.plugin.commands.argument.Resolver;
import com.ulfric.plugin.economy.BankAccount;
import com.ulfric.plugin.economy.Economy;

public class BankAccountResolver extends Resolver<BankAccount> {

	@Inject
	private Economy economy;

	public BankAccountResolver() {
		super(BankAccount.class);
	}

	@Override
	public BankAccount apply(ResolutionRequest request) {
		String argument = request.getArgument();
		UUID uniqueId = PlayerHelper.getCachedUniqueId(argument);
		if (uniqueId == null) {
			return null;
		}
		return economy.getBankAccount(uniqueId);
	}

}
