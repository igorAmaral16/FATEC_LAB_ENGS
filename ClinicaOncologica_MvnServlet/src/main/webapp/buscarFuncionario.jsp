<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Buscar Funcionário</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

    <div class="container">
        <a class = "back-link" href="menuRh.jsp">Voltar para o Menu RH</a>

        <h2>Buscar Funcionário</h2>
        <form action="buscarFuncionarioServlet" method="post">
            <label for="cpf">CPF:</label>
            <input type="text" name="cpf" id="cpf" required>
            <input type="submit" value="Buscar">
        </form>

        <c:if test="${not empty funcionarios}">
            <div class="resultado">
                <h3>Resultado da Busca:</h3>
                <table>
                    <tr>
                        <th>CPF</th>
                        <th>Nome</th>
                        <th>Sobrenome</th>
                        <th>Nascimento</th>
                        <th>Tipo de Usuário</th>
                    </tr>
                    <c:forEach var="funcionario" items="${funcionarios}">
                        <tr>
                            <td>${funcionario.CPF}</td>
                            <td>${funcionario.nome}</td>
                            <td>${funcionario.sobrenome}</td>
                            <td>${funcionario.nascimento}</td>
                            <td>${funcionario.tipoUsuario}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:if>

        <table>
            <c:if test="${not empty erro}">
                <tr>
                    <td class="error-message">${erro}</td>
                </tr>
            </c:if>
        </table>
    </div>

</body>
</html>
