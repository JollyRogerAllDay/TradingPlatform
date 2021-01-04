package com.fdmgroup.TradingPlatform;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.TradingPlatform.IDGenerator;

public class IDGeneratorTest {

	IDGenerator IDGen;
	
	@Before
	public void init()
	{
		IDGen = IDGenerator.getInstance();
	}
	
	@Test
	public void testTradeIDGen() {
		assertEquals(1,IDGen.getNewTradeID());
		assertEquals(2,IDGen.getNewTradeID());
	}
	
	@Test
	public void testTradeIDGen2()
	{
		assertEquals(2,IDGen.getCurrentTradeID());
	}
	
	@Test
	public void testTradeIDGen3()
	{
		assertEquals(0,IDGen.getCurrentTradeID());
	}
	
	@Test
	public void testMatchTradeIDGen() {
		int firstID = IDGen.getCurrentMatchTradeID();
		assertEquals(firstID++,IDGen.getNewMatchTradeID());
	}

}
