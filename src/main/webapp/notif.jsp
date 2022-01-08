<%@ page import="fr.ul.projetcovid.persistence.Notification" %>
<%@ page import="java.util.List" %>
<%@ page import="fr.ul.projetcovid.persistence.dao.NotificationDAO" %>
<%@ page import="javaf.util.Objects" %>
<%@ page import="fr.ul.projetcovid.persistence.FriendRequestNotification" %>
<%@ page import="fr.ul.projetcovid.persistence.CovidNotification" %><%--
  Created by IntelliJ IDEA.
  User: lindsay
  Date: 12/29/21
  Time: 11:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="html/head.html" %>


    <title>Notification</title>
</head>
<body class="home">

<%@include file="navbar.jsp" %>
<header id="head" class="secondary"></header>
<div class="container">
    <article class="col-xs-12 maincontent">
        <header class="page-header">
            <h1 class="page-title"> Notifications </h1>
        </header>
    </article>

    <input id="filter" type="text" class="form-control filter" placeholder="Rechercher...">
    </br>
    <table class="table">
        <thead>
        <tr>

            <th scope="col">Type</th>
            <th scope="col">Source</th>
            <th scope="col">Message</th>
            <th scope="col">Accepté</th>
            <th scope="col">Refusé</th>

        </tr>
        </thead>
        <tbody>


            <%
        final String myId = Objects.nonNullOrElse((String) session.getAttribute("id"), "");
        final Optional<UserAccount> maybeMyself = new UserAccountDAO().getById(myId);
        if (!maybeMyself.isPresent()) {
            response.sendError(403);
            return;
        }
        final UserAccount myself = maybeMyself.get();

        for (Notification notif : myself.getNotifications()) {
            switch (notif.getType()) {
                case COVID: {


    %>

        <tr>
            <td><%=notif.getType()%>
            </td>
            <td scope="col"><%= ((CovidNotification) notif).getSource().getPrenom()%> <%=  ((CovidNotification) notif).getSource().getNom()%>
            </td>
            <td><%=notif.getMessage()%>
            </td>
            <td></td>
            <td></td>
        </tr>

            <%
                    break;
                }
                case FRIEND_REQUEST: {
    %>
        <tr>
            <td><%=notif.getType()%>
            </td>
            <td scope="col"><%=((FriendRequestNotification) notif).getAuthor().getNom() %> - <%=((FriendRequestNotification) notif).getAuthor().getPrenom()  %>
            </td>
            <td><%= String.format(notif.getMessage(), ((FriendRequestNotification) notif).getAuthor().getPrenom()) %>
            </td>

            <%
                if (!((FriendRequestNotification) notif).getAccepted()) {%>
            <td><a class="btn btn-action"  href="/acceptfr?id=<%= notif.getId()%>&status=accept"> Accepté </a></td>
            <td><a class="btn btn-danger" href="/acceptfr?id=<%= notif.getId() %>&status=deny"> Refusé  </a></td>
            <% } else { %>
            <td colspan="2"></td>
            <% }%>
        </tr>
            <%
                    break;
                }
                default:
                    %>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        %><%

            }
        }
    %>
</div>

<%@include file="html/src.html" %>
</body>
</html>
