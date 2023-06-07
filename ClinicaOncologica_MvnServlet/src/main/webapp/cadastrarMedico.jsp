<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cadastro</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<a class = "back-link" href="menuRh.jsp">Voltar para o Menu RH</a>

    <h1>Cadastrar M�dico(a)</h1>
    
    <c:if test="${not empty erroCadastro}">
        <div class="erro">${erroCadastro}</div>
    </c:if>

    <!-- Formul�rio -->
	<form method="post" action=cadastrarMedicoServlet>
    <!-- Op��es -->
    <label for="action">A��o:</label>
    <input type="radio" id="cadastrar" name="action" value="cadastrar" required>
    <label for="cadastrar">Cadastrar</label>
    <input type="radio" id="atualizar" name="action" value="atualizar" required>
    <label for="atualizar">Atualizar</label>
    <br>
    <br>
    <!-- Campos do formul�rio -->
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
    <label>N�mero:</label>
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
		    <option value="AP">Amap�</option>
		    <option value="AM">Amazonas</option>
		    <option value="BA">Bahia</option>
		    <option value="CE">Cear�</option>
		    <option value="DF">Distrito Federal</option>
		    <option value="ES">Esp�rito Santo</option>
		    <option value="GO">Goi�s</option>
		    <option value="MA">Maranh�o</option>
		    <option value="MT">Mato Grosso</option>
		    <option value="MS">Mato Grosso do Sul</option>
		    <option value="MG">Minas Gerais</option>
		    <option value="PA">Par�</option>
		    <option value="PB">Para�ba</option>
		    <option value="PR">Paran�</option>
		    <option value="PE">Pernambuco</option>
		    <option value="PI">Piau�</option>
		    <option value="RJ">Rio de Janeiro</option>
		    <option value="RN">Rio Grande do Norte</option>
		    <option value="RS">Rio Grande do Sul</option>
		    <option value="RO">Rond�nia</option>
		    <option value="RR">Roraima</option>
		    <option value="SC">Santa Catarina</option>
		    <option value="SP">S�o Paulo</option>
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
    <label>Tipo Usu�rio: 
        <select name="tipoUsuario">
		    <option value="MEDICO">M�DICO(A)</option>
		</select>
		</label>
		<br>
    <label>RG:</label>
    <input type="number" name="rg" placeholder="RG" required>
    <br>
    <label>CRM:</label>
    <input type="number" name="crm" placeholder="CRM" required>
    <br>
    <!-- Bot�o de envio -->
    <button type="submit">Enviar</button>
</form>
</body>
</html>
