package com.fdmgroup.TradingPlatform;

import java.util.ArrayList;

public class UserManager {
	
	private static UserManager userManager;
	private IUserStorage userStorage;
	private IUserRequestStorage userRequestStorage;
	
	private UserManager()
	{
		userStorage = new UserDatabaseStorage();
		userRequestStorage = new UserRequestDatabaseStorage(); 
	}
	
	public static UserManager getInstance()
	{
		if(userManager==null)
		{
			userManager = new UserManager();
		}
		return userManager;
	}
	
	public boolean addUser(String username,String password, Permissions permissions)
	{
		UserFactory uf = new UserFactory();
		User user = uf.createUser(username, password, permissions);
		return userStorage.create(user);
	}
	
	public int deleteUser(String username)
	{
		return userStorage.delete(username);
	}
	
	public int updateDetails(String oldUser, User newUser)
	{
		return userStorage.update(oldUser, newUser);
	}
	
	public User readUser(String username)
	{
		return userStorage.read(username);
	}
	
	public int banUser(String username)
	{
		User user = userStorage.read(username);
		user.setBanned(true);
		return userStorage.update(username, user);
	}
	
	public ArrayList<User> getAllUsers()
	{
		return userStorage.readAll();
	}
	
	public ArrayList<UserRequest> getAllUserRequest()
	{
		return userRequestStorage.readAll();
	}
	
	public ArrayList<UserRequest> getUserRequest(String username)
	{
		return userRequestStorage.read(username);
	}
	
	public boolean addAddRequest(String username, String requestType)
	{
		UserRequest request = new UserRequest(UserRequestType.valueOf(requestType),RequestStatus.OUTSTANDING,username,"none");
		return userRequestStorage.create(request);
	}
	
	public int deleteRequest()
	{
		return 0;
	}
	
	public int updateRequest()
	{
		return 0;
	}
}
