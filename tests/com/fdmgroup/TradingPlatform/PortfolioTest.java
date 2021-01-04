package com.fdmgroup.TradingPlatform;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.TradingPlatform.Portfolio;

public class PortfolioTest {

	Portfolio portfolio;
	@Before
	public void init()
	{
		portfolio = new Portfolio();
	}
	
	@Test
	public void testAddToShares() {
		portfolio.addToShares("GOOG", 2);
		assertTrue(portfolio.shareExists("GOOG"));
	}
	
	@Test
	public void testAddToSharesTwice() 
	{
		portfolio = new Portfolio();
		portfolio.addToShares("GOOG", 2);
		portfolio.addToShares("GOOG", 2);
		assertEquals(4,(int)portfolio.getNumOfShares("GOOG"));
	}
	
	@Test
	public void testRemoveFromShares()
	{
		portfolio.addToShares("GOOG", 2);
		portfolio.removeFromShares("GOOG");
		assertFalse(portfolio.shareExists("GOOG"));
	}
	
	@Test
	public void testAddToFavourites()
	{
		portfolio.addToFavourites("GOOG");
		assertTrue(portfolio.getFavouriteShares().contains("GOOG"));
	}
	
	@Test 
	public void testRemoveFromFavourites()
	{
		portfolio.addToFavourites("GOOG");
		portfolio.removeFromFavourites("GOOG");
		assertFalse(portfolio.getFavouriteShares().contains("GOOG"));
	}
	
	@Test
	public void testSubtractFromShares()
	{
		portfolio = new Portfolio();
		portfolio.addToShares("GOOG", 5);
		portfolio.subtractShares("GOOG", 2);
		assertEquals(3,(int)portfolio.getNumOfShares("GOOG"));
	}
	
	@Test
	public void testSubtractFromSharesSameQuantity()
	{
		portfolio = new Portfolio();
		portfolio.addToShares("GOOG", 5);
		portfolio.subtractShares("GOOG", 5);
		assertFalse(portfolio.shareExists("GOOG"));
	}

}
