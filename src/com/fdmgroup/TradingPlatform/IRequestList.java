package com.fdmgroup.TradingPlatform;


/**
 * The user request list represents the collection of requests that a user sends to an admin to fulfill. An example
 * of an user request would be requesting a password reset, or perhaps a username change. 
 * The interface request list is simply a median between the admin and user, so that the user may send request while the given
 * admin is offline. 
 */
import java.util.ArrayList;

public interface IRequestList{
	
	public void addRequest(String adminUsername, UserRequest userRequest);
	public ArrayList<UserRequest> getRequestList(String adminUsername);
	public void clearAdminRequestList(String adminUsername);
	public void clearRequestTable();
	
}
