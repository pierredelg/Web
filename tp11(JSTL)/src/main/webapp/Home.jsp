<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page errorPage="Error.jsp" %>
<html>
<head>
    <title>Home</title>
</head>
<%
    List<String> noms = new ArrayList<String>();
    noms.add("Jean");
    noms.add("Jacques");
    pageContext.setAttribute("noms", noms);

%>

<strong>Recherche d'un objet avec scope:</strong> ${requestScope.personne}
<br><br>
<strong>Recherche d'un objet sans scope:</strong> ${personne}
<br><br>
<strong>Operateur [] :</strong> ${applicationScope["User.Cookie"]}
<br><br>
<strong>Usage du . en cascade:</strong> ${sessionScope.employe.adresse.adresse}
<br><br>
<strong>Elements d'une liste:</strong> ${noms[1]}
<br><br>
<strong>Entete HTTP:</strong> ${header["Accept-Encoding"]}
<br><br>
<strong>Cookie utilisteur:</strong> ${cookie["User.Cookie"].value}
<br><br>
<strong>Contexte de page:</strong> HTTP Method is ${pageContext.request.method}
<br><br>
<strong>Accès aux params du contexte:</strong> ${initParam.AppID}
<br><br>
<strong>Operateur arithmétique:</strong> ${initParam.AppID + 200}
<br><br>
<strong>Operateur de comparaison:</strong> ${initParam.AppID < 200}
<br><br>
<body>

</body>
</html>
