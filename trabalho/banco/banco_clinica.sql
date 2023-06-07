CREATE DATABASE trabEng
GO
USE trabEng
GO
CREATE TABLE pessoa(
CPF				CHAR(11)		NOT NULL,
nome			VARCHAR(150)	NOT NULL,
sobrenome		VARCHAR(150)	NOT NULL,
nascimento		DATE			NOT NULL,
numero			INT				NOT NULL,
logradouro		VARCHAR(100)	NOT NULL,
CEP				CHAR(8)			NOT NULL,
estado			CHAR(2)			NOT NULL,
bairro			VARCHAR(100)	NOT NULL,
PRIMARY KEY(CPF)
)
GO
CREATE TABLE paciente(
cpf_pessoa			CHAR(11)		NOT NULL,
telefone			CHAR(11)		NOT NULL,
sexo				CHAR(2)			NOT NULL,
tipoProcedimento	VARCHAR(50)		NOT NULL,
nomeAcompanhante	VARCHAR(150)	NOT NULL,
sobrenomeAcompanhante VARCHAR(150)	NOT NULL,
telefoneAcompanhante	CHAR(11)	NOT NULL,
FOREIGN KEY(cpf_pessoa) REFERENCES pessoa(CPF),
PRIMARY KEY(cpf_pessoa)
)
GO
CREATE TABLE funcionario(
cpf_pessoa		CHAR(11)			NOT NULL,
login			VARCHAR(100)		NOT NULL,
senha			CHAR(20)			NOT NULL,
tipoUsuario		VARCHAR(20)			NOT NULL,
RG				CHAR(13)			NOT NULL,
FOREIGN KEY(cpf_pessoa) REFERENCES pessoa(CPF) ON DELETE CASCADE,
PRIMARY KEY(cpf_pessoa)
)
GO
CREATE TABLE profissionalSaude(
funcionario		CHAR(11)			NOT NULL,
validador		INT					NOT NULL,
FOREIGN KEY(funcionario) REFERENCES funcionario(cpf_pessoa),
PRIMARY KEY(validador)
)
GO
CREATE TABLE evolucao(
atendimento		CHAR(10)			NOT NULL,
dataRealizada	DATE				NOT NULL,
tipoEvolucao	VARCHAR(50)			NOT NULL,
paciente		CHAR(11)			NOT NULL,
profissionalSaude INT				NOT NULL,
FOREIGN KEY(paciente) REFERENCES paciente(cpf_pessoa) ON DELETE CASCADE,
FOREIGN KEY(profissionalSaude) REFERENCES profissionalSaude(validador),
PRIMARY KEY(atendimento)
)
GO
SELECT * FROM pessoa
SELECT * FROM paciente
SELECT * FROM funcionario
SELECT * FROM profissionalSaude
SELECT * FROM evolucao

ALTER TABLE dbo.profissionalSaude
DROP CONSTRAINT FK__profissio__funci__440B1D61;

ALTER TABLE dbo.profissionalSaude
ADD CONSTRAINT FK__profissio__funci__440B1D61
FOREIGN KEY (funcionario) REFERENCES funcionario (cpf_pessoa)
ON DELETE CASCADE;

SELECT p.CPF, p.nome, p.sobrenome, p.nascimento, p.numero, p.logradouro, p.CEP, p.estado, p.bairro, f.login, f.senha, f.tipoUsuario, f.RG
FROM pessoa p, funcionario f 
WHERE f.cpf_pessoa = p.CPF
AND f.cpf_pessoa = 11122233344

--CRIAÇÃO DA PROCEDURE DE INSERÇÃO DO FUNCIONÁRIO

CREATE PROCEDURE p_insereFuncionario (
			@CPF			CHAR(11),
			@nome			VARCHAR(150),
			@sobrenome		VARCHAR(150),
			@nascimento		DATE,
			@numero			INT,
			@logradouro		VARCHAR(100),
			@CEP			CHAR(8),
			@estado			CHAR(2),
			@bairro			VARCHAR(100),
			@login			VARCHAR(100),
			@senha			CHAR(20),
			@tipoUsuario	VARCHAR(20),
			@RG				CHAR(13)
			)
