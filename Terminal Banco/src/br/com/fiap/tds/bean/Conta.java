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
			setChequeEspecial(250);
			this.taxaSaque = 0;
		}
		else if(tipo.equalsIgnoreCase("normal")){
			setChequeEspecial(500);
			this.taxaSaque = 10;
		}
		else if(tipo.equalsIgnoreCase("aposentado")){
			setChequeEspecial(750);
			this.taxaSaque = 5;
		}		
	}
	
	public boolean testaSenha(int senha) {
		if (this.senha == senha) {
			return true;
		}
		else {
			return false;
		}
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
	
	public boolean getBloqueado() {
		return bloqueado;
	}
	
	public void bloqueia() {
		this.bloqueado = true;
	}
	
	public boolean testaUsuario(String usuario) {
		if (this.usuario.equals(usuario)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private void setChequeEspecial(double chequeEspecial) {
		this.chequeEspecial = chequeEspecial;
	}
	
	public void depositar(double valor) {
		this.saldo += valor;
		extrato += "\n" + "Depositado R$" + valor;
	}
	
	public void retirar(double valor) {	
		extrato += "\n" + "Sacado R$" + valor;
		valor += taxaSaque;			
		this.saldo -= valor;
	}
	
}
