<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cadastro</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<a class = "back-link" href="menuRh.jsp">Voltar para o Menu RH</a>

    <h1>Cadastrar Médico(a)</h1>
    
    <c:if test="${not empty erroCadastro}">
        <div class="erro">${erroCadastro}</div>
    </c:if>

    <!-- Formulário -->
	<form method="post" action=cadastrarMedicoServlet>
    <!-- Opções -->
    <label for="action">Ação:</label>
    <input type="radio" id="cadastrar" name="action" value="cadastrar" required>
    <label for="cadastrar">Cadastrar</label>
    <input type="radio" id="atualizar" name="action" value="atualizar" required>
    <label for="atualizar">Atualizar</label>
    <br>
    <br>
    <!-- Campos do formulário -->
    <label>CPF:</label>
    <input type="number" name="cpf" placeholder="CPF" required>
    <br>
    <label>Nome:</label>
    <input type="text" name="nome" placeholder="Nome" required>
    <br>
    <label>Sobrenome:</label>
    <input type="text" name="sobrenome" placeholder="Sobrenome" required>
    <br>
    <label>Nascimento:</label>
    <input type="date" name="nascimento" required>
    <br>
    <label>Número:</label>
    <input type="number" name="numero" required>
    <br>
    <label>Logradouro:</label>
    <input type="text" name="logradouro" placeholder="Logradouro" required>
    <br>
    <label>CEP:</label>
    <input type="number" name="cep" placeholder="CEP" required>
    <br>
    <label>Estado: 
        <select name="estado">
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
		</label>
		<br>
    <label>Bairro:</label>
    <input type="text" name="bairro" placeholder="Bairro" required>
    <br>
    <label>Login:</label>
    <input type="text" name="login" placeholder="Login" required>
    <br>
    <label>Senha:</label>
    <input type="password" name="senha" placeholder="Senha" required>
    <br>
    <label>Tipo Usuário: 
        <select name="tipoUsuario">
		    <option value="MEDICO">MÉDICO(A)</option>
		</select>
		</label>
		<br>
    <label>RG:</label>
    <input type="number" name="rg" placeholder="RG" required>
    <br>
    <label>CRM:</label>
    <input type="number" name="crm" placeholder="CRM" required>
    <br>
    <!-- Botão de envio -->
    <button type="submit">Enviar</button>
</form>
</body>
</html>
