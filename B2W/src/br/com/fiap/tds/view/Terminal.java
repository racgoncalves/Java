package br.com.fiap.tds.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.fiap.tds.bean.Dados;
import br.com.fiap.tds.bean.Login;
import br.com.fiap.tds.bean.Upload;

public class Terminal {
	
	public static void main(String args[]) {
		
		String usuario = "";
		String senha = "";
		String nome = "";
		String cpf = "";
		String cpfConjuge = "";
		String cpfFilho = "";
		String cpfFilhos = "";
		String pis = "";
		String sexo = "";
		String codigoSexo = "";
		String dataNascimento = "";
		int dia = 0;
		int mes = 0;
		int ano = 0;
		String cidadeNascimento = "";
		String estado = "";
		String pais = "";
		String estadoCivil = "";
		int codigoEstadoCivil = 0;
		int numeroFilhos = 0;
		String etnia = "";
		int codigoEtnia = 0;
		String agencia = "";
		String conta = "";
		String agenciaNumero = "";
		String agenciaDigito = "";
		String contaNumero = "";
		String contaDigito = "";
		String tamanhoCamiseta = "";
		int codigoTamanhoCamiseta = 0;
		String docRg = "";
		String docTrabalho = "";
		String docResidencia = "";
		String docEleitor = "";
		String docEscolaridade = "";
		String docReservista = "";
		String docConjuge = "";
		String docFilho = "";
		String docFilhos = "";
		String docPais = "";
		String respostaDados = "";
		String respostaUpload = "";
		String respostaPais = "";
		int cont = 0;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Sistema de cadastro para novos funcionários");
		
		//	<<<<<<<<<<<<<<<	LOGIN >>>>>>>>>>>>>>>
		
		Login login = new Login();

		System.out.println("\n1ª Etapa - Login");
		System.out.println("\nPara testar utilize:\nUsuário = usuario\nSenha = fiap123");
		
		do {
		
			System.out.println("\nDigite o usuário:");
			usuario = sc.next();
		
			if (!login.testaUsuario(usuario)){
				System.out.println("\nUsuário não encontrado!");
			}
		
		} while(!login.testaUsuario(usuario));
		
		do {
			
			System.out.println("Digite a senha:");
			senha = sc.next();
		
			if (!login.testaSenha(senha)){
				System.out.println("\nSenha incorreta!");
			}
		
		} while(!login.testaSenha(senha));
		
		System.out.println("\nLogin efetuado com sucesso!");
		
		//	<<<<<<<<<<<<<<<	DADOS >>>>>>>>>>>>>>>
		
		Dados dados = new Dados();
		
		System.out.println("\n2ª Etapa - Envio de Dados");
		
		do {
			
			//NOME COCOMPLETO
			do {
				System.out.println("\nDigite seu nome completo:");
				nome = sc.next() + sc.nextLine();
				nome = nome.toUpperCase();				
			} while (nome.isEmpty());
			
			dados.setNome(nome);
			
			//CPF
			do {
				System.out.println("\nDigite seu CPF (sem pontos nem traços - apenas números):\nExemplo de CPF: 04620829463");
				cpf = sc.next();
			
				if(cpf.length()!=11) {
					System.out.println("\nO CPF tem 11 dígitos!");
				}
				else if(!cpf.matches("[0-9]+")) {
					System.out.println("\nDigite somente números!");
				}
				else if(!dados.isCPF(cpf)) {
					System.out.println("\nEste CPF não existe!");
				}
		
			} while (!dados.isCPF(cpf));
			
			cpf = cpf.substring(0,3).concat(".").concat(cpf.substring(3,6)).concat(".").concat(cpf.substring(6,9)).concat("-").concat(cpf.substring(9));
			
			dados.setCpf(cpf);
			
			//PIS
			do {
				System.out.println("\nDigite seu PIS (sem pontos nem traços - apenas números):\nExemplo de PIS: 17033259504");
				pis = sc.next();
			
				if(pis.length()!=11) {
					System.out.println("\nO PIS/PASEP tem 11 dígitos!");
				}
				else if(!pis.matches("[0-9]+")) {
					System.out.println("\nDigite somente números!");
				}
				else if(!dados.isPis(pis)) {
				System.out.println("\nEste PIS/PASEP não existe!");
				}	
		
			} while (!dados.isPis(pis));
			
			pis = pis.substring(0,3).concat(".").concat(pis.substring(3,8)).concat(".").concat(pis.substring(8,10)).concat("-").concat(pis.substring(10));
			
			dados.setPis(pis);
			
			//SEXO
			System.out.println("\nDigite seu sexo de nascimento ('F' para FEMININO, 'M' para MASCULINO ou 'O' para OUTRO):");
			codigoSexo = sc.next();
			codigoSexo = codigoSexo.toUpperCase();
			if (!codigoSexo.matches("[F]") && !codigoSexo.matches("[M]") && !codigoSexo.matches("[O]")) {
				System.out.println("\nValor Incorreto!");
				codigoSexo = "";
			}
				
			sexo = dados.carregaSexo(codigoSexo);
		
			dados.setSexo(sexo);
			
			//DATA DE NASCIMENTO
			
			//Ano
			do {
				try {
					System.out.println("\nDigite o ano em que você nasceu:");
					ano = sc.nextInt();
					if(!dados.validaAno(ano)) {
						System.out.println("\nData Inválida");
					}
				}catch(InputMismatchException e){
					System.err.println("Digite somente números!!");
					sc.nextLine();
					ano = 0;
				}
					
			}while (!dados.validaAno(ano));
			
			//Mês
			do {
				try {
					System.out.println("\nDigite o mês em que você nasceu:");
					mes = sc.nextInt();
					if(!dados.validaMes(mes)) {
						System.out.println("\nData Inválida");
					}
				}catch(InputMismatchException e){
					System.err.println("Digite somente números!!");
					sc.nextLine();
					mes = 0;
				}
				}while (!dados.validaMes(mes));
			
			//Dia
			do {
				try{
					System.out.println("\nDigite o dia em que você nasceu:");
					dia = sc.nextInt();
					if(!dados.validaDia(dia, mes)) {
						System.out.println("\nData Inválida");
					}
				}catch(InputMismatchException e){
					System.err.println("Digite somente números!!");
					sc.nextLine();
					dia = 0;
				}
			}while (!dados.validaDia(dia, mes));
			
			dataNascimento = dados.montaData(dia, mes, ano);
			
			dados.setDataNascimento(dataNascimento);
			
			//PAÍS
			do {
				System.out.println("\nDigite seu país de nascimento:");
				pais = sc.next() + sc.nextLine();
				pais = pais.toUpperCase();				
			} while (pais.isEmpty());
			
			dados.setPais(pais);
			
			//ESTADO
			do {
				System.out.println("\nDigite seu estado de nascimento:");
				estado = sc.next() + sc.nextLine();
				estado = estado.toUpperCase();				
			} while (estado.isEmpty());
			
			dados.setEstado(estado);
			
			//CIDADE DE NASCIMENTO
			do {
				System.out.println("\nDigite sua cidade de nascimento:");
				cidadeNascimento = sc.next() + sc.nextLine();
				cidadeNascimento = cidadeNascimento.toUpperCase();				
			} while (cidadeNascimento.isEmpty());
			
			dados.setCidadeNascimento(cidadeNascimento);
			
			//ESTADO CIVIL
			do {
				try {
					System.out.println("\n1 - SOLTEIRO\n2 - CASADO\n3 - SEPARADO\n4 - DIVORCIADO\n5 - VIÚVO\nDigite o código do seu estado civil:");
					codigoEstadoCivil = sc.nextInt();
				}catch(InputMismatchException e){
					System.err.println("Digite somente números!!");
					sc.nextLine();
					codigoEstadoCivil = 0;
				}
			} while (codigoEstadoCivil < 1 || codigoEstadoCivil > 5 );
			
			estadoCivil = dados.carregaEstadoCivil(codigoEstadoCivil);
			
			dados.setEstadoCivil(estadoCivil);
			
			//CPF CÔNJUGE
			if(estadoCivil.equals("CASADO")) {
				do {
					System.out.println("\nDigite o CPF do Cônjuge (sem pontos nem traços - apenas números):\nExemplo de CPF: 04620829463");
					cpfConjuge = sc.next();
				
					if(cpfConjuge.length()!=11) {
						System.out.println("\nO CPF tem 11 dígitos!");
					}
					else if(!cpfConjuge.matches("[0-9]+")) {
						System.out.println("\nDigite somente números!");
					}
					else if(!dados.isCPF(cpfConjuge)) {
						System.out.println("\nEste CPF não existe!\nExemplo de CPF:\n04620829463");
					}
			
				} while (!dados.isCPF(cpfConjuge));
				
				cpfConjuge = cpfConjuge.substring(0,3).concat(".").concat(cpfConjuge.substring(3,6)).concat(".").concat(cpfConjuge.substring(6,9)).concat("-").concat(cpfConjuge.substring(9));
				
				dados.setCpfConjuge(cpfConjuge);
			}
			
			
			//NÚMERO FILHOS
			do {
				try {
					System.out.println("\nDigite o número de filhos que você possui:");
					numeroFilhos = sc.nextInt();
				}catch(InputMismatchException e){
					System.err.println("Digite somente números!!");
					sc.nextLine();
					numeroFilhos = -1;
				}
							
			} while (numeroFilhos < 0);
			
			dados.setNumeroFilhos(numeroFilhos);
			
			//CPF FILHOS
			if(numeroFilhos > 0) {
				
				do {
					cont += 1;
					do {
						System.out.println("\nDigite o CPF do seu filho: "+ cont +" (sem pontos nem traços - apenas números):\nExemplo de CPF: 04620829463");
						cpfFilho = sc.next();
				
						if(cpfFilho.length()!=11) {
							System.out.println("\nO CPF tem 11 dígitos!");
						}
						else if(!cpfFilho.matches("[0-9]+")) {
							System.out.println("\nDigite somente números!");
						}
						else if(!dados.isCPF(cpfFilho)) {
							System.out.println("\nEste CPF não existe!\nExemplo de CPF:\n04620829463");
						}
			
					} while (!dados.isCPF(cpfFilho));
				
					cpfFilho = cpfFilho.substring(0,3).concat(".").concat(cpfFilho.substring(3,6)).concat(".").concat(cpfFilho.substring(6,9)).concat("-").concat(cpfFilho.substring(9).concat(";"));
				
					cpfFilhos += cpfFilho;
				
				} while (cont < numeroFilhos);
				
				cpfFilhos = cpfFilhos.substring(0,cpfFilhos.length()-1);
				
				dados.setCpfFilhos(cpfFilhos);
			}
			
			//ETNIA
			do {
				try {
					System.out.println("\n1 - BRANCO\n2 - NEGRO\n3 - PARDO\n4 - INDÍGENA\nDigite o código da sua etnia:");
					codigoEtnia = sc.nextInt();
				}catch(InputMismatchException e){
					System.err.println("Digite somente números!!");
					sc.nextLine();
					codigoEtnia = 0;
				}
			} while (codigoEtnia < 1 || codigoEtnia > 4 );
			
			etnia = dados.carregaEtnia(codigoEtnia);
			
			dados.setEtnia(etnia);
			
			//CONTA BRADESCO
			System.out.println("\nInforme os dados da sua Conta do Bradesco (corrente/salário)");
			do {
				System.out.println("\nDigite o número da sua agência (sem o dígito verificador):\nObs: O número da agência tem 4 dígitos");
				agenciaNumero = sc.next();
			
				if(agenciaNumero.length() != 4) {
					System.out.println("\nValor Incorreto!");
				}
					
				else if(!agenciaNumero.matches("[0-9]+")) {
					System.out.println("\nDigite somente números!");				
				}
					
			} while(agenciaNumero.length() != 4 || !agenciaNumero.matches("[0-9]+"));
				
			do {
				System.out.println("\nDigite o dígito verificador da sua agência:");
				agenciaDigito = sc.next();
				if(!agenciaDigito.matches("[0-9]")) {
					System.out.println("\nValor Incorreto!");
				}
					
			} while (!agenciaDigito.matches("[0-9]"));
				
			do {
				System.out.println("\nDigite o número da sua conta (sem o dígito verificador):\nObs: O número da conta tem até 7 dígitos");
				contaNumero = sc.next();
			
				if(contaNumero.length() > 7 || contaNumero.length() < 1) {
					System.out.println("\nValor Incorreto!");
				}
					
				else if(!contaNumero.matches("[0-9]+")) {
					System.out.println("\nDigite somente números!");				
				}
					
			} while(contaNumero.length() > 7 || contaNumero.length() < 1 || !contaNumero.matches("[0-9]+"));
				
			do {
				System.out.println("\nDigite o dígito verificador da sua conta:");
				contaDigito = sc.next();
				if(!contaDigito.matches("[0-9]")) {
					System.out.println("\nValor Incorreto!");
				}
					
			} while (!contaDigito.matches("[0-9]"));

			agencia = agenciaNumero.concat("-").concat(agenciaDigito);
			conta = contaNumero.concat("-").concat(contaDigito);
				
			dados.setAgencia(agencia);
			dados.setConta(conta);
	
			
			//TAMANHO CAMISETA
			do {
				try {
					System.out.println("\n1 - PP\n2 - P\n3 - M\n4 - G\n5 - GG\nDigite o código do tamanho da sua Camiseta:");
					codigoTamanhoCamiseta = sc.nextInt();
				}catch(InputMismatchException e){
					System.err.println("Digite somente números!!");
					sc.nextLine();
					codigoTamanhoCamiseta = 0;
				}
			} while (codigoTamanhoCamiseta < 1 || codigoTamanhoCamiseta > 5 );

        	tamanhoCamiseta = dados.carregaTamanhoCamiseta(codigoTamanhoCamiseta);
        	
        	dados.setTamanhoCamiseta(tamanhoCamiseta);
			
        	//Mostra os dados enviados
			System.out.println("\nOs Dados enviados foram:" + dados);
			
			//Conclui ou reenvia os dados
			do {
				System.out.println("Digite 'S' para CONCLUIR o envio dos dados ou 'N' para NÃO CONCLUIR e digitá-los novamente");	
				respostaDados = sc.next();
			} while(!respostaDados.equalsIgnoreCase("s") && !respostaDados.equalsIgnoreCase("n"));
			
			if (respostaDados.equalsIgnoreCase("n")) {
				dados.zeraDados();
			}
		
		} while (respostaDados.equalsIgnoreCase("n"));
			
		System.out.println("\nDados enviados com sucesso!");
		
		//	<<<<<<<<<<<<<<<	UPLOAD >>>>>>>>>>>>>>>
		
		Upload upload = new Upload();
		
		do {
		
			System.out.println("\n3ª Etapa - Upload de Arquivos");
		
			System.out.println("\nTodos os arquivos devem estar em PDF ou JPG");
			System.out.println("Os nomes dos arquivos devem terminar com '.pdf' ou '.jpg' para serem válidos");
			
			//RG
			do {
				System.out.println("\nCarregue seu RG:");
				docRg = sc.next();
				if(!docRg.endsWith(".pdf") && !docRg.endsWith(".jpg") || docRg.length() <= 4) {
					System.out.println("\nDocumento Inválido!");
				}
				else {
					upload.setDocRg(docRg);
					System.out.println("\nO documento " + docRg + " foi enviado com sucesso!");
				}
			} while(!docRg.endsWith(".pdf") && !docRg.endsWith(".jpg") || docRg.length() <= 4);
			
			//CARTEIRA DE TRABALHO
			do {
				System.out.println("\nCarregue sua Carteira de Trabalho:");
				docTrabalho = sc.next();
				if(!docTrabalho.endsWith(".pdf") && !docTrabalho.endsWith(".jpg") || docTrabalho.length() <= 4) {
					System.out.println("\nDocumento Inválido!");
				}
				else {
					upload.setDocTrabalho(docTrabalho);
					System.out.println("\nO documento " + docTrabalho + " foi enviado com sucesso!");
				}
			} while(!docTrabalho.endsWith(".pdf") && !docTrabalho.endsWith(".jpg") || docTrabalho.length() <= 4);
			
			//COMPROVANTE DE RESIDÊNCIA
			do {
				System.out.println("\nCarregue seu Comprovante de Residência:");
				docResidencia = sc.next();
				if(!docResidencia.endsWith(".pdf") && !docResidencia.endsWith(".jpg") || docResidencia.length() <= 4) {
					System.out.println("\nDocumento Inválido!");
				}
				else {
					upload.setDocResidencia(docResidencia);
					System.out.println("\nO documento " + docResidencia + " foi enviado com sucesso!");
				}
			} while(!docResidencia.endsWith(".pdf") && !docResidencia.endsWith(".jpg") || docResidencia.length() <= 4);
		
			//TÍTULO DE ELEITOR
			do {
				System.out.println("\nCarregue seu Título de Eleitor:");
				docEleitor = sc.next();
				if(!docEleitor.endsWith(".pdf") && !docEleitor.endsWith(".jpg") || docEleitor.length() <= 4) {
					System.out.println("\nDocumento Inválido!");
				}
				else {
					upload.setDocEleitor(docEleitor);
					System.out.println("\nO documento " + docEleitor + " foi enviado com sucesso!");
				}
			} while(!docEleitor.endsWith(".pdf") && !docEleitor.endsWith(".jpg") || docEleitor.length() <= 4);
			
			//COMPROVANTE DE ESCOLARIDADE
			do {
				System.out.println("\nCarregue seu Comprovante de Escolaridade:");
				docEscolaridade = sc.next();
				if(!docEscolaridade.endsWith(".pdf") && !docEscolaridade.endsWith(".jpg") || docEscolaridade.length() <= 4) {
					System.out.println("\nDocumento Inválido!");
				}
				else {
					upload.setDocEscolaridade(docEscolaridade);
					System.out.println("\nO documento " + docEscolaridade + " foi enviado com sucesso!");
				}
			} while(!docEscolaridade.endsWith(".pdf") && !docEscolaridade.endsWith(".jpg") || docEscolaridade.length() <= 4);	
			
			//CERTIFICADO DE RESERVISTA
			if (dados.getSexo().equals("MASCULINO") && ano < 2002) {
				do {
					System.out.println("\nCarregue seu Certificado de Reservista:");
					docReservista = sc.next();
					if(!docReservista.endsWith(".pdf") && !docReservista.endsWith(".jpg") || docReservista.length() <= 4) {
						System.out.println("\nDocumento Inválido!");
					}
					else {
						upload.setDocReservista(docReservista);
						System.out.println("\nO documento " + docReservista + " foi enviado com sucesso!");
					}
				} while(!docReservista.endsWith(".pdf") && !docReservista.endsWith(".jpg") || docReservista.length() <= 4);	
			}
			
			//CERTIDÃO DE NASCIMENTO CÔNJUGE
			if (dados.getEstadoCivil().equals("CASADO")) {
				do {
					System.out.println("\nCarregue a certidão de nascimento do Cônjuge:");
					docConjuge = sc.next();
					if(!docConjuge.endsWith(".pdf") && !docConjuge.endsWith(".jpg") || docConjuge.length() <= 4) {
						System.out.println("\nDocumento Inválido!");
					}
					else {
						upload.setDocConjuge(docConjuge);
						System.out.println("\nO documento " + docConjuge + " foi enviado com sucesso!");
					}
				} while(!docConjuge.endsWith(".pdf") && !docConjuge.endsWith(".jpg") || docConjuge.length() <= 4);
			}
			
			//CERTIDÃO DE NASCIMENTO FILHOS
			if (dados.getNumeroFilhos() > 0) {
				cont = 0;
				do {
					cont += 1;
					do {
						System.out.println("\nCarregue a certidão de nascimento do seu filho " + cont + ":");
						docFilho = sc.next();
						if(!docFilho.endsWith(".pdf") && !docFilho.endsWith(".jpg") || docFilho.length() <= 4) {
							System.out.println("\nDocumento Inválido!");
						}
						else {
							docFilhos += docFilho.concat(";");
							upload.setDocFilhos(docFilhos);
							System.out.println("\nO documento " + docFilho + " foi enviado com sucesso!");
						}
						
					} while(!docFilho.endsWith(".pdf") && !docFilho.endsWith(".jpg") || docFilho.length() <= 4);
										
				} while(cont < dados.getNumeroFilhos());
				docFilhos = docFilhos.substring(0,docFilhos.length()-1);
				upload.setDocFilhos(docFilhos);
			}
			
			//DOCUMENTOS DOS PAIS
			do {
				System.out.println("\nSeu pai e/ou sua mãe são seus dependentes no imposto de renda?");
				System.out.println("Digite 'S' para SIM ou 'N' para NÃO");
				respostaPais = sc.next();
			} while(!respostaPais.equalsIgnoreCase("s") && !respostaPais.equalsIgnoreCase("n"));
			
			if (respostaPais.equalsIgnoreCase("s")) {
				do {
					System.out.println("\nCarregue o RG e CPF do seu pai e/ou sua mãe:");
					docPais = sc.next();
					if(!docPais.endsWith(".pdf") && !docPais.endsWith(".jpg") || docPais.length() <= 4) {
						System.out.println("\nDocumento Inválido!");
					}
					else {
						upload.setDocPais(docPais);
						System.out.println("\nO documento " + docPais + " foi enviado com sucesso!");
					}
				} while(!docPais.endsWith(".pdf") && !docPais.endsWith(".jpg") || docPais.length() <= 4);
			}
			
			//Mostra os arquivos enviados
			System.out.println("\nOs documentos enviados foram:" + upload);
			
			//Conclui ou reenvia os arquivos
			do {
				System.out.println("Digite 'S' para CONCLUIR o upload ou 'N' para NÃO CONCLUIR e reenviar os documentos");	
				respostaUpload = sc.next();			
			} while(!respostaUpload.equalsIgnoreCase("s") && !respostaUpload.equalsIgnoreCase("n"));
			
			if (respostaUpload.equalsIgnoreCase("n")) {
				upload.zeraUpload();
				respostaPais = "";
				docFilho = "";
			}
			
		} while (respostaUpload.equalsIgnoreCase("n"));
		
		// <<<<<<<<<<<<<<<	ENCERRAMENTO >>>>>>>>>>>>>>>
		
		System.out.println("\nDados enviados:" + dados);
		System.out.println("\nDocumentos enviados:" + upload);
		
		System.out.println("\nCadastro concluído!!\nSeja bem-vindo à B2W");
		
		sc.close();
		
	}
}