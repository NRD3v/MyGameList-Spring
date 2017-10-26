<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="A MyGameList Spring app">
    <meta name="author" content="NRD3v - Nicolas Rabagny">
    <title>MyGameList - Spring App</title>
    <!-- Bootstrap core CSS -->
    <c:url value="/css/bootstrap.min.css" var="jstlBootstrapCSS" />
    <link href="${jstlBootstrapCSS}" rel="stylesheet" />
    <c:url value="/css/simple-sidebar.css" var="jstlBootstrapTemplate" />
    <link href="${jstlBootstrapTemplate}" rel="stylesheet" />
    <!-- Custom CSS -->
    <c:url value="/css/main.css" var="jstlMainCss" />
    <link href="${jstlMainCss}" rel="stylesheet" />
    <!-- jQuery core JavaScript -->
    <c:url value="/js/jquery.min.js" var="jstlJQuery" />
    <script type="text/javascript" src="${jstlJQuery}"></script>
    <!-- Bootstrap core JavaScript -->
    <c:url value="/js/bootstrap.min.js" var="jstlBootstrapJS" />
    <script type="text/javascript" src="${jstlBootstrapJS}"></script>
</head>
<body>
<div id="wrapper">
    <!-- Sidebar -->
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebar-brand">
                <a href="#">
                    MYGAMELIST
                </a>
            </li>
            <li>
                <a href="#">Dashboard</a>
            </li>
            <li>
                <a href="#">Shortcuts</a>
            </li>
            <li>
                <a href="#">Overview</a>
            </li>
            <li>
                <a href="#">Events</a>
            </li>
            <li>
                <a href="#">About</a>
            </li>
            <li>
                <a href="#">Services</a>
            </li>
            <li>
                <a href="#">Contact</a>
            </li>
        </ul>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row">
                <button id="menu-toggle" class="btn btn-primary mt0 mb20">Menu</button>
                <form class="form-horizontal col-lg-3 pull-right">
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon" id="sizing-addon1"><span class="glyphicon glyphicon-search"></span></span>
                            <%--<input type="text" class="form-control"  name="gameNameSearch" id="gameNameSearch"--%>
                                   <%--placeholder="Rechercher un jeu" aria-describedby="sizing-addon1">--%>
                        </div>
                </form>
            </div>
            <button type="button" class="btn btn-success pull-right mt0 mb20"
                    data-toggle="modal" data-target="#gameAddModal">Ajouter</button>

            <!-- Modal -->
            <div class="modal fade" id="gameAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <form:form cssClass="form-horizontal" method="POST" action="/add" modelAttribute="newGame">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title" id="myModalLabel">Ajouter un jeu</h4>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <form:label cssClass="col-lg-2 control-label" path="name">Titre</form:label>
                                    <div class="col-lg-9">
                                        <form:input cssClass="form-control" path="name" placeholder="Entrer un titre" required="required"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label cssClass="col-lg-2 control-label" path="developer">Développeur</form:label>
                                    <div class="col-lg-9">
                                        <form:select cssClass="form-control" path="developer">
                                            <form:option value="NONE"> -- Choisir parmi la liste --</form:option>
                                            <c:forEach items="${developers}" var="developer">
                                                <form:option value="${developer.id}">${developer.name}</form:option>
                                            </c:forEach>
                                        </form:select>
                                    </div>
                                </div>
                                <%--<div class="form-group">--%>
                                    <%--<form:label cssClass="col-lg-2 control-label" path="developer">Développeur</form:label>--%>
                                    <%--<div class="col-lg-9">--%>
                                        <%--<form:input cssClass="form-control" path="developer"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
                                <button type="submit" value="Submit" class="btn btn-success">Enregistrer</button>
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>

            <div class="row text-center">
                <div id="results" class="col-lg-12"></div>
            </div>

            <table id="dataTable" class="table table-sm table-hover">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Titre</th>
                    <th scope="col">Développeur</th>
                    <%--<th scope="col">Support</th>--%>
                    <%--<th scope="col">Sortie</th>--%>
                    <%--<th scope="col">Score</th>--%>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="game" items="${games}">
                    <tr>
                        <th scope="row">
                            <button class="btn btn-sm">
                                <c:if test="${game.id != null}">
                                    ${game.id}
                                </c:if>
                            </button>
                        </th>
                        <td>
                            <c:if test="${game.name != null}">
                                ${game.name}
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${game.developer.name != null}">
                                ${game.developer.name}
                            </c:if>
                        </td>
                        <%--<td>--%>
                            <%--<c:if test="${game.platform.name != null}">--%>
                                <%--${game.platform.name}--%>
                            <%--</c:if>--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<c:if test="${game.year != null}">--%>
                                <%--${game.year}--%>
                            <%--</c:if>--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<c:if test="${game.score != null}">--%>
                                <%--${game.score}--%>
                            <%--</c:if>--%>
                        <%--</td>--%>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
    <!-- /#page-content-wrapper -->

</div>
<!-- /#wrapper -->

<script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    $("#gameNameSearch").keypress(function () {
        var val = $(this).val();
        if (val.length >= 3) {
            $("#results").empty();
            $.ajax({
                url: "/api/search/",
                type: "POST",
                data: { name: val },
                cache: false,
                dataType: "json",
                success: function (response) {
                    for (var i=0; i<response.length; i++) {
                        $.ajax({
                            url: "/api/search/",
                            type: "POST",
                            data: { id: response[i].id },
                            cache: false,
                            dataType: "json",
                            success: function (response) {
                                $("#results").append(
                                    "<div class='col-lg-2'>" +
                                    "<img class='mt20 mb10' style='border-radius:10px;' src='" + response[0].cover.url + "' height='150' width='150'>" +
                                    "<span class='btn btn-info btn-sm mb20' id='gameModal" + response[0].id + "'>" +
                                    "<span class='glyphicon glyphicon-question-sign'></span>" +
                                    "</span>" +
                                    "<span class='btn btn-info btn-sm mb20' id='gameModal" + response[0].id + "'>" +
                                    "<span class='glyphicon glyphicon-plus-sign'></span>" +
                                    "</span>" +
                                    "</div>"
                                );
                                console.log("Success:" + JSON.stringify(response));
                            },
                            error: function (response) {
                                console.log("Error:" + JSON.stringify(response));
                            }
                            //.done(function(data) { };
                        });
                    }
                },
                error: function (response) {
                    console.log("Error:" + JSON.stringify(response));
                }
                //.done(function(data) { };
            });
        }
    });
</script>
</body>
</html>
