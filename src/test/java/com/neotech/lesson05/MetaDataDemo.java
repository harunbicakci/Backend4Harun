package com.neotech.lesson05;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;



public class MetaDataDemo {

	public static String dbUserName = "user1";
	public static String dbPassword = "Neotech@123";

	public static String dbUrl = "jdbc:mysql://147.182.216.34:3306/classicmodels";
	
//	@Test
	public void test() throws SQLException {
		Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		DatabaseMetaData dbMetaData = conn.getMetaData();
	
		String driverName = dbMetaData.getDriverName();
		System.out.println(driverName);
		
		String driverVersion = dbMetaData.getDriverVersion();
		System.out.println(driverVersion);
		
		String dbName = dbMetaData.getDatabaseProductName();
		System.out.println(dbName);	
	}	
	
	@Test
	public void rsMetaData() throws SQLException {
		Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM employees WHERE employeeNumber = 1056");
		
		// Get the information about ResultSet
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int columnCount = rsMetaData.getColumnCount();
		System.out.println("columnCount --> " + columnCount);
		
		String columnName1 = rsMetaData.getColumnName(1);
		System.out.println("columnName1 --> " + columnName1);
		
		
		String columnName3 = rsMetaData.getColumnName(3);
		System.out.println("columnName3 --> " + columnName3);
		
		for(int i = 1; i <= columnCount; i++) {
			String columnType = rsMetaData.getColumnTypeName(i);
			System.out.println("columnType --> " + columnType);
			
		}
		
		
	}
	
}
