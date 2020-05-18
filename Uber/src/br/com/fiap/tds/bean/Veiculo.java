package br.com.fiap.tds.bean;

public class Veiculo extends Uber {
	
	//Atributos
	private String tipoVeiculo;
	private double taxaVeiculo;
	private double valorCorrida;
	
	//Construtores
	public Veiculo() {}
	
	public Veiculo(String tipoVeiculo) {
		enviaVeiculo(tipoVeiculo);
	}
	
	//Gets e Sets
	public String getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(String tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	public double getTaxaVeiculo() {
		return taxaVeiculo;
	}

	public void setTaxaVeiculo(double taxaVeiculo) {
		this.taxaVeiculo = taxaVeiculo;
	}
	
	public double getValorCorrida() {
		return valorCorrida;
	}

	public void setValorCorrida(double valorCorrida) {
		this.valorCorrida = valorCorrida;
	}

	//Override
	public void setValorCorrida() {
		valorCorrida = getTaxaUber() + getTaxaVeiculo()*getTaxaMotorista();
		super.setValorCorrida(valorCorrida);
	}
	
	//Métodos
	
	//Envia o tipo de veículo e a taxa
	protected void enviaVeiculo(String tipoVeiculo) {
		if (tipoVeiculo.equals("CARRO")) {
			this.taxaVeiculo = 1.5;
			this.tipoVeiculo = "Carro";
		}
		else if (tipoVeiculo.equals("MOTO")) {
			this.taxaVeiculo = 1.25;
			this.tipoVeiculo = "Moto";
		}
	}
}
