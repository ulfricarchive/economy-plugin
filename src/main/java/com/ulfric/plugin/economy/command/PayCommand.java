package com.ulfric.plugin.economy.command;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.ulfric.commons.math.NumberHelper;
import com.ulfric.commons.value.UniqueIdHelper;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.plugin.commands.argument.Argument;
import com.ulfric.plugin.economy.BankAccount;
import com.ulfric.plugin.economy.Economy;

public class PayCommand extends SkeletalPayCommand { // TODO should we set a minimum amount to avoid /pay spamming?

	@Inject
	private Economy economy;

	@Argument
	protected BankAccount target;

	@Override
	protected boolean isValidAmount() {
		return NumberHelper.isPositive(amount);
	}

	@Override
	protected BankAccount target() {
		return target;
	}

	@Override
	protected String reason() {
		return "/pay";
	}

	@Override
	protected void onPaySelf() {
		tell("economy-pay-self");
	}

	@Override
	protected void onPayTarget() {
		tell("economy-pay-target");
	}

	@Override
	protected void onPaidBySender() {
		UUID targetUniqueId = UniqueIdHelper.parseUniqueIdExact(target.getAccountIdentifier());
		if (targetUniqueId != null) {
			Player player = Bukkit.getPlayer(targetUniqueId);
			if (player != null) {
				tell(player, "economy-pay-by");
			}
		}
	}

	@Override
	protected void onPaymentFail() {
		tell("economy-pay-fail");
	}

	@Override
	protected void onInvalidAmount() {
		tell("economy-pay-positive");
	}

}
