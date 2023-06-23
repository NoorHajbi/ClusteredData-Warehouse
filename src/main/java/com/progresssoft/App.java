package com.progresssoft;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import com.progresssoft.models.DealDetails;
import com.progresssoft.service.ClusteredDataService;

public class App {
	static {
		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
	}

	public static void main(String[] args) {
		ClusteredDataService clusteredDataService = new ClusteredDataService();
		DealDetails deal = new DealDetails();
		Random random = new Random();
		int dealId = random.nextInt();
		deal.setDealId(dealId);
		deal.setFromCurrencyIsoCode("JOR");
		deal.setToCurrencyIsoCode("LEB");
		deal.setDealTimestamp(new Timestamp(System.currentTimeMillis()));
		deal.setDealAmount(1234.32);
		clusteredDataService.acceptDeals(deal);

		List<DealDetails> allDeals = clusteredDataService.findAllDealsDetails();
		System.out.println("All stored deals:");
		int i = 1;
		for (DealDetails stroedDeal : allDeals) {
			System.out.println("NUMBER: " + i++ + " ID: " + stroedDeal.getDealId());
		}

	}
}
