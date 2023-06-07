<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Excluir Paciente</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<a class = "back-link" href="menuAtendente.jsp">Voltar para o Menu Atendente</a>
    <h2>Excluir Paciente</h2>
    
    <form action="excluirPacienteServlet" method="post">
        <label for="cpf">CPF:</label>
        <input type="text" name="cpf" id="cpf" required>
        <input type="submit" value="Excluir">
    </form>
    
    <h3>Resultado da Exclusão:</h3>
    <p class = "error-message">${mensagem}</p>

</body>
</html>