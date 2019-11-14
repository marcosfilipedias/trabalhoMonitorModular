package com.trabalho.modular.cadastroMonitor.models;

public class Aluno {

	private Integer matricula;
	//verificar como sera passado as materias e valores
	private String historico;
	private Boolean curriculo;
	public Integer getMatricula() {
		return matricula;
	}
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}	
	public String getHistorico() {
		return historico;
	}
	public void setHistorico(String historico) {
		this.historico = historico;
	}
	public Boolean getCurriculo() {
		return curriculo;
	}
	public void setCurriculo(boolean b) {
		this.curriculo = b;
	}
	public Aluno() {
		
	}
	}
