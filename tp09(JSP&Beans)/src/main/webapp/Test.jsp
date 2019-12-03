<%--
  Created by IntelliJ IDEA.
  User: delgranp
  Date: 03/12/2019
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%-- scope session le compteur reste propre à l'utilisateur --%>
    <%-- scope application le compteur reste propre à l'instance du context(demarrage du serveur) --%>
    <%-- scope request le compteur reste propre à la requete (se réinitialise à chaque requete) --%>

    <jsp:useBean id="p" class="fr.da2i.Message" scope="application" />
    <%=p%>
</body>
</html>
