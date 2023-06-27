package com.nprcet.login;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbDetails {
	private static final String DB_URL = "dbUrl";
	private static final String DB_USERNAME = "dbUserName";
	private static final String DB_PASSWORD = "dbPassword";
	private static final String DB_PROPERTIES_FILE_LOCATION = "C:\\Users\\jeanmartin\\eclipse-workspace\\NPRCET\\DbConnection properties\\DbDetails.properties";

	public static Connection getConnection() {
		Connection connectionObj = null;
		try {
			
		Properties propertyObj = new Properties();
			propertyObj.load(new FileInputStream(DB_PROPERTIES_FILE_LOCATION));
			connectionObj = (Connection) DriverManager.getConnection(propertyObj.getProperty(DB_URL),
					propertyObj.getProperty(DB_USERNAME), propertyObj.getProperty(DB_PASSWORD));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connectionObj;
	}

}

	

