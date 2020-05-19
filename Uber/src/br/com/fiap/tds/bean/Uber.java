package br.com.fiap.tds.bean;

public class Uber {
	
	//Atributos
	private static final double TAXA_UBER = 3;
	private double taxaMotorista = 1.5;
	private double valorCorrida;
	
	//Construtores
	public Uber () {}
	
	//Gets e Sets
	protected double getTaxaMotorista() {
		return taxaMotorista;
	}

	protected void setTaxaMotorista(double taxaMotorista) {
		this.taxaMotorista = taxaMotorista;
	}
	
	protected double getTaxaUber() {
		return TAXA_UBER;
	}
	
	protected double getValorCorrida() {
		return valorCorrida;
	}

	protected void setValorCorrida(double valorCorrida) {
		this.valorCorrida = valorCorrida;
	}

	//Altera taxa do motorista pela cidade
	protected void setTaxaMotorista(String resposta) {
		if (resposta.equals("S")) {
			taxaMotorista += 0.25;
		}
	}
	
	//Altera taxa do motorista pela idade
	protected void setTaxaMotorista(int idade) {
		if (idade >= 65) {
			setTaxaMotorista(getTaxaMotorista() - 0.25);
		}
	}
	
	//Monta valor corrida
	protected void setValorCorrida() {
		valorCorrida = TAXA_UBER + getTaxaMotorista();
	}
	
}
