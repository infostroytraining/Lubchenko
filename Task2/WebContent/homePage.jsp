<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<title>Insert title here</title>
</head>
<body>

	<p>Hi there! It is nothing worth attention in this app, anyway it is no time to describe, you should register or login immediately</p><br><br>
	<a href="/registration.jsp"><BUTTON>REGISTRATION</BUTTON></a><br><br>

	<form action="controller" method="post">
		<input type="hidden" name="action" value="login" /> 
		<input type="text" name="email" placeholder="E-mail" required="required" />
		<input type="password" name="password" placeholder="Password" required="required" /> 
		<input class="button" type="submit"
							value="Login"/>
	</form>


</body>
	</html>
