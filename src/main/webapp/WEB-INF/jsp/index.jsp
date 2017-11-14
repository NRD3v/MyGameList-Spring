<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="A MyGameList Spring app">
    <meta name="author" content="NRD3v - Nicolas Rabagny">
    <title>MyGameList - Spring App</title>

    <!-- Core CSS -->
    <c:url value="/css/bootstrap-lumen.min.css" var="bootstrapCss" />
    <link href="${bootstrapCss}" rel="stylesheet" />
    <c:url value="/css/simple-sidebar.css" var="bootstrapLumen" />
    <link href="${bootstrapLumen}" rel="stylesheet" />
    <c:url value="/css/datatables.min.css" var="datatablesCss" />
    <link href="${datatablesCss}" rel="stylesheet" />

    <!-- Core JavaScript -->
    <c:url value="/js/jquery.min.js" var="jquery" />
    <script type="text/javascript" src="${jquery}"></script>
    <c:url value="/js/bootstrap.min.js" var="bootstrapJs" />
    <script type="text/javascript" src="${bootstrapJs}"></script>
    <c:url value="/js/underscore.min.js" var="underscore" />
    <script type="text/javascript" src="${underscore}"></script>
    <c:url value="/js/datatables.min.js" var="datatablesJs" />
    <script type="text/javascript" src="${datatablesJs}"></script>


    <!-- Custom CSS -->
    <c:url value="/css/main.css" var="jstlMainCss" />
    <link href="${jstlMainCss}" rel="stylesheet" />

    <!-- Custom JavaScript -->
    <c:url value="/js/main.js" var="jstlMainJs" />
    <script type="text/javascript" src="${jstlMainJs}"></script>
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
            <span id="menu-toggle" class="btn btn-primary mt0 mb20">
                <span class="glyphicon glyphicon-menu-hamburger"></span>
            </span>
            <%--<button type="button" class="btn btn-success pull-right mt0 mb20"--%>
                    <%--data-toggle="modal" data-target="#gameModal">Ajouter</button>--%>
            <%--<br>--%>
            <form class="form-horizontal mb20 col-lg-3 pull-right">
                <div class="form-group">
                    <div class="input-group">
                    <span class="input-group-addon" id="sizing-addon1">
                        <span class="glyphicon glyphicon-search"></span>
                    </span>
                    <input type="text" class="form-control" name="gameNameSearch" id="gameNameSearch"
                           placeholder="Rechercher un jeu (min. 3 caractères)" aria-describedby="sizing-addon1">
                    </div>
                </div>
            </form>

            <!-- Modal -->
            <div class="modal fade" id="gameModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <form class="form-horizontal" method="POST" action="/game/add">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title" id="myModalLabel"></h4>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="nameField">Titre</label>
                                    <div class="col-lg-9">
                                        <spring:bind path="newGame.gameName">
                                            <input type="text" class="nameField form-control" id="nameField" name="${status.expression}"
                                                   value="${status.value}" placeholder="Entrer un titre" required="required">
                                        </spring:bind>
                                    </div>
                                    <spring:bind path="newGame.gameGiantbombId">
                                        <input type="hidden" class="giantbombIdGameField" name="${status.expression}"
                                               value="${status.value}" placeholder="Entrer un titre">
                                    </spring:bind>
                                    <spring:bind path="newGame.releaseDate">
                                        <input type="hidden" class="releaseDateField" name="${status.expression}"
                                               value="${status.value}" placeholder="Entrer un titre" required="required">
                                    </spring:bind>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="developerField">Développeur</label>
                                    <div class="col-lg-9">
                                        <spring:bind path="newDeveloper.developerName">
                                            <input type="text" class="developerField form-control" id="developerField" name="${status.expression}"
                                                   value="${status.value}" placeholder="Entrer un développeur">
                                        </spring:bind>
                                    </div>
                                    <spring:bind path="newDeveloper.developerGiantbombId">
                                        <input type="hidden" class="giantbombIdDeveloperField" name="${status.expression}"
                                               value="${status.value}" placeholder="Entrer un titre">
                                    </spring:bind>
                                </div>
                                <%--<form:form cssClass="form-horizontal" method="POST" action="/game/add" modelAttribute="newDeveloper">--%>
                                <%--<div class="form-group">--%>
                                    <%--<form:label cssClass="col-lg-2 control-label" path="developer">Développeur</form:label>--%>
                                    <%--<div class="col-lg-9">--%>
                                        <%--<form:input cssClass="developerField form-control" path="developer" placeholder="Entrer un développeur" />--%>
                                        <%--<form:input type="hidden" cssClass="giantbombIdDeveloperField" path="giantbombId" />--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--</form:form>--%>
                            </div>
                            <div class="modal-footer">
                                <span id="deleteButton" data-dismiss="modal" class="btn btn-danger pull-left"
                                      data-toggle="modal" data-target="#gameDeleteModal">Supprimer</span>
                                <span class="btn btn-default" data-dismiss="modal">Fermer</span>
                                <input type="submit" id="saveButton" value="Enregistrer" class="btn btn-success" />
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div class="row col-lg-12 text-center">
                <p id="waiting"><strong>Chargement en cours...</strong></p>
                <div id="results"></div>
            </div>

            <table id="dataTable" class="table table-sm table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Titre</th>
                    <th>Développeur(s)</th>
                    <th>Plateforme(s)</th>
                    <th>Sortie</th>
                    <%--<th>Actions&nbsp;&nbsp;<span class="glyphicon glyphicon-menu-down"></span></th>--%>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="gameRelease" items="${gameReleases}">
                    <tr class="dataRow" id="${gameRelease.game.id}" data-toggle="modal" data-target="#gameModal">
                        <th scope="row">
                            <span class="btn btn-secondary" data-toggle="modal" data-target="#showGameModal">
                                <span class="glyphicon glyphicon-search"></span>
                            </span>
                        </th>
                        <td>
                            <c:if test="${gameRelease.game.gameName != null}">
                                <strong>${gameRelease.game.gameName}</strong>
                            </c:if>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${gameRelease.game.developers != null}">
                                    <c:forEach var="developer" items="${gameRelease.game.developers}" varStatus="status">
                                        ${developer.developerName}
                                        <c:if test="${!status.last}">,</c:if>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <em>Aucune donnée disponible...</em>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${gameRelease.platform.abbreviation != null}">
                                    <div class="platform ${fn:toLowerCase(gameRelease.platform.abbreviation)}">
                                        ${gameRelease.platform.abbreviation}
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <em>Aucune plateforme sélectionnée...</em>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:if test="${gameRelease.game.releaseDate != null}">
                                <fmt:parseDate value="${gameRelease.game.releaseDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss" var="formatedDate"/>
                                <fmt:formatDate value="${formatedDate}" type="date" pattern="yyyy"/>
                            </c:if>
                        </td>
                        <%--<td>--%>
                            <%--<button class="btn btn-xs btn-warning">--%>
                                <%--<span class="glyphicon glyphicon-pencil"></span>--%>
                            <%--</button>--%>
                            <%--<button data-toggle="modal" data-target="#gameDeleteModal" class="btn btn-xs btn-danger">--%>
                                <%--<span class="glyphicon glyphicon-remove"></span>--%>
                            <%--</button>--%>

                            <div class="modal fade" id="gameDeleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                            <h4 class="modal-title" id="gameDeleteModalLabel">Attention !</h4>
                                        </div>
                                        <div class="modal-body text-center">
                                            <p class="m20">Souhaitez-vous supprimer le jeu de votre liste ?</p>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                                            <a href="/game/${gameRelease.game.id}/delete">
                                                <button type="submit" value="Submit" class="btn btn-danger">Supprimer</button>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
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
</body>
</html>
