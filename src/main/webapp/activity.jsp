<%@ page import="fr.ul.projetcovid.persistence.Activity" %>
<%@ page import="fr.ul.projetcovid.persistence.dao.ActivityDAO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: lindsay
  Date: 1/3/22
  Time: 6:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="html/head.html" %>


    <title>activity</title>
</head>
<body class="home">
<%

    Optional<UserAccount> acc = new UserAccountDAO().getById((String) session.getAttribute("id"));
    if (!acc.isPresent()) {
        response.sendError(403);
        return;
    }
    UserAccount account = acc.get();
    Boolean admin = new IsAdminDAO().isAdmin(account);

%>
<%@include file="navbar.jsp" %>
<header id="head" class="secondary"></header>
<div class="row">
    <div class="container">
        <article class="col-xs-12 maincontent">
            <header class="page-header">
                <h1 class="page-title"> Ajout d'activité </h1>
            </header>

            <% if (request.getAttribute("error") != null) {%>
            <div class="error">
                            <span class="error">
                            Une erreur a été rencontrée : <%=request.getAttribute("error")%>
                            </span>
            </div>
            <%}%>


            <form method="POST" action="${pageContext.request.contextPath}/addActivity">

                <div class="row top-margin">
                    <div class="col-sm-6">
                        <input type="text" class="form-control formFriend" id="nameActivity" name="nameActivity"
                               required="required">

                    </div>
                    <div class="col-sm-6">
                        <button class="btn btn-action formFriend" id="addActivity" type="submit">Ajouter une activit&eacute;</button>
                    </div>
                </div>
            </form>

            </br> </br>


            <input id="filter" type="text" class="form-control filter" placeholder="Rechercher..." >
            </br>



            <table class="table">
               <thead>
                <tr>
                    <th scope="col">Activité Name</th>
                    <% if(admin){%>
                    <th scope="col">Modifier</th>
                    <th scope="col">Supprimer</th>
                    <%}%>
                </tr>
                </thead>
                <tbody>

                <%


                    List<Activity> activityList = new ActivityDAO().getAll();
                    if (!activityList.isEmpty()) {
                        for (Activity a : activityList) {

                %>

                <tr>
                    <td>
                        <%=a.getName()%>
                    </td>
                    <%if(admin){%>
                    <td>
                        <a href="editActivity.jsp?idActivity=<%=a.getId()%>" class="btn btn-action btn-lg">Modifier</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/deleteActivity?idActivity=<%=a.getId()%>"
                           class="btn btn-danger btn-lg">Supprimer</a>
                    </td>
                    <%}%>
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
