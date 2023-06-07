package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Funcionario;
import model.ProfissionalSaude;

public class FuncionarioDao implements IFuncionarioDao{
	
	private GenericDao gDao;
	
	public FuncionarioDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	@Override
	public List<Funcionario> listarFuncionarios() throws SQLException, ClassNotFoundException {
		return null;
	}

	@Override
	public List<Funcionario> buscarFuncionarioCompleto(String CPF) throws SQLException, ClassNotFoundException {
		System.out.println("ENTROUUU");
		List<Funcionario> funcionarios = new ArrayList<>();
		
		Connection con = gDao.getConnection();
		String sql = "SELECT p.CPF, p.nome, p.sobrenome, p.nascimento, p.numero, p.logradouro, p.CEP, p.estado, p.bairro, f.login, f.senha, f.tipoUsuario, f.RG "
                + "FROM pessoa p, funcionario f "
                + "WHERE f.cpf_pessoa = p.CPF "
                + "AND p.CPF = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, CPF);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
        	ProfissionalSaude funcionario = new ProfissionalSaude();
            funcionario.setCPF(rs.getString("CPF"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setSobrenome(rs.getString("sobrenome"));
            funcionario.setNascimento(LocalDate.parse(rs.getString("nascimento")));
            funcionario.setNumero(rs.getInt("numero"));
            funcionario.setLogradouro(rs.getString("logradouro"));
            funcionario.setCEP(rs.getString("CEP"));
            funcionario.setEstado(rs.getString("estado"));
            funcionario.setBairro(rs.getString("bairro"));
            funcionario.setLogin(rs.getString("login"));
            funcionario.setSenha(rs.getString("senha"));
            funcionario.setTipoUsuario(rs.getString("tipoUsuario"));
            funcionario.setRG(rs.getString("RG"));

            funcionarios.add(funcionario);
	    }
	    
	    rs.close();
	    ps.close();
	    con.close();

	    return funcionarios;
	}

	@Override
	public boolean atualizarFuncionario(Funcionario funcionario) throws ClassNotFoundException, SQLException {
		
		Connection conexao = gDao.getConnection();
		
		String sql = "{call p_atualizaFuncionario(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
		boolean cpfExiste = false;
        CallableStatement stmt = conexao.prepareCall(sql);

        try {
        	
            stmt = conexao.prepareCall("{call p_atualizaFuncionario(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

            // Configurar os parâmetros da stored procedure
            stmt.setString(1, funcionario.getCPF());
            stmt.setString(2, funcionario.getNome());
            stmt.setString(3, funcionario.getSobrenome());
            stmt.setDate(4, java.sql.Date.valueOf(funcionario.getNascimento()));
            stmt.setInt(5, funcionario.getNumero());
            stmt.setString(6, funcionario.getLogradouro());
            stmt.setString(7, funcionario.getCEP());
            stmt.setString(8, funcionario.getEstado());
            stmt.setString(9, funcionario.getBairro());
            stmt.setString(10, funcionario.getLogin());
            stmt.setString(11, funcionario.getSenha());
            stmt.setString(12, funcionario.getTipoUsuario());
            stmt.setString(13, funcionario.getRG());

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
	public void inserirFuncionario(Funcionario funcionario) throws ClassNotFoundException, SQLException {
		
		System.out.println("ENTROU PRA INSERIR");
		Connection con = gDao.getConnection();
		
		String sql = "{CALL p_insereFuncionario(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement statement = con.prepareCall(sql);

        statement.setString(1, funcionario.getCPF());
        statement.setString(2, funcionario.getNome());
        statement.setString(3, funcionario.getSobrenome());
        statement.setDate(4, java.sql.Date.valueOf(funcionario.getNascimento()));
        statement.setInt(5, funcionario.getNumero());
        statement.setString(6, funcionario.getLogradouro());
        statement.setString(7, funcionario.getCEP());
        statement.setString(8, funcionario.getEstado());
        statement.setString(9, funcionario.getBairro());
        statement.setString(10, funcionario.getLogin());
        statement.setString(11, funcionario.getSenha());
        statement.setString(12, funcionario.getTipoUsuario());
        statement.setString(13, funcionario.getRG());
        
        statement.execute();
        
        statement.close();
        con.close();
		
	}

	@Override
	public boolean excluirFuncionario(String CPF) throws ClassNotFoundException, SQLException {
		
		Connection con = gDao.getConnection();
		
		String sql = "DELETE FROM funcionario WHERE cpf_pessoa = ?";

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

	@Override
	public boolean procurarFuncionario(String usuario, String senha, String tipoUsuario) throws ClassNotFoundException, SQLException {
		Connection con = gDao.getConnection();
		
		String sql = "SELECT * FROM funcionario WHERE login = ? AND senha = ? AND tipoUsuario = ?";
	    
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, usuario);
	    ps.setString(2, senha);
	    ps.setString(3, tipoUsuario);
	    
	    ResultSet rs = ps.executeQuery();
	    
	    boolean autenticado = rs.next();
	    
	    rs.close();
	    ps.close();
	    con.close();
	    
	    return autenticado;
	}
	
	public boolean buscarFuncionario(String cpf) throws SQLException, ClassNotFoundException {
		System.out.println("ENTROUUU");
		
		Connection con = gDao.getConnection();
		
		String sql = "SELECT p.CPF, p.nome, p.sobrenome, p.nascimento, p.numero, p.logradouro, p.CEP, p.estado, p.bairro, f.login, f.senha, f.tipoUsuario, f.RG "
                + "FROM pessoa p, funcionario f "
                + "WHERE f.cpf_pessoa = p.CPF "
                + "AND p.CPF = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, cpf);

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
}
