<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="_header.jsp"%>

<div class="container text-center">
    <div class="row">
        <div class="col-sm-4"></div>
        <div class="col-sm-4">
            <div class="card bg-light p-3">
                <h4> <a href="<c:url value="/user-${user.id}"/>">${user.firstname} ${user.lastname} </a>File</h4>
                <ul class="list-group list-group-flush">
                    <c:choose>
                        <c:when test="${docs}">
                            <li class="list-group-item"><small>Name </small> <strong>${user.userDocuments.name}</strong></li>
                            <li class="list-group-item"><small>Description </small> <strong> ${user.userDocuments.descr}</strong></li>
                            <li class="list-group-item"><small>Type </small> <strong>${user.userDocuments.type}</strong></li>

                            <li class="list-group-item">
                                <a href="<c:url value="/downloadFile-${user.id}-${user.userDocuments.idUD}"/>"><button type="button" class="btn btn-outline-secondary btn-sm"> Download </button></a>
                                <a href="<c:url value="/deleteFile-${user.id}"/>"><button type="button" class="btn btn-outline-secondary btn-sm"> Delete </button></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="list-group-item"> No files</li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
            <hr>
            <h5> Upload New File</h5>
            <form:form method="POST" modelAttribute="fileBucket" enctype="multipart/form-data" role="form" class="form-group ">
                <div class="form-group">
                    <label for="file" class="control-label  ">Insert document: </label>
                    <form:input type="file" path="file" id="file" class="form-control ${invalidFB}"/>
                    <div class="invalid-feedback">
                        <form:errors path="file"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="description" class="control-label  ">Description: </label>
                    <form:input type="text" path="description" class="form-control ${invalidDesc}" id="description"/>
                    <div class="invalid-feedback">
                        <form:errors path="description"/>
                    </div>
                </div>
                <input type="submit" value="Upload" class="btn btn-outline-primary"/>
            </form:form>
        </div>
    </div>
</div>

<%@ include file="_footer.jsp"%>