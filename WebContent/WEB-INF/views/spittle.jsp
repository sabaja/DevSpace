<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
</head>
<body>
	<!%@include file="authheader.jsp"% >
	<div class="spittleView">

		<form method="POST" action="exception">
			<div class="spittleId">
				<c:out value="id:${spittle.id} " />
			</div>
			<div class="spittleMessage">
				<c:out value="${spittle.message}" />
			</div>
			<div>
				<span class="spittleTime"><c:out value="${spittle.time}" /></span>
			</div>
			<div>
				<button onclick="goBack()">Back</button>

				<script>
					function goBack() {
						window.history.back();
					}
				</script>
				<input type="submit" value="Launch Exception" />
				<!-- input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /-->
			</div>
		</form>
	</div>
</body>
</html>