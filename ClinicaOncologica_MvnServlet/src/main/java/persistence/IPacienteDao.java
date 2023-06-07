package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Paciente;

public interface IPacienteDao {
	public List<Paciente> listarPacientes() throws SQLException, ClassNotFoundException;
	public boolean buscarPaciente(String CPF) throws SQLException, ClassNotFoundException;
	public List<Paciente> buscarPacienteCompleto(String cpf) throws SQLException, ClassNotFoundException;
	public boolean atualizarPaciente(Paciente paciente) throws ClassNotFoundException, SQLException;
	public void inserirPaciente(Paciente paciente) throws ClassNotFoundException, SQLException;
	public boolean excluirPaciente(String CPF) throws ClassNotFoundException, SQLException;
	
}
