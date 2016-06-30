<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="<c:url value="/resources/js/location.js"/>"></script>
<script src="<c:url value="/resources/js/profile.js"/>"></script>
<c:set var="photo" value="${user.photo}"/>
<c:if test="${empty user.photo}">
    <c:set var="photo" value="empty.gif"/>
</c:if>
<spring:url value="/friend/add" var="addFriendUrl"/>
<spring:url value="/friend/all/${user.id}" var="allFriendUrl"/>
<spring:url value="/chat/sendMessagePage?addresseeId=${user.id}" var="sendMessage"/>

<div id ="avatar">
    <img src="../../avatar/${photo}" width="200" height="250"/>
    <div id="statusAddFriend">
        <c:if test="${statusFriends eq RatioFriends.IS_FRIEND}">
            <p>${user.name} <spring:message code="label.isFriend"/></p>
        </c:if>
        <c:if test="${statusFriends eq RatioFriends.WAITING}">
            <p><spring:message code="label.waitFriend"/></p>
        </c:if>
        <c:if test="${statusFriends eq RatioFriends.NOTFRIENDS}">
            <a href="${addFriendUrl}/${user.id}" id="addFriend"><spring:message code="label.addFriend"/></a>
        </c:if>
        <c:if test="${statusFriends != RatioFriends.HIMSELF}">
            <br/><a href="${sendMessage}"><spring:message code="label.newChat" /> ${user.name}</a><br/>
            <a href="${allFriendUrl}"><spring:message code="label.Friends" /> ${user.name}</a>
            </c:if>
    </div>

</div>

<div id="information">
    <h1>${user.name} ${user.surname}</h1>
    <table>
        <tr>
            <td><spring:message code="label.hometown" /></td>
            <td>${user.hometown}</td>
        </tr>
        <tr>
            <td><spring:message code="label.birthday" /></td>
            <td>${user.displayDate()}</td>
        </tr>   
        <tr>
            <td><spring:message code="label.hobby" /></td>
            <td>${user.hobby}</td>
        </tr>
        <tr>
            <td><spring:message code="label.telephone" /></td>
            <td>${user.phone}</td>
        </tr>
        <tr>
            <td><spring:message code="label.email" /></td>
            <td><c:if test="${!empty user.emails}">
                    <c:forEach items="${user.emails}" var="email">
                        <a href="malito:${email.email}">${email.email}</a><br>
                    </c:forEach>    
                </c:if></td>
        </tr>
    </table>    


    <details>
        <summary><spring:message code="label.location" /></summary>
        <div id="map"></div>
    </details>
</div>