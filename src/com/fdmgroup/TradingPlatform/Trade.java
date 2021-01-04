package com.fdmgroup.TradingPlatform;

public class Trade implements ITrade{

	private String ticker;
	private int quantity;
	private TradeType orderType; //buy or sell
	private String shareholder;
	private String broker;
	private double price;
	private int tradeID;
	
	public Trade(String ticker, double price, int quantity, TradeType orderType, String shareholder, String broker)
	{
		this.ticker = ticker;
		this.price = price;
		this.quantity = quantity;
		this.orderType = orderType;
		this.shareholder = shareholder;
		this.broker = broker;
	}
	
	@Override
	public TradeType getOrderType() {
		return orderType;
	}

	@Override
	public String getTicker() {
		return ticker;
	}
	
	@Override
	public int getQuantity()
	{
		return quantity;
	}
	
	@Override
	public String getShareholder()
	{
		return shareholder;
	}
	
	@Override
	public String getBroker()
	{
		return broker;
	}
	
	@Override
	public double getPrice()
	{
		return price;
	}
	
	@Override
	public void setTradeID(int value)
	{
		tradeID = value;
	}
	
	
	@Override
	public double getTotalPrice()
	{
		return price*quantity;
	}
	
	@Override
	public int getTradeID()
	{
		return tradeID;
	}
	
	@Override
	public String toString()
	{
		return "Trade(ID="+ tradeID+ ", " + ticker + ", " + quantity + ", " + orderType.toString() + ", " + shareholder.toString()+")";
	}
	
}

