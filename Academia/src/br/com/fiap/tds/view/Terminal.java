package br.com.fiap.tds.view;

/**
 * Classe que define um terminal de academia
 * 
 * @author Rodrigo Chiarelli
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JOptionPane;

import br.com.fiap.tds.bean.Aluno;
import br.com.fiap.tds.bean.Matriculados;
import br.com.fiap.tds.bean.Pessoa;
import br.com.fiap.tds.bean.Plano;

public class Terminal {
	
	static public void main(String args[]) {
		
		//Inicia a classe Matriculados com um mapa e uma lista vazios
		HashMap<Integer,Aluno> mat = new HashMap<Integer,Aluno>();
		ArrayList<Integer> matr = new ArrayList<Integer>();
		Matriculados map = new Matriculados(mat, matr);

		//Atributos
		Aluno value = null;
		Set<Integer> keys = null;
		int matricula = 0;
		int key = 0;
		String nome = "";
		String email = "";
		String telefone = "";
		int idade = 0;
		int op = 0;
		int resp = 0;
		int menu = 0;
		int aux = 0;
		int cont = 0;
		String nomePlano = "";
		
		//Looping do Menu
		do {
			
			//Menu principal
			do {
				
				
				
				try {
					menu = Integer.parseInt(JOptionPane.showInputDialog("Sistema da Academia FIAP\n\n"
					+ "1 - Adicionar novo aluno\n"
					+ "2 - Buscar aluno pela matrícula\n"
					+ "3 - Buscar aluno pelo email\n"
					+ "4 - Mostrar todos os alunos\n"
					+ "5 - Remover aluno pela matricula\n"
					+ "6 - Sair do sistema\n\n"));
				}catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Opção inválida");
					menu = 0;
				}
			}while(menu == 0);
			
			//Início do Switch do Menu
			switch(menu) {
			
			
			
			//Adiciona aluno
			case 1:
				
				
				try {
					do {
						nome = JOptionPane.showInputDialog("Digite o nome do aluno:");
					}while(nome.isEmpty());
				}catch(NullPointerException e) {
					break;
				}
				
				try {
					do {
						email = JOptionPane.showInputDialog("Digite o email do aluno:");
					}while(email.isEmpty());
				}catch(NullPointerException e) {
					break;
				}
				
				try {
					do {
						telefone = JOptionPane.showInputDialog("Digite o telefone do aluno:");
					}while(telefone.isEmpty() || telefone.length() < 8 || telefone.length() > 11 || !telefone.matches("[0-9 ]+"));	
				}catch(NullPointerException e) {
					break;
				}
				
				do {
					try {
						idade = Integer.parseInt(JOptionPane.showInputDialog("Digite a idade do aluno:"));
					}catch(NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Opção inválida");
						idade = 0;
					}
				}while(idade == 0);
				
				//Looping do Plano
				do {
					
					do {
						try {
							op = Integer.parseInt(JOptionPane.showInputDialog("Escolha o plano da academia:\n"
								+ "1 - Mensal: R$100 / Mês\n"
								+ "2 - Trimestral: R$90 / Mês\n"
								+ "3 - Semestral: R$75 / Mês\n"
								+ "4 - Anual: R$60 / Mês\n\n"));
						}catch(NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Opção inválida");
							op = 0;
						}	
					}while(op == 0);
					
					//Início do Switch do Plano
					switch(op) {
					
					case 1:
						nomePlano = "MENSAL";
						break;
						
					case 2:
						nomePlano = "TRIMESTRAL";
						break;
						
					case 3:
						nomePlano = "SEMESTRAL";
						break;
						
					case 4:
						nomePlano = "ANUAL";
						break;
						
					default: JOptionPane.showMessageDialog(null, "Opção inválida"); 
					
					}
					
				}while(op < 1 || op > 4);
				
				//Cria as classes
				Pessoa pessoa = new Pessoa(nome,email,telefone,idade);
				Plano plano = new Plano(nomePlano);
				Aluno aluno = new Aluno(pessoa,plano);
				
				//Gera nº de matrícula
				matricula = map.geraMatricula();
				
				//Adiciona na classe do mapa
				map.getMatriculados().put(matricula, aluno);
				
				JOptionPane.showMessageDialog(null, "Aluno adicionado!\nNúmero de Matrícula: " + matricula + "\n\n");
				
				break;
				
			//Busca aluno pela matrícula
			case 2:
				key = 0;
				//Recebe e checa a chave
				
				try {
					key = Integer.parseInt(JOptionPane.showInputDialog("Digite a matrícula do aluno:"));
				}catch(NumberFormatException e) {		
					JOptionPane.showMessageDialog(null, "Opção inválida");
					if(key == 0) break;
				}
			
				
				if (map.getMatriculados().containsKey(key)) {
					
					//Recebe os valores da chave encontrada
					value = map.getMatriculados().get(key);
					
					//Mostra os valores
					aux = JOptionPane.showConfirmDialog(null, "Matrícula: " + key + "\n" + value.getPessoa() + value.getPlano() + "\n\nDeseja fazer alguma alteração?\n\n");
					if (aux == JOptionPane.YES_OPTION) {
						
						//Looping da alteração
						do {
							
							//Menu de alteração
							try {
								resp = Integer.parseInt(JOptionPane.showInputDialog("Escolha o item que deseja alterar:\n"
									+ "1 - Nome: "+ value.getPessoa().getNome() + "\n"
									+ "2 - Email: "+ value.getPessoa().getEmail() +"\n"
									+ "3 - Telefone: "+ value.getPessoa().getTelefone() +"\n"
									+ "4 - Idade :" + value.getPessoa().getIdade() + "\n"
									+ "5 - Plano :" + value.getPlano().getNomePlano() + "\n"
									+ "6 - Nenhuma alteração\n\n"));
							}catch(NumberFormatException e) {
								JOptionPane.showMessageDialog(null, "Opção inválida");
								resp = 0;
								break;
							}
							
							switch(resp) {
							
							case 1:
								nome = JOptionPane.showInputDialog("Digite o novo nome do aluno:");
								value.getPessoa().setNome(nome);
								break;
								
							case 2:
								email = JOptionPane.showInputDialog("Digite o novo email do aluno:");
								value.getPessoa().setEmail(email);
								break;
								
							case 3:
								telefone = JOptionPane.showInputDialog("Digite o novo telefone do aluno:");
								value.getPessoa().setTelefone(telefone);
								break;
								
							case 4:
								idade = Integer.parseInt(JOptionPane.showInputDialog("Digite a nova idade do aluno:"));
								value.getPessoa().setIdade(idade);
								break;
								
							case 5:
								
								//Looping do Plano
								do {
									
									do {
										try {
											op = Integer.parseInt(JOptionPane.showInputDialog("Escolha o novo plano da academia:\n"
												+ "1 - Mensal: R$100 / Mês\n"
												+ "2 - Trimestral: R$90 / Mês\n"
												+ "3 - Semestral: R$75 / Mês\n"
												+ "4 - Anual: R$60 / Mês\n\n"));
										}catch(NumberFormatException e) {
											JOptionPane.showMessageDialog(null, "Opção inválida");
											op = 0;
										}	
									}while(op == 0);
									
									switch(op) {
									
									case 1:
										nomePlano = "MENSAL";
										break;
										
									case 2:
										nomePlano = "TRIMESTRAL";
										break;
										
									case 3:
										nomePlano = "SEMESTRAL";
										break;
										
									case 4:
										nomePlano = "ANUAL";
										break;
										
									default: JOptionPane.showMessageDialog(null, "Opção inválida"); 
									
									}
									
								}while(op < 1 || op > 4);
								
								value.getPlano().setNomePlano(nomePlano);
								value.getPlano().geraDesconto(nomePlano);
								
								break;
								
							case 6:
								break;
								
							default: JOptionPane.showMessageDialog(null, "Opção inválida"); 
							
							}
							
						}while(resp != 6);
					}
				}
				else JOptionPane.showMessageDialog(null, "Aluno não encontrado");
				
				break;
				
			//Busca aluno pelo email
			case 3:
				
				//Recebe o email
				try {
					do {
						email = JOptionPane.showInputDialog("Digite o email do aluno:");
					}while(email.isEmpty());
				}catch(NullPointerException e) {
					break;
				}
				
				//Recebe todas as chaves
				keys = map.getMatriculados().keySet();
				
				//Zera o contador
				cont = 0;
				
				//Percorre as chaves
				for (Integer keyMap : keys) {
					
					//Recebe os valores da chave atual
					value = map.getMatriculados().get(keyMap);
					
					//Caso encontre o email
					if (value.getPessoa().getEmail().equals(email)) {
						
						//Confirma que o aluno foi encontrado
						cont ++;
						
						//Mostra os valores
						aux = JOptionPane.showConfirmDialog(null, "Matrícula: " + keyMap + "\n" + value.getPessoa() + value.getPlano() + "\n\nDeseja fazer alguma alteração?\n\n");
						if (aux == JOptionPane.YES_OPTION) {
								
							//Looping da alteração
							do {
								
								//Menu de alteração
								try {
									resp = Integer.parseInt(JOptionPane.showInputDialog("Escolha o item que deseja alterar:\n"
										+ "1 - Nome: "+ value.getPessoa().getNome() + "\n"
										+ "2 - Email: "+ value.getPessoa().getEmail() +"\n"
										+ "3 - Telefone: "+ value.getPessoa().getTelefone() +"\n"
										+ "4 - Idade: " + value.getPessoa().getIdade() + "\n"
										+ "5 - Plano: " + value.getPlano().getNomePlano() + "\n"
										+ "6 - Nenhuma alteração\n\n"));
								}catch(NumberFormatException e) {
									JOptionPane.showMessageDialog(null, "Opção inválida");
									resp = 0;
									break;
								}	
							
								switch(resp) {
								
								case 1:
									nome = JOptionPane.showInputDialog("Digite o novo nome do aluno:");
									value.getPessoa().setNome(nome);
									break;
									
								case 2:
									email = JOptionPane.showInputDialog("Digite o novo email do aluno:");
									value.getPessoa().setEmail(email);
									break;
									
								case 3:
									telefone = JOptionPane.showInputDialog("Digite o novo telefone do aluno:");
									value.getPessoa().setTelefone(telefone);
									break;
									
								case 4:
									idade = Integer.parseInt(JOptionPane.showInputDialog("Digite a nova idade do aluno:"));
									value.getPessoa().setIdade(idade);
									break;
									
								case 5:
									
									//Looping do Plano
									do {
										
										do {
											try {
												op = Integer.parseInt(JOptionPane.showInputDialog("Escolha o novo plano da academia:\n"
													+ "1 - Mensal: R$100 / Mês\n"
													+ "2 - Trimestral: R$90 / Mês\n"
													+ "3 - Semestral: R$75 / Mês\n"
													+ "4 - Anual: R$60 / Mês\n\n"));
											}catch(NumberFormatException e) {
												JOptionPane.showMessageDialog(null, "Opção inválida");
												op = 0;
											}	
										}while(op == 0);
										
										switch(op) {
										
										case 1:
											nomePlano = "MENSAL";
											break;
											
										case 2:
											nomePlano = "TRIMESTRAL";
											break;
											
										case 3:
											nomePlano = "SEMESTRAL";
											break;
											
										case 4:
											nomePlano = "ANUAL";
											break;
											
										default: JOptionPane.showMessageDialog(null, "Opção inválida"); 
										
										}
										
									}while(op < 1 || op > 4);
									
									value.getPlano().setNomePlano(nomePlano);
									value.getPlano().geraDesconto(nomePlano);
									
									break;
									
								case 6:
									break;
									
								default: JOptionPane.showMessageDialog(null, "Opção inválida"); 
								}
								
							}while(resp != 6);
						}
					}
				}
				
				if (cont == 0) JOptionPane.showMessageDialog(null, "Aluno não encontrado");
				
				break;
			
			//Mostra todos os alunos
			case 4:
				
				//Recebe todas as chaves
				keys = map.getMatriculados().keySet();
				
				//Percorre as chaves
				for (Integer keyMap : keys) {
					
					//Recebe os valores da chave atual e imprime
					value = map.getMatriculados().get(keyMap);
					System.out.println("Matrícula: " + keyMap + "\n" + value.getPessoa() + value.getPlano() + "\n");
				}

				break;
				
			//Remove aluno
			case 5:
				key = 0;
				//Recebe e checa a chave

				try {
					key = Integer.parseInt(JOptionPane.showInputDialog("Digite a matrícula do aluno:"));					
				}catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Opção inválida");
					if(key == 0) break;
				}

				
				if (map.getMatriculados().containsKey(key)) {
					
					//Recebe os valores da chave encontrada
					value = map.getMatriculados().get(key);
					
					//Mostra os valores
					aux = JOptionPane.showConfirmDialog(null, "Matrícula: " + key + "\n" + value.getPessoa() + value.getPlano() + "\n\nTem certeza que deseja remover o aluno?\n\n");
					if (aux == JOptionPane.YES_OPTION) {
						
						//Remove a chave com seus valores
						map.getMatriculados().remove(key);
						JOptionPane.showMessageDialog(null, "Aluno removido do sistema");
					}
				}
				else JOptionPane.showMessageDialog(null, "Aluno não encontrado");	
				
				break;
			
			//Sai do programa
			case 6:
				JOptionPane.showMessageDialog(null, "Finalizando o programa...");
				break;
			
			//Opção inválida
			default: JOptionPane.showMessageDialog(null, "Opção inválida");
			
			}
			
		}while(menu != 6);	
		
	}
}
