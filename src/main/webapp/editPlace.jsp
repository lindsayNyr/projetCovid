<%@ page import="fr.ul.projetcovid.persistence.Place" %>
<%@ page import="fr.ul.projetcovid.persistence.dao.PlaceDAO" %><%--
  Created by IntelliJ IDEA.
  User: lindsay
  Date: 1/7/22
  Time: 9:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- head -->

<%

    String idPlace = request.getParameter("idPlace");
    Place place = new PlaceDAO().getById(idPlace);

    if (place == null) {
        response.sendError(403);
        return;
    }

%>

<html>
<head>
    <%@include file="html/head.html" %>
    <title>Profil</title>
</head>
<body>
<%@include file="navbar.jsp" %>
<header id="head" class="secondary"></header>

<!-- container -->
<div class="container">


    <div class="row">

        <!-- Article main content -->
        <article class="col-xs-12 maincontent">
            <header class="page-header">
                <h1 class="page-title">Editer le lieux</h1>
            </header>

            <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h3 class="thin text-center">Modifier le lieux </h3>

                        <hr>

                        <% if (request.getAttribute("error") != null) {%>
                        <div class="error">
                            <span class="error">
                            Une erreur a été rencontrée: <%=request.getAttribute("error")%>
                            </span>
                        </div>
                        <%}%>


                        <form method="POST"
                              action="${pageContext.request.contextPath}/editPlace?idPlace=<%=idPlace%>">


                            <div class="top-margin">
                                <input type="text" class="form-control formFriend mdb-autocomplete" id="namePlace" name="namePlace"
                                       required="required" placeholder="name" value="<%= place.getName()%>">
                            </div>

                            <div class="top-margin">
                                <input type="text" class="form-control formFriend mdb-autocomplete" id="ville" name="ville"
                                       required="required" placeholder="ville" value="<%= place.getCity()%>">
                            </div>

                            <div class="top-margin">
                                <input class="form-control formFriend" id="cp" name="cp"
                                       required="required" placeholder="codePostal" value="<%= place.getCodePostal()%>">
                            </div>
                            <div class="top-margin">
                                <input class="form-control formFriend" name="adresse" id="adresse" type="text"
                                       placeholder="Adresse" value="<%= place.getAdresse()%>">
                            </div>


                            <div class="top-margin">
                                    <button class="btn btn-action" id="editPlace" type="submit"> Enregistrer</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

            </div>

        </article>
        <!-- /Article -->

    </div>
</div>    <!-- /container -->

<%@include file="html/src.html" %>
</body>
</html>