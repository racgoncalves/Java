package br.com.fiap.tds.validation;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DataValidation {

	// Valida a data se for maior de 16 anos e menor de 100 anos
	public boolean validarDataColaborador(String data) {

		if (!validarData(data))
			return false;

		int ano = getAno(data);
		int mes = getMes(data);
		int dia = getDia(data);

		// Pega a data atual
		Date dataAtual = new Date(System.currentTimeMillis());

		// Formata a data atual em ano
		SimpleDateFormat formatarAno = new SimpleDateFormat("yyyy");

		// Formata a data atual em mês
		SimpleDateFormat formatarMes = new SimpleDateFormat("MM");

		// Formata a data atual em dia
		SimpleDateFormat formatarDia = new SimpleDateFormat("dd");

		// Transforma os valores em números
		int anoAtual = Integer.parseInt(formatarAno.format(dataAtual));
		int mesAtual = Integer.parseInt(formatarMes.format(dataAtual));
		int diaAtual = Integer.parseInt(formatarDia.format(dataAtual));

		// Compara as datas
		if (ano > (anoAtual - 16))
			return false;
		else if (ano == (anoAtual - 16) && mes > mesAtual)
			return false;
		else if (ano == (anoAtual - 16) && mes == mesAtual && dia > diaAtual)
			return false;
		else if (ano < (anoAtual - 100))
			return false;

		return true;
	}

	// Valida a data se for menor do que a atual
	public boolean validarDataDependente(String data) {

		if (!validarData(data))
			return false;

		int ano = getAno(data);
		int mes = getMes(data);
		int dia = getDia(data);

		// Pega a data atual
		Date dataAtual = new Date(System.currentTimeMillis());

		// Formata a data atual em ano
		SimpleDateFormat formatarAno = new SimpleDateFormat("yyyy");

		// Formata a data atual em mês
		SimpleDateFormat formatarMes = new SimpleDateFormat("MM");

		// Formata a data atual em dia
		SimpleDateFormat formatarDia = new SimpleDateFormat("dd");

		// Transforma os valores em números
		int anoAtual = Integer.parseInt(formatarAno.format(dataAtual));
		int mesAtual = Integer.parseInt(formatarMes.format(dataAtual));
		int diaAtual = Integer.parseInt(formatarDia.format(dataAtual));

		// Compara as datas
		if (ano > anoAtual)
			return false;
		else if (ano == anoAtual && mes > mesAtual)
			return false;
		else if (ano == anoAtual && mes == mesAtual && dia > diaAtual)
			return false;

		return true;
	}

	// Valida a data
	public boolean validarData(String data) {

		if (data.length() != 10)
			return false;
		if (!data.substring(2, 3).equals("/") || !data.substring(5, 6).equals("/"))
			return false;
		if (!data.substring(0, 2).matches("[0-9]+") || !data.substring(3, 5).matches("[0-9]+")
				|| !data.substring(6).matches("[0-9]+"))
			return false;

		if (!validarAno(getAno(data)))
			return false;

		if (!validarMes(getMes(data)))
			return false;

		if (!validarDia(getDia(data), getMes(data)))
			return false;

		return true;
	}

	// Lista acontendo os dias de cada mês
	private int listaMes[] = { 31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	// Valida o ano e define dias de fevereiro
	public boolean validarAno(int ano) {
		if (ano > 0) {
			if (ano % 400 == 0) {
				this.listaMes[1] = 29;
			} else if (ano % 100 != 0 && ano % 4 == 0) {
				this.listaMes[1] = 29;
			} else {
				this.listaMes[1] = 28;
			}
			return true;
		} else {
			return false;
		}
	}

	// Valida o mês
	public boolean validarMes(int mes) {
		return mes > 0 && mes <= 12;
	}

	// Valida o dia
	public boolean validarDia(int dia, int mes) {
		if (validarMes(mes)) {
			return dia > 0 && dia <= listaMes[mes - 1];
		} else {
			return false;
		}
	}

	// Pega o Ano
	public int getAno(String data) {
		int ano = Integer.parseInt(data.substring(6));
		return ano;
	}

	// Pega o mês
	public int getMes(String data) {
		int mes = Integer.parseInt(data.substring(3, 5));
		return mes;
	}

	// Pega o dia
	public int getDia(String data) {
		int dia = Integer.parseInt(data.substring(0, 2));
		return dia;
	}

}
