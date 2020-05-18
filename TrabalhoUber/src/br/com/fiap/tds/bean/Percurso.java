package br.com.fiap.tds.bean;

public class Percurso extends Veiculo{
	
	//Atributos
	private float distancia;
	private double valorCorrida;
	
	//Construtores
	public Percurso() {}
	
	public Percurso (String tipoVeiculo, float distancia) {
		super(tipoVeiculo);
		this.distancia = distancia;	
	}
	
	//Gets e Sets
	public float getDistancia() {
		return distancia;
	}

	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}
	
	public double getValorCorrida() {
		return valorCorrida;
	}

	public void setValorCorrida(double valorCorrida) {
		this.valorCorrida = valorCorrida;
	}
	
	//Override
	public void setValorCorrida() {
		valorCorrida = getTaxaUber() + getTaxaVeiculo()*getTaxaMotorista()*getDistancia();
		super.setValorCorrida(valorCorrida);
	}
}
