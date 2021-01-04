package com.fdmgroup.TradingPlatform;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.TradingPlatform.Broker;
import com.fdmgroup.TradingPlatform.Permissions;
import com.fdmgroup.TradingPlatform.Trade;
import com.fdmgroup.TradingPlatform.TradeRequest;
import com.fdmgroup.TradingPlatform.TradeType;

public class TradeRequestTest {

	private Broker broker;
	private TradeRequest tradeRequests;
	private Trade testTrade;
	
	@Before
	public void init()
	{

		broker = new Broker("Bob","1234",Permissions.BROKER);
		testTrade = new Trade("GOOG",50.0,1,TradeType.BUY,"name",broker.getUsername());
		tradeRequests = TradeRequest.getInstance();
		tradeRequests.clearTradeTable();
	}
	
	@Test
	public void testSingletonInstance()
	{
		TradeRequest tradeRequests2 = TradeRequest.getInstance();
		assertEquals(tradeRequests2,tradeRequests);
	}
	
	@Test
	public void testAddAndGetTrade()
	{
		tradeRequests.addTrade(broker.getUsername(), testTrade);
		assertEquals(testTrade,tradeRequests.getTradeList(broker.getUsername()).get(0));
	}
	
	@Test
	public void testAddAndGetTrade_TwoBrokers()
	{
		Broker broker2 = new Broker("Steve","4321",Permissions.BROKER);
		Trade testTrade2 = new Trade("MSFT",100.0,1,TradeType.BUY,"name2",broker.getUsername());
		tradeRequests.addTrade(broker2.getUsername(), testTrade2);
		tradeRequests.addTrade(broker.getUsername(), testTrade);
		assertEquals(testTrade,tradeRequests.getTradeList(broker.getUsername()).get(0));
		assertEquals(testTrade2,tradeRequests.getTradeList(broker2.getUsername()).get(0));
	}
	
	@Test
	public void testBrokerDoesNotExist()
	{
		assertTrue(tradeRequests.getTradeList("NoBroker").isEmpty());
	}
	
}
