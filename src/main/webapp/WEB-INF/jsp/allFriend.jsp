<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<spring:url value="/profile/photo" var="userPhotoUrl"/>

<div id="searchResult">
    <c:choose>
        <c:when test="${!empty userFriendsConfirm || !empty userFriends}">
            <c:if test="${!empty userFriendsConfirm}">
                <c:forEach items="${userFriendsConfirm}" var="friend">
                    <c:set var="photo" value="${friend.photo}"/>
                    <c:if test="${empty friend.photo}">
                        <c:set var="photo" value="empty.gif"/>
                    </c:if>            
                    <div class="divSearchUserData">
                        <div class="avatarSearch">
                            <img src="../../avatar/${photo}" width="100" height="100">
                        </div>
                        <a href="${pageContext.request.contextPath}/profile/${friend.profileLink}">${friend.surname} ${friend.name}</a><br>
                        <%--<c:if test="${friend.status eq 3}">--%>
                            <a href="${pageContext.request.contextPath}/friend/confirm/${friend.getId()}"><spring:message code="label.yesConfirm"/></a>
                        <%--</c:if>--%>
                        <a href="${pageContext.request.contextPath}/friend/del/${friend.userFriend.getId()}"><spring:message code="label.del"/></a>
                    </div>
                </c:forEach>
            </c:if>

            <c:if test="${!empty userFriends}">
                <c:forEach items="${userFriends}" var="friend">
                    <c:set var="photo" value="${friend.photo}"/>
                    <c:if test="${empty friend.photo}">
                        <c:set var="photo" value="empty.gif"/>
                    </c:if>            
                    <div class="divSearchUserData">
                        <div class="avatarSearch">
                            <img src="../../avatar/${photo}" width="100" height="100">
                        </div>
                        <a href="${pageContext.request.contextPath}/profile/${friend.profileLink}">${friend.surname} ${friend.name}</a><br>
                        <c:if test="${myFriends}">
                            <a href="${pageContext.request.contextPath}/friend/del/${friend.getId()}"><spring:message code="label.del"/></a>
                        </c:if>
                    </div>
                </c:forEach>
            </c:if>

        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test ="${myFriends}">
                    <spring:message code="label.youNoFriends"/>            
                    <a href="${pageContext.request.contextPath}/search"><spring:message code="label.search"/></a>
                </c:when>
                <c:otherwise>
                    <spring:message code="label.noFriends"/>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
</div>