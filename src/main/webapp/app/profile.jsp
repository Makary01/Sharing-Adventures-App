<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User's profile</title>
</head>
<body>
<c:choose>
    <c:when test="${user.id != userId}">
        czyjś profil
    </c:when>
    <c:otherwise>
        twój profil
    </c:otherwise>
</c:choose>

</body>
</html>
