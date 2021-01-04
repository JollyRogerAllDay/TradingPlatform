package com.fdmgroup.TradingPlatform;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserRequestDatabaseStorage implements IUserRequestStorage{

	private Connection dbConn;
	private Log log;
	
	public UserRequestDatabaseStorage()
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
	public boolean create(UserRequest request) {
		String requestType = request.getRequestType().toString();
		String statusType = request.getStatus().toString();
		String admin = request.getAdmin();
		String user = request.getUser();
		
		String query = "INSERT INTO user_request (request_id, username, admin, requestType, status) VALUES (user_req_id_seq.NEXTVAL,?,?,?,?)";
		
		try {
			PreparedStatement dbStatement = dbConn.prepareStatement(query);
			dbStatement.setString(1, user);
			dbStatement.setString(2, admin);
			dbStatement.setString(3, requestType);
			dbStatement.setString(4, statusType);
			
			if(dbStatement.executeUpdate() == 1)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			log.logError("Unable to insert "+ request.toString() + " into user_request table.");
		}
		
		return false;
	}

	@Override
	public ArrayList<UserRequest> read(String username) {
		
		String query = "SELECT request_id, username, admin, requestType, status FROM user_request WHERE username=?";
		ArrayList<UserRequest> requests = new ArrayList<UserRequest>();
		try {
			PreparedStatement dbStatement = dbConn.prepareStatement(query);
			dbStatement.setString(1, username);
			
			ResultSet rs = dbStatement.executeQuery();
			while(rs.next())
			{
				String requestType = rs.getString("requestType");
				String statusType = rs.getString("status");
				String uname = rs.getString("username");
				String admin = rs.getString("admin");
				UserRequest request = new UserRequest(UserRequestType.valueOf(requestType),RequestStatus.valueOf(statusType),uname,admin);
				requests.add(request);
			}
			
			return requests;
			
		} catch (SQLException e) {
			log.logError("Unable to select user requests for " + username);
		}
		
		
		return requests;
	}
	
	public ArrayList<UserRequest> readAll() {
		
		String query = "SELECT request_id, username, admin, requestType, status FROM user_request";
		ArrayList<UserRequest> requests = new ArrayList<UserRequest>();
		try {
			Statement dbStatement = dbConn.createStatement();
			
			ResultSet rs = dbStatement.executeQuery(query);
			while(rs.next())
			{
				String requestType = rs.getString("requestType");
				String statusType = rs.getString("status");
				String uname = rs.getString("username");
				String admin = rs.getString("admin");
				UserRequest request = new UserRequest(UserRequestType.valueOf(requestType),RequestStatus.valueOf(statusType),uname,admin);
				requests.add(request);
			}
			
			return requests;
			
		} catch (SQLException e) {
			log.logError("Unable to select all user requests");
		}
		return requests;
	}
	
	@Override
	public int update(UserRequest oldRecord, UserRequest newRecord) {
		
		String query = "SELECT request_id FROM user_request WHERE username=? AND admin=? AND requestType=? AND status=?";
		
		try {
			PreparedStatement dbStatement = dbConn.prepareStatement(query);
			dbStatement.setString(1, oldRecord.getUser());
			dbStatement.setString(2, oldRecord.getAdmin());
			dbStatement.setString(3, oldRecord.getRequestType().toString());
			dbStatement.setString(4, oldRecord.getStatus().toString());
			
			ResultSet rs1 = dbStatement.executeQuery();
			int oldRequestID=-1;
			while(rs1.next())
			{
				oldRequestID = rs1.getInt("request_id");
			}
			
			query = "UPDATE user_request SET username=?, admin=?, requestType=?,status=? WHERE request_id=?";
			dbStatement = dbConn.prepareStatement(query);
			dbStatement.setString(1, newRecord.getUser());
			dbStatement.setString(2, newRecord.getAdmin());
			dbStatement.setString(3, newRecord.getRequestType().toString());
			dbStatement.setString(4, newRecord.getStatus().toString());
			dbStatement.setInt(5, oldRequestID);
			
			return dbStatement.executeUpdate();
			
		} catch (SQLException e) {
			log.logError("Unable to update " + oldRecord.toString() + " to " + newRecord.toString());
		}
		return 0;
	}

	@Override
	public int delete(UserRequest request) {
		String query = "DELETE FROM user_request WHERE username=? AND admin=? AND requestType=? AND status=?";
		try {
			PreparedStatement dbStatement = dbConn.prepareStatement(query);
			dbStatement.setString(1, request.getUser());
			dbStatement.setString(2, request.getAdmin());
			dbStatement.setString(3, request.getRequestType().toString());
			dbStatement.setString(4, request.getStatus().toString());
			
			return dbStatement.executeUpdate();
		} catch (SQLException e) {
			log.logError("Unable to remove " + request.toString() + "from user_request table.");
		}
		return 0;
	}
	
}
