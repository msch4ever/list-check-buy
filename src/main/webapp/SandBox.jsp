<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.lang.Math" %>

<html>
<body>
<%
  double num = Math.random();
  if (num > 0.95) {
%>
<h2>Ты счастливчик, парень!</h2><p>(<%= num %>)</p>
<%
  }
%>
</body>
</html>
<html>
<head>
    <title>SandBox</title>
</head>
<body>

</body>
</html>
