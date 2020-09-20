package br.com.fiap.tds.bean;

/**
 * Classe que define os matriculados de uma academia
 * 
 * @author Rodrigo Chiarelli
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Matriculados {
	
	private HashMap<Integer,Aluno> matriculados;
	private ArrayList<Integer> matriculas;
	
	public Matriculados() {}
	
	public Matriculados(HashMap<Integer,Aluno> matriculados, ArrayList<Integer> matr) {
		this.matriculados = matriculados;
		this.matriculas = matr;
	}

	public HashMap<Integer, Aluno> getMatriculados() {
		return matriculados;
	}

	public void setMatriculados(HashMap<Integer, Aluno> matriculados) {
		this.matriculados = matriculados;
	}
	
	public ArrayList<Integer> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(ArrayList<Integer> matriculas) {
		this.matriculas = matriculas;
	}

	/**
	 * @return matricula um novo número de matrícula ainda não utilizado
	 */
	public int geraMatricula() {
		int matricula = 0;
		if (this.matriculas.isEmpty()) {
			matricula = 1000;
			this.matriculas.add(matricula);
		}
		else {
			matricula = this.matriculas.get(this.matriculas.size() -1) + 1;
			this.matriculas.add(matricula);
		}
		return matricula;
	}
	
}
