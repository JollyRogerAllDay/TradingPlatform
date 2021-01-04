<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML >
<html>
	<head>
		<title>Trading Platform</title>
		<link rel="stylesheet" type="text/css" href="stylesheet.css" />
		<script type="text/javascript" src="javascript.js"></script>
	</head>
	<body>
		<div id="main">
			<div id="page_title">
			<img id="title_img" src="images/fdm_banner.PNG"/>
				<div id="page_title_subtitle"> 
				<c:choose>
					<c:when test="${sessionScope.loginStatus eq 0}">
						${sessionScope.username} is logged in.
						<form id="form1" method="post" action="logout">
							Username:
							<input type="text" size="10" name="username"/>
							<br/>
							Password:
							<input type="password" size="10" maxlength="16" name="password" onclick="highlight(this)"/>
							<Button id="logout_button" class="button" onmouseover="mouseOverButton(this);" onmouseout="mouseOutButton(this);">Logout</Button>
							<br/>
						</form> 
					</c:when>
					<c:otherwise>
						<b>Login of Trading Platform</b>
						<form id="form1" method="post" action="login">
							Username:
							<input type="text" size="10" name="username"/>
							<br/>
							Password:
							<input type="password" size="10" maxlength="16" name="password" onclick="highlight(this)"/>
							<Button id="login_button" align="center" class="button" onmouseover="mouseOverButton(this);" onmouseout="mouseOutButton(this);">Login</Button>
							<br/>
						</form> 
					</c:otherwise>
				</c:choose>					
				
				</div>  
			</div>
			
			<div id="main_content"> 
				<c:choose>
					<c:when test= "${loginStatus eq 0}">
						<c:choose>
							<c:when test="${sessionScope.permissions == 'SHAREHOLDER'}">
								<p>Welcome ${sessionScope.username}!</p>
								<div class="nav_menu">
										<b>Shareholder Menu</b><br/>
										<a href="portfolio">View Portfolio</a>
										<br/>
										<a href="TradeRequestServlet">Request Buy/Sell</a>
										<br/>
										<a href="">View Available Shares</a>
										<br/>
										<a href="">Manager Brokers</a>
										<br/>
										<a href="SendUserRequestServlet">Contact System Admin</a>
								</div>
							</c:when>
							<c:when test="${sessionScope.permissions == 'BROKER'}">
								<p>Welcome ${sessionScope.username}!</p>
								<div class="nav_menu">
										<b>Broker Menu</b><br/>
										<a href="TradeRequestsServlet">Trade Requests</a>
										<br/>
										<a href="TradeHistoryServlet">Trade History</a>
										<br/>
										<a href="ViewSharesServlet">View Available Shares</a>
										<br/>
										<a href="CommissionServlet">Commission</a>
										<br/>
										<a href="SendUserRequestServlet">Contact System Admin</a>
								</div>
							</c:when>
							<c:when test="${sessionScope.permissions == 'BROKER_SHAREHOLDER'}">
								<p>Welcome ${sessionScope.username}!</p>
								<div class="nav_menu">
										<b>Broker Menu</b><br/>
										<a href="TradeRequestsServlet">Trade Requests</a>
										<br/>
										<a href="TradeHistoryServlet">Trade History</a>
										<br/>
										<a href="ViewSharesServlet">View Available Shares</a>
										<br/>
										<a href="CommissionServlet">Commission</a>
										<br/>
										<br/>
										<b>Shareholder Menu</b><br/>
										<a href="portfolio">View Portfolio</a>
										<br/>
										<a href="TradeRequestServlet">Request Buy/Sell</a>
										<br/>
										<a href="">View Available Shares</a>
										<br/>
										<a href="">Manager Brokers</a>
										<br/>
										<a href="SendUserRequestServlet">Contact System Admin</a>
								</div>
							</c:when>
							<c:when test="${sessionScope.permissions == 'SYSADMIN'}">
								<p>Welcome ${sessionScope.username}!</p>
								<div class="nav_menu">
									<b>Admin Menu</b><br/>
										<a href="user_management">Manage Users</a>
										<br/>
										<a href="ViewUserRequestServlet">View User Requests</a>
								</div>
							</c:when>
						</c:choose>
						
					</c:when>
					<c:when test= "${loginStatus eq 1}">
						
					</c:when>
					<c:when test= "${loginStatus eq 2}">
						
					</c:when>
					<c:when test= "${loginStatus eq 3}">
						
					</c:when>
				</c:choose>	
				
											
				<div id="contentarea">
					
				</div>
			</div>
		</div>
</body>
</html>