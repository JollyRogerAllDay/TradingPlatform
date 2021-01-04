package com.fdmgroup.TradingPlatform;

import java.util.ArrayList;

public interface ITradeStorage {
	public boolean create(Trade trade);
	public ArrayList<Trade> read(String brokerUsername); 
	public int update (Trade oldRecord, String brokerUsername,Trade newRecord); 
	public int delete(Trade trade);
}
