<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<title>Spitter's spittles</title>
<link href="<c:url value='css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='css/app.css' />" rel="stylesheet"></link>
<%@include file="authheader.jsp"%>
<p>List of spittles:</p>

<c:forEach items="${spittleList}" var="spittle">
	<!--c:set var="${spitterIds}" /-->
	<ul>
		<li id="spittle_<c:out value="spittle.id"/>">
			<div class="spittleMessage">
				<a href="./spittle/${spittle.id}"><c:out
						value="${spittle.message}" /> </a>

			</div>
			<div>
				<span class="spitterName" style="color: #337ab7; font-weight: bold">[
					spitter name: <c:out value="${spittle.spitter.getUsername()}" />,
				</span>
				<span class="spitterId"	style="color: #337ab7; font-weight: bold"> spitter id (<c:out
						value="${spittle.spitter.getId()}" />),
				</span> <span class="spittleTime"
					style="color: #337ab7; font-weight: bold"> date time: <c:out
						value="${spittle.time}" /> ]
				</span>
				<!--  span class="spittleLocation"
					style="color: darkolivegreen; font-weight: bold"> ( <c:out
						value="${spittle.latitude}" />, <c:out
						value="${spittle.longitude}" /> )
				</span-->
			</div>
		</li>
	</ul>
</c:forEach>
