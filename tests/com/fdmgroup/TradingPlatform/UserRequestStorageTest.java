package com.fdmgroup.TradingPlatform;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.TradingPlatform.RequestStatus;
import com.fdmgroup.TradingPlatform.UserRequest;
import com.fdmgroup.TradingPlatform.UserRequestDatabaseStorage;
import com.fdmgroup.TradingPlatform.UserRequestType;

public class UserRequestStorageTest {

	UserRequestDatabaseStorage uRDBStorage;
	UserRequest request;
	
	@Before
	public void init()
	{
		uRDBStorage = new UserRequestDatabaseStorage();
		request = new UserRequest(UserRequestType.RESET_PASSWORD,RequestStatus.IN_PROGRESS,"Bob","admin");
	}
	
	@Test
	public void testCreate() {
		assertTrue(uRDBStorage.create(request));
	}
	
	@Test
	public void testRead()
	{
		UserRequest newRequest = new UserRequest(UserRequestType.RESET_PASSWORD,RequestStatus.IN_PROGRESS,"Bob","admin");
		uRDBStorage.create(newRequest);
		ArrayList<UserRequest> returnedList = uRDBStorage.read("Bob");
		assertEquals(UserRequestType.RESET_PASSWORD, returnedList.get(0).getRequestType());
		assertEquals(RequestStatus.IN_PROGRESS, returnedList.get(0).getStatus());
		assertEquals("Bob",returnedList.get(0).getUser());
		assertEquals("admin",returnedList.get(0).getAdmin());
	}
	
	@Test
	public void testUpdate()
	{
		UserRequest requestToUpdate = new UserRequest(UserRequestType.REPORT_BUG,RequestStatus.COMPLETED,"Steve","admin");
		UserRequest newRequest = new UserRequest(UserRequestType.RESET_PASSWORD,RequestStatus.IN_PROGRESS,"Steve","admin");
		uRDBStorage.create(requestToUpdate);
		assertEquals(1,uRDBStorage.update(requestToUpdate, newRequest));
	}
	
	@Test
	public void testDelete()
	{
		UserRequest requestToDelete = new UserRequest(UserRequestType.REPORT_BUG,RequestStatus.COMPLETED,"DeleteMe","admin");
		uRDBStorage.create(requestToDelete);
		assertEquals(1, uRDBStorage.delete(requestToDelete));
	}

}
