<%@ page import="java.util.Optional" %>
<%@ page import="fr.ul.projetcovid.persistence.UserAccount" %>
<%@ page import="fr.ul.projetcovid.persistence.dao.UserAccountDAO" %>
<%@ page import="fr.ul.projetcovid.persistence.dao.IsAdminDAO" %>
<!-- Fixed navbar -->
<div class="navbar navbar-inverse navbar-fixed-top headroom">
    <div class="container">
        <div class="navbar-header">
            <!-- Button for smallest screens -->
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span></button>
            <a class="navbar-brand" href="index.jsp"><img id="logo" src="css/template/assets/images/logo.png"
                                                          alt="Progressus HTML5 template"></a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav pull-right">
                <li><a href="index.jsp">Home</a></li>

                <%if (session.getAttribute("id") == null) {%>

                <li><a class="btn" href="logged.jsp">SE CONNECTER</a></li>
                <%} else {
                    final String myId = (String) session.getAttribute("id");
                    final Optional<UserAccount> maybeMyself = new UserAccountDAO().getById(myId);
                    if (!maybeMyself.isPresent()) {
                        response.sendError(500);
                        return;
                    }
                    final UserAccount myself = maybeMyself.get();

                    final long notificationCount = myself.getNotifications().stream().filter(n -> !n.getRead()).count();
                %>
                <li class="dropdown">
                    <a class="dropdown-toggle" href="#" data-toggle="dropdown">Activit&eacute; <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="place.jsp">Lieux</a></li>
                        <li><a class="dropdown-item" href="myActivity.jsp">Mes activit&eacute;s</a></li>
                        <li><a class="dropdown-item" href="activity.jsp">Nouvelle activit&eacute;</a></li>
                    </ul>
                </li>
                <li><a href="friends.jsp">Amis</a></li>
                <li><a href="notif.jsp">Notifications
                    <% if (notificationCount > 0) {
                    %>
                    <span class="badge"><%= notificationCount %></span>
                    <% } %></a></li>
                <li><a href="profile.jsp">Profil</a></li>

                <%

                    final boolean isAdmin = new IsAdminDAO().isAdmin(myself);

                    if (isAdmin) {
                %>

                <li class="dropdown">
                    <a class="dropdown-toggle" href="#" data-toggle="dropdown">Gestions<b class="caret"></b></a>
                    <ul class="dropdown-menu">

                        <li><a class="dropdown-item" href="user.jsp">utilisateur</a></li>
                    </ul>
                </li>

                <% } %>

                <li><a class="btn" href="${pageContext.request.contextPath}/logout">SE DECONNECTER</a></li>
                <% }%>

            </ul>
        </div><!--/.nav-collapse -->
    </div>
</div>
<!-- /.navbar -->