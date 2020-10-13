package br.com.fiap.tds.view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.com.fiap.tds.bean.Login;
import br.com.fiap.tds.bo.ColaboradorBo;
import br.com.fiap.tds.bo.DependenteBo;
import br.com.fiap.tds.bo.DocumentoBo;
import br.com.fiap.tds.bo.LoginBo;
import br.com.fiap.tds.bean.Colaborador;
import br.com.fiap.tds.bean.Dependente;
import br.com.fiap.tds.bean.Documento;
import br.com.fiap.tds.exception.AtualizacaoNaoRealizadaException;
import br.com.fiap.tds.exception.ItemCadastradoException;
import br.com.fiap.tds.exception.ItemNaoEncontradoException;
import br.com.fiap.tds.validation.ContaBancoValidation;
import br.com.fiap.tds.validation.CpfValidation;
import br.com.fiap.tds.validation.DataValidation;
import br.com.fiap.tds.validation.PisValidation;
import br.com.fiap.tds.exception.DadoInvalidoException;

public class TerminalRH {

	public static void main(String args[]) {

		// Atributos
		int matricula = 0;
		int codigoDependente = 0;
		String senha = "";
		String menuPrincipal = "";
		String menuPesquisar = "";
		String menuEditar = "";
		String menuDependente = "";
		String respostaRC = "";
		String email = "";
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
		String camiseta = "";
		int codigoCamiseta = 0;
		String orientacao = "";
		String religiao = "";
		String agencia = "";
		String digitoAgencia = "";
		String conta = "";
		String digitoConta = "";
		String tipo = "";
		int codigoDocumento = 0;
		String nomeDocumento = "";
		String nomeArquivo = "";

		// Lists
		List<String> listaDependentes = new ArrayList<String>();

		// Iniciar o scanner
		Scanner sc = new Scanner(System.in);

		// Classes Bean
		Login login = new Login();
		Login loginColab = new Login();
		Colaborador colab = new Colaborador();
		Documento doc = new Documento();
		Documento docDep = new Documento();
		Dependente dep = new Dependente();

		// Classes Bo
		LoginBo loginBo = new LoginBo();
		ColaboradorBo colabBo = new ColaboradorBo();
		DocumentoBo docBo = new DocumentoBo();
		DependenteBo depBo = new DependenteBo();

		// Inicia classes Validation
		CpfValidation cpfVal = new CpfValidation();
		PisValidation pisVal = new PisValidation();
		DataValidation dataVal = new DataValidation();
		ContaBancoValidation contaVal = new ContaBancoValidation();

		// <<<<<<<<<<<<<<<<<<<<<<<< SISTEMA P/ RECURSOS HUMANOS >>>>>>>>>>>>>>>>>>>>>>>>

		System.out.println("\nSistema xSolution de Recursos Humanos");

		// <<<<<<<<<<<<<<< LOGIN RH >>>>>>>>>>>>>>>

		System.out.println("\nPara testar utilize:\nE-mail = admin@fiap.com.br\nSenha = fiap123");

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
			System.out.println("\nLogin efetuado com sucesso!");

			// Início do looping do Menu Principal
			do {

				// <<<<< MENU PRINCIPAL >>>>>>
				do {
					System.out.println("\nMenu Principal\n" + "\n1 - Pesquisar/Editar/Remover o colaborador"
							+ "\n2 - Exportar os dados de todos os colaboradores" + "\n3 - Sair do programa"
							+ "\n\nEscolha qual operação deseja executar:");
					menuPrincipal = sc.next();
				} while (menuPrincipal.isEmpty());

				// Início switch Menu Principal
				switch (menuPrincipal) {

//			<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>

				// PESQUISA O COLABORADOR
				case "1":

					// Início do looping do Menu Pesquisar
					do {

						colab = null;

						// MENU PESQUISAR
						do {
							System.out.println("\n1 - Pesquisar pela matrícula" + "\n2 - Pesquisar pelo email"
									+ "\n3 - Retornar ao menu principal" + "\nEscolha qual operação deseja executar:");
							menuPesquisar = sc.next();
						} while (menuPesquisar.isEmpty());

						try {

							// Início switch Menu Pesquisar
							switch (menuPesquisar) {

							// PESQUISA MATRICULA
							case "1":

								do {
									try {
										System.out.println("\nDigite a matrícula do colaborador:");
										matricula = sc.nextInt();
									} catch (InputMismatchException e) {
										System.err.println("Digite somente números!!");
										sc.nextLine();
										matricula = 0;
									}
								} while (matricula == 0);

								// Pega o colaborador
								loginColab = loginBo.pesquisar(matricula);
								colab = colabBo.pesquisar(loginColab.getMatricula());

								break;

							// PESQUISA EMAIL
							case "2":

								do {
									System.out.println("\nDigite o email do colaborador:");
									email = sc.next();
								} while (email.isEmpty());

								// Pega o colaborador e a matricula
								loginColab = loginBo.pesquisar(email);
								colab = colabBo.pesquisar(loginColab.getMatricula());

								break;

							// SAIR
							case "3":
								break;

							default:
								System.out.println("\nOpção Inválida!");

							}// Fim switch Menu Pesquisar

						} catch (ClassNotFoundException | SQLException e) {
							System.out.println("\nNão foi possível completar a operação!");
							e.printStackTrace();
						} catch (ItemNaoEncontradoException e) {
							System.out.println(e.getMessage());
						}

//					<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>

						// ENCONTROU O COLABORADOR

						if (colab != null) {

							// Início do looping do Menu Editar
							do {

								try {

									// Preenche as classes beans
									colab = colabBo.pesquisar(loginColab.getMatricula());
									listaDependentes = depBo.listar(loginColab.getMatricula());

									// Caso a lista de dependentes não esteja vazia
									if (listaDependentes.size() != 0) {
										System.out.println("\nDados dos dependentes:");
										// Transforma ela numa String
										for (String dependente : listaDependentes) {
											System.out.println(dependente);
										}
									}

									// MENU EDITAR
									System.out.println("\nDados do Colaborador:" + colab
											+ "\n\nPara alterar algum dado digite o seu respectivo código."
											+ "\nPara fazer o download em zip de todos os documentos do colaborador digite 'ZC'."
											+ "\nPara fazer o download de algum documento do colaborador digite 'DC'."
											+ "\nPara fazer o upload de algum documento do colaborador digite 'UC'."
											+ "\nPara remover o colaborador digite 'RC'."
											+ "\nPara fazer o download do documento de algum dependente digite 'DD'."
											+ "\nPara fazer o upload de um novo dependente digite 'UD'."
											+ "\nPara remover algum dependente digite 'RD'."
											+ "\nPara não realizar nenhuma operação e voltar ao menu anterior digite 'N'.");

									menuEditar = sc.next().toUpperCase();

									// Início switch Menu Editar
									switch (menuEditar) {

									// NOME COMPLETO
									case "1":

										do {
											System.out.println("\nDigite o nome completo do colaborador:");
											nome = sc.next() + sc.nextLine();
											nome = nome.toUpperCase();
										} while (nome.isEmpty());

										// Envia para a classe bean
										colab.setNome(nome);

										break;

									// CPF
									case "2":

										do {
											System.out.println(
													"\nDigite o CPF do colaborador:\nExemplo de CPF: 79835295468");
											cpf = colab.getNumeroCpfOrPis(sc.next());

											// Checa o CPF
											if (!cpfVal.isCpf(cpf)) {
												System.out.println("\nCPF inválido!");
											}

										} while (!cpfVal.isCpf(cpf));

										// Envia para a classe bean
										colab.setCpf(cpf);

										break;

									// PIS
									case "3":

										do {
											System.out.println(
													"\nDigite o PIS do colaborador:\nExemplo de PIS: 10703281531");
											pis = colab.getNumeroCpfOrPis(sc.next());

											// Checa o PIS
											if (!pisVal.isPis(pis)) {
												System.out.println("\nPIS inválido!");
											}

										} while (!pisVal.isPis(pis));

										// Envia para a classe bean
										colab.setPis(pis);

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

										// Pega o sexo
										sexo = colab.selecionarSexo(codigoSexo);

										// Envia para a classe bean
										colab.setSexo(codigoSexo);

										break;

									// NACIONALIDADE
									case "5":

										do {
											System.out.println("\nDigite a nacionalidade do colaborador:");
											nacionalidade = sc.next() + sc.nextLine();
											nacionalidade = nacionalidade.toUpperCase();
										} while (nacionalidade.isEmpty());

										// Envia para a classe bean
										colab.setNacionalidade(nacionalidade);

										break;

									// NATURALIDADE
									case "6":

										do {
											System.out.println("\nDigite a naturalidade do colaborador:");
											naturalidade = sc.next() + sc.nextLine();
											naturalidade = naturalidade.toUpperCase();
										} while (naturalidade.isEmpty());

										// Envia para a classe bean
										colab.setNaturalidade(naturalidade);

										break;

									// DATA DE NASCIMENTO
									case "7":

										do {

											System.out.println(
													"\nDigite a data de nascimento do colaborador no seguinte formato 'DD/MM/AAAA':");
											dataNascimento = sc.next();

											// Valida a data
											if (!dataVal.validarDataColaborador(dataNascimento))
												System.out.println("\nData Inválida");

										} while (!dataVal.validarDataColaborador(dataNascimento));

										// Envia para a classe bean
										colab.setDataNascimento(dataNascimento);

										break;

									// ESTADO CIVIL
									case "8":

										do {
											try {
												System.out.println("\n1 - SOLTEIRO" + "\n2 - CASADO"
														+ "\n3 - DIVORCIADO" + "\n4 - VIÚVO"
														+ "\nDigite o código do estado civil do colaborador:");
												codigoEstadoCivil = sc.nextInt();
											} catch (InputMismatchException e) {
												System.err.println("Digite somente números!!");
												sc.nextLine();
												codigoEstadoCivil = 0;
											}
										} while (codigoEstadoCivil < 1 || codigoEstadoCivil > 4);

										// Pega o estado civil
										estadoCivil = colab.selecionarEstadoCivil(codigoEstadoCivil);

										// Envia para a classe bean
										colab.setEstadoCivil(estadoCivil);

										break;

									// NÚMERO FILHOS
									case "9":

										do {
											try {
												System.out.println(
														"\nDigite o número de filhos que o colaborador possui:");
												filhos = sc.nextInt();
											} catch (InputMismatchException e) {
												System.err.println("Digite somente números!!");
												sc.nextLine();
												filhos = -1;
											}

										} while (filhos < 0);

										// Envia para a classe bean
										colab.setFilhos(filhos);

										break;

									// ETNIA
									case "10":

										do {
											try {
												System.out.println("\n1 - BRANCO\n" + "2 - NEGRO\n" + "3 - PARDO\n"
														+ "4 - INDÍGENA\n" + "5 - NÃO INFORMAR\n"
														+ "Digite o código da etnia do colaborador:");
												codigoEtnia = sc.nextInt();
											} catch (InputMismatchException e) {
												System.err.println("Digite somente números!!");
												sc.nextLine();
												codigoEtnia = 0;
											}
										} while (codigoEtnia < 1 || codigoEtnia > 5);

										// Pega a etnia
										etnia = colab.selecionarEtnia(codigoEtnia);

										// Envia para a classe bean
										colab.setEtnia(etnia);

										break;

									// TAMANHO CAMISETA
									case "11":

										do {
											try {
												System.out.println("\n1 - PP\n" + "2 - P\n" + "3 - M\n" + "4 - G\n"
														+ "5 - GG\n"
														+ "Digite o código do tamanho da camiseta do colaborador:");
												codigoCamiseta = sc.nextInt();
											} catch (InputMismatchException e) {
												System.err.println("Digite somente números!!");
												sc.nextLine();
												codigoCamiseta = 0;
											}
										} while (codigoCamiseta < 1 || codigoCamiseta > 5);

										// Pega o tamanho da camiseta
										camiseta = colab.selecionarCamiseta(codigoCamiseta);

										// Envia para a classe bean
										colab.setCamiseta(camiseta);

										break;

									// AGÊNCIA BRADESCO
									case "12":

										do {
											System.out.println(
													"\nDigite o número da agência do colaborador (sem o dígito verificador):\n"
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

										// Envia para a classe bean
										colab.setAgencia(agencia);
										colab.setDigitoAgencia(digitoAgencia);

										break;

									// CONTA BRADESCO
									case "13":

										do {
											System.out.println(
													"\nDigite o número da conta do colaborador (sem o dígito verificador):\n"
															+ "Obs: A conta tem entre 1 e 7 números");
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

										// Envia para a classe bean
										colab.setConta(conta);
										colab.setDigitoConta(digitoConta);

										break;

									// ORIENTAÇÃO SEXUAL
									case "14":
										do {
											System.out.println(
													"\nQual a orientação sexual do colaborador? (Digite N para não informar)");
											orientacao = sc.next() + sc.nextLine();
											orientacao = orientacao.toUpperCase();
											if (orientacao.equals("N")) {
												orientacao = "NÃO INFORMADO";
											}
										} while (orientacao.isEmpty());

										// Envia para a classe bean
										colab.setOrientacao(orientacao);

										break;

									// RELIGIÃO
									case "15":

										do {
											System.out.println(
													"\nQual a religião do colaborador? (Digite N para não informar)");
											religiao = sc.next() + sc.nextLine();
											religiao = religiao.toUpperCase();
											if (religiao.equals("N")) {
												religiao = "NÃO INFORMADO";
											}
										} while (religiao.isEmpty());

										// Envia para a classe bean
										colab.setReligiao(religiao);

										break;

//								<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>

									// DOWNLOAD ZIP COLABORADOR
									case "ZC":

										try {

											// Faz o download do .zip
											docBo.baixarZip(loginColab.getMatricula());
											System.out.println("\nDownload realizado!");

										} catch (IOException e) {
											// e.printStackTrace();
											System.out.println("\nErro na operação!");
										}

										break;

//								<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>

									// DOWNLOAD DOCUMENTO COLABORADOR
									case "DC":

										do {
											try {
												System.out.println("\n1 - RG\n2 - CARTEIRA DE TRABALHO"
														+ "\n3 - COMPROVANTE DE RESIDÊNCIA" + "\n4 - TÍTULO DE ELEITOR"
														+ "\n5 - COMPROVANTE DE ESCOLARIDADE"
														+ "\n6 - CERTIFICADO DE RESERVISTA" + "\n7 - Retornar ao menu"
														+ "\nEscolha qual operação deseja executar:");
												codigoDocumento = sc.nextInt();
											} catch (InputMismatchException e) {
												System.err.println("Digite somente números!!");
												sc.nextLine();
												codigoDocumento = 0;
											}

										} while (codigoDocumento < 1 || codigoDocumento > 7);

										// Pega o nome do documento
										nomeDocumento = doc.selecionarNome(codigoDocumento);

										if (!nomeDocumento.isEmpty()) {
											try {

												// Faz o download do arquivo
												docBo.baixarArquivo(loginColab.getMatricula(), nomeDocumento);
												System.out.println("\nDownload realizado!");

											} catch (IOException e) {
												System.out.println("\nDocumento não encontrado!");
											}
										}

										break;

//								<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>

									// UPLOAD DOCUMENTO COLABORADOR
									case "UC":

										do {
											try {
												System.out.println("\n1 - RG\n2 - CARTEIRA DE TRABALHO"
														+ "\n3 - COMPROVANTE DE RESIDÊNCIA" + "\n4 - TÍTULO DE ELEITOR"
														+ "\n5 - COMPROVANTE DE ESCOLARIDADE"
														+ "\n6 - CERTIFICADO DE RESERVISTA" + "\n7 - Retornar ao menu"
														+ "\nEscolha qual operação deseja executar:");
												codigoDocumento = sc.nextInt();
											} catch (InputMismatchException e) {
												System.err.println("Digite somente números!!");
												sc.nextLine();
												codigoDocumento = 0;
											}

										} while (codigoDocumento < 1 || codigoDocumento > 7);

										// Pega o nome do documento
										nomeDocumento = doc.selecionarNome(codigoDocumento);

										if (!nomeDocumento.isEmpty()) {

											System.out.println("\nEnvie o seu " + nomeDocumento
													+ ".\nDigite o nome completo do arquivo:");
											nomeArquivo = sc.next();

											try {

												// Envia os dados para a classe bean e atualiza o documento
												doc = new Documento(loginColab, nomeDocumento, nomeArquivo,
														docBo.importarArquivo(nomeArquivo));
												docBo.atualizar(doc);

												System.out.println("\nDocumento enviado!");

											} catch (IOException e) {
												System.out.println("\nDocumento não encontrado!");
											} catch (ItemCadastradoException e) {
												System.out.println(e.getMessage());
											}

										}

										break;

//									<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>

									// REMOVE COLABORADOR
									case "RC":

										do {

											System.out.println(
													"\nTem certeza que deseja remover o colaborador? (Digite 'S' para SIM ou 'N' para NÃO)");
											respostaRC = sc.next();
											if (respostaRC.equalsIgnoreCase("s")) {

												// Remove a chave com seus valores
												loginBo.remover(loginColab.getMatricula());
												System.out.println("\nColaborador removido do sistema");

												// Sai do menu editar
												menuEditar = "N";
											}

											else if (respostaRC.equalsIgnoreCase("n")) {
												System.out.println("\nColaborador NÃO removido do sistema");
											}

											else
												System.out.println("\nOpção Inválida!");

										} while (!respostaRC.equalsIgnoreCase("s")
												&& !respostaRC.equalsIgnoreCase("n"));

										break;

//									<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>

									// DOWNLOAD DOCUMENTO DEPENDENTE
									case "DD":

										do {
											try {
												System.out.println(
														"\nDigite o código do dependente que deseja fazer o download do documento ou '0' para sair:");
												codigoDependente = sc.nextInt();

												if (codigoDependente != 0) {

													// Faz o download do arquivo
													depBo.baixarArquivo(loginColab.getMatricula(), codigoDependente);
													System.out.println("\nDownload realizado!");

												}

											} catch (InputMismatchException e) {
												System.err.println("Digite somente números!!");
												sc.nextLine();
												codigoDependente = 0;
											} catch (IOException e) {
												System.out.println("\nDocumento não encontrado!");
												codigoDependente = 0;
											}

										} while (codigoDependente == 0);

										break;

//									<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>

									// UPLOAD DEPENDENTE
									case "UD":

										// Início do looping do Menu Dependente
										do {

											// MENU DEPENDENTE
											do {
												System.out.println("\n1 - Adicionar cônjuge"
														+ "\n2 - Adicionar filho(a)" + "\n3 - adicionar genitor(pai)"
														+ "\n4 - adicionar genitora(mãe)" + "\n5 - Retornar ao menu"
														+ "\nEscolha qual operação deseja executar:");
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
													System.out.println(
															"\nDigite o nome completo do cônjuge do colaborador:");
													nome = sc.next() + sc.nextLine();
													nome = nome.toUpperCase();
												} while (nome.isEmpty());

												// CPF
												do {
													System.out.println(
															"\nDigite o CPF do cônjuge do colaborador (sem pontos nem traços - apenas números):\n"
																	+ "Exemplo de CPF: 00770539408");
													cpf = dep.getNumeroCpfOrPis(sc.next());

													// Checa o CPF
													if (!cpfVal.isCpf(cpf)) {
														System.out.println("\nCPF inválido!");
													}

												} while (!cpfVal.isCpf(cpf));

												// SEXO
												do {
													System.out.println(
															"\nDigite o sexo de nascimento do cônjuge do colaborador ('F' para FEMININO ou 'M' para MASCULINO):");
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
															"\nDigite a data de nascimento do cônjuge do colaborador no seguinte formato 'DD/MM/AAAA':");
													dataNascimento = sc.next();

													// Valida a data
													if (!dataVal.validarDataDependente(dataNascimento))
														System.out.println("\nData Inválida");

												} while (!dataVal.validarDataDependente(dataNascimento));

												// CERTIDÃO DE CASAMENTO
												System.out.println(
														"\nEnvie a Certidão de Casamento do colaborador.\nDigite o nome completo do arquivo:");
												nomeArquivo = sc.next();

												try {

													// Envia os dados para as classes bean e cadastra o
													// dependente
													docDep = new Documento(nomeDocumento, nomeArquivo,
															docBo.importarArquivo(nomeArquivo));
													dep = new Dependente(loginColab, tipo, nome, cpf, sexo,
															dataNascimento, docDep);
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
													System.out.println(
															"\nDigite o nome completo do filho do colaborador:");
													nome = sc.next() + sc.nextLine();
													nome = nome.toUpperCase();
												} while (nome.isEmpty());

												// CPF
												do {
													System.out.println(
															"\nDigite o CPF do filho do colaborador (sem pontos nem traços - apenas números):\n"
																	+ "Exemplo de CPF: 21124604120");
													cpf = dep.getNumeroCpfOrPis(sc.next());

													// Checa o CPF
													if (!cpfVal.isCpf(cpf)) {
														System.out.println("\nCPF inválido!");
													}

												} while (!cpfVal.isCpf(cpf));

												// SEXO
												do {
													System.out.println(
															"\nDigite o sexo de nascimento do filho do colaborador ('F' para FEMININO, 'M' para MASCULINO):");
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
															"\nDigite a data de nascimento do filho do colaborador no seguinte formato 'DD/MM/AAAA':");
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

													// Envia os dados para as classes bean e cadastra o
													// dependente
													docDep = new Documento(nomeDocumento, nomeArquivo,
															docBo.importarArquivo(nomeArquivo));
													dep = new Dependente(loginColab, tipo, nome, cpf, sexo,
															dataNascimento, docDep);
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
													System.out
															.println("\nDigite o nome completo do pai do colaborador:");
													nome = sc.next() + sc.nextLine();
													nome = nome.toUpperCase();
												} while (nome.isEmpty());

												// CPF
												do {
													System.out.println(
															"\nDigite o CPF do pai do colaborador (sem pontos nem traços - apenas números):\n"
																	+ "Exemplo de CPF: 27809562835");
													cpf = dep.getNumeroCpfOrPis(sc.next());

													// Checa o CPF
													if (!cpfVal.isCpf(cpf)) {
														System.out.println("\nCPF inválido!");
													}

												} while (!cpfVal.isCpf(cpf));

												// DATA DE NASCIMENTO
												do {

													System.out.println(
															"\nDigite a data de nascimento do pai do colaborador no seguinte formato 'DD/MM/AAAA':");
													dataNascimento = sc.next();

													// Valida a data
													if (!dataVal.validarDataDependente(dataNascimento))
														System.out.println("\nData Inválida");

												} while (!dataVal.validarDataDependente(dataNascimento));

												// RG
												System.out.println(
														"\nEnvie o RG do pai do colaborador.\nDigite o nome completo do arquivo:");
												nomeArquivo = sc.next();

												try {

													// Envia os dados para as classes bean e cadastra o
													// dependente
													docDep = new Documento(nomeDocumento, nomeArquivo,
															docBo.importarArquivo(nomeArquivo));
													dep = new Dependente(loginColab, tipo, nome, cpf, sexo,
															dataNascimento, docDep);
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
													System.out
															.println("\nDigite o nome completo da mãe do colaborador:");
													nome = sc.next() + sc.nextLine();
													nome = nome.toUpperCase();
												} while (nome.isEmpty());

												// CPF
												do {
													System.out.println(
															"\nDigite o CPF da mãe do colaborador (sem pontos nem traços - apenas números):\n"
																	+ "Exemplo de CPF: 70850941253");
													cpf = dep.getNumeroCpfOrPis(sc.next());

													// Checa o CPF
													if (!cpfVal.isCpf(cpf)) {
														System.out.println("\nCPF inválido!");
													}

												} while (!cpfVal.isCpf(cpf));

												// DATA DE NASCIMENTO
												do {

													System.out.println(
															"\nDigite a data de nascimento da mâe do colaborador no seguinte formato 'DD/MM/AAAA':");
													dataNascimento = sc.next();

													// Valida a data
													if (!dataVal.validarDataDependente(dataNascimento))
														System.out.println("\nData Inválida");

												} while (!dataVal.validarDataDependente(dataNascimento));

												// RG
												System.out.println(
														"\nEnvie o RG da mãe do colaborador.\nDigite o nome completo do arquivo:");
												nomeArquivo = sc.next();

												try {

													// Envia os dados para as classes bean e cadastra o
													// dependente
													docDep = new Documento(nomeDocumento, nomeArquivo,
															docBo.importarArquivo(nomeArquivo));
													dep = new Dependente(loginColab, tipo, nome, cpf, sexo,
															dataNascimento, docDep);
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

										} while (!menuDependente.equals("5")); // Fim do looping do Menu Dependente

										break;

//									<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>

									// REMOVE DEPENDENTE
									case "RD":
										do {
											try {

												System.out.println(
														"\nDigite o código do dependente a ser removido ou '0' para sair:");
												codigoDependente = sc.nextInt();

												if (codigoDependente != 0) {

													depBo.remover(loginColab.getMatricula(), codigoDependente);
													System.out.println("\nDependente removido!");

												}

											} catch (InputMismatchException e) {
												System.err.println("Digite somente números!!");
												sc.nextLine();
												codigoDependente = -1;
											}

										} while (codigoDependente != 0);

										break;

//									<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>

									// SAIR
									case "N":
										break;

									default:
										System.out.println("\nOpção Inválida!");

									}// Fim switch Menu Editar

									if (menuEditar.matches("[0-9]+")) {

										// Atualiza o colaborador
										colabBo.atualizar(colab);
										loginBo.atualizar(loginColab);
										System.out.println("\nColaborador atualizado com sucesso!");
									}

								} catch (ItemNaoEncontradoException | AtualizacaoNaoRealizadaException
										| DadoInvalidoException e) {
									System.out.println(e.getMessage());
									break;
								}

							} while (!menuEditar.equalsIgnoreCase("n")); // Fim do Menu Editar

						} // Encontrou o Colaborador

					} while (!menuPesquisar.equals("3")); // Fim do looping do Menu Pesquisar

					break;

//				<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>

				// EXPORTA TODOS OS COLABORADORES
				case "2":

					try {
						colabBo.exportar();
					} catch (IOException e) {
						System.out.println("\nErro na operação!");
					}

					System.out.println("\nColaboradores exportados!");

					break;

//				<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>

				// SAIR
				case "3":
					System.out.println("\nSaindo...!\n");
					break;

				default:
					System.out.println("\nOpção Inválida!\n");

				}// Fim switch Menu Principal

			} while (!menuPrincipal.equals("3")); // Fim do looping do Menu Principal

		} catch (ClassNotFoundException |

				SQLException e) {
			System.out.println("\nOperação não realizada!");
			e.printStackTrace();
		}

		// Fecha o scanner
		sc.close();

	}// Main
}// Classe
