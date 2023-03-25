package com.neotech.lesson05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

public class Homework {

//        Connect to classicmodels database
//        Execute a query to get all information of customer with id 124
//        Get the resultset metadata
//        Print the number of columns
//        Get all the column names and store them in an arraylist
//        Print the Arraylist

	public static String dbUserName = "user1";
	public static String dbPassword = "Neotech@123";
	public static String dbUrl = "jdbc:mysql://147.182.216.34:3306/classicmodels";

	@Test
	public void test() throws SQLException {

		Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		System.out.println("Database connection successful!!!");
		Statement st = conn.createStatement();
		
		ResultSet rs = st.executeQuery("SELECT * FROM customers WHERE customerNumber = 124;");

		ResultSetMetaData rsMetaData = rs.getMetaData();
		int columnCount = rsMetaData.getColumnCount();
		System.out.println("\ncolumnCount --> " + columnCount);

		List<String> columnNames = new ArrayList<>();
		Map<String, String> maps = new HashMap<>();

		for (int i = 1; i <= columnCount; i++) {

	//		maps.put(rsMetaData.getColumnName(i), rs.next());

		}
//		System.out.println("\nColumn Names are --> " + columnNames);
		System.out.println("\nMaps are --> " + maps);
		
		rs.close();
		st.close();
		conn.close();
	}
}