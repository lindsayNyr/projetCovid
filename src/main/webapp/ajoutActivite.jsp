<%--
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
    <script src="js/friendsFilter.js"></script>
    <title>amis</title>
</head>
<body class="home">

<%@include file="navbar.jsp" %>
<header id="head" class="secondary"></header>
<div class="row">
    <div class="container">
        <article class="col-xs-12 maincontent">
            <header class="page-header">
                <h1 class="page-title"> Ajout et Liste d'activité des activités</h1>
            </header>

            <form>

                <div class="row top-margin">
                    <div class="col-sm-6">
                        <input type="text" class="form-control formFriend" id="nameActivite" name="nameActivite"
                               required="required">

                    </div>
                    <div class="col-sm-6">
                        <input type="text" class="form-control formFriend" id="codePostal" name="codePostal"
                               required="required">
                    </div>
                    <div class="col-sm-6">
                        <input type="text" class="form-control formFriend" id="ville" name="ville"
                               required="required">
                    </div>
                    <div class="col-sm-6">
                        <btn class="btn btn-action formFriend" type="submit">Ajouter une activit&eacute;</btn>
                    </div>
                </div>
            </form>

            </br> </br>


            <input id="filter" type="text" class="form-control" placeholder="Rechercher..." onkeyup="filter()">
            </br>
            <table class="table">
                <thead>
                <tr>

                    <th scope="col">Activité</th>
                    <th scope="col">Lieux</th>
                    <th scope="col">Date</th>
                </tr>
                </thead>
                <tbody>



                </tbody>
            </table>
        </article>
    </div>
</div>
</div>
</div>


</body>
</html>