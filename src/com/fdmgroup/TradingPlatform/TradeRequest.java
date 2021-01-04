package com.fdmgroup.TradingPlatform;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Trade request represents the a list of trades that a broker has from various shareholders. When a shareholder wants to buy
 * or sell shares, a trade will be added to the trade request queue for the broker to access and add to their buy
 * or sell lists. Because the broker may not be online at the time, the trade request queue needs to exist on its own so
 * shareholders still have the options of sending trades, and wait for the time until the broker is able to come online and 
 * fulfill those trades.  
 * @author Michael.Young
 *
 */

public class TradeRequest implements ITradeRequest{
	
	private static HashMap<String,ArrayList<Trade>> tradeLists;
	private static TradeRequest tradeRequest;
	
	private TradeRequest()
	{
		tradeLists = new HashMap<String,ArrayList<Trade>>();
	}
	
	public static TradeRequest getInstance()
	{
		if(tradeRequest == null)
		{
			tradeRequest = new TradeRequest();
		}
		
		return tradeRequest;
	}
	
	@Override
	public void addTrade(String brokerUsername, Trade trade) 
	{
		if(tradeLists.containsKey(brokerUsername))
		{
			tradeLists.get(brokerUsername).add(trade);
		}
		else
		{
			tradeLists.put(brokerUsername, new ArrayList<Trade>());
			tradeLists.get(brokerUsername).add(trade);
		}
	}
	
	@Override
	public ArrayList<Trade> getTradeList(String brokerUsername) 
	{
		if(tradeLists.containsKey(brokerUsername))
		{
			return tradeLists.get(brokerUsername);
		}
		return new ArrayList<Trade>();
	}
	
	/**
	 * Accepts a trade and searchs through the hashmap to find the corresponding broker
	 * @param trade
	 * @return
	 */
	public String getBrokerUsername(Trade trade)
	{
		for(String broker: tradeLists.keySet())
		{
			tradeLists.get(broker).contains(trade);
			return broker;
		}
		return null;
	}
	
	@Override
	public void clearBrokerTradeList(String brokerUsername)
	{
		tradeLists.put(brokerUsername,new ArrayList<Trade>());
	}
	
	@Override 
	public void clearTradeTable()
	{	
		tradeLists = new HashMap<String,ArrayList<Trade>>();
	}
}
