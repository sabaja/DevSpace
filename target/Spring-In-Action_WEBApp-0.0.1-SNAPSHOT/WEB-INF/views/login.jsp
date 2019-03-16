<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
<title>Spitter Login Page</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<link rel="stylesheet" type="text/css"
	href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
</head>
<body>
	<div align="center">
		<h3>Spitter Login Page</h3>
	</div>
	<div style="color:#337ab7" align="center">
		<a href="/Spring-In-Action_WEBApp/"><h6>
				spitter's home
			</h6></a>
	</div>
	<div align="center" id="mainWrapper">
		<div class="login-container">
			<div class="login-card">
				<div class="login-form">
					<c:url var="loginUrl" value="/login" />
					<form action="${loginUrl}" method="post" class="form-horizontal">
						<c:if test="${param.error != null}">
							<div class="alert alert-danger">
								<p>Invalid username and password.</p>
							</div>
						</c:if>
						<c:if test="${param.logout != null}">
							<div class="alert alert-success">
								<p>You have been logged out successfully.</p>
							</div>
						</c:if>

						<table>
							<tr>
								<div class="input-group input-sm">
									<td td align="right"><label class="input-group-addon"
										for="username"><i class="fa fa-user"></i></label></td>
									<td><input type="text" class="form-control" id="username"
										name="username" placeholder="Enter Username" required></td>
								</div>
							</tr>
							<tr>
								<div class="input-group input-sm">
									<td td align="right"><label class="input-group-addon"
										for="password"><i class="fa fa-lock"></i></label></td>
									<td><input type="password" class="form-control"
										id="password" name="password" placeholder="Enter Password"
										required></td>
								</div>
							</tr>
							<tr>
								<div class="input-group input-sm">
									<td td align="right"><div class="checkbox">
											<label><input type="checkbox" id="rememberme"
												name="remember-me"></td>
									<td>Remember Me</label>
								</div>
								</td>
								</div>
							</tr>
							<tr align="right">
								<td></td>
								<td><input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />

									<div class="form-actions">
										<input type="submit"
											class="btn btn-block btn-primary btn-default" value="Log in">
									</div></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>
