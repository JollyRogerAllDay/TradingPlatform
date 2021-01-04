package com.fdmgroup.TradingPlatform;

import java.util.ArrayList;

public interface IUserRequestStorage {
	public boolean create(UserRequest request);
	public ArrayList<UserRequest> read(String username); 
	public int update (UserRequest oldRecord, UserRequest newRecord); 
	public int delete(UserRequest request);
	public ArrayList<UserRequest> readAll();
}
