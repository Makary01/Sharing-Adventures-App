<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page Title</title>
    <style>
        form {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }
        #buttons{
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
        }
    </style>
</head>
<body>
<div id="buttons">
    <button onclick="document.querySelector('#registerForm').style.display = 'flex';
                    document.querySelector('#loginForm').style.display = 'none';">register</button>
    <button onclick="document.querySelector('#loginForm').style.display = 'flex';
                    document.querySelector('#registerForm').style.display = 'none';">log in</button>
</div>

<form style="display: none" id="registerForm" action="/register" method="post">
    <h1>register</h1>
    <input type="text" name="username" placeholder="user name" required
           pattern="[a-zA-Z0-9_-]{5,16}" title="5 to 16 letters, numbers, _ and -">

    <input type="email" name="email" placeholder="email" required>

    <input type="password" name="password" placeholder="password"
           required pattern=".{5,20}" title="5 to 20 chars" >

    <input type="password" name="password2" placeholder="confirm password"
           required pattern=".{5,20}" title="5 to 20 chars" >

    <input type="submit" value="register">
</form>

<form id="loginForm" action="/login" method="post">
    <h1>login</h1>
    <input type="text" name="username" placeholder="user name">
    <input type="password" name="password" placeholder="password" required>
    <input type="submit" value="log in">
</form>

</body>
</html>
