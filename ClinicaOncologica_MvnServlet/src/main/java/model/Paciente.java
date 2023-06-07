package model;

public class Paciente extends Pessoa{
	private String telefone;
	private String sexo;
	private String tipoProcedimento;
	private String nomeAcompanhante;
	private String sobrenomeAcompanhante;
	private String telefoneAcompanhante;
	
	public Paciente() {
		super();
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTipoProcedimento() {
		return tipoProcedimento;
	}

	public void setTipoProcedimento(String tipoProcedimento) {
		this.tipoProcedimento = tipoProcedimento;
	}

	public String getNomeAcompanhante() {
		return nomeAcompanhante;
	}

	public void setNomeAcompanhante(String nomeAcompanhante) {
		this.nomeAcompanhante = nomeAcompanhante;
	}

	public String getSobrenomeAcompanhante() {
		return sobrenomeAcompanhante;
	}

	public void setSobrenomeAcompanhante(String sobrenomeAcompanhante) {
		this.sobrenomeAcompanhante = sobrenomeAcompanhante;
	}

	public String getTelefoneAcompanhante() {
		return telefoneAcompanhante;
	}

	public void setTelefoneAcompanhante(String telefoneAcompanhante) {
		this.telefoneAcompanhante = telefoneAcompanhante;
	}

}
