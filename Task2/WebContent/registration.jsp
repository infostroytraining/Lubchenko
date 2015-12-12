<%@page import="botdetect.web.Captcha"%>


	
<!DOCTYPE html>
<html>
<head>
<title>Registration</title>
</head>
<body>
	<div id="heading">
		<h1>Fill the form, please</h1>
	</div>
		<p>${message}</p>
	<form id="registration_form" action="controller" method="post" >
		<input type="hidden" name="action" value="registr" />
		
		<input type="text" name="email" placeholder="email"  /><br>
		<input type="text" name="name" placeholder="First name"  /><br>
		<input type="text" name="surname" placeholder="Surname"  /><br>
		<input type="password" name="password" placeholder="Password"  /><br>
		<input type="password" name="confirmPassword" placeholder="Confirm password" /><br><br>
		
		<label for="avatar">You can add a photo to your account :)  (fake file loader)</label><br>
		<input name="description" type="text"/><br>
		<input name="avatar" type="file"/><br><br>
		
		<label for="captchaCodeTextBox">Confirm you are a human</label><br>
		<% 
			Captcha captcha = Captcha.load(request, "captcha"); 
			captcha.renderCaptchaMarkup(pageContext.getServletContext(), 
			    pageContext.getOut());
		%>

		<input id="captchaCodeTextBox" type="text" name="captchaCodeTextBox" /><br><br>
		<input class="button" type="submit" value="Registr"/>  	
	</form>
</body>
</html>
