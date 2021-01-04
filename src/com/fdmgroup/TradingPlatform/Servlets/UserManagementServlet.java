package com.fdmgroup.TradingPlatform.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdmgroup.TradingPlatform.LoginManager;
import com.fdmgroup.TradingPlatform.Permissions;
import com.fdmgroup.TradingPlatform.SystemAdmin;
import com.fdmgroup.TradingPlatform.User;
import com.fdmgroup.TradingPlatform.UserFactory;

/**
 * Servlet implementation class UserManagementServlet
 */
public class UserManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private SystemAdmin admin;   
    private LoginManager loginManager;
    private UserFactory uf;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManagementServlet() {
        super();
        loginManager = LoginManager.getInstance();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//populat user list
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String buttonAction = request.getParameter("button");
		String username = request.getParameter("userToManage");
        admin = (SystemAdmin)loginManager.getUser(request.getSession().getAttribute("username").toString());
	
		String actionStatus="";
        if(buttonAction.equals("add_user_button"))
		{
			//admin.addUser(username, password, permissions);
		}
		else if(buttonAction.equals("delete_user_button"))
		{
			
			if(admin.deleteUser(username)==1)
				actionStatus = username + " was successfully deleted.";
			else
				actionStatus = username + " was not deleted. Perhaps the user does not exists.";
				
		}
		else if(buttonAction.equals("update_user_button"))
		{

				String newUsername = request.getParameter("newUsername");
				String newPassword = request.getParameter("newPassword");
				String permissions = request.getParameter("type");
				User newUser = uf.createUser(newUsername, newPassword, Permissions.valueOf(permissions));
				if(admin.updateDetails(username, newUser)==1)
					actionStatus = username + " details were successfully updated.";
				else
					actionStatus = "Failed to update details for "+ username;
		}
		else if(buttonAction.equals("ban_user_button"))
		{
			if(admin.banUser(username)==1)
				actionStatus = username+ " was successfully banned.";
			else
				actionStatus = username+ " failed to ban.";
		}
        
        request.setAttribute("status", actionStatus);
        
        request.getRequestDispatcher("/user_management").forward(request, response);
			
	}

}
