<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add adventure title</title>
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

<form id="addForm" action="/app/add" method="post">
    <h1>Add adventure</h1>
    <input type="text" name="title" placeholder="title" required
           pattern=".{4,128}" title="4 to 128 letters and numbers">
    <textarea name="content" placeholder="content" required></textarea>
    Start date <input type="date" name="startDate" required>
    End date <input type="date" name="endDate" required>
    Type <select name="type" required><c:forEach items="${types}" var="type">
        <option>${type}</option>
    </c:forEach></select>
    <input type="submit" value="add adventure">
</form>
</body>
</html>
