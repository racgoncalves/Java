package br.com.fiap.tds.view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.fiap.tds.bean.ContaBanco;
import br.com.fiap.tds.bean.Dados;
import br.com.fiap.tds.bean.DadosDependente;
import br.com.fiap.tds.bean.Dependente;
import br.com.fiap.tds.bean.Documento;
import br.com.fiap.tds.dao.DadosDao;
import br.com.fiap.tds.dao.DependenteDao;
import br.com.fiap.tds.dao.DocumentoDao;
import br.com.fiap.tds.dao.LoginDao;
import br.com.fiap.tds.exception.AtualizacaoNaoRealizadaException;
import br.com.fiap.tds.exception.ItemCadastradoException;
import br.com.fiap.tds.exception.ItemNaoEncontradoException;
import br.com.fiap.tds.exception.OperacaoInvalidaException;
import br.com.fiap.tds.dao.ColaboradorDao;
import br.com.fiap.tds.dao.ContaBancoDao;

public class TerminalColaborador {

	public static void main(String args[]) {

		// Atributos
		String email = "";
		int matricula = 0;
		int senha = 0;
		int cadastrado = 0;
		String apelido = "";
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
		String menuEditar = "";
		String menuDependente = "";
		String tipo = "";
		String nomeDocumento = "";
		String nomeArquivo = "";

		// Inicia o scanner
		Scanner sc = new Scanner(System.in);

		// Inicia classes Bean
		Dados dados = new Dados();
		ContaBanco contaBanco = new ContaBanco();
		Documento doc = new Documento();
		Dependente dep = new Dependente();
		DadosDependente dadosDep = new DadosDependente();

		// Inicia classes Dao
		ColaboradorDao colaboradorDao = new ColaboradorDao();
		DadosDao dadosDao = new DadosDao();
		ContaBancoDao contaDao = new ContaBancoDao();
		DocumentoDao docDao = new DocumentoDao();
		DependenteDao depDao = new DependenteDao();
		LoginDao loginDao = new LoginDao();

		System.out.println("\nSistema de cadastro para novos funcion�rios");

		// <<<<<<<<<<<<<<< LOGIN >>>>>>>>>>>>>>>

		System.out.println("\n1� Etapa - Login");

		try {
			do {
				// E-MAIL
				do {
					System.out.println("\nDigite o seu email:");
					email = sc.next();
				} while (email.isEmpty());

				// SENHA

				do {
					try {
						System.out.println("\nDigite a sua senha (n� de matr�cula):");
						senha = sc.nextInt();
					} catch (InputMismatchException e) {
						System.err.println("Digite somente n�meros!!");
						sc.nextLine();
						senha = 0;
					}
				} while (senha == 0);

				// Avisa se o e-mail e/ou a senha estiverem errados
				if (!loginDao.colaborador(email, senha))
					System.out.println("\nUsu�rio e/ou senha incorretos!");

				// Looping s� termina quando logado
			} while (!loginDao.colaborador(email, senha));

		} catch (Exception e) {
			System.out.println("\nN�o foi poss�vel realizar o login!");
		}

		try {
			// Se o login estiver correto e n�o tiver feito o cadastro continua
			if (loginDao.colaborador(email, senha) && !colaboradorDao.getStatus(email)) {

				System.out.println("\nLogin efetuado com sucesso!");

				// Pega a matr�cula
				matricula = senha;

				// <<<<<<<<<<<<<<< DADOS >>>>>>>>>>>>>>>

				// Checa se j� enviou os dados, se ainda n�o enviou os dados entra
				if (!dadosDao.isCadastrado(matricula)) {

					System.out.println("\n2� Etapa - Envio de Dados");

					// Pega o apelido
					try {
						apelido = colaboradorDao.getApelido(matricula);

						// Mostra sauda��o
						System.out.println("\nOl� " + apelido + ", seja bem-vindo!");
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}

					// NOME COMPLETO
					do {
						System.out.println("\nDigite seu nome completo:");
						nome = sc.next() + sc.nextLine();
						nome = nome.toUpperCase();
					} while (nome.isEmpty());

					// CPF
					do {
						System.out.println("\nDigite seu CPF (sem pontos nem tra�os - apenas n�meros):\n"
								+ "Exemplo de CPF: 60586941320");
						cpf = sc.next();

						// Checa o CPF
						if (!dados.isCPF(cpf)) {
							System.out.println("\nCPF inv�lido!");
						}

					} while (!dados.isCPF(cpf));

					// PIS
					do {
						System.out.println("\nDigite seu PIS (sem pontos nem tra�os - apenas n�meros):\n"
								+ "Exemplo de PIS: 17033259504");
						pis = sc.next();

						// Checa o PIS
						if (!dados.isPis(pis)) {
							System.out.println("\nPIS inv�lido!");
						}

					} while (!dados.isPis(pis));

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
					sexo = dados.selecionarSexo(codigoSexo);

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
						// ANO
						do {
							try {
								System.out.println("\nDigite o ano em que voc� nasceu:");
								ano = sc.nextInt();

								// Checa o ano
								if (!dados.validarAno(ano)) {
									System.out.println("\nData Inv�lida");
								}
							} catch (InputMismatchException e) {
								System.err.println("Digite somente n�meros!!");
								sc.nextLine();
								ano = 0;
							}

						} while (!dados.validarAno(ano));

						// M�S
						do {
							try {
								System.out.println("\nDigite o m�s em que voc� nasceu:");
								mes = sc.nextInt();

								// Checa o m�s
								if (!dados.validarMes(mes)) {
									System.out.println("\nData Inv�lida");
								}
							} catch (InputMismatchException e) {
								System.err.println("Digite somente n�meros!!");
								sc.nextLine();
								mes = 0;
							}
						} while (!dados.validarMes(mes));

						// DIA
						do {
							try {
								System.out.println("\nDigite o dia em que voc� nasceu:");
								dia = sc.nextInt();

								// Checa o dia
								if (!dados.validarDia(dia, mes)) {
									System.out.println("\nData Inv�lida");
								}
							} catch (InputMismatchException e) {
								System.err.println("Digite somente n�meros!!");
								sc.nextLine();
								dia = 0;
							}
						} while (!dados.validarDia(dia, mes));

						// Valida a data
						if (!dados.validarData(dia, mes, ano))
							System.out.println("\nData Inv�lida");

					} while (!dados.validarData(dia, mes, ano));

					// Monta a data
					dataNascimento = dados.montarData(dia, mes, ano);

					// ESTADO CIVIL
					do {
						try {
							System.out.println("\n1 - SOLTEIRO" + "\n2 - CASADO" + "\n3 - DIVORCIADO" + "\n4 - VI�VO"
									+ "\nDigite o c�digo do seu estado civil:");
							codigoEstadoCivil = sc.nextInt();
						} catch (InputMismatchException e) {
							System.err.println("Digite somente n�meros!!");
							sc.nextLine();
							codigoEstadoCivil = 0;
						}
					} while (codigoEstadoCivil < 1 || codigoEstadoCivil > 4);

					// Pega o estado civil
					estadoCivil = dados.selecionarEstadoCivil(codigoEstadoCivil);

					// N�MERO FILHOS
					do {
						try {
							System.out.println("\nDigite o n�mero de filhos que voc� possui:");
							numeroFilhos = sc.nextInt();
						} catch (InputMismatchException e) {
							System.err.println("Digite somente n�meros!!");
							sc.nextLine();
							numeroFilhos = -1;
						}

					} while (numeroFilhos < 0);

					// ETNIA
					do {
						try {
							System.out.println("\n1 - BRANCO\n" + "2 - NEGRO\n" + "3 - PARDO\n" + "4 - IND�GENA\n"
									+ "5 - N�O INFORMAR\n" + "Digite o c�digo da sua etnia:");
							codigoEtnia = sc.nextInt();
						} catch (InputMismatchException e) {
							System.err.println("Digite somente n�meros!!");
							sc.nextLine();
							codigoEtnia = 0;
						}
					} while (codigoEtnia < 1 || codigoEtnia > 5);

					// Pega a etnia
					etnia = dados.selecionarEtnia(codigoEtnia);

					// TAMANHO CAMISETA
					do {
						try {
							System.out.println("\n1 - PP\n" + "2 - P\n" + "3 - M\n" + "4 - G\n" + "5 - GG\n"
									+ "Digite o c�digo do tamanho da sua Camiseta:");
							codigoTamanhoCamiseta = sc.nextInt();
						} catch (InputMismatchException e) {
							System.err.println("Digite somente n�meros!!");
							sc.nextLine();
							codigoTamanhoCamiseta = 0;
						}
					} while (codigoTamanhoCamiseta < 1 || codigoTamanhoCamiseta > 5);

					// Pega o tamanho da camiseta
					tamanhoCamiseta = dados.selecionarTamanhoCamiseta(codigoTamanhoCamiseta);

					// BRADESCO
					System.out.println("\nInforme os dados da sua Conta do Bradesco (corrente/sal�rio)");

					// AG�NCIA BRADESCO
					do {
						System.out.println("\nDigite o n�mero da sua ag�ncia (sem o d�gito verificador):\n"
								+ "Obs: O n�mero da ag�ncia tem 4 d�gitos");
						agenciaNumero = sc.next();

						// Limita os d�gitos
						if (agenciaNumero.length() != 4) {
							System.out.println("\nValor Incorreto!");
						}

						// Apenas valores num�ricos
						else if (!agenciaNumero.matches("[0-9]+")) {
							System.out.println("\nDigite somente n�meros!");
						}

					} while (agenciaNumero.length() != 4 || !agenciaNumero.matches("[0-9]+"));

					do {
						System.out.println("\nDigite o d�gito verificador da sua ag�ncia:");
						agenciaDigito = sc.next();

						// Limita para apenas 1 d�gito e valor num�rico
						if (!agenciaDigito.matches("[0-9]")) {
							System.out.println("\nValor Incorreto!");
						}

					} while (!agenciaDigito.matches("[0-9]"));

					// CONTA BRADESCO
					do {
						System.out.println("\nDigite o n�mero da sua conta (sem o d�gito verificador):\n"
								+ "Obs: O n�mero da conta tem at� 7 d�gitos");
						contaNumero = sc.next();

						// Limite os d�gitos
						if (contaNumero.length() > 7 || contaNumero.length() < 1) {
							System.out.println("\nValor Incorreto!");
						}

						// Apenas valores num�ricos
						else if (!contaNumero.matches("[0-9]+")) {
							System.out.println("\nDigite somente n�meros!");
						}

					} while (contaNumero.length() > 7 || contaNumero.length() < 1 || !contaNumero.matches("[0-9]+"));

					do {
						System.out.println("\nDigite o d�gito verificador da sua conta:");
						contaDigito = sc.next();

						// Limita para apenas 1 d�gito e valor num�rico
						if (!contaDigito.matches("[0-9]")) {
							System.out.println("\nValor Incorreto!");
						}

					} while (!contaDigito.matches("[0-9]"));

					// DADOS SENS�VEIS
					System.out.println("\nAs perguntas abaixo fazem parte de uma pesquisa da B2W, "
							+ "esses dados n�o ser�o divulgados nem utilizados em processos seletivos "
							+ "da empresa e voc� n�o � obrigado a inform�-los.");

					// ORIENTA��O SEXUAL
					do {
						System.out.println("\nQual a sua orienta��o sexual? (Digite N para n�o informar)");
						orientacao = sc.next() + sc.nextLine();
						orientacao = orientacao.toUpperCase();
						if (orientacao.equals("N")) {
							orientacao = "N�O INFORMADO";
						}
					} while (orientacao.isEmpty());

					// RELIGI�O
					do {
						System.out.println("\nQual a sua religi�o? (Digite N para n�o informar)");
						religiao = sc.next() + sc.nextLine();
						religiao = religiao.toUpperCase();
						if (religiao.equals("N")) {
							religiao = "N�O INFORMADO";
						}
					} while (religiao.isEmpty());

					// Envia os dados para as classes bean
					dados = new Dados(nome, cpf, pis, sexo, nacionalidade, naturalidade, dataNascimento, estadoCivil,
							numeroFilhos, etnia, tamanhoCamiseta, orientacao, religiao);

					contaBanco = new ContaBanco(Integer.parseInt(agenciaNumero), Integer.parseInt(agenciaDigito),
							Integer.parseInt(contaNumero), Integer.parseInt(contaDigito));

					// In�cio do looping do Menu Editar
					do {

						// MENU EDI��O
						System.out.println("\nDados do preenchidos:\n" + dados.toStringColaborador()
								+ contaBanco.toStringColaborador()
								+ "\n\nPara alterar algum dado digite o seu respectivo c�digo ou digite 'S'"
								+ " para confirmar as informa��es e enviar os dados.");

						menuEditar = sc.next().toUpperCase();

						// In�cio switch Menu Editar
						switch (menuEditar) {

						// NOME COMPLETO
						case "1":

							do {
								System.out.println("\nDigite o seu nome completo:");
								nome = sc.next() + sc.nextLine();
								nome = nome.toUpperCase();
							} while (nome.isEmpty());

							// Envia o nome
							dados.setNome(nome);

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// CPF
						case "2":

							do {
								System.out.println("\nDigite o seu CPF (sem pontos nem tra�os - apenas n�meros):\n"
										+ "Exemplo de CPF: 48157396840");
								cpf = sc.next();

								// Checa o CPF
								if (!dados.isCPF(cpf)) {
									System.out.println("\nCPF inv�lido!");
								}

							} while (!dados.isCPF(cpf));

							// Envia o CPF
							dados.setCpf(cpf);

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// PIS
						case "3":

							do {
								System.out.println("\nDigite o seu PIS (sem pontos nem tra�os - apenas n�meros):\n"
										+ "Exemplo de PIS: 12023362999");
								pis = sc.next();

								// Checa o PIS
								if (!dados.isPis(pis)) {
									System.out.println("\nPIS inv�lido!");
								}

							} while (!dados.isPis(pis));

							// Envia o PIS
							dados.setPis(pis);

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// SEXO
						case "4":

							do {
								System.out.println(
										"\nDigite o sexo do colaborador ('F' para FEMININO ou 'M' para MASCULINO):");
								codigoSexo = sc.next();
								codigoSexo = codigoSexo.toUpperCase();
								if (!codigoSexo.matches("[F]") && !codigoSexo.matches("[M]")) {
									System.out.println("\nValor Incorreto!");
									codigoSexo = "";
								}
							} while (codigoSexo.isEmpty());

							// Monta o sexo
							sexo = dados.selecionarSexo(codigoSexo);

							// Envia o sexo
							dados.setSexo(sexo);

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// NACIONALIDADE
						case "5":

							do {
								System.out.println("\nDigite a nacionalidade do colaborador:");
								nacionalidade = sc.next() + sc.nextLine();
								nacionalidade = nacionalidade.toUpperCase();
							} while (nacionalidade.isEmpty());

							// Envia a nacionalidade
							dados.setNacionalidade(nacionalidade);

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// NATURALIDADE
						case "6":

							do {
								System.out.println("\nDigite a naturalidade do colaborador:");
								naturalidade = sc.next() + sc.nextLine();
								naturalidade = naturalidade.toUpperCase();
							} while (naturalidade.isEmpty());

							// Envia a naturalidade
							dados.setNaturalidade(naturalidade);

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// DATA DE NASCIMENTO
						case "7":
							do {
								// ANO
								do {
									try {
										System.out.println("\nDigite o ano em que o colaborador nasceu:");
										ano = sc.nextInt();

										// Checa o ano
										if (!dados.validarAno(ano)) {
											System.out.println("\nData Inv�lida");
										}
									} catch (InputMismatchException e) {
										System.err.println("Digite somente n�meros!!");
										sc.nextLine();
										ano = 0;
									}

								} while (!dados.validarAno(ano));

								// M�S
								do {
									try {
										System.out.println("\nDigite o m�s em que o colaborador nasceu:");
										mes = sc.nextInt();

										// Checa o m�s
										if (!dados.validarMes(mes)) {
											System.out.println("\nData Inv�lida");
										}
									} catch (InputMismatchException e) {
										System.err.println("Digite somente n�meros!!");
										sc.nextLine();
										mes = 0;
									}
								} while (!dados.validarMes(mes));

								// DIA
								do {
									try {
										System.out.println("\nDigite o dia em que o colaborador nasceu:");
										dia = sc.nextInt();

										// Checa o dia
										if (!dados.validarDia(dia, mes)) {
											System.out.println("\nData Inv�lida");
										}
									} catch (InputMismatchException e) {
										System.err.println("Digite somente n�meros!!");
										sc.nextLine();
										dia = 0;
									}
								} while (!dados.validarDia(dia, mes));

								// Valida a data
								if (!dados.validarData(dia, mes, ano))
									System.out.println("\nData Inv�lida");

							} while (!dados.validarData(dia, mes, ano));

							// Monta a data
							dataNascimento = dados.montarData(dia, mes, ano);

							// Envia a data
							dados.setDataNascimento(dataNascimento);

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// ESTADO CIVIL
						case "8":

							do {
								try {
									System.out.println("\n1 - SOLTEIRO" + "\n2 - CASADO" + "\n3 - DIVORCIADO"
											+ "\n4 - VI�VO" + "\nDigite o c�digo do seu estado civil:");
									codigoEstadoCivil = sc.nextInt();
								} catch (InputMismatchException e) {
									System.err.println("Digite somente n�meros!!");
									sc.nextLine();
									codigoEstadoCivil = 0;
								}
							} while (codigoEstadoCivil < 1 || codigoEstadoCivil > 4);

							// Pega o estado civil
							estadoCivil = dados.selecionarEstadoCivil(codigoEstadoCivil);

							// Envia o estado civil
							dados.setEstadoCivil(estadoCivil);

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// N�MERO FILHOS
						case "9":

							do {
								try {
									System.out.println("\nDigite o n�mero de filhos que o colaborador possui:");
									numeroFilhos = sc.nextInt();
								} catch (InputMismatchException e) {
									System.err.println("Digite somente n�meros!!");
									sc.nextLine();
									numeroFilhos = -1;
								}

							} while (numeroFilhos < 0);

							// Envia o n�mero de filhos
							dados.setNumeroFilhos(numeroFilhos);

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// ETNIA
						case "10":

							do {
								try {
									System.out
											.println("\n1 - BRANCO\n" + "2 - NEGRO\n" + "3 - PARDO\n" + "4 - IND�GENA\n"
													+ "5 - N�O INFORMAR\n" + "Digite o c�digo da sua etnia:");
									codigoEtnia = sc.nextInt();
								} catch (InputMismatchException e) {
									System.err.println("Digite somente n�meros!!");
									sc.nextLine();
									codigoEtnia = 0;
								}
							} while (codigoEtnia < 1 || codigoEtnia > 5);

							// Pega a etnia
							etnia = dados.selecionarEtnia(codigoEtnia);

							// Envia a etnia
							dados.setEtnia(etnia);

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// TAMANHO CAMISETA
						case "11":

							do {
								try {
									System.out.println("\n1 - PP\n" + "2 - P\n" + "3 - M\n" + "4 - G\n" + "5 - GG\n"
											+ "Digite o c�digo do tamanho da camiseta do colaborador:");
									codigoTamanhoCamiseta = sc.nextInt();
								} catch (InputMismatchException e) {
									System.err.println("Digite somente n�meros!!");
									sc.nextLine();
									codigoTamanhoCamiseta = 0;
								}
							} while (codigoTamanhoCamiseta < 1 || codigoTamanhoCamiseta > 5);

							// Pega o tamanho da camiseta
							tamanhoCamiseta = dados.selecionarTamanhoCamiseta(codigoTamanhoCamiseta);

							// Envia o tamanho da camiseta
							dados.setTamanhoCamiseta(tamanhoCamiseta);

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// ORIENTA��O SEXUAL
						case "12":
							do {
								System.out.println(
										"\nQual a rienta��o sexual do colaborador? (Digite N para n�o informar)");
								orientacao = sc.next() + sc.nextLine();
								orientacao = orientacao.toUpperCase();
								if (orientacao.equals("N")) {
									orientacao = "N�O INFORMADO";
								}
							} while (orientacao.isEmpty());

							// Envia a orienta��o sexual
							dados.setOrientacao(orientacao);

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// RELIGI�O
						case "13":

							do {
								System.out.println("\nQual a religi�o do colaborador? (Digite N para n�o informar)");
								religiao = sc.next() + sc.nextLine();
								religiao = religiao.toUpperCase();
								if (religiao.equals("N")) {
									religiao = "N�O INFORMADO";
								}
							} while (religiao.isEmpty());

							// Envia a religi�o
							dados.setReligiao(religiao);

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// AG�NCIA BRADESCO
						case "14":

							do {
								System.out.println(
										"\nDigite o n�mero da ag�ncia do colaborador (sem o d�gito verificador):\n"
												+ "Obs: O n�mero da ag�ncia tem 4 d�gitos");
								agenciaNumero = sc.next();

								// Limita os d�gitos
								if (agenciaNumero.length() != 4) {
									System.out.println("\nValor Incorreto!");
								}

								// Apenas valores num�ricos
								else if (!agenciaNumero.matches("[0-9]+")) {
									System.out.println("\nDigite somente n�meros!");
								}

							} while (agenciaNumero.length() != 4 || !agenciaNumero.matches("[0-9]+"));

							do {
								System.out.println("\nDigite o d�gito verificador da ag�ncia:");
								agenciaDigito = sc.next();

								// Limita para apenas 1 d�gito e valor num�rico
								if (!agenciaDigito.matches("[0-9]")) {
									System.out.println("\nValor Incorreto!");
								}

							} while (!agenciaDigito.matches("[0-9]"));

							// Envia os dados da ag�ncia
							contaBanco.setAgencia(Integer.parseInt(agenciaNumero));
							contaBanco.setDigitoAgencia(Integer.parseInt(agenciaDigito));

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// CONTA BRADESCO
						case "15":

							do {
								System.out.println(
										"\nDigite o n�mero da conta do colaborador (sem o d�gito verificador):\n"
												+ "Obs: O n�mero da conta tem at� 7 d�gitos");
								contaNumero = sc.next();

								// Limita os d�gitos
								if (contaNumero.length() > 7 || contaNumero.length() < 1) {
									System.out.println("\nValor Incorreto!");
								}

								// Apenas valores num�ricos
								else if (!contaNumero.matches("[0-9]+")) {
									System.out.println("\nDigite somente n�meros!");
								}

							} while (contaNumero.length() > 7 || contaNumero.length() < 1
									|| !contaNumero.matches("[0-9]+"));

							do {
								System.out.println("\nDigite o d�gito verificador da conta:");
								contaDigito = sc.next();

								// Limita para apenas 1 d�gito e valor num�rico
								if (!contaDigito.matches("[0-9]")) {
									System.out.println("\nValor Incorreto!");
								}

							} while (!contaDigito.matches("[0-9]"));

							// Envia os dados da conta
							contaBanco.setConta(Integer.parseInt(contaNumero));
							contaBanco.setDigitoConta(Integer.parseInt(contaDigito));

							System.out.println("\nDado atualizado com sucesso!");

							break;

						// CONFIRMA DADOS
						case "S":
							break;

						default:
							System.out.println("\nOp��o Inv�lida!\n");

						}// Fim switch Menu Editar

					} while (!menuEditar.equals("S")); // Fim do looping do Menu Editar

					try {

						// Grava os dados no banco de dados
						dadosDao.cadastrar(matricula, dados);
						contaDao.cadastrar(matricula, contaBanco);

						System.out.println("\nDados enviados com sucesso!");

					} catch (Exception e) {
						System.out.println("\nN�o foi poss�vel enviar os dados!");
						e.printStackTrace();
					}

				} // !Enviou Dados

				// Se enviou os dados continua
				if (dadosDao.isCadastrado(matricula)) {

					// Checa se enviou os documentos, se ainda n�o enviou os documentos entra
					if (!docDao.isTodosCadastrados(matricula)) {

						dados = dadosDao.pesquisar(matricula);

						// <<<<<<<<<<<<<<< DOCUMENTOS >>>>>>>>>>>>>>>

						System.out.println("\n3� Etapa - Envio de Documentos");

						do {
							// RG
							System.out.println("\nEnvie o seu RG.\nDigite o nome completo do arquivo:");
							nomeArquivo = sc.next();
							nomeDocumento = "RG";

							try {

								// Envia os dados para a classe bean e cadastra o documento
								doc = new Documento(nomeDocumento, nomeArquivo, docDao.importarArquivo(nomeArquivo));
								docDao.cadastrar(matricula, doc);

								System.out.println("\nDocumento enviado!");

							} catch (OperacaoInvalidaException e) {
								System.out.println(e.getMessage());
							} catch (ItemCadastradoException e) {
								System.out.println(e.getMessage());
							} catch (IOException e) {
								System.out.println("\nDocumento n�o encontrado!");
								nomeArquivo = "errado";
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
								doc = new Documento(nomeDocumento, nomeArquivo, docDao.importarArquivo(nomeArquivo));
								docDao.cadastrar(matricula, doc);

								System.out.println("\nDocumento enviado!");

							} catch (OperacaoInvalidaException e) {
								System.out.println(e.getMessage());
							} catch (ItemCadastradoException e) {
								System.out.println(e.getMessage());
							} catch (IOException e) {
								System.out.println("\nDocumento n�o encontrado!");
								nomeArquivo = "errado";
							}

						} while (nomeArquivo.equals("errado"));

						do {

							// COMPROVANTE DE RESID�NCIA
							System.out.println(
									"\nEnvie o seu Comprovante de Resid�ncia.\nDigite o nome completo do arquivo:");
							nomeArquivo = sc.next();
							nomeDocumento = "COMPROVANTE DE RESID�NCIA";

							try {

								// Envia os dados para a classe bean e cadastra o documento
								doc = new Documento(nomeDocumento, nomeArquivo, docDao.importarArquivo(nomeArquivo));
								docDao.cadastrar(matricula, doc);

								System.out.println("\nDocumento enviado!");

							} catch (OperacaoInvalidaException e) {
								System.out.println(e.getMessage());
							} catch (ItemCadastradoException e) {
								System.out.println(e.getMessage());
							} catch (IOException e) {
								System.out.println("\nDocumento n�o encontrado!");
								nomeArquivo = "errado";
							}

						} while (nomeArquivo.equals("errado"));

						do {

							// T�TULO DE ELEITOR
							System.out.println("\nEnvie o seu T�tulo de Eleitor.\nDigite o nome completo do arquivo:");
							nomeArquivo = sc.next();
							nomeDocumento = "T�TULO DE ELEITOR";

							try {

								// Envia os dados para a classe bean e cadastra o documento
								doc = new Documento(nomeDocumento, nomeArquivo, docDao.importarArquivo(nomeArquivo));
								docDao.cadastrar(matricula, doc);

								System.out.println("\nDocumento enviado!");

							} catch (OperacaoInvalidaException e) {
								System.out.println(e.getMessage());
							} catch (ItemCadastradoException e) {
								System.out.println(e.getMessage());
							} catch (IOException e) {
								System.out.println("\nDocumento n�o encontrado!");
								nomeArquivo = "errado";
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
								doc = new Documento(nomeDocumento, nomeArquivo, docDao.importarArquivo(nomeArquivo));
								docDao.cadastrar(matricula, doc);

								System.out.println("\nDocumento enviado!");

							} catch (OperacaoInvalidaException e) {
								System.out.println(e.getMessage());
							} catch (ItemCadastradoException e) {
								System.out.println(e.getMessage());
							} catch (IOException e) {
								System.out.println("\nDocumento n�o encontrado!");
								nomeArquivo = "errado";
							}

						} while (nomeArquivo.equals("errado"));

						if (doc.isReservista(dados.getSexo(), dados.getDataNascimento())) {

							do {

								// CERTIFICADO DE RESERVISTA
								System.out.println(
										"\nEnvie o seu Certificado de Reservista.\nDigite o nome completo do arquivo:");
								nomeArquivo = sc.next();
								nomeDocumento = "CERTIFICADO DE RESERVISTA";

								try {

									// Envia os dados para a classe bean e cadastra o documento
									doc = new Documento(nomeDocumento, nomeArquivo, docDao.importarArquivo(nomeArquivo));

									docDao.cadastrar(matricula, doc);

									System.out.println("\nDocumento enviado!");

								} catch (OperacaoInvalidaException e) {
									System.out.println(e.getMessage());
								} catch (ItemCadastradoException e) {
									System.out.println(e.getMessage());
								} catch (IOException e) {
									System.out.println("\nDocumento n�o encontrado!");
									nomeArquivo = "errado";
								}

							} while (nomeArquivo.equals("errado"));

						}

					} // !Enviou Documentos

					// Se j� enviou os documentos continua
					if (docDao.isTodosCadastrados(matricula)) {

						dados = dadosDao.pesquisar(matricula);

						// <<<<<<<<<<<<<<< DEPENDENTES >>>>>>>>>>>>>>>

						System.out.println("\n4� Etapa - Adicionar os Dependentes");

						// In�cio do looping do Menu Dependente
						do {

							// MENU DEPENDENTE
							do {
								System.out.println("\n1 - Adicionar c�njuge" + "\n2 - Adicionar filho(a)"
										+ "\n3 - adicionar genitor(pai)" + "\n4 - adicionar genitora(m�e)"
										+ "\n5 - Finalizar o cadastro" + "\nEscolha qual opera��o deseja executar:");
								menuDependente = sc.next();
							} while (menuDependente.isEmpty());

							// In�cio switch Menu Dependente
							switch (menuDependente) {

							// C�NJUGE
							case "1":

								tipo = "C�NJUGE";
								nomeDocumento = "CERTID�O DE CASAMENTO";

								// NOME COMPLETO
								do {
									System.out.println("\nDigite o nome completo do seu c�njuge:");
									nome = sc.next() + sc.nextLine();
									nome = nome.toUpperCase();
								} while (nome.isEmpty());

								// CPF
								do {
									System.out.println(
											"\nDigite o CPF do seu c�njuge (sem pontos nem tra�os - apenas n�meros):\n"
													+ "Exemplo de CPF: 10907926894");
									cpf = sc.next();

									// Checa o CPF
									if (!dados.isCPF(cpf)) {
										System.out.println("\nCPF inv�lido!");
									}

								} while (!dadosDep.isCPF(cpf));

								// SEXO
								do {
									System.out.println(
											"\nDigite o sexo de nascimento do seu c�njuge ('F' para FEMININO ou 'M' para MASCULINO):");
									codigoSexo = sc.next();
									codigoSexo = codigoSexo.toUpperCase();
									if (!codigoSexo.matches("[F]") && !codigoSexo.matches("[M]")) {
										System.out.println("\nValor Incorreto!");
										codigoSexo = "";
									}
								} while (codigoSexo.isEmpty());

								// Pega o sexo
								sexo = dadosDep.selecionarSexo(codigoSexo);

								// DATA DE NASCIMENTO
								do {
									// ANO
									do {
										try {
											System.out.println("\nDigite o ano em que seu c�njuge nasceu:");
											ano = sc.nextInt();

											// Checa o ano
											if (!dadosDep.validarAno(ano)) {
												System.out.println("\nData Inv�lida");
											}
										} catch (InputMismatchException e) {
											System.err.println("Digite somente n�meros!!");
											sc.nextLine();
											ano = 0;
										}

									} while (!dadosDep.validarAno(ano));

									// M�S
									do {
										try {
											System.out.println("\nDigite o m�s em que seu c�njuge nasceu:");
											mes = sc.nextInt();

											// Checa o m�s
											if (!dadosDep.validarMes(mes)) {
												System.out.println("\nData Inv�lida");
											}
										} catch (InputMismatchException e) {
											System.err.println("Digite somente n�meros!!");
											sc.nextLine();
											mes = 0;
										}
									} while (!dadosDep.validarMes(mes));

									// DIA
									do {
										try {
											System.out.println("\nDigite o dia em que seu c�njuge nasceu:");
											dia = sc.nextInt();

											// Checa o dia
											if (!dadosDep.validarDia(dia, mes)) {
												System.out.println("\nData Inv�lida");
											}
										} catch (InputMismatchException e) {
											System.err.println("Digite somente n�meros!!");
											sc.nextLine();
											dia = 0;
										}
									} while (!dadosDep.validarDia(dia, mes));

									// Valida a data
									if (!dadosDep.validarData(dia, mes, ano))
										System.out.println("\nData Inv�lida");

								} while (!dadosDep.validarData(dia, mes, ano));

								// Monta a data
								dataNascimento = dadosDep.montarData(dia, mes, ano);

								do {

									// CERTID�O DE CASAMENTO
									System.out.println(
											"\nEnvie a sua Certid�o de Casamento.\nDigite o nome completo do arquivo:");
									nomeArquivo = sc.next();

									try {

										// Envia os dados para as classes bean e cadastra o dependente
										dadosDep = new DadosDependente(nome, cpf, sexo, dataNascimento);
										dep = new Dependente(tipo, dadosDep, nomeDocumento, nomeArquivo,
												docDao.importarArquivo(nomeArquivo));
										depDao.cadastrar(matricula, dep);

										System.out.println("\nDependente enviado!");

									} catch (OperacaoInvalidaException e) {
										System.out.println(e.getMessage());
									} catch (ItemCadastradoException e) {
										System.out.println(e.getMessage());
									} catch (IOException e) {
										System.out.println("\nDocumento n�o encontrado!");
										nomeArquivo = "errado";
									}

								} while (nomeArquivo.equals("errado"));

								break;

							// FILHO
							case "2":

								tipo = "FILHO";
								nomeDocumento = "CERTID�O DE NASCIMENTO";

								// NOME COMPLETO
								do {
									System.out.println("\nDigite o nome completo do seu filho:");
									nome = sc.next() + sc.nextLine();
									nome = nome.toUpperCase();
								} while (nome.isEmpty());

								// CPF
								do {
									System.out.println(
											"\nDigite o CPF do seu filho (sem pontos nem tra�os - apenas n�meros):\n"
													+ "Exemplo de CPF: 93303777268");
									cpf = sc.next();

									// Checa o CPF
									if (!dados.isCPF(cpf)) {
										System.out.println("\nCPF inv�lido!");
									}

								} while (!dadosDep.isCPF(cpf));

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
								sexo = dadosDep.selecionarSexo(codigoSexo);

								// DATA DE NASCIMENTO
								do {
									// ANO
									do {
										try {
											System.out.println("\nDigite o ano em que seu filho nasceu:");
											ano = sc.nextInt();

											// Checa o ano
											if (!dadosDep.validarAno(ano)) {
												System.out.println("\nData Inv�lida");
											}
										} catch (InputMismatchException e) {
											System.err.println("Digite somente n�meros!!");
											sc.nextLine();
											ano = 0;
										}

									} while (!dadosDep.validarAno(ano));

									// M�S
									do {
										try {
											System.out.println("\nDigite o m�s em que seu filho nasceu:");
											mes = sc.nextInt();

											// Checa o m�s
											if (!dadosDep.validarMes(mes)) {
												System.out.println("\nData Inv�lida");
											}
										} catch (InputMismatchException e) {
											System.err.println("Digite somente n�meros!!");
											sc.nextLine();
											mes = 0;
										}
									} while (!dadosDep.validarMes(mes));

									// DIA
									do {
										try {
											System.out.println("\nDigite o dia em que seu filho nasceu:");
											dia = sc.nextInt();

											// Checa o dia
											if (!dadosDep.validarDia(dia, mes)) {
												System.out.println("\nData Inv�lida");
											}
										} catch (InputMismatchException e) {
											System.err.println("Digite somente n�meros!!");
											sc.nextLine();
											dia = 0;
										}
									} while (!dadosDep.validarDia(dia, mes));

									// Valida a data
									if (!dadosDep.validarData(dia, mes, ano))
										System.out.println("\nData Inv�lida");

								} while (!dadosDep.validarData(dia, mes, ano));

								// Monta a data
								dataNascimento = dadosDep.montarData(dia, mes, ano);

								do {

									// CERTID�O DE NASCIMENTO
									System.out.println("\nEnvie a Certid�o de Nascimento do " + nome
											+ ".\nDigite o nome completo do arquivo:");
									nomeArquivo = sc.next();

									try {

										// Envia os dados para as classes bean e cadastra o dependente
										dadosDep = new DadosDependente(nome, cpf, sexo, dataNascimento);
										dep = new Dependente(tipo, dadosDep, nomeDocumento, nomeArquivo,
												docDao.importarArquivo(nomeArquivo));
										depDao.cadastrar(matricula, dep);

										System.out.println("\nDependente enviado!");

									} catch (OperacaoInvalidaException e) {
										System.out.println(e.getMessage());
									} catch (ItemCadastradoException e) {
										System.out.println(e.getMessage());
									} catch (IOException e) {
										System.out.println("\nDocumento n�o encontrado!");
										nomeArquivo = "errado";
									}

								} while (nomeArquivo.equals("errado"));

								break;

							// PAI
							case "3":

								tipo = "GENITOR";
								nomeDocumento = "RG";

								// SEXO
								sexo = "MASCULINO";

								// NOME COMPLETO
								do {
									System.out.println("\nDigite o nome completo do seu pai:");
									nome = sc.next() + sc.nextLine();
									nome = nome.toUpperCase();
								} while (nome.isEmpty());

								// CPF
								do {
									System.out.println(
											"\nDigite o CPF do seu pai (sem pontos nem tra�os - apenas n�meros):\n"
													+ "Exemplo de CPF: 92696376034");
									cpf = sc.next();

									// Checa o CPF
									if (!dados.isCPF(cpf)) {
										System.out.println("\nCPF inv�lido!");
									}

								} while (!dadosDep.isCPF(cpf));

								// DATA DE NASCIMENTO
								do {
									// ANO
									do {
										try {
											System.out.println("\nDigite o ano em que seu pai nasceu:");
											ano = sc.nextInt();

											// Checa o ano
											if (!dadosDep.validarAno(ano)) {
												System.out.println("\nData Inv�lida");
											}
										} catch (InputMismatchException e) {
											System.err.println("Digite somente n�meros!!");
											sc.nextLine();
											ano = 0;
										}

									} while (!dadosDep.validarAno(ano));

									// M�S
									do {
										try {
											System.out.println("\nDigite o m�s em que seu pai nasceu:");
											mes = sc.nextInt();

											// Checa o m�s
											if (!dadosDep.validarMes(mes)) {
												System.out.println("\nData Inv�lida");
											}
										} catch (InputMismatchException e) {
											System.err.println("Digite somente n�meros!!");
											sc.nextLine();
											mes = 0;
										}
									} while (!dadosDep.validarMes(mes));

									// DIA
									do {
										try {
											System.out.println("\nDigite o dia em que seu pai nasceu:");
											dia = sc.nextInt();

											// Checa o dia
											if (!dadosDep.validarDia(dia, mes)) {
												System.out.println("\nData Inv�lida");
											}
										} catch (InputMismatchException e) {
											System.err.println("Digite somente n�meros!!");
											sc.nextLine();
											dia = 0;
										}
									} while (!dadosDep.validarDia(dia, mes));

									// Valida a data
									if (!dadosDep.validarData(dia, mes, ano))
										System.out.println("\nData Inv�lida");

								} while (!dadosDep.validarData(dia, mes, ano));

								// Monta a data
								dataNascimento = dadosDep.montarData(dia, mes, ano);

								do {

									// RG
									System.out.println("\nEnvie o RG de seu pai.\nDigite o nome completo do arquivo:");
									nomeArquivo = sc.next();

									try {

										// Envia os dados para as classes bean e cadastra o dependente
										dadosDep = new DadosDependente(nome, cpf, sexo, dataNascimento);
										dep = new Dependente(tipo, dadosDep, nomeDocumento, nomeArquivo,
												docDao.importarArquivo(nomeArquivo));
										depDao.cadastrar(matricula, dep);

										System.out.println("\nDependente enviado!");

									} catch (OperacaoInvalidaException e) {
										System.out.println(e.getMessage());
									} catch (ItemCadastradoException e) {
										System.out.println(e.getMessage());
									} catch (IOException e) {
										System.out.println("\nDocumento n�o encontrado!");
										nomeArquivo = "errado";
									}

								} while (nomeArquivo.equals("errado"));

								break;

							// M�E
							case "4":

								tipo = "GENITOR";
								nomeDocumento = "RG";

								// SEXO
								sexo = "FEMININO";

								// NOME COMPLETO
								do {
									System.out.println("\nDigite o nome completo da sua m�e:");
									nome = sc.next() + sc.nextLine();
									nome = nome.toUpperCase();
								} while (nome.isEmpty());

								// CPF
								do {
									System.out.println(
											"\nDigite o CPF da sua m�e (sem pontos nem tra�os - apenas n�meros):\n"
													+ "Exemplo de CPF: 04685211600");
									cpf = sc.next();

									// Checa o CPF
									if (!dados.isCPF(cpf)) {
										System.out.println("\nCPF inv�lido!");
									}

								} while (!dadosDep.isCPF(cpf));

								// DATA DE NASCIMENTO
								do {
									// ANO
									do {
										try {
											System.out.println("\nDigite o ano em que sua m�e nasceu:");
											ano = sc.nextInt();

											// Checa o ano
											if (!dadosDep.validarAno(ano)) {
												System.out.println("\nData Inv�lida");
											}
										} catch (InputMismatchException e) {
											System.err.println("Digite somente n�meros!!");
											sc.nextLine();
											ano = 0;
										}

									} while (!dadosDep.validarAno(ano));

									// M�S
									do {
										try {
											System.out.println("\nDigite o m�s em que sua m�e nasceu:");
											mes = sc.nextInt();

											// Checa o m�s
											if (!dadosDep.validarMes(mes)) {
												System.out.println("\nData Inv�lida");
											}
										} catch (InputMismatchException e) {
											System.err.println("Digite somente n�meros!!");
											sc.nextLine();
											mes = 0;
										}
									} while (!dadosDep.validarMes(mes));

									// DIA
									do {
										try {
											System.out.println("\nDigite o dia em que sua m�e nasceu:");
											dia = sc.nextInt();

											// Checa o dia
											if (!dadosDep.validarDia(dia, mes)) {
												System.out.println("\nData Inv�lida");
											}
										} catch (InputMismatchException e) {
											System.err.println("Digite somente n�meros!!");
											sc.nextLine();
											dia = 0;
										}
									} while (!dadosDep.validarDia(dia, mes));

									// Valida a data
									if (!dadosDep.validarData(dia, mes, ano))
										System.out.println("\nData Inv�lida");

								} while (!dadosDep.validarData(dia, mes, ano));

								// Monta a data
								dataNascimento = dadosDep.montarData(dia, mes, ano);

								do {

									// RG
									System.out.println("\nEnvie o RG de sua m�e.\nDigite o nome completo do arquivo:");
									nomeArquivo = sc.next();

									try {

										// Envia os dados para as classes bean e cadastra o dependente
										dadosDep = new DadosDependente(nome, cpf, sexo, dataNascimento);
										dep = new Dependente(tipo, dadosDep, nomeDocumento, nomeArquivo,
												docDao.importarArquivo(nomeArquivo));
										depDao.cadastrar(matricula, dep);

										System.out.println("\nDependente enviado!");

									} catch (OperacaoInvalidaException e) {
										System.out.println(e.getMessage());
									} catch (ItemCadastradoException e) {
										System.out.println(e.getMessage());
									} catch (IOException e) {
										System.out.println("\nDocumento n�o encontrado!");
										nomeArquivo = "errado";
									}

								} while (nomeArquivo.equals("errado"));

								break;

							// SAIR
							case "5":
								break;

							default:
								System.out.println("\nOp��o Inv�lida!");

							}// Fim switch Menu Dependente

						} while (!menuDependente.equals("5")); // Fim do looping do Menu
																// Dependente

						// STATUS CADASTRADO
						cadastrado = 1;
						try {
							colaboradorDao.setStatusCadastro(matricula, cadastrado);
						} catch (AtualizacaoNaoRealizadaException e) {
							System.out.println(e.getMessage());
						}

						// <<<<<<<<<<<<<<< ENCERRAMENTO >>>>>>>>>>>>>>>

						// Mensagem de encerramento
						System.out.println("\nCadastro conclu�do!!\nSeja bem-vindo � B2W");

					} // Enviou Documentos

				} // Enviou Dados

			} // Logado

			else if (loginDao.colaborador(email, senha) && colaboradorDao.getStatus(email)) {
				System.out.println("\nUsu�rio j� realizou o cadastro!");
			}

			else
				System.out.println("\nLogin inv�lido!");

		} catch (ItemNaoEncontradoException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException | SQLException e) {
			//e.printStackTrace();
			System.out.println("\nOpera��o n�o realizada!");
		}

		// Fecha o scanner
		sc.close();

	}// Main
}// Classe