package com.fdmgroup.TradingPlatform;

import java.util.Date;

/**
 * User profile interface represents additional information about the user, such as the user's real name,
 * age, and birthday.  
 * @author Michael.Young
 *
 */
public interface IUserProfile {
	public String getName();
	public int getAge();
	public Date getBirthday();
	
	public void setName(String name);
	public void setAge(int age);
	public void setBirthday(Date birthday);
}
