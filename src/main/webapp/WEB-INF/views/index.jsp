
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ include file="_header.jsp"%>

<div class="container text-center">
    <div clasS="row">
        <div class="col-sm-12"><h3>USERS </h3></div>
    </div>
    ${success}
    <table class="table table-hover">
        <tr>
            <th>Firstname</th>
            <th>Lastname</th>
            <th>Country</th>
            <th>Birth Date</th>
            <th>Marital Status</th>
            <th>Skills</th>
            <th>Document</th>
            <th>Option</th>
        </tr>
        <c:forEach var="u" items="${users}">
            <c:set var = "userDate" value = "${u.birthDate}" />
            <tr>
                <td><a href="<c:url value="/user-${u.id}"/>">${u.firstname}</a></td>
                <td>${u.lastname}</td>
                <td>${u.country}</td>
                <td><fmt:formatDate type = "date" value = "${userDate}" /></td>
                <td>${u.maritalStatus.nome}</td>
                <td class="dropdown">
                    <button class="btn btn-outline-secondary dropdown-toggle btn-sm" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        View skills
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <c:forEach var="su" items="${u.skills}">
                            <p class="dropdown-item dropdown-item-sm"> <small> ${su.nome} </small></p>
                        </c:forEach>
                    </div>
                </td>
                <td>
                    <div class="btn-group">
                        <button type="button" onclick="printNotice('${u.userDocuments.name}', 'info', 500);" class="btn btn-outline-secondary btn-sm">
                            ${u.userDocuments.name}
                        </button>
                        <button type="button" class="btn btn-outline-secondary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="sr-only">Toggle Dropdown</span>
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <a href="<c:url value="/uploadFile-${u.id}"/>"><button type="button" class=" btn dropdown-item dropdown-item-sm"><small>Manage File</small></button></a>
                        </div>
                    </div>
                </td>
                <td>
                    <a href="<c:url value="/edit-user-${u.id}"/>"><button type="button" class="btn btn-outline-warning btn-sm">edit</button></a>
                    <a id="a${u.id}" href=""><button type="button"  onclick="deleteConfirm(${u.id})" class="btn btn-outline-danger btn-sm">delete</button></a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <c:if test="${noElement}">
        <div class="alert alert-warning" role="alert">
            <strong>Warning!</strong> No element found
        </div>
    </c:if>
    <hr>

    <a href="new"><button type="button" class="btn btn-success">Add User</button></a>
    <c:if test="${reset}">
        <a href="/"><button type="button" class="btn btn-warning">Reset</button> </a>
    </c:if>

    <hr>

    <!-- TEST NOTIFICHE LATO CLIENT -->
    <button type="button" class="alert alert-success" onclick="printNotice('success', 'success', '3000');"> test </button>
    <button type="button" class="alert alert-warning" onclick="printNotice('warning', 'warning', '3000');"> test </button>
    <button type="button" class="alert alert-info" onclick="printNotice('info', 'info', '3000');"> test </button>
    <button type="button" class="alert alert-danger" onclick="printNotice('danger', 'danger', '3000');"> test </button>
    <button type="button" class="alert alert-primary" onclick="printNotice('primary', 'primary', '3000');"> test </button>
    <button type="button" class="alert alert-secondary" onclick="printNotice('secondary', 'secondary', '3000');"> test </button>
    <button type="button" class="alert alert-light" onclick="printNotice('light', 'light', '3000');"> test </button>
    <button type="button" class="alert alert-dark" onclick="printNotice('dark', 'dark', '3000');"> test </button>
</div>

</body>
<%@ include file="_footer.jsp"%>