AS
BEGIN
	DECLARE @tabelaFuncionario TABLE(
			CPF				CHAR(11),
			nome			VARCHAR(150),
			sobrenome		VARCHAR(150),
			nascimento		DATE,
			numero			INT,
			logradouro		VARCHAR(100),
			CEP				CHAR(8),
			estado			CHAR(2),
			bairro			VARCHAR(100),
			login			VARCHAR(100),
			senha			CHAR(20),
			tipoUsuario		VARCHAR(20),
			RG				CHAR(13)
			)

			DECLARE @cpfExistente INT

			SET @cpfExistente = (SELECT COUNT(*) FROM funcionario WHERE cpf_pessoa = @CPF)

	IF(@cpfExistente = 0)
	BEGIN
		INSERT INTO @tabelaFuncionario VALUES
		(@CPF, @nome, @sobrenome, @nascimento, @numero, @logradouro, @CEP, @estado, @bairro, @login, @senha, @tipoUsuario, @RG)

		INSERT INTO pessoa
		SELECT CPF, nome, sobrenome, nascimento, numero, logradouro, CEP, estado, bairro
		FROM @tabelaFuncionario

		INSERT INTO funcionario 
		SELECT @CPF, @login, @senha, @tipoUsuario, @RG
		FROM @tabelaFuncionario tf, pessoa p
		WHERE p.CPF = @CPF

		RAISERROR('FUNCIONÁRIO INSERIDO COM SUCESSO', 16, 1)
	END
	ELSE
		RAISERROR('USUÁRIO JÁ CADASTRADO', 16, 1)
		
END

EXEC p_insereFuncionario '44422233345', 'IGOR', 'AMARAL', '03-12-2003', 138, 'aGRIMENSOR sUGAYA', '08260030', 'SP', 'Colonia Zona Leste', 'IGOR@RH', '12345678', 'RH', '1234567891234'

CREATE PROCEDURE p_atualizaFuncionario (
    @CPF CHAR(11),
    @nome VARCHAR(150),
    @sobrenome VARCHAR(150),
    @nascimento DATE,
    @numero INT,
    @logradouro VARCHAR(100),
    @CEP CHAR(8),
    @estado CHAR(2),
    @bairro VARCHAR(100),
    @login VARCHAR(100),
    @senha VARCHAR(100),
    @tipoUsuario VARCHAR(50),
    @RG VARCHAR(20)
)
AS
BEGIN
    IF EXISTS (SELECT 1 FROM funcionario WHERE cpf_pessoa = @CPF)
    BEGIN
        UPDATE pessoa
        SET nome = @nome,
            sobrenome = @sobrenome,
            nascimento = @nascimento,
            numero = @numero,
            logradouro = @logradouro,
            CEP = @CEP,
            estado = @estado,
            bairro = @bairro
        WHERE CPF = @CPF

        UPDATE funcionario
        SET login = @login,
            senha = @senha,
            tipoUsuario = @tipoUsuario,
            RG = @RG
        WHERE cpf_pessoa = @CPF

        SELECT 'Funcionário atualizado com sucesso' AS mensagem
    END
    ELSE
    BEGIN
        SELECT 'Funcionário não encontrado' AS mensagem
    END
END

--CRIAÇÃO DA PROCEDURE DE INSERÇÃO DO PROFISSIONAL DE SAÚDE

CREATE PROCEDURE p_insereProfissionalSaude(
			@CPF			CHAR(11),
			@nome			VARCHAR(150),
			@sobrenome		VARCHAR(150),
			@nascimento		DATE,
			@numero			INT,
			@logradouro		VARCHAR(100),
			@CEP			CHAR(8),
			@estado			CHAR(2),
			@bairro			VARCHAR(100),
			@login			VARCHAR(100),
			@senha			CHAR(20),
			@tipoUsuario	VARCHAR(20),
			@RG				CHAR(13),
			@validador		INT
			)
