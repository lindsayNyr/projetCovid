<%--
  Created by IntelliJ IDEA.
  User: lindsay
  Date: 12/29/21
  Time: 10:32 PM
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
                <h1 class="page-title">Ajout et Liste D'amis </h1>
            </header>

            <form>

                <div class="row top-margin">
                    <div class="col-sm-6">
                        <input type="text" class="form-control formFriend" id="emailFriend" name="emailFriend"
                               required="required">
                    </div>
                    <div class="col-sm-6">
                        <btn class="btn btn-action formFriend" type="submit">Ajouter un ami</btn>
                    </div>
                </div>
            </form>

            </br> </br>


            <input id="filter" type="text" class="form-control" placeholder="Rechercher..." onkeyup="filter()">
            </br>
            <table class="table">
                <thead>
                <tr>

                    <th scope="col">Nom</th>
                    <th scope="col">Prénom</th>
                    <th scope="col">Date de Naissance</th>
                    <th scope="col">Email</th>
                    <th scope="col">Supprimer</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="row">Noyer</th>
                    <td>Lindsay</td>
                    <td>06/12/1999</td>
                    <td>lindsay.noyer@icloud.com</td>
                    <td>
                        <btn href="#" class="btn btn-danger btn-lg">Supprimer</btn>
                    </td>
                </tr>
                <tr>
                    <th scope="row">Noyer</th>
                    <td>Lindsay</td>
                    <td>06/12/1999</td>
                    <td>lindsay.noyer@icloud.com</td>
                    <td>
                        <btn href="#" class="btn btn-danger btn-lg">Supprimer</btn>
                    </td>
                </tr>
                <tr>
                    <th scope="row">Noyer</th>
                    <td>Lindsay</td>
                    <td>06/12/1999</td>
                    <td>lindsay.noyer@icloud.com</td>
                    <td>
                        <btn href="#" class="btn btn-danger btn-lg">Supprimer</btn>
                    </td>
                </tr>
                <tr>
                    <th scope="row">user1</th>
                    <td>user1</td>
                    <td>03/06/1997</td>
                    <td>user1.user1@icloud.com</td>
                    <td>
                        <btn href="#" class="btn btn-danger btn-lg">Supprimer</btn>
                    </td>
                </tr>

                </tbody>
            </table>
        </article>
    </div>
</div>
</div>
</div>


</body>
</html>