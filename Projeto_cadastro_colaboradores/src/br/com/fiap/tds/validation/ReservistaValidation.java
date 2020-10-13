package br.com.fiap.tds.validation;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class ReservistaValidation {

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

}
