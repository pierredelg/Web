<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Annuaire</title>
</head>
<body>
<jsp:useBean id="p" class="fr.da2i.DAOPersonne" scope="session"/>
<%=p.find("Delgrange").getHTML()%>


</body>
</html>
