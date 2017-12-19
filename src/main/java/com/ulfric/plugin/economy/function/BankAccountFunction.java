package com.ulfric.plugin.economy.function;

import com.ulfric.i18n.function.Function;
import com.ulfric.plugin.economy.BankAccount;

public abstract class BankAccountFunction extends Function<BankAccount> {

	public BankAccountFunction(String name) {
		super(name, BankAccount.class);
	}

}
