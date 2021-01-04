package com.fdmgroup.TradingPlatform.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdmgroup.TradingPlatform.LoginManager;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginManager loginManager;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        loginManager = LoginManager.getInstance();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String confirmPassword = request.getParameter("password");
		String permissions = request.getParameter("type");
		int registerStatus;

		if(loginManager.registerUser(username, confirmPassword, permissions))
		registerStatus = 0; //successfully registered
		else
		registerStatus=1;
		
		request.setAttribute("registerStatus", registerStatus);
		request.getSession().setAttribute("username", username);
		
		request.getRequestDispatcher("register").forward(request, response);
	}

}
