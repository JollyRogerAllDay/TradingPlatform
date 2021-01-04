package com.fdmgroup.TradingPlatform.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdmgroup.TradingPlatform.UserManager;

/**
 * Servlet implementation class ProcessUserRequest
 */
public class ProcessUserRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserManager userManager;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessUserRequestServlet() {
        super();
        userManager = UserManager.getInstance();
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
		String username = request.getSession().getAttribute("username").toString();
		String requestType = request.getParameter("type");
		
		if(userManager.addAddRequest(username, requestType))
			request.setAttribute("status", "success");
		else
			request.setAttribute("status", "fail");
		
		request.getRequestDispatcher("send_user_request").forward(request, response);
	}

}
