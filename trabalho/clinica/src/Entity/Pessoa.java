package Entity;

import java.sql.Date;

public class Pessoa {
	
	private String nome;
	private String sobrenome;
	private String logradouro;
	private String bairro;
	private char CPF;
	private char CEP;
	private char estado;
    private Date nascimento;
    private int numero;
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
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public char getCPF() {
		return CPF;
	}
	public void setCPF(char cPF) {
		CPF = cPF;
	}
	public char getCEP() {
		return CEP;
	}
	public void setCEP(char cEP) {
		CEP = cEP;
	}
	public char getEstado() {
		return estado;
	}
	public void setEstado(char estado) {
		this.estado = estado;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public void verificarCPF() {
		
	}
	
	public void verificarCEP() {
		
	}
	
	@Override
	public String toString() {
		return "Pessoa ["
				+ "nome=" + nome 
				+ ", sobrenome=" + sobrenome 
				+ ", logradouro=" + logradouro 
				+ ", bairro=" + bairro
				+ ", CPF=" + CPF 
				+ ", CEP=" + CEP 
				+ ", estado=" + estado 
				+ ", nascimento=" + nascimento 
				+ ", numero=" + numero 
				+ "]";
	}
    
    
}
