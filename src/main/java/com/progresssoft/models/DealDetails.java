package com.progresssoft.models;

import java.sql.Timestamp;

public class DealDetails {
	/**
	 * forgot to follow fields declaration convention, to not use primitive DT
	 */
	private int dealId;
	private String fromCurrencyIsoCode;
	private String toCurrencyIsoCode;
	private Timestamp dealTimestamp;
	private double dealAmount;

	public DealDetails() {
	}

	public DealDetails(int dealId, String fromCurrencyIsoCode, String toCurrencyIsoCode, Timestamp dealTimestamp,
			double dealAmount) {
		super();
		this.dealId = dealId;
		this.fromCurrencyIsoCode = fromCurrencyIsoCode;
		this.toCurrencyIsoCode = toCurrencyIsoCode;
		this.dealTimestamp = dealTimestamp;
		this.dealAmount = dealAmount;
	}

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
