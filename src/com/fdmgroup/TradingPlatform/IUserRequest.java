package com.fdmgroup.TradingPlatform;

/**
 * User request interface. A user request is a message that is sent from a user to an admin for a specific reason.
 * These reasons can include changing a password, altering information, or any other request the user might have.
 * @author Michael.Young
 *
 */

public interface IUserRequest {
	public UserRequestType getRequestType();
	public RequestStatus getStatus();
	public String getUser();
	public String getAdmin();
	public void setStatus(RequestStatus status);
	public void setAdmin(String admin);
}
