package com.progresssoft.models;

import java.sql.Timestamp;

public class DealsDetails {
	private int dealId;
	private String fromCurrencyIsoCode;
	private String toCurrencyIsoCode;
	private Timestamp dealTimestamp;
	private double dealAmount;

	public int getDealId() {
		return dealId;
	}

	public void setDealId(int dealId) {
		this.dealId = dealId;
	}

	public String getFromCurrencyIsoCode() {
		return fromCurrencyIsoCode;
	}

	public void setFromCurrencyIsoCode(String fromCurrencyIsoCode) {
		this.fromCurrencyIsoCode = fromCurrencyIsoCode;
	}

	public String getToCurrencyIsoCode() {
		return toCurrencyIsoCode;
	}

	public void setToCurrencyIsoCode(String toCurrencyIsoCode) {
		this.toCurrencyIsoCode = toCurrencyIsoCode;
	}

	public Timestamp getDealTimestamp() {
		return dealTimestamp;
	}

	public void setDealTimestamp(Timestamp dealTimestamp) {
		this.dealTimestamp = dealTimestamp;
	}

	public double getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(double dealAmount) {
		this.dealAmount = dealAmount;
	}

}
