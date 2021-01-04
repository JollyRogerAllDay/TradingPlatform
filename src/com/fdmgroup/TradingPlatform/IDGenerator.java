package com.fdmgroup.TradingPlatform;

/**
 * The IDGenerator class is used to assign unique ID values to trades, users and matched trades. 
 * @author Michael.Young
 *
 */

public class IDGenerator {
	private static IDGenerator IDGen;
	private static int tradeID;
	private static int matchTradeID;
	
	private IDGenerator()
	{
		tradeID = 0;
		matchTradeID = 0;
	}

	
	public static IDGenerator getInstance()
	{
		if(IDGen == null)
		{
			IDGen = new IDGenerator();
		}
		return IDGen;
	}
	
	public int getCurrentTradeID()
	{
		return tradeID;
	}
	public int getNewTradeID()
	{
		return ++tradeID;
	}
	
	public int getCurrentMatchTradeID()
	{
		return matchTradeID;
	}
	public int getNewMatchTradeID()
	{
		return matchTradeID++;
	}

	
}
