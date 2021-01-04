package com.fdmgroup.TradingPlatform;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.TradingPlatform.Broker;
import com.fdmgroup.TradingPlatform.Permissions;
import com.fdmgroup.TradingPlatform.Shareholder;
import com.fdmgroup.TradingPlatform.User;
import com.fdmgroup.TradingPlatform.UserDatabaseStorage;

public class UserDatabaseStorageTest {

	UserDatabaseStorage UDBStorage;
	User user;
	User banUser;
	
	@Before
	public void init()
	{
		UDBStorage = new UserDatabaseStorage();
		user = new Shareholder("user","pass",Permissions.SHAREHOLDER);
	}
	
	@Test
	public void testCreateSuccess() {
		assertTrue(UDBStorage.create(user));
	}
	
	
	@Test
	public void testCreateSuccess2() {
		User user = new Shareholder("user2","pass2",Permissions.SHAREHOLDER);
		assertTrue(UDBStorage.create(user));
	}
	
	@Test
	public void testCreateBannedUser()
	{
		banUser = new Shareholder("banUser","banPass",Permissions.SHAREHOLDER);
		banUser.setBanned(true);
		assertTrue(UDBStorage.create(banUser));
	}
	
	@Test
	public void testReadSuccessNotBanned()
	{
		User userReturn = UDBStorage.read(user.getUsername());
		assertEquals("user",userReturn.getUsername());
		assertEquals("pass",userReturn.getPassword());
		assertFalse(userReturn.IsBanned());
		assertEquals(Permissions.SHAREHOLDER,userReturn.getPermissions());
	}
	
	@Test
	public void testReadSuccess()
	{
//		User user = new Shareholder("user3","pass3",Permissions.SHAREHOLDER);
//		UDBStorage.create(user);
		User userReturn = UDBStorage.read(user.getUsername());
		assertEquals("user",userReturn.getUsername());
		assertEquals("pass",userReturn.getPassword());
		assertFalse(userReturn.IsBanned());
		assertEquals(Permissions.SHAREHOLDER,userReturn.getPermissions());
	}
	
	@Test
	public void testReadSuccessBanned()
	{
		User banUser = new Shareholder("banUser","banPass",Permissions.SHAREHOLDER);
		banUser.setBanned(true);
		UDBStorage.create(banUser);
		User userReturn = UDBStorage.read(banUser.getUsername());
		assertEquals("banUser",userReturn.getUsername());
		assertEquals("banPass",userReturn.getPassword());
		assertTrue(userReturn.IsBanned());
		assertEquals(Permissions.SHAREHOLDER,userReturn.getPermissions());
	}
	
	@Test
	public void testReadSuccessFail()
	{
		User userDoesNotExist = new Shareholder("noUsername","noPass",Permissions.SHAREHOLDER);
		assertEquals(null,UDBStorage.read(userDoesNotExist.getUsername()));
	}
	
	@Test
	public void testDelete()
	{
		assertEquals(1,UDBStorage.delete(user.getUsername()));
	}
	
	@Test
	public void testUpdate()
	{
		User user = new Shareholder("userToUpdate","passToUpdate",Permissions.SHAREHOLDER);
		UDBStorage.create(user);
		
		User newUser = new Broker("broker","brokerpass",Permissions.BROKER);
		assertEquals(1,UDBStorage.update(user.getUsername(), newUser));
	}
	

	
	
//	@Test
//	public void testCreateFail() {
//		User user = new Broker("user","pass");
//		assertTrue(UDBStorage.create(user));
//	}

}
