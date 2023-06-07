<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Buscar Paciente</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div class="container">
	<a class = "back-link" href="menuAtendente.jsp">Voltar para o Menu Atendente</a>
	
     <h2>Buscar Paciente</h2>
    <form action="buscarPacienteServlet" method="post">
        <label for="cpf">CPF:</label>
        <input type="text" name="cpf" id="cpf" required>
        <input type="submit" value="Buscar">
    </form>
    
    <c:if test="${not empty paciente}">
        <div class="resultado">
            <h3>Resultado da Busca:</h3>
            <table>
                <tr>
                    <th>CPF</th>
                    <th>Nome</th>
                    <th>Sobrenome</th>
                    <th>Nascimento</th>
                    <th>telefone</th>
                    <th>sexo</th>
                    <th>tipo do Procedimento</th>
                    
                    
                </tr>
                <c:forEach var="paciente" items="${paciente}">
                    <tr>
                        <td>${paciente.CPF}</td>
                        <td>${paciente.nome}</td>
                        <td>${paciente.sobrenome}</td>
                        <td>${paciente.nascimento}</td>
                        <td>${paciente.telefone}</td>
                        <td>${paciente.sexo}</td>
                        <td>${paciente.tipoProcedimento}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
    
     <table>
           <c:if test="${not empty erro}">
           		<td class="error-message">${erro}</td>
           </c:if>
      </table>
      </div>

</body>
</html>