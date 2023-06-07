package Entity;

public abstract class ProfissionalSaude extends Funcionario{

	private int validador;
	
	public ProfissionalSaude() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getValidador() {
		return validador;
	}

	public void setValidador(int validador) {
		this.validador = validador;
	}
	
	public boolean validarValidador() {
		
		return false;
	}
	
}
