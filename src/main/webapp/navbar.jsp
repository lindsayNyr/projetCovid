
<!-- Fixed navbar -->
<div class="navbar navbar-inverse navbar-fixed-top headroom" >
    <div class="container">
        <div class="navbar-header">
            <!-- Button for smallest screens -->
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
            <a class="navbar-brand" href="index.html"><img id="logo" src="css/template/assets/images/logo.png" alt="Progressus HTML5 template"></a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav pull-right">
                <li ><a href="index.jsp">Home</a></li>
                <li><a href="about.html">About</a></li>






                <%if (session.getAttribute("id") == null) {%>

                        <li><a class="btn" href="logged.jsp">SE CONNECTER</a></li>
                <%}else{%>
                <li class="dropdown">
                    <a  class="dropdown-toggle" href="#" data-toggle="dropdown">Activit&eacute; <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li ><a href="mesActivites.jsp">Mes activit&eacute;s</a></li>
                        <li><a href="ajoutActivite.jsp">Nouvelle activit&eacute;</a></li>
                    </ul>
                </li>
                        <li><a href="friends.jsp">Amis</a></li>
                        <li><a href="notif.jsp">Notification</a></li>
                        <li><a href="profile.jsp">Profil</a></li>
                        <li><a class="btn" href="${pageContext.request.contextPath}/logout">SE DECONNECTER</a></li>
                <% }%>

            </ul>
        </div><!--/.nav-collapse -->
    </div>
</div>
<!-- /.navbar -->