<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profile</title>
<style type="text/css">
@import url(css/style.css);
</style>
</head>
<body>
	<form method="GET" action="../spitter">
		<h1>Your Profile</h1>
		User:
		<c:out value="${spitter.username}" />
		<br /> Password:
		<c:out value="${spitter.password}" />
		<br /> FirstName:
		<c:out value="${spitter.firstName}" />
		<br /> LastName:
		<c:out value="${spitter.lastName}" />
		<br /> Email:
		<c:out value="${spitter.email}" />
		<br />
		<div>
			<input type="submit" value="Home" />
		</div>
	</form>
</body>
</html>