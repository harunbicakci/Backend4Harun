package com.neotech.lesson05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCDemo1 {

	public static String dbUserName = "user1";
	public static String dbPassword = "Neotech@123";

	// jdbc:jdbctype://ipaddress:portnumber/db_name
	public static String dbUrl = "jdbc:mysql://147.182.216.34:3306/LibraryMgmt";
	
	public static void main(String[] args) throws SQLException {
		
	
			// HostName: 147.182.216.34
			// Port: 3306
			// DB Name: LibraryMgmt
			// UserName: user1
			// Password: Neotech@123
	
		Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		System.out.println("Database connetion is successful!!!");
		
		// Creating a Statement
		Statement st = conn.createStatement();
		
		// Executinh a Query
		ResultSet rs = st.executeQuery("SELECT * FROM book");
		System.out.println("---------------------");		
				
		// Getting values from the ResultSet
		
		// Move to the first row
		rs.next();
		
		// Get the Bookname from the first row
		String bookName1 = rs.getString("BookName");
		System.out.println(bookName1);
		
		// Move to the next row
		rs.next();
		
		String bookName2 = rs.getString(2);
		System.out.println(bookName2);
				
		// moving to next row
		rs.next();
		String bookName3 = rs.getObject("BookName").toString();		
		System.out.println(bookName3);
		
		List<String> list = new ArrayList<>();
		
		while(rs.next()) {
			
			String bookName = rs.getString("BookCategoryName").toString();
		//	System.out.println(bookName);
		
			list.add(bookName);
		}
		
		System.out.println(list);
		
		// Closing the connection and other objects
		rs.close();
		st.close();
		conn.close();
		
		
	}
}
