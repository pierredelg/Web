<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="fr">
<head>
    <title>Erreur</title>
    <link rel="stylesheet" type="text/css" href="css/erreur.css">
</head>
<body>
<%
    String messageErreur = (String) session.getAttribute("erreur");
    session.invalidate();

    if(messageErreur == null){
        messageErreur = "Une erreur s'est produite lors du chargement de la page";
    }
%>
    <div id='erreur'>
    <div id='erreur-text'>
        <span>Erreur</span>
        <p><%=messageErreur%></p>
        <p class='hmpg'><a href='login.html' class="back">Revenir à la page d'acceuil</a></p>
    </div>
</div>
</body>
</html>
