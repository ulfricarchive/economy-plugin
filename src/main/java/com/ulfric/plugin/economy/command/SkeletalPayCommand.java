package com.ulfric.plugin.economy.command;

import java.math.BigDecimal;
import java.util.Objects;

import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.plugin.commands.Command;
import com.ulfric.plugin.commands.argument.Argument;
import com.ulfric.plugin.economy.BankAccount;
import com.ulfric.plugin.economy.Economy;
import com.ulfric.plugin.economy.Transaction;

public abstract class SkeletalPayCommand extends Command {

	@Inject
	private Economy economy;

	@Argument
	protected BigDecimal amount;

	@Override
	public final void run() {
		if (!isValidAmount()) {
			onInvalidAmount();
			return;
		}

		BankAccount target = target();
		BankAccount self = economy.getBankAccount(uniqueId());
		if (Objects.equals(target.getAccountIdentifier(), self.getAccountIdentifier())) {
			onPaySelf();
			return;
		}

		Transaction take = new Transaction();
		take.setAmount(amount.negate());
		take.setCounterparty(target.getAccountIdentifier());
		take.setReason(reason());

		if (self.execute(take)) {
			Transaction give = new Transaction();
			give.setAmount(amount);
			give.setCounterparty(self.getAccountIdentifier());
			give.setReason(reason());

			target.execute(give);

			onPayTarget();
			onPaidBySender();
		} else {
			onPaymentFail();
		}
	}

	protected abstract boolean isValidAmount();

	protected abstract BankAccount target();

	protected abstract String reason();

	protected abstract void onPaySelf();

	protected abstract void onPayTarget();

	protected abstract void onPaidBySender();

	protected abstract void onPaymentFail();

	protected abstract void onInvalidAmount();

}
