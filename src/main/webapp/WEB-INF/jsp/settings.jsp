<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="<c:url value="/resources/js/jquery.validate.min.js"/>"></script>
<script src="<c:url value="/resources/js/settings.js"/>"></script>
<script src="<c:url value="/resources/js/messages/messages_${locale}.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.listen-1.0.3-min.js"/>"></script>

<div id="settings">
    <p id = "statusError">${statusChange}</p>
    <form:form method="post" action="settings" modelAttribute="user" id="formUserData"
               enctype="multipart/form-data">
        <form:hidden path="Id" />
        <form:hidden path="photo" />
        <table>    
            <tr>
                <td><spring:message code="label.name"/></td>
                <td><form:input path="name"/></td>
                <td><span class="error"><form:errors path="name"/></span></td>
            </tr>
            <tr>
                <td><spring:message code="label.surname"/></td>
                <td><form:input path="surname"/></td>
                <td><span class="error"><form:errors path="surname"/></span></td>
            </tr>
            <tr>
                <td><spring:message code="label.hometown"/></td>
                <td><form:input path="hometown"/></td>
                <td><span class="error"><form:errors path="hometown"/></span></td>
            </tr>
            <tr>
                <td><spring:message code="label.birthday" /></td>
                <td><input type="date" name="birth" id="birth" value="${user.parseDate()}"/></td>
                <td><span class="error"><form:errors path="birth"/></span></td>
            </tr>   
            <tr>
                <td><spring:message code="label.hobby" /></td>
                <td><form:input path="hobby" /></td>
                <td><span class="error"><form:errors path="hobby"/></span></td>
            </tr>
            <tr>
                <td><spring:message code="label.telephone" /></td>
                <td><form:input path="phone" /></td>
                <td><span class="error"><form:errors path="phone"/></span></td>
            </tr>
            <tr>
                <td><spring:message code="label.email" /></td>
                <td>
                    <c:if test="${!empty user.emails}">
                        <c:forEach items="${user.emails}" var="email_" varStatus="status">
                            <form:hidden path="emails[${status.index}].Id"/>
                            <form:hidden path="emails[${status.index}].remove" />
                            <div id="emails${status.index}">
                                <form:input path="emails[${status.index}].email"/>
                                <a href="#" id="${status.index}" class="delEmail">Remove</a>
                            </div>                        
                        </c:forEach>                    
                    </c:if>
                    <a href="#" id="addEmail" class="${user.emails.size()}">Add</a>
                </td>        
            </tr>
            <tr>
                <td><spring:message code="label.profileLink" /></td>
                <td><form:input path="profileLink" /></td>
                <td><span class="error"><form:errors path="profileLink"/></span></td>
            </tr>
            <tr>
                <td><spring:message code="label.photo" /></td>
                <td><input type="file" name="photoImg" id="photoImg"
                           accept="image/jpeg,image/jpg,image/png,image/gif"/></td>
                <td><span class="error"><form:errors path="photoImg"/></span></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="<spring:message code="label.change"/>"></td>
            </tr>
        </table>
    </form:form>
    <div id="LocaleChange">
        <p><spring:message code="label.localeChange"/></p>
        <a href="?locale=ru">ru</a>
        <a href="?locale=en">en</a>
    </div>
</div>