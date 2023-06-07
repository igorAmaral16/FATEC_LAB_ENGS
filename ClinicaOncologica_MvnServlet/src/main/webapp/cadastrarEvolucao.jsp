<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastrar Evolu��o</title>
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
  
  // Verifica se o radio button "Atualizar" est� selecionado
  if (radioAtualizar.checked) {
    campoAtendimento.classList.remove("hidden"); // Remove a classe "hidden" para exibir o campo "atendimento"
    labelAtendimento.classList.remove("hidden"); // Remove a classe "hidden" para exibir o r�tulo do campo "atendimento"
  } else {
    campoAtendimento.classList.add("hidden"); // Adiciona a classe "hidden" para ocultar o campo "atendimento"
    labelAtendimento.classList.add("hidden"); // Adiciona a classe "hidden" para ocultar o r�tulo do campo "atendimento"
  }
}
</script>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<a class = "back-link" href="menuProfissional.jsp">Voltar para o Menu Profissional de Sa�de</a>

<h1>Cadastrar Evolu��o</h1>

<c:if test="${not empty erroCadastro}">
    <div class="erro">${erroCadastro}</div>
</c:if>

<form method="post" action="cadastrarEvolucaoServlet">
	<!-- Op��es -->
    <label for="action">A��o:</label>
    <input type="radio" id="cadastrar" name="action" value="cadastrar" required onclick="mostrarAtendimento()">
    <label for="cadastrar">Cadastrar</label>
    <input type="radio" id="atualizar" name="action" value="atualizar" required onclick="mostrarAtendimento()">
    <label for="atualizar">Atualizar</label>
    <br>
    <br>
    <label for="tipoEvolucao">Tipo de Evolu��o:</label>
	<select id="tipoEvolucao" name="tipoEvolucao" required>
	    <option value="ENFERMAGEM">Enfermagem</option>
	    <option value="MEDICA">M�dica</option>
	    <option value="INTERCORRENCIA">Intercorr�ncia</option>
	</select>

    <label for="paciente">CPF do Paciente:</label>
    <input type="text" id="paciente" name="paciente" maxlength="11" required>

    <label for="profissionalSaude">Validador do Profissional de Sa�de:</label>
    <input type="text" id="profissionalSaude" name="profissionalSaude" required>

    <label for="atendimento" id="labelAtendimento" class="hidden">Atendimento:</label>
    <input type="text" id="atendimento" name="atendimento" maxlength="6" class="hidden">

    <button type="submit">Enviar</button>
</form>
</body>
</html>
