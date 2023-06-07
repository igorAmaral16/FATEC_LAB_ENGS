<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Cadastro</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <a class = "back-link" href="menuRh.jsp" class="back-link">Voltar para o Menu RH</a>

    <h1>Cadastrar Atendente</h1>
    
    <c:if test="${not empty erroCadastro}">
        <div class="error-message">${erroCadastro}</div>
    </c:if>

    <!-- Formulário -->
    <form method="post" action="cadastrarAtendenteServlet">
	    <!-- Opções -->
	    <label for="action">Ação:</label>
	    <input type="radio" id="cadastrar" name="action" value="cadastrar">
	    <label for="cadastrar">Cadastrar</label>
	    <input type="radio" id="atualizar" name="action" value="atualizar">
	    <label for="atualizar">Atualizar</label>
	    <br>
	    <br>
        <!-- Campos do formulário -->
        <label class="form-label">CPF:</label>
        <input class="form-input" type="number" name="cpf" placeholder="CPF" required maxlength="11">
        <br>
        <label class="form-label">Nome:</label>
        <input class="form-input" type="text" name="nome" placeholder="Nome" required>
        <br>
        <label class="form-label">Sobrenome:</label>
        <input class="form-input" type="text" name="sobrenome" placeholder="Sobrenome" required>
        <br>
        <label class="form-label">Nascimento:</label>
        <input class="form-input" type="date" name="nascimento" required>
        <br>
        <label class="form-label">Número:</label>
        <input class="form-input" type="number" name="numero" required maxlength="6">
        <br>
        <label class="form-label">Logradouro:</label>
        <input class="form-input" type="text" name="logradouro" placeholder="Logradouro" required>
        <br>
        <label class="form-label">CEP:</label>
        <input class="form-input" type="number" name="cep" placeholder="CEP" required maxlength="8">
        <br>
        <label class="form-label">Estado:</label>
        <select class="form-select" name="estado">
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
        <label class="form-label">Bairro:</label>
        <input class="form-input" type="text" name="bairro" placeholder="Bairro" required>
        <br>
        <label class="form-label">Login:</label>
        <input class="form-input" type="text" name="login" placeholder="Login" required maxlength="20">
        <br>
        <label class="form-label">Senha:</label>
        <input class="form-input" type="password" name="senha" placeholder="Senha" required maxlength="20">
        <br>
        <label class="form-label">Tipo Usuário:</label>
        <select class="form-select" name="tipoUsuario">
            <option value="ATENDENTE">ATENDENTE</option>
        </select>
        <br>
        <label class="form-label">RG:</label>
        <input class="form-input" type="number" name="rg" placeholder="RG" required maxlength="13">
        <br>
        <!-- Botão de envio -->
        <button class="form-button" type="submit">Enviar</button>
    </form>
</body>
</html>

