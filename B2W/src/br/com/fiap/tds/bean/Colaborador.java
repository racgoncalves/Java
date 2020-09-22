package br.com.fiap.tds.bean;

public class Colaborador {

	private String matricula;
	private String nome;
	private String email;
	private double salario;

	public Colaborador() {}

	public Colaborador(String nome, String email, double salario) {

		this.nome = nome;
		this.email = email;
		this.salario = salario;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

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

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "\nMatricula: " + matricula + "\n\n1 - Nome: " + nome + "\n2 - Email: " + email + "\n3 - Salario: " + salario;
	}
	
}
