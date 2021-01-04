package com.fdmgroup.TradingPlatform;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.TradingPlatform.Broker;
import com.fdmgroup.TradingPlatform.LoginManager;
import com.fdmgroup.TradingPlatform.Permissions;
import com.fdmgroup.TradingPlatform.Shareholder;
import com.fdmgroup.TradingPlatform.SystemAdmin;
import com.fdmgroup.TradingPlatform.User;

public class LoginManagerTest {

	LoginManager login;
	User user1,user2,user3,user4;
	UserDatabaseStorage uDBStorage;
	
	@Before
	public void init()
	{
		uDBStorage = new UserDatabaseStorage();
		login = LoginManager.getInstance();
		user1 = new Shareholder("shareUser","password1",Permissions.SHAREHOLDER);

		
		uDBStorage.create(user1);
	}
	
	
	@Test
	public void testCheckLogin()
	{
		assertEquals(0,login.login(user1.getUsername(), user1.getPassword()));
	}
	
	@Test
	public void testCheckLogout()
	{
		login.login(user1.getUsername(), user1.getPassword());
		assertTrue(login.logout(user1.getUsername()));
	}
	
	@Test
	public void testRegisterUser()
	{
		assertTrue(login.registerUser("testUser", "testPass", "BROKER"));
	}
	
	@After
	public void removeUsers()
	{
		uDBStorage.delete(user1.getUsername());
	}


}
