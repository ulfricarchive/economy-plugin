package com.ulfric.plugin.economy;

import com.ulfric.plugin.Plugin;
import com.ulfric.plugin.economy.function.BankAccountToBalanceFunction;
import com.ulfric.plugin.economy.function.BankAccountToIdentifierFunction;
import com.ulfric.plugin.economy.function.CommandSenderToBankAccountFunction;
import com.ulfric.plugin.economy.internal.DollarEconomy;

public class EconomyPlugin extends Plugin {

	public EconomyPlugin() {
		install(DollarEconomy.class);

		install(CommandSenderToBankAccountFunction.class);
		install(BankAccountToBalanceFunction.class);
		install(BankAccountToIdentifierFunction.class);
	}

}
