<%@ page import="fr.da2i.Compteur" %><%--
  Created by IntelliJ IDEA.
  User: delgranp
  Date: 19/11/2019
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Compteur</title>
</head>
<body>
<%
    Compteur global = (Compteur) application.getAttribute("global");
    if(global == null){
        global = new Compteur();
        application.setAttribute("global",global);
    }
    global.incr();

    Compteur local = (Compteur) application.getAttribute("local");
    if(local == null){
        local = new Compteur();
        application.setAttribute("local",local);
    }
    local.incr();
%>
<h1>
    Vous avez accédé <%= local %> fois à cette page sur les <%= global %> accès effectués.
</h1>
</body>
</html>
