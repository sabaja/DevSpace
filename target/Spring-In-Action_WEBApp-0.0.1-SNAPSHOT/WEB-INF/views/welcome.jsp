<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
</head>
<body>
	<s:escapeBody htmlEscape="true">
		Please press link below to enter into Spitter's home 
	</s:escapeBody>
	<h1>
		<a href="spitter"><s:message code="spittr.home" /></a>
	</h1>
	<s:escapeBody htmlEscape="true">
		Please press link below to login to your Spitter Profile
	</s:escapeBody>
	<h1>
		<a href="login"><s:message code="spittr.login" /></a>
	</h1>
</body>
<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
</html>