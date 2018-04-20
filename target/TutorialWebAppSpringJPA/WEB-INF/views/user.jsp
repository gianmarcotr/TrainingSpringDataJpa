<%@ include file="_header.jsp"%>
<div class="container">
    <div class="row my-2">
        <div class="col-lg-8 order-lg-2">
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <a href="" data-target="#profile" data-toggle="tab" class="nav-link active">Profile</a>
                </li>
                <li class="nav-item">
                    <a href="" data-target="#edit" data-toggle="tab" class="nav-link">Edit</a>
                </li>
            </ul>
            <div class="tab-content py-4">
                <div class="tab-pane active" id="profile">
                    <h5 class="mb-3">${user.firstname} ${user.lastname}</h5>
                    <div class="row">
                        <div class="col-md-6">
                            <h6>Date of birth</h6>
                            <p>
                                <c:set var = "userDate" value = "${user.birthDate}" />
                                <fmt:formatDate type = "date" value = "${userDate}" />
                            </p>
                            <h6>County</h6>
                            <p>
                                ${user.country}
                            </p>
                            <h6>Marital Status</h6>
                            <p>
                                ${user.maritalStatus.nome}
                            </p>
                        </div>
                        <div class="col-md-6">
                            <h6>Skills</h6>
                            <c:choose>
                                <c:when test="${noSkill}">
                                    <script>printNotice('No skills: click on Edit to add skills', 'warning', 3000)</script>
                                    <p> No skills </p>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="su" items="${user.skills}">
                                        <a href="#" class="badge badge-dark badge-pill">${su.nome}</a>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                            <hr>
                            <h6>User options </h6>
                            <a href="<c:url value="/edit-user-${user.id}"/>"><button type="button" class="btn btn-outline-warning">Edit</button></a>
                            <a id="a${u.id}" href=""><button type="button"  onclick="deleteConfirm(${user.id})" class="btn btn-outline-danger">Delete</button></a>
                            <a href="<c:url value="/new"/>"><button type="button" class="btn btn-outline-success">New</button></a>
                            <a href="<c:url value="/"/>"><button type="button" class="btn btn-outline-info">List Users</button></a>
                        </div>
                    </div>
                    <!--/row-->
                </div>
                <div class="tab-pane" id="edit">

                </div>
            </div>
        </div>
        <div class="col-lg-4 order-lg-1 text-center">
            <br><br><br>
            <div class="card bg-light p-3">
            <c:choose>
                <c:when test="${docs}">
                    <h6>File</h6>
                    <p>${user.userDocuments.name}</p>
                </c:when>
                <c:otherwise>
                    <p>No files</p>
                </c:otherwise>
            </c:choose>
            <br>
            <a href="<c:url value="/uploadFile-${user.id}"/>"><button type="button" class="btn btn-outline-dark btn-sm">Manage File</button></a>
            </div>
        </div>
    </div>
</div>
</body>
<%@ include file="_footer.jsp"%>

