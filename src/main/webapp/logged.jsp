<%--
  Created by IntelliJ IDEA.
  User: lindsay
  Date: 12/27/21
  Time: 10:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<!-- head -->
<head>
<%@include  file="html/head.html" %>
    <title>connexion</title>

</head>

<body class="home">
<!-- navbar -->
<%@include  file="navbar.jsp" %>
<header id="head" class="secondary"></header>
<div class="container">

    <ol class="breadcrumb">
        <li><a href="index.html">Home</a></li>
        <li class="active">Connexion</li>
    </ol>

    <div class="row">

        <!-- Article main content -->
        <article class="col-xs-12 maincontent">
            <header class="page-header">
                <h1 class="page-title">Connection</h1>
            </header>

            <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h3 class="thin text-center">Connectez-vous a votre compte </h3>
                        <p class="text-center text-muted">Si vous n'avez pas de compte cliquez  <a href="register.jsp">ici</a> pour effectuer votre inscription  </p>
                        <hr>

                        <% if(request.getAttribute("error") != null) {%>
                        <div class="error">
                            <span class="error">
                            Une erreur a été rencontrée: <%=request.getAttribute("error")%>
                            </span>
                        </div>
                        <%}%>

                        <form method="POST" action="${pageContext.request.contextPath}/login">
                            <div class="top-margin">
                                <label>Email <span class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="email" name="email" required="required">
                            </div>
                            <div class="top-margin">
                                <label>Mot de passe <span class="text-danger">*</span></label>
                                <input type="password" class="form-control" id="password" name="password" required="required">
                            </div>

                            <hr>

                            <div class="row">

                                <div class="col-lg-4 text-right">
                                    <button class="btn btn-action" type="submit">Sign in</button>
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

<!-- src -->
<%@include  file="html/src.html" %>


</body>
</html>
