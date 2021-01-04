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

public class BrokerTest {

	Broker broker;
	Shareholder shareholder;
	TradeRequest tradeList;
	
	@Before
	public void init()
	{
		broker = new Broker("Bob","1234",Permissions.BROKER);
		shareholder = new Shareholder("Mike","1234",Permissions.SHAREHOLDER);
		tradeList = TradeRequest.getInstance();
	}
	
	@Test
	public void testGettersAndSetters()
	{
		assertEquals("Bob",broker.getUsername());
		assertEquals("1234",broker.getPassword());
	}
	
	@Test
	public void testTradeLists()
	{
		Trade aBuyTrade = new Trade("GOOG",50.00,2,TradeType.BUY,shareholder.getUsername(),broker.getUsername());
		Trade aSellTrade = new Trade("GOOG",50.00,2,TradeType.SELL,shareholder.getUsername(),broker.getUsername());
		broker.addTradeToList(aBuyTrade);
		broker.addTradeToList(aSellTrade);
		assertTrue(broker.getBuyTrades().contains(aBuyTrade));
		assertTrue(broker.getSellTrades().contains(aSellTrade));
	}
	
	@Test
	public void testAddTradeToBuy() {
		Trade aTrade = new Trade("GOOG",50.00,2,TradeType.BUY,shareholder.getUsername(),broker.getUsername());
		broker.addTradeToList(aTrade);
		assertTrue(broker.getBuyTrades().contains(aTrade));
	}
	
	@Test
	public void testAddTradeToBuy_Fail() {
		Trade aTrade = new Trade("GOOG",50.00,2,TradeType.SELL,shareholder.getUsername(),broker.getUsername());
		broker.addTradeToList(aTrade);
		assertFalse(broker.getBuyTrades().contains(aTrade));
	}
	
	@Test
	public void testAddTradeToSell() {
		Trade aTrade = new Trade ("GOOG",50.00,1,TradeType.SELL,shareholder.getUsername(),broker.getUsername());
		broker.addTradeToList(aTrade);
		assertTrue(broker.getSellTrades().contains(aTrade));
	}
	
	
	@Test 
	public void testGetTradesFromRequests_BuyTrade()
	{
		//Populate tradeRequest so broker can grab the trades and add them to their list
		tradeList.clearTradeTable();
		Trade aBuyTrade = new Trade("GOOG",50.00,2,TradeType.BUY,shareholder.getUsername(),broker.getUsername());
		tradeList.addTrade(broker.getUsername(), aBuyTrade);
		broker.getTradesFromRequests();
		assertTrue(broker.getBuyTrades().contains(aBuyTrade));
	}
	
	@Test 
	public void testGetTradesFromRequests_SellTrade()
	{
		//Populate tradeRequest so broker can grab the trades and add them to their list
		tradeList.clearTradeTable();
		Trade aSellTrade = new Trade("GOOG",50.00,2,TradeType.SELL,shareholder.getUsername(),broker.getUsername());
		tradeList.addTrade(broker.getUsername(), aSellTrade);
		broker.getTradesFromRequests();
		assertTrue(broker.getSellTrades().contains(aSellTrade));
	}
	
	@Test 
	public void testGetTradesFromRequests_BuyAndSellTrade()
	{
		//Populate tradeRequest so broker can grab the trades and add them to their list
		tradeList.clearTradeTable();
		Trade aBuyTrade = new Trade("GOOG",50.00,2,TradeType.BUY,shareholder.getUsername(),broker.getUsername());
		Trade aSellTrade = new Trade("GOOG",50.00,2,TradeType.SELL,shareholder.getUsername(),broker.getUsername());
		tradeList.addTrade(broker.getUsername(), aSellTrade);
		tradeList.addTrade(broker.getUsername(), aBuyTrade);
		broker.getTradesFromRequests();
		assertTrue(broker.getSellTrades().contains(aSellTrade));
		assertTrue(broker.getBuyTrades().contains(aBuyTrade));
	}
	
	@Test 
	public void testGetTradesFromRequests_BuyAndSellTrade_BrokerDoesNotExist()
	{
		//Populate tradeRequest so broker can grab the trades and add them to their list
		tradeList.clearTradeTable();
		Trade aBuyTrade = new Trade("GOOG",50.00,2,TradeType.BUY,shareholder.getUsername(),broker.getUsername());
		Trade aSellTrade = new Trade("GOOG",50.00,2,TradeType.SELL,shareholder.getUsername(),broker.getUsername());
		tradeList.addTrade(broker.getUsername(), aSellTrade);
		tradeList.addTrade(broker.getUsername(), aBuyTrade);
		Broker brokerWithNoTrades = new Broker ("doesNotExist","123212",Permissions.BROKER);
		brokerWithNoTrades.getTradesFromRequests();
		assertTrue(broker.getSellTrades().isEmpty()); //trades should be empty because there were no trades in the trade request list
		assertTrue(broker.getBuyTrades().isEmpty()); // for the specified broker
	}
	
