<%--JSP permettant l'affichage de la page contenant l'upload
ainsi que les fichiers présents dans le dossier de l'utilisateur--%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Espace personnel</title>
    <link rel="stylesheet" href="css/upload.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" id="bootstrap-css" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <%@ page contentType="text/html;charset=UTF-8" %>
    <%@ page import="java.io.File" %>
    <%@ page import="java.io.IOException" %>
</head>
<body class="d-flex flex-column">
<%
    /* Vérification afin de savoir si l'utilisateur s'est enregistrer*/
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
        System.out.println("nom user = " + nomUser);

%>
<%-- Barre du haut avec le titre --%>
<div class="jumbotron p-4 p-md-5 text-white rounded bg-dark">
    <div class="col-md-6 px-0">
        <h1 class="display-4 font-italic">Bienvenue <%=nomUser%>
        </h1>
        <p class="lead my-3">Vous pouvez uploader les fichiers que vous souhaitez garder sur notre serveur ou
            télécharger les fichiers contenus dans la liste.</p>
    </div>
</div>
<script src="js/upload.js"></script>
<div class="container"><br/>
    <%-- Panel principal--%>
    <div class="panel panel-default">

        <%-- Premier panel pour l'upload --%>
        <div class="panel-default">
            <%-- Titre du panel upload --%>
            <div class="panel panel-heading bg-success">
                <strong class="panel-title text-white">Upload d'un document</strong>
            </div>
            <%-- Barre d'upload --%>
            <div class="panel panel-body">
                    <div class="input-group image-preview">
                        <input placeholder="" type="text" class="form-control"
                            <%
    String fichier = request.getParameter("fichier");
    if(fichier != null){
%> value="<%=fichier%>"
                            <%
        }
%>
                        >
                        <%-- Group pour les boutons--%>
                        <span class="input-group-btn">

                                <%-- Bouton effacer --%>
                                <button type="button" class="btn btn-default"
                                        <%
    if(fichier == null){
      %>style="display:none;"<%
                                    }
                                %>
                                >
                                    <span class="glyphicon glyphicon-remove">
                                        Effacer
                                    </span>
                                </button>

                                <%-- Bouton Parcourir --%>
                                <div class="btn btn-default btn-labeled ">
                                     <span class="btn-label">
                                    <i class="glyphicon glyphicon-folder-open"></i>
                                        Parcourir
                                     </span>
                                    <input type="file" name="fichier"/>
                                </div>

                                <%-- Bouton Upload --%>
                                <form action="<%=contextPath%>/Upload" method="post" enctype="multipart/form-data">
                                    <input type="hidden" name="description" />
                                    <input type="hidden" name="file" />
                                    <button type="submit" class="btn btn-labeled btn-success">
                                        <span class="btn-label">
                                            <i class="glyphicon glyphicon-upload"></i>
                                            Upload
                                        </span>
                                    </button>
                                </form>

                            </span>
                    </div>
            </div>
        </div>

        <%-- Panel avec la liste des fichiers --%>
        <div class="panel panel-default">
            <%-- Titre du panel pour les fichiers --%>
            <div class="panel-heading bg-success">
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
                    System.out.println("path dossier user = " + servletPath + "/users/" + nomUser + "/");
                    File file = new File(servletPath + "/users/" + nomUser + "/");
                    String[] list = file.list();
                    System.out.println("liste fichiers : " + list);
                    int numeroDeFichier = 0;
                    for (String fichierPresent : list) {
                        if (fichierPresent != null) {
                            System.out.println("fichier présent = " + fichierPresent);
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
</html>