package com.fdmgroup.TradingPlatform;

import java.util.ArrayList;

/*
 * DML
 * Create - creates a new record in the table
 * Read - get information from database
 * 		Return type - T(generic), Collection<T>
 * Update - update(T old, T new) or update(criteria String [] fields / String [] values /Pairs[] changes or Map<fields,changes>)
 * Delete - 
 */

public interface IUserStorage {
	public boolean create(User user); //1-1 field -var ; store one per run
	public User read(String criteria); //return one of type T; id info
	public int update (String oldRecord, User newRecord); //current item, fields to modify, new values
	public int delete(String username);
	public ArrayList<User> readAll();
}
