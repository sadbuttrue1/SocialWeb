<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="<c:url value="/resources/js/showMessage.js"/>"></script>
<script type="text/javascript">
  //var block = document.getElementById("messageList");
  //block.scrollTop = block.scrollHeight;
  //$('#messageList').scrollTop(100000);
</script>

<c:set var="principalId" value="${principalUser.id}"/>
<c:set var="addresseeId" value="${addresseeUser.id}"/>

<input type="hidden" name="profileLink${principalId}" value="${principalUser.profileLink}"/>
<input type="hidden" name="photo${principalId}" value="${principalUser.photo}"/>
<input type="hidden" name="name${principalId}" value="${principalUser.name}"/>
<input type="hidden" name="profileLink${addresseeId}" value="${addresseeUser.profileLink}"/>
<input type="hidden" name="photo${addresseeId}" value="${addresseeUser.photo}"/>
<input type="hidden" name="name${addresseeId}" value="${addresseeUser.name}"/>
<input type="hidden" name="lastUpdate" value="${lastUpdate}"/>
<input type="hidden" name="chatId" value="${chatId}">

<div id="chat">
    <div id="messageList">
        <c:choose>
            <c:when test="${!empty messages}">
                <c:forEach items="${messages}" var="message">

                    <c:choose>
                        <c:when test="${principalId eq message.sender}">
                            <c:set var="profileLink" value="${principalUser.profileLink}" />
                            <c:set var="photo" value="${principalUser.photo}" />
                            <c:set var="name" value="${principalUser.name}" />
                        </c:when>
                        <c:otherwise>
                            <c:set var="profileLink" value="${addresseeUser.profileLink}" />
                            <c:set var="photo" value="${addresseeUser.photo}" />
                            <c:set var="name" value="${addresseeUser.name}" />
                        </c:otherwise>
                    </c:choose>

                    <div class="message">                     
                        <div class="avatarSearch">
                            <img src="../../avatar/${photo}" width="80" height="80">
                        </div>
                        <a href="${pageContext.request.contextPath}/profile/${profileLink}">${name}</a>
                        
                        <table width="80%"><tr>
                                <td width="70%">${message.text}</td>
                                <td class="tdMessageDate" align="center">${message.messageDate}</td>
                            </tr></table>
                        
                    </div>
                    <div style="clear: both;"></div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p><spring:message code="label.youNoMessage"/></p>
            </c:otherwise>
        </c:choose>
    </div>    
    <form action="${pageContext.request.contextPath}/chat/sendMessageChat/${chatId}" id="inputMessages" name="inputMessages">
        <textarea id="text" placeholder="<spring:message code="label.areaMessage"/>"></textarea><br/>
        <input type="submit" value="<spring:message code="label.submit" />"/>
    </form>
</div>

