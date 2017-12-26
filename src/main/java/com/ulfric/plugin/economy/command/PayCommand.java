package com.ulfric.plugin.economy.command;

import java.math.BigDecimal;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.ulfric.commons.value.UniqueIdHelper;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.plugin.commands.Command;
import com.ulfric.plugin.commands.argument.Argument;
import com.ulfric.plugin.economy.BankAccount;
import com.ulfric.plugin.economy.Economy;

public class PayCommand extends Command implements SkeletalPayCommand { // TODO should we set a minimum amount to avoid /pay spamming?

	@Override
	public void run() {
		pay();
	}

	@Inject
	private Economy economy;

	@Argument
	private BankAccount target;

	@Argument
	private BigDecimal amount;

	@Override
	public Economy economy() {
		return economy;
	}

	@Override
	public BigDecimal amount() {
		return amount;
	}

	@Override
	public BankAccount target() {
		return target;
	}

	@Override
	public String reason() {
		return "/pay";
	}

	@Override
	public void onPaySelf() {
		tell("economy-pay-self");
	}

	@Override
	public void onPayTarget() {
		tell("economy-pay-target");
	}

	@Override
	public void onPaidBySender() {
		UUID targetUniqueId = UniqueIdHelper.parseUniqueIdExact(target.getAccountIdentifier());
		if (targetUniqueId != null) {
			Player player = Bukkit.getPlayer(targetUniqueId);
			if (player != null) {
				tell(player, "economy-pay-by");
			}
		}
	}

	@Override
	public void onPaymentFail() {
		tell("economy-pay-fail");
	}

	@Override
	public void onInvalidAmount() {
		tell("economy-pay-positive");
	}

}
