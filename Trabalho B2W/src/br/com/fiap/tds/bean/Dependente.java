package br.com.fiap.tds.bean;

/**
 * Classe que representa um dependente
 * 
 * @author Rodrigo Chiarelli
 * @version 1.0
 */
public class Dependente {

	private int codigoDependente;
	private String tipo;
	private DadosDependente dadosDependente;
	private String nomeDocumento;
	private String nomeArquivo;
	private byte[] arquivo;

	public Dependente(int codigoDependente, String tipo, DadosDependente dadosDependente, String nomeDocumento,
			String nomeArquivo, byte[] arquivo) {
		this.codigoDependente = codigoDependente;
		this.tipo = tipo;
		this.dadosDependente = dadosDependente;
		this.nomeDocumento = nomeDocumento;
		this.nomeArquivo = nomeArquivo;
		this.arquivo = arquivo;
	}
	
	public Dependente(String tipo, DadosDependente dadosDependente, String nomeDocumento,
			String nomeArquivo, byte[] arquivo) {

		this.tipo = tipo;
		this.dadosDependente = dadosDependente;
		this.nomeDocumento = nomeDocumento;
		this.nomeArquivo = nomeArquivo;
		this.arquivo = arquivo;
	}

	public Dependente() {
	}

	// Gets e Sets
	public int getCodigoDependente() {
		return codigoDependente;
	}

	public void setCodigoDependente(int codigoDependente) {
		this.codigoDependente = codigoDependente;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNomeDocumento() {
		return nomeDocumento;
	}

	public void setNomeDocumento(String nomeDocumento) {
		this.nomeDocumento = nomeDocumento;
	}

	public DadosDependente getDadosDependente() {
		return dadosDependente;
	}

	public void setDadosDependente(DadosDependente dadosDependente) {
		this.dadosDependente = dadosDependente;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	// Mostra todos os dados do dependente
	public String toString() {
		return "\nCódigo do Dependente: " + codigoDependente + "\nTipo: " + tipo + dadosDependente
				+ "\nNome do Documento: " + nomeDocumento;
	}

}
