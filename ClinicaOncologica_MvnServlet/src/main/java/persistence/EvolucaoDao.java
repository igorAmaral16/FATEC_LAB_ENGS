package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Evolucao;

public class EvolucaoDao implements IEvolucaoDao{
	private GenericDao gDao;
	
	public EvolucaoDao(GenericDao gDao) {
		this.gDao = gDao;
		
	}

	@Override
	public List<Evolucao> listarEvolucoes() throws SQLException, ClassNotFoundException {
		List<Evolucao> evolucoes = new ArrayList<>();
	    
	    Connection con = gDao.getConnection();
	    String sql = "SELECT atendimento, tipoEvolucao, paciente, profissionalSaude\r\n"
	    		+ "FROM evolucao";
	    
	    PreparedStatement ps = con.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();
	    
	    while (rs.next()) {
	        Evolucao evolucao = new Evolucao();
	        evolucao.setAtendimento(rs.getString("atendimento"));
	        evolucao.setDataRealizada(LocalDate.parse(rs.getString("dataRealizada")));
	        evolucao.setTipoEvolucao(rs.getString("tipoEvolucao"));
	        evolucao.setPaciente(rs.getString("paciente"));
	        evolucao.setProfissionalSaude(rs.getInt("profissionalSaude"));
	        
	        evolucoes.add(evolucao);
	    }
	    
	    rs.close();
	    ps.close();
	    con.close();
	    
	    return evolucoes;
	}

	@Override
	public boolean buscarEvolucao(String atendimento) throws SQLException, ClassNotFoundException {
		System.out.println("ENTROUUU");
		
		Connection con = gDao.getConnection();
		
		String sql = "SELECT atendimento FROM evolucao WHERE atendimento = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, atendimento);

        ResultSet rs = ps.executeQuery();
        
        boolean atendimentoExiste = rs.next();
        if(atendimentoExiste == true) {
        	atendimentoExiste = true;
        }else {
        	atendimentoExiste = false;
        }
	    
	    rs.close();
	    ps.close();
	    con.close();

	    return atendimentoExiste;
	}

	@Override
	public List<Evolucao> buscarEvolucaoCompleto(String atendimeto) throws SQLException, ClassNotFoundException {
		List<Evolucao> evolucoes = new ArrayList<>();
	    
	    Connection con = gDao.getConnection();
	    String sql = "SELECT atendimento, tipoEvolucao, paciente, profissionalSaude\r\n"
	    		+ "FROM evolucao";
	    
	    PreparedStatement ps = con.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();
	    
	    while (rs.next()) {
	        Evolucao evolucao = new Evolucao();
	        evolucao.setAtendimento(rs.getString("atendimento"));
	        evolucao.setDataRealizada(LocalDate.parse(rs.getString("dataRealizada")));
	        evolucao.setTipoEvolucao(rs.getString("tipoEvolucao"));
	        evolucao.setPaciente(rs.getString("paciente"));
	        evolucao.setProfissionalSaude(rs.getInt("profissionalSaude"));
	        
	        evolucoes.add(evolucao);
	    }
	    
	    rs.close();
	    ps.close();
	    con.close();
	    
	    return evolucoes;
	}

	@Override
	public boolean atualizarEvolucao(Evolucao evolucao) throws ClassNotFoundException, SQLException {
		Connection conexao = gDao.getConnection();
		System.out.println("ENTROU PRA INSERIR");
		String sql = "{CALL p_atualizaEvolucao(?, ?, ?, ?)}";
		boolean cpfExiste = false;
        CallableStatement stmt = conexao.prepareCall(sql);

        try {
        	System.out.println("CHAMOU A PROCEDURE");
            stmt = conexao.prepareCall("{CALL p_atualizaEvolucao(?, ?, ?, ?)}");

            // Configurar os parâmetros da stored procedure
            stmt.setString(1, evolucao.getAtendimento());
            stmt.setString(2, evolucao.getTipoEvolucao());
            stmt.setString(3, evolucao.getPaciente());
            stmt.setInt(4, evolucao.getProfissionalSaude());

            stmt.executeUpdate();
            
            System.out.println("EXECUTOU A PROCEDURE");
            // Verificar se a atualização foi bem-sucedida
            ResultSet rs = stmt.getResultSet();
            if (rs != null && rs.next()) {
                String mensagem = rs.getString("mensagem");
                if ("Evolução atualizada com sucesso".equals(mensagem)) {
                    cpfExiste = true;
                }
            }
        } catch (Exception e) {
        	System.out.println(e.getMessage());
		}finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        }

        return cpfExiste;
	}

	@Override
	public void inserirEvolucao(Evolucao evolucao) throws ClassNotFoundException, SQLException {
		System.out.println("ENTROU PRA INSERIR");
		Connection con = gDao.getConnection();
		
		String sql = "{CALL p_insereEvolucao(?, ?, ?, ?)}";
        CallableStatement stmt = con.prepareCall(sql);

        stmt.setString(1, evolucao.getAtendimento());
        stmt.setString(2, evolucao.getTipoEvolucao());
        stmt.setString(3, evolucao.getPaciente());
        stmt.setInt(4, evolucao.getProfissionalSaude());
        
        stmt.execute();
        
        stmt.close();
        con.close();
		
	}

	@Override
	public boolean excluirEvolucao(String atendimento) throws ClassNotFoundException, SQLException {
	    Connection con = gDao.getConnection();
	    String sql = "DELETE FROM evolucao WHERE atendimento = ?";
	    boolean exclusao = false;

	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, atendimento);
	    int rowsAffected = ps.executeUpdate();

	    if (rowsAffected > 0) {
	        exclusao = true;
	    }

	    ps.close();
	    con.close();

	    return exclusao;
	}
}
