<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Spittr</title>
<style type="text/css">@import url(css/style.css);</style>
</head>
<body>
	<h1>Register</h1>

	<form method="POST" action="spitter/registration">
		First Name: <input type="text" name="firstName" /><br/>
		Last Name: <input type="text" name="lastName" /><br/>
		Username: <input type="text" name="username" /><br/>
		Password: <input type="password" name="password" /><br/>
		<input type="submit" path="Register" />
	</form>
</body>
</html>