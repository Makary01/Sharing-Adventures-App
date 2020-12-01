<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User's home page title</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>



<div id="header">
<a href="/app/logout">Log out</a>
<a href="/app/profile?id=${userId}">Your profile</a>
<a href="/app/add">Add adventure</a>
</div>

<div id="container">

<h1 id="errorMsg" style="color: ${color}">${msg}</h1><br>
Latest adventures:
<ul>
<c:forEach items="${adventures}" var="adventure">
    <li><a href="/app/adventure?id=${adventure.id}">${adventure.title}</a>|${adventure.type}</li>
</c:forEach><br>
    <a href="/app/home?page=${page-1}">prev page</a>|<a href="/app/home?page=${page+1}">next page</a>
</ul>

</div>
</body>
</html>
