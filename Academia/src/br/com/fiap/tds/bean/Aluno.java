package br.com.fiap.tds.bean;

/**
 * Classe que define um aluno de academia
 * 
 * @author Rodrigo Chiarelli
 * @version 1.0
 */

public class Aluno {
	
	private Pessoa pessoa;
	private Plano plano;
	
	/**
	 * Inicializa um aluno com uma pessoa e um plano da academia
	 * @param pessoa 
	 * @param plano 
	 */
	public Aluno(Pessoa pessoa, Plano plano) {
		this.pessoa = pessoa;
		this.plano = plano;
	}

	public Aluno() {}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}
	
}
