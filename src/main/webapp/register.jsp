<%@ page import="javax.naming.InitialContext" %><%--
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
    <%@include  file="html/head.html" %>
    <title>inscription</title>
</head>
<body>
<%@include  file="html/navbar.html" %>
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
                        <p class="text-center text-muted">Si vous avez déjà un compte connectez vous <a href="logged.jsp">ici</a>  </p>
                        <hr>

                        <% if(request.getAttribute("error") != null) {%>
                        <div class="error">
                            <span class="error">
                            Une erreur a été rencontrée: <%=request.getAttribute("error")%>
                            </span>
                        </div>


                        <%}%>
                        <form method="POST" action="${pageContext.request.contextPath}/register">
                            <div class="top-margin">
                                <label>Nom</label>
                                <input type="text" class="form-control" id="nameRegister" name="nameRegister" required="required">
                            </div>
                            <div class="top-margin">
                                <label>Prénom</label>
                                <input type="text" class="form-control" id="firstNameRegister" name="firstNameRegister" required="required">
                            </div>
                            <div class="top-margin">
                                <label>Email <span class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="emailRegister" name="emailRegister" required="required">
                            </div>

                            <div class="row top-margin">
                                <div class="col-sm-6">
                                    <label>Mot de passe <span class="text-danger">*</span></label>
                                    <input type="password" class="form-control" id="passwordRegister" name="passwordRegister" required="required">
                                </div>
                                <div class="col-sm-6">
                                    <label>Confirmer le mot de passe <span class="text-danger">*</span></label>
                                    <input type="password" class="form-control" id="passwordConfirmRegister" name="passwordConfirmRegister" required="required">
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
</div>	<!-- /container -->

</body>
</html>