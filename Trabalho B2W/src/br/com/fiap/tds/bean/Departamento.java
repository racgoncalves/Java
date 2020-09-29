package br.com.fiap.tds.bean;

import java.util.List;

/**
 * Classe que representa um departamento
 * 
 * @author Rodrigo Chiarelli
 * @version 1.0
 */
public class Departamento {

	private int codigo;
	private String nome;
	private List<String> lista;

	public Departamento() {
	}

	public Departamento(int codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<String> getLista() {
		return lista;
	}

	public void setLista(List<String> lista) {
		this.lista = lista;
	}

	public String toString() {
		return "\nCódigo do departamento: " + codigo + "\nNome do departamento: " + nome;
	}

}
