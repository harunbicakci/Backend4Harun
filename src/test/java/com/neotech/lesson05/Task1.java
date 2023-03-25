package com.neotech.lesson05;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Task1 {

	public static String dbUserName = "user1";
	public static String dbPassword = "Neotech@123";

	// jdbc:jdbctype://ipaddress:portnumber/db_name
	public static String dbUrl = "jdbc:mysql://147.182.216.34:3306/LibraryMgmt";
	
	public static void main(String[] args) throws SQLException {
		
		Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		System.out.println("Database connetion is successful!!!");
		
		Statement st = conn.createStatement();
		
		ResultSet rs = st.executeQuery("SELECT * FROM bookcategory");
		
		List<String> list = new ArrayList<>();
		
		while(rs.next()) {	
			String bookName = rs.getString("BookCategoryName").toString();	
			list.add(bookName);
		}
		
		System.out.println(list);
		
		rs.close();
		st.close();
		conn.close();
		
		
	}
}

	

