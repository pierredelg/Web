<%--JSP permettant l'affichage de la page contenant l'upload
ainsi que les fichiers présents dans le dossier de l'utilisateur--%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Espace personnel</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" id="bootstrap-css" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" %>
    <%@ page import="java.io.File" %>
    <%@ page import="java.io.IOException" %>
    <%@ page errorPage="Error.jsp" %>
</head>
<body class="d-flex flex-column" style="background-color: #ebebeb">
<%
    /* Vérification afin de savoir si l'utilisateur s'est enregistré*/
    String contextPath = application.getContextPath();
    String servletPath = request.getServletContext().getRealPath("");
    if (session.getAttribute("identificateur") == null) {
        try {
            response.sendRedirect(contextPath + "/login.html");
        } catch (IOException e) {
            e.getMessage();
        }
    } else {
        String nomUser = (String) session.getAttribute("nomUser");
        String nomDossier = (String) session.getAttribute("nomDossier");
%>
<%-- Barre du haut avec le titre --%>
<div class="jumbotron p-4 p-md-5 text-white rounded bg-dark d-flex justify-content-between">
    <div class="col-md-6 px-0">
        <h1 class="display-4 font-italic">Bienvenue <%=nomUser%>
        </h1>
        <p class="lead my-3">Vous pouvez uploader les fichiers que vous souhaitez garder sur notre serveur ou
            télécharger les fichiers contenus dans la liste.</p>
    </div>
    <div>
        <form action="<%=contextPath%>/Disconnect" method="get">
            <button type="submit" class="btn btn-lg btn-success">  Déconnexion  </button>
        </form>
    </div>
</div>
<div class="container"><br/>
    <%-- Panel principal--%>
    <div class="panel" style="border-color: #ebebeb">
        <%-- Premier panel pour l'upload --%>
        <div style="background-color: #ebebeb">
            <%-- Titre du panel upload --%>
            <div class="panel panel-heading bg-dark">
                <strong class="panel-title text-white">Upload d'un document</strong>
            </div>
                <form action="<%=contextPath%>/Upload" method="post" enctype='multipart/form-data'>
                    <%-- <form action="<%=contextPath%>/UploadFileApache" method="post" enctype='multipart/form-data'> --%>
                    <div class="input-group">
                        <label class="input-group-btn">
                            <span class="btn btn-success">
                                Choisissez vos fichiers<input type="file" style="display: none;" name="fichierUpload" multiple="true" accept="*/*">
                            </span>
                        </label>
                        <input type="text" class="form-control" readonly>
                        <label class="input-group-btn">
                        <span class="btn btn-success">
                            Valider <input type="submit" style="display: none;" multiple>
                        </span>
                        </label>
                    </div>
                </form>
        </div>
    </div>

    <%-- Panel avec la liste des fichiers --%>
    <div class="panel panel-default">
        <%-- Titre du panel pour les fichiers --%>
        <div class="panel-heading bg-dark">
            <strong class="panel-title text-white">Vos documents</strong>
        </div>
        <%-- Tableau contenant la liste les fichiers --%>
        <table class="panel-body table table-hover table-bordered table-striped narrower">
            <%-- Tete du tableau--%>
            <thead>
            <tr class="w-100 d-flex" scope="row">
                <th class="flex-grow-1 text-center">Nom du fichier</th>
            </tr>
            </thead>
            <%-- Corps du tableau--%>
            <tbody>

            <%
                /*Liste des fichiers du dossier utilisateur*/

                File file = new File(servletPath + "/users/" + nomDossier + "/");
                String[] list = file.list();
                for (String fichierPresent : list) {
                    if (fichierPresent != null) {
            %>
            <tr class="w-100 d-flex" scope="row">
                <%-- Colonne avec l'icone de fichier --%>
                <td class="">
                    <i class="glyphicon glyphicon-file" style="font-size: 2em"></i>
                </td>
                <%-- Colonne avec le nom du fichier--%>
                <td class="text-center align-middle flex-grow-1" scope="col">
                    <%= fichierPresent%>
                </td>
                <%--Colonne avec le bouton Télécharger--%>
                <form action="<%=contextPath%>/Download" method="post">
                    <input type="hidden" id="fichierDownload" name="fichierDownload" value="<%=fichierPresent%>"/>
                    <td class="text-center align-middle " scope="col">
                        <i class="fas fa-upload mr-2" aria-hidden="true"></i>
                        <input class="btn btn-success" type="submit" value="Télécharger"/>
                    </td>
                </form>
                    <form action="<%=contextPath%>/Delete" method="post">
                        <input type="hidden" id="fichierDelete" name="fichierDelete" value="<%=fichierPresent%>"/>
                        <td class="text-center align-middle " scope="col">
                            <i class="fas fa-upload mr-2" aria-hidden="true"></i>
                            <input class="btn btn-danger" type="submit" value="Supprimer"/>
                        </td>
                    </form>
            </tr>
            <%
                        }
                    }
                }

            %>
            </tbody>
        </table>
    </div>
</div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="js/main.js"></script>
</html>