AS
BEGIN
	DECLARE @tabelaProfissionalSaude TABLE(
			CPF				CHAR(11),
			nome			VARCHAR(150),
			sobrenome		VARCHAR(150),
			nascimento		DATE,
			numero			INT,
			logradouro		VARCHAR(100),
			CEP				CHAR(8),
			estado			CHAR(2),
			bairro			VARCHAR(100),
			login			VARCHAR(100),
			senha			CHAR(20),
			tipoUsuario		VARCHAR(20),
			RG				CHAR(13)		,
			validador		INT
			)

	DECLARE @cpfExistente INT

			SET @cpfExistente = (SELECT COUNT(*) FROM funcionario WHERE cpf_pessoa = @CPF)

	IF(@cpfExistente = 0)
	BEGIN
		INSERT INTO @tabelaProfissionalSaude VALUES
		(@CPF, @nome, @sobrenome, @nascimento, @numero, @logradouro, @CEP, @estado, @bairro, @login, @senha, @tipoUsuario, @RG, @validador)

		INSERT INTO pessoa
		SELECT CPF, nome, sobrenome, nascimento, numero, logradouro, CEP, estado, bairro
		FROM @tabelaProfissionalSaude

		INSERT INTO funcionario 
		SELECT @CPF, @login, @senha, @tipoUsuario, @RG
		FROM @tabelaProfissionalSaude tps, pessoa p
		WHERE p.CPF = @CPF

		INSERT INTO profissionalSaude (funcionario, validador)
		SELECT @CPF, @validador
		FROM @tabelaProfissionalSaude tps, funcionario f
		WHERE f.cpf_pessoa = @CPF

		RAISERROR('PROFISSIONAL INSERIDO COM SUCESSO', 16, 1)
	END
	ELSE
		RAISERROR('USUÁRIO JÁ CADASTRADO', 16, 1)
		
END


EXEC p_insereProfissionalSaude '11122233347', 'IGOR', 'AMARAL', '03-12-2003', 138, 'aGRIMENSOR sUGAYA', '08260030', 'SP', 'Colonia Zona Leste', 'IGOR MÉDICO', '12345678', 'MEDICO', '1234567891234', '123456'

-- Atualização do Profissional de saude
CREATE PROCEDURE p_atualizaProfissional (
    @CPF CHAR(11),
    @nome VARCHAR(150),
    @sobrenome VARCHAR(150),
    @nascimento DATE,
    @numero INT,
    @logradouro VARCHAR(100),
    @CEP CHAR(8),
    @estado CHAR(2),
    @bairro VARCHAR(100),
    @login VARCHAR(100),
    @senha VARCHAR(100),
    @tipoUsuario VARCHAR(50),
    @RG VARCHAR(20),
	@validador INT
)
AS
BEGIN
    IF EXISTS (SELECT 1 FROM funcionario WHERE cpf_pessoa = @CPF)
    BEGIN
        UPDATE pessoa
        SET nome = @nome,
            sobrenome = @sobrenome,
            nascimento = @nascimento,
            numero = @numero,
            logradouro = @logradouro,
            CEP = @CEP,
            estado = @estado,
            bairro = @bairro
        WHERE CPF = @CPF

        UPDATE funcionario
        SET login = @login,
            senha = @senha,
            tipoUsuario = @tipoUsuario,
            RG = @RG
        WHERE cpf_pessoa = @CPF

		UPDATE profissionalSaude
		SET validador = @validador
		WHERE funcionario = @CPF

        SELECT 'PROFISSIONAL ATUALIZADO COM SUCESSO' AS mensagem
    END
    ELSE
    BEGIN
        SELECT 'PROFISSIONAL NÃO ENCONTRADO' AS mensagem
    END
END


-- Criação da procedure de inserção de pacientes

