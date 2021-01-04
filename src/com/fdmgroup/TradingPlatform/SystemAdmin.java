package com.fdmgroup.TradingPlatform;

import java.util.ArrayList;


/**
 * The system admin class. The system admin class has total control over the trading platform. They have the ability 
 * to add, remove, ban and update user details. 
 * System admins also deal with user requests. Updating the status on requests from outstanding, in progress and completed.
 * User request are also assigned an admin. Seeing as how there can be multiple system admins, the work load for user requests should
 * be evenly distributed among the admins. 
 * @author Michael.Young
 *
 */

public class SystemAdmin extends User{

	private Log log;
	private UserManager userManager;
	private ArrayList<UserRequest> userRequests;
	
	public SystemAdmin(String username,String password, Permissions perm)
	{
		super(username,password);
		super.setPermission(perm);
		log = new Log(this);
		userManager = UserManager.getInstance();
		log.logTrace(this.toString() + " was created.");
		userRequests = new ArrayList<UserRequest>();
	}
	
	@Override
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}
	
	@Override
	public void createProfile() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Add the given user to the user list stored in the database.
	 * @param user
	 */
	public boolean addUser(String username, String password, Permissions permissions)
	{
		if(userManager.addUser(username, password, permissions))
		{
			log.logInfo(username.toString() + " was added to the user list.");
			return true;
		}
		else
		{
			log.logInfo(username.toString() + " faied to be added to the user list.");
			return false;
		}
	}
	
	/** 
	 * Delete the given user from the database.
	 * @param user
	 */
	public int deleteUser(String username)
	{
		if (userManager.deleteUser(username)==1)
		{
			log.logInfo(username + " was deleted to the user list.");
			return 1;
		}
		else
		{
			log.logInfo(username + " failed to be deleted from the user list.");
			return 0;
		}
	}
	
	
	/**
	 * Update the current user details.
	 * @param user
	 */
	public int updateDetails(String oldUser, User newUser)
	{
		if(userManager.updateDetails(oldUser, newUser)==1)
		{
			log.logInfo(this.getUsername() + " updated details for " + oldUser);
			return 1;
		}
		else
		{
			log.logInfo(this.getUsername() + " failed to update details for " + oldUser);
			return 0;
		}
		
	}
	
	/**
	 * Ban the given user.
	 * @param user
	 */
	public int banUser(String username)
	{
		if(userManager.banUser(username)==1)
		{
			log.logInfo(username + " was banned.");
			return 1;
		}
		else
		{
			log.logInfo(username + " failed to be banned.");
			return 0;
		}
		
	}
	
	public ArrayList<UserRequest> viewUserRequest(String username)
	{
		userRequests = userManager.getUserRequest(username);
		return userRequests;
	}
	
	public ArrayList<UserRequest> viewUserRequest()
	{
		userRequests = userManager.getAllUserRequest();
		return userRequests;
	}
	
	public void changeStatusRequest(RequestStatus newStatus,UserRequest request)
	{
		UserRequest newRequest = request;
		newRequest.setStatus(newStatus);
	}
	
	public void assignAdmin(String newAdmin, UserRequest request)
	{
		//log assignAdmin to request
		//iterate through user requests?
	}
	
	public void viewSystemLogs()
	{
		
	}
	
	public void viewUserLogs()
	{
		//load log file and display
	}
	
	
	public void login()
	{
		log.logInfo(this.toString()+" logged on.");
	}
	
	public void logoff()
	{
		log.logInfo(this.toString()+" logged off.");
	}

	@Override
	public String toString() {
		return "system admin- " + super.getUsername();
	}
}
