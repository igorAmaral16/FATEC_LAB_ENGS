package Entity;

public class Paciente extends Pessoa{
	
	private int telefone;
    private char sexo;
	private String tipoProcedimento;
	
	private Acompanhante acompanhante = new Acompanhante();

	public Paciente() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public String getTipoProcedimento() {
		return tipoProcedimento;
	}
	public void setTipoProcedimento(String tipoProcedimento) {
		this.tipoProcedimento = tipoProcedimento;
	}
	public Acompanhante getAcompanhante() {
		return acompanhante;
	}
	public void setAcompanhante(Acompanhante acompanhante) {
		this.acompanhante = acompanhante;
	}
	
	
	
}
