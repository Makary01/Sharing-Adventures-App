<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page Title</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>
<div id="buttons">
    <button id="registerButton">register</button>
    <button id="loginButton">log in</button>
</div>

<form id="registerForm" action="/register" method="post">
    <h1>register</h1>
    <input type="text" name="username" placeholder="user name" required
           pattern="[a-zA-Z0-9_-]{5,16}" title="5 to 16 letters, numbers, _ and -">

    <input type="email" name="email" placeholder="email" required>

    <input type="text" name="city" placeholder="city" required
           pattern="[a-zA-Z]{2,16}" title="2 to 16 letters">

    <input type="text" name="country" placeholder="country" required
           pattern="[a-zA-Z]{2,16}" title="2 to 16 letters">

    <input type="password" name="password" placeholder="password"
           required pattern=".{5,20}" title="5 to 20 chars" >

    <input type="password" name="password2" placeholder="confirm password"
           required pattern=".{5,20}" title="5 to 20 chars" >

    <input type="submit" value="register" style="cursor: pointer;">
</form>

<form id="loginForm" action="/login" method="post">
    <h1>login</h1>
    <input type="text" name="username" placeholder="user name" required>
    <input type="password" name="password" placeholder="password" required>
    <input type="submit" value="log in" style="cursor: pointer;">
</form>

</body>
<script src="app.js"></script>
</html>
