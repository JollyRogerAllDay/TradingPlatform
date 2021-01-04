package com.fdmgroup.TradingPlatform;

import java.util.ArrayList;

public interface ITradeRequest {
	public void addTrade(String brokerUsername, Trade trade);
	public ArrayList<Trade> getTradeList(String brokerUsername);
	public void clearBrokerTradeList(String brokerUsername);
	public void clearTradeTable();
}

