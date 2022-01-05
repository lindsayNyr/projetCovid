<%--
  Created by IntelliJ IDEA.
  User: lindsay
  Date: 1/3/22
  Time: 5:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <%@include file="html/head.html" %>



    <title>mes activites</title>
</head>
<body class="home">

<%@include file="navbar.jsp" %>
<header id="head" class="secondary"></header>
<div class="row">
    <div class="container">
        <article class="col-xs-12 maincontent">
            <header class="page-header">
                <h1 class="page-title"> Ajout et Liste de mes activités</h1>
            </header>

            <form>

                <div class="row top-margin">
                    <div class="col-sm-3">
                        <select type="text" class="form-control formFriend" id="nameActivite" name="nameActivite"
                                required="required"></select>

                    </div>
                    <div class="col-sm-3">
                        <input type="datetime-local" class="form-control formFriend" id="date"
                               name="date">

                    </div>

                    <div class="col-sm-3">
                        <input type="text" class="form-control formFriend mdb-autocomplete" id="ville" name="ville"
                               required="required" placeholder="ville">
                    </div>

                    <div class="col-sm-3">
                        <input  class="form-control formFriend" id="cp" name="cp"
                                 required="required" placeholder="codePostal">
                    </div>


                </div>
                <br>

                <btn class="btn btn-action btnActivite formFriend" type="submit">Ajouter une activité</btn>

            </form>

            <br> <br>


            <input id="filter" type="text" class="form-control" placeholder="Rechercher..." onkeyup="filter()">
            </br>
            <table class="table">
                <thead>
                <tr>

                    <th scope="col">Activité</th>
                    <th scope="col">Lieux</th>
                    <th scope="col">Date</th>
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

<%@include  file="html/src.html" %>




</body>
</html>