	@Test
	public void testMatchTrades_EvenMatch()
	{
		Shareholder shareholderBuy = new Shareholder("Stan","1111",Permissions.SHAREHOLDER);
		Shareholder shareholderSell = new Shareholder("Lee","9999",Permissions.SHAREHOLDER);
		broker = new Broker("Bob","1234",Permissions.BROKER);
		
		//Populate tradeRequest so broker can grab the trades and add them to their list
		tradeList.clearTradeTable();
		Trade aBuyTrade = new Trade("GOOG",50.00,2,TradeType.BUY,shareholderBuy.getUsername(),broker.getUsername());
		Trade aSellTrade = new Trade("GOOG",50.00,2,TradeType.SELL,shareholderSell.getUsername(),broker.getUsername());
		shareholderSell.addToPortfolio(aSellTrade.getTicker(), aSellTrade.getQuantity());
		
		shareholderBuy.requestBuyShare(broker.getUsername(), "GOOG",50.00,2,TradeType.BUY);
		shareholderSell.requestSellShare(broker.getUsername(), "GOOG",50.00,2,TradeType.SELL);
		
		broker.getTradesFromRequests();
		
		broker.matchAllTrades();
		
		//shares were added to shareholderBuy portfolio
		assertTrue(shareholderBuy.getPortfolio().shareExists("GOOG"));
		assertEquals(2,shareholderBuy.getPortfolio().getNumOfShares("GOOG"),0.0);
		
		//shares were removed from shareHolderSell portfolio
		assertFalse(shareholderSell.getPortfolio().shareExists("GOOG"));
		
		//Shares were removed from the buy and sell list after a match was found
		assertFalse(broker.getBuyTrades().contains(aBuyTrade));
		assertFalse(broker.getSellTrades().contains(aSellTrade));
	}
	
	@Test
	public void testMatchTrades_EvenMatch_MultipleTrades()
	{
		Shareholder shareholderBuy = new Shareholder("Stan","1111",Permissions.SHAREHOLDER);
		Shareholder shareholderSell = new Shareholder("Lee","9999",Permissions.SHAREHOLDER);
		broker = new Broker("Bob","1234",Permissions.BROKER);
		
		//Populate tradeRequest so broker can grab the trades and add them to their list
		tradeList.clearTradeTable();
		Trade aBuyTrade1 = new Trade("GOOG",50.00,2,TradeType.BUY,shareholderBuy.getUsername(),broker.getUsername());
		Trade aSellTrade1 = new Trade("GOOG",50.00,2,TradeType.SELL,shareholderSell.getUsername(),broker.getUsername());
		Trade aBuyTrade2 = new Trade("MSFT",50.00,4,TradeType.BUY,shareholderBuy.getUsername(),broker.getUsername());
		Trade aSellTrade2 = new Trade("MSFT",50.00,4,TradeType.SELL,shareholderSell.getUsername(),broker.getUsername());
		
		shareholderSell.addToPortfolio(aSellTrade1.getTicker(), aSellTrade1.getQuantity());
		shareholderSell.addToPortfolio(aSellTrade2.getTicker(), aSellTrade2.getQuantity());
		
		shareholderBuy.requestBuyShare(broker.getUsername(), "GOOG",50.00,2,TradeType.BUY);
		shareholderSell.requestSellShare(broker.getUsername(), "GOOG",50.00,2,TradeType.SELL);
		
		shareholderBuy.requestBuyShare(broker.getUsername(), "MSFT",50.00,4,TradeType.BUY);
		shareholderSell.requestSellShare(broker.getUsername(), "MSFT",50.00,4,TradeType.SELL);
		
		broker.getTradesFromRequests();
		
		broker.matchAllTrades();
		
		//shares were added to shareholderBuy portfolio
		assertTrue(shareholderBuy.getPortfolio().shareExists("GOOG"));
		assertEquals(2,shareholderBuy.getPortfolio().getNumOfShares("GOOG"),0.0);
		assertTrue(shareholderBuy.getPortfolio().shareExists("MSFT"));
		assertEquals(4,shareholderBuy.getPortfolio().getNumOfShares("MSFT"),0.0);
		
		//shares were removed from shareHolderSell portfolio
		assertFalse(shareholderSell.getPortfolio().shareExists("GOOG"));
		assertFalse(shareholderSell.getPortfolio().shareExists("MSFT"));
		
		//Shares were removed from the buy and sell list after a match was found
		assertFalse(broker.getBuyTrades().contains(aBuyTrade1));
		assertFalse(broker.getSellTrades().contains(aSellTrade1));
		
		assertFalse(broker.getBuyTrades().contains(aBuyTrade2));
		assertFalse(broker.getSellTrades().contains(aSellTrade2));
	}
	
	@Test
	public void testMatchTrades_MoreBuyThanSell()
	{
		
	}
	
	@Test
	public void testMatchTrades_MoreSellThanBuy()
	{
		
	}
	
	
	@Test
	public void testSetStandardCommission()
	{
		broker.getShareHolders().add(shareholder.getUsername());
		broker.setStandardCommission(0.50);
		assertEquals(0.50, broker.getCommissionLevel().get(shareholder.getUsername()).doubleValue(),0.0);
	}

	@Test
	public void testSetStandardCommissionForShareholders()
	{
		Shareholder newShareholder = new Shareholder("Steve","4311",Permissions.SHAREHOLDER);
		broker.getShareHolders().add(shareholder.getUsername());
		broker.getShareHolders().add(newShareholder.getUsername());
		broker.setCommissionForShareholder(shareholder.getUsername(),0.50);
		broker.setCommissionForShareholder(newShareholder.getUsername(),0.25);
		assertEquals(0.50, broker.getCommissionLevel().get(shareholder.getUsername()).doubleValue(),0.0);
		assertEquals(0.25, broker.getCommissionLevel().get(newShareholder.getUsername()).doubleValue(),0.0);
	}
	
}
