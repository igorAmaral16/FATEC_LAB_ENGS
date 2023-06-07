<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Excluir Evolu��o</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<a class = "back-link" href="menuProfissional.jsp">Voltar para o Menu Profissional de Sa�de</a>
    <h2>Excluir Evolu��o</h2>
    
    <form action="excluirEvolucaoServlet" method="post">
        <label for="atendimento">Atendimento:</label>
        <input type="text" name="atendimento" id="atendimento" required>
        <input type="submit" value="Excluir">
    </form>
    
    <h3>Resultado da Exclus�o:</h3>
    <p class = "error-message">${mensagem}</p>

</body>
</html>