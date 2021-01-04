<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML >
<html>
	<head>
		<title>Trading Platform</title>
		<link rel="stylesheet" type="text/css" href="stylesheet.css" />
		<script type="text/javascript" src="javascript.js">
		</script>
	</head>
	<body>
		<div id="main">
			<div id="page_title">
			<a href="index.jsp"><img id="title_img" src="images/fdm_banner.PNG"/></a>
				<div id="page_title_subtitle"> 
			
							
						<b>Login of Trading Platform</b>
						<form id="form1" method="post" action="login">
						Username:
						<input type="text" size="10" name="username"/>
						<br/>
						Password:
						<input type="password" size="10" maxlength="16" name="password" onclick="highlight(this)"/>
						<Button id="login_button" class="button" onmouseover="mouseOverButton(this);" onmouseout="mouseOutButton(this);">Login</Button>
						<br/>
					</form> 
						<!-- 
					<c:choose>
						<c:when test= "${sessionScope.loginStatus eq 0}">
							${sessionScope.username} is logged in.
							<form id="form1" method="post" action="logout">
								Username:
								<input type="text" size="10" name="username"/>
								<br/>
								Password:
								<input type="password" size="10" maxlength="16" name="password" onclick="highlight(this)"/>
								<Button id="logout_button" align="center" class="button" onmouseover="mouseOverButton(this);" onmouseout="mouseOutButton(this);">Logout</Button>
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
					</c:choose> -->
				</div>  
			</div>
			
			<div id="main_content">
				<h1 align="center">Welcome to the Ultimate Trading Platform!</h1>
					<p class="p_content">The trading platform is an application that is designed to act as a stock trading system. 
						</br>
						If you are new to the platform, <a href="register.html" style="color: #0a287b;">click here to register</a>. If you have already signed up, please login at
						the top of the screen by entering the correct username and password.
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
					 </p>
				<!-- <div id="footer" align="bottom">
					Michael Young FDM Training Academy - Toronto, Ontario, Canada
		
				</div> -->
			 </div>
		</div><!-- main-->

		
	</body>
</html>