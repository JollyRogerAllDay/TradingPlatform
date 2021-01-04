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
	<body  onload="calcHeight()" >
		<div id="main">
			<div id="page_title">
			<img id="title_img" src="images/fdm_banner.PNG"/>
				<div id="page_title_subtitle"> 
									
				<b>Login to Trading Platform</b>
					<form id="form1" method="post" action="login">
						Username:
						<input type="text" size="10" name="username"/>
						<br/>
						Password:
						<input type="password" size="10" maxlength="16" name="password" onclick="highlight(this)"/>
						<Button id="login_button" align="center" class="button" onmouseover="mouseOverButton(this);" onmouseout="mouseOutButton(this);">Login</Button>
						&nbsp;<span id="wrong_info" style="font-size: x-small" align="left"></span>
						<br/>
					</form> 
				</div>  
			</div>
			
			<div id="main_content"> 
				<c:choose>
					<c:when test= "${loginStatus eq 1}">
						<p>${param.username} is currently banned.</p>
					</c:when>
					
					<c:when test= "${loginStatus eq 2}">
						<p>Invalid login information. Please ensure the username and password are correct.</p>
					</c:when>
					
					<c:when test= "${loginStatus eq 3}">
						<p>No user with that username exists.</p>
					</c:when>
				</c:choose>	
				
											
				<div id="contentarea">
					
				</div>
			</div>

</body>
</html>