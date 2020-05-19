package br.com.fiap.tds.bean;

public class Conta{
	
	private String usuario;
	private String tipo;
	private int senha;
	private double chequeEspecial;
	private double taxaSaque;
	private boolean bloqueado = false;
	private double saldo;
	private String extrato = "";
	
	public Conta(String usuario, String tipo,int senha) {
		this.usuario = usuario;	
		this.senha = senha;		
		this.tipo = tipo;
		
		if(tipo.equalsIgnoreCase("estudante")) {
			chequeEspecial = 250;
			taxaSaque = 0;
		}
		else if(tipo.equalsIgnoreCase("normal")){
			chequeEspecial = 500;
			taxaSaque = 10;
		}
		else {
			chequeEspecial = 750;
			taxaSaque = 5;
		}		
	}
	
	//Gets
	public boolean testaSenha(int senha) {
		return this.senha == senha;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public double getChequeEspecial() {
		return chequeEspecial;
	}
	
	public double getSaldoDisponivel() {
		return saldo + chequeEspecial;
	}
	
	public double getTaxaSaque() {
		return taxaSaque;
	}
	
	public String getExtrato() {
		return extrato;
	}
	
	public boolean isBloqueado() {
		return bloqueado;
	}
	
	//Bloqueia conta
	public void bloqueia() {
		this.bloqueado = true;
	}
	
	//Testa usuário
	public boolean testaUsuario(String usuario) {
		return this.usuario.equals(usuario);
	}
	
	//Deposita
	public void depositar(double valor) {
		this.saldo += valor;
		extrato += "\n" + "Depositado R$" + valor;
	}
	
	//Saca
	public void retirar(double valor) {	
		extrato += "\n" + "Sacado R$" + valor;
		valor += taxaSaque;			
		this.saldo -= valor;
	}
	
}
