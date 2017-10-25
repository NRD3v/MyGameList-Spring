<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            <button id="menu-toggle" class="btn btn-secondary float-left mt0 mb20">Menu</button>
            <button type="button" class="btn btn-success float-right mt0 mb20">Ajouter</button>

            <table class="table table-sm table-hover">
                <thead class="thead-light">
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
                        <th class="align-middle" scope="row">
                            <button class="btn btn-sm btn-outline-secondary">
                                <c:if test="${game.id != null}">
                                    ${game.id}
                                </c:if>
                            </button>
                        </th>
                        <td class="align-middle">
                            <c:if test="${game.name != null}">
                                ${game.name}
                            </c:if>
                        </td>
                        <td class="align-middle">
                            <c:if test="${game.developer.name != null}">
                                ${game.developer.name}
                            </c:if>
                        </td>
                        <%--<td class="align-middle">--%>
                            <%--<c:if test="${game.platform.name != null}">--%>
                                <%--${game.platform.name}--%>
                            <%--</c:if>--%>
                        <%--</td>--%>
                        <%--<td class="align-middle">--%>
                            <%--<c:if test="${game.year != null}">--%>
                                <%--${game.year}--%>
                            <%--</c:if>--%>
                        <%--</td>--%>
                        <%--<td class="align-middle">--%>
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
</script>
</body>
</html>
