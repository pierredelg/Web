<%--
  Created by IntelliJ IDEA.
  User: pierre
  Date: 21/11/2019
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Erreur</title>
</head>
<body>
<div style="text-align: center;">
    <h3><%= exception.toString() %></h3>
</div>
</body>
</html>
