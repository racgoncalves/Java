package br.com.fiap.tds.bean;

/**
 * Classe que representa um documento
 * 
 * @author Rodrigo Chiarelli
 * @version 4.0
 */
public class Documento {

	private Login login;
	private int codigo;
	private String nome;
	private String nomeArquivo;
	private byte[] arquivo;

	public Documento() {
	}

	public Documento(Login login, int codigo, String nome, String nomeArquivo, byte[] arquivo) {

		this.login = login;
		this.codigo = codigo;
		this.nome = nome;
		this.nomeArquivo = nomeArquivo;
		this.arquivo = arquivo;
	}

	public Documento(Login login, String nome, String nomeArquivo, byte[] arquivo) {

		this.login = login;
		this.nome = nome;
		this.nomeArquivo = nomeArquivo;
		this.arquivo = arquivo;
	}

	public Documento(String nome, String nomeArquivo, byte[] arquivo) {

		this.nome = nome;
		this.nomeArquivo = nomeArquivo;
		this.arquivo = arquivo;
	}

	// Gets e Sets
	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	// Carrega nome do documento
	public String selecionarNome(int codigoNome) {
		switch (codigoNome) {
		case 1:
			return "RG";
		case 2:
			return "CARTEIRA DE TRABALHO";
		case 3:
			return "COMPROVANTE DE RESIDÊNCIA";
		case 4:
			return "TÍTULO DE ELEITOR";
		case 5:
			return "COMPROVANTE DE ESCOLARIDADE";
		case 6:
			return "CERTIFICADO DE RESERVISTA";
		default:
			return "";
		}
	}

}// Classe
