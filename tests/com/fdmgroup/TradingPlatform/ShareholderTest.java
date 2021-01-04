package com.fdmgroup.TradingPlatform;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.TradingPlatform.Broker;
import com.fdmgroup.TradingPlatform.Permissions;
import com.fdmgroup.TradingPlatform.Shareholder;
import com.fdmgroup.TradingPlatform.Trade;
import com.fdmgroup.TradingPlatform.TradeRequest;
import com.fdmgroup.TradingPlatform.TradeType;

public class ShareholderTest {

	Shareholder shareholder;
	TradeRequest tradeRequest;
	@Before
	public void init()
	{
		shareholder = new Shareholder("Mary","4321",Permissions.SHAREHOLDER);
		tradeRequest = TradeRequest.getInstance();
	}
	
	@Test
	public void testGetterAndSetter()
	{
		Broker broker = new Broker("Bob","4312",Permissions.BROKER);
		shareholder.addToBrokers(broker.getUsername());
		assertEquals("Mary",shareholder.getUsername());
		assertEquals("4321",shareholder.getPassword());
		assertEquals(broker,shareholder.getBrokers().get(0));
	}
	
	@Test
	public void testAddShareTPortfolio() {
		shareholder.addToPortfolio("GOOG",2);
		assertTrue(shareholder.getPortfolio().shareExists("GOOG"));
	}
	
	@Test
	public void testRequestSellShare()
	{
		Broker broker = new Broker("Bob","4312",Permissions.BROKER);
		tradeRequest.clearTradeTable();
		shareholder.addToPortfolio("MSFT", 5);
		shareholder.requestSellShare(broker.getUsername(), "MSFT",50.0,5,TradeType.SELL);
		assertEquals("MSFT",tradeRequest.getTradeList(broker.getUsername()).get(0).getTicker());
		assertEquals(50.0,tradeRequest.getTradeList(broker.getUsername()).get(0).getPrice(),0.0);
		assertEquals(5,tradeRequest.getTradeList(broker.getUsername()).get(0).getQuantity());
		assertFalse(shareholder.getPortfolio().shareExists("MSFT"));
	}
	
	@Test
	public void testRequestSellShare_Fail_BuyType()
	{
		Shareholder shareholder = new Shareholder("Mary","4321",Permissions.SHAREHOLDER);
		Broker broker = new Broker("Bob","4312",Permissions.BROKER);
		tradeRequest.clearTradeTable();
		shareholder.addToPortfolio("MSFT", 5);
		shareholder.requestSellShare(broker.getUsername(), "MSFT",50.0,5,TradeType.BUY);
		assertTrue(shareholder.getPortfolio().shareExists("MSFT"));
	}
	
	@Test
	public void testRequestSellShare_Fail_NotEnoughShares()
	{
		Shareholder shareholder = new Shareholder("Mary","4321",Permissions.SHAREHOLDER);
		Broker broker = new Broker("Bob","4312",Permissions.BROKER);
		tradeRequest.clearTradeTable();
		shareholder.addToPortfolio("MSFT", 2);
		shareholder.requestSellShare(broker.getUsername(), "MSFT",50.0,5,TradeType.SELL);
		assertTrue(shareholder.getPortfolio().shareExists("MSFT"));
		assertEquals(2, shareholder.getPortfolio().getNumOfShares("MSFT"),0.0);
	}
	
	@Test
	public void testRequestSellShare_Fail_Share()
	{
		Shareholder shareholder = new Shareholder("Mary","4321",Permissions.SHAREHOLDER);
		Broker broker = new Broker("Bob","4312",Permissions.BROKER);
		tradeRequest.clearTradeTable();
		shareholder.addToPortfolio("MSFT", 5);
		shareholder.requestSellShare(broker.getUsername(), "MSFT",50.0,5,TradeType.BUY);
		assertTrue(shareholder.getPortfolio().shareExists("MSFT"));
	}

	@Test
	public void testRequestBuyShare()
	{
		Shareholder shareholder = new Shareholder("Mary","4321",Permissions.SHAREHOLDER);
		Broker broker = new Broker("Bob","4312",Permissions.BROKER);
		tradeRequest.clearTradeTable();
		shareholder.requestBuyShare(broker.getUsername(), "MSFT",50.0,5,TradeType.BUY);
		assertEquals("MSFT",tradeRequest.getTradeList(broker.getUsername()).get(0).getTicker());
		assertEquals(50.0,tradeRequest.getTradeList(broker.getUsername()).get(0).getPrice(),0.0);
		assertEquals(5,tradeRequest.getTradeList(broker.getUsername()).get(0).getQuantity());
	}
	
	@Test
	public void testRequestBuyShare_Fail()
	{
		Shareholder shareholder = new Shareholder("Mary","4321",Permissions.SHAREHOLDER);
		Broker broker = new Broker("Bob","4312",Permissions.BROKER);
		Trade trade = new Trade("MSFT",50.0,5,TradeType.SELL,shareholder.getUsername(),broker.getUsername());
		tradeRequest.clearTradeTable();
		shareholder.requestBuyShare(broker.getUsername(), "MSFT",50.0,5,TradeType.SELL);
		assertFalse(tradeRequest.getTradeList(broker.getUsername()).contains(trade));
	}
	
	@Test
	public void testViewProfile()
	{
		
	}
	
	@Test
	public void addToHistory()
	{
		Broker broker = new Broker("Bob","4312",Permissions.BROKER);
		Shareholder shareholder = new Shareholder("Mary","4321",Permissions.SHAREHOLDER);
		Trade trade = new Trade("MSFT",50.0,5,TradeType.BUY,shareholder.getUsername(),broker.getUsername());
		shareholder.addTradeToPortfolioHistory(trade);
		assertTrue(shareholder.getPortfolio().getTradeHistory().contains(trade));
	}
}
