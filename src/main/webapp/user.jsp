<%--
  Created by IntelliJ IDEA.
  User: lindsay
  Date: 1/5/22
  Time: 11:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <%@include file="html/head.html" %>


    <title>utlisateurs</title>
</head>
<body class="home">

<%@include file="navbar.jsp" %>
<header id="head" class="secondary"></header>
<div class="row">
    <div class="container">
        <article class="col-xs-12 maincontent">
            <header class="page-header">
                <h1 class="page-title"> Liste des utilisateurs</h1>
            </header>


            <input id="filter" type="text" class="form-control" placeholder="Rechercher..." onkeyup="filter()">
            </br>
            <table class="table">
                <thead>
                <tr>

                    <th scope="col">Nom</th>
                    <th scope="col">Prénom</th>
                    <th scope="col">Date de naissance</th>
                    <th scope="col">Email</th>
                    <th scope="col">Modifier</th>
                    <th scope="col">Supprimer</th>
                </tr>
                </thead>
                <tbody>


                </tbody>
            </table>
        </article>
    </div>
</div>

<%@include file="html/src.html" %>


</body>
</html>
