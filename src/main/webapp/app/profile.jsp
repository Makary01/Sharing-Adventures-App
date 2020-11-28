<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User's profile</title>
    <style>
        form {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }
    </style>
</head>
<body>

<a href="/app/home">Back to home page</a>

<c:choose>
    <c:when test="${user.id != userId}">
        User nr${user.id}<br>
        Username: ${user.username}<br>
        From: ${user.city},${user.country}<br>
        Email: ${user.email}<br><br>

        User's adventures:
        <ul>
        <c:forEach items="${adventures}" var="adventure">
            <li><a href="/app/adventure?id=${adventure.id}">${adventure.title}</a>|${adventure.type}</li>
        </c:forEach>
        </ul>


    </c:when>


    <c:otherwise>

        <form method="post" action="/app/profile">
            <h1>Your profile</h1><br>
            Username: <input type="text" name="username" value="${user.username}" required><br>
            Email: <input type="email" name="email" value="${user.email}" required><br>
            Country: <input type="text" name="country" value="${user.country}" required><br>
            City: <input type="text" name="city" value="${user.city}" required><br>
            Old password: <input type="password" name="oldPassword" required placeholder="old password" required><br>
            Password: <input type="password" name="password" placeholder="new password" required><br>
            Confirm password: <input type="password" name="password2" placeholder="confirm password" required><br>
            <input type="submit" value="Save changes"><br><br>
        </form>
        Your adventures:
        <ul>
            <c:forEach items="${adventures}" var="adventure">
                <li><a href="/app/adventure?id=${adventure.id}">${adventure.title}</a></li>
            </c:forEach>
        </ul>
    </c:otherwise>

</c:choose>
</body>
</html>
