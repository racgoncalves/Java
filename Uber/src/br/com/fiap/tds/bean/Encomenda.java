package br.com.fiap.tds.bean;

public class Encomenda extends Percurso{
	
	//Atributos
	private String remetente;
	private float peso;
	private double taxaPeso;
	private double valorTotal;
	
	//Construtores
	public Encomenda () {}
	
	public Encomenda (String tipoVeiculo, float distancia, String remetente, float peso) {
		super (tipoVeiculo, distancia);
		this.remetente = remetente;
		this.peso = peso;
	}
	
	//Gets e Sets
	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public double getTaxaPeso() {
		return taxaPeso;
	}

	public void setTaxaPeso(double taxaPeso) {
		this.taxaPeso = taxaPeso;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	//Métodos
	
	//Envia os dados
	public void enviaEncomenda (String tipoVeiculo, float distancia, String remetente, float peso, String resposta) {
		enviaVeiculo(tipoVeiculo);
		setDistancia(distancia);
		this.remetente = remetente;
		this.peso = peso;
		montaTaxaPeso(peso);
		setTaxaMotorista(resposta);
		setValorCorrida();
		setValorTotal();
	}
	
	//Valida o peso/veículo
	public boolean validaPesoVeiculo(float peso, String tipoVeiculo) {
		if (tipoVeiculo.equals("MOTO")){
			return peso <= 50;
		}
		
		else {
			return peso <= 200;
		}
	}

	//Monta taxa do peso
	private void montaTaxaPeso(float peso) {
		this.taxaPeso = peso*2.5;
	}

	//Monta valor total
	public void setValorTotal() {
		valorTotal = getValorCorrida() +  + taxaPeso;
	}
	
	//Retorna resumo do envio
	public String toString() {
		return "\nNome do Remetente: " + remetente + "\nPeso do item: " + peso + "kg" + "\nTaxa do peso: R$" + taxaPeso + "\nDistância: " + getDistancia() + "km" + "\nTipo de veículo: " + getTipoVeiculo() + "\nValor da corrida: R$" + getValorCorrida() + "\nValor Total: R$" + valorTotal;
	}

}