CREATE PROCEDURE p_inserePaciente (
			@CPF			CHAR(11),
			@nome			VARCHAR(150),
			@sobrenome		VARCHAR(150),
			@nascimento		DATE,
			@numero			INT,
			@logradouro		VARCHAR(100),
			@CEP			CHAR(8),
			@estado			CHAR(2),
			@bairro			VARCHAR(100),
			@telefone			CHAR(11),
			@sexo				CHAR(2),
			@tipoProcedimento	VARCHAR(50),
			@nomeAcompanhante	VARCHAR(150),
			@sobrenomeAcompanhante VARCHAR(150),
			@telefoneAcompanhante	CHAR(11)
			)
AS
BEGIN
	DECLARE @tabelaPaciente TABLE(
			CPF				CHAR(11),
			nome			VARCHAR(150),
			sobrenome		VARCHAR(150),
			nascimento		DATE,
			numero			INT,
			logradouro		VARCHAR(100),
			CEP				CHAR(8),
			estado			CHAR(2),
			bairro			VARCHAR(100),
			telefone			CHAR(11),
			sexo				CHAR(2),
			tipoProcedimento	VARCHAR(50),
			nomeAcompanhante	VARCHAR(150),
			sobrenomeAcompanhante VARCHAR(150),
			telefoneAcompanhante	CHAR(11)
			)

			DECLARE @cpfExistente INT

			SET @cpfExistente = (SELECT COUNT(*) FROM paciente WHERE cpf_pessoa = @CPF)

	IF(@cpfExistente = 0)
	BEGIN
		INSERT INTO @tabelaPaciente VALUES
		(@CPF, @nome, @sobrenome, @nascimento, @numero, @logradouro, @CEP, @estado, @bairro, @telefone, @sexo, @tipoProcedimento, @nomeAcompanhante, @sobrenomeAcompanhante, @telefoneAcompanhante)

		INSERT INTO pessoa
		SELECT CPF, nome, sobrenome, nascimento, numero, logradouro, CEP, estado, bairro
		FROM @tabelaPaciente
		
		INSERT INTO paciente
		SELECT @CPF, @telefone, @sexo, @tipoProcedimento, @nomeAcompanhante, @sobrenomeAcompanhante, @telefoneAcompanhante
		FROM @tabelaPaciente tp, pessoa p
		WHERE p.CPF = @CPF

		RAISERROR('PACIENTE INSERIDO COM SUCESSO', 16, 1)
	END
	ELSE
		RAISERROR('PACIENTE JÁ CADASTRADO', 16, 1)
		
END

CREATE PROCEDURE p_atualizaPaciente (
			@CPF CHAR(11),
			@nome VARCHAR(150),
			@sobrenome VARCHAR(150),
			@nascimento DATE,
			@numero INT,
			@logradouro VARCHAR(100),
			@CEP CHAR(8),
			@estado CHAR(2),
			@bairro VARCHAR(100),
			@telefone CHAR(11),
			@sexo CHAR(2),
			@tipoProcedimento VARCHAR(50),
			@nomeAcompanhante VARCHAR(150),
			@sobrenomeAcompanhante VARCHAR(150),
			@telefoneAcompanhante CHAR(11)
)
AS
BEGIN
    DECLARE @tabelaPaciente TABLE (
			CPF CHAR(11),
			nome VARCHAR(150),
			sobrenome VARCHAR(150),
			nascimento DATE,
			numero INT,
			logradouro VARCHAR(100),
			CEP CHAR(8),
			estado CHAR(2),
			bairro VARCHAR(100),
			telefone CHAR(11),
			sexo CHAR(2),
			tipoProcedimento VARCHAR(50),
			nomeAcompanhante VARCHAR(150),
			sobrenomeAcompanhante VARCHAR(150),
			telefoneAcompanhante CHAR(11)
    )

    DECLARE @cpfExistente INT

    SET @cpfExistente = (SELECT COUNT(*) FROM paciente WHERE cpf_pessoa = @CPF)

    IF (@cpfExistente = 1)
    BEGIN
        UPDATE pessoa
        SET nome = @nome,
            sobrenome = @sobrenome,
            nascimento = @nascimento,
            numero = @numero,
            logradouro = @logradouro,
            CEP = @CEP,
            estado = @estado,
            bairro = @bairro
        WHERE CPF = @CPF

        UPDATE paciente
        SET telefone = @telefone,
            sexo = @sexo,
            tipoProcedimento = @tipoProcedimento,
            nomeAcompanhante = @nomeAcompanhante,
            sobrenomeAcompanhante = @sobrenomeAcompanhante,
            telefoneAcompanhante = @telefoneAcompanhante
        WHERE cpf_pessoa = @CPF

        RAISERROR('PACIENTE ATUALIZADO COM SUCESSO', 16, 1)
    END
    ELSE
        RAISERROR('PACIENTE NÃO ENCONTRADO', 16, 1)
