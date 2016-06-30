<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="chatListFull">
    <a href="${pageContext.request.contextPath}/chat/createChat?isPage=0"><spring:message code="label.newChat"/></a>
    <c:if test="${!empty UserDatas}">
        <c:forEach items="${UserDatas}" var="userData">
            <c:set var="photo" value="${userData.photo}"/>
            <c:if test="${empty userData.photo}">
                <c:set var="photo" value="empty.gif"/>
            </c:if>            
            <div onclick="window.location = '${pageContext.request.contextPath}/chat/showMessage?addresseeId=${userData.id}'"
                 class="chatsList">
                <div class="avatarSearch">
                    <img src="../../avatar/${photo}" width="100" height="100">
                </div>
                <a href="${pageContext.request.contextPath}/profile/${userData.profileLink}">${userData.surname} ${userData.name}</a>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${empty UserDatas}">
        <p><spring:message code="label.youNoMessage"/></p>
    </c:if>
</div>
