package br.com.fiap.tds.bean;

/**
 * Classe que representa um colaborador
 * 
 * @author Rodrigo Chiarelli
 * @version 1.0
 */
public class Colaborador {

	private int matricula;
	private Departamento departamento;
	private String apelido;
	private String email;
	private boolean cadastrado;

	public Colaborador() {
	}

	public Colaborador(Departamento departamento, String apelido, String email) {

		this.departamento = departamento;
		this.apelido = apelido;
		this.email = email;
	}

	// Gets e Sets
	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isCadastrado() {
		return cadastrado;
	}

	public void setCadastrado(boolean cadastrado) {
		this.cadastrado = cadastrado;
	}

	// Mostra os dados principais do colaborador (Terminal RH - Menu Edição)
	public String toString() {
		return "\nMatricula: " + matricula + "\n\n1 - Departamento: " + departamento.getNome() + "\n2 - Apelido: "
				+ apelido + "\n3 - Email: " + email;
	}

	// Mostra os dados principais do colaborador (Terminal RH - Lista com todos os
	// colaboradores)
	public String toStringLista() {
		return "\nMatricula: " + matricula + "\n\nDepartamento: " + departamento.getNome() + "\nApelido: "
				+ apelido + "\nEmail: " + email;
	}

}
