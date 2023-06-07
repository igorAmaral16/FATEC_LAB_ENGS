package Entity;

public class Acompanhante {
	
	private String nome;
    private String sobrenome;
	private int telefone;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	@Override
	public String toString() {
		return "Acompanhante ["
				+ "nome=" + nome 
				+ ", sobrenome=" + sobrenome 
				+ ", telefone=" + telefone 
				+ "]";
	}
	
	
	
}
