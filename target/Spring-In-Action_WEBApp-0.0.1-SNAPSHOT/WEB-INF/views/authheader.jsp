<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div class="authbar">
	<span>Dear <strong>${loggedinuser}</strong>, Welcome to
		Spitter's Web App.
	</span> <span class="floatRight">&nbsp;<a href="<c:url value="/" />">Home</a>
	</span>
	
		<span class="floatRight">
		<sec:authorize
		access="hasRole('ADMIN') or hasRole('DB') or hasRole('USER')">
		 &nbsp;|&nbsp;<a href="<c:url value="/logout" />">Logout</a>
		 </sec:authorize>
		</span>
	

</div>
