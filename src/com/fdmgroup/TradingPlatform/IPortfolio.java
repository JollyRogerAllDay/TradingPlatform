package com.fdmgroup.TradingPlatform;

import java.util.ArrayList;
/**
 * Portfolio Interface
 * This interface represents the framework for the user portfolio.
 * A portfolio represents a list of stocks(or shares) the user currently owns.
 * The shares are stored in a hashmap for easy access. The key is passed into the
 * hashmap and an arraylist is returned that includes the quantity and price total 
 * for that stock.
 * @author Michael.Young
 *
 */
public interface IPortfolio {
	public Integer getNumOfShares(String ticker);
	public ArrayList<String> getFavouriteShares();
	public void addToShares(String ticker, int quantity);
	public void removeFromShares(String ticker);
	public void addToFavourites(String ticker);
	public void removeFromFavourites(String ticker);
	public boolean shareExists(String ticker);
	public void subtractShares(String ticker,int quantity);
}
