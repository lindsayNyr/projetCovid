<%@ page import="fr.ul.projetcovid.persistence.Activity" %>
<%@ page import="fr.ul.projetcovid.persistence.dao.ActivityDAO" %><%--
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

    String idActivity = request.getParameter("idActivity");
    Activity activity = new ActivityDAO().getById(idActivity);

    if (activity == null) {
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
                <h1 class="page-title">Editer l'activité</h1>
            </header>

            <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h3 class="thin text-center">Modifier l'activité</h3>

                        <hr>

                        <% if (request.getAttribute("error") != null) {%>
                        <div class="error">
                            <span class="error">
                            Une erreur a été rencontrée: <%=request.getAttribute("error")%>
                            </span>
                        </div>
                        <%}%>


                        <form method="POST"
                              action="${pageContext.request.contextPath}/editActivity?idActivity=<%=idActivity%>">


                            <div class="row top-margin">
                                <div class="col-sm-6">
                                    <input type="text" class="form-control formFriend " id="nameActivity"
                                           name="nameActivity"
                                           required="required" value="<%= activity.getName()%>">

                                </div>
                                <div class="col-sm-6">
                                    <button class="btn btn-action" id="editActivity" type="submit"> Enregistrer</button>
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

