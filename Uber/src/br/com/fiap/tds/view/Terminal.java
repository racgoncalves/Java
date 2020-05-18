package br.com.fiap.tds.view;

import javax.swing.JOptionPane;

import br.com.fiap.tds.bean.Comida;
import br.com.fiap.tds.bean.Encomenda;
import br.com.fiap.tds.bean.Passageiro;

public class Terminal {

	public static void main(String[] args) {
		
		//Atributos
		String opcao = "";
		String nome = "";
		String tipoVeiculo = "";
		float distancia = 0;
		int idade = 0;
		String resposta = "";
		String codigoRestaurante = "";
		String codigoItem = "";
		int quantidade = 0;
		String itensRestaurante = "";
		String remetente = "";
		float peso = 0;
		
		//Menu
		do {
			opcao = JOptionPane.showInputDialog(null,"UBER\n\nEscolha uma op��o:\n\n1 - Viagem de Passageiro\n2 - Entrega de Comida\n3 - Transporte de Encomenda\n\n");
		} while (!opcao.equals("1") && !opcao.equals("2") && !opcao.equals("3"));
		
		switch(opcao) {
			
			// <<<<<<<<<<<<<<<<<< Passageiro >>>>>>>>>>>>>>>>>>
			case "1":
				
				//Nome
				do {
					nome = JOptionPane.showInputDialog("Digite o seu nome");
				} while(nome.isEmpty());
				
				//Idade
				do {
					try {
						idade = Integer.parseInt(JOptionPane.showInputDialog("Digite a sua idade"));
					}catch(NumberFormatException e){
						JOptionPane.showMessageDialog(null,"Digite apenas n�meros!");
						idade = 0;
					}
				} while (idade <= 0);
				
				//Tipo de Ve�culo
				do {
					tipoVeiculo = JOptionPane.showInputDialog("Digite o tipo de ve�culo (CARRO ou MOTO)");
					tipoVeiculo = tipoVeiculo.toUpperCase();
				} while(!tipoVeiculo.equals("CARRO") && !tipoVeiculo.equals("MOTO"));
				
				//Dist�ncia
				do {
					try {
						distancia = Float.parseFloat(JOptionPane.showInputDialog("Digite a dist�ncia em km"));
					}catch(NumberFormatException e){
						JOptionPane.showMessageDialog(null,"Digite apenas n�meros!");
						distancia = 0;
					}
				} while (distancia <= 0);
				
				//Outra Cidade
				do {
					resposta = JOptionPane.showInputDialog("A corrida ir� para outra cidade? (Digite 'S' para sim ou 'N' para n�o)");
					resposta = resposta.toUpperCase();
				} while(!resposta.equals("S") && !resposta.equals("N"));
				
				//Cria o passageiro
				Passageiro passageiro = new Passageiro (tipoVeiculo, distancia, nome, idade, resposta);
				
				//Monta o valor da corrida
				passageiro.setValorCorrida();
				
				//Resumo da Corrida
				JOptionPane.showMessageDialog(null,"Segue o resumo da sua corrida\n" + passageiro);
				
				break;
			
			// <<<<<<<<<<<<<<<<<< Comida >>>>>>>>>>>>>>>>>>
			case "2":
				
				//Cria a Comida
				Comida comida = new Comida();
				
				//Restaurante
				do {
					codigoRestaurante = JOptionPane.showInputDialog("Digite o c�digo do restaurante\n\n1 - Lanchonete Big Dog\n2 - Pizzaria La Italiana\n3 - Temakeria Tokyo\n\n");
				} while(!codigoRestaurante.matches("[1-3]"));
				
				//Itens Restaurante
				do {
					
					//Item Restaurante
					do {
						codigoItem = JOptionPane.showInputDialog("Digite o c�digo do item\n" + comida.itensRestaurante(codigoRestaurante));
					} while(!codigoItem.matches("[1-3]"));
					
					//Envia item e restaurante
					comida.itensRestaurante(codigoItem, codigoRestaurante);
					
					//Quantidade
					do {
						try {
							quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de " + comida.getItemRestaurante()));
						}catch(NumberFormatException e){
							JOptionPane.showMessageDialog(null,"Digite apenas n�meros!");
							quantidade = 0;
						}
					} while (quantidade <= 0);
					
					//Envia quantidade e valor
					comida.setQuantidade(quantidade);
					comida.setValorItens();
					
					//Monta os itens
					if (!itensRestaurante.contains(comida.getItemRestaurante())){
					
					itensRestaurante += quantidade + " " + comida.getItemRestaurante().concat(", ");
					}
					
					else {
						int aux = Integer.parseInt(itensRestaurante.substring(itensRestaurante.indexOf(comida.getItemRestaurante())-2,itensRestaurante.indexOf(comida.getItemRestaurante())-1));
						aux += quantidade;
						itensRestaurante = itensRestaurante.substring(0,itensRestaurante.indexOf(comida.getItemRestaurante())-2).concat(String.valueOf(aux)).concat(itensRestaurante.substring(itensRestaurante.indexOf(comida.getItemRestaurante())-1));
					}
				
					//Looping Itens Restaurante
					do {
						resposta = JOptionPane.showInputDialog("Deseja adicionar mais algum item?\n(Digite 'S' para sim ou 'N' para n�o)");
						resposta = resposta.toUpperCase();
					} while (!resposta.equals("S") && !resposta.equals("N"));
				} while(resposta.equals("S"));
				
				//Envia os itens
				itensRestaurante = itensRestaurante.substring(0,itensRestaurante.length()-2);
				comida.setItensRestaurante(itensRestaurante);
					
				//Tipo de Ve�culo
				do {
					tipoVeiculo = JOptionPane.showInputDialog("Digite o tipo de ve�culo (CARRO ou MOTO)");
					tipoVeiculo = tipoVeiculo.toUpperCase();
				} while(!tipoVeiculo.equals("CARRO") && !tipoVeiculo.equals("MOTO"));
				
				//Dist�ncia
				do {
					try {
						distancia = Float.parseFloat(JOptionPane.showInputDialog("Digite a dist�ncia em km"));
					}catch(NumberFormatException e){
						JOptionPane.showMessageDialog(null,"Digite apenas n�meros!");
						distancia = 0;
					}
					if(distancia > 30) {
						JOptionPane.showMessageDialog(null,"N�o entregamos comida nessa dist�ncia!\nDist�ncia m�xima de 30km");
					}
				} while (distancia <= 0 || !comida.validaDistancia(distancia));
				
				//Outra Cidade
				do {
					resposta = JOptionPane.showInputDialog("A encomenda ir� para outra cidade?\n(Digite 'S' para sim ou 'N' para n�o)");
					resposta = resposta.toUpperCase();
				} while(!resposta.equals("S") && !resposta.equals("N"));
				
				//Envia dados do ve�culo e da dist�ncia
				comida.enviaComida(tipoVeiculo, distancia, resposta);
				
				//Resumo da Compra
				JOptionPane.showMessageDialog(null,"Segue o resumo da sua compra\n" + comida);
				
				break;
				
			// <<<<<<<<<<<<<<<<<< Encomenda >>>>>>>>>>>>>>>>>>
			default:
				
				//Cria a Encomenda
				Encomenda encomenda = new Encomenda();
				
				//Nome
				do {
					remetente = JOptionPane.showInputDialog("Digite o nome do remetente");
				} while(remetente.isEmpty());
				
				do {
					
					//Tipo de Ve�culo
					do {
						tipoVeiculo = JOptionPane.showInputDialog("Digite o tipo de ve�culo (CARRO ou MOTO)");
						tipoVeiculo = tipoVeiculo.toUpperCase();
					} while(!tipoVeiculo.equals("CARRO") && !tipoVeiculo.equals("MOTO"));
					
					//Peso
					do {
						try {
							peso = Float.parseFloat(JOptionPane.showInputDialog("Digite o peso do produto em kg (digite 1 se possuir menos de 1kg)"));
						}catch(NumberFormatException e){
							JOptionPane.showMessageDialog(null,"Digite apenas n�meros!");
							peso = 0;
						}
					} while(peso < 1);
					
					//Valida Ve�culo/Peso
					if (!encomenda.validaPesoVeiculo(peso, tipoVeiculo)){
						JOptionPane.showMessageDialog(null, "O ve�culo n�o suporta o peso do produto\nPeso m�ximo para motos: 50kg\nPeso m�ximo para carros: 200kg");
					}
					
				//Valida peso e ve�culo
				} while(!encomenda.validaPesoVeiculo(peso, tipoVeiculo));
					
				//Dist�ncia
				do {
					try {
						distancia = Float.parseFloat(JOptionPane.showInputDialog("Digite a dist�ncia em km"));
					}catch(NumberFormatException e){
						JOptionPane.showMessageDialog(null,"Digite apenas n�meros!");
						distancia = 0;
					}
				} while (distancia <= 0);
				
				//Outra Cidade
				do {
					resposta = JOptionPane.showInputDialog("A encomenda ir� para outra cidade? (Digite 'S' para sim ou 'N' para n�o)");
					resposta = resposta.toUpperCase();
				} while(!resposta.equals("S") && !resposta.equals("N"));
				
				//Envia os dados
				encomenda.enviaEncomenda(tipoVeiculo, distancia, remetente, peso, resposta);
				
				//Resumo do Envio
				JOptionPane.showMessageDialog(null,"Segue o resumo do seu envio\n" + encomenda);
				
		}
	}
}
