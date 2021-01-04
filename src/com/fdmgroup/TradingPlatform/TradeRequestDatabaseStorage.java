package com.fdmgroup.TradingPlatform;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class TradeRequestDatabaseStorage implements ITradeStorage{
	
	public boolean create(Trade trade)
	{
		return false;
	}
	
	public ArrayList<Trade> read(String brokerUsername)
	{
		return new ArrayList<Trade>();
	}
	
	public int update (Trade oldRecord, String brokerUsername,Trade newRecord)
	{
		return 0;
	}
	
	public int delete(Trade trade)
	{
		return 0;
	}
}
