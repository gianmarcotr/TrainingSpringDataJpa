<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="_header.jsp"%>


<div class="container text-center">
    <div class="row" id="asd">
        <div class="col-sm-4"></div>

        <div class="col-sm-4">
            <h3> Inserisci dati utente</h3><hr>
            <form:form method="POST" modelAttribute="user" role="form" class="form-group " data-toggle="validator"  id="addU" novalidate="">
                <div class="form-group">
                    <label for="firstname" class="control-label  ">*First name:</label>
                    <form:input type="text" path="firstname" class="form-control ${invalidFN}" id="firstname"/>
                    <div class="invalid-feedback">
                        <form:errors path="firstname"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="lastname" class="control-label ">*Last name:</label>
                    <form:input type="text" path="lastname" class="form-control ${invalidLN}" id="lastname"/>
                    <div class="invalid-feedback">
                        <form:errors path="lastname"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="country" class="control-label ">Country:</label>
                    <form:input type="text" path="country"  class="form-control" id="country"/>
                    <div class="help-block with-error">
                        <form:errors path="country" class="is-invalid"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="birthDate" class="control-label">Birth Date: </label>
                    <form:input type="date" path="birthDate" class="form-control ${invalidBD}" id="birthDate"/>
                    <div class="invalid-feedback">
                            <form:errors path="birthDate"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="" class="control-lablel">Marital Status: </label>
                    <form:select path="maritalStatus.idMS"  multiple="false"  class="form-control input-sm" >
                        <form:option value="0">--SELECT--</form:option>
                        <form:options items="${maritalStatus}" itemValue="idMS" itemLabel="nome"/>
                    </form:select>
                    <div class="has-error">
                        <form:errors path="maritalStatus" class="help-inline"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="" class="control-lablel">Skills: </label>
                    <select class="form-control" onchange="addSkill()" id="skl" form="addU">
                        <option selected>--SELECT--</option>
                        <c:forEach var="skill" items="${listSkills}">
                            <option value="${skill.idS}" id="${skill.idS}"> ${skill.nome} </option>
                            <c:forEach var="su" items="${user.skills}">
                                <c:if test="${skill.idS == su.idS}">
                                    <script>document.getElementById('${skill.idS}').remove();</script>
                                </c:if>
                            </c:forEach>
                        </c:forEach>
                    </select>

                    <div class="card bg-light p-3" id="pos">
                        <c:forEach var="su" items="${user.skills}">
                            <input type="hidden" name="skills" value="${su.idS}" id="${su.idS}hid">
                            <button id="${su.idS}sk" class="btn btn-outline-dark btn-sm" size="5" onclick="rmvSkill('${su.nome}', '${su.idS}')">${su.nome}</button>
                        </c:forEach>
                    </div>
                </div>



                <c:choose>
                    <c:when test="${edit}">
                        <input type="submit" value="Update" class="btn btn-primary btn-sm"/> or
                        <a href="<c:url value='/list' />">Cancel</a>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Register" class="btn btn-outline-primary"/> or
                        <a href="<c:url value='/list' />">Cancel</a>
                    </c:otherwise>
                </c:choose>
                <hr>
                <p class="text-primary">* campi obbligatori</p>
            </form:form>
        </div>

    </div>
</div>
<div class="span4"></div>



</body>

<%@ include file="_footer.jsp"%>
