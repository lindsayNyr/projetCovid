<%@ page import="fr.ul.projetcovid.persistence.UserAccount" %>
<%@ page import="java.util.Optional" %>
<%@ page import="fr.ul.projetcovid.persistence.dao.UserAccountDAO" %><%--
  Created by IntelliJ IDEA.
  User: lindsay
  Date: 12/27/21
  Time: 10:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<!-- head -->


<head>
<%@include  file="html/head.html" %>

  <title>home</title>
</head>
<body class="home">

<!-- navbar -->
<%@include  file="navbar.jsp" %>

<!-- Header -->
<header id="head">
  <div class="container">
    <div class="row">
      <%if (session.getAttribute("id") == null) {%>
        <h1 class="lead">BIENVENUE</h1>
      <%}else{

        Optional<UserAccount> acc = new UserAccountDAO().getById((String) session.getAttribute("id"));
        if (!acc.isPresent()) {
          response.sendError(403);
          return;
        }
        UserAccount account = acc.get();
      %>

        <h1 class="lead">Hello, <%= account.getPrenom()%> </h1>
      <%}%>

      <p class="tagline">COVIDTRACKER : Le site de traçage du covid</p>

    </div>
  </div>
</header>
<!-- /Header -->

<!-- Intro -->
<div class="container text-center">
  <br> <br>
  <h2 class="thin">Nous enregistrons vos activités</h2>
  <p class="text-muted">
   CovidTracker répertorie toutes vos activités afin de vous avertir d'un éventuel cas contact<br>

  </p>
</div>
<!-- /Intro-->

<!-- Highlights - jumbotron -->
<div class="jumbotron top-space">
  <div class="container">

    <h3 class="text-center thin">Pourquoi utilisé CovidTracker ? </h3>

    <div class="row">
      <div class="col-md-3 col-sm-6 highlight">
        <div class="h-caption"><h4><i class="fa fa-cogs fa-5"></i>Evolutions</h4></div>
        <div class="h-body text-center">
          <p>Nous développons le site chaque jour, et réagissons en moins de 24h aux éventuels problèmes technique grace à notre équipe de développeurs </p>
      </div>
      </div>
      <div class="col-md-3 col-sm-6 highlight">
        <div class="h-caption"><h4><i class="fa fa-flash fa-5"></i>Puissance</h4></div>
        <div class="h-body text-center">
          <p>Nos équipes donnent toute son énergie de jour en jour pour augmenter la puissance du site</p>
        </div>
      </div>
      <div class="col-md-3 col-sm-6 highlight">
        <div class="h-caption"><h4><i class="fa fa-heart fa-5"></i>Communauté</h4></div>
        <div class="h-body text-center">
          <p>Nous avons une communauté soudée, avec notre grande communauté nous pouvons être plus réactives </p>
        </div>
      </div>
      <div class="col-md-3 col-sm-6 highlight">
        <div class="h-caption"><h4><i class="fa fa-smile-o fa-5"></i>Clients satisfaits</h4></div>
        <div class="h-body text-center">
          <p>Nous avons un taux de satisfactions de nos Clients de 98% et nous nous améliorions en prenant en compte leurs avis en tout circonstances</p>
        </div>
      </div>
    </div> <!-- /row  -->

  </div>
</div>
<!-- /Highlights -->

<!-- container -->
<div class="container">

  <h2 class="text-center top-space">Questions fréquentes</h2>
  <br>

  <div class="row">
    <div class="col-sm-6">
      <h3>En combien de temps sommes-nous notifier d'être cas contact ?  </h3>
      <p>La notification est instantanée, dès que la personne s'est déclarée positive</p>
    </div>
    <div class="col-sm-6">
      <h3>Pouvons-nous gérer les activités d'un proche ?</h3>
      <p>Non, nous y travaillons, encore un peu de patience ...</p>
    </div>
  </div> <!-- /row -->

  <div class="row">
    <div class="col-sm-6">
      <h3>Peut-on enregistrer des activités dans d'autres pays ?</h3>
      <p>
        Nous n'enregistrons pas les pays, mais vous pouvez saisir une activité dans une ville étrangère même si l'auto-complétion vous ne le propose pas
      </p>
    </div>
    <div class="col-sm-6">
      <h3>Peut-on ajouter des amis qui étaient présent avec moi a une activité ?</h3>
      <p>Non, chacun de vos amis doit déclarer l'activité personnellement</p>
    </div>
  </div> <!-- /row -->

  <div class="jumbotron top-space">
    <h4>Vous pouvez contactez notre service client au : +33 1 28 53 87 18
    ou par adresse mail a : <a href="mailto:covidTracker@service.com">covidTracker@service.com</a></h4>
  </div>

</div>	<!-- /container -->


<!-- src -->
<%@include  file="html/src.html" %>


</body>
</html>