<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
  
  // Verifica se o radio button "Atualizar" está selecionado
  if (radioAtualizar.checked) {
    campoAtendimento.classList.remove("hidden"); // Remove a classe "hidden" para exibir o campo "atendimento"
    labelAtendimento.classList.remove("hidden"); // Remove a classe "hidden" para exibir o rótulo do campo "atendimento"
  } else {
    campoAtendimento.classList.add("hidden"); // Adiciona a classe "hidden" para ocultar o campo "atendimento"
    labelAtendimento.classList.add("hidden"); // Adiciona a classe "hidden" para ocultar o rótulo do campo "atendimento"
  }
}
</script>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<a class = "back-link" href="menuProfissional.jsp">Voltar para o Menu Profissional de Saúde</a>

<h1>Cadastrar Evolução</h1>

<c:if test="${not empty erroCadastro}">
    <div class="erro">${erroCadastro}</div>
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

    <label for="paciente">CPF do Paciente:</label>
    <input type="text" id="paciente" name="paciente" maxlength="11" required>

    <label for="profissionalSaude">Validador do Profissional de Saúde:</label>
    <input type="text" id="profissionalSaude" name="profissionalSaude" required>

    <label for="atendimento" id="labelAtendimento" class="hidden">Atendimento:</label>
    <input type="text" id="atendimento" name="atendimento" maxlength="6" class="hidden">

    <button type="submit">Enviar</button>
</form>
</body>
</html>
