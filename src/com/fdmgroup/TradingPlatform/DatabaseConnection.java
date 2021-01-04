package com.fdmgroup.TradingPlatform;


public abstract class DatabaseConnection {

	public static String getDatabase()
	{
		return "jdbc:oracle:thin:username/password@oracle.fdmgroup.com:1521:xe";
	}
	
	public static String getUsername()
	{
		return "michaelyoung";
	}
	
	public static String getPassword()
	{
		return "Oracle101";
	}
}
