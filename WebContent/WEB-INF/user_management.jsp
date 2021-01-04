<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Management</title>
<link rel="stylesheet" type="text/css" href="stylesheet.css" />
		<script type="text/javascript" src="javascript.js"></script>
		<script type="text/javascript">
		function displayElement(id)
		{
			document.getElementById(id).style.display='block';
			document.form1.userToManage.display = false;
		}
		
		function displayUserInfo()
		{
			displayElement("userInfo");
			//removeElement("sell");
		}
		
		function testUsername()
		{
			var username = document.getElementById("userToManage").value;
			if(username=='' )
			{
				document.getElementById("errorMessage").innerHTML = "Please enter a username.";
				return false;
			}
			else
			{
				return true;
			}
		}
		
		function testFields()
		{
			var newUsername = document.getElementById("newUsername").value;
			var newPassword = document.getElementById("newPassword").value;
			if(newUsername=='' || newPassword=='')
			{
				document.getElementById("errorMessage").innerHTML = "Please enter a new username and password.";
				return false;
			}
			else
			{
				return true;
			}
		}
		</script>
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
						<Button id="logout_button" class="button" onmouseover="mouseOverButton(this);" onmouseout="mouseOutButton(this);">Logout</Button>
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
					<h1>User Management</h1>
					<form id="form1" method="post" action="UserManagementServlet"  onSubmit="return !!(testUsername() & testFields())">
						Username:
						<input type="text" id="userToManage" size="10" maxlength="16" name="userToManage" onclick="highlight(this)"/>
						<br/>
						<Button name="button" value="add_user_button" class="button" onclick="testUsername()" onmouseover="mouseOverButton(this);" onmouseout="mouseOutButton(this);">Add User</Button>
						<Button name="button" value="delete_user_button" class="button" onclick="testUsername()" onmouseover="mouseOverButton(this);" onmouseout="mouseOutButton(this);">Delete User</Button>
						<Button name="button" value="ban_user_button" class="button" onclick="testUsername()" onmouseover="mouseOverButton(this);" onmouseout="mouseOutButton(this);">Ban User</Button>
						<Button name="button" value="update_user_details_button" class="button" onclick="displayUserInfo()" onmouseover="mouseOverButton(this);" onmouseout="mouseOutButton(this);">Update Details</Button>
					<div id="userInfo" style="display:none;">
						New Username:
						<input type="text" size="10" maxlength="16" id="newUsername" name="newUsername" onclick="highlight(this)"/><br/>
						New Password:
						<input type="text" size="10" maxlength="16" id="newPassword" name="newPassword" onclick="highlight(this)"/><br/>
						New Permission:
						<select name="type">
							  <option value="BROKER">BROKER</option>
							  <option value="SHAREHOLDER">SHAREHOLDER</option>
							  <option value="BROKER_SHAREHOLDER">BROKER_SHAREHOLDER</option>
						</select></br>
						<Button name="button" value="update_user_button" class="button" onclick="testUsername();testFields()" onmouseover="mouseOverButton(this);" onmouseout="mouseOutButton(this);">Submit Information</Button>
					</form> 
					</div>
					<p id="errorMessage" style="color:red">
					</p>
					<p>
						${status }
					</p> 
				</div>
			</div>
		</div>
</body>
</html>