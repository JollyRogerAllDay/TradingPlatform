package com.fdmgroup.TradingPlatform;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.TradingPlatform.Trade;
import com.fdmgroup.TradingPlatform.TradeType;

public class TradeTest {
	
	private Trade aTrade;
	
	@Before
	public void init()
	{
		aTrade = new Trade("GOOG",50.00,2,TradeType.BUY,"name","brokername");
	}
	
	@Test
	public void testTradeGettersAndSetters()
	{
		assertEquals("GOOG",aTrade.getTicker());
		assertEquals(50.00,aTrade.getPrice(),0.0);
		assertEquals(100.00,aTrade.getTotalPrice(),0.0);
		assertEquals(2,aTrade.getQuantity());
		assertEquals(TradeType.BUY,aTrade.getOrderType());
		assertEquals("brokername",aTrade.getBroker());
	}
	
}
