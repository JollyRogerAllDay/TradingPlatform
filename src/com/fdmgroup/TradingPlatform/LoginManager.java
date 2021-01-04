package com.fdmgroup.TradingPlatform;

import java.util.ArrayList;

public class LoginManager {

	private static LoginManager login;
	private ArrayList<User> users; //user collection of logged in users
	private UserManager userManager;
	private Log log;
	
	private LoginManager()
	{
		userManager = UserManager.getInstance();
		users = new ArrayList<User>();
		log = new Log();
	}
	
	public static LoginManager getInstance()
	{
		if(login == null)
		{
			login = new LoginManager();
		}
		return login;
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return 0- success, 1- user is banned, 2-invalid credentials
	 */
	public int login(String username, String password)
	{
		User userLogin = userManager.readUser(username);
		
		if(userLogin != null)
		{
			if(checkCredentials(userLogin,username,password))
			{
				if(userLogin.IsBanned())
				{
					log.logError(userLogin.toString() + " tried to login but is currently banned.");
					return 1; //banned
				}
				else
				{
					//add user to users that are logged in to the system
					users.add(userLogin);
					log.logInfo(userLogin.toString() + " logged on.");
					userLogin.login();
					return 0;//success
				}
			}
			else
			{
				return 2;//invalid credentials
			}
		}
		else
		{
			return 3;//user doesnt exist
		}
	}
	
	/**
	 * Returns a user that is currently logged in to the system.
	 * @param username
	 * @return
	 */
	public User getUser(String username)
	{
		for(User user:users)
		{
			if(user.getUsername().equals(username))
			{
				return user;
			}
		}
		return null;
	}
	
	public boolean logout(String username)
	{
		for(User user: users)
		{
			if(user.getUsername().equals(username))
			{
				log.logInfo(user.toString() + " logged off.");
				users.remove(user);
				return true;
			}
		}
		return false;
	}
	
	public boolean registerUser(String username, String password, String permissions)
	{	
		return userManager.addUser(username, password, Permissions.valueOf(permissions));
	}
	
	private boolean checkCredentials(User userLogin,String username,String password)
	{
		return(userLogin.getUsername().equals(username) && userLogin.getPassword().equals(password));
	}

}
