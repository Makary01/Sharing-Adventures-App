<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add adventure title</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>

<a href="/app/home">Back to home page</a>

<form id="addForm" action="/app/add" method="post">
    <h1>Add adventure</h1>
    Title:<input type="text" name="title" value="${adventure.title}" required
                 pattern=".{4,128}" title="4 to 128 letters and numbers"><br>
    Content:<textarea name="content" required rows="12" cols="50">${adventure.content}</textarea><br>
    Start date: <input type="date" name="startDate" required value="${adventure.startDate}"><br>
    End date: <input type="date" name="endDate" required value="${adventure.endDate}"><br>
    Type: <select name="type" required><c:forEach items="${types}" var="type">
        <option>${type}</option>
    </c:forEach></select>
    <input type="submit" value="add adventure" style="cursor: pointer;">
</form>
</body>
</html>