END

--Criação da inserção de evolução

CREATE PROCEDURE p_insereEvolucao (
				@atendimento	CHAR (10),
				@tipoEvolucao	VARCHAR(50),
				@paciente		CHAR(11),
				@profissionalSaude INT
			)
AS
BEGIN
	DECLARE @tabelaEvolucao TABLE(
				atendimento		CHAR(10),
				tipoEvolucao	VARCHAR(50),
				paciente		CHAR(11),
				profissionalSaude INT
			)

			DECLARE @atendimentoExistente INT,
					@pacienteExistente INT,
					@profissionalExistente INT

			SET @atendimentoExistente = (SELECT COUNT(*) FROM evolucao WHERE atendimento = @atendimento)
			SET @pacienteExistente = (SELECT COUNT(*) FROM paciente WHERE cpf_pessoa = @paciente)
			SET @profissionalExistente = (SELECT COUNT(*) FROM profissionalSaude WHERE validador = @profissionalSaude)


	IF(@atendimentoExistente = 0 AND @pacienteExistente >= 1 AND @profissionalExistente >= 1)
	BEGIN
		INSERT INTO @tabelaEvolucao VALUES
		(@atendimento, @tipoEvolucao, @paciente, @profissionalSaude)

		INSERT INTO evolucao
		SELECT atendimento, GETDATE(), tipoEvolucao, paciente, profissionalSaude
		FROM @tabelaEvolucao
		WHERE atendimento = @atendimento

		RAISERROR('EVOLUÇÃO INSERIDA COM SUCESSO', 16, 1)
	END
	ELSE
		RAISERROR('EVOLUÇÃO COM DADOS INCORRETOS, PREENCHA OS CAMPOS CORRETAMENTE', 16, 1)
		
END

CREATE PROCEDURE p_atualizaEvolucao (
    @atendimento CHAR(10),
    @tipoEvolucao VARCHAR(50),
    @paciente CHAR(11),
    @profissionalSaude INT
)
AS
BEGIN
    DECLARE @tabelaEvolucao TABLE (
        atendimento CHAR(10),
        tipoEvolucao VARCHAR(50),
        paciente CHAR(11),
        profissionalSaude INT
    )

    DECLARE @atendimentoExistente INT,
        @pacienteExistente INT,
        @profissionalExistente INT

    SET @atendimentoExistente = (SELECT COUNT(*) FROM evolucao WHERE atendimento = @atendimento)
    SET @pacienteExistente = (SELECT COUNT(*) FROM paciente WHERE cpf_pessoa = @paciente)
    SET @profissionalExistente = (SELECT COUNT(*) FROM profissionalSaude WHERE validador = @profissionalSaude)

    IF (@atendimentoExistente >= 1 AND @pacienteExistente >= 1 AND @profissionalExistente >= 1)
    BEGIN
        UPDATE evolucao
        SET tipoEvolucao = @tipoEvolucao,
            paciente = @paciente,
            profissionalSaude = @profissionalSaude
        WHERE atendimento = @atendimento

        RAISERROR('EVOLUÇÃO ATUALIZADA COM SUCESSO', 16, 1)
    END
    ELSE
        RAISERROR('EVOLUÇÃO NÃO ENCONTRADA OU DADOS INCORRETOS, PREENCHA OS CAMPOS CORRETAMENTE', 16, 1)
END


EXEC p_insereEvolucao 1, 'MÉDICA', '11222333144', 123456