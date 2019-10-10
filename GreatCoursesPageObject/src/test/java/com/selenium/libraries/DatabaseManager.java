package com.selenium.libraries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class DatabaseManager {
	final static Logger logger = Logger.getLogger(DatabaseManager.class);

	private String databaseServerName;
	private String databasePort;
	private String databaseName;
	private String userName;
	private String userPassword;

	private String connectionURL = null;
	private ResultSet resultSet = null;
	private Statement statement = null;
	private Connection connection = null;

	private void connectToOracleDB() {
		databaseServerName = "localhost";
		databasePort = "1521";
		databaseName = "xe";
		userName = "hr";
		userPassword = "hr";

		try {
			connectionURL = "jdbc:oracle:thin:hr@//" + databaseServerName + ":" + databasePort + "/" + databaseName;
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection(connectionURL, userName, userPassword);
			statement = connection.createStatement();

		} catch (Exception e) {
			logger.error("Error: ", e);
		}
	}

	public ResultSet runSQLQuery(String sqlQuery){
		try{
			connectToOracleDB();
			resultSet = statement.executeQuery(sqlQuery);
		}catch(Exception e){
			logger.error("Error: ", e);
		}
		return resultSet;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws Exception {
		DatabaseManager dbm = new DatabaseManager();
		dbm.connectToOracleDB();
		ResultSet resultSet = dbm.runSQLQuery("Select * From Countries");
		
		System.out.println("countryID " + "\t" + "countryName " + "\t" + "regionID");
		
		while(resultSet.next()){
			String countryID = resultSet.getString("COUNTRY_ID");
			String countryName = resultSet.getString("COUNTRY_NAME");
			int regionID = resultSet.getInt("REGION_ID");
			
			System.out.println(countryID + " \t" + countryName + " \t" + regionID);
		}
		
		
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
