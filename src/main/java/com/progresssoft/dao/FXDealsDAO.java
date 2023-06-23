package com.progresssoft.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.progresssoft.models.DealDetails;
import com.progresssoft.utils.Constants.DaoConstants;
import com.progresssoft.utils.LoggerUtil;

/**
 * @author noor.hajbi
 * 
 */
public class FXDealsDAO {
	private LoggerUtil logger = new LoggerUtil();
	private Connection conn;

/////////////////////////////////////////////////////////////////////////////////////////////////////
	private void buildConnection() {
		logger.info("Start establish the connection..");
		try {
			Properties prop = new Properties();
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
			prop.load(inputStream);

			String url = prop.getProperty("jdbc.url");
			String user = prop.getProperty("jdbc.username");
			String password = prop.getProperty("jdbc.password");
			conn = DriverManager.getConnection(url, user, password);
			logger.debug("Finished establishing the connection..");
		} catch (IOException e) {
			logger.error("Failed to read config.properties due: \n", e.getMessage());
		} catch (SQLException e) {
			logger.error("Failed to get mysql connection due: \n", e.getMessage());
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
	public void createTable() {
		try {
			logger.info("Start creating DEALS_DETAILS Table.");
			buildConnection();
			Statement statement = conn.createStatement();
			statement.execute(DaoConstants.CREATE_TABLE_QUERY);
			logger.debug("Finished creating DEALS_DETAILS Table.");
		} catch (SQLException e) {
			logger.error("Failed to create DEALS_DETAILS Table due: \n", e.getMessage());
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
	public void saveDealDetails(DealDetails deal) {
		try {
			logger.info("Start saving a new deal details.");
			buildConnection();
			PreparedStatement statement = conn.prepareStatement(DaoConstants.INSERT_DEAL_QUERY);
			statement.setInt(1, deal.getDealId());
			statement.setString(2, deal.getFromCurrencyIsoCode());
			statement.setString(3, deal.getToCurrencyIsoCode());
			statement.setTimestamp(4, deal.getDealTimestamp());
			statement.setDouble(5, deal.getDealAmount());
			statement.executeUpdate();

			logger.debug("Finished saving a new deal details.");
		} catch (SQLException e) {
			logger.error("Failed to save a new deal details due: \n");
			e.printStackTrace();
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
	public List<DealDetails> findAllDeals() {
		try {
			logger.info("Start retrieving all deals details.");

			List<DealDetails> dealsList = new ArrayList<>();
			buildConnection();
			PreparedStatement statement = conn.prepareStatement(DaoConstants.RETRIEVE_DEALS_QUERY);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				DealDetails deal = new DealDetails(resultSet.getInt("DEAL_ID"),
						resultSet.getString("FROM_CURRENCY_ISO_CODE"), resultSet.getString("TO_CURRENCY_ISO_CODE"),
						resultSet.getTimestamp("DEAL_TIMESTAMP"), resultSet.getDouble("DEAL_AMOUNT"));
				dealsList.add(deal);
			}
			logger.debug("Finished retrieving all deals details.");
			return dealsList;
		} catch (Exception e) {
			logger.error("Failed to retrieve all deals details due: \n", e.getMessage());
			return null;
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
	public void delete(int id) {
		try {
			logger.info("Start deleting a deal details of id = " + id);

			buildConnection();
			PreparedStatement statement = conn.prepareStatement(DaoConstants.DELETE_DEAL_QUERY);
			statement.setInt(1, id);

			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted == 0) {
				logger.warn("Didn't find a Deal details of id = ", id + "");
			} else {
				logger.debug("Finished deleting a deal details of id = " + id);
			}

		} catch (SQLException e) {
			logger.error("Failed to delete a Deal details due: \n", e.getMessage());
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////
	public void close() {
		try {
			logger.info("Start closing the connection.");
			conn.close();
			logger.debug("Finished closing the connection.");

		} catch (SQLException e) {
			logger.error("Failed to close the connection due: \n", e.getMessage());
		}
	}
}
