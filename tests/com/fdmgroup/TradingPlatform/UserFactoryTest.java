package com.fdmgroup.TradingPlatform;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.TradingPlatform.Broker;
import com.fdmgroup.TradingPlatform.Permissions;
import com.fdmgroup.TradingPlatform.Shareholder;
import com.fdmgroup.TradingPlatform.SystemAdmin;
import com.fdmgroup.TradingPlatform.User;
import com.fdmgroup.TradingPlatform.UserFactory;

public class UserFactoryTest {

	UserFactory userFactory;
	
	@Before
	public void init()
	{
		userFactory = new UserFactory();
	}
	
	@Test
	public void testCreateShareholder() 
	{
		Shareholder newShareholder = (Shareholder)userFactory.createUser("bob7","1234", Permissions.SHAREHOLDER);
		assertEquals("bob7",newShareholder.getUsername());
		assertEquals("1234",newShareholder.getPassword());
	}
	
	@Test
	public void testCreateBroker()
	{
		Broker newBroker = (Broker)userFactory.createUser("joe5","1234", Permissions.BROKER);
		assertEquals("joe5",newBroker.getUsername());
		assertEquals("1234",newBroker.getPassword());
	}
	
	@Test
	public void testCreateSystemAdmin()
	{
		SystemAdmin newSysAdmin = (SystemAdmin)userFactory.createUser("jerry","1234",Permissions.SYSADMIN);
		assertEquals("jerry",newSysAdmin.getUsername());
		assertEquals("1234",newSysAdmin.getPassword());
	}
	
	@Test
	public void testCreateFalseUser()
	{
		User nullUser = userFactory.createUser("noname","nopass", Permissions.BROKER_SHAREHOLDER);
		assertEquals(null,nullUser);
	}

}
