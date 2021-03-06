<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Adventure's title</title>
    <link href="style.css" rel="stylesheet">

</head>
<body>

<a href="/app/home">Back to home page</a><br>
<div id="container">
<c:choose>
    <c:when test="${adventure.userId != userId}">
        <div id="adventure">
        <a href="/app/profile?id=${adventure.userId}">Author's profile</a><br>
        Start date:${adventure.startDate}<br>
        End date:${adventure.endDate}<br>
        Type:${adventure.type}<br>
        <h1>${adventure.title}</h1><br>
        <h4>${adventure.content}</h4><br>
        </div>
    </c:when>

    <c:otherwise>
        <form id="editForm" action="/app/adventure" method="post">
            <h1>edit adventure</h1><br>
            Title:<input type="text" name="title" value="${adventure.title}" required
                   pattern=".{4,128}" title="4 to 128 letters and numbers"><br>
            Content:<textarea name="content" required rows="12" cols="50">${adventure.content}</textarea><br>
            Start date: <input type="date" name="startDate" required value="${adventure.startDate}"><br>
            End date: <input type="date" name="endDate" required value="${adventure.endDate}"><br>
            Type: <select name="type" required >
            <option>${adventure.type}</option>
            <c:forEach items="${types}" var="type">
            <option>${type}</option>
            </c:forEach></select><br>
            <input type="submit" value="save changes" style="cursor: pointer;">
            <input type="submit" name="delete" value="delete adventure" style="cursor: pointer;">
        </form>
    </c:otherwise>
</c:choose>
</div>
</body>
</html>
