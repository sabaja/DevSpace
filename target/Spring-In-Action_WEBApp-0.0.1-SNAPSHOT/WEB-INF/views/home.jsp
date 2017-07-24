<html xmlns:th="http://www.thymeleaf.org">
<head>
<title>Spittr</title>
<link rel="stylesheet" type="text/css" th:href="@{/css/style.css}"></link>
</head>
<body>
	<h1>Welcome to Spittr</h1>
	<a th:href="@{/spittles}">Spittles</a> |
	<a th:href="@{/spitter/register}">Register</a>
</body>
</html>