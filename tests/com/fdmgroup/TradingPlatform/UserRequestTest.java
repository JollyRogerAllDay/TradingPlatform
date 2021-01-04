package com.fdmgroup.TradingPlatform;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.TradingPlatform.Permissions;
import com.fdmgroup.TradingPlatform.RequestStatus;
import com.fdmgroup.TradingPlatform.Shareholder;
import com.fdmgroup.TradingPlatform.SystemAdmin;
import com.fdmgroup.TradingPlatform.UserRequest;
import com.fdmgroup.TradingPlatform.UserRequestType;

public class UserRequestTest {

	private UserRequest aRequest;
	private Shareholder aUser;
	private SystemAdmin admin;
	
	@Before
	public void init()
	{
		aUser = new Shareholder ("Bob","1234",Permissions.SHAREHOLDER);
		aRequest = new UserRequest(UserRequestType.RESET_PASSWORD,RequestStatus.IN_PROGRESS, aUser.getUsername(),"admin");
	}
	
	@Test
	public void testGettersAndSetters() {
		assertEquals(UserRequestType.RESET_PASSWORD,aRequest.getRequestType());
		assertEquals(RequestStatus.IN_PROGRESS,aRequest.getStatus());
		assertEquals(aUser.getUsername(),aRequest.getUser());
	}
	
	@Test
	public void testSetStatus()
	{
		aRequest.setStatus(RequestStatus.COMPLETED);
		assertEquals(RequestStatus.COMPLETED,aRequest.getStatus());
	}
	
	@Test
	public void testGetUser()
	{
		assertEquals(aUser.getUsername(),aRequest.getUser());
	}
	
	@Test
	public void testGetAdmin()
	{
		aRequest.setAdmin("admin");
		assertEquals("admin",aRequest.getAdmin());
	}

}
