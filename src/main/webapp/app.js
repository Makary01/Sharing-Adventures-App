document.getElementById("registerButton").addEventListener("click", function (){
    document.querySelector('#registerForm').style.display = 'flex';
    document.querySelector('#loginForm').style.display = 'none';
})

document.getElementById("loginButton").addEventListener("click", function (){
    document.querySelector('#registerForm').style.display = 'none';
    document.querySelector('#loginForm').style.display = 'flex';
})
