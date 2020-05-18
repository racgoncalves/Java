package br.com.fiap.tds.bean;

public class Produto {
	
	private String nome;
	private double preco;
	private double precoVista;
	private int codigo;
	private boolean disponivel;
	private double multiplicadorDesconto;
	private double desconto;
	
	public Produto() {}
	
	public Produto(String nome, double preco) {
		setNome(nome);
		this.preco = preco;
		disponivel = true;
		codigo ++;
	}
	
	public void setNome(String nome) {
		nome = nome.toUpperCase();
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public double getPreco() {
		return preco;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void porcentagemDesconto(double desconto) {
		multiplicadorDesconto = desconto/100;
		this.desconto = multiplicadorDesconto*preco;
		precoVista = preco - this.desconto;		
	}
	
	public String toString() {
		return "Código: " + codigo + "\n" + "Nome: " + nome + "\n" + "Preço a prazo: R$" + preco + "\n" + "Preço à vista: R$" + precoVista + "\n" + "Valor do desconto: R$" + desconto + "\n" + "Disponibilidade: " + disponivel;
	}
}
