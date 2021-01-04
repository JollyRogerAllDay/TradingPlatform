package com.fdmgroup.TradingPlatform;

/**
 * The trade interface represents the transaction between a broker and shareholders. When the shareholder
 * decides to buy or sell shares, a trade must be processed and verified by the broker.
 * A trade contains the order type, either buy or sell. The share type, which is simply the ticker symbol 
 * of a company. As well as the amount of shares being sold.
 * @author Michael.Young
 *
 */
public interface ITrade {
	public TradeType getOrderType();
	public String getTicker();
	public int getQuantity();
	public String getShareholder();
	public String getBroker();
	public double getPrice();
	public double getTotalPrice();
	public int getTradeID();
	public void setTradeID(int value);
}
