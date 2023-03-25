package com.neotech.lesson06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.testng.annotations.Test;
import com.neotech.utils.ConfigsReader;
import com.neotech.utils.Constants;

public class StoringData {	
	
	@Test
	public void getAndStoreData() throws SQLException
	{
		//reading properties so that we can use getProperty()
		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);
		
		Connection conn = DriverManager.getConnection(
				ConfigsReader.getProperty("dbUrl"), 
				ConfigsReader.getProperty("dbUserName"), 
				ConfigsReader.getProperty("dbPassword"));
				
		Statement st = conn.createStatement();
		
		ResultSet rs = st.executeQuery("SELECT employeeNumber, lastName, "
				+ "firstName, email FROM employees LIMIT 5");
		
		
		List<Map<String, String>> listOfMaps = new ArrayList<>();
		Map<String, String> map;
		
		while(rs.next())
		{
			//create a new map for every record and fill the information, then add it to 
			//the list
			map = new LinkedHashMap<>();
			
			map.put("Employee Number", rs.getString("employeeNumber"));
			map.put("Last Name", rs.getString("lastName"));
			map.put("First Name", rs.getString("firstName"));
			map.put("Email", rs.getString("email"));
			
			listOfMaps.add(map);
		}
		
		System.out.println(listOfMaps);
		
		rs.close();
		st.close();
		conn.close();
		
	}
	
	
	@Test
	public void getAndStoreDataEnhanced() throws SQLException
	{
		//reading properties so that we can use getProperty()
		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);
		
		Connection conn = DriverManager.getConnection(
				ConfigsReader.getProperty("dbUrl"), 
				ConfigsReader.getProperty("dbUserName"), 
				ConfigsReader.getProperty("dbPassword"));

		Statement st = conn.createStatement();
		
		int cNo = 124;
		ResultSet rs = st.executeQuery("SELECT customerNumber, contactFirstName, contactLastName, "
				+ " creditLimit FROM customers WHERE customerNumber < " + cNo);
		
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int colCount = rsMetaData.getColumnCount();
		
		
		List<Map<String, String>> listOfMaps = new ArrayList<>();
		Map<String, String> map;
		
		//iterate on rows
		while(rs.next())
		{
			//initialize a new map
			map = new LinkedHashMap<>();
			//fill the map using the MetaData Labels
			for ( int i = 1; i <= colCount;i++)
			{
				map.put(rsMetaData.getColumnName(i), rs.getString(i));
			}
			
			//put this map to the list
			listOfMaps.add(map);
		}
		
		
		System.out.println(listOfMaps);
		
		rs.close();
		st.close();
		conn.close();
	}
	
}
