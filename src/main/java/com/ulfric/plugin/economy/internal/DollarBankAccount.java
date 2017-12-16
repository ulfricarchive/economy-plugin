package com.ulfric.plugin.economy.internal;

import java.math.BigDecimal;

import com.ulfric.commons.math.NumberHelper;
import com.ulfric.dragoon.acrodb.Store;
import com.ulfric.plugin.economy.BankAccount;
import com.ulfric.plugin.economy.Transaction;

final class DollarBankAccount implements BankAccount {

	private final Store<OfflineBankAccount> store;
	private final String identifier;

	public DollarBankAccount(Store<OfflineBankAccount> store, String identifier) {
		this.store = store;
		this.identifier = identifier;
	}

	@Override
	public String getAccountIdentifier() {
		return identifier;
	}

	@Override
	public BigDecimal getBalance() {
		return get().getBalance();
	}

	@Override
	public void setBalance(BigDecimal amount) {
		store.editAndWrite(identifier, document -> document.setBalance(amount));
	}

	@Override
	public boolean execute(Transaction transaction) {
		return store.editAndWriteIf(identifier, document -> {
			BigDecimal amount = transaction.getAmount();
			if (amount == null) {
				amount = BigDecimal.ZERO;
			}

			BigDecimal balance = document.getBalance();

			if (NumberHelper.isNegative(amount)) {
				if (NumberHelper.isNegative(balance)) {
					return false; // TODO proper status
				}

				BigDecimal potentialNewBalance = balance.add(amount);
				if (NumberHelper.isNegative(potentialNewBalance)) {
					return false; // TODO proper status
				}
				document.setBalance(potentialNewBalance);
			} else {
				document.setBalance(balance.add(amount));
			}

			transaction.setStatus(true);
			return true; // TODO proper status
		});
	}

	private OfflineBankAccount get() {
		return store.get(identifier);
	}

}
