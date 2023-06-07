package model;

import java.time.LocalDate;

public class Pessoa {
	
	private String nome;
	private String sobrenome;
	private LocalDate nascimento;
	private String CPF;
	private int numero;
	private String logradouro;
	private String CEP;
	private String estado;
	private String bairro;
	
	public Pessoa(String nome, String sobrenome, LocalDate nascimento, String cPF, int numero, String logradouro,
			String cEP, String estado, String bairro) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.nascimento = nascimento;
		CPF = cPF;
		this.numero = numero;
		this.logradouro = logradouro;
		CEP = cEP;
		this.estado = estado;
		this.bairro = bairro;
	}
	
	public Pessoa() {
		// TODO Auto-generated constructor stub
	}

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
	public LocalDate getNascimento() {
		return nascimento;
	}
	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getCEP() {
		return CEP;
	}
	public void setCEP(String cEP) {
		CEP = cEP;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

}
