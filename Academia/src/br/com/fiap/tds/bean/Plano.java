package br.com.fiap.tds.bean;

/**
 * Classe que define um plano de academia
 * 
 * @author Rodrigo Chiarelli
 * @version 1.0
 */

public class Plano extends Aluno {
	
	private String nomePlano;
	private double desconto;
	private double mensalidade;
	
	/**
	 * Armazena o valor da mensalidade sem desconto
	 */
	private static final int MES = 100;
	
	public Plano(String nomePlano) {
		super();
		this.nomePlano = nomePlano;
		geraDesconto(nomePlano);
	}
	
	public Plano() {}

	public String getNomePlano() {
		return nomePlano;
	}

	public void setNomePlano(String nomePlano) {
		this.nomePlano = nomePlano;
	}
	
	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	
	public double getMensalidade() {
		return mensalidade;
	}
	
	public void setMensalidade(double mensalidade) {
		this.mensalidade = mensalidade;
	}
	
	/**
	 * @return MES valor de uma mensalidade sem desconto
	 */
	public static double getMes() {
		return MES;
	}
	
	/**
	 * Calcula a mensalidade com o parâmetro desconto
	 * @param desconto da mensalidade
	 */
	protected void calculaMensalidade(double desconto) {
		this.mensalidade = getMes() - getMes()*desconto;
	}

	/**
	 * Define o desconto da mensalidade com o parâmetro nomePlano
	 * @param nomePlano nome do plano da academia
	 */
	public void geraDesconto(String nomePlano) {
		switch(nomePlano) {
		
			case "MENSAL":
				desconto = 0;
				break;
		
			case "TRIMESTRAL":
				desconto = 0.1;
				break;
			
			case "SEMESTRAL":
				desconto = 0.25;
				break;
		
			case "ANUAL":
				desconto = 0.4;
				break;
		}
		
		calculaMensalidade(desconto);
	}
	
	@Override
	public String toString() {
		return "\nPlano: " + nomePlano + "\nMensalidade: R$" + mensalidade;
	}
	
	
}
