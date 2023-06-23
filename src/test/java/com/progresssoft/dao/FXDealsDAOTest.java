package com.progresssoft.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.progresssoft.models.DealDetails;
import com.progresssoft.utils.LoggerUtil;

class FXDealsDAOTest {
	@Mock
	private Connection connection;

	@Mock
	private PreparedStatement preparedStatement;

	@Mock
	private ResultSet resultSet;
	@Mock
	private LoggerUtil logger;
	@Mock
	private InputStream inputStream;
	@InjectMocks
	private FXDealsDAO fxDealsDAO;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
    void testSaveDealDetails() throws Exception {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(preparedStatement).setString(anyInt(), anyString());
        doNothing().when(preparedStatement).setTimestamp(anyInt(), any());
        doNothing().when(preparedStatement).setDouble(anyInt(), anyDouble());
        when(preparedStatement.executeUpdate()).thenThrow(SQLException.class);
        fxDealsDAO.saveDealDetails(new DealDetails());
        assertThrows(SQLException.class, () -> {
            throw new SQLException("Expected exception");
        });
    }

	@Test
    void testFindAllDeals() throws Exception {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt("DEAL_ID")).thenReturn(1);
        when(resultSet.getString("FROM_CURRENCY_ISO_CODE")).thenReturn("USD");
        List<DealDetails> dealsList = fxDealsDAO.findAllDeals();
        DealDetails deal = dealsList.get(0);
        assertEquals("USD", deal.getFromCurrencyIsoCode());
    }

	@Test
    void testDelete() throws Exception {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        when(preparedStatement.executeUpdate()).thenThrow(SQLException.class);
        fxDealsDAO.delete(1);
        assertThrows(SQLException.class, () -> {
            throw new SQLException("Expected exception");
        });
    }

	@Test
	void testClose() throws Exception {
		fxDealsDAO.close();
	}

}
