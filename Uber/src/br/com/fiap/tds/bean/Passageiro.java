package br.com.fiap.tds.bean;

public class Passageiro extends Percurso{
	
	//Atributos
	private String nome;
	private int idade;
	
	//Construtores
	public Passageiro () {}
	
	public Passageiro (String tipoVeiculo, float distancia, String nome, int idade, String resposta) {
		super (tipoVeiculo, distancia);
		this.nome = nome;
		this.idade = idade;
		setTaxaMotorista(idade);
		setTaxaMotorista(resposta);
		setValorCorrida();
	}
	
	//Gets e Sets
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	//Override
	
	//Altera taxa do motorista pela idade
	private void setTaxaMotorista(int idade) {
		if (idade >= 65) {
			setTaxaMotorista(getTaxaMotorista() - 0.25);
		}
	}
	
	//Métodos
	
	//Retorna o resumo da corrida
	public String toString() {
		return "\nNome do passageiro: " + nome + "\nIdade do passageiro: " + idade + "\nTipo de veículo: " + getTipoVeiculo() + "\nDistância: " + getDistancia() + "km" + "\nValor da corrida: R$"  + getValorCorrida();
	}
}