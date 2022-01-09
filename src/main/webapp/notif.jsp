<%@ page import="fr.ul.projetcovid.persistence.dao.NotificationDAO" %>
<%@ page import="javaf.util.Objects" %>
<%@ page import="fr.ul.projetcovid.persistence.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
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

            <th scope="col">Reçu à</th>
            <th scope="col">Message</th>

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

        final NotificationDAO notifDAO = new NotificationDAO();
        List<Notification> notifs = myself.getNotifications();
        notifs.sort(Comparator.comparing(Notification::getTimestamp));
        Collections.reverse(notifs);
        for (Notification notif : notifs) {
            final Date sent = notif.getTimestamp();
            String colorClass = (notif.getType() == Notification.NotificationType.COVID ? "warning" : "") + (notif.getRead() ? "" : "info");
%>
        <tr class="<%= colorClass %>">
            <td><%= new SimpleDateFormat("dd-MM-yyyy hh:mm").format(sent) %></td>
        <%
            switch (notif.getType()) {
                case COVID: {

                    final CovidNotification covidNotification = (CovidNotification) notif;
    %>
            <td style="vertical-align: middle"><%= String.format(covidNotification.getMessage(), covidNotification.getSource().getPrenom() + " " + covidNotification.getSource().getNom()) %>
            </td>

            <%
                    break;
                }
                case FRIEND_REQUEST: {
    %>
            <td style="vertical-align: middle;display: flex; align-items: center">
                <span style="flex-grow: 1"><%= String.format(notif.getMessage(), ((FriendRequestNotification) notif).getAuthor().getPrenom()) %></span>
                <% if (!((FriendRequestNotification) notif).getAccepted()) {%>
                <span>
                    <a class="btn btn-action"  href="${pageContext.request.contextPath}/acceptfr?id=<%= notif.getId()%>&status=accept"> Accepter </a>
                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/acceptfr?id=<%= notif.getId() %>&status=deny"> Refuser  </a>
                </span>
                <%} else {}%>
            </td>
            <%
                    break;
                }
                case BASIC: {
                    final BasicNotification basicNotification = (BasicNotification) notif;
            %>
                    <td style="vertical-align: middle"><%= String.format(basicNotification.getMessage(), basicNotification.getAuthor().getPrenom()) %></td>
                <%
                    break;
                }
                default:
                    %>
            <td style="vertical-align: middle"></td>
        %><%
            }

            notifDAO.markRead(notif);
            %>
        </tr>
            <%
        }
    %>
</div>

<%@include file="html/src.html" %>
</body>
</html>
