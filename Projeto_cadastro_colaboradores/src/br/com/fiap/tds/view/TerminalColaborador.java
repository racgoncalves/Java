package br.com.fiap.tds.view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.fiap.tds.bean.Colaborador;
import br.com.fiap.tds.bean.Dependente;
import br.com.fiap.tds.bean.Documento;
import br.com.fiap.tds.bean.Login;
import br.com.fiap.tds.bo.ColaboradorBo;
import br.com.fiap.tds.bo.DependenteBo;
import br.com.fiap.tds.bo.DocumentoBo;
import br.com.fiap.tds.bo.LoginBo;
import br.com.fiap.tds.exception.AtualizacaoNaoRealizadaException;
import br.com.fiap.tds.exception.ItemCadastradoException;
import br.com.fiap.tds.exception.ItemNaoEncontradoException;
import br.com.fiap.tds.validation.ContaBancoValidation;
import br.com.fiap.tds.validation.CpfValidation;
import br.com.fiap.tds.validation.DataValidation;
import br.com.fiap.tds.validation.PisValidation;
import br.com.fiap.tds.validation.ReservistaValidation;
import br.com.fiap.tds.exception.DadoInvalidoException;

public class TerminalColaborador {

	public static void main(String args[]) {

		// Atributos
		String email = "";
		String senha = "";
		String nome = "";
		String cpf = "";
		String pis = "";
		String sexo = "";
		String codigoSexo = "";
		String dataNascimento = "";
		String nacionalidade = "";
		String naturalidade = "";
		String estadoCivil = "";
		int codigoEstadoCivil = 0;
		int filhos = 0;
		String etnia = "";
		int codigoEtnia = 0;
		String agencia = "";
		String digitoAgencia = "";
		String conta = "";
		String digitoConta = "";
		String camiseta = "";
		int codigoCamiseta = 0;
		String orientacao = "";
		String religiao = "";
		String menuEditar = "";
		String menuDependente = "";
		String tipo = "";
		String nomeDocumento = "";
		String nomeArquivo = "";

		// Inicia o scanner
		Scanner sc = new Scanner(System.in);

		// Inicia classes Bean
		Login login = new Login();
		Colaborador colab = new Colaborador();
		Documento doc = new Documento();
		Documento docDep = new Documento();
		Dependente dep = new Dependente();

		// Inicia classes Bo
		LoginBo loginBo = new LoginBo();
		ColaboradorBo colabBo = new ColaboradorBo();
		DocumentoBo docBo = new DocumentoBo();
		DependenteBo depBo = new DependenteBo();

		// Inicia classes Validation
		CpfValidation cpfVal = new CpfValidation();
		PisValidation pisVal = new PisValidation();
		DataValidation dataVal = new DataValidation();
		ContaBancoValidation contaVal = new ContaBancoValidation();
		ReservistaValidation reservistaVal = new ReservistaValidation();

		// <<<<<<<<<<<<<<<<<<<<<<<< SISTEMA P/ COLABORADORES >>>>>>>>>>>>>>>>>>>>>>>>

		System.out.println("\nSistema xSolution de cadastro para novos colaboradores");

		// <<<<<<<<<<<<<<< LOGIN >>>>>>>>>>>>>>>

		System.out.println("\n1ª Etapa - Login");

		do {
			// E-MAIL
			do {
				System.out.println("\nDigite o seu email:");
				email = sc.next();
			} while (email.isEmpty());

			// SENHA
			do {
				System.out.println("\nDigite a sua senha:");
				senha = sc.next();
			} while (senha.isEmpty());

			try {
				login = loginBo.pesquisar(email, senha);
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("\nNão foi possível conectar ao servidor!");
			} catch (DadoInvalidoException e) {
				// Avisa se o e-mail e/ou a senha estiverem errados
				System.out.println(e.getMessage());
				login = null;
			}

			// Looping só termina quando logado
		} while (login == null);

		try {
			// Se o login estiver correto e não tiver finalizado o cadastro continua
			if (!login.isFinalizado()) {

				System.out.println("\nLogin efetuado com sucesso!");

				// <<<<<<<<<<<<<<< DADOS >>>>>>>>>>>>>>>

				// Checa se já enviou os dados, se ainda não enviou os dados entra
				if (!colabBo.isCadastrado(login.getMatricula())) {

					System.out.println("\n2ª Etapa - Envio de Dados");

					// Mostra saudação
					System.out.println("\nOlá " + login.getApelido() + ", seja bem-vindo!");

					// NOME COMPLETO
					do {
						System.out.println("\nDigite seu nome completo:");
						nome = sc.next() + sc.nextLine();
						nome = nome.toUpperCase();
					} while (nome.isEmpty());

					// CPF
					do {
						System.out.println("\nDigite seu CPF:\nExemplo de CPF: 60586941320");
						cpf = colab.getNumeroCpfOrPis(sc.next());

						// Checa o CPF
						if (!cpfVal.isCpf(cpf)) {
							System.out.println("\nCPF inválido!");
						}

					} while (!cpfVal.isCpf(cpf));

					// PIS
					do {
						System.out.println("\nDigite seu PIS:\nExemplo de PIS: 17033259504");
						pis = colab.getNumeroCpfOrPis(sc.next());

						// Checa o PIS
						if (!pisVal.isPis(pis)) {
							System.out.println("\nPIS inválido!");
						}

					} while (!pisVal.isPis(pis));

					// SEXO
					do {
						System.out
								.println("\nDigite seu sexo de nascimento ('F' para FEMININO ou 'M' para MASCULINO):");
						codigoSexo = sc.next();
						codigoSexo = codigoSexo.toUpperCase();
						if (!codigoSexo.matches("[F]") && !codigoSexo.matches("[M]")) {
							System.out.println("\nValor Incorreto!");
							codigoSexo = "";
						}
					} while (codigoSexo.isEmpty());

					// Pega o sexo
					sexo = colab.selecionarSexo(codigoSexo);

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
					do {

						System.out.println("\nDigite a sua data de nascimento no seguinte formato 'DD/MM/AAAA':");
						dataNascimento = sc.next();

						// Valida a data
						if (!dataVal.validarDataColaborador(dataNascimento))
							System.out.println("\nData Inválida");

					} while (!dataVal.validarDataColaborador(dataNascimento));

					// ESTADO CIVIL
					do {
						try {
							System.out.println("\n1 - SOLTEIRO" + "\n2 - CASADO" + "\n3 - DIVORCIADO" + "\n4 - VIÚVO"
									+ "\nDigite o código do seu estado civil:");
							codigoEstadoCivil = sc.nextInt();
						} catch (InputMismatchException e) {
							System.err.println("Digite somente números!!");
							sc.nextLine();
							codigoEstadoCivil = 0;
						}
					} while (codigoEstadoCivil < 1 || codigoEstadoCivil > 4);

					// Pega o estado civil
					estadoCivil = colab.selecionarEstadoCivil(codigoEstadoCivil);

					// NÚMERO FILHOS
					do {
						try {
							System.out.println("\nDigite o número de filhos que você possui:");
							filhos = sc.nextInt();
						} catch (InputMismatchException e) {
							System.err.println("Digite somente números!!");
							sc.nextLine();
							filhos = -1;
						}

					} while (filhos < 0);

					// ETNIA
					do {
						try {
							System.out.println("\n1 - BRANCO\n" + "2 - NEGRO\n" + "3 - PARDO\n" + "4 - INDÍGENA\n"
									+ "5 - NÃO INFORMAR\n" + "Digite o código da sua etnia:");
							codigoEtnia = sc.nextInt();
						} catch (InputMismatchException e) {
							System.err.println("Digite somente números!!");
							sc.nextLine();
							codigoEtnia = 0;
						}
					} while (codigoEtnia < 1 || codigoEtnia > 5);

					// Pega a etnia
					etnia = colab.selecionarEtnia(codigoEtnia);

					// TAMANHO CAMISETA
					do {
						try {
							System.out.println("\n1 - PP\n" + "2 - P\n" + "3 - M\n" + "4 - G\n" + "5 - GG\n"
									+ "Digite o código do tamanho da sua Camiseta:");
							codigoCamiseta = sc.nextInt();
						} catch (InputMismatchException e) {
							System.err.println("Digite somente números!!");
							sc.nextLine();
							codigoCamiseta = 0;
						}
					} while (codigoCamiseta < 1 || codigoCamiseta > 5);

					// Pega o tamanho da camiseta
					camiseta = colab.selecionarCamiseta(codigoCamiseta);

					// BRADESCO
					System.out.println("\nInforme os dados da sua Conta do Bradesco (corrente/salário)");

					// AGÊNCIA BRADESCO
					do {
						System.out.println("\nDigite o número da sua agência (sem o dígito verificador):\n"
								+ "Obs: A agência tem 4 números");
						agencia = sc.next();

						// Checa a agência
						if (!contaVal.isAgencia(agencia)) {
							System.out.println("\nA agência deve conter 4 números!");
						}

					} while (!contaVal.isAgencia(agencia));

					do {
						System.out.println("\nDigite o dígito verificador da agência:");
						digitoAgencia = sc.next();

						// Checa o dígito
						if (!contaVal.isDigito(digitoAgencia)) {
							System.out.println("\nValor deve ser um único número!");
						}

					} while (!contaVal.isDigito(digitoAgencia));

					// CONTA BRADESCO
					do {
						System.out.println("\nDigite o número da sua conta (sem o dígito verificador):\n"
								+ "Obs: A conta tem até 7 números");
						conta = sc.next();

						// Checa a conta
						if (!contaVal.isConta(conta)) {
							System.out.println("\nA conta deve ter entre 1 e 7 números!");
						}

					} while (!contaVal.isConta(conta));

					do {
						System.out.println("\nDigite o dígito verificador da conta:");
						digitoConta = sc.next();

						// Checa o dígito
						if (!contaVal.isDigito(digitoConta)) {
							System.out.println("\nValor deve ser um único número!");
						}

					} while (!contaVal.isDigito(digitoConta));

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

					// Envia os dados para as classes bean
					colab = new Colaborador(login, nome, cpf, pis, sexo, nacionalidade, naturalidade, dataNascimento,
							estadoCivil, filhos, etnia, camiseta, agencia, digitoAgencia, conta, digitoConta,
							orientacao, religiao);

					// Início do looping do Menu Editar
					do {

						// MENU EDIÇÂO
						System.out.println("\nDados do preenchidos:\n" + colab
								+ "\n\nPara alterar algum dado digite o seu respectivo código ou digite 'S'"
								+ " para confirmar as informações e enviar os dados.");

						menuEditar = sc.next().toUpperCase();

						// Início switch Menu Editar
						switch (menuEditar) {

						// NOME COMPLETO
						case "1":

							do {
								System.out.println("\nDigite o seu nome completo:");
								nome = sc.next() + sc.nextLine();
								nome = nome.toUpperCase();
							} while (nome.isEmpty());

							// Envia o nome
							colab.setNome(nome);

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// CPF
						case "2":

							do {
								System.out.println("\nDigite o seu CPF:\nExemplo de CPF: 48157396840");
								cpf = colab.getNumeroCpfOrPis(sc.next());

								// Checa o CPF
								if (!cpfVal.isCpf(cpf)) {
									System.out.println("\nCPF inválido!");
								}

							} while (!cpfVal.isCpf(cpf));

							// Envia o CPF
							colab.setCpf(cpf);

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// PIS
						case "3":

							do {
								System.out.println("\nDigite o seu PIS:\nExemplo de PIS: 12023362999");
								pis = colab.getNumeroCpfOrPis(sc.next());

								// Checa o PIS
								if (!pisVal.isPis(pis)) {
									System.out.println("\nPIS inválido!");
								}

							} while (!pisVal.isPis(pis));

							// Envia o PIS
							colab.setPis(pis);

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// SEXO
						case "4":

							do {
								System.out.println("\nDigite o seu sexo ('F' para FEMININO ou 'M' para MASCULINO):");
								codigoSexo = sc.next();
								codigoSexo = codigoSexo.toUpperCase();
								if (!codigoSexo.matches("[F]") && !codigoSexo.matches("[M]")) {
									System.out.println("\nValor Incorreto!");
									codigoSexo = "";
								}
							} while (codigoSexo.isEmpty());

							// Pega o sexo
							sexo = colab.selecionarSexo(codigoSexo);

							// Envia o sexo
							colab.setSexo(sexo);

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// NACIONALIDADE
						case "5":

							do {
								System.out.println("\nDigite a sua nacionalidade:");
								nacionalidade = sc.next() + sc.nextLine();
								nacionalidade = nacionalidade.toUpperCase();
							} while (nacionalidade.isEmpty());

							// Envia a nacionalidade
							colab.setNacionalidade(nacionalidade);

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// NATURALIDADE
						case "6":

							do {
								System.out.println("\nDigite a sua naturalidade:");
								naturalidade = sc.next() + sc.nextLine();
								naturalidade = naturalidade.toUpperCase();
							} while (naturalidade.isEmpty());

							// Envia a naturalidade
							colab.setNaturalidade(naturalidade);

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// DATA DE NASCIMENTO
						case "7":

							do {

								System.out
										.println("\nDigite a sua data de nascimento no seguinte formato 'DD/MM/AAAA':");
								dataNascimento = sc.next();

								// Valida a data
								if (!dataVal.validarDataColaborador(dataNascimento))
									System.out.println("\nData Inválida");

							} while (!dataVal.validarDataColaborador(dataNascimento));

							// Envia a data
							colab.setDataNascimento(dataNascimento);

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// ESTADO CIVIL
						case "8":

							do {
								try {
									System.out.println("\n1 - SOLTEIRO" + "\n2 - CASADO" + "\n3 - DIVORCIADO"
											+ "\n4 - VIÚVO" + "\nDigite o código do seu estado civil:");
									codigoEstadoCivil = sc.nextInt();
								} catch (InputMismatchException e) {
									System.err.println("Digite somente números!!");
									sc.nextLine();
									codigoEstadoCivil = 0;
								}
							} while (codigoEstadoCivil < 1 || codigoEstadoCivil > 4);

							// Pega o estado civil
							estadoCivil = colab.selecionarEstadoCivil(codigoEstadoCivil);

							// Envia o estado civil
							colab.setEstadoCivil(estadoCivil);

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// NÚMERO FILHOS
						case "9":

							do {
								try {
									System.out.println("\nDigite o número de filhos que você possui:");
									filhos = sc.nextInt();
								} catch (InputMismatchException e) {
									System.err.println("Digite somente números!!");
									sc.nextLine();
									filhos = -1;
								}

							} while (filhos < 0);

							// Envia o número de filhos
							colab.setFilhos(filhos);

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// ETNIA
						case "10":

							do {
								try {
									System.out
											.println("\n1 - BRANCO\n" + "2 - NEGRO\n" + "3 - PARDO\n" + "4 - INDÍGENA\n"
													+ "5 - NÃO INFORMAR\n" + "Digite o código da sua etnia:");
									codigoEtnia = sc.nextInt();
								} catch (InputMismatchException e) {
									System.err.println("Digite somente números!!");
									sc.nextLine();
									codigoEtnia = 0;
								}
							} while (codigoEtnia < 1 || codigoEtnia > 5);

							// Pega a etnia
							etnia = colab.selecionarEtnia(codigoEtnia);

							// Envia a etnia
							colab.setEtnia(etnia);

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// TAMANHO CAMISETA
						case "11":

							do {
								try {
									System.out.println("\n1 - PP\n" + "2 - P\n" + "3 - M\n" + "4 - G\n" + "5 - GG\n"
											+ "Digite o código do tamanho da sua camiseta:");
									codigoCamiseta = sc.nextInt();
								} catch (InputMismatchException e) {
									System.err.println("Digite somente números!!");
									sc.nextLine();
									codigoCamiseta = 0;
								}
							} while (codigoCamiseta < 1 || codigoCamiseta > 5);

							// Pega o tamanho da camiseta
							camiseta = colab.selecionarCamiseta(codigoCamiseta);

							// Envia o tamanho da camiseta
							colab.setCamiseta(camiseta);

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// AGÊNCIA BRADESCO
						case "12":

							do {
								System.out.println("\nDigite o número da sua agência (sem o dígito verificador):\n"
										+ "Obs: A agência tem 4 números");
								agencia = sc.next();

								// Checa a agência
								if (!contaVal.isAgencia(agencia)) {
									System.out.println("\nA agência deve conter 4 números!");
								}

							} while (!contaVal.isAgencia(agencia));

							do {
								System.out.println("\nDigite o dígito verificador da agência:");
								digitoAgencia = sc.next();

								// Checa o dígito
								if (!contaVal.isDigito(digitoAgencia)) {
									System.out.println("\nValor deve ser um único número!");
								}

							} while (!contaVal.isDigito(digitoAgencia));

							// Envia os dados da agência
							colab.setAgencia(agencia);
							colab.setDigitoAgencia(digitoAgencia);

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// CONTA BRADESCO
						case "13":

							do {
								System.out.println("\nDigite o número da sua conta (sem o dígito verificador):\n"
										+ "Obs: A conta tem até 7 números");
								conta = sc.next();

								// Checa a conta
								if (!contaVal.isConta(conta)) {
									System.out.println("\nA conta deve ter entre 1 e 7 números!");
								}

							} while (!contaVal.isConta(conta));

							do {
								System.out.println("\nDigite o dígito verificador da conta:");
								digitoConta = sc.next();

								// Checa o dígito
								if (!contaVal.isDigito(digitoConta)) {
									System.out.println("\nValor deve ser um único número!");
								}

							} while (!contaVal.isDigito(digitoConta));

							// Envia os dados da conta
							colab.setConta(conta);
							colab.setDigitoConta(digitoConta);

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// ORIENTAÇÃO SEXUAL
						case "14":
							do {
								System.out.println("\nQual a sua rientação sexual? (Digite N para não informar)");
								orientacao = sc.next() + sc.nextLine();
								orientacao = orientacao.toUpperCase();
								if (orientacao.equals("N")) {
									orientacao = "NÃO INFORMADO";
								}
							} while (orientacao.isEmpty());

							// Envia a orientação sexual
							colab.setOrientacao(orientacao);

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// RELIGIÃO
						case "15":

							do {
								System.out.println("\nQual a sua religião? (Digite N para não informar)");
								religiao = sc.next() + sc.nextLine();
								religiao = religiao.toUpperCase();
								if (religiao.equals("N")) {
									religiao = "NÃO INFORMADO";
								}
							} while (religiao.isEmpty());

							// Envia a religião
							colab.setReligiao(religiao);

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// CONFIRMA DADOS
						case "S":
							break;

						default:
							System.out.println("\nOpção Inválida!\n");

						}// Fim switch Menu Editar

					} while (!menuEditar.equals("S")); // Fim do looping do Menu Editar

					try {

						// Grava os dados no banco de dados
						colabBo.cadastrar(colab);

						System.out.println("\nDados enviados com sucesso!");

					} catch (Exception e) {
						System.out.println("\nNão foi possível enviar os dados!");
						e.printStackTrace();
					}

				} // !Enviou Dados

				// Se enviou os dados continua
				if (colabBo.isCadastrado(login.getMatricula())) {

					// Checa se enviou os documentos, se ainda não enviou os documentos entra
					if (!docBo.isTodosCadastrados(login.getMatricula())) {

						colab = colabBo.pesquisar(login.getMatricula());

						// <<<<<<<<<<<<<<< DOCUMENTOS >>>>>>>>>>>>>>>

						System.out.println("\n3ª Etapa - Envio de Documentos");

						do {
							// RG
							System.out.println("\nEnvie o seu RG.\nDigite o nome completo do arquivo:");
							nomeArquivo = sc.next();
							nomeDocumento = "RG";

							try {

								// Envia os dados para a classe bean e cadastra o documento
								doc = new Documento(login, nomeDocumento, nomeArquivo,
										docBo.importarArquivo(nomeArquivo));
								docBo.cadastrar(doc);

								System.out.println("\nDocumento enviado!");

							} catch (IOException e) {
								System.out.println("\nDocumento não encontrado!");
								nomeArquivo = "errado";
							} catch (DadoInvalidoException | ItemCadastradoException e) {
								System.out.println(e.getMessage());
							}

						} while (nomeArquivo.equals("errado"));

						do {

							// CARTEIRA DE TRABALHO
							System.out
									.println("\nEnvia a sua Carteira de Trabalho.\nDigite o nome completo do arquivo:");
							nomeArquivo = sc.next();
							nomeDocumento = "CARTEIRA DE TRABALHO";

							try {

								// Envia os dados para a classe bean e cadastra o documento
								doc = new Documento(login, nomeDocumento, nomeArquivo,
										docBo.importarArquivo(nomeArquivo));
								docBo.cadastrar(doc);

								System.out.println("\nDocumento enviado!");

							} catch (IOException e) {
								System.out.println("\nDocumento não encontrado!");
								nomeArquivo = "errado";
							} catch (DadoInvalidoException | ItemCadastradoException e) {
								System.out.println(e.getMessage());
							}

						} while (nomeArquivo.equals("errado"));

						do {

							// COMPROVANTE DE RESIDÊNCIA
							System.out.println(
									"\nEnvie o seu Comprovante de Residência.\nDigite o nome completo do arquivo:");
							nomeArquivo = sc.next();
							nomeDocumento = "COMPROVANTE DE RESIDÊNCIA";

							try {

								// Envia os dados para a classe bean e cadastra o documento
								doc = new Documento(login, nomeDocumento, nomeArquivo,
										docBo.importarArquivo(nomeArquivo));
								docBo.cadastrar(doc);

								System.out.println("\nDocumento enviado!");

							} catch (IOException e) {
								System.out.println("\nDocumento não encontrado!");
								nomeArquivo = "errado";
							} catch (DadoInvalidoException | ItemCadastradoException e) {
								System.out.println(e.getMessage());
							}

						} while (nomeArquivo.equals("errado"));

						do {

							// TÍTULO DE ELEITOR
							System.out.println("\nEnvie o seu Título de Eleitor.\nDigite o nome completo do arquivo:");
							nomeArquivo = sc.next();
							nomeDocumento = "TÍTULO DE ELEITOR";

							try {

								// Envia os dados para a classe bean e cadastra o documento
								doc = new Documento(login, nomeDocumento, nomeArquivo,
										docBo.importarArquivo(nomeArquivo));
								docBo.cadastrar(doc);

								System.out.println("\nDocumento enviado!");

							} catch (IOException e) {
								System.out.println("\nDocumento não encontrado!");
								nomeArquivo = "errado";
							} catch (DadoInvalidoException | ItemCadastradoException e) {
								System.out.println(e.getMessage());
							}

						} while (nomeArquivo.equals("errado"));

						do {

							// COMPROVANTE DE ESCOLARIDADE
							System.out.println(
									"\nEnvie o seu Comprovante de Escolaridade.\nDigite o nome completo do arquivo:");
							nomeArquivo = sc.next();
							nomeDocumento = "COMPROVANTE DE ESCOLARIDADE";

							try {

								// Envia os dados para a classe bean e cadastra o documento
								doc = new Documento(login, nomeDocumento, nomeArquivo,
										docBo.importarArquivo(nomeArquivo));
								docBo.cadastrar(doc);

								System.out.println("\nDocumento enviado!");

							} catch (IOException e) {
								System.out.println("\nDocumento não encontrado!");
								nomeArquivo = "errado";
							} catch (DadoInvalidoException | ItemCadastradoException e) {
								System.out.println(e.getMessage());
							}

						} while (nomeArquivo.equals("errado"));

						if (reservistaVal.isReservista(colab.getSexo(), colab.getDataNascimento())) {

							do {

								// CERTIFICADO DE RESERVISTA
								System.out.println(
										"\nEnvie o seu Certificado de Reservista.\nDigite o nome completo do arquivo:");
								nomeArquivo = sc.next();
								nomeDocumento = "CERTIFICADO DE RESERVISTA";

								try {

									// Envia os dados para a classe bean e cadastra o documento
									doc = new Documento(login, nomeDocumento, nomeArquivo,
											docBo.importarArquivo(nomeArquivo));
									docBo.cadastrar(doc);

									System.out.println("\nDocumento enviado!");

								} catch (IOException e) {
									System.out.println("\nDocumento não encontrado!");
									nomeArquivo = "errado";
								} catch (DadoInvalidoException | ItemCadastradoException e) {
									System.out.println(e.getMessage());
								}

							} while (nomeArquivo.equals("errado"));

						}

					} // !Enviou Documentos

					// Se já enviou os documentos continua
					if (docBo.isTodosCadastrados(login.getMatricula())) {

						colab = colabBo.pesquisar(login.getMatricula());

						// <<<<<<<<<<<<<<< DEPENDENTES >>>>>>>>>>>>>>>

						System.out.println("\n4ª Etapa - Adicionar os Dependentes");

						// Início do looping do Menu Dependente
						do {

							// MENU DEPENDENTE
							do {
								System.out.println("\n1 - Adicionar cônjuge" + "\n2 - Adicionar filho(a)"
										+ "\n3 - adicionar genitor(pai)" + "\n4 - adicionar genitora(mãe)"
										+ "\n5 - Finalizar o cadastro" + "\nEscolha qual operação deseja executar:");
								menuDependente = sc.next();
							} while (menuDependente.isEmpty());

							// Início switch Menu Dependente
							switch (menuDependente) {

							// CÔNJUGE
							case "1":

								tipo = "CÔNJUGE";
								nomeDocumento = "CERTIDÃO DE CASAMENTO";

								// NOME COMPLETO
								do {
									System.out.println("\nDigite o nome completo do seu cônjuge:");
									nome = sc.next() + sc.nextLine();
									nome = nome.toUpperCase();
								} while (nome.isEmpty());

								// CPF
								do {
									System.out.println("\nDigite o CPF do seu cônjuge:\nExemplo de CPF: 10907926894");
									cpf = dep.getNumeroCpfOrPis(sc.next());

									// Checa o CPF
									if (!cpfVal.isCpf(cpf)) {
										System.out.println("\nCPF inválido!");
									}

								} while (!cpfVal.isCpf(cpf));

								// SEXO
								do {
									System.out.println(
											"\nDigite o sexo de nascimento do seu cônjuge ('F' para FEMININO ou 'M' para MASCULINO):");
									codigoSexo = sc.next();
									codigoSexo = codigoSexo.toUpperCase();
									if (!codigoSexo.matches("[F]") && !codigoSexo.matches("[M]")) {
										System.out.println("\nValor Incorreto!");
										codigoSexo = "";
									}
								} while (codigoSexo.isEmpty());

								// Pega o sexo
								sexo = dep.selecionarSexo(codigoSexo);

								// DATA DE NASCIMENTO
								do {

									System.out.println(
											"\nDigite a sua data de nascimento do seu cônjuge no seguinte formato 'DD/MM/AAAA':");
									dataNascimento = sc.next();

									// Valida a data
									if (!dataVal.validarDataDependente(dataNascimento))
										System.out.println("\nData Inválida");

								} while (!dataVal.validarDataDependente(dataNascimento));

								// CERTIDÃO DE CASAMENTO
								System.out.println(
										"\nEnvie a sua Certidão de Casamento.\nDigite o nome completo do arquivo:");
								nomeArquivo = sc.next();

								try {

									// Envia os dados para as classes bean e cadastra o dependente
									docDep = new Documento(nomeDocumento, nomeArquivo,
											docBo.importarArquivo(nomeArquivo));
									dep = new Dependente(login, tipo, nome, cpf, sexo, dataNascimento, docDep);
									depBo.cadastrar(dep);

									System.out.println("\nDependente enviado!");

								} catch (IOException e) {
									System.out.println("\nDocumento não encontrado!");
								} catch (DadoInvalidoException | ItemCadastradoException e) {
									System.out.println(e.getMessage());
								}

								break;

							// FILHO
							case "2":

								tipo = "FILHO";
								nomeDocumento = "CERTIDÃO DE NASCIMENTO";

								// NOME COMPLETO
								do {
									System.out.println("\nDigite o nome completo do seu filho:");
									nome = sc.next() + sc.nextLine();
									nome = nome.toUpperCase();
								} while (nome.isEmpty());

								// CPF
								do {
									System.out.println("\nDigite o CPF do seu filho:\nExemplo de CPF: 93303777268");
									cpf = dep.getNumeroCpfOrPis(sc.next());

									// Checa o CPF
									if (!cpfVal.isCpf(cpf)) {
										System.out.println("\nCPF inválido!");
									}

								} while (!cpfVal.isCpf(cpf));

								// SEXO
								do {
									System.out.println(
											"\nDigite o sexo de nascimento do seu filho ('F' para FEMININO, 'M' para MASCULINO):");
									codigoSexo = sc.next();
									codigoSexo = codigoSexo.toUpperCase();
									if (!codigoSexo.matches("[F]") && !codigoSexo.matches("[M]")) {
										System.out.println("\nValor Incorreto!");
										codigoSexo = "";
									}
								} while (codigoSexo.isEmpty());

								// Pega o sexo
								sexo = dep.selecionarSexo(codigoSexo);

								// DATA DE NASCIMENTO
								do {

									System.out.println(
											"\nDigite a sua data de nascimento do seu cônjuge no seguinte formato 'DD/MM/AAAA':");
									dataNascimento = sc.next();

									// Valida a data
									if (!dataVal.validarDataDependente(dataNascimento))
										System.out.println("\nData Inválida");

								} while (!dataVal.validarDataDependente(dataNascimento));

								// CERTIDÃO DE NASCIMENTO
								System.out.println("\nEnvie a Certidão de Nascimento do " + nome
										+ ".\nDigite o nome completo do arquivo:");
								nomeArquivo = sc.next();

								try {

									// Envia os dados para as classes bean e cadastra o dependente
									docDep = new Documento(nomeDocumento, nomeArquivo,
											docBo.importarArquivo(nomeArquivo));
									dep = new Dependente(login, tipo, nome, cpf, sexo, dataNascimento, docDep);
									depBo.cadastrar(dep);

									System.out.println("\nDependente enviado!");

								} catch (IOException e) {
									System.out.println("\nDocumento não encontrado!");
								} catch (DadoInvalidoException | ItemCadastradoException e) {
									System.out.println(e.getMessage());
								}

								break;

							// PAI
							case "3":

								tipo = "GENITOR";
								nomeDocumento = "RG";
								sexo = "MASCULINO";

								// NOME COMPLETO
								do {
									System.out.println("\nDigite o nome completo do seu pai:");
									nome = sc.next() + sc.nextLine();
									nome = nome.toUpperCase();
								} while (nome.isEmpty());

								// CPF
								do {
									System.out.println("\nDigite o CPF do seu pai:\nExemplo de CPF: 92696376034");
									cpf = dep.getNumeroCpfOrPis(sc.next());

									// Checa o CPF
									if (!cpfVal.isCpf(cpf)) {
										System.out.println("\nCPF inválido!");
									}

								} while (!cpfVal.isCpf(cpf));

								// DATA DE NASCIMENTO
								do {

									System.out.println(
											"\nDigite a sua data de nascimento do seu cônjuge no seguinte formato 'DD/MM/AAAA':");
									dataNascimento = sc.next();

									// Valida a data
									if (!dataVal.validarDataDependente(dataNascimento))
										System.out.println("\nData Inválida");

								} while (!dataVal.validarDataDependente(dataNascimento));

								// RG
								System.out.println("\nEnvie o RG de seu pai.\nDigite o nome completo do arquivo:");
								nomeArquivo = sc.next();

								try {

									// Envia os dados para as classes bean e cadastra o dependente
									docDep = new Documento(nomeDocumento, nomeArquivo,
											docBo.importarArquivo(nomeArquivo));
									dep = new Dependente(login, tipo, nome, cpf, sexo, dataNascimento, docDep);
									depBo.cadastrar(dep);

									System.out.println("\nDependente enviado!");

								} catch (IOException e) {
									System.out.println("\nDocumento não encontrado!");
								} catch (DadoInvalidoException | ItemCadastradoException e) {
									System.out.println(e.getMessage());
								}

								break;

							// MÃE
							case "4":

								tipo = "GENITOR";
								nomeDocumento = "RG";
								sexo = "FEMININO";

								// NOME COMPLETO
								do {
									System.out.println("\nDigite o nome completo da sua mãe:");
									nome = sc.next() + sc.nextLine();
									nome = nome.toUpperCase();
								} while (nome.isEmpty());

								// CPF
								do {
									System.out.println("\nDigite o CPF da sua mãe:\nExemplo de CPF: 04685211600");
									cpf = dep.getNumeroCpfOrPis(sc.next());

									// Checa o CPF
									if (!cpfVal.isCpf(cpf)) {
										System.out.println("\nCPF inválido!");
									}

								} while (!cpfVal.isCpf(cpf));

								// DATA DE NASCIMENTO
								do {

									System.out.println(
											"\nDigite a sua data de nascimento do seu cônjuge no seguinte formato 'DD/MM/AAAA':");
									dataNascimento = sc.next();

									// Valida a data
									if (!dataVal.validarDataDependente(dataNascimento))
										System.out.println("\nData Inválida");

								} while (!dataVal.validarDataDependente(dataNascimento));

								// RG
								System.out.println("\nEnvie o RG de sua mãe.\nDigite o nome completo do arquivo:");
								nomeArquivo = sc.next();

								try {

									// Envia os dados para as classes bean e cadastra o dependente
									docDep = new Documento(nomeDocumento, nomeArquivo,
											docBo.importarArquivo(nomeArquivo));
									dep = new Dependente(login, tipo, nome, cpf, sexo, dataNascimento, docDep);
									depBo.cadastrar(dep);

									System.out.println("\nDependente enviado!");

								} catch (IOException e) {
									System.out.println("\nDocumento não encontrado!");
								} catch (DadoInvalidoException | ItemCadastradoException e) {
									System.out.println(e.getMessage());
								}

								break;

							// SAIR
							case "5":
								break;

							default:
								System.out.println("\nOpção Inválida!");

							}// Fim switch Menu Dependente

						} while (!menuDependente.equals("5")); // Fim do looping do Menu
																// Dependente

						// STATUS CADASTRADO
						try {
							login.setFinalizado(true);
							loginBo.atualizar(login);
						} catch (AtualizacaoNaoRealizadaException | DadoInvalidoException e) {
							System.out.println(e.getMessage());
						}

						// <<<<<<<<<<<<<<< ENCERRAMENTO >>>>>>>>>>>>>>>

						// Mensagem de encerramento
						System.out.println("\nCadastro concluído!!\nSeja bem-vindo à B2W");

					} // Enviou Documentos

				} // Enviou Dados

			} // Logado

			else
				System.out.println("\nUsuário já realizou o cadastro!");

		} catch (ItemNaoEncontradoException e) {
			System.out.println(e.getMessage());

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("\nOperação não realizada!");
		}

		// Fecha o scanner
		sc.close();

	}// Main
}// Classe