package com.ulfric.plugin.economy;

import java.math.BigDecimal;

public interface BankAccount {

	String getAccountIdentifier();

	BigDecimal getBalance();

	void setBalance(BigDecimal amount);

	boolean execute(Transaction transaction);

}
