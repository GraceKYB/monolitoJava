<%-- 
    Document   : Login
    Created on : 09/05/2024, 17:46:43
    Author     : domes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-image: url('nature.jpg');
            background-size: cover;
            background-position: center;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        
        .login-container {
            background-color: rgba(255, 255, 255, 0.9);
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
            max-width: 400px;
            width: 100%;
            text-align: center;
        }
        
        .login-container h2 {
            margin-bottom: 20px;
        }
        
        .login-container input {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 16px;
        }
        
        .login-container input[type="submit"] {
            background-color: #009999;
            color: #fff;
            cursor: pointer;
        }
        
        .login-container input[type="submit"]:hover {
            background-color: #0056b3;
        }
        
        .login-container a {
            color: #009999;
            text-decoration: none;
        }
        
        .login-container a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Iniciar Sesión </h2>
        <form action="LoginServlet" method="post">
            <input type="text" name="cedula" placeholder="Cedula" required autocomplete="off"><br>
            <input type="password" name="contraseña" placeholder="Contraseña" required autocomplete="off"><br>
            <input type="submit" value="Login">
        </form>
        <br>
        <a href="Registro.jsp">Regístrate</a>
    </div>
</body>
</html>

