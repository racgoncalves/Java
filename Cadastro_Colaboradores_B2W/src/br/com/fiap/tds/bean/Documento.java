package br.com.fiap.tds.bean;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Classe que representa os documentos de um colaborador
 * 
 * @author Rodrigo Chiarelli
 * @version 1.0
 */
public class Documento {

	private String nome;
	private String nomeArquivo;
	private byte[] arquivo;

	public Documento(String nome, String nomeArquivo, byte[] arquivo) {
		super();
		this.nome = nome;
		this.nomeArquivo = nomeArquivo;
		this.arquivo = arquivo;
	}

	public Documento() {
	}

	// Gets e Sets

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

	// Checa se é Reservista
	public boolean isReservista(String sexo, String dataNascimento) {

		if (sexo.equals("FEMININO"))
			return false;

		else {

			// Valida se for menor de 18 anos o ano de nascimento
			return isMaiorDezoito(dataNascimento);
		}
	}

	// Valida a data se o ano de nascimento é superior a 18 anos
	public boolean isMaiorDezoito(String dataNascimento) {

		// Pega o ano da data e transforma em número
		int ano = Integer.parseInt(dataNascimento.substring(dataNascimento.length() - 4));

		// Pega a data atual
		Date data = new Date(System.currentTimeMillis());

		// Formata a data atual em ano
		SimpleDateFormat formatarAno = new SimpleDateFormat("yyyy");

		// Transforma a data atual em número
		int anoAtual = Integer.parseInt(formatarAno.format(data));

		// Compara as duas datas
		if (ano > (anoAtual - 18))
			return false;

		return true;
	}

}// Classe
