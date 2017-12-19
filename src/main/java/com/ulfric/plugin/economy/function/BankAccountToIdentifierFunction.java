package com.ulfric.plugin.economy.function;

import com.ulfric.plugin.economy.BankAccount;

public class BankAccountToIdentifierFunction extends BankAccountFunction {

	public BankAccountToIdentifierFunction() {
		super("identifier");
	}

	@Override
	public Object apply(BankAccount account) {
		return account.getAccountIdentifier();
	}

}
