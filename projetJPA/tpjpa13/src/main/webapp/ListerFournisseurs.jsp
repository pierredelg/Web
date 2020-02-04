<%@ page import="java.sql.*" %>
<%@ page import="fr.da2i.Fournisseurs" %>
<%@ page import="java.util.List" %>
<%@ page import="fr.da2i.Commandes" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page errorPage="ErrorPage.jsp" %>
<html lang="fr">
<head>
    <title>Lister les commandes</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="text-center">
<div class="c-container d-flex w-100 h-100 p-3 mr-5 ml-5 flex-column">
    <header class="masthead mb-auto">
        <div class="inner">
            <h3 class="masthead-brand">Projet JPA</h3>
            <nav class="nav nav-masthead justify-content-center">
                <a class="nav-link" href="ListerCommandes.jsp">Commandes</a>
                <a class="nav-link active" href="ListerFournisseurs.jsp">Fournisseurs</a>
                <a class="nav-link" href="ListerCommandesProduits.jsp">Commandes/Fournisseurs/Produits</a>
            </nav>
        </div>
    </header>
    <div class="panel panel-default text-center">
        <div class="panel-heading bg-dark">
            <strong class="panel-title text-white">Informations</strong>
        </div>
        <jsp:useBean id="daoCommande" class="fr.da2i.DAOCommandes" scope="application"/>
        <jsp:useBean id="daoFournisseurs" class="fr.da2i.DAOFournisseurs" scope="application"/>
        <%
            List<Fournisseurs> fournisseursList = daoFournisseurs.findAll();
            for(Fournisseurs fournisseurs : fournisseursList){
                List<Commandes> commandesList = daoCommande.findByFno(fournisseurs.getFno());
                int tailleListe = commandesList.size();
                int fno = fournisseurs.getFno();
        %>
        <table class="panel-body table table-hover table-bordered table-striped narrower table-color-grey">
            <tr>
        <th>Numéro du fournisseur </th><th class="text-center" colspan="<%=tailleListe%>"><%=fno%></th>
            </tr>
            <tr>
                <td>Numéro de commandes associées</td>
        <%

            for(Commandes commandes : commandesList){

                int num = commandes.getCno();
        %>
                <td class="text-center bg-lightgray"><%=num%></td>
        <%
        }
            %>
            </tr>
        </table>
            <%
            }
        %>
    </div>
</div>
</body>
</html>
