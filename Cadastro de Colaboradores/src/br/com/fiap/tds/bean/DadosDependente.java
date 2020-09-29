package br.com.fiap.tds.bean;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Classe que representa os dados gerais de um dependente
 * 
 * @author Rodrigo Chiarelli
 * @version 1.0
 */
public class DadosDependente extends Dados {

	private String nome;
	private String cpf;
	private String sexo;
	private String dataNascimento;

	public DadosDependente(String nome, String cpf, String sexo, String dataNascimento) {

		this.nome = nome;
		this.cpf = cpf;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
	}

	public DadosDependente() {
	}

	// Gets e Sets
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String toString() {
		return "\nNome: " + nome + "\nCPF: " + montarCPF(cpf) + "\nSexo: " + sexo + "\nData de Nascimento: "
				+ dataNascimento;
	}

	// Valida a data se for menor do que a atual
	public boolean validarData(int dia, int mes, int ano) {

		// Pega a data atual
		Date data = new Date(System.currentTimeMillis());

		// Formata a data atual em ano
		SimpleDateFormat formatarAno = new SimpleDateFormat("yyyy");

		// Formata a data atual em mês
		SimpleDateFormat formatarMes = new SimpleDateFormat("MM");

		// Formata a data atual em dia
		SimpleDateFormat formatarDia = new SimpleDateFormat("dd");

		// Transforma os valores em números
		int anoAtual = Integer.parseInt(formatarAno.format(data));
		int mesAtual = Integer.parseInt(formatarMes.format(data));
		int diaAtual = Integer.parseInt(formatarDia.format(data));

		// Compara as datas
		if (ano > anoAtual)
			return false;
		else if (ano == anoAtual && mes > mesAtual)
			return false;
		else if (ano == anoAtual && mes == mesAtual && dia > diaAtual)
			return false;

		return true;
	}

}
