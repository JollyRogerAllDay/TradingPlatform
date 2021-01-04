package com.fdmgroup.TradingPlatform;

public abstract class User {

	private String username;
	private String password;
	private Permissions permission;
	private boolean banned;
	
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
		banned = false;
	}
	
	public String getUsername()
	{
		return this.username;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	public void setPermission(Permissions perm)
	{
		this.permission = perm;
	}
	
	public Permissions getPermissions()
	{
		return this.permission;
	}
	
	public void setBanned(boolean value)
	{
		banned = value;
	}
	
	public void setBanned(int value)
	{
		if(value == 1)
		{
			banned = true;
		}
		else
		{
			banned = false;
		}
	}
	
	public boolean IsBanned()
	{
		return banned;
	}
	
	public abstract void createProfile();
	
	public abstract String toString();
	
	public abstract void login();
	public abstract void logoff();
}

