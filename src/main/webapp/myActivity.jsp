<%@ page import="fr.ul.projetcovid.persistence.Activity" %>
<%@ page import="java.util.List" %>
<%@ page import="fr.ul.projetcovid.persistence.Place" %>
<%@ page import="fr.ul.projetcovid.persistence.dao.*" %>
<%@ page import="fr.ul.projetcovid.persistence.MyActivity" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="javaf.util.Objects" %><%--
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

            <form method="POST" action="${pageContext.request.contextPath}/addMyActivity">

                <div class="row top-margin">
                    <div class="col-sm-3">
                        <select type="text" class="form-control formFriend" id="idActivity" name="idActivity"
                                required="required">
                            <% List<Activity> activityList = new ActivityDAO().getAll();
                                if (!activityList.isEmpty()) {
                                    for (Activity a : activityList) {

                            %>
                            <option value="<%=a.getId()%>"><%= a.getName() %>
                            </option>

                            <%
                                    }
                                }

                            %>


                        </select>


                        <select type="text" class="form-control formFriend" id="idPlace" name="idPlace"
                                required="required">
                            <% List<Place> placesList = new PlaceDAO().getAll();
                                if (!placesList.isEmpty()) {
                                    for (Place p : placesList) {

                            %>
                            <option value="<%=p.getId()%>"> <%= p.getName()%> - <%= p.getCodePostal()%> - <%=  p.getCity() %>
                                -<%=  p.getAdresse() %>
                            </option>

                            <%
                                    }
                                }

                            %>


                        </select>


                    </div>
                    <div class="col-sm-3">
                        <input type="date" class="form-control formFriend" id="date"
                               name="date">

                    </div>
                    <div class="col-sm-3">
                        <input type="time" class="form-control formFriend" id="startTime"
                               name="startTime" placeholder="heure de début">

                    </div>
                    <div class="col-sm-3">
                        <input type="time" class="form-control formFriend" id="endTime"
                               name="endTime" placeholder="heure de fin">
                    </div>


                </div>


                <br>

                <button class="btn btn-action btnActivite formFriend" type="submit">Ajouter une activité</button>

            </form>

            <br> <br>


            <input id="filter" type="text" class="form-control filter" placeholder="Rechercher...">
            <br>
            <table class="table">
                <thead>
                <tr>

                    <th scope="col">Activité</th>
                    <th scope="col">Lieux</th>
                    <th scope="col">Date</th>
                    <th scope="col">Heure début</th>
                    <th scope="col">Heure fin</th>
                    <th scope="col">Modifier</th>
                    <th scope="col">Supprimer</th>
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

                    String pattern = "dd-MM-yyyy";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

                    String patterntime = "hh:mm";
                    SimpleDateFormat time = new SimpleDateFormat(patterntime);
                    List<MyActivity> MyActivityList = myself.getMyActivities();
                    if (!activityList.isEmpty()) {
                        for (MyActivity m : MyActivityList) {
                            String date  = simpleDateFormat.format(m.getDate());
                            String start = time.format(m.getStartTime());
                            String end = time.format(m.getEndTime());

                %>

                <tr>
                    <td>
                        <%=m.getActivity().getName()%>
                    </td>
                    <td>
                        <%=m.getPlace().getCity()%>
                    </td>
                    <td>
                        <%=date%>
                    </td>
                    <td>
                        <%=start%>
                    </td>
                    <td>
                        <%=end%>
                    </td>
                    <td>
                        <a href="editMyActivity.jsp?idMyActivity=<%=m.getId()%>" class="btn btn-action btn-lg">Modifier</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/deleteMyActivity?idMyActivity=<%=m.getId()%>"
                           class="btn btn-danger btn-lg">Supprimer</a>
                    </td>
                </tr>

                <%
                        }
                    }
                %>


                </tbody>
            </table>
        </article>
    </div>
</div>

<%@include file="html/src.html" %>


</body>
</html>