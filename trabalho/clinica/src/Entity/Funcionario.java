package Entity;

public abstract class Funcionario extends Pessoa{
	
	private String login;
    private String tipoUSuario;
    private char senha;
    private char RG;
    
	public Funcionario() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getTipoUSuario() {
		return tipoUSuario;
	}
	public void setTipoUSuario(String tipoUSuario) {
		this.tipoUSuario = tipoUSuario;
	}
	public char getSenha() {
		return senha;
	}
	public void setSenha(char senha) {
		this.senha = senha;
	}
	public char getRG() {
		return RG;
	}
	public void setRG(char rG) {
		RG = rG;
	}
	
	public boolean validarLogin() {
		
		return false;
	}
	
	public void validarRG() {
		
	}

	@Override
	public String toString() {
		return "Funcionario ["
				+ "login=" + login 
				+ ", tipoUSuario=" + tipoUSuario 
				+ ", senha=" + senha 
				+ ", RG=" + RG 
				+ "]";
	}
    
	
    
}
