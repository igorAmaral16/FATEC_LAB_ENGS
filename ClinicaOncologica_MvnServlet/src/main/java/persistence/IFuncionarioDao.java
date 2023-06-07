package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Funcionario;

public interface IFuncionarioDao {
	public List<Funcionario> listarFuncionarios() throws SQLException, ClassNotFoundException;
	public boolean buscarFuncionario(String CPF) throws SQLException, ClassNotFoundException;
	public List<Funcionario> buscarFuncionarioCompleto(String cpf) throws SQLException, ClassNotFoundException;
	public boolean atualizarFuncionario(Funcionario funcionario) throws ClassNotFoundException, SQLException;
	public void inserirFuncionario(Funcionario profissionalSaude) throws ClassNotFoundException, SQLException;
	public boolean excluirFuncionario(String CPF) throws ClassNotFoundException, SQLException;
	public boolean procurarFuncionario(String usuario, String senha, String tipoUsuario) throws ClassNotFoundException, SQLException;

}
