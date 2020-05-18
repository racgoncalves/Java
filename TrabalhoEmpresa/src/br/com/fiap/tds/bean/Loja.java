package br.com.fiap.tds.bean;

public class Loja {
	
	private int codigo;
	public String estado;
	private String cidade;
	private float tamanho;
	private int numFuncionarios;
	
	public Loja(int codigo, String cidade) {
		this.codigo = codigo;
		setCidade(cidade);
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setEstado(String estado) {
		estado = estado.toUpperCase();
		this.estado = estado;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setCidade(String cidade) {
		cidade = cidade.toUpperCase();
		this.cidade = cidade;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setTamanhoLoja(float tamanho) {
		this.tamanho = tamanho;
	}
	
	public double getTamanhoLoja() {
		return tamanho;
	}
	
	public void setNumFuncionarios(int numero) {
		numFuncionarios = numero;
	}
	
	public int getNumFuncionarios() {
		return numFuncionarios;
	}
	
	public void novoFuncionario() {
		numFuncionarios++;
	}
	
	public String toString() {
		return "Código: " + codigo + "\n" + "Estado: " + estado + "\n" + "Cidade: " + cidade + "\n" + "Tamanho: " + tamanho +"m²" + "\n" + "Nº de funcionários: " + numFuncionarios;
	}

}
