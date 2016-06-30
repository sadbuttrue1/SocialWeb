<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>SW</title>
     <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/theme/style.css"/>"/>    
    <!--[if lt IE 9]> 
     <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> 
    <![endif]-->    
    <script src="http://api-maps.yandex.ru/2.0-stable/?load=package.standard&lang=ru-RU" type="text/javascript"></script>    
    <script src="<c:url value="/resources/js/jquery.js"/>"></script>
</head>
<body>
    
        <header>
            <tiles:insertAttribute name="header" />            
        </header>
        
        <div id="body">
            <tiles:insertAttribute name="bodyNav"/>            
            
                <tiles:insertAttribute name="body" />
            
        </div>
            
        <footer>
            <tiles:insertAttribute name="footer" />
        </footer>
        
</body>
</html>