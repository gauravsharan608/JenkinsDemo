package com.sdet.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil 
{
	private static Connection con;
	public static Connection getConnection() 
	{	
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/diagnostic_db","root","Welcome123");
			
		}catch(Exception e){ System.out.println(e);} 
		 
		return con;
	
	}
	public static void releaseResource(Object object) 
	{
		try 
		{
			con.close();
		} catch(Exception e){ System.out.println(e);} 
	}

}
