<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Spittr</title>
<!-- <style type="text/css"> @import url(css/style.css);</style> -->
<link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>
<body>
	<h1>Register</h1>
	<!--	
	In the preceding code, you set commandName to spitter . Therefore, there must be
	an object in the model whose key is spitter , or else the form won t be able to render
	(and you ll get JSP errors). That means you need to make a small change to Spitter-
	Controller to ensure that a Spitter object is in the model under the spitter key:
	-->
	<sf:form method="POST" commandName="spitter">


		<div>
			First Name:
			<sf:input path="firstName" />
			<sf:errors path="firstName" cssErrorClass="errors" />
		</div>
		<br />
		<div>
			Last Name:
			<sf:input path="lastName" />
			<sf:errors path="lastName" cssClass="errors" />
		</div>
		<br />
		<div>
			Username:
			<sf:input path="username" />
			<sf:errors path="username" cssClass="errors" />
		</div>
		<br />
		<div>
			Password:
			<sf:password path="password" />
			<sf:errors path="password" cssClass="errors" />
		</div>
		<br />
		<input type="submit" value="Register" />
	</sf:form>
</body>
</html>