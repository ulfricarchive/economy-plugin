package com.ulfric.plugin.economy.internal;

import java.util.concurrent.ConcurrentMap;

import com.ulfric.commons.collection.MapHelper;
import com.ulfric.dragoon.acrodb.Database;
import com.ulfric.dragoon.acrodb.Store;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.plugin.economy.BankAccount;
import com.ulfric.plugin.economy.Economy;

public class DollarEconomy implements Economy {

	private final ConcurrentMap<String, BankAccount> loadedAccounts = MapHelper.newConcurrentMap(2);

	@Inject
	@Database({ "factions", "denizens" })
	private Store<OfflineBankAccount> accounts;

	@Override
	public Class<Economy> getService() {
		return Economy.class;
	}

	@Override
	public BankAccount getBankAccount(String accountIdentifier) {
		return loadedAccounts.computeIfAbsent(accountIdentifier, this::loadBankAccount);
	}

	private BankAccount loadBankAccount(String accountIdentifier) {
		return new DollarBankAccount(accounts, accountIdentifier);
	}

}
