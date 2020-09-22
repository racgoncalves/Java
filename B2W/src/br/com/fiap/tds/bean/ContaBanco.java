package br.com.fiap.tds.bean;

public class ContaBanco {

	private String agencia;
	private String digitoAgencia;
	private String conta;
	private String digitoConta;

	public ContaBanco() {}

	public ContaBanco(String agencia, String digitoAgencia, String conta, String digitoConta) {
		this.agencia = agencia;
		this.digitoAgencia = digitoAgencia;
		this.conta = conta;
		this.digitoConta = digitoConta;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getDigitoAgencia() {
		return digitoAgencia;
	}

	public void setDigitoAgencia(String digitoAgencia) {
		this.digitoAgencia = digitoAgencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getDigitoConta() {
		return digitoConta;
	}

	public void setDigitoConta(String digitoConta) {
		this.digitoConta = digitoConta;
	}

	@Override
	public String toString() {
		return "\n16 - Agencia Bradesco: " + agencia + "-" + digitoAgencia + "\n17 - Conta Bradesco: " + conta + "-" + digitoConta;
	}

}
