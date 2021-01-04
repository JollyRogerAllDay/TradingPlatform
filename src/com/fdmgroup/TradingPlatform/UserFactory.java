package com.fdmgroup.TradingPlatform;

/**
 * The user factory is in charge of creating all the new users. The specifications are passed through the
 * createUser method and the corresponding user classes are created.
 * @author Michael.Young
 *
 */

public class UserFactory {

	public User createUser(String username, String password, Permissions permission)
	{
		//additional info will be used with the decorator pattern to add additional functionality to each user created
		
		if(permission.equals(Permissions.SHAREHOLDER))
		{
			//system log new shareholder was created
			return new Shareholder(username, password,Permissions.SHAREHOLDER);
		}
		else if(permission.equals(Permissions.BROKER))
		{
			//system log new broker was created
			return new Broker(username, password,Permissions.BROKER);
		}
		else if(permission.equals(Permissions.BROKER_SHAREHOLDER))
		{
			//system log new broker was created
			return new Broker(username, password,Permissions.BROKER_SHAREHOLDER);
		}
		else if(permission.equals(Permissions.SYSADMIN))
		{
			//system log new system admin was created
			return new SystemAdmin(username, password,Permissions.SYSADMIN);
		}
		else if(permission.equals(Permissions.GUEST))
		{
			return new Guest(username, password, Permissions.GUEST);
		}
		return null;
	}
}
