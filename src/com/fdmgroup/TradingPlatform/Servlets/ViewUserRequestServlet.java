package com.fdmgroup.TradingPlatform.Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdmgroup.TradingPlatform.LoginManager;
import com.fdmgroup.TradingPlatform.SystemAdmin;
import com.fdmgroup.TradingPlatform.UserRequest;

/**
 * Servlet implementation class ViewUserRequestServlet
 */
public class ViewUserRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ArrayList<UserRequest> userRequests;
    private SystemAdmin admin;
    private LoginManager loginManager;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewUserRequestServlet() {
        super();
        userRequests = new ArrayList<UserRequest>();
        loginManager = LoginManager.getInstance();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        admin = (SystemAdmin) loginManager.getUser(request.getSession().getAttribute("username").toString());
		
        userRequests = admin.viewUserRequest();
		System.out.println(admin.getUsername());
		request.getSession().setAttribute("user_requests", userRequests);
		
		request.getRequestDispatcher("/view_user_request").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
