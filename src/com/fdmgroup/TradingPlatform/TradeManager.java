package com.fdmgroup.TradingPlatform;

import java.util.ArrayList;

public class TradeManager {
	private static TradeManager tradeManager;
	private TradeDatabaseStorage tradeStorage;
	
	private TradeManager()
	{
		tradeStorage = new TradeDatabaseStorage();
	}
	
	public static TradeManager getInstance()
	{
		if(tradeManager == null)
		{
			return new TradeManager();
		}
		
		return tradeManager;
	}
	
	public ArrayList<Trade> getTradeRequest(String brokerUsername)
	{
		return tradeStorage.readTradeRequest(brokerUsername);
	}
	
	public void addTradeRequest(Trade trade)
	{
		tradeStorage.create(trade);
	}
	
	public ArrayList<Trade> getMatchedTrades(String brokerUsername)
	{
		return tradeStorage.readMatchedTrades(brokerUsername);
	}
	
	public void matchTrades(Trade trade1, Trade trade2)
	{
		//update both trades in the trade table with each others trade information
	}
}
