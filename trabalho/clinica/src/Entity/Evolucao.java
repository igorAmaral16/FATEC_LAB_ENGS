package Entity;

import java.time.LocalDate;

public class Evolucao {
	
	private int atendimento;
	private LocalDate dataRealizada;
	private String tipoEvolucao;
	private Paciente paciente = new Paciente();
	private ProfissionalSaude profissionalSaude = new ProfissionalSaude() {};

	public Evolucao(int atendimento, LocalDate dataRealizada, String tipoEvolucao, Paciente paciente,
			ProfissionalSaude profissionalSaude) {
		super();
		this.atendimento = atendimento;
		this.dataRealizada = dataRealizada;
		this.tipoEvolucao = tipoEvolucao;
		this.paciente = paciente;
		this.profissionalSaude = profissionalSaude;
	}

	public int getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(int atendimento) {
		this.atendimento = atendimento;
	}

	public LocalDate getDataRealizada() {
		return dataRealizada;
	}

	public void setDataRealizada(LocalDate dataRealizada) {
		this.dataRealizada = dataRealizada;
	}

	public String getTipoEvolucao() {
		return tipoEvolucao;
	}

	public void setTipoEvolucao(String tipoEvolucao) {
		this.tipoEvolucao = tipoEvolucao;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public ProfissionalSaude getProfissionalSaude() {
		return profissionalSaude;
	}

	public void setProfissionalSaude(ProfissionalSaude profissionalSaude) {
		this.profissionalSaude = profissionalSaude;
	}

	@Override
	public String toString() {
		return "Evolucao [atendimento=" + atendimento + ", dataRealizada=" + dataRealizada + ", tipoEvolucao="
				+ tipoEvolucao + ", paciente=" + paciente + ", profissionalSaude=" + profissionalSaude + "]";
	}
	
}
