<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User's home page title</title>
</head>
<body>
<a href="/app/profile?id=${userId}">Twój profil</a><br>
Najnowsze przygody:
<ul>
<c:forEach items="${adventures}" var="adventure">
    <li><a href="/app/adventure?id=${adventure.id}">${adventure.title}</a></li>
</c:forEach>
</ul>
</body>
</html>
