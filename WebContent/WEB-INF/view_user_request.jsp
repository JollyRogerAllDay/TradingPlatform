<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="stylesheet.css" />
		<script type="text/javascript" src="javascript.js"></script>
<title>User Requests</title>
</head>
<body>
	<div id="main">
			<div id="page_title">
			<img id="title_img" src="images/fdm_banner.PNG"/>
				<div id="page_title_subtitle"> 
									
				${sessionScope.username} is logged in.
					<form id="form1" method="post" action="logout">
						Username:
						<input type="text" size="10" name="username"/>
						<br/>
						Password:
						<input type="password" size="10" maxlength="16" name="password" onclick="highlight(this)"/>
						<Button id="logout_button" align="center" class="button" onmouseover="mouseOverButton(this);" onmouseout="mouseOutButton(this);">Logout</Button>
						&nbsp;<span id="wrong_info" style="font-size: x-small" align="left"></span>
						<br/>
					</form> 
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
										<a href="">Contact System Admin</a>
								</div>
							</c:when>
							<c:when test="${sessionScope.permissions == 'BROKER'}">
								<p>Welcome ${sessionScope.username}!</p>
								<div class="nav_menu">
										<b>Broker Menu</b><br/>
										<a href="TradeRequestsServlet"">Trade Requests</a>
										<br/>
										<a href="TradeHistoryServlet">Trade History</a>
										<br/>
										<a href="ViewSharesServlet">View Available Shares</a>
										<br/>
										<a href="CommissionServlet">Commission</a>
								</div>
							</c:when>
							<c:when test="${sessionScope.permissions == 'BROKER_SHAREHOLDER'}">
								<p>Welcome ${sessionScope.username}!</p>
								<div class="nav_menu">
										<b>Broker Menu</b><br/>
										<a href="TradeRequestsServlet"">Trade Requests</a>
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
										<a href="">Contact System Admin</a>
								</div>
							</c:when>
							<c:when test="${sessionScope.permissions == 'SYSADMIN'}">
								<p>Welcome ${sessionScope.username}!</p>
								<div class="nav_menu">
									<form id="formList" class="nav_menu" method="post">
										<b>Admin Menu</b><br/>
											<a href="user_management">Manage Users</a>
											<br/>
											<a href="ViewUserRequestServlet">View User Requests</a>

									</form>
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
				<h1>View User Request</h1>
					<table id="user_request_table">
						<tr>
							<td>
							   User
							</td>
							<td>	
								Admin
							</td>
							<td>
								Type
							</td>
							<td>
								Status
							</td>
						</tr>
						<c:forEach var="request" items="${sessionScope.user_requests }">
							<tr>
								<td>
								    ${request.user}
								</td>
								<td>	
									${request.admin}
								</td>
								<td>
									${request.requestType }
								</td>
								<td>
									${request.status }
								</td>
							</tr>
						</c:forEach>
					</table>
			</div>
		</div>
</body>
</html>