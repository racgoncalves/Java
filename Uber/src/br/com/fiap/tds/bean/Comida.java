package br.com.fiap.tds.bean;

public class Comida extends Percurso{
	
	//Atributos
	private String restaurante;
	private String itemRestaurante;
	private String itensRestaurante;
	private double valorItem;
	private int quantidade;
	private double valorItens;
	private double valorTotal;
	
	
	//Construtores
	public Comida () {
		super();
	}
	
	public Comida (String tipoVeiculo, float distancia, String restaurante, String itemRestaurante) {
		super (tipoVeiculo, distancia);
		this.restaurante = restaurante;
		this.itemRestaurante = itemRestaurante;
	}
	
	//Gets e Sets
	public String getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(String restaurante) {
		this.restaurante = restaurante;
	}

	public String getItemRestaurante() {
		return itemRestaurante;
	}

	public void setItemRestaurante(String itemRestaurante) {
		this.itemRestaurante = itemRestaurante;
	}
	
	public String getItensRestaurante() {
		return itensRestaurante;
	}

	public void setItensRestaurante(String itensRestaurante) {
		this.itensRestaurante = itensRestaurante;
	}

	public double getValorItem() {
		return valorItem;
	}

	public void setValorItem(double valorItem) {
		this.valorItem = valorItem;
	}
	
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValorItens() {
		return valorItens;
	}

	public void setValorItens(double valorItens) {
		this.valorItens = valorItens;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	//Valida a distância
	public boolean validaDistancia (float distancia) {
		return distancia <= 30;
	}

	//Seleciona o restaurante e mostra os itens
	public String itensRestaurante(String codigoRestaurante) {
		switch(codigoRestaurante) {
			case "1":
				restaurante = "Lanchonete Big Dog";
				return "\n1 - Dogão Simples: R$8\n2 - Dogão Duplo: R$13\n3 - Dogão Completo: R$18\n\n";
			case "2":
				restaurante = "Pizzaria La Italiana";
				return "\n1 - Pizza Margherita: R$25\n2 - Pizza Calabresa: R$30\n3 - Pizza Portuguesa: R$35\n\n";
			default:
				restaurante = "Temakeria Tokyo";
				return "\n1 - Temaki de Pepino: R$10\n2 - Temaki de Salmão: R$15\n3 - Temaki de Camarão: R$20\n\n";
		}
	}
	
	//Seleciona os itens e os valores
	public void itensRestaurante (String codigoItem, String codigoRestaurante) {
		switch(codigoRestaurante) {
			case "1":
				switch(codigoItem) {
					case "1":
						itemRestaurante = "Dogão Simples";
						valorItem = 8;
						break;
					case "2":
						itemRestaurante = "Dogão Duplo";
						valorItem = 13;
						break;
					default:
						itemRestaurante = "Dogão Completo";
						valorItem = 18;
				}
				break;
				
			case "2":
				switch(codigoItem) {
					case "1":
						itemRestaurante = "Pizza Margherita";
						valorItem = 25;
						break;
					case "2":
						itemRestaurante = "Pizza Calabresa";
						valorItem = 30;
						break;
					default:
						itemRestaurante = "Pizza Portuguesa";
						valorItem = 35;
				}
				break;
				
			default:
				switch(codigoItem) {
					case "1":
						itemRestaurante = "Temaki de Pepino";
						valorItem = 10;
						break;
					case "2":
						itemRestaurante = "Temaki de Salmão";
						valorItem = 15;
						break;
					default:
						itemRestaurante = "Temaki de Camarão";
						valorItem = 20;
				}
				
		}
	}
	
	//Soma o valor dos itens
	public void setValorItens() {
		this.valorItens += valorItem*quantidade;
	}
	
	//Envia os dados
	public void enviaComida (String tipoVeiculo, float distancia, String resposta) {
		enviaVeiculo(tipoVeiculo);
		setDistancia(distancia);
		setTaxaMotorista(resposta);
		setValorCorrida();
		setValorTotal();
	}
	
	//Monta o valor total
	public void setValorTotal() {
		valorTotal = getValorCorrida() +  + valorItens;
	}
	
	//Retorna o resumo da compra
	public String toString() {
		return "\nNome do Restaurante: " + restaurante + "\nOs Itens são: " + itensRestaurante + "\nDistância: " + getDistancia() + "km" + "\nTipo de veículo: " + getTipoVeiculo() + "\nValor dos itens: R$" + valorItens + "\nValor da corrida: R$" + getValorCorrida() + "\nValor Total: R$" + valorTotal;
	}

}
