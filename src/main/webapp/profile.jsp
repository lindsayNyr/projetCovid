<%@ page import="javax.naming.InitialContext" %>
<%@ page import="fr.ul.projetcovid.persistence.UserAccount" %>
<%@ page import="java.util.Optional" %>
<%@ page import="fr.ul.projetcovid.persistence.dao.UserAccountDAO" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: lindsay
  Date: 12/27/21
  Time: 11:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- head -->

<%
    Optional<UserAccount> acc = new UserAccountDAO().getById((String) session.getAttribute("id"));
    if (!acc.isPresent()) {
        response.sendError(403);
        return;
    }
    UserAccount account = acc.get();
%>

<html>
<head>
    <%@include  file="html/head.html" %>
    <title>Profil</title>
</head>
<body>
<%@include  file="navbar.jsp" %>
<header id="head" class="secondary"></header>

<!-- container -->
<div class="container">

    <ol class="breadcrumb">
        <li><a href="index.html">Home</a></li>
        <li class="active">Inscription</li>
    </ol>

    <div class="row">

        <!-- Article main content -->
        <article class="col-xs-12 maincontent">
            <header class="page-header">
                <h1 class="page-title">Profil</h1>
            </header>

            <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h3 class="thin text-center">Modifier votre profil</h3>
                        <hr>

                        <% if(request.getAttribute("error") != null) {%>
                        <div class="error">
                            <span class="error">
                            Une erreur a été rencontrée: <%=request.getAttribute("error")%>
                            </span>
                        </div>
                        <%}%>

                        <form method="POST" action="${pageContext.request.contextPath}/profile">
                            <div class="top-margin">
                                <label>Nom <span class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="lastname" name="lastname" required="required" value="<%= account.getNom() %>">
                            </div>
                            <div class="top-margin">
                                <label>Prénom <span class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="firstname" name="firstname" required="required" value="<%= account.getPrenom() %>">
                            </div>
                            <div class="top-margin">
                                <label>Date de Naissance <span class="text-danger">*</span></label>
                                <input type="date" class="form-control" id="dateBirth" name="dateBirth" required="required"  value="<%= new SimpleDateFormat("yyyy-MM-dd").format(account.getNaissance()) %>">
                            </div>
                            <div class="top-margin">
                                <label>Email <span class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="email" name="email" required="required" value="<%= account.getLogin() %>">
                            </div>

                            <div class="row top-margin">
                                <div class="col-sm-6">
                                    <label>Mot de passe <span class="text-danger">*</span></label>
                                    <input type="password" class="form-control" id="password" name="password" required="required">
                                </div>
                                <div class="col-sm-6">
                                    <label>Confirmer le mot de passe <span class="text-danger">*</span></label>
                                    <input type="password" class="form-control" id="password-confirm" name="password-confirm" required="required">
                                </div>
                            </div>

                            <hr>

                            <div class="row">

                                <div class="col-lg-4 text-right">
                                    <button class="btn btn-action" type="submit">Enregistrer</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

            </div>

        </article>
        <!-- /Article -->

    </div>
</div>	<!-- /container -->

</body>
</html>
