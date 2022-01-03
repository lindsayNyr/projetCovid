<%@ page import="fr.ul.projetcovid.persistence.UserAccount" %>
<%@ page import="java.util.List" %>
<%@ page import="fr.ul.projetcovid.persistence.dao.FriendsDAO" %>
<%@ page import="fr.ul.projetcovid.persistence.dao.UserAccountDAO" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
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
                               required="required" placeholder="adresse mail">
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

                <%
                    String userId = (String) session.getAttribute("id");
                    if (userId == null) {
                        response.sendError(403);
                        return;
                    }

                    Optional<UserAccount> maybeMyself = new UserAccountDAO().getById(userId);
                    if (!maybeMyself.isPresent()) {
                        response.sendError(403);
                        return;
                    }

                    UserAccount myself = maybeMyself.get();
                    List<UserAccount> friends = new FriendsDAO().friendsOf(myself);

                    for (UserAccount friend : friends) {
                %>
                <tr>
                    <th scope="row"><%= friend.getNom() %></th>
                    <td><%= friend.getPrenom() %></td>
                    <td><%= new SimpleDateFormat("dd/MM/yyyy").format(friend.getNaissance()) %></td>
                    <td><%= friend.getLogin() %></td>
                    <td>
                        <btn href="#" class="btn btn-danger btn-lg">Supprimer</btn>
                    </td>
                </tr>
                <% }
                %>

                </tbody>
            </table>
        </article>
    </div>
</div>
</div>
</div>


</body>
</html>