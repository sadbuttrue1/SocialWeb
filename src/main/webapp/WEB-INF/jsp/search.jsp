<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="<c:url value="/resources/js/jquery.validate.min.js"/>"></script>
<script src="<c:url value="/resources/js/messages/messages_${locale}.js"/>"></script>
<script src="<c:url value="/resources/js/search.js"/>"></script>

<spring:url value="/profile/photo" var="userPhotoUrl"/>

<div id="searchResult">
    <c:if test="${!empty UserDatas}">
        <c:forEach items="${UserDatas}" var="userData">
            <c:set var="photo" value="${userData.photo}"/>
            <c:if test="${empty userData.photo}">
                <c:set var="photo" value="empty.gif"/>
            </c:if>            
            <div class="divSearchUserData">
                <div class="avatarSearch">
                    <img src="../../avatar/${photo}" width="100" height="100">
                </div>
                <a href="${pageContext.request.contextPath}/profile/${userData.profileLink}">${userData.surname} ${userData.name}</a>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${empty UserDatas}">
        <spring:message code="label.emptyUserData"/>
        <c:set value="none" var="classPageNumber" />
    </c:if>
</div>

<div id="serch">
    <form:form method="post" action="${pageContext.request.contextPath}/search"
               commandName="searchForm" id="formSearchUserData">
        <table>    
            <tr>
                <td><spring:message code="label.name"/>:</td>
                <td><form:input path="name"/></td>
                <td><span class="error"><form:errors path="name"/></span></td>
            </tr>
            <tr>
                <td><spring:message code="label.surname"/>:</td>
                <td><form:input path="surname"/></td>
                <td><span class="error"><form:errors path="surname"/></span></td>
            </tr>
            <tr>
                <td><spring:message code="label.hometown"/>:</td>
                <td><form:input path="hometown"/></td>
                <td><span class="error"><form:errors path="hometown"/></span></td>
            </tr>
            <tr>
                <td><spring:message code="label.age" />:</td>
                <td><input type="number" name="ageFrom" id="ageFrom" min="6" max="120" size="5"
                           value="<c:if test="${searchForm.ageFrom != 0}">${searchForm.ageFrom}</c:if>"
                           placeholder="<spring:message code="label.From"/>"/>
                    <input type="number" name="ageTo" id="ageTo" min="6" max="120" size="5"
                           value="<c:if test="${searchForm.ageTo != 0}">${searchForm.ageTo}</c:if>"
                           placeholder="<spring:message code="label.To"/>"/></td>
                <td><span class="error" id="age"><form:errors path="ageFrom"/> <form:errors path="ageTo"/></span></td>
            </tr>   
            <tr>
                <td><spring:message code="label.hobby" />:</td>
                <td><form:input path="hobby" /></td>
                <td><span class="error"><form:errors path="hobby"/></span></td>
            </tr>   
            <tr>
                <td><spring:message code="label.email" />:</td>
                <td><form:input path="email" /></td>
                <td><span class="error"><form:errors path="email"/></span></td>
            </tr>            
            <tr class="${classPageNumber}">
                <td><spring:message code="label.pageNumber" />:</td>
                <td><input type="number" id="pageNumber" name="pageNumber"
                           value="${searchForm.pageNumber}" min="1" max="${sizeListUser}"></td>
                <td><span class="error"><form:errors path="pageNumber"/></span></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="<spring:message code="label.find"/>"></td>
            </tr>
        </table>
    </form:form>
</div>
