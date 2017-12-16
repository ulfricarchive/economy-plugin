package com.ulfric.plugin.economy;

import java.util.UUID;

import com.ulfric.plugin.services.Service;

public interface Economy extends Service<Economy> {

	default BankAccount getBankAccount(UUID accountIdentifier) {
		return getBankAccount(accountIdentifier.toString());
	}

	BankAccount getBankAccount(String accountIdentifier);

}
