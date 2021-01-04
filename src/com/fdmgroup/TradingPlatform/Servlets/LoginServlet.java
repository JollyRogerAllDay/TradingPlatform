package com.fdmgroup.TradingPlatform.Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdmgroup.TradingPlatform.LoginManager;
import com.fdmgroup.TradingPlatform.User;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private LoginManager loginManager;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        loginManager = LoginManager.getInstance();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		request.getSession().setAttribute("username", username);

		int newValue = loginManager.login(username, password);
		request.getSession().setAttribute("loginStatus", newValue);
		
		
		if(newValue == 0)
		{
			User userLoggedIn = loginManager.getUser(username);
			request.getSession().setAttribute("permissions", userLoggedIn.getPermissions().toString());
			request.getRequestDispatcher("/login_success").forward(request, response);
		}
		else
		{
			request.getRequestDispatcher("/login_fail").forward(request, response);
		}
	}
	

}
