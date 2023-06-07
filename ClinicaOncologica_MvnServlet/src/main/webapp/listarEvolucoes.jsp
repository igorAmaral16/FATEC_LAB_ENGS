<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listar Evoluções</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
 <a class = "back-link" href="menuProfissional.jsp">Voltar para o Menu Profissional</a>

<h2>Listar Evoluções</h2>
<form action="listarEvolucoes" method="post">
    <input type="submit" value="Listar">
</form>

<c:if test="${not empty evolucoes}">
    <div>
        <h3>Resultado da Busca</h3>
        <table>
            <tr>
                <th>Atendimento:</th>
                <th>Data Realizada:</th>
                <th>Tipo:</th>
                <th>Paciente</th>
                <th>Profissional</th>
            </tr>
            <c:forEach var="p" items="${evolucoes}">
                <tr>
                    <td>${p.atendimento}</td>
                    <td>${p.dataRealizada}</td>
                    <td>${p.tipoEvolucao}</td>
                    <td>${p.paciente}</td>
                    <td>${p.profissionalSaude}</td>
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
