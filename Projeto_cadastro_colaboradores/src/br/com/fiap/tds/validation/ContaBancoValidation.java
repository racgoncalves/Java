package br.com.fiap.tds.validation;

public class ContaBancoValidation {

	// Valida o número da agência
	public boolean isAgencia(String agencia) {
		if (agencia.length() != 4 || !agencia.matches("[0-9]+"))
			return false;
		return true;
	}

	// Valida o número da conta
	public boolean isConta(String conta) {
		if (conta.length() > 7 || conta.length() < 1 || !conta.matches("[0-9]+"))
			return false;
		return true;
	}

	// Valida o dígito da agência e da conta
	public boolean isDigito(String digito) {
		if (!digito.matches("[0-9]"))
			return false;
		return true;
	}

}
