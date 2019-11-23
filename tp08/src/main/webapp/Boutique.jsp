<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: pierre
  Date: 22/11/2019
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Boutique</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ page errorPage="ErrorPage.jsp" %>
</head>
<body>
<%
    String urlPsql = config.getServletContext().getInitParameter("url");
    String nomPsql = config.getServletContext().getInitParameter("login");
    String mdpPsql = config.getServletContext().getInitParameter("mdp");
    String driverPsql = config.getServletContext().getInitParameter("driver");
    String num = request.getParameter("num");
    String pageDemande = request.getParameter("pageDemande");
    String libelle = request.getParameter("libelle");
    String prix = request.getParameter("prix");
%>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="Boutique.jsp">Mon Ecommerce</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
            <form class="form-inline my-2 my-lg-0">
                <a class="btn btn-success btn-sm ml-3" href="cart.html">
                    <i class="fa fa-shopping-cart"></i> Panier
                    <span class="badge badge-light">1</span>
                </a>
            </form>
        </div>
    </div>
</nav>
<section class="jumbotron text-center">
    <div class="container">
<%if(pageDemande != null && pageDemande.equals("panier")){%>
        <h1 class="jumbotron-heading">Mon panier</h1>
<%}else{%>
        <h1 class="jumbotron-heading">Les produits disponibles</h1>
        <%}%>
    </div>
</section>
<div class="container mb-4">
    <div class="row">
        <div class="col-12">
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">Libelle</th>
                        <th scope="col">Numero</th>
                        <th scope="col" class="text-right">Prix</th>
                        <th> </th>
                    </tr>
                    </thead>
                    <tbody>
                    <%if(pageDemande != null && pageDemande.equals("panier")){
                        session.getAttribute("tab");



                    %>
                    <tr>
                        <td><%=libelle%></td>
                        <td><%=num%></td>
                        <td class="text-right"><%=prix%></td>
                        <td></td>
                    </tr>
                    <%}else{




                    %>
                        <h1 class="jumbotron-heading">Les produits disponibles</h1>
                    <%}%>
                    <tr>
                        <td>Product Name Dada</td>
                        <td>In stock</td>
                        <td class="text-right">124,90 €</td>
                        <td class="text-right"><button class="btn btn-sm btn-danger"><i class="fa fa-trash"></i> </button> </td>
                    </tr>
                    <tr>
                        <td>Product Name Toto</td>
                        <td>In stock</td>
                        <td class="text-right">33,90 €</td>
                        <td class="text-right"><button class="btn btn-sm btn-danger"><i class="fa fa-trash"></i> </button> </td>
                    </tr>
                    <tr>
                        <td>Product Name Titi</td>
                        <td>In stock</td>
                        <td class="text-right">70,00 €</td>
                        <td class="text-right"><button class="btn btn-sm btn-danger"><i class="fa fa-trash"></i> </button> </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col mb-2">
            <div class="row">
                <div class="col-sm-12  col-md-6">
                    <button class="btn btn-block btn-light">Voir les produits</button>
                </div>
                <div class="col-sm-12 col-md-6 text-right">
                    <button class="btn btn-lg btn-block btn-success text-uppercase">Panier</button>
                </div>
            </div>
        </div>
    </div>
</div>
    </body>
</html>
<%if(pageDemande != null && pageDemande.equals("panier")){%>

<%}
else{

    Class.forName(driverPsql);
    Connection con = DriverManager.getConnection(urlPsql,nomPsql,mdpPsql);
    String query = "Select * from produits where num=?";
    PreparedStatement ps = con.prepareStatement(query);
    ps.setString(1,num);

    ResultSet rs = ps.executeQuery();
  while(rs.next()) {


  }
}
%>