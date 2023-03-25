package com.neotech.lesson06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;

public class Homework01 {

	
	/*
	 * Connect to classicmodels database 
	 * Execute a query to get all information of
	 * customer with id 124 
	 * Get the resultset metadata 
	 * Print the number of columns
	 * Get all the column names and store them in an arraylist 
	 * Print the Arraylist
	 */
	
	
	//what do I need to connect to a db?
	//1. DB url fomat
	//2. username and password
	
	//DB URL fomat: 
	//jdbc: jdbc Type // ipAddress:portNumber/db_name
	// jdbc:mysql://147.182.216.34:3306/classicmodels
	public static String dbUrl = "jdbc:mysql://147.182.216.34:3306/classicmodels";
	
	
	public static String dbUserName = "user1";
	public static String dbPassword = "Neotech@123";
	
	@Test
	public void getResults() throws SQLException
	{
		//create a connection to the db
		Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
	
		//we need to create a statement -- a question to ask to the DB
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT CONCAT(contactFirstName, ' ', contactLastName) as FullName, "
				+ "phone, state FROM customers WHERE customerNumber < 124;");
		
		
		//get the information about the structure of the result set
		
		ResultSetMetaData rsMetaData = rs.getMetaData();
		
		//get number of cols
		int colCount = rsMetaData.getColumnCount();
		
		String firstColName = rsMetaData.getColumnName(1);
		System.out.println("The first column name is: " + firstColName);
		
		//get all col names
		List<String> ls = new ArrayList<>();
		for (int i = 1; i <= colCount; i++)
		{
			ls.add(rsMetaData.getColumnName(i));
			//in two lines
//			String colName = rsMetaData.getColumnName(i);
//			ls.add(colName);
		}
		System.out.println(ls);
		
		//how do we read data
	//	rs.next(); //this moves the cursor to the first row
	//	String fullName = rs.getString("FullName");
	//	String phone = rs.getString("phone");
	//	System.out.println(fullName + " "+ phone);
			
		while(rs.next())
		{
			String fullName = rs.getString("FullName");
			String phone = rs.getString(ls.get(1));
			String state = rs.getString(3);
			
			System.out.println(fullName + " " + phone + " " + state);
		}
		
		
		
		//no error is thrown -- just warning
		rs.close();
		st.close();
		conn.close();
		
	}
	
}
