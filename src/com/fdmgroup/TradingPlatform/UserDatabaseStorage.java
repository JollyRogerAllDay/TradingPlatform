package com.fdmgroup.TradingPlatform;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * UserStorage is a concrete class for the IUserStorage interface that deals with the Data access for the user object in a database.
 * @author Michael.Young
 *
 */

public class UserDatabaseStorage implements IUserStorage{

	private Connection dbConn;
	private Log log;

	public UserDatabaseStorage()
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
	public boolean create(User user) 
	{
		String username = user.getUsername();
		String password = user.getPassword();
	    int banned = 0;
	    String permission=user.getPermissions().toString();
	    
	    if(user.IsBanned()) banned = 1;
 
		String query = "INSERT INTO users (user_id,username,password,banned,permission) VALUES (user_id_seq.NEXTVAL,?,?,?,?)";
		try {
			PreparedStatement dbStatement = dbConn.prepareStatement(query);
			dbStatement.setString(1, username);
			dbStatement.setString(2, password);
			dbStatement.setInt(3, banned);
			dbStatement.setString(4, permission);
			
			if(dbStatement.executeUpdate() == 1)
				return true;
			else
				return false;
		} catch (SQLException e1) {
			log.logError(user.toString() + " unable to insert into user table.");
			return false;
		}
	}

	@Override
	public User read(String criteria) 
	{
		UserFactory uf = new UserFactory();
		User user;
		String query = "SELECT username, password, banned, permission FROM users WHERE username=?";
		try {
			
			PreparedStatement dbStatement = dbConn.prepareStatement(query);
			dbStatement.setString(1, criteria);
			
			ResultSet rs = dbStatement.executeQuery();
			if(rs.next())
			{
				String username = rs.getString("username");
				String password = rs.getString("password");
				int banned = rs.getInt("banned");
				String permission = rs.getString("permission");
				Permissions permEnum = Permissions.valueOf(permission);

				user = uf.createUser(username, password, permEnum);

				user.setBanned(banned);
				return user;
			}
		} catch (SQLException e) {
			log.logError("There was an error trying to select \"" + criteria + "\" from the table.");
		}
		return null;
	}
	
	public ArrayList<User> readAll() 
	{
		UserFactory uf = new UserFactory();
		ArrayList<User> users = new ArrayList<User>();
		
		String query = "SELECT username, password, banned, permission FROM users";
		try {
			
			Statement dbStatement = dbConn.createStatement();
			
			ResultSet rs = dbStatement.executeQuery(query);
			while(rs.next())
			{
				String username = rs.getString("username");
				String password = rs.getString("password");
				int banned = rs.getInt("banned");
				String permission = rs.getString("permission");
				Permissions permEnum = Permissions.valueOf(permission);

				User user = uf.createUser(username, password, permEnum);

				user.setBanned(banned);
				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			log.logError("There was an error trying to select all users from the table.");
		}
		return users;
	}

	@Override
	public int delete(String username) 
	{
		String query = "DELETE FROM users WHERE username=?";
		try {
			
			PreparedStatement dbStatement = dbConn.prepareStatement(query);
			dbStatement.setString(1, username);
			
			return dbStatement.executeUpdate();
		} catch (SQLException e) {
			log.logError("There was an error trying to delete \"" + username + "\" from the table.");
		}
		return 0;
	}

	@Override
	public int update(String oldRecord, User newRecord) 
	{
		String query = "UPDATE users SET username=?, password=?, banned=?, permission=? WHERE username=?";
		try {
			
			PreparedStatement dbStatement = dbConn.prepareStatement(query);
			dbStatement.setString(1, newRecord.getUsername());
			dbStatement.setString(2, newRecord.getPassword());
			if(newRecord.IsBanned())
				dbStatement.setInt(3, 1);
			else
				dbStatement.setInt(3, 0);
			dbStatement.setString(4, newRecord.getPermissions().toString());
			dbStatement.setString(5, oldRecord);
			return dbStatement.executeUpdate();
		} catch (SQLException e) {
			log.logError("There was an error trying to update \"" + oldRecord.toString() + "\" to the table.");
		}
		return 0;
	}
	
}
