package com.progresssoft.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.progresssoft.dao.FXDealsDAO;
import com.progresssoft.models.DealDetails;

public class ClusteredDataServiceTest {
	@InjectMocks
	private ClusteredDataService clusteredDataService;

	@Mock
	private FXDealsDAO mockDao;

/////////////////////////////////////////////////////////////////////////////////////////////////////
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test // happy scenario
	void testAcceptDeals() {
		DealDetails validDeal = new DealDetails();
		validDeal.setDealId(1);
		validDeal.setFromCurrencyIsoCode("USD");
		validDeal.setToCurrencyIsoCode("JOR");
		validDeal.setDealTimestamp(new Timestamp(System.currentTimeMillis()));
		validDeal.setDealAmount(121.1);

		assertDoesNotThrow(() -> clusteredDataService.acceptDeals(validDeal));
		verify(mockDao, times(1)).saveDealDetails(validDeal);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void testAcceptDeals_InvalidId() {
		DealDetails invalidID = new DealDetails();
		invalidID.setDealId(0);
		clusteredDataService.acceptDeals(invalidID);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void testAcceptDeals_NullFromCurrencyIsoCode() {
		DealDetails invalidFromCurrencyIsoCode = new DealDetails();
		invalidFromCurrencyIsoCode.setDealId(1);
		invalidFromCurrencyIsoCode.setFromCurrencyIsoCode(null);
		clusteredDataService.acceptDeals(invalidFromCurrencyIsoCode);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void testAcceptDeals_EmptyFromCurrencyIsoCode() {
		DealDetails invalidFromCurrencyIsoCode = new DealDetails();
		invalidFromCurrencyIsoCode.setDealId(1);
		invalidFromCurrencyIsoCode.setFromCurrencyIsoCode("");
		clusteredDataService.acceptDeals(invalidFromCurrencyIsoCode);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void testAcceptDeals_NullToCurrencyIsoCode() {
		DealDetails invalidToCurrencyIsoCode = new DealDetails();
		invalidToCurrencyIsoCode.setDealId(1);
		invalidToCurrencyIsoCode.setFromCurrencyIsoCode("JOR");
		invalidToCurrencyIsoCode.setToCurrencyIsoCode(null);
		clusteredDataService.acceptDeals(invalidToCurrencyIsoCode);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void testAcceptDeals_EmptyToCurrencyIsoCode() {
		DealDetails invalidToCurrencyIsoCode = new DealDetails();
		invalidToCurrencyIsoCode.setDealId(1);
		invalidToCurrencyIsoCode.setFromCurrencyIsoCode("JOR");
		invalidToCurrencyIsoCode.setToCurrencyIsoCode("");
		clusteredDataService.acceptDeals(invalidToCurrencyIsoCode);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void testAcceptDeals_InvalidTimestamp() {
		DealDetails invalidTimestamp = new DealDetails();
		invalidTimestamp.setDealId(1);
		invalidTimestamp.setFromCurrencyIsoCode("JOR");
		invalidTimestamp.setToCurrencyIsoCode("LEB");
		invalidTimestamp.setDealTimestamp(null);
		clusteredDataService.acceptDeals(invalidTimestamp);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void testAcceptDeals_InvalidDealAmount() {
		DealDetails invalidDealAmount = new DealDetails();
		invalidDealAmount.setDealId(1);
		invalidDealAmount.setFromCurrencyIsoCode("JOR");
		invalidDealAmount.setToCurrencyIsoCode("LEB");
		invalidDealAmount.setDealTimestamp(new Timestamp(0));
		invalidDealAmount.setDealAmount(-1);
		clusteredDataService.acceptDeals(invalidDealAmount);
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////

	@Test
	void testCreateDealDetailsTable() {
		clusteredDataService.createDealDetailsTable();
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void testFindAllDealsDetails() {
		List<DealDetails> expected = new ArrayList<>();
		when(mockDao.findAllDeals()).thenReturn(expected);
		assertEquals(expected, clusteredDataService.findAllDealsDetails());
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////

	@Test
	void testDeleteDealDetails() {
		clusteredDataService.deleteDealDetails(1);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void testCloseDealsConnection() {
		clusteredDataService.CloseDealsConnection();
	}
}
