package com.progresssoft.test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.progresssoft.models.DealsDetails;

public class TestMysql {
//	CREATE TABLE IF NOT EXISTS DEALS_DETAILS (
//			  DEAL_ID INT PRIMARY KEY,
//			  FROM_CURRENCY_ISO_CODE VARCHAR(3) NOT NULL,
//			  TO_CURRENCY_ISO_CODE VARCHAR(3) NOT NULL,
//			  DEAL_TIMESTAMP TIMESTAMP NOT NULL,
//			  DEAL_AMOUNT DECIMAL(20, 3) NOT NULL,
//			);
	static Connection conn;

	public static void main(String[] args) {

		try {
			InputStream inputStream = DealsDetails.class.getClassLoader().getResourceAsStream("config.properties");
			Properties prop = new Properties();
			prop.load(inputStream);

			String driver = prop.getProperty("jdbc.driver");
			String url = prop.getProperty("jdbc.url");
			String user = prop.getProperty("jdbc.username");
			String password = prop.getProperty("jdbc.password");

			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
