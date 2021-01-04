package com.fdmgroup.TradingPlatform;

import java.util.ArrayList;

public class Shareholder extends User{

	private Portfolio portfolio;
	private ArrayList<String> brokers;
	private Log log;
	private TradeRequest tradeRequest;
	
	public Shareholder(String username, String password, Permissions perm)
	{
		super(username,password);
		super.setPermission(perm);
		log = new Log(this);
		portfolio = new Portfolio();
		brokers = new ArrayList<String>();
		tradeRequest = TradeRequest.getInstance();
		log.logInfo(this.toString() + " was created.");
	}

	@Override
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}
	
	public Portfolio getPortfolio()
	{
		return portfolio;
	}
	
	public ArrayList<String> getBrokers()
	{
		return brokers;
	}

	@Override
	public void createProfile() 
	{
		
	}
	
	public void addToPortfolio(String ticker,int quantity)
	{
		this.portfolio.addToShares(ticker,quantity);
	}
	
	public void addToBrokers(String brokerUsername)
	{
		brokers.add(brokerUsername);
	}
	
	public void removeFromBrokers(Broker broker)
	{
		brokers.remove(broker);
	}
	
	public void requestSellShare(String broker, String ticker, double price, int quantity,TradeType orderType)
	{
		Trade trade = new Trade(ticker,price,quantity,orderType,this.getUsername(),broker);
		//Does this make sense to check to see if the share exists?
		//From the view the user should be able to select their shares, so the ticker symbol
		//will always be there.
		if(portfolio.shareExists(trade.getTicker()) && trade.getOrderType().equals(TradeType.SELL))
		{
			if(portfolio.getNumOfShares(trade.getTicker()) >= trade.getQuantity())
			{
				//sell shares and add to TradeRequest
				portfolio.subtractShares(trade.getTicker(), trade.getQuantity());
				tradeRequest.addTrade(broker, trade);
				log.logInfo(this.toString() + " requested to sell "+ trade.toString() + " with " + broker);
				
			}
			else
			{
				//Insufficient number of shares available for trade
				log.logInfo(this.toString() + " unable to request "+ trade.toString() + " with " + broker + ". Insufficient number of shares available in portfolio.");
			}
		}
		else
		{
			//log unable to process sell trade. Share with given ticker value does not exist in portfolio
			log.logWarn(this.toString() + " unable to request "+ trade.toString() + " with " + broker.toString() + ". Trade is not of type sell.");
		}
		
	}
	
	public void requestBuyShare(String broker, String ticker, double price, int quantity,TradeType orderType)
	{
		Trade trade = new Trade(ticker,price,quantity,orderType,this.getUsername(),broker);
		//send trade request to broker
		if(trade.getOrderType().equals(TradeType.BUY))
		{
			tradeRequest.addTrade(broker, trade);
			log.logInfo(this.toString() + " requested to buy "+ trade.toString() + " with " + broker);
		}
		else
		{
			//log unable to buy share
			log.logWarn(this.toString() + " unable to request "+ trade.toString() + " with " + broker + ". Trade is not of type buy.");
		}
	}
	
	public void addTradeToPortfolioHistory(Trade trade)
	{
		portfolio.addTradeToHistory(trade);
	}
	
	
	public void login()
	{
		log.logInfo(this.toString() + " logged on.");
	}
	
	public void logoff()
	{
		log.logInfo(this.toString() + " logged off.");
	}
	
	@Override
	public String toString()
	{
		return "shareholder=" + super.getUsername();
	}
		 
}
