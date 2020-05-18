package br.com.fiap.tds.bean;

public class Uber {
	
	//Atributos
	private double taxaUber = 3;
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
		return taxaUber;
	}
	
	protected void setTaxaUber (double taxaUber) {
		this.taxaUber = taxaUber;
	}
	
	public double getValorCorrida() {
		return valorCorrida;
	}

	public void setValorCorrida(double valorCorrida) {
		this.valorCorrida = valorCorrida;
	}
	
	//Overload
	
	//Altera taxa do motorista pela cidade
	protected void setTaxaMotorista(String resposta) {
		if (resposta.equals("S")) {
			taxaMotorista += 0.25;
		}
	}
	
	//Monta valor corrida
	public void setValorCorrida() {
		valorCorrida = getTaxaUber() + getTaxaMotorista();
	}
	
}
