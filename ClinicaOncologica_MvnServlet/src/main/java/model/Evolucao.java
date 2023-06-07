package model;

import java.time.LocalDate;

public class Evolucao {
	private String atendimento;
	private LocalDate dataRealizada;
	private String tipoEvolucao;
	private String paciente;
	private int profissionalSaude;
	
	public String getAtendimento() {
		return atendimento;
	}
	public void setAtendimento(String string) {
		this.atendimento = string;
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
	public String getPaciente() {
		return paciente;
	}
	public void setPaciente(String string) {
		this.paciente = string;
	}
	public int getProfissionalSaude() {
		return profissionalSaude;
	}
	public void setProfissionalSaude(int string) {
		this.profissionalSaude = string;
	}

}
