package com.fdmgroup.TradingPlatform;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * The broker class is an extension of the User abstract class. A broker is a type of user whose job is to settle trades between a shareholder
 * and the company from which they are buying shares. 
 * @author Michael.Young
 *	
 */
public class Broker extends User{
	
	private ArrayList<Trade> buyTrades;
	private ArrayList<Trade> sellTrades;
	private ArrayList<String> shareholders;

	private HashMap<String,Double> commissionLevel;
	private Log log;
	private TradeManager tradeManager;
	
	public Broker(String username, String password, Permissions perm)
	{
		super(username,password);
		super.setPermission(perm);
		buyTrades = new ArrayList<Trade>();
		sellTrades = new ArrayList<Trade>();
		shareholders = new ArrayList<String>();
		commissionLevel = new HashMap<String,Double>();
		log = new Log(this);
		tradeManager = TradeManager.getInstance();
		log.logTrace(this.toString() + " was created.");
	}

	@Override
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}
	
	public ArrayList<Trade> getBuyTrades()
	{
		return buyTrades;
	}
	
	public ArrayList<Trade> getSellTrades()
	{
		return sellTrades;
	}
	
	public ArrayList<String> getShareHolders()
	{
		return shareholders;
	}

	/**
	 * Access the trade request collection for this broker and populate the buy and sell trade lists
	 */
	public void getTradesFromRequests()
	{
		ArrayList<Trade> tradesToAdd = tradeManager.getTradeRequest(this.getUsername());
		
		for(Trade t: tradesToAdd)
		{
			addTradeToList(t);
		}
		
		//tradeRequests.clearTradeTable();//change this clear list for this broker, not all brokers
	}
	
//	public ArrayList<Trade> getTrades()
//	{
//		return tradeStorage.read(this.getUsername());
//	}
//	
	public ArrayList<Trade> getTradeRequests()
	{
		return tradeManager.getTradeRequest(this.getUsername());
	}
	
	public void setStandardCommission(double standardCommission)
	{
		for(String shareholder: shareholders)
		{
			commissionLevel.put(shareholder, standardCommission);
		}
	}
	
	public void setCommissionForShareholder(String shareholderName, double commission)
	{
		commissionLevel.put(shareholderName, commission);
	}
	
	public HashMap<String,Double> getCommissionLevel()
	{
		return commissionLevel;
	}
	
	
	public void matchAllTrades()
	{
		ArrayList<Trade> buyTradesToRemove = new ArrayList<Trade>();
		for(Trade buy: buyTrades)
		{
			if(matchTrades(buy))
			{
				buyTradesToRemove.add(buy);
			}
		}
		
		for(Trade buy:buyTradesToRemove)
		{
			buyTrades.remove(buy);
		}
	}
	
	/*
	 * Focused only on evenly matched trades
	 */
	private boolean matchTrades(Trade buy)
	{
		for(Trade sell: sellTrades)
		{
			if(buy.getTicker().equalsIgnoreCase(sell.getTicker()))
			{
				if(buy.getQuantity() == sell.getQuantity())
				{
					//send trade to buy.shareholder
					//buy.getShareholder().addToPortfolio(buy.getTicker(),buy.getQuantity());
					
					//inform sell.shareholder trade was successful
					
					log.logInfo("Trade matched:" + buy.toString() + " and " + sell.toString());
					addToHistory(buy,sell);
					sellTrades.remove(sell);
					return true;
				}
				else if(buy.getQuantity() < sell.getQuantity())
				{
					//send buy trade to buy.shareholder
					//update sell.quantity - buy.quantity. Make a new trade
					//remove buy from buyTrades
				}
				else
				{
					//update buy.quantity - sell.quantity. Make a new trade
				}
			}
		}
		return false;
	}
	
	private void addToHistory(Trade buy, Trade sell)
	{
		//buy.getShareholder().addTradeToPortfolioHistory(buy);
		//sell.getShareholder().addTradeToPortfolioHistory(sell);
	}
	
	public void login()
	{
		//additional login functionality
		log.logInfo(this.toString()+" logged on.");
	}
	
	public void logoff()
	{
		//additional logoff functionality
		log.logInfo(this.toString()+" logged off.");
	}
	
	
	//Prompt user for necessary profile information
	@Override
	public void createProfile() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Add the given trade to the buy or sells trades list depending on the type.
	 * @param trade
	 */
	public void addTradeToList(Trade trade)
	{
		if(trade.getOrderType().equals(TradeType.BUY))
		{
			buyTrades.add(trade);
			log.logInfo(this.toString() + " added a new " + trade.toString() + " to their buy list.");
		}
		else if(trade.getOrderType().equals(TradeType.SELL))
		{
			sellTrades.add(trade);
			log.logInfo(this.toString() + " added a new " + trade.toString() + " to their sell list.");
		}
		else
		{
			log.logWarn(this.toString() + " could not add " + trade.toString() + " to their buy or sell list. Invalid trade order type.");
		}
	}
	
	public ArrayList<Trade> getTradeHistory()
	{
		return new ArrayList<Trade>();
	}
	
	@Override
	public String toString()
	{
		return "broker=" + super.getUsername();
	}
}
