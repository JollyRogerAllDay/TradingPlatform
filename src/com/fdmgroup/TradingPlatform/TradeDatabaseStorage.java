package com.fdmgroup.TradingPlatform;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TradeDatabaseStorage implements ITradeStorage{

	private Connection dbConn;
	private Log log;
	
	public TradeDatabaseStorage()
	{
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			dbConn = DriverManager.getConnection(DatabaseConnection.getDatabase(), DatabaseConnection.getUsername(), DatabaseConnection.getPassword());
			log = new Log();
		} catch (SQLException e) {
			log.logError("There was an error connecting to "+ DatabaseConnection.getDatabase());
		}
	}

	@Override
	public boolean create(Trade trade) 
	{
		String tradeType = trade.getOrderType().toString();
		int quantity = trade.getQuantity();
		String ticker = trade.getTicker();
		String shareholderUsername = trade.getShareholder();
		String brokerUsername = trade.getBroker();
		double price = trade.getPrice();
		int tradeID = IDGenerator.getInstance().getNewTradeID();
		
		String query = "INSERT INTO trade (trade_id, shareholder_username, broker_username, type, ticker, quantity, price, matched, matched_trade_id, date_matched, date_requested) VALUES (?,?,?,?,?,?,?,?,null,null,SYSDATE)";
		
		try {
			PreparedStatement dbStatement = dbConn.prepareStatement(query);
			
			dbStatement.setInt(1, tradeID);
			dbStatement.setString(2,shareholderUsername);
			dbStatement.setString(3, brokerUsername);
			dbStatement.setString(4, tradeType);
			dbStatement.setString(5, ticker);
			dbStatement.setInt(6, quantity);
			dbStatement.setDouble(7, price);
			dbStatement.setInt(8, 0); //no matched trade
			
			
			if(dbStatement.executeUpdate() == 1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			log.logError("Unable to store " + trade.toString() + " request in table.");
		}
		return false;
	}

	@Override
	public ArrayList<Trade> read(String brokerUsername) 
	{
		String query = "SELECT trade_id, shareholder_username, broker_username, type, ticker, quantity, price FROM trade WHERE broker_username=?";
		ArrayList<Trade> trades = new ArrayList<Trade>();
		
		try {
			PreparedStatement dbStatement = dbConn.prepareStatement(query);
			dbStatement.setString(1, brokerUsername);
			ResultSet rs = dbStatement.executeQuery();
			
			while(rs.next())
			{
				int tradeId = rs.getInt("trade_id");
				String shareholderUsername = rs.getString("shareholder_username");
				
				TradeType tradeType = TradeType.valueOf(rs.getString("type"));
				
				String ticker = rs.getString("ticker");
				int quantity = rs.getInt("quantity");
				double price = rs.getDouble("price");
				
				Trade trade = new Trade(ticker,price,quantity,tradeType,shareholderUsername,brokerUsername);
				trade.setTradeID(tradeId);
				trades.add(trade);
			}
			return trades;
		} catch (SQLException e) {
			e.printStackTrace();
			log.logError("Unable to find trade list for " + brokerUsername);
		}
		return trades;
	}
	
	public ArrayList<Trade> readTradeRequest(String brokerUsername) 
	{
		String query = "SELECT trade_id, shareholder_username, broker_username, type, ticker, quantity, price FROM trade_requests WHERE broker_username=?";
		ArrayList<Trade> trades = new ArrayList<Trade>();
		
		try {
			PreparedStatement dbStatement = dbConn.prepareStatement(query);
			dbStatement.setString(1, brokerUsername);
			ResultSet rs = dbStatement.executeQuery();
			
			while(rs.next())
			{
				int tradeId = rs.getInt("trade_id");
				String shareholderUsername = rs.getString("shareholder_username");
				
				TradeType tradeType = TradeType.valueOf(rs.getString("type"));
				
				String ticker = rs.getString("ticker");
				int quantity = rs.getInt("quantity");
				double price = rs.getDouble("price");
				
				Trade trade = new Trade(ticker,price,quantity,tradeType,shareholderUsername,brokerUsername);
				trade.setTradeID(tradeId);
				trades.add(trade);
			}
			return trades;
		} catch (SQLException e) {
			e.printStackTrace();
			log.logError("Unable to find trade list for " + brokerUsername);
		}
		return trades;
	}
	
	public ArrayList<Trade> readMatchedTrades(String brokerUsername) 
	{
		String query = "SELECT trade_id, shareholder_username, broker_username, type, ticker, quantity, price FROM matched_trades WHERE broker_username=?";
		ArrayList<Trade> trades = new ArrayList<Trade>();
		
		try {
			PreparedStatement dbStatement = dbConn.prepareStatement(query);
			dbStatement.setString(1, brokerUsername);
			ResultSet rs = dbStatement.executeQuery();
			
			while(rs.next())
			{
				int tradeId = rs.getInt("trade_id");
				String shareholderUsername = rs.getString("shareholder_username");
				
				TradeType tradeType = TradeType.valueOf(rs.getString("type"));
				
				String ticker = rs.getString("ticker");
				int quantity = rs.getInt("quantity");
				double price = rs.getDouble("price");
				
				Trade trade = new Trade(ticker,price,quantity,tradeType,shareholderUsername,brokerUsername);
				trade.setTradeID(tradeId);
				trades.add(trade);
			}
			return trades;
		} catch (SQLException e) {
			e.printStackTrace();
			log.logError("Unable to find trade list for " + brokerUsername);
		}
		return trades;
	}
	
	
	@Override
	public int update(Trade oldRecord,String brokerUsername, Trade newRecord) 
	{
		
		return 0;
	}

	@Override
	public int delete(Trade trade) 
	{
		String query = "DELETE FROM trade WHERE trade_id=?";
		
		try {
			PreparedStatement dbStatement = dbConn.prepareStatement(query);
			dbStatement.setInt(1, trade.getTradeID());
			
			return dbStatement.executeUpdate();
		} catch (SQLException e) {
			log.logError("Unable to delete "+ trade.toString() + " from the database.");
		}
		
		return 0;
	}
	
	
}
