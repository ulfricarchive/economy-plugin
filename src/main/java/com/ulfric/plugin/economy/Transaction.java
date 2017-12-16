package com.ulfric.plugin.economy;

import java.math.BigDecimal;

import com.ulfric.commons.value.Bean;

public class Transaction extends Bean {

	private BigDecimal amount;
	private String reason;
	private String counterparty;
	private Boolean status;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCounterparty() {
		return counterparty;
	}

	public void setCounterparty(String counterparty) {
		this.counterparty = counterparty;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
