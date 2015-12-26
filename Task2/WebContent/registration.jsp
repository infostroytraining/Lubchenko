<%@ page import="botdetect.web.Captcha"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Registration</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">
</head>
<body>
	<div id="heading">
		<h1>Fill the form, please</h1>
	</div>
	<p>${message}</p>
	<form class="form-forizontal" id="registration_form"
		action="controller" method="post">

		<input type="hidden" name="action" value="registr" />

		<div class=form-group>
			<label for="inputEmail">E-mail:</label> <input type="email"
				class="form-control" name="email" id="inputEmail"
				placeholder="E-mail" required="required" />
		</div>

		<div class=form-group>
			<label for="inputName">Name:</label> <input type="text"
				class="form-control" id="inputName" name="name"
				placeholder="First name" required="required" />
		</div>

		<div class=form-group>
			<label for="inputSurname">Surname:</label> <input type="text"
				class="form-control" id="inputSurname" name="surname"
				placeholder="Surname" required="required" />
		</div>

		<div class=form-group>
			<label for="inputPassword">Password:</label> <input type="password"
				class="form-control" id="inputPassword" name="password"
				placeholder="Password" pattern="[A-Za-z0-9]{8,}" required="required" />
		</div>

		<div class=form-group>
			<label for="confirmPassword">Confirm password:</label> <input
				type="password" class="form-control" id="confirmPassword"
				name="confirmPassword" placeholder="Confirm password"
				required="required" />
		</div>

		<div class=form-group>
			<br> <label for="avatar">You can add a photo to your
				account :) (fake file loader)</label><br> <input type="file"
				class="form-control" name="imageUrl" /><br>
		</div>

		<div class=form-group>
			<label for="captchaCodeTextBox">Confirm you are a human</label><br>
			<%
				Captcha captcha = Captcha.load(request, "captcha");
				captcha.renderCaptchaMarkup(pageContext.getServletContext(), pageContext.getOut());
			%>

			<input id="captchaCodeTextBox" type="text" class="form-control"
				name="captchaCodeTextBox" required="required" />
		</div>
		<input class="btn btn-primary" type="submit" value="Registr" />
	</form>
</body>
</html>
