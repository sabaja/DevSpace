<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
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
				<span class="spitterId"
					style="color: darkcyan; font-weight: bold">[ spitter id: <c:out
						value="${spittle.spitter.getId()}" />, spitter name: <c:out
						value="${spittle.spitter.getUsername()}" />
				</span> 
				<span class="spittleTime"
					style="color: darkcyan; font-weight: bold">
					, date time: <c:out
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
