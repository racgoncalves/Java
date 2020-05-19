package br.com.fiap.tds.view;

import java.util.InputMismatchException;
import java.util.Scanner;
import br.com.fiap.tds.bean.Conta;

public class TestandoConta {
	
public static void main(String args[]) {
		
		int cont;
		String usuario = null;
		int opcao = 0;
		int senha = 0;
		int looping = 0;
		boolean teste = false;
		
		Scanner sc = new Scanner(System.in);
		
		// <<<<<<<<< Cadastro >>>>>>>>>
		
		//Usu�rio
		do {			
			
			System.out.println("Digite o seu usu�rio da conta corrente:");
			usuario = sc.next();	
			
		}while (usuario.isEmpty());
		
		//Tipo
		System.out.println("Digite o tipo da conta (Estudante, Normal ou Aposentado):");
		String tipo = sc.next();
		while(!tipo.equalsIgnoreCase("estudante") && !tipo.equalsIgnoreCase("normal") && !tipo.equalsIgnoreCase("aposentado")){
			System.out.println("Tipo de conta inv�lido");
			System.out.println("Digite o tipo da conta (Estudante, Normal ou Aposentado):");
			tipo = sc.next();	
		}
		
		//Senha
		do {
			
			try {
				System.out.println("Digite uma senha de 4 n�meros:");
				senha = sc.nextInt();
				while (senha > 9999 || senha < 1000) {
					System.out.println("A senha deve ter 4 n�meros, favor digitar novamente:");
					senha = sc.nextInt();
				}
			}catch(InputMismatchException e){
				System.err.println("\nA T E N � � O\n\nDigite somente n�meros!!\n");
				sc.nextLine();
				senha = 0;
			}
			
		}while (senha == 0);
		
		Conta cc = new Conta(usuario,tipo.toUpperCase(),senha);
		System.out.println("\nCadastro realizado com sucesso!!");
		
		//Looping
		do {
		
		// <<<<<<<<< Login >>>>>>>>>
		
		//Testa Usu�rio
		do {
			
			System.out.println("\nFa�a o Login para realizar opera��es");
			System.out.println("Digite o usu�rio da conta corrente:");
			usuario = sc.next();
		
			if (!cc.testaUsuario(usuario)) {
			System.out.println("Usu�rio Inv�lido");
			}
		
		}while (!cc.testaUsuario(usuario));
		
		if (cc.testaUsuario(usuario) == true && cc.isBloqueado() == true) {
			System.out.println("Essa conta est� bloqueda!!");
		}
		
		//Testa Senha
		else if (cc.testaUsuario(usuario) == true && cc.isBloqueado() == false) {
		
			System.out.println("Digite a senha de 4 n�meros:");
			senha = sc.nextInt();
			teste = cc.testaSenha(senha);
		
			cont = 3;
			while (!teste) {
				if (cont > 1) {
					System.out.println("Senha incorreta!\nVoc� tem mais " + cont + " tentativas!");
				} else {
					System.out.println("� sua �ltima tentativa!\nSe errar a conta ser� bloqueada!");
				}
				senha = sc.nextInt();
				teste = cc.testaSenha(senha);
				cont -= 1;
				if(teste == false && cont == 0) {
					cc.bloqueia();
					System.out.println("Conta Bloqueada!!");
					break;
				}
			}
		}
		
		// <<<<<<<<< Acesso >>>>>>>>>
		if (teste) {
			
			System.out.println("\nAcesso liberado!!");
			
		//Menu
		do	{
			
			System.out.println("\nDigite 1 para depositar");
			System.out.println("Digite 2 para sacar");
			System.out.println("Digite 3 para ver o saldo");
			System.out.println("Digite 4 para ver o extrato");
			System.out.println("Digite 5 para ver os dados da conta");
			System.out.println("Digite 6 para sair");
			opcao = sc.nextInt();

		//Depositar
		if (opcao == 1) {
			
			System.out.println("\nDigite o valor a ser depositado:");
			double deposito = sc.nextDouble();
			while (deposito < 0) {
				System.out.println("Valor Inv�lido");
				System.out.println("Digite o valor a ser depositado:");
				deposito = sc.nextDouble();
			}
		
			if (teste) {
				if (deposito == 0) {
					System.out.println("Nenhum valor depositado");
				}
				else {
					System.out.println("Depositado o valor de R$" + deposito);
					cc.depositar(deposito);	
				}			
			}		
		}
		
		//Sacar
		if (opcao == 2) {
			
			System.out.println("\nDigite o valor a ser retirado:");
			double saque = sc.nextDouble();
			
			while (saque < 0) {
				System.out.println("Valor Inv�lido");
				System.out.println("Digite o valor a ser retirado:");
				saque = sc.nextDouble();
			}
			
			if (saque == 0) {
				System.out.println("Nenhum valor ser� sacado");
			}
			
			else if (saque + cc.getTaxaSaque() > cc.getSaldoDisponivel()) {
				System.out.println("Saldo Insuficiente");	
			}
			
			else {
				cc.retirar(saque);
				System.out.println("Favor retirar o valor de R$" + saque);
				System.out.println("Taxa de Saque: R$" + cc.getTaxaSaque());
			}			
		}
		
		//Saldo
		if (opcao == 3) {
			
		System.out.println("\nSaldo na conta.......R$" + cc.getSaldo());
		System.out.println("Saldo total..........R$" + cc.getSaldoDisponivel());
		
		}
		
		//Extrato
		if (opcao == 4) {
			System.out.println(cc.getExtrato());
		}
		
		//Dados
		if (opcao == 5) {
			
		System.out.println("\nUsu�rio da conta............" + cc.getUsuario());
		System.out.println("Tipo da conta..............." + cc.getTipo());
		System.out.println("Limite do cheque especial...R$" + cc.getChequeEspecial());
		System.out.println("Taxa de saque...............R$" + cc.getTaxaSaque());
		
		}
		
		//Sair
		if (opcao == 6) {
			System.out.println("Opera��o encerrada!!");
		}
		
		//Fim Menu
		}while(opcao != 6);
			
		//Fim Acesso
		} 
		
		//Fim Looping
		}while(looping == 0);
		
		//Encerra Leitor
		sc.close();
		
	}
}
