<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="<c:url value="/resources/js/jquery.validate.min.js"/>"></script>
<script src="<c:url value="/resources/js/registration.js"/>"></script>
<script src="<c:url value="/resources/js/messages/messages_${locale}.js"/>"></script>

<div class="error">${repeatEmail}</div>
<form:form method="post" action="${pageContext.request.contextPath}/registration/try" commandName="registrationForm" id="formRegistration">    
<table>
    <tr>
        <td><spring:message code="label.email"/></td>
        <td><form:input path="email"/></td>
        <td><span class="error"><form:errors path="email"/></span></td>
    </tr>
    <tr>
        <td><spring:message code="label.displayEmail"/></td>
        <td><form:checkbox path="displayEmail"/></td>
    </tr>
    <tr>
        <td><spring:message code="label.password" /></td>
        <td><form:password path="password"/></td>
        <td><span class="error"><form:errors path="password"/></span></td>
    </tr>
    <tr>
        <td><spring:message code="label.passwordConfirm" /></td>
        <td><form:password path="passwordConfirm"/></td>
        <td><span class="error"><form:errors path="passwordConfirm"/></span></td>
    </tr>
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
        <td colspan="2"><input type="submit" value="<spring:message code="label.submit"/>"></td>
    </tr>
</table>
</form:form>