package com.ulfric.plugin.economy;

import com.ulfric.plugin.Plugin;
import com.ulfric.plugin.economy.internal.DollarEconomy;

public class EconomyPlugin extends Plugin {

	public EconomyPlugin() {
		install(DollarEconomy.class);
	}

}
