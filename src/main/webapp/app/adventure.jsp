<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Adventure's title</title>
</head>
<body>
<c:choose>

    <c:when test="${adventure.id != userId}">
        czyjaś przygoda
    </c:when>


    <c:otherwise>
        twoja przygoda
    </c:otherwise>

</c:choose>


</body>
</html>
