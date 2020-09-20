package br.com.fiap.tds.bean;

/**
 * Classe que define uma pessoa
 * 
 * @author Rodrigo Chiarelli
 * @version 1.0
 */

public class Pessoa extends Aluno{
	
	/**
	 * Define o nome da pessoa
	 */
	private String nome;
	
	/**
	 * Define o email da pessoa
	 */
	private String email;
	
	/**
	 * Define o telefone da pessoa
	 */
	private String telefone;
	
	/**
	 * Define a idade da pessoa
	 */
	private int idade;
	
	/**
	 * Cria uma nova pessoa definindo valores nos atributos
	 * @param nome da pessoa
	 * @param email da pessoa
	 * @param telefone da pessoa
	 * @param idade da pessoa
	 */
	public Pessoa(String nome, String email, String telefone, int idade) {
		super();
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.idade = idade;
	}
	
	/**
	 * Inicializa uma classe Pessoa vazia
	 */
	public Pessoa() {}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	@Override
	public String toString() {
		return "Nome: " + nome + "\nEmail: " + email + "\nTelefone: " + telefone + "\nIdade: " + idade;
	}

}
