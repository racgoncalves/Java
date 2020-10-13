package br.com.fiap.tds.bean;

/**
 * Classe que representa um login
 * 
 * @author Rodrigo Chiarelli
 * @version 4.0
 */
public class Login {

	private int matricula;
	private String email;
	private String senha;
	private String apelido;
	private boolean finalizado;

	public Login() {
	}

	public Login(int matricula, String email, String senha, String apelido, boolean finalizado) {

		this.matricula = matricula;
		this.email = email;
		this.senha = senha;
		this.apelido = apelido;
		this.finalizado = finalizado;
	}

	public Login(String email, String senha, String apelido, boolean finalizado) {

		this.email = email;
		this.senha = senha;
		this.apelido = apelido;
		this.finalizado = finalizado;
	}

	// Gets e Sets
	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	// Mostra todos os dados de login
	public String toString() {

		return "\n\n16 - E-mail: " + email + "\n17 - Senha: " + senha + "\n18 - Apelido: " + apelido;
	}

}// Classe
