<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>

<%-- <%@ taglib prefix="sec" --%>
<%-- 	uri="http://www.springframework.org/security/tags"%> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spitter's users list</title>
<link href="<c:url value='css/bootstrap.css' />" rel="stylesheet"></link>
<link href="<c:url value='css/app.css' />" rel="stylesheet"></link>
</head>

<body>
<%@include file="authheader.jsp"%>
	<div class="generic-container">

		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">List of Spitter's users </span>
			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Firstname</th>
						<th>Lastname</th>
						<th>Email</th>
						<th>Username</th>
						<sec:authorize access="hasRole('ADMIN') or hasRole('DB')">
							<th width="100"></th>
						</sec:authorize>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="user">
						<tr>
							<td>${user.firstName}</td>
							<td>${user.lastName}</td>
							<td>${user.email}</td>
							<td>
								<a href="javascript:CenteredPopup()">
									<a href="<c:url value='/spitter/getrole-${user.username}'/>">
									${user.username}
									</a>
								</a>
							</td>
							<sec:authorize access="hasRole('ADMIN') or hasRole('DB')">
								<td><a href="<c:url value='/edit-user-${user.username}' />"
									class="btn btn-success custom-width">edit</a></td>
							</sec:authorize>
							<sec:authorize access="hasRole('ADMIN')">
								<td><a
									href="<c:url value='/delete-user-${user.username}' />"
									class="btn btn-danger custom-width">delete</a></td>
							</sec:authorize>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<sec:authorize access="hasRole('ADMIN')">
			<div class="well">
				<!-- 				<form action="/registration" method="post"> -->
				<!-- 					<button type="submit" name="Add_New_User" value="Add New User" -->
				<!-- 						class="btn-link"></button> -->
				<!-- 				</form> -->
				<a href="<c:url value='/registration' />">Add New User</a>
			</div>
		</sec:authorize>
	</div>
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</body>
<script type="text/javascript">

	function CenteredPopup() {
		var w = 400;
		var h = 250;
		var l = Math.floor((screen.width - w) / 2);
		var t = Math.floor((screen.height - h) / 2);

		window.open("getrole.jsp", "_blank", "width=" + w + ",height=" + h + ",top="
				+ t + ",left=" + l);
	}

</script>
</html>