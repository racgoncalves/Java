package br.com.fiap.tds.bean;

public class Funcionario {
	
	private String nome;
	private int idade;
	private String departamento;
	private double salario;
	private boolean autorizacao = false;
	private String senha = "abc123";
	
	public Funcionario() {}
	
	private Funcionario(String nome, double salario) {
		this.nome = nome;
		this.salario = salario;
	}
	
	public Funcionario(String nome, int idade, String departamento, double salario) {
		this(nome,salario);
		this.idade = idade;
		setDepartamento(departamento);
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setDepartamento(String departamento) {
		departamento = departamento.toUpperCase();
		this.departamento = departamento;
	}
	
	public String getDepartamento() {
		return departamento;
	}
	
	public double getSalario() {
		return salario;
	}
	
	public String toString() {
		return "Nome: " + nome + "\n" + "Idade: " + idade + " anos" + "\n" + "Departamento: " + departamento + "\n" + "Salário: R$" + salario;
	}
	
	private void testaSenha(String senha) {
		if (senha.equals(this.senha)) {
			autorizacao = true;
		}
	}
	
	public void setAumentaSalario(double valor, String senha) {
		testaSenha(senha);
		if (autorizacao == true) {	
			salario += valor;
		}
		autorizacao = false;	
	}
}
