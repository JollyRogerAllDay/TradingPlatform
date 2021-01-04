package com.fdmgroup.TradingPlatform;

public class UserRequest implements IUserRequest{

	private UserRequestType requestType;
	private RequestStatus status;
	private String username;
	private String adminUsername;
	
	public UserRequest(UserRequestType request, RequestStatus status, String username, String adminUsername)
	{
		this.requestType = request;
		this.status = status;
		this.username = username;
		this.adminUsername = adminUsername;
	}

	public void setStatus(RequestStatus status)
	{
		this.status = status;
	}
	
	@Override
	public UserRequestType getRequestType() {
		return requestType;
	}

	@Override
	public RequestStatus getStatus() {
		return status;
	}

	@Override
	public String getUser() {
		return username;
	}
	
	@Override
	public void setAdmin(String admin)
	{
		this.adminUsername = admin; 
	}
	
	@Override
	public String getAdmin()
	{
		return adminUsername;
	}
	
	@Override
	public String toString()
	{
		return username + " has request " + requestType.toString() + " for admin " + adminUsername;
	}
	

}



