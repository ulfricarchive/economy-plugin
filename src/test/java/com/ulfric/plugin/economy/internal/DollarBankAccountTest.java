package com.ulfric.plugin.economy.internal;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.google.common.jimfs.Jimfs;
import com.google.common.truth.Truth;
import com.ulfric.acrodb.Bucket;
import com.ulfric.dragoon.acrodb.Store;
import com.ulfric.plugin.economy.BankAccount;
import com.ulfric.plugin.economy.Transaction;

class DollarBankAccountTest {

	@Test
	void testSetBalance() {
		BigDecimal amount = BigDecimal.valueOf(157.25);
		BankAccount account = mockAccountWithBalance(amount);
		Truth.assertThat(account.getBalance()).isEquivalentAccordingToCompareTo(amount);
	}

	@Test
	void testTakeWithPositiveBalance() {
		BigDecimal startingBalance = BigDecimal.valueOf(100);
		BankAccount account = mockAccountWithBalance(startingBalance);

		BigDecimal cost = BigDecimal.valueOf(-25);
		Transaction transaction = transaction(cost);

		Truth.assertThat(account.execute(transaction)).isTrue();

		Truth.assertThat(account.getBalance()).isEqualToIgnoringScale(75);
	}

	@Test
	void testTakeWithNegativeBalance() {
		BigDecimal startingBalance = BigDecimal.valueOf(-100);
		BankAccount account = mockAccountWithBalance(startingBalance);

		BigDecimal cost = BigDecimal.valueOf(-25);
		Transaction transaction = transaction(cost);

		Truth.assertThat(account.execute(transaction)).isFalse();
		Truth.assertThat(account.getBalance()).isEquivalentAccordingToCompareTo(startingBalance);
	}

	@Test
	void testGiveWithPositiveBalance() {
		BigDecimal startingBalance = BigDecimal.valueOf(100);
		BankAccount account = mockAccountWithBalance(startingBalance);

		BigDecimal cost = BigDecimal.valueOf(25);
		Transaction transaction = transaction(cost);

		Truth.assertThat(account.execute(transaction)).isTrue();

		Truth.assertThat(account.getBalance()).isEqualToIgnoringScale(125);
	}

	@Test
	void testGiveWithNegativeBalance() {
		BigDecimal startingBalance = BigDecimal.valueOf(-100);
		BankAccount account = mockAccountWithBalance(startingBalance);

		BigDecimal cost = BigDecimal.valueOf(25);
		Transaction transaction = transaction(cost);

		Truth.assertThat(account.execute(transaction)).isTrue();
		Truth.assertThat(account.getBalance()).isEqualToIgnoringScale(-75);
	}

	private Transaction transaction(BigDecimal amount) {
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setCounterparty(UUID.randomUUID().toString());
		transaction.setReason("gift");
		return transaction;
	}

	private BankAccount mockAccountWithBalance(BigDecimal balance) {
		BankAccount account = mockAccount();
		account.setBalance(balance);
		return account;
	}

	private BankAccount mockAccount() {
		return new DollarBankAccount(mockStore(), UUID.randomUUID().toString());
	}

	private Store<OfflineBankAccount> mockStore() {
		Bucket mockBucket = new Bucket(Jimfs.newFileSystem().getPath("mock"));
		return new Store<>(mockBucket, OfflineBankAccount.class);
	}

}
