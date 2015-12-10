<%@page import="botdetect.web.Captcha"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<!DOCTYPE html>
<html>
<head>
<title>Registration</title>
</head>
<body>
	<div id="heading">
		<h1>Fill the form, please</h1>
	</div>
		
	<form id="registration_form" action="controller" method="post" 
	>
		<input type="hidden" name="action" value="registr" />
		
		<input type="email" name="email" placeholder="email" required="required" /><br>
		<input type="text" name="name" placeholder="First name" required="required" /><br>
		<input type="text" name="surname" placeholder="Surname" required="required" /><br>
		<input type="password" name="password" placeholder="Password" required="required" /><br>
		<input type="password" name="confirmPassword" placeholder="Confirm password" required="required" /><br><br>
		
		<label for="avatar">You can add a photo to your account :)  (fake file loader)</label><br>
		<input name="description" type="text"><br>
		<input name="avatar" type="file"><br><br>
		
		<label for="captchaCodeTextBox">Confirm you are a human (fake captcha)</label><br>
		<% 
			// Adding BotDetect Captcha to the page
			Captcha captcha = Captcha.load(request, "exampleCaptcha"); 
			captcha.renderCaptchaMarkup(pageContext.getServletContext(), 
			    pageContext.getOut());
		%>

		<input id="captchaCodeTextBox" type="text" name="captchaCodeTextBox" /><br><br>
		<input class="button" type="submit" value="Registr">  
	</form>
</body>
</html>
