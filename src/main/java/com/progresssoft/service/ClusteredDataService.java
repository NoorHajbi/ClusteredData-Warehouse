package com.progresssoft.service;

import java.util.List;

import com.progresssoft.dao.FXDealsDAO;
import com.progresssoft.models.DealDetails;
import com.progresssoft.utils.LoggerUtil;

public class ClusteredDataService {
	private static final LoggerUtil logger = new LoggerUtil();
	private FXDealsDAO dao;

/////////////////////////////////////////////////////////////////////////////////////////////////////
	public ClusteredDataService() {
		this.dao = new FXDealsDAO();
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
	private void validateDealStructure(DealDetails deal) {
		if (deal.getDealId() <= 0) {
			throw new IllegalArgumentException("Invalid deal ID");
		}

		if (deal.getFromCurrencyIsoCode() == null || deal.getFromCurrencyIsoCode().isEmpty()) {
			throw new IllegalArgumentException("Missing fromCurrencyISOCode");
		}

		if (deal.getToCurrencyIsoCode() == null || deal.getToCurrencyIsoCode().isEmpty()) {
			throw new IllegalArgumentException("Missing toCurrencyISOCode");
		}

		if (deal.getDealTimestamp() == null) {
			throw new IllegalArgumentException("Missing dealTimestamp");
		}

		if (deal.getDealAmount() <= 0) {
			throw new IllegalArgumentException("Invalid dealAmount");
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
	public void acceptDeals(DealDetails deal) {
		try {
			logger.info("Start getting deal details of id  = " + deal.getDealId());
			validateDealStructure(deal);
			dao.saveDealDetails(deal);
			logger.debug("Finished analyzing deal details of id  = " + deal.getDealId());

		} catch (IllegalArgumentException e) {
			logger.error("Provided deal details are invalid due \n");
			e.printStackTrace();
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Additional code
	 */
	public void createDealDetailsTable() {
		dao.createTable();
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
	public List<DealDetails> findAllDealsDetails() {
		return dao.findAllDeals();
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
	public void deleteDealDetails(int id) {
		dao.delete(id);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
	public void CloseDealsConnection() {
		dao.close();
	}
}
