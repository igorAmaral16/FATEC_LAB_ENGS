<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Excluir Evolução</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<a class = "back-link" href="menuProfissional.jsp">Voltar para o Menu Profissional de Saúde</a>
    <h2>Excluir Evolução</h2>
    
    <form action="excluirEvolucaoServlet" method="post">
        <label for="atendimento">Atendimento:</label>
        <input type="text" name="atendimento" id="atendimento" required>
        <input type="submit" value="Excluir">
    </form>
    
    <h3>Resultado da Exclusão:</h3>
    <p class = "error-message">${mensagem}</p>

</body>
</html>