package com.suliman.datawarehouse.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DealDTO {

	private Long id;
	private String fromCurrency;
	private String toCurrency;;
	private LocalDateTime dealTimestamp;
	private BigDecimal amount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public LocalDateTime getDealTimestamp() {
		return dealTimestamp;
	}

	public void setDealTimestamp(LocalDateTime dealTimestamp) {
		this.dealTimestamp = dealTimestamp;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
