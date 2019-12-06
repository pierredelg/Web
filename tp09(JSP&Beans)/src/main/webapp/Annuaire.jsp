<%@ page import="org.apache.commons.text.StringEscapeUtils" %>
<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page errorPage="ErrorPage.jsp" %>
<html lang="fr">
<head>
    <title>Annuaire</title>
    <link rel="stylesheet" id="bootstrap-css" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="panel panel-default text-center">
        <div class="panel-heading bg-dark">
            <strong class="panel-title text-white">Informations</strong>
        </div>
        <table class="panel-body table table-hover table-bordered table-striped narrower">
            <thead>
            <tr class="w-100 d-flex" scope="row">
                <th class="flex-grow-1 text-center"></th>
            </tr>
            </thead>
            <tbody>
            <%
                String url = config.getServletContext().getInitParameter("url");
                String login = config.getServletContext().getInitParameter("login");
                String mdp = config.getServletContext().getInitParameter("mdp");
                String driver = config.getServletContext().getInitParameter("driver");

                String nom = StringEscapeUtils.escapeHtml4(request.getParameter("nom"));
                String prenom = StringEscapeUtils.escapeHtml4(request.getParameter("prenom"));
                String sexe = StringEscapeUtils.escapeHtml4(request.getParameter("sexe"));
                String tel = StringEscapeUtils.escapeHtml4(request.getParameter("tel"));
                String fonction = StringEscapeUtils.escapeHtml4(request.getParameter("fonction"));


                Class.forName(driver);

                Connection con = DriverManager.getConnection(url, login, mdp);

                //On constitue la requete select
                String query = "select * from annuaire where nom like ? and prenom like ? and sexe like ? and tel like ? and fonction like ?;";

                //On exécute la requete
                PreparedStatement ps = null;

                if (con != null) {
                    ps = con.prepareStatement(query);
                    ps.setString(1, nom + "%");
                    ps.setString(2, prenom + "%");
                    ps.setString(3, sexe + "%");
                    ps.setString(4, tel + "%");
                    ps.setString(5, fonction + "%");
                }

                //On récupere le résultat
                ResultSet rs = null;
                if (ps != null) {
                    rs = ps.executeQuery();
                }
                //On récupere le résultat des métadatas
                ResultSetMetaData metaData = null;
                if (rs != null) {
                    metaData = rs.getMetaData();
                }
                if(!rs.next()){
            %>
            <div class="alert alert-danger" role="alert">
                Aucun resultat
            </div>
            <%
                }else{
            %>
            <tr class="thead-dark" scope="row">
                <%
                    String nomColonne = null;
                    if (metaData != null) {
                        for (int i = 2; i <= metaData.getColumnCount(); i++) {
                            nomColonne = metaData.getColumnName(i).toUpperCase();

                %>
                <th class="text-center align-middle"><%=nomColonne%>
                </th>
                <%
                        }
                    }
                %>
            </tr>
            </tbody>
            <tr scope="row">
                <%
                        if (metaData != null) {
                            for (int i = 2; i <= metaData.getColumnCount(); i++) {
                                String valeur = rs.getString(i);

                %>
                <td class="text-center align-middle" scope="col">
                    <%=valeur%>
                </td>
                <%
                            }
                        }
                %>
            </tr>
            <%
                }
            %>
        </table>
        <form action="formulaire.html">
            <div>
                <button type="submit" class="btn btn-success">Nouvelle recherche</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
