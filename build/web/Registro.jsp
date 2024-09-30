<%-- 
    Document   : Registro
    Created on : 09/05/2024, 17:47:18
    Author     : domes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de Usuario</title>
    <style>
       body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-image: url('nature.jpg'); /* Ruta de la imagen de fondo */
            background-size: cover;
            background-position: center;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        
        .register-container {
            background-color: rgba(255, 255, 255, 0.9);
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
            max-width: 400px;
            width: 100%;
            text-align: center;
        }
        
        .register-container h2 {
            margin-bottom: 20px;
        }
        
        .register-container input,
        .register-container select {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 16px;
        }
        
        .register-container input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }
        
        .register-container input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="register-container">
        <h2>Registro de Usuario</h2>
        <form id="registroForm" action="RegistroServlet" method="post" onsubmit="return validarFormulario()">
            <input type="text" name="nombre" id="nombre" placeholder="Nombre" required><br>
            <input type="text" name="cedula" id="cedula" placeholder="Cédula" required><br>
            <input type="text" name="direccion" id="direccion" placeholder="Dirección" required><br>
            <input type="email" name="correo" id="correo" placeholder="Correo electrónico" required><br>
            <input type="password" name="contraseña" id="contraseña" placeholder="Contraseña" required><br>
            <select name="tipo_usuario" id="tipo_usuario">
                <option value="1">Selecciona</option>
                <option value="1">Administrador</option>
                <option value="2">Usuario</option>
            </select><br>
            <input type="submit" value="Registrar">
        </form>
    </div>

    <script>
        function validarFormulario() {
            var nombre = document.getElementById('nombre').value;
            var cedula = document.getElementById('cedula').value;
            var direccion = document.getElementById('direccion').value;
            var correo = document.getElementById('correo').value;

            // Expresiones regulares para validar los campos
            var letras = /^[a-zA-Z\s]+$/;
            var numeros = /^\d{10}$/;
            var correoFormato = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

            // Validación del nombre
            if (!nombre.match(letras)) {
                alert("Por favor, ingresa solo letras en el campo Nombre.");
                return false;
            }

            // Validación de la cédula
            if (!cedula.match(numeros)) {
                alert("Por favor, ingresa una cédula válida de 10 dígitos.");
                return false;
            }

            // Validación de la dirección
            if (!direccion.match(letras)) {
                alert("Por favor, ingresa solo letras en el campo Dirección.");
                return false;
            }

            // Validación del correo electrónico
            if (!correo.match(correoFormato)) {
                alert("Por favor, ingresa un correo electrónico válido.");
                return false;
            }

            // Si todos los campos son válidos, el formulario se enviará
            return true;
        }
    </script>
</body>
</html>


