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
<form id="addForm" action="/app/add" method="post">
    <h1>Add adventure</h1>
    <input type="text" name="title" placeholder="title" required
           pattern="[a-zA-Z0-9]{4,128}" title="4 to 128 letters and numbers">
    <input type="text" name="content" placeholder="content" required>
    Start date <input type="date" name="startDate" required>
    End date <input type="date" name="endDate" required>
    Type <select name="type" required><c:forEach items="${types}" var="type">
        <option>${type}</option>
    </c:forEach></select>
    <input type="submit" value="add adventure">
</form>
</body>
</html>
