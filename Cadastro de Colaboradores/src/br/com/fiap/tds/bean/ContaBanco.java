package br.com.fiap.tds.bean;

/**
 * Classe que representa os dados bancários do colaborador
 * 
 * @author Rodrigo Chiarelli
 * @version 1.0
 */
public class ContaBanco {

	private int agencia;
	private int digitoAgencia;
	private int conta;
	private int digitoConta;

	public ContaBanco() {
	}

	public ContaBanco(int agencia, int digitoAgencia, int conta, int digitoConta) {
		this.agencia = agencia;
		this.digitoAgencia = digitoAgencia;
		this.conta = conta;
		this.digitoConta = digitoConta;
	}

	// Gets e Sets
	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public int getDigitoAgencia() {
		return digitoAgencia;
	}

	public void setDigitoAgencia(int digitoAgencia) {
		this.digitoAgencia = digitoAgencia;
	}

	public int getConta() {
		return conta;
	}

	public void setConta(int conta) {
		this.conta = conta;
	}

	public int getDigitoConta() {
		return digitoConta;
	}

	public void setDigitoConta(int digitoConta) {
		this.digitoConta = digitoConta;
	}

	// Mstra os dados bancários (Terminal RH)
	public String toString() {
		return "\n17 - Agencia Bradesco: " + agencia + "-" + digitoAgencia + "\n18 - Conta Bradesco: " + conta + "-"
				+ digitoConta;
	}

	// Mostra os dados bancários (Terminal Colaborador)
	public String toStringColaborador() {
		return "\n14 - Agencia Bradesco: " + agencia + "-" + digitoAgencia + "\n15 - Conta Bradesco: " + conta + "-"
				+ digitoConta;
	}

}
