package br.com.fiap.tds.view;

import java.util.Scanner;
import br.com.fiap.tds.bean.Funcionario;
import br.com.fiap.tds.bean.Loja;
import br.com.fiap.tds.bean.Produto;

public class Terminal {
	
	public static void main(String args[]) {
		
		Scanner sc = new Scanner(System.in);
		double porcentagem;
		
		//Funcion�rio
		
		System.out.println("SISTEMA DA B2W - RECURSOS HUMANOS");
		
		Funcionario funcionario1 = new Funcionario("Rodrigo",32,"informatica",4200.50);
		
		System.out.println("\nDados do funcion�rio:" + "\n" + (funcionario1));
		
		System.out.println("\nO funcion�rio " + funcionario1.getNome() + " est� mudando de departamento.");
		System.out.println("\nDigite o novo departamento:");
		funcionario1.setDepartamento(sc.nextLine());
		System.out.println("\nDepartamento alterado com sucesso!");
		
		System.out.println("\nDigite o aumento de sal�rio:");
		double aumento = sc.nextDouble();
		
		if (aumento == 0) {
			System.out.println("\nSal�rio n�o alterado!");
		} else {
		
			System.out.println("Digite a senha de acesso:");
			String senha = sc.next();
		
			double salarioAnterior = funcionario1.getSalario();
		
			funcionario1.setAumentaSalario(aumento, senha);
		
			double salarioNovo = funcionario1.getSalario();
		
			if (salarioNovo > salarioAnterior) {
				System.out.println("\nSal�rio alterado com sucesso!");
			} else {
				System.out.println("\nSenha incorreta! O sal�rio n�o foi alterado!");
			}
		}
		
		System.out.println("\nOs dados atualizados do funcion�rio s�o:" + "\n" + (funcionario1));

		//Produto
		
		System.out.println("\nSISTEMA DA B2W - ALMOXARIFADO SUBMARINO");
		
		System.out.println("\nUm novo produto chegou para ser cadastrado.");
		System.out.println("\nDigite o nome do produto:");
		String produto = sc.next();
		
		System.out.println("Digite o pre�o do produto a prazo:");
		double valor = sc.nextDouble();
		
		Produto produto1 = new Produto(produto,valor);
		
		do {
			System.out.println("Digite a % do valor de desconto para a compra � vista (max de 30%):");
			porcentagem = sc.nextDouble();
			if (porcentagem > 30 || porcentagem < 0) {
				System.out.println("Valor n�o permitido!!");
			}
		} while(porcentagem > 30 || porcentagem < 0);
		
		produto1.porcentagemDesconto(porcentagem);
		
		System.out.println("\nProduto cadastrado com sucesso!");
		
		System.out.println("\nOs dados do produto s�o:" + "\n" + produto1);
		
		//Loja
		
		System.out.println("\nSISTEMA DA B2W - LOJAS AMERICANAS");
		
		Loja loja1 = new Loja(432,"Botucatu");
		
		loja1.setEstado("sp");	
		
		System.out.println("\nAtualiza��o dos dados da loja C�digo " + loja1.getCodigo() + " de " + loja1.getCidade() + "/" + loja1.getEstado() + ".");
		System.out.println("\nDigite o tamanho da loja em m�:");
		loja1.setTamanhoLoja(sc.nextFloat());
		
		System.out.println("Digite a quantidade de funcion�rios:");
		loja1.setNumFuncionarios(sc.nextInt());
		
		System.out.println("O Rodrigo ser� o novo contratado? Digite 'S' para sim:");
		String resposta = sc.next();
		if (resposta.equalsIgnoreCase("s")) {
			loja1.novoFuncionario();
			System.out.println("\nFuncion�rio adicionado com sucesso!");
		} else {
			System.out.println("\nO funcion�rio n�o foi adicionado!");
		}
		
		System.out.println("\nDados foram atualizados com sucesso!");
		
		System.out.println("\nOs dados atualizados da loja s�o:");
		System.out.println(loja1);
		
		//fechando o scanner
		sc.close();
	}

}
