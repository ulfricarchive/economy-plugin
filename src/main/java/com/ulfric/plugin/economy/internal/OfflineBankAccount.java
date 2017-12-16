package com.ulfric.plugin.economy.internal;

import java.math.BigDecimal;

import com.ulfric.dragoon.acrodb.Document;

public class OfflineBankAccount extends Document { // TODO SPIKE - convert this to a sink system to store transaction history

	private BigDecimal balance;

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
