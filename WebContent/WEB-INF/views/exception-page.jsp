<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
<h3>Exception Page</h3>
 <p>
  status:<br/><%=response.getStatus()%>
  </p>
  <p>
  Exception Message:<br/>${exception.message}
  </p>
  <p>
  Exception type:<br/>${exception['class'].name}
  </p>
</body>
</html>