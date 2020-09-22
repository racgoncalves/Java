package br.com.fiap.tds.bean;

public class Documentos {

	private byte[] rg;
	private byte[] carteiraTrabalho;
	private byte[] residencia;
	private byte[] tituloEleitor;
	private byte[] escolaridade;
	private byte[] reservista;
	
	public Documentos() {}
	
	public Documentos(byte[] rg, byte[] carteiraTrabalho, byte[] residencia, byte[] tituloEleitor,
			byte[] escolaridade) {
		this.rg = rg;
		this.carteiraTrabalho = carteiraTrabalho;
		this.residencia = residencia;
		this.tituloEleitor = tituloEleitor;
		this.escolaridade = escolaridade;
	}

	public byte[] getRg() {
		return rg;
	}

	public void setRg(byte[] rg) {
		this.rg = rg;
	}

	public byte[] getCarteiraTrabalho() {
		return carteiraTrabalho;
	}

	public void setCarteiraTrabalho(byte[] carteiraTrabalho) {
		this.carteiraTrabalho = carteiraTrabalho;
	}

	public byte[] getResidencia() {
		return residencia;
	}

	public void setResidencia(byte[] residencia) {
		this.residencia = residencia;
	}

	public byte[] getTituloEleitor() {
		return tituloEleitor;
	}

	public void setTituloEleitor(byte[] tituloEleitor) {
		this.tituloEleitor = tituloEleitor;
	}

	public byte[] getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(byte[] escolaridade) {
		this.escolaridade = escolaridade;
	}

	public byte[] getReservista() {
		return reservista;
	}

	public void setReservista(byte[] reservista) {
		this.reservista = reservista;
	}

}// Classe
