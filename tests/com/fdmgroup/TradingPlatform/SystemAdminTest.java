package com.fdmgroup.TradingPlatform;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.TradingPlatform.Permissions;
import com.fdmgroup.TradingPlatform.RequestStatus;
import com.fdmgroup.TradingPlatform.Shareholder;
import com.fdmgroup.TradingPlatform.SystemAdmin;
import com.fdmgroup.TradingPlatform.User;
import com.fdmgroup.TradingPlatform.UserRequest;
import com.fdmgroup.TradingPlatform.UserRequestList;
import com.fdmgroup.TradingPlatform.UserRequestType;

public class SystemAdminTest {
	
	private SystemAdmin sysAdmin;
	
	@Before
	public void init()
	{
		sysAdmin = new SystemAdmin("admin","admin",Permissions.SYSADMIN);
	}
	
	@Test
	public void testGettersAndSetters()
	{
		assertEquals("admin",sysAdmin.getUsername());
		assertEquals("admin",sysAdmin.getPassword());
	}
	
	@Test
	public void testAddUser() {
		User shareholder = new Shareholder("Bob","1234",Permissions.SHAREHOLDER);
		
		sysAdmin.addUser(shareholder.getUsername(),shareholder.getPassword(),shareholder.getPermissions());
		
	}
	
	@Test
	public void testChangeStatusOfUserRequest()
	{
		RequestStatus newStatus = RequestStatus.COMPLETED;
		Shareholder user = new Shareholder("aUser","43234",Permissions.SHAREHOLDER);
		UserRequest userReq = new UserRequest(UserRequestType.RESET_PASSWORD,RequestStatus.IN_PROGRESS,user.getUsername(),sysAdmin.getUsername());
		UserRequestList.getInstance().addRequest(sysAdmin.getUsername(), userReq);
		sysAdmin.changeStatusRequest(newStatus, userReq);
		assertEquals(RequestStatus.COMPLETED,UserRequestList.getInstance().getRequestList(sysAdmin.getUsername()).get(0).getStatus());
	}
	

}
