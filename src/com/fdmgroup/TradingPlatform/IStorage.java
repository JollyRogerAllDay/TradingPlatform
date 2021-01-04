package com.fdmgroup.TradingPlatform;

import java.util.ArrayList;

public interface IStorage<T> {
	public boolean create(T record);
	public ArrayList<T> read(String key); 
	public int update (T oldRecord, T newRecord); 
	public int delete(T record);
}
