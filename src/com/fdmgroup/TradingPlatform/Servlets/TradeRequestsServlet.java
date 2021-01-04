package com.fdmgroup.TradingPlatform.Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdmgroup.TradingPlatform.Broker;
import com.fdmgroup.TradingPlatform.LoginManager;
import com.fdmgroup.TradingPlatform.Trade;



/**
 * Servlet implementation class TradeRequestsServlet
 * Broker trade requests
 */
public class TradeRequestsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Broker broker;
	private ArrayList<Trade> tradeRequests;
	private LoginManager loginManager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TradeRequestsServlet() {
        super();
        loginManager = LoginManager.getInstance();
        tradeRequests = new ArrayList<Trade>();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		broker = (Broker) loginManager.getUser(request.getSession().getAttribute("username").toString());
		tradeRequests = broker.getTradeRequests();

		request.getSession().setAttribute("requests", tradeRequests);
		
		request.getRequestDispatcher("/trade_requests").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
