package com.ulfric.plugin.economy.function;

import com.ulfric.plugin.economy.BankAccount;

public class BankAccountToBalanceFunction extends BankAccountFunction {

	public BankAccountToBalanceFunction() {
		super("balance");
	}

	@Override
	public Object apply(BankAccount account) {
		return account.getBalance();
	}

}
