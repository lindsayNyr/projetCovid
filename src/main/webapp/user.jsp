<%@ page import="fr.ul.projetcovid.persistence.UserAccount" %>
<%@ page import="fr.ul.projetcovid.persistence.dao.UserAccountDAO" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.print.DocFlavor" %><%--
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


            <input id="filter" type="text" class="form-control filter" placeholder="Rechercher..." >
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
                    List<UserAccount> accounts = new UserAccountDAO().getAllExceptMe(myself);
                    for (UserAccount account : accounts) {
                        String id = account.getId();

                %>
                <tr>
                    <th scope="row"><%= account.getNom() %></th>
                    <td><%= account.getPrenom() %></td>
                    <td><%= new SimpleDateFormat("dd/MM/yyyy").format(account.getNaissance()) %></td>
                    <td><%= account.getLogin() %></td>
                    <td>
                        <a href="editUser.jsp?id=<%=id%>" class="btn btn-action btn-lg">Modifier</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/deleteUser?idUser=<%=id%>" class="btn btn-danger btn-lg">Supprimer</a>
                    </td>

                </tr>
                <%}%>

                </tbody>
            </table>
        </article>
    </div>
</div>

<%@include file="html/src.html" %>


</body>
</html>
