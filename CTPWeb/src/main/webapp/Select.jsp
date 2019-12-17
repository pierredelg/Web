<%@ page import="java.sql.*" %>
<%@ page errorPage="Error.jsp" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Select</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
</head>
<body class="container">
<h1 class="text-secondary text-center">Affichage de la table FOURNISSEURS :</h1>
<div class="alert alert-warning">Dernière requête exécutée : select * from fournisseurs;</div>
<table class="table table-hover table-bordered table-striped card card-cascade narrower">
    <tr class="thead-dark d-flex w-100" scope="row">
    <%
        String url = config.getServletContext().getInitParameter("urlBDD");
        String login = config.getServletContext().getInitParameter("loginBDD");
        String mdp = config.getServletContext().getInitParameter("mdpBDD");
        String driver = config.getServletContext().getInitParameter("driverBDD");

        Class.forName(driver);

        Connection con = DriverManager.getConnection(url, login, mdp);

        String table = "fournisseurs";

        String query = "select * from "+ table +";";

        PreparedStatement ps = con.prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        ResultSetMetaData metaData = rs.getMetaData();

        int nombreColonne = metaData.getColumnCount();

        for (int i = 1; i <= nombreColonne; i++) {

            String nomColonne = metaData.getColumnName(i);

    %>
        <th class="text-center align-middle w-100"><%=nomColonne%></th>
    <%
        }
        %>
    </tr>
        <%

         while (rs != null && rs.next()) {

    %>
    <tr class="d-flex" scope="row">
    <%
             for (int i = 1; i <= nombreColonne; i++) {
                 Object valeur = rs.getObject(i);
    %>
        <td class="text-center align-middle w-100" scope="col"><%=valeur%></td>
    <%
             }
     %>
        </tr>
    <%
         }
    %>

</table>
</body>
</html>
