<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <div class="ok">${registrationOk}</div>
<c:if test="${not empty param.error}">
	<font color="red"> ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} </font>
</c:if>
<form method="POST" action="<c:url value="/j_spring_security_check" />">
<table>
	<tr>
            <td align="right"><spring:message code="label.email"/>: </td>
		<td><input type="text" name="j_username" /></td>
	</tr>
	<tr>
		<td align="right"><spring:message code="label.password"/>: </td>
		<td><input type="password" name="j_password" /></td>
	</tr>
        <tr>            
            <td colspan="2" align="right"><input type="submit" value="<spring:message code="label.login"/>" /></td>
	</tr>
</table>
</form>
    <a href = "${pageContext.request.contextPath}/registration"><spring:message code = "label.registration"/></a>
