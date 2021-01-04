package com.fdmgroup.TradingPlatform;

import java.util.Date;

public class UserProfile implements IUserProfile{
	
	private String name;
	private int age;
	private Date birthday;
	
	public UserProfile(String name, int age, Date birthday)
	{
		this.name = name;
		this.age = age;
		this.birthday = birthday;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getAge()
	{
		return age;
	}
	
	public Date getBirthday()
	{
		return birthday;
	}
	
	public void setName(String newName)
	{
		this.name = newName;
	}
	
	public void setAge(int newAge)
	{
		this.age = newAge;
	}

	@Override
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
