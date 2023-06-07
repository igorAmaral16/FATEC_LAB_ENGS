<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listar Pacientes</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
 <a class = "back-link" href="menuAtendente.jsp">Voltar para o Menu Atendente</a>

<h2>Listar Pacientes</h2>
<form action="listarPacientes" method="post">
    <input type="submit" value="Listar">
</form>

<c:if test="${not empty pacientes}">
    <div>
        <h3>Resultado da Busca:</h3>
        <table>
            <tr>
                <th>CPF</th>
                <th>Nome</th>
                <th>Sobrenome</th>
                <th>Nascimento</th>
                <th>Telefone</th>
                <th>Sexo</th>
                <th>Tipo do Procedimento</th>
            </tr>
            <c:forEach var="p" items="${pacientes}">
                <tr>
                    <td>${p.CPF}</td>
                    <td>${p.nome}</td>
                    <td>${p.sobrenome}</td>
                    <td>${p.nascimento}</td>
                    <td>${p.telefone}</td>
                    <td>${p.sexo}</td>
                    <td>${p.tipoProcedimento}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>

<c:if test="${not empty erro}">
    <table>
        <tr>
            <td class = "error-message">${erro}</td>
        </tr>
    </table>
</c:if>

</body>
</html>
