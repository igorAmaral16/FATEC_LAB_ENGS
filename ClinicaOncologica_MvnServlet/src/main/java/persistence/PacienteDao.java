package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Paciente;

public class PacienteDao implements IPacienteDao{
private GenericDao gDao;
	
	public PacienteDao(GenericDao gDao) {
		this.gDao = gDao;
		
	}
	@Override
	public List<Paciente> listarPacientes() throws SQLException, ClassNotFoundException {
		List<Paciente> pacientes = new ArrayList<>();
	    
	    Connection con = gDao.getConnection();
	    String sql = "SELECT p.CPF, p.nome, p.sobrenome, p.nascimento, p.numero, p.logradouro, p.CEP, p.estado, p.bairro, pa.telefone, pa.sexo, pa.tipoProcedimento, pa.nomeAcompanhante, pa.sobrenomeAcompanhante, pa.telefoneAcompanhante "
	            + "FROM pessoa p, paciente pa "
	            + "WHERE pa.cpf_pessoa = p.CPF";
	    
	    PreparedStatement ps = con.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();
	    
	    while (rs.next()) {
	        Paciente paciente = new Paciente();
	        paciente.setCPF(rs.getString("CPF"));
	        paciente.setNome(rs.getString("nome"));
	        paciente.setSobrenome(rs.getString("sobrenome"));
	        paciente.setNascimento(LocalDate.parse(rs.getString("nascimento")));
	        paciente.setNumero(rs.getInt("numero"));
	        paciente.setLogradouro(rs.getString("logradouro"));
	        paciente.setCEP(rs.getString("CEP"));
	        paciente.setEstado(rs.getString("estado"));
	        paciente.setBairro(rs.getString("bairro"));
	        paciente.setTelefone(rs.getString("telefone"));
	        paciente.setSexo(rs.getString("sexo"));
	        paciente.setTipoProcedimento(rs.getString("tipoProcedimento"));
	        paciente.setNomeAcompanhante(rs.getString("nomeAcompanhante"));
	        paciente.setSobrenomeAcompanhante(rs.getString("sobrenomeAcompanhante"));
	        paciente.setTelefoneAcompanhante(rs.getString("telefoneAcompanhante"));
	        
	        pacientes.add(paciente);
	    }
	    
	    rs.close();
	    ps.close();
	    con.close();
	    
	    return pacientes;
	}

	@Override
	public boolean buscarPaciente(String CPF) throws SQLException, ClassNotFoundException {
		System.out.println("ENTROUUU");
		
		Connection con = gDao.getConnection();
		
		String sql = "SELECT p.CPF, p.nome, p.sobrenome, p.nascimento, p.numero, p.logradouro, p.CEP, p.estado, p.bairro, pa.telefone, pa.sexo, pa.tipoProcedimento "
                + "FROM pessoa p, paciente pa "
                + "WHERE pa.cpf_pessoa = p.CPF "
                + "AND p.CPF = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, CPF);

        ResultSet rs = ps.executeQuery();
        
        boolean cpfExiste = rs.next();
        if(cpfExiste == true) {
        	cpfExiste = true;
        }else {
        	cpfExiste = false;
        }
	    
	    rs.close();
	    ps.close();
	    con.close();

	    return cpfExiste;
	}

	@Override
	public List<Paciente> buscarPacienteCompleto(String cpf) throws SQLException, ClassNotFoundException {
		System.out.println("ENTROUUU");
		List<Paciente> pacientes = new ArrayList<>();
		
		Connection con = gDao.getConnection();
		String sql = "SELECT p.CPF, p.nome, p.sobrenome, p.nascimento, p.numero, p.logradouro, p.CEP, p.estado, p.bairro, pa.telefone, pa.sexo, pa.tipoProcedimento "
                + "FROM pessoa p, paciente pa "
                + "WHERE pa.cpf_pessoa = p.CPF "
                + "AND p.CPF = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, cpf);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
        	Paciente paciente = new Paciente();
        	paciente.setCPF(rs.getString("CPF"));
        	paciente.setNome(rs.getString("nome"));
        	paciente.setSobrenome(rs.getString("sobrenome"));
        	paciente.setNascimento(LocalDate.parse(rs.getString("nascimento")));
        	paciente.setNumero(rs.getInt("numero"));
        	paciente.setLogradouro(rs.getString("logradouro"));
        	paciente.setCEP(rs.getString("CEP"));
        	paciente.setEstado(rs.getString("estado"));
        	paciente.setBairro(rs.getString("bairro"));
        	paciente.setBairro(rs.getString("telefone"));
        	paciente.setBairro(rs.getString("sexo"));
        	paciente.setBairro(rs.getString("tipoProcedimento"));
            pacientes.add(paciente);
        }
        rs.close();
	    ps.close();
	    con.close();

