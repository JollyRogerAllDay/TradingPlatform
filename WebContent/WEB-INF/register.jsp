<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML >
<html>
	<head>
		<title>Register</title>
		<link rel="stylesheet" type="text/css" href="stylesheet.css" />
		<script type="text/javascript" src="javascript.js">
		</script>
	</head>
	<body  onload="calcHeight();" >
		<div id="main">
			<div id="page_title">
			<a href="/home"><img id="title_img" src="images/fdm_banner.PNG"/></a>
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

				</div>  
			</div>
			
			<div id="main_content">
			
				<c:choose>
					<c:when test="${registerStatus eq 0}">
						${sessionScope.username } has been registered successfully! Feel free to login.
						<br/>
						<a href="home">Return home.</a>
					</c:when>
					<c:when test="${registerStatus eq 1}">
						There was an error adding the user to the database. The user may already exist.
						<br/>
						<a href="register.html">Back</a>
					</c:when>
					<c:otherwise>
						Unable to complete registration.
					</c:otherwise>
				</c:choose>
			 </div>
		</div><!-- main-->
	</body>
</html>


