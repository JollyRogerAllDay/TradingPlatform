package com.fdmgroup.TradingPlatform;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.TradingPlatform.Broker;
import com.fdmgroup.TradingPlatform.Permissions;
import com.fdmgroup.TradingPlatform.Shareholder;
import com.fdmgroup.TradingPlatform.Trade;
import com.fdmgroup.TradingPlatform.TradeDatabaseStorage;
import com.fdmgroup.TradingPlatform.TradeType;
import com.fdmgroup.TradingPlatform.User;
import com.fdmgroup.TradingPlatform.UserDatabaseStorage;

public class TradeDatabaseStorageTest {

	TradeDatabaseStorage trDBStorage;
	UserDatabaseStorage udbstorage;
	Trade trade;
	User aBroker;
	User aBuyer;
	
	
	@Before
	public void init()
	{
		trDBStorage = new TradeDatabaseStorage();
		udbstorage = new UserDatabaseStorage();
		aBuyer = new Shareholder("Bob","1234",Permissions.SHAREHOLDER);
		aBroker = new Broker("BrokerBill","5421",Permissions.BROKER);
		trade = new Trade("GOOG",50.0,10,TradeType.BUY,aBuyer.getUsername(),aBroker.getUsername());
	}
	
	@Test
	public void testCreate() {
		udbstorage.create(aBroker);
		udbstorage.create(aBuyer);
		Trade trade2 = new Trade("MSFT",100.0,10,TradeType.BUY,aBuyer.getUsername(),aBroker.getUsername());
		Trade trade3 = new Trade("MSFT",100.0,20,TradeType.BUY,aBuyer.getUsername(),aBroker.getUsername());
		Trade trade4 = new Trade("ZDA",150.0,5,TradeType.SELL,aBuyer.getUsername(),aBroker.getUsername());
		assertTrue(trDBStorage.create(trade));
		assertTrue(trDBStorage.create(trade2));
		assertTrue(trDBStorage.create(trade3));
		assertTrue(trDBStorage.create(trade4));
	}
	
	@Test
	public void testRead()
	{
		User broker = new Broker("BrokerBob","5421",Permissions.BROKER);
		User shareholder = new Shareholder("ShareholderStan","5423",Permissions.SHAREHOLDER);
		udbstorage.create(broker);
		udbstorage.create(shareholder);
		Trade trade = new Trade("GOOG",50.0,10,TradeType.BUY,shareholder.getUsername(),broker.getUsername());
		trDBStorage.create(trade);
		assertEquals("GOOG",trDBStorage.read("BrokerBob").get(0).getTicker());
		assertEquals(50.0,trDBStorage.read("BrokerBob").get(0).getPrice(),0.0);
		assertEquals(TradeType.BUY,trDBStorage.read("BrokerBob").get(0).getOrderType());
		assertEquals(shareholder.getUsername(),trDBStorage.read("BrokerBob").get(0).getShareholder());
		assertEquals(10,trDBStorage.read("BrokerBob").get(0).getQuantity());
	}


	@Test
	public void testDelete()
	{
		udbstorage.create(aBroker);
		udbstorage.create(aBuyer);
		Trade trade = new Trade("GOOG",50.0,10,TradeType.BUY,aBuyer.getUsername(),aBroker.getUsername());
		trade.setTradeID(2);
		assertEquals(1,trDBStorage.delete(trade));
	}
	
}