	    return pacientes;
	}

	@Override
	public boolean atualizarPaciente(Paciente paciente) throws ClassNotFoundException, SQLException {
		Connection conexao = gDao.getConnection();
		
		String sql = "{call p_atualizaPaciente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
		boolean cpfExiste = false;
        CallableStatement stmt = conexao.prepareCall(sql);

        try {
        	
            stmt = conexao.prepareCall("{call p_atualizaPaciente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

            // Configurar os parâmetros da stored procedure
            stmt.setString(1, paciente.getCPF());
            stmt.setString(2, paciente.getNome());
            stmt.setString(3, paciente.getSobrenome());
            stmt.setDate(4, java.sql.Date.valueOf(paciente.getNascimento()));
            stmt.setInt(5, paciente.getNumero());
            stmt.setString(6, paciente.getLogradouro());
            stmt.setString(7, paciente.getCEP());
            stmt.setString(8, paciente.getEstado());
            stmt.setString(9, paciente.getBairro());
            stmt.setString(9, paciente.getBairro());
            stmt.setString(9, paciente.getBairro());
            stmt.setString(9, paciente.getBairro());
            stmt.setString(9, paciente.getBairro());
            stmt.setString(9, paciente.getBairro());
            stmt.setString(9, paciente.getBairro());

            stmt.executeUpdate();

            // Verificar se a atualização foi bem-sucedida
            ResultSet rs = stmt.getResultSet();
            if (rs != null && rs.next()) {
                String mensagem = rs.getString("mensagem");
                if ("Funcionário atualizado com sucesso".equals(mensagem)) {
                    cpfExiste = true;
                }
            }
        } finally {
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
	public void inserirPaciente(Paciente paciente) throws ClassNotFoundException, SQLException {
		System.out.println("ENTROU PRA INSERIR");
		Connection con = gDao.getConnection();
		
		String sql = "{CALL p_inserePaciente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement statement = con.prepareCall(sql);

        statement.setString(1, paciente.getCPF());
        statement.setString(2, paciente.getNome());
        statement.setString(3, paciente.getSobrenome());
        statement.setDate(4, java.sql.Date.valueOf(paciente.getNascimento()));
        statement.setInt(5, paciente.getNumero());
        statement.setString(6, paciente.getLogradouro());
        statement.setString(7, paciente.getCEP());
        statement.setString(8, paciente.getEstado());
        statement.setString(9, paciente.getBairro());
        statement.setString(10, paciente.getTelefone());
        statement.setString(11, paciente.getSexo());
        statement.setString(12, paciente.getTipoProcedimento());
        statement.setString(13, paciente.getNomeAcompanhante());
        statement.setString(14, paciente.getSobrenomeAcompanhante());
        statement.setString(15, paciente.getTelefoneAcompanhante());
        statement.execute();
        
        statement.close();
        con.close();
		
	}

	@Override
	public boolean excluirPaciente(String CPF) throws ClassNotFoundException, SQLException {
		Connection con = gDao.getConnection();
		
		String sql = "DELETE FROM paciente WHERE cpf_pessoa = ?";
		boolean exclusao = false;
		

	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, CPF);
	    int rowsAffected = ps.executeUpdate();

	    if (rowsAffected > 0) {
	        exclusao = true;
	    }

	    ps.close();
	    con.close();

	    return exclusao;
	}
}
