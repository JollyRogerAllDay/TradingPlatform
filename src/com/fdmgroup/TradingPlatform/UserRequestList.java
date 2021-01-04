package com.fdmgroup.TradingPlatform;

import java.util.ArrayList;
import java.util.HashMap;

public class UserRequestList implements IRequestList{
	
	private static HashMap<String,ArrayList<UserRequest>> userRequestList;
	private static UserRequestList requestList;
	
	private UserRequestList()
	{
		userRequestList = new HashMap<String,ArrayList<UserRequest>>();
	}
	
	public static UserRequestList getInstance()
	{
		if(requestList == null)
		{
			requestList = new UserRequestList();
		}
		return requestList;
	}

	@Override
	public void addRequest(String adminUsername, UserRequest userRequest) 
	{
		if(userRequestList.containsKey(adminUsername))
		{
			userRequestList.get(adminUsername).add(userRequest);
		}
		else
		{
			userRequestList.put(adminUsername, new ArrayList<UserRequest>());
			userRequestList.get(adminUsername).add(userRequest);
		}
	}

	@Override
	public ArrayList<UserRequest> getRequestList(String adminUsername) 
	{
		if(userRequestList.containsKey(adminUsername))
		{
			return userRequestList.get(adminUsername);
		}
		return new ArrayList<UserRequest>();
	}

	@Override
	public void clearAdminRequestList(String adminUsername) 
	{
		userRequestList.put(adminUsername, new ArrayList<UserRequest>());
	}

	@Override
	public void clearRequestTable() 
	{
		userRequestList = new HashMap<String,ArrayList<UserRequest>>();
	}
	

}
