<%@ page import="fr.ul.projetcovid.persistence.Notification" %>
<%@ page import="java.util.List" %>
<%@ page import="fr.ul.projetcovid.persistence.dao.NotificationDAO" %>
<%@ page import="javaf.util.Objects" %><%--
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
        Jsp
    <%
                    break;
                }
                case FRIEND_REQUEST: {
    %>
        Jsp non plus
    <%
                    break;
                }
            }
        }
    %>
</div>

<%@include  file="html/src.html" %>
</body>
</html>
