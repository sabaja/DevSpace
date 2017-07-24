<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false"%>
<html>
<head>
<title>Spittr</title>
<style type="text/css">@import url(css/style.css);</style>
<!-- <link rel="stylesheet" type="text/css" href="style.css" /> -->
</head>
<body>

	<h1><s:message code="spittr.home" /></h1>
	<a href="<c:url value="/spittle" />">Spittles</a> |
	<a href="<c:url value="/spitter/registration" />">Register</a>
</body>
</html>