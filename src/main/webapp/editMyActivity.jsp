<%@ page import="fr.ul.projetcovid.persistence.Place" %>
<%@ page import="fr.ul.projetcovid.persistence.dao.PlaceDAO" %>
<%@ page import="fr.ul.projetcovid.persistence.MyActivity" %>
<%@ page import="fr.ul.projetcovid.persistence.dao.MyActivityDAO" %><%--
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

    String idMyActivity = request.getParameter("idMyActivity");
    Place myActivity = new PlaceDAO().getById(idMyActivity);

    if (myActivity == null) {
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
                <h1 class="page-title">Editer son activité </h1>
            </header>

            <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h3 class="thin text-center">Modifier l'activité </h3>

                        <hr>

                        <% if (request.getAttribute("error") != null) {%>
                        <div class="error">
                            <span class="error">
                            Une erreur a été rencontrée: <%=request.getAttribute("error")%>
                            </span>
                        </div>
                        <%}%>

                        <form method="POST" action="${pageContext.request.contextPath}/editMyActivity?idMyActivity=<%=idMyActivity%>">

                            <div class="row top-margin">
                                <div class="top-margin">
                                    <select type="text" class="form-control formFriend" id="idMyActivity"
                                            name="idMyActivity"
                                            required="required">
                                        <% List<MyActivity> myActivityList = new MyActivityDAO().getAll();
                                            if (!myActivityList.isEmpty()) {
                                                for (MyActivity a : myActivityList) {

                                        %>
                                        <option value="<%=a.getId()%>"><%= a.getName() %>
                                        </option>

                                        <%
                                                }
                                            }

                                        %>


                                    </select>


                                    <select type="text" class="form-control formFriend" id="idPlace" name="idPlace"
                                            required="required">
                                        <% List<Place> placesList = new PlaceDAO().getAll();
                                            if (!placesList.isEmpty()) {
                                                for (Place p : placesList) {

                                        %>
                                        <option value="<%=p.getId()%>"><%= p.getCodePostal()%> - <%=  p.getCity() %>
                                            -<%=  p.getAdresse() %>
                                        </option>

                                        <%
                                                }
                                            }

                                        %>


                                    </select>
                                    
                                </div>
                                <div class="top-margin">
                                    <input type="date" class="form-control formFriend" id="date"
                                           name="date">

                                </div>
                                <div class="top-margin">
                                    <input type="time" class="form-control formFriend" id="startTime"
                                           name="startTime" placeholder="heure de début">

                                </div>
                                <div class="top-margin">
                                    <input type="time" class="form-control formFriend" id="endTime"
                                           name="endTime" placeholder="heure de fin">
                                </div>


                            </div>


                            <br>

                            <button class="btn btn-action btnActivite formFriend" type="submit">Ajouter une activité
                            </button>

                        </form>
                    </div>

                </div>

        </article>
        <!-- /Article -->

    </div>
</div>    <!-- /container -->

<%@include file="html/src.html" %>
</body>
</html>