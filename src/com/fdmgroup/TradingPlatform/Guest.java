package com.fdmgroup.TradingPlatform;

public class Guest extends User{
	
	public Guest(String username, String password,Permissions perm)
	{
		super(username,password);
		super.setPermission(perm);
	}
	
	@Override
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public void createProfile() {
		// TODO Auto-generated method stub
		
	}
	
	public void login()
	{
		
	}
	
	public void logoff()
	{
		
	}

	@Override
	public String toString() {
		return "guest " + super.getUsername();
	}
	
}
