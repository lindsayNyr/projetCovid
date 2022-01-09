<%@ page import="fr.ul.projetcovid.persistence.Place" %>
<%@ page import="fr.ul.projetcovid.persistence.dao.PlaceDAO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: lindsay
  Date: 08/01/2022
  Time: 23:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="html/head.html" %>

    <title>Place</title>
</head>
<body class="home">

<%@include file="navbar.jsp" %>
<header id="head" class="secondary"></header>
<div class="row">
    <div class="container">
        <article class="col-xs-12 maincontent">
            <header class="page-header">
                <h1 class="page-title"> Ajout et Liste de des lieux</h1>
            </header>

            <% if (request.getAttribute("error") != null) {%>
            <div class="error">
                            <span class="error">
                            Une erreur a été rencontrée: <%=request.getAttribute("error")%>
                            </span>
            </div>
            <%
                }%>

            <form method="POST" action="${pageContext.request.contextPath}/addPlace">

                <div class="row top-margin">
                    <div class="col-sm-3">
                        <input type="text" class="form-control formFriend mdb-autocomplete" id="name" name="name"
                               required="required" placeholder="nom">
                    </div>

                    <div class="col-sm-3">
                        <input type="text" class="form-control formFriend mdb-autocomplete" id="ville" name="ville"
                               required="required" placeholder="ville">
                    </div>

                    <div class="col-sm-3">
                        <input class="form-control formFriend" id="cp" name="cp"
                               required="required" placeholder="codePostal">
                    </div>
                    <div class="col-sm-3">
                        <input class="form-control formFriend" name="adresse" id="adresse" type="text"
                               placeholder="Adresse">
                    </div>

                </div>
                    <br>

                    <button class="btn btn-action btnActivite formFriend" type="submit">Ajouter un lieux</button>

            </form>

            <input id="filter" type="text" class="form-control filter" placeholder="Rechercher..." >
            <br>


            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Nom</th>
                    <th scope="col">Ville</th>
                    <th scope="col">CodePostal</th>
                    <th scope="col">Adresse</th>
                    <th scope="col"> Modifier</th>
                    <th scope="col"> Supprimer</th>

                </tr>
                </thead>
                <tbody>

                <%


                    List<Place> placesList = new PlaceDAO().getAll();
                    if (!placesList.isEmpty()) {
                        for (Place p : placesList) {


                %>

                <tr>
                    <td>
                        <%=p.getName()%>
                    </td>

                    <td><%=p.getCity()%>
                    </td>
                    <td><%=p.getCodePostal()%>
                    </td>
                    <td><%=p.getAdresse()%>
                    </td>


                    <td>
                        <a href="editPlace.jsp?idPlace=<%=p.getId()%>" class="btn btn-action btn-lg">Modifier</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/deletePlace?idPlace=<%=p.getId()%>"
                           class="btn btn-danger btn-lg">Supprimer</a>
                    </td>
                </tr>

                <%
                        }
                    }
                %>



                </tbody>
            </table>

        </article>
    </div>
</div>

<%@include file="html/src.html" %>


</body>
</html>
