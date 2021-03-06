<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javaf.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: lindsay
  Date: 12/27/21
  Time: 11:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- head -->

<html>
<head>
    <%@include file="html/head.html" %>
    <title>inscription</title>
</head>
<body>
<%@include file="navbar.jsp" %>
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
                <h1 class="page-title">Inscription</h1>
            </header>

            <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h3 class="thin text-center">Enregistrer un nouveau compte</h3>
                        <p class="text-center text-muted">Si vous avez déjà un compte connectez vous <a
                                href="logged.jsp">ici</a></p>
                        <hr>

                        <% if (request.getAttribute("error") != null) {%>
                        <div class="error">
                            <span class="error">
                            Une erreur a été rencontrée : <%=request.getAttribute("error")%>
                            </span>
                        </div>
                        <%}%>

                        <form method="POST" action="${pageContext.request.contextPath}/register">
                            <div class="top-margin">
                                <div class="text-center">
                                    <img src="https://us.123rf.com/450wm/kritchanut/kritchanut1401/kritchanut140100054/25251050-photo-de-profil-d-affaires-de-l-avatar.jpg?ver=6"
                                        class="img-rounded" name="imageProfil" id="imageProfil"  alt="...">
                                </div>
                            </div>
                            <div class="top-margin">
                                <label>Url Image<span class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="url" name="url"
                                       required="required"
                                       value="https://us.123rf.com/450wm/kritchanut/kritchanut1401/kritchanut140100054/25251050-photo-de-profil-d-affaires-de-l-avatar.jpg?ver=6">
                            </div>
                            <div class="top-margin">
                                <label>Nom <span class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="lastname" name="lastname"
                                       required="required"
                                       value="<%= Objects.nonNullOrElse(request.getParameter("lastname"), "") %>">
                            </div>
                            <div class="top-margin">
                                <label>Prénom <span class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="firstname" name="firstname"
                                       required="required"
                                       value="<%= Objects.nonNullOrElse(request.getParameter("firstname"), "") %>">
                            </div>
                            <div class="top-margin">
                                <label>Date de Naissance <span class="text-danger">*</span></label>
                                <input type="date" class="form-control" id="birthdate" name="birthdate"
                                       required="required"
                                       value="<%= Objects.nonNullOrElse(request.getParameter("birthdate"), "") %>">
                            </div>
                            <div class="top-margin">
                                <label>Email <span class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="email" name="email" required="required"
                                       value="<%= Objects.nonNullOrElse(request.getParameter("email"), "") %>">
                            </div>

                            <div class="row top-margin">
                                <div class="col-sm-6">
                                    <label>Mot de passe <span class="text-danger">*</span></label>
                                    <input type="password" class="form-control" id="password" name="password"
                                           required="required">
                                </div>
                                <div class="col-sm-6">
                                    <label>Confirmer le mot de passe <span class="text-danger">*</span></label>
                                    <input type="password" class="form-control" id="password-confirm"
                                           name="password-confirm" required="required">
                                </div>
                            </div>

                            <hr>

                            <div class="row">

                                <div class="col-lg-4 text-right">
                                    <button class="btn btn-action" type="submit">Inscription</button>
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
<link rel="stylesheet" href="css/template/assets/css/mycss.css">

</body>
</html>