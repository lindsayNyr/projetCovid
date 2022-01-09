<%@ page import="fr.ul.projetcovid.persistence.Place" %>
<%@ page import="fr.ul.projetcovid.persistence.MyActivity" %>
<%@ page import="fr.ul.projetcovid.persistence.Activity" %>
<%@ page import="java.util.List" %>
<%@ page import="fr.ul.projetcovid.persistence.dao.*" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: lindsay
  Date: 1/7/22
  Time: 9:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- head -->

<%

    String idMyActivity = request.getParameter("idMyActivity");
    MyActivity myActivity = new MyActivityDAO().getById(idMyActivity);



%>

<html>
<head>
    <%@include file="html/head.html" %>
    <title>Editer mes activité</title>
</head>
<body>
<%@include file="navbar.jsp" %>
<header id="head" class="secondary"></header>

<!-- container -->
<div class="container">


    <div class="row">

        <!-- Article main content -->
        <article class="col-xs-12 maincontent">
            <header class="page-header">
                <h1 class="page-title">Editer son activité </h1>
            </header>

            <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <h3 class="thin text-center">Modifier l'activité </h3>

                        <hr>

                        <% if (request.getAttribute("error") != null) {%>
                        <div class="error">
                            <span class="error">
                            Une erreur a été rencontrée: <%=request.getAttribute("error")%>
                            </span>
                        </div>
                        <%
                            }
                            String pattern = "YYYY-MM-DD";
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

                            String patterntime = "hh:mm";
                            SimpleDateFormat time = new SimpleDateFormat(patterntime);
                        %>
s
                        <form method="POST"
                              action="${pageContext.request.contextPath}/editMyActivity?idMyActivity=<%=myActivity.getId()%>">

                            <div class="row top-margin">
                                <div class="top-margin">
                                    <select type="text" class="form-control formFriend" id="idActivity"
                                            name="idActivity"
                                            required="required">
                                        <%
                                            List<Activity> activityList = new ActivityDAO().getAll();
                                            if (!activityList.isEmpty()) {
                                                for (Activity a : activityList) {
                                                    if (a == myActivity.getActivity()) { %>

                                                    <option selected value="<%=a.getId()%>"><%= a.getName()%>   </option>

                                                <%}else{ %>

                                        <option value="<%=a.getId()%>"><%= a.getName() %></option>

                                        <%
                                                    }
                                                }
                                            }

                                        %>


                                    </select>


                                    <select type="text" class="form-control formFriend" id="idPlace" name="idPlace"
                                            required="required">
                                        <% List<Place> placesList = new PlaceDAO().getAll();
                                            if (!placesList.isEmpty()) {
                                                for (Place p : placesList) {
                                                    if (p == myActivity.getPlace()) { %>

                                    <option selected value="<%=p.getId()%>"> <%= p.getName()%> - <%=p.getCity()%> </option>

                                        <%}else{ %>

                                        <option value="<%=p.getId()%>"><%= p.getName()%> - <%=p.getCity()%></option>

                                        <%
                                                    }
                                                }
                                            }

                                        %>



                                    </select>

                                </div>
                                <div class="top-margin">
                                    <input type="date" class="form-control formFriend" id="date"
                                           name="date"  value="<%=simpleDateFormat.format(myActivity.getDate())%>">



                                </div>
                                <div class="top-margin">
                                    <input type="time" class="form-control formFriend" id="startTime"
                                           name="startTime" placeholder="heure de début" value="<%=time.format(myActivity.getStartTime())%>">

                                </div>
                                <div class="top-margin">
                                    <input type="time" class="form-control formFriend" id="endTime"
                                           name="endTime" placeholder="heure de fin" value="<%=time.format((myActivity.getEndTime()))%>">
                                </div>


                            </div>


                            <br>

                            <button class="btn btn-action btnActivite formFriend" type="submit">Enregistré
                            </button>

                        </form>
                    </div>

                </div>

        </article>
        <!-- /Article -->

    </div>
</div>    <!-- /container -->

<%@include file="html/src.html" %>
</body>
</html>