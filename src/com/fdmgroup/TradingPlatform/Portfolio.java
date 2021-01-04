package com.fdmgroup.TradingPlatform;

import java.util.ArrayList;
import java.util.HashMap;

public class Portfolio implements IPortfolio{

	private HashMap<String,Integer> shares;
	private ArrayList<String> favouriteShares;
	private ArrayList<Trade> tradeHistory;
	
	public Portfolio()
	{
		shares= new HashMap<String,Integer>();
		favouriteShares = new ArrayList<String>();
		tradeHistory = new ArrayList<Trade>();
	}
	
	@Override
	public Integer getNumOfShares(String ticker) {
		return shares.get(ticker);
	}

	@Override
	public ArrayList<String> getFavouriteShares() {
		return favouriteShares;
	}
	
	public ArrayList<Trade> getTradeHistory()
	{
		return tradeHistory;
	}

	/**
	 * Adds the given share(ticker symbol) to the list of shares.
	 * If that share already exists, the quantity given is added to 
	 * the existing share quantity.
	 */
	@Override
	public void addToShares(String ticker,int quantity) 
	{
		if(shares.containsKey(ticker))
		{
			shares.put(ticker, shares.get(ticker).intValue() + quantity);
		}
		else
		{
			shares.put(ticker, quantity);
		}
	}

	/**
	 * Subtracts the given quantity of shares from the share collection
	 * If the subtraction of the quantity equates the total to 0, the shares
	 * are removed from the collection.
	 */
	@Override
	public void subtractShares(String ticker, int quantity)
	{
		if((shares.get(ticker).intValue() - quantity) == 0)
		{
			removeFromShares(ticker);
		}
		else
		{
			shares.put(ticker, shares.get(ticker).intValue() - quantity);
		}
	}
	
	@Override
	public void removeFromShares(String ticker) {
		shares.remove(ticker);
	}

	@Override
	public void addToFavourites(String ticker) {
		favouriteShares.add(ticker);
	}

	@Override
	public void removeFromFavourites(String ticker) {
		favouriteShares.remove(ticker);
	}
	
	public boolean shareExists(String ticker)
	{
		return shares.containsKey(ticker);
	}
	
	public void addTradeToHistory(Trade trade)
	{
		tradeHistory.add(trade);
	}
	
	
}
