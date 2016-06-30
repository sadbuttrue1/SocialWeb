<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="bodyNav">
    <ol>
        <li><a href="${pageContext.request.contextPath}/profile"><spring:message code="label.profile"/></a></li>
        <li><a href="${pageContext.request.contextPath}/friend/all"><spring:message code="label.myFriends"/></a></li>
        <li><a href="${pageContext.request.contextPath}/chat"><spring:message code="label.myMessage"/></a></li>
        <li><a href="${pageContext.request.contextPath}/settings"><spring:message code="label.settings"/></a></li>
        <li><a href="${pageContext.request.contextPath}/search"><spring:message code="label.search"/></a></li>
        <li><a href="<c:url value="/logout"/>"><spring:message code="label.logout"/></a></li>
    </ol>
</div>
