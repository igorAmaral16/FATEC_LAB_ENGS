package persistence;

import java.sql.SQLException;
import java.util.List;

import model.ProfissionalSaude;

public interface IProfissionalSaude {
	public List<ProfissionalSaude> listarFuncionarios() throws SQLException, ClassNotFoundException;
	public boolean buscarFuncionario(String CPF) throws SQLException, ClassNotFoundException;
	public List<ProfissionalSaude> buscarFuncionarioCompleto(String cpf) throws SQLException, ClassNotFoundException;
	public boolean atualizarFuncionario(ProfissionalSaude funcionario) throws ClassNotFoundException, SQLException;
	public void inserirFuncionario(ProfissionalSaude profissionalSaude) throws ClassNotFoundException, SQLException;
	public void excluirFuncionario(String CPF) throws ClassNotFoundException, SQLException;
	public boolean procurarFuncionario(String usuario, String senha, String tipoUsuario) throws ClassNotFoundException, SQLException;

}
