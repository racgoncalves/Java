package br.com.fiap.tds.view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.fiap.tds.bean.Colaborador;
import br.com.fiap.tds.bean.ContaBanco;
import br.com.fiap.tds.bean.Dados;
import br.com.fiap.tds.bean.Dependente;
import br.com.fiap.tds.bean.Documentos;
import br.com.fiap.tds.dao.DadosDao;
import br.com.fiap.tds.dao.DependentesDao;
import br.com.fiap.tds.dao.DocumentosDao;
import br.com.fiap.tds.dao.ColaboradorDao;
import br.com.fiap.tds.dao.ContaBancoDao;

public class TerminalColaborador {

	public static void main(String args[]) {

		// Atributos
		String matricula = "";
		String senha = "";
		int cadastrado = 1;
		String email = "";
		String nome = "";
		String cpf = "";
		String pis = "";
		String sexo = "";
		String codigoSexo = "";
		String dataNascimento = "";
		int dia = 0;
		int mes = 0;
		int ano = 0;
		String nacionalidade = "";
		String naturalidade = "";
		String estadoCivil = "";
		int codigoEstadoCivil = 0;
		int numeroFilhos = 0;
		String etnia = "";
		int codigoEtnia = 0;
		String agenciaNumero = "";
		String agenciaDigito = "";
		String contaNumero = "";
		String contaDigito = "";
		String tamanhoCamiseta = "";
		int codigoTamanhoCamiseta = 0;
		String orientacao = "";
		String religiao = "";
		int cont = 0;
		boolean enviou;
		String nomeArquivo = "";
		byte[] residencia = null;
		byte[] escolaridade = null;
		byte[] rg = null;
		byte[] carteiraTrabalho = null;
		byte[] tituloEleitor = null;
		byte[] reservista = null;
		byte[] certidaoCasamento = null;
		byte[] certidaoNascimentoFilho = null;
		byte[] rgGenitor = null;
		int respostaPais = 0;
		int i = 0;
		int nFilhosDependentes = 0;

		// Inicia o scanner
		Scanner sc = new Scanner(System.in);

		// Inicia classes Dao
		ColaboradorDao colaboradorDao = new ColaboradorDao();
		DadosDao dadosDao = new DadosDao();
		ContaBancoDao contaDao = new ContaBancoDao();
		DocumentosDao docDao = new DocumentosDao();
		DependentesDao depDao = new DependentesDao();

		// Inicia classes Bean
		Dados dados = new Dados();
		ContaBanco contaBanco = new ContaBanco();
		Colaborador colaborador = new Colaborador();
		Documentos documentos = new Documentos();
		Dependente dependente = new Dependente();

		System.out.println("\nSistema de cadastro para novos funcionários");

		// <<<<<<<<<<<<<<< LOGIN >>>>>>>>>>>>>>>

		System.out.println("\n1ª Etapa - Login");

		try {

			do {
				try {
					do {
						System.out.println("\nDigite o seu email:");
						email = sc.next();
					} while (email.isEmpty());
				} catch (NullPointerException e) {

				}

				matricula = colaboradorDao.getMatricula(email);

				if (matricula.isEmpty())
					System.out.println("\nEmail não encontrado!");

			} while (matricula.isEmpty());

			// Checa se já fez o cadastro
			cadastrado = colaboradorDao.getStatus(email);

		} catch (Exception e) {

			System.out.println("\nNão foi possível realizar o login!");

		}

		// Se não tiver feito o cadastro continua
		if (cadastrado == 0) {
			do {
				cont = 0;
				try {
					do {
						System.out.println("\nDigite a sua senha:");
						senha = sc.next();
					} while (senha.isEmpty());
				} catch (NullPointerException e) {

				}
				// Caso encontre o email
				if (matricula.equals(senha)) {

					// Confirma que a senha do colaborador foi encontrada
					cont++;

				}

				if (cont == 0)
					System.out.println("\nSenha Incorreta!");

			} while (cont == 0);

			System.out.println("\nLogin efetuado com sucesso!");

			// <<<<<<<<<<<<<<< DADOS >>>>>>>>>>>>>>>

			System.out.println("\n2ª Etapa - Envio de Dados");

			// NOME COCOMPLETO
			do {
				System.out.println("\nDigite seu nome completo:");
				nome = sc.next() + sc.nextLine();
				nome = nome.toUpperCase();
			} while (nome.isEmpty());

			// CPF
			do {
				System.out.println(
						"\nDigite seu CPF (sem pontos nem traços - apenas números):\n" + "Exemplo de CPF: 04620829463");
				cpf = sc.next();

				if (cpf.length() != 11) {
					System.out.println("\nO CPF tem 11 dígitos!");
				} else if (!cpf.matches("[0-9]+")) {
					System.out.println("\nDigite somente números!");
				} else if (!dados.isCPF(cpf)) {
					System.out.println("\nEste CPF não existe!");
				}

			} while (!dados.isCPF(cpf));

			cpf = cpf.substring(0, 3).concat(".").concat(cpf.substring(3, 6)).concat(".").concat(cpf.substring(6, 9))
					.concat("-").concat(cpf.substring(9));

			// PIS
			do {
				System.out.println(
						"\nDigite seu PIS (sem pontos nem traços - apenas números):\n" + "Exemplo de PIS: 17033259504");
				pis = sc.next();

				if (pis.length() != 11) {
					System.out.println("\nO PIS/PASEP tem 11 dígitos!");
				} else if (!pis.matches("[0-9]+")) {
					System.out.println("\nDigite somente números!");
				} else if (!dados.isPis(pis)) {
					System.out.println("\nEste PIS/PASEP não existe!");
				}

			} while (!dados.isPis(pis));

			pis = pis.substring(0, 3).concat(".").concat(pis.substring(3, 8)).concat(".").concat(pis.substring(8, 10))
					.concat("-").concat(pis.substring(10));

			// SEXO
			do {
			System.out.println(
					"\nDigite seu sexo de nascimento ('F' para FEMININO, 'M' para MASCULINO ou 'N' NÃO INFORMAR):");
			codigoSexo = sc.next();
			codigoSexo = codigoSexo.toUpperCase();
			if (!codigoSexo.matches("[F]") && !codigoSexo.matches("[M]") && !codigoSexo.matches("[N]")) {
				System.out.println("\nValor Incorreto!");
				codigoSexo = "";
			}
			}while (codigoSexo.isEmpty());

			sexo = dados.carregaSexo(codigoSexo);

			// NACIONALIDADE
			do {
				System.out.println("\nDigite sua nacionalidade:");
				nacionalidade = sc.next() + sc.nextLine();
				nacionalidade = nacionalidade.toUpperCase();
			} while (nacionalidade.isEmpty());

			// NATURALIDADE
			do {
				System.out.println("\nDigite sua naturalidade:");
				naturalidade = sc.next() + sc.nextLine();
				naturalidade = naturalidade.toUpperCase();
			} while (naturalidade.isEmpty());

			// DATA DE NASCIMENTO

			// ANO
			do {
				try {
					System.out.println("\nDigite o ano em que você nasceu:");
					ano = sc.nextInt();
					if (!dados.validaAno(ano)) {
						System.out.println("\nData Inválida");
					}
				} catch (InputMismatchException e) {
					System.err.println("Digite somente números!!");
					sc.nextLine();
					ano = 0;
				}

			} while (!dados.validaAno(ano));

			// MÊS
			do {
				try {
					System.out.println("\nDigite o mês em que você nasceu:");
					mes = sc.nextInt();
					if (!dados.validaMes(mes)) {
						System.out.println("\nData Inválida");
					}
				} catch (InputMismatchException e) {
					System.err.println("Digite somente números!!");
					sc.nextLine();
					mes = 0;
				}
			} while (!dados.validaMes(mes));

			// DIA
			do {
				try {
					System.out.println("\nDigite o dia em que você nasceu:");
					dia = sc.nextInt();
					if (!dados.validaDia(dia, mes)) {
						System.out.println("\nData Inválida");
					}
				} catch (InputMismatchException e) {
					System.err.println("Digite somente números!!");
					sc.nextLine();
					dia = 0;
				}
			} while (!dados.validaDia(dia, mes));

			dataNascimento = dados.montaData(dia, mes, ano);

			// ESTADO CIVIL
			do {
				try {
					System.out.println("\n1 - SOLTEIRO\n" + "2 - CASADO\n" + "3 - SEPARADO\n" + "4 - DIVORCIADO\n"
							+ "5 - VIÚVO\n" + "Digite o código do seu estado civil:");
					codigoEstadoCivil = sc.nextInt();
				} catch (InputMismatchException e) {
					System.err.println("Digite somente números!!");
					sc.nextLine();
					codigoEstadoCivil = 0;
				}
			} while (codigoEstadoCivil < 1 || codigoEstadoCivil > 5);

			estadoCivil = dados.carregaEstadoCivil(codigoEstadoCivil);

			// NÚMERO FILHOS
			do {
				try {
					System.out.println("\nDigite o número de filhos que você possui:");
					numeroFilhos = sc.nextInt();
				} catch (InputMismatchException e) {
					System.err.println("Digite somente números!!");
					sc.nextLine();
					numeroFilhos = -1;
				}

			} while (numeroFilhos < 0);

			// BRADESCO
			System.out.println("\nInforme os dados da sua Conta do Bradesco (corrente/salário)");

			// AGÊNCIA BRADESCO
			do {
				System.out.println("\nDigite o número da sua agência (sem o dígito verificador):\n"
						+ "Obs: O número da agência tem 4 dígitos");
				agenciaNumero = sc.next();

				if (agenciaNumero.length() != 4) {
					System.out.println("\nValor Incorreto!");
				}

				else if (!agenciaNumero.matches("[0-9]+")) {
					System.out.println("\nDigite somente números!");
				}

			} while (agenciaNumero.length() != 4 || !agenciaNumero.matches("[0-9]+"));

			do {
				System.out.println("\nDigite o dígito verificador da sua agência:");
				agenciaDigito = sc.next();
				if (!agenciaDigito.matches("[0-9]")) {
					System.out.println("\nValor Incorreto!");
				}

			} while (!agenciaDigito.matches("[0-9]"));

			// CONTA BRADESCO
			do {
				System.out.println("\nDigite o número da sua conta (sem o dígito verificador):\n"
						+ "Obs: O número da conta tem até 7 dígitos");
				contaNumero = sc.next();

				if (contaNumero.length() > 7 || contaNumero.length() < 1) {
					System.out.println("\nValor Incorreto!");
				}

				else if (!contaNumero.matches("[0-9]+")) {
					System.out.println("\nDigite somente números!");
				}

			} while (contaNumero.length() > 7 || contaNumero.length() < 1 || !contaNumero.matches("[0-9]+"));

			do {
				System.out.println("\nDigite o dígito verificador da sua conta:");
				contaDigito = sc.next();
				if (!contaDigito.matches("[0-9]")) {
					System.out.println("\nValor Incorreto!");
				}

			} while (!contaDigito.matches("[0-9]"));

			// ETNIA
			do {
				try {
					System.out.println("\n1 - BRANCO\n" + "2 - NEGRO\n" + "3 - PARDO\n" + "4 - INDÍGENA\n"
							+ "Digite o código da sua etnia:");
					codigoEtnia = sc.nextInt();
				} catch (InputMismatchException e) {
					System.err.println("Digite somente números!!");
					sc.nextLine();
					codigoEtnia = 0;
				}
			} while (codigoEtnia < 1 || codigoEtnia > 4);

			etnia = dados.carregaEtnia(codigoEtnia);

			// TAMANHO CAMISETA
			do {
				try {
					System.out.println("\n1 - PP\n" + "2 - P\n" + "3 - M\n" + "4 - G\n" + "5 - GG\n"
							+ "Digite o código do tamanho da sua Camiseta:");
					codigoTamanhoCamiseta = sc.nextInt();
				} catch (InputMismatchException e) {
					System.err.println("Digite somente números!!");
					sc.nextLine();
					codigoTamanhoCamiseta = 0;
				}
			} while (codigoTamanhoCamiseta < 1 || codigoTamanhoCamiseta > 5);

			tamanhoCamiseta = dados.carregaTamanhoCamiseta(codigoTamanhoCamiseta);

			// DADOS SENSÍVEIS
			System.out.println("\nAs perguntas abaixo fazem parte de uma pesquisa da B2W, "
					+ "esses dados não serão divulgados nem utilizados em processos seletivos "
					+ "da empresa e você não é obrigado a informá-los.");

			// ORIENTAÇÃO SEXUAL
			do {
				System.out.println("\nQual a sua orientação sexual? (Digite N para não informar)");
				orientacao = sc.next() + sc.nextLine();
				orientacao = orientacao.toUpperCase();
				if (orientacao.equals("N")) {
					orientacao = "NÃO INFORMADO";
				}
			} while (orientacao.isEmpty());

			// RELIGIÃO
			do {
				System.out.println("\nQual a sua religião? (Digite N para não informar)");
				religiao = sc.next() + sc.nextLine();
				religiao = religiao.toUpperCase();
				if (religiao.equals("N")) {
					religiao = "NÃO INFORMADO";
				}
			} while (religiao.isEmpty());

			// Envia os dados para as classes
			colaborador.setNome(nome);

			dados = new Dados(cpf, pis, sexo, nacionalidade, naturalidade, dataNascimento, estadoCivil, numeroFilhos,
					etnia, tamanhoCamiseta, orientacao, religiao);

			contaBanco = new ContaBanco(agenciaNumero, agenciaDigito, contaNumero, contaDigito);

			// Altera o boolean
			enviou = true;

			try {

				// Grava os dados no banco de dados
				colaboradorDao.setNome(matricula, colaborador.getNome());
				dadosDao.setDados(matricula, dados);
				contaDao.setContaBanco(matricula, contaBanco);

			} catch (Exception e) {
				System.out.println("\nNão foi possível enviar os dados!");
				e.printStackTrace();
				enviou = false;
			}

			if (enviou) {

				System.out.println("\nDados enviados com sucesso!");

				// <<<<<<<<<<<<<<< DOCUMENTOS >>>>>>>>>>>>>>>

				System.out.println("\n3ª Etapa - Envio de Documentos");

				try {

					// RG
					System.out.println("\nEnvie o seu RG.\nDigite o nome do arquivo sem o '.pdf':");
					nomeArquivo = sc.next();
					rg = docDao.lerDocumento(nomeArquivo);

					// CARTEIRA DE TRABALHO
					System.out.println("\nEnvia a sua Carteira de Trabalho.\nDigite o nome do arquivo sem o '.pdf':");
					nomeArquivo = sc.next();
					carteiraTrabalho = docDao.lerDocumento(nomeArquivo);

					// COMPROVANTE DE RESIDÊNCIA
					System.out.println(
							"\nEnvie o seu Comprovante de Residência.\nDigite o nome do arquivo sem o '.pdf':");
					nomeArquivo = sc.next();
					residencia = docDao.lerDocumento(nomeArquivo);

					// TÍTULO DE ELEITOR
					System.out.println("\nEnvie o seu Título de Eleitor.\nDigite o nome do arquivo sem o '.pdf':");
					nomeArquivo = sc.next();
					tituloEleitor = docDao.lerDocumento(nomeArquivo);

					// COMPROVANTE DE ESCOLARIDADE
					System.out.println(
							"\nEnvie o seu Comprovante de Escolaridade.\nDigite o nome do arquivo sem o '.pdf':");
					nomeArquivo = sc.next();
					escolaridade = docDao.lerDocumento(nomeArquivo);

					// CERTIFICADO DE RESERVISTA
					if (dados.getSexo().equals("MASCULINO") && ano < 2002) {
						System.out.println(
								"\nEnvie o seu Certificado de Reservista.\nDigite o nome do arquivo sem o '.pdf':");
						nomeArquivo = sc.next();
						reservista = docDao.lerDocumento(nomeArquivo);
					}

					// Envia os documentos para a classe
					documentos = new Documentos(rg, carteiraTrabalho, residencia, tituloEleitor, escolaridade);

					// Grava os documentos no banco de dados
					docDao.setDocumentos(matricula, documentos);

					if (dados.getSexo().equals("MASCULINO") && ano < 2002)
						documentos.setReservista(reservista);
					docDao.setCertificadoReservista(matricula, documentos);

				} catch (ClassNotFoundException | SQLException | IOException e) {
					System.out.println("\nNão foi possível enviar os documentos!");
					enviou = false;
					e.printStackTrace();
				}

				if (enviou) {

					System.out.println("\nDocumentos enviados com sucesso!");

					// <<<<<<<<<<<<<<< DEPENDENTES >>>>>>>>>>>>>>>

					System.out.println("\n4ª Etapa - Envio de Documentos dos Dependentes");

					try {

						// CERTIDÃO DE CASAMENTO
						if (dados.getEstadoCivil().equals("CASADO")) {
							System.out.println(
									"\nEnvie a sua Certidão de Casamento.\nDigite o nome do arquivo sem o '.pdf':");
							nomeArquivo = sc.next();
							certidaoCasamento = docDao.lerDocumento(nomeArquivo);

							dependente.setCertidaoCasamento(certidaoCasamento);
							dependente.setTipo("CONJUGE");
							depDao.setCertidaoCasamento(matricula, dependente);
						}

						// CERTIDÃO DE NASCIMENTO FILHOS
						if (dados.getNumeroFilhos() > 0) {

							do {
								try {
									System.out.println("\nDigite a quantidade de filhos dependentes:");
									nFilhosDependentes = sc.nextInt();

									if (nFilhosDependentes < 0)
										System.out.println("\nDigite um valor positivo ou 0 para sair!");

								} catch (InputMismatchException e) {
									System.err.println("Digite somente números!!");
									sc.nextLine();
								}
							} while (nFilhosDependentes < 0);

							if (nFilhosDependentes > 0) {

								do {

									i++;

									System.out.println("\nEnvie a Certidão de Nascimento de seu filho nº " + i
											+ ".\nDigite o nome do arquivo sem o '.pdf':");
									nomeArquivo = sc.next();
									certidaoNascimentoFilho = docDao.lerDocumento(nomeArquivo);

									dependente.setCertidaoNascimentoFilho(certidaoNascimentoFilho);
									dependente.setTipo("FILHO");
									depDao.setCertidaoNascimento(matricula, dependente);

								} while (i < nFilhosDependentes);
							}
						}

						// DOCUMENTOS DOS GENITORES
						do {
							try {
								System.out.println("\nSeu pai e/ou sua mãe são seus dependentes no imposto de renda?");
								System.out.println(
										"1 - Somente o Pai\n2 - Somente a Mãe\n3 - Ambos\n4 - Nenhum\nDigite o código da sua resposta:");
								respostaPais = sc.nextInt();
							} catch (InputMismatchException e) {
								System.err.println("Digite somente números!!");
								sc.nextLine();
								respostaPais = 0;
							}

							if (respostaPais < 1 || respostaPais > 4)
								System.out.println("\nOpção Inexistente!");

						} while (respostaPais < 1 || respostaPais > 4);

						// PAI
						if (respostaPais == 1 || respostaPais == 3) {
							System.out.println("\nEnvie o RG de seu pai.\nDigite o nome do arquivo sem o '.pdf':");
							nomeArquivo = sc.next();
							rgGenitor = docDao.lerDocumento(nomeArquivo);

							dependente.setRgGenitor(rgGenitor);
							dependente.setTipo("GENITOR");
							depDao.setRgGenitor(matricula, dependente);
						}

						// MÃE
						if (respostaPais == 2 || respostaPais == 3) {
							System.out.println("\nEnvie o RG de sua mãe.\nDigite o nome do arquivo sem o '.pdf':");
							nomeArquivo = sc.next();
							rgGenitor = docDao.lerDocumento(nomeArquivo);

							dependente.setRgGenitor(rgGenitor);
							dependente.setTipo("GENITOR");
							depDao.setRgGenitor(matricula, dependente);
						}

						// STATUS CADASTRADO
						cadastrado = 1;
						colaboradorDao.setStatus(matricula, cadastrado);

					} catch (ClassNotFoundException | SQLException | IOException e2) {
						System.out.println("\nNão foi possível enviar os documentos!");
						enviou = false;
						e2.printStackTrace();
					}

					if (enviou) {

						System.out.println("\nDocumentos enviados com sucesso!");

						// <<<<<<<<<<<<<<< ENCERRAMENTO >>>>>>>>>>>>>>>

						System.out.println("\nCadastro concluído!!\nSeja bem-vindo à B2W");

					}
				}
			}

		} else
			System.out.println("\nUsuário já realizou o cadastro!");

		// Fecha o scanner
		sc.close();

	}// Main

}// Classe