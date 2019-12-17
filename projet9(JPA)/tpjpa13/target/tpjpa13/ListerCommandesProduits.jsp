<%@ page import="java.sql.*" %>
<%@ page import="fr.da2i.Commandes" %>
<%@ page import="java.util.List" %>
<%@ page import="fr.da2i.Fournisseurs" %>
<%@ page import="fr.da2i.Produits" %>
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
                <a class="nav-link" href="ListerFournisseurs.jsp">Fournisseurs</a>
                <a class="nav-link active" href="ListerCommandesProduits.jsp">Commandes/Fournisseurs/Produits</a>
            </nav>
        </div>
    </header>
    <div class="panel panel-default text-center">
        <div class="panel-heading bg-dark">
            <strong class="panel-title text-white">Informations</strong>
        </div>
        <jsp:useBean id="daoCommande" class="fr.da2i.DAOCommandes" scope="application"/>
        <jsp:useBean id="daoFournisseurs" class="fr.da2i.DAOFournisseurs" scope="application"/>
        <jsp:useBean id="daoProduits" class="fr.da2i.DAOProduits" scope="application"/>
        <table class="panel-body table table-hover table-bordered table-striped narrower table-color-grey">
            <thead>
            <tr class="text-center">
                <th>Numero de commande</th>
                <th>Nom du fournisseur</th>
                <th>Libelle du produit</th>
            </tr>
            </thead>
            <tbody class="bg-lightgray">
            <%
                List<Commandes> commandesListe = daoCommande.findAll();
                for(Commandes commandes : commandesListe){
                    int cno = commandes.getCno();
                    Fournisseurs fournisseurs = daoFournisseurs.find(commandes.getFno());
                    String nomFournisseur = fournisseurs.getNom();
                    Produits produits = daoProduits.find(commandes.getPno());
                    String libelleProduit = produits.getLibelle();
            %>
            <tr class="text-center">
                <td><%=cno%></td>
                <td><%=nomFournisseur%></td>
                <td><%=libelleProduit%></td>
            <%
                }
            %>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
