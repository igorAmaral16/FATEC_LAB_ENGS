<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<a class = "back-link" href="menuAtendente.jsp">Voltar para o Menu Atendente</a>

    <h1>Cadastrar Paciente</h1>
    
    <c:if test="${not empty erroCadastro}">
        <div class="error-message">${erroCadastro}</div>
    </c:if>

    <!-- Formulário -->
	<form method="post" action=cadastrarPacienteServlet>
    <!-- Opções -->
    <label for="action">Ação:</label>
    <input type="radio" id="cadastrar" name="action" value="cadastrar" required>
    <label for="cadastrar">Cadastrar</label>
    <input type="radio" id="atualizar" name="action" value="atualizar" required>
    <label for="atualizar">Atualizar</label>
    <br>
    <br>
    <label for="cpf">CPF:</label>
    <input type="text" id="cpf" name="cpf" maxlength="11" required>
    <br>
    <label for="nome">Nome:</label>
    <input type="text" id="nome" name="nome" maxlength="150" required>
    <br>
    <label for="sobrenome">Sobrenome:</label>
    <input type="text" id="sobrenome" name="sobrenome" maxlength="150" required>
    <br>
    <label for="nascimento">Data de Nascimento:</label>
    <input type="date" id="nascimento" name="nascimento" required>
    <br>
    <label for="numero">Número:</label>
    <input type="number" id="numero" name="numero" required>
    <br>
    <label for="logradouro">Logradouro:</label>
    <input type="text" id="logradouro" name="logradouro" maxlength="100" required>
    <br>
    <label for="cep">CEP:</label>
    <input type="text" id="cep" name="cep" maxlength="8" required>
    <br>
    <label for="estado">Estado:</label>
    <select id="estado" name="estado" required>
        <option value="AC">Acre</option>
        <option value="AL">Alagoas</option>
        <option value="AP">Amapá</option>
        <option value="AM">Amazonas</option>
        <option value="BA">Bahia</option>
        <option value="CE">Ceará</option>
        <option value="DF">Distrito Federal</option>
        <option value="ES">Espírito Santo</option>
        <option value="GO">Goiás</option>
        <option value="MA">Maranhão</option>
        <option value="MT">Mato Grosso</option>
        <option value="MS">Mato Grosso do Sul</option>
        <option value="MG">Minas Gerais</option>
        <option value="PA">Pará</option>
        <option value="PB">Paraíba</option>
        <option value="PR">Paraná</option>
        <option value="PE">Pernambuco</option>
        <option value="PI">Piauí</option>
        <option value="RJ">Rio de Janeiro</option>
        <option value="RN">Rio Grande do Norte</option>
        <option value="RS">Rio Grande do Sul</option>
        <option value="RO">Rondônia</option>
        <option value="RR">Roraima</option>
        <option value="SC">Santa Catarina</option>
        <option value="SP">São Paulo</option>
        <option value="SE">Sergipe</option>
        <option value="TO">Tocantins</option>
    </select>
    <br>
    <label for="bairro">Bairro:</label>
    <input type="text" id="bairro" name="bairro" maxlength="100" required>
    <br>
    <label for="telefone">Telefone:</label>
    <input type="tel" id="telefone" name="telefone" pattern="[0-9]{11}" required>
    <br>
    <label for="sexo">Sexo:</label>
    <select id="sexo" name="sexo" required>
        <option value="M">Masculino</option>
        <option value="F">Feminino</option>
    </select>
    <br>
    <label for="tipoProcedimento">Tipo de Procedimento:</label>
    <select id="tipoProcedimento" name="tipoProcedimento" required>
        <option value="CONSULTA">Consulta</option>
        <option value="QUIMIOTERAPIA">Quimioterapia</option>
    </select>
    <br>
    <label for="nomeAcompanhante">Nome do Acompanhante:</label>
    <input type="text" id="nomeAcompanhante" name="nomeAcompanhante" maxlength="150" required>
    <br>
    <label for="sobrenomeAcompanhante">Sobrenome do Acompanhante:</label>
    <input type="text" id="sobrenomeAcompanhante" name="sobrenomeAcompanhante" maxlength="150" required>
    <br>
    <label for="telefoneAcompanhante">Telefone do Acompanhante:</label>
    <input type="tel" id="telefoneAcompanhante" name="telefoneAcompanhante" pattern="[0-9]{11}" required>
	<br>
 	<!-- Botão de envio -->
    <button type="submit">Enviar</button>

</body>
</html>