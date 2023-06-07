<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Excluir Funcionário</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<a class = "back-link" href="menuRh.jsp">Voltar para o Menu RH</a>
    <h2>Excluir Funcionário</h2>
    
    <form action="excluirFuncionarioServlet" method="post">
        <label for="cpf">CPF:</label>
        <input type="text" name="cpf" id="cpf" required>
        <input type="submit" value="Excluir">
    </form>
    
    <h3>Resultado da Exclusão:</h3>
    <p>${mensagem}</p>
</body>
</html>
