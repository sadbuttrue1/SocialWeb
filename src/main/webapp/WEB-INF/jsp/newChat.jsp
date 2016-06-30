<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="<c:url value="/resources/js/newChat.js"/>"></script>

<div id="newChat">
    <form:form id="newChatForm" action="${pageContext.request.contextPath}/chat/createChat?isPage=${isPage}"
               modelAttribute="newChatForm">
        <c:choose>
            <c:when test="${isPage != 0}">
                <input type="hidden" name="addresseeUserId" id="addresseeUserId" value="${addresseeUser.id}"/>
                <span class="error"><form:errors path="addresseeUserId"/></span>
                <p><spring:message code="label.youWrite"/> ${addresseeUser.surname} ${addresseeUser.name}</p>
            </c:when>
            <c:otherwise>
                <select name="addresseeUserId" id="addresseeUserId">
                    <option value="0" selected><spring:message code="label.selectFriend"/></option>
                    <c:if test="${!empty friends}">
                        <c:forEach items="${friends}" var="friend">
                            <option value="${friend.id}">${friend.surname} ${friend.name}</option>
                        </c:forEach>
                    </c:if>
                </select><span class="error"><form:errors path="addresseeUserId"/></span>
            </c:otherwise>                
        </c:choose>
                <div id='inputMessages'>
        <textarea name="message" id="text"
                  placeholder="<spring:message code="label.areaMessage"/>"></textarea>
                  <span class="error"><form:errors path="message"/></span>
                </div>
        <input type="submit" value="<spring:message code="label.submit" />" />
    </form:form>
</div>
