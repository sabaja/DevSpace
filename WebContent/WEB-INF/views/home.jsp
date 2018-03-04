<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page session="false"%>
<html>
<head>
<title>Spitter Home</title>
<!-- style type="text/css">@import url(css/style.css);</style -->
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<!-- <link rel="stylesheet" type="text/css" href="style.css" /> -->
</head>
<%@include file="authheader.jsp"%>
<body>
	
	<h1>
		<s:message code="spittr.home" />
	</h1>
	<a href="<c:url value="/spittle" />">&nbsp;&nbsp;&nbsp;Spittles</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
	<a href="<c:url value="/spitter/registration" />">Register</a>
	<sec:authorize access="hasRole('ADMIN') or hasRole('DB')">
		<h1>
		<s:message code="spittr.admin" />
	</h1>
		&nbsp;&nbsp;&nbsp;<a href="<c:url value="/list" />">Spitter users</a>
	</sec:authorize>
</body>
<input type="hidden" name="${_csrf.parameterName}"
	value="${_csrf.token}" />
</html>