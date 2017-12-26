package com.ulfric.plugin.economy.command;

import java.math.BigDecimal;
import java.util.Objects;

import com.ulfric.commons.math.NumberHelper;
import com.ulfric.plugin.commands.CommandExtension;
import com.ulfric.plugin.economy.BankAccount;
import com.ulfric.plugin.economy.Economy;
import com.ulfric.plugin.economy.Transaction;

public interface SkeletalPayCommand extends CommandExtension {

	default boolean pay() {
		if (!isValidAmount()) {
			onInvalidAmount();
			return false;
		}

		BankAccount target = target();
		BankAccount self = economy().getBankAccount(uniqueId());
		if (Objects.equals(target.getAccountIdentifier(), self.getAccountIdentifier())) {
			onPaySelf();
			return false;
		}

		Transaction take = new Transaction();
		take.setAmount(amount().negate());
		take.setCounterparty(target.getAccountIdentifier());
		take.setReason(reason());

		if (self.execute(take)) {
			Transaction give = new Transaction();
			give.setAmount(amount());
			give.setCounterparty(self.getAccountIdentifier());
			give.setReason(reason());

			target.execute(give);

			onPayTarget();
			onPaidBySender();
			return true;
		}

		onPaymentFail();
		return false;
	}

	default boolean isValidAmount() {
		return NumberHelper.isPositive(amount());
	}

	Economy economy();

	BigDecimal amount();

	BankAccount target();

	String reason();

	default void onPaySelf() {
	}

	void onPayTarget();

	void onPaidBySender();

	void onPaymentFail();

	void onInvalidAmount();

}
