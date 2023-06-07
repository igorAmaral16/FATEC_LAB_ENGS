<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <div class="login-container">
        <h1 class="login-title">Login</h1>
        <form method="post" action="loginServlet">
            <label for="usuario">Usuário:</label>
            <input type="text" id="usuario" name="usuario" class="login-input" required>
            <br><br>
            <label for="senha">Senha:</label>
            <input type="password" id="senha" name="senha" class="login-input" required>
            <br><br>
            <label for="tipoUsuario">Tipo de Usuário:</label>
            <select id="tipoUsuario" name="tipoUsuario" class="login-input login-select" required>
                <option value="ENFERMEIRO">Enfermeiro(a)</option>
                <option value="MEDICO">Médico(a)</option>
                <option value="RH">RH</option>
                <option value="ATENDENTE">Atendente</option>
            </select>
            <br><br>
            <button type="submit" class="login-button">Entrar</button>
        </form>
        <p class="login-error">${erro}</p>
    </div>
</body>
</html>
