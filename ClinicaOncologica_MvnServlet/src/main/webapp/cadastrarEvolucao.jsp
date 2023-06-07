<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastrar Evolução</title>
<style>
  .hidden {
    display: none;
  }
</style>
<script>
    function mostrarAtendimento() {
      var radioAtualizar = document.getElementById("atualizar");
      var campoAtendimento = document.getElementById("atendimento");
      var labelAtendimento = document.getElementById("labelAtendimento");
      
      // Verifica se o radio button Atualizar está selecionado
      if (radioAtualizar.checked) {
        campoAtendimento.classList.remove("hidden");
        labelAtendimento.classList.remove("hidden"); 
      } else {
        campoAtendimento.classList.add("hidden"); 
        labelAtendimento.classList.add("hidden"); 
      }
    }
    function carregarPacientes() {
        window.location.href = "cadastrarEvolucaoServlet?action=loadPacientes";
    }
</script>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<a class = "back-link" href="menuProfissional.jsp">Voltar para o Menu Profissional de Saúde</a>

<h1>Cadastrar Evolução</h1>

<c:if test="${not empty erroCadastro}">
    <div class="error-message">${erroCadastro}</div>
</c:if>

<form method="post" action="cadastrarEvolucaoServlet">
    <!-- Opções -->
    <label for="action">Ação:</label>
    <input type="radio" id="cadastrar" name="action" value="cadastrar" required onclick="mostrarAtendimento()">
    <label for="cadastrar">Cadastrar</label>
    <input type="radio" id="atualizar" name="action" value="atualizar" required onclick="mostrarAtendimento()">
    <label for="atualizar">Atualizar</label>
    <br>
    <br>
    <label for="tipoEvolucao">Tipo de Evolução:</label>
    <select id="tipoEvolucao" name="tipoEvolucao" required>
        <option value="ENFERMAGEM">Enfermagem</option>
        <option value="MEDICA">Médica</option>
        <option value="INTERCORRENCIA">Intercorrência</option>
    </select>
    <br>
    <!-- Combobox de pacientes -->
        <label for="paciente">Paciente:</label>
        <select name="paciente" id="paciente">
            <c:forEach var="paciente" items="${pacientes}">
                <option value="${paciente.CPF}">${paciente.nome}</option>
            </c:forEach>
        </select>
        <!-- Botão para acionar o método doGet -->
        <button type="button" onclick="carregarPacientes()">Carregar Pacientes</button>
        <br>
    <label for="profissionalSaude">Validador do Profissional de Saúde:</label>
    <input type="text" id="profissionalSaude" name="profissionalSaude" required>
    <br>
    <label for="atendimento" id="labelAtendimento" class="hidden">Atendimento:</label>
    <input type="text" id="atendimento" name="atendimento" maxlength="6" class="hidden">
    <br>
    <button type="submit">Enviar</button>
</form>
</body>
</html>
