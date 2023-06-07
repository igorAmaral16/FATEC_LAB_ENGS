package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Evolucao;

public interface IEvolucaoDao {
	public List<Evolucao> listarEvolucoes() throws SQLException, ClassNotFoundException;
	public boolean buscarEvolucao(String atendimento) throws SQLException, ClassNotFoundException;
	public List<Evolucao> buscarEvolucaoCompleto(String atendimento) throws SQLException, ClassNotFoundException;
	public boolean atualizarEvolucao(Evolucao evolucao) throws ClassNotFoundException, SQLException;
	public void inserirEvolucao(Evolucao evolucao) throws ClassNotFoundException, SQLException;
	public boolean excluirEvolucao(String atendimento) throws ClassNotFoundException, SQLException;

}
