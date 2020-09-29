package br.com.fiap.tds.view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.com.fiap.tds.bean.Colaborador;
import br.com.fiap.tds.bean.ContaBanco;
import br.com.fiap.tds.bean.Dados;
import br.com.fiap.tds.bean.DadosDependente;
import br.com.fiap.tds.bean.Departamento;
import br.com.fiap.tds.bean.Dependente;
import br.com.fiap.tds.bean.Documento;
import br.com.fiap.tds.dao.DadosDao;
import br.com.fiap.tds.dao.DepartamentoDao;
import br.com.fiap.tds.dao.DependenteDao;
import br.com.fiap.tds.dao.DocumentoDao;
import br.com.fiap.tds.dao.ColaboradorDao;
import br.com.fiap.tds.dao.ContaBancoDao;
import br.com.fiap.tds.dao.LoginDao;
import br.com.fiap.tds.exception.AtualizacaoNaoRealizadaException;
import br.com.fiap.tds.exception.ItemCadastradoException;
import br.com.fiap.tds.exception.ItemNaoEncontradoException;
import br.com.fiap.tds.exception.OperacaoInvalidaException;

public class TerminalRH {

	public static void main(String args[]) {

		// Atributos
		int matricula = 0;
		int codigoDependente = 0;
		int codigoDepto = 0;
		String usuario = "";
		String senha = "";
		String menuPrincipal = "";
		String menuPesquisar = "";
		String menuEditar = "";
		String menuDependente = "";
		String respostaRC = "";
		String apelido = "";
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
		String tamanhoCamiseta = "";
		int codigoTamanhoCamiseta = 0;
		String orientacao = "";
		String religiao = "";
		String agenciaNumero = "";
		String agenciaDigito = "";
		String contaNumero = "";
		String contaDigito = "";
		String tipo = "";
		int codigoNome = 0;
		String nomeDocumento = "";
		String nomeArquivo = "";

		// Lists
		List<String> listaDependentes = new ArrayList<String>();

		// Iniciar o scanner
		Scanner sc = new Scanner(System.in);

		// Classes Bean
		Departamento depto = new Departamento();
		Colaborador colaborador = new Colaborador();
		Dados dados = new Dados();
		ContaBanco contaBanco = new ContaBanco();
		Documento doc = new Documento();
		Dependente dep = new Dependente();
		DadosDependente dadosDep = new DadosDependente();

		// Classes Dao
		LoginDao loginDao = new LoginDao();
		DepartamentoDao deptoDao = new DepartamentoDao();
		ColaboradorDao colaboradorDao = new ColaboradorDao();
		DadosDao dadosDao = new DadosDao();
		ContaBancoDao contaBancoDao = new ContaBancoDao();
		DocumentoDao docDao = new DocumentoDao();
		DependenteDao depDao = new DependenteDao();

		// <<<<<<<<<<<<<<<<<<<<<<<< SISTEMA P/ RECURSOS HUMANOS >>>>>>>>>>>>>>>>>>>>>>>>

		System.out.println("\nSistema de Recursos Humanos");

		// <<<<<<<<<<<<<<< LOGIN RH >>>>>>>>>>>>>>>

		System.out.println("\nPara testar utilize:\nUsuário = usuario\nSenha = fiap123");

		try {
			do {
				// USUÁRIO
				do {
					System.out.println("\nDigite o usuário:");
					usuario = sc.next();
				} while (usuario.isEmpty());

				// SENHA
				do {
					System.out.println("\nDigite a senha:");
					senha = sc.next();
				} while (senha.isEmpty());

				if (!loginDao.rh(usuario, senha))
					System.out.println("\nUsuário e/ou senha incorretos!");

			} while (!loginDao.rh(usuario, senha));
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("\nLogin não realizado!");
		}

		try {
			// Se login estiver correto continua
			if (loginDao.rh(usuario, senha)) {

				System.out.println("\nLogin efetuado com sucesso!");

				// Início do looping do Menu Principal
				do {

					// <<<<< MENU PRINCIPAL >>>>>>
					do {
						System.out.println("\nMenu Principal\n" + "\n1 - Pesquisar/Editar/Remover o colaborador"
								+ "\n2 - Exportar os dados de todos os colaboradores"
								+ "\n3 - Cadastrar um novo colaborador" + "\n4 - Sair do programa"
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

							colaborador = null;

							// MENU PESQUISAR
							do {
								System.out.println("\n1 - Pesquisar pela matrícula" + "\n2 - Pesquisar pelo email"
										+ "\n3 - Retornar ao menu principal"
										+ "\nEscolha qual operação deseja executar:");
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
									colaborador = colaboradorDao.pesquisar(matricula);

									break;

								// PESQUISA EMAIL
								case "2":

									do {
										System.out.println("\nDigite o email do colaborador:");
										email = sc.next();
									} while (email.isEmpty());

									// Pega o colaborador
									colaborador = colaboradorDao.pesquisar(email);
									matricula = colaborador.getMatricula();

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
							if (colaborador != null && !colaborador.isCadastrado()) {

								// Início do looping do Menu Editar
								do {
									try {

										// Preenche a classe bean
										colaborador = colaboradorDao.pesquisar(matricula);

										// MENU EDITAR
										System.out.println("\nDados do Colaborador:\n" + colaborador
												+ "\n\nPara alterar algum dado digite o seu respectivo código."
												+ "\nPara não realizar nenhuma operação e voltar ao menu anterior digite 'N'.");

										menuEditar = sc.next().toUpperCase();

										// Início switch Menu Editar
										switch (menuEditar) {

										// DEPARTAMENTO
										case "1":

											// Carrega e mostra a lista de departamentos
											depto.setLista(deptoDao.listar());
											for (String item : depto.getLista())
												System.out.println(item);

											do {
												try {
													System.out.println(
															"\nDigite o código do departamento do colaborador:");
													codigoDepto = sc.nextInt();
												} catch (InputMismatchException e) {
													System.err.println("Digite somente números!!");
													sc.nextLine();
													codigoDepto = 0;
												}

											} while (codigoDepto == 0);

											// Atualiza o departamento
											colaboradorDao.setDepartamento(matricula, codigoDepto);

											System.out.println("\nDado atualizado com sucesso!");

											break;

										// APELIDO
										case "2":

											do {
												System.out.println("\nDigite o apelido do colaborador:");
												apelido = sc.next() + sc.nextLine();
												apelido = apelido.toUpperCase();
											} while (apelido.isEmpty());

											// Atualiza o apelido
											colaboradorDao.setApelido(matricula, apelido);

											System.out.println("\nDado atualizado com sucesso!");

											break;

										// EMAIL
										case "3":

											do {
												System.out.println("\nQual o email do colaborador?");
												email = sc.next();
											} while (email.isEmpty());

											// Atualiza o email
											colaboradorDao.setEmail(matricula, email);

											System.out.println("\nDado atualizado com sucesso!");

											break;

										// SAIR
										case "N":
											break;

										default:
											System.out.println("\nOpção Inválida!");

										}// Fim switch Menu Editar

									} catch (AtualizacaoNaoRealizadaException | ItemNaoEncontradoException e) {
										System.out.println(e.getMessage());
										break;
									}

								} while (!menuEditar.equalsIgnoreCase("n")); // Fim do Menu Editar

							}

							else if (colaborador != null) {

								// Início do looping do Menu Editar
								do {

									try {

										// Preenche as classes beans
										colaborador = colaboradorDao.pesquisar(matricula);
										dados = dadosDao.pesquisar(matricula);
										contaBanco = contaBancoDao.pesquisar(matricula);
										listaDependentes = depDao.listar(matricula);

										// Caso a lista de dependentes não esteja vazia
										if (listaDependentes.size() != 0) {
											System.out.println("\nDados dos dependentes:");
											// Transforma ela numa String
											for (String dependente : listaDependentes) {
												System.out.println(dependente);
											}
										}

										// MENU EDITAR
										System.out.println("\nDados do Colaborador:\n" + colaborador + dados
												+ contaBanco
												+ "\n\nPara alterar algum dado digite o seu respectivo código."
												+ "\nPara fazer o download em zip de todos os documentos do colaborador digite 'ZC'."
												+ "\nPara fazer o download de algum documento do colaborador digite 'DC'."
												+ "\nPara fazer o upload de algum documento do colaborador digite 'UC'."
												+ "\nPara remover o colaborador digite 'RC'."
												+ "\nPara fazer o download em zip de todos os documentos dos dependentes digite 'ZD'."
												+ "\nPara fazer o download do documento de algum dependente digite 'DD'."
												+ "\nPara fazer o upload de um novo dependente digite 'UD'."
												+ "\nPara remover algum dependente digite 'RD'."
												+ "\nPara não realizar nenhuma operação e voltar ao menu anterior digite 'N'.");

										menuEditar = sc.next().toUpperCase();

										// Início switch Menu Editar
										switch (menuEditar) {

										// DEPARTAMENTO
										case "1":

											// Carrega e mostra a lista de departamentos
											depto.setLista(deptoDao.listar());
											for (String item : depto.getLista())
												System.out.println(item);

											do {
												try {
													System.out.println(
															"\nDigite o código do departamento do colaborador ou 0 para sair:");
													codigoDepto = sc.nextInt();
												} catch (InputMismatchException e) {
													System.err.println("Digite somente números!!");
													sc.nextLine();
													codigoDepto = -1;
												}

											} while (codigoDepto == -1);

											// Atualiza o departamento
											colaboradorDao.setDepartamento(matricula, codigoDepto);

											System.out.println("\nDado atualizado com sucesso!");

											break;

										// APELIDO
										case "2":

											do {
												System.out.println("\nDigite o apelido do colaborador:");
												apelido = sc.next() + sc.nextLine();
												apelido = apelido.toUpperCase();
											} while (apelido.isEmpty());

											// Atualiza o apelido
											colaboradorDao.setApelido(matricula, apelido);

											System.out.println("\nDado atualizado com sucesso!");

											break;

										// EMAIL
										case "3":

											do {
												System.out.println("\nQual o email do colaborador?");
												email = sc.next();
											} while (email.isEmpty());

											// Atualiza o email
											colaboradorDao.setEmail(matricula, email);

											System.out.println("\nDado atualizado com sucesso!");

											break;

										// NOME COMPLETO
										case "4":

											do {
												System.out.println("\nDigite o nome completo do colaborador:");
												nome = sc.next() + sc.nextLine();
												nome = nome.toUpperCase();
											} while (nome.isEmpty());

											// Atualiza o nome
											dadosDao.setNome(matricula, nome);

											System.out.println("\nDado atualizado com sucesso!");

											break;

										// CPF
										case "5":

											do {
												System.out.println(
														"\nDigite o CPF do colaborador (sem pontos nem traços - apenas números):\n"
																+ "Exemplo de CPF: 79835295468");
												cpf = sc.next();

												// Checa o CPF
												if (!dados.isCPF(cpf)) {
													System.out.println("\nCPF inválido!");
												}

											} while (!dados.isCPF(cpf));

											// Atualiza o CPF
											dadosDao.setCpf(matricula, cpf);

											System.out.println("\nDado atualizado com sucesso!");

											break;

										// PIS
										case "6":

											do {
												System.out.println(
														"\nDigite o PIS do colaborador (sem pontos nem traços - apenas números):\n"
																+ "Exemplo de PIS: 10703281531");
												pis = sc.next();

												// Checa o PIS
												if (!dados.isPis(pis)) {
													System.out.println("\nPIS inválido!");
												}

											} while (!dados.isPis(pis));

											// Atualiza o PIS
											dadosDao.setPis(matricula, pis);

											System.out.println("\nDado atualizado com sucesso!");

											break;

										// SEXO
										case "7":

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
											sexo = dados.selecionarSexo(codigoSexo);

											// Atualiza o sexo
											dadosDao.setSexo(matricula, sexo);

											System.out.println("\nDado atualizado com sucesso!");

											break;

										// NACIONALIDADE
										case "8":

											do {
												System.out.println("\nDigite a nacionalidade do colaborador:");
												nacionalidade = sc.next() + sc.nextLine();
												nacionalidade = nacionalidade.toUpperCase();
											} while (nacionalidade.isEmpty());

											// Atualiza a nacionalidade
											dadosDao.setNacionalidade(matricula, nacionalidade);

											System.out.println("\nDado atualizado com sucesso!");

											break;

										// NATURALIDADE
										case "9":

											do {
												System.out.println("\nDigite a naturalidade do colaborador:");
												naturalidade = sc.next() + sc.nextLine();
												naturalidade = naturalidade.toUpperCase();
											} while (naturalidade.isEmpty());

											// Atualiza a naturalidade
											dadosDao.setNaturalidade(matricula, naturalidade);

											System.out.println("\nDado atualizado com sucesso!");

											break;

										// DATA DE NASCIMENTO
										case "10":
											do {
												// ANO
												do {
													try {
														System.out
																.println("\nDigite o ano em que o colaborador nasceu:");
														ano = sc.nextInt();

														// Checa o ano
														if (!dados.validarAno(ano)) {
															System.out.println("\nData Inválida");
														}
													} catch (InputMismatchException e) {
														System.err.println("Digite somente números!!");
														sc.nextLine();
														ano = 0;
													}

												} while (!dados.validarAno(ano));

												// MÊS
												do {
													try {
														System.out
																.println("\nDigite o mês em que o colaborador nasceu:");
														mes = sc.nextInt();

														// Checa o mês
														if (!dados.validarMes(mes)) {
															System.out.println("\nData Inválida");
														}
													} catch (InputMismatchException e) {
														System.err.println("Digite somente números!!");
														sc.nextLine();
														mes = 0;
													}
												} while (!dados.validarMes(mes));

												// DIA
												do {
													try {
														System.out
																.println("\nDigite o dia em que o colaborador nasceu:");
														dia = sc.nextInt();

														// Checa o dia
														if (!dados.validarDia(dia, mes)) {
															System.out.println("\nData Inválida");
														}
													} catch (InputMismatchException e) {
														System.err.println("Digite somente números!!");
														sc.nextLine();
														dia = 0;
													}
												} while (!dados.validarDia(dia, mes));

												// Valida a data
												if (!dados.validarData(dia, mes, ano))
													System.out.println("\nData Inválida");

											} while (!dados.validarData(dia, mes, ano));

											// Monta a data
											dataNascimento = dados.montarData(dia, mes, ano);

											// Envia a data
											dadosDao.setDataNascimento(matricula, dataNascimento);

											System.out.println("\nDado atualizado com sucesso!");

											break;

										// ESTADO CIVIL
										case "11":

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
											estadoCivil = dados.selecionarEstadoCivil(codigoEstadoCivil);

											// Envia o estado civil
											dadosDao.setEstadoCivil(matricula, estadoCivil);

											System.out.println("\nDado atualizado com sucesso!");

											break;

										// NÚMERO FILHOS
										case "12":

											do {
												try {
													System.out.println(
															"\nDigite o número de filhos que o colaborador possui:");
													numeroFilhos = sc.nextInt();
												} catch (InputMismatchException e) {
													System.err.println("Digite somente números!!");
													sc.nextLine();
													numeroFilhos = -1;
												}

											} while (numeroFilhos < 0);

											// Envia o número de filhos
											dadosDao.setFilhos(matricula, numeroFilhos);

											System.out.println("\nDado atualizado com sucesso!");

											break;

										// ETNIA
										case "13":

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
											etnia = dados.selecionarEtnia(codigoEtnia);

											// Envia a etnia
											dadosDao.setEtnia(matricula, etnia);

											System.out.println("\nDado atualizado com sucesso!");

											break;

										// TAMANHO CAMISETA
										case "14":

											do {
												try {
													System.out.println("\n1 - PP\n" + "2 - P\n" + "3 - M\n" + "4 - G\n"
															+ "5 - GG\n"
															+ "Digite o código do tamanho da camiseta do colaborador:");
													codigoTamanhoCamiseta = sc.nextInt();
												} catch (InputMismatchException e) {
													System.err.println("Digite somente números!!");
													sc.nextLine();
													codigoTamanhoCamiseta = 0;
												}
											} while (codigoTamanhoCamiseta < 1 || codigoTamanhoCamiseta > 5);

											// Pega o tamanho da camiseta
											tamanhoCamiseta = dados.selecionarTamanhoCamiseta(codigoTamanhoCamiseta);

											// Envia o tamanho da camiseta
											dadosDao.setTamanhoCamiseta(matricula, tamanhoCamiseta);

											System.out.println("\nDado atualizado com sucesso!");

											break;

										// ORIENTAÇÃO SEXUAL
										case "15":
											do {
												System.out.println(
														"\nQual a rientação sexual do colaborador? (Digite N para não informar)");
												orientacao = sc.next() + sc.nextLine();
												orientacao = orientacao.toUpperCase();
												if (orientacao.equals("N")) {
													orientacao = "NÃO INFORMADO";
												}
											} while (orientacao.isEmpty());

											// Envia a orientação sexual
											dadosDao.setOrientacao(matricula, orientacao);

											System.out.println("\nDado atualizado com sucesso!");

											break;

										// RELIGIÃO
										case "16":

											do {
												System.out.println(
														"\nQual a religião do colaborador? (Digite N para não informar)");
												religiao = sc.next() + sc.nextLine();
												religiao = religiao.toUpperCase();
												if (religiao.equals("N")) {
													religiao = "NÃO INFORMADO";
												}
											} while (religiao.isEmpty());

											// Envia a religião
											dadosDao.setReligiao(matricula, religiao);

											System.out.println("\nDado atualizado com sucesso!");

											break;

										// AGÊNCIA BRADESCO
										case "17":

											do {
												System.out.println(
														"\nDigite o número da agência do colaborador (sem o dígito verificador):\n"
																+ "Obs: O número da agência tem 4 dígitos");
												agenciaNumero = sc.next();

												// Limita os dígitos
												if (agenciaNumero.length() != 4) {
													System.out.println("\nValor Incorreto!");
												}

												// Apenas valores numéricos
												else if (!agenciaNumero.matches("[0-9]+")) {
													System.out.println("\nDigite somente números!");
												}

											} while (agenciaNumero.length() != 4 || !agenciaNumero.matches("[0-9]+"));

											do {
												System.out.println("\nDigite o dígito verificador da agência:");
												agenciaDigito = sc.next();

												// Limita para apenas 1 dígito e valor numérico
												if (!agenciaDigito.matches("[0-9]")) {
													System.out.println("\nValor Incorreto!");
												}

											} while (!agenciaDigito.matches("[0-9]"));

											// Envia os dados da agência
											contaBancoDao.setAgencia(matricula, Integer.parseInt(agenciaNumero),
													Integer.parseInt(agenciaDigito));

											System.out.println("\nDado atualizado com sucesso!");

											break;

										// CONTA BRADESCO
										case "18":

											do {
												System.out.println(
														"\nDigite o número da conta do colaborador (sem o dígito verificador):\n"
																+ "Obs: O número da conta tem até 7 dígitos");
												contaNumero = sc.next();

												// Limita os dígitos
												if (contaNumero.length() > 7 || contaNumero.length() < 1) {
													System.out.println("\nValor Incorreto!");
												}

												// Apenas valores numéricos
												else if (!contaNumero.matches("[0-9]+")) {
													System.out.println("\nDigite somente números!");
												}

											} while (contaNumero.length() > 7 || contaNumero.length() < 1
													|| !contaNumero.matches("[0-9]+"));

											do {
												System.out.println("\nDigite o dígito verificador da conta:");
												contaDigito = sc.next();
												// Limita para apenas 1 dígito e valor numérico
												if (!contaDigito.matches("[0-9]")) {
													System.out.println("\nValor Incorreto!");
												}

											} while (!contaDigito.matches("[0-9]"));

											// Envia os dados da conta
											contaBancoDao.setConta(matricula, Integer.parseInt(contaNumero),
													Integer.parseInt(contaDigito));

											System.out.println("\nDado atualizado com sucesso!");

											break;

//								<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>

										// DOWNLOAD ZIP COLABORADOR
										case "ZC":

											try {

												// Faz o download do .zip
												docDao.baixarZip(matricula);
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
															+ "\n3 - COMPROVANTE DE RESIDÊNCIA"
															+ "\n4 - TÍTULO DE ELEITOR"
															+ "\n5 - COMPROVANTE DE ESCOLARIDADE"
															+ "\n6 - CERTIFICADO DE RESERVISTA"
															+ "\n7 - Retornar ao menu"
															+ "\nEscolha qual operação deseja executar:");
													codigoNome = sc.nextInt();
												} catch (InputMismatchException e) {
													System.err.println("Digite somente números!!");
													sc.nextLine();
													codigoNome = 0;
												}

											} while (codigoNome < 1 || codigoNome > 7);

											// Pega o nome do documento
											nomeDocumento = doc.selecionarNome(codigoNome);

											if (!nomeDocumento.isEmpty()) {
												try {

													// Faz o download do arquivo
													docDao.baixarArquivo(matricula, nomeDocumento);
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
															+ "\n3 - COMPROVANTE DE RESIDÊNCIA"
															+ "\n4 - TÍTULO DE ELEITOR"
															+ "\n5 - COMPROVANTE DE ESCOLARIDADE"
															+ "\n6 - CERTIFICADO DE RESERVISTA"
															+ "\n7 - Retornar ao menu"
															+ "\nEscolha qual operação deseja executar:");
													codigoNome = sc.nextInt();
												} catch (InputMismatchException e) {
													System.err.println("Digite somente números!!");
													sc.nextLine();
													codigoNome = 0;
												}

											} while (codigoNome < 1 || codigoNome > 7);

											// Pega o nome do documento
											nomeDocumento = doc.selecionarNome(codigoNome);

											if (!nomeDocumento.isEmpty()) {

												do {

													System.out.println("\nEnvie o seu " + nomeDocumento
															+ ".\nDigite o nome completo do arquivo:");
													nomeArquivo = sc.next();

													try {

														// Envia os dados para a classe bean e cadastra o documento
														doc = new Documento(nomeDocumento, nomeArquivo,
																docDao.importarArquivo(nomeArquivo));
														docDao.atualizar(matricula, doc);

														System.out.println("\nDocumento enviado!");

													} catch (IOException e) {
														System.out.println("\nDocumento não encontrado!");
														nomeArquivo = "errado";
													}

												} while (nomeArquivo.equals("errado"));
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
													colaboradorDao.remover(matricula);
													System.out.println("\nColaborador removido do sistema");

													// Sai do menu editar
													menuEditar = "n";
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

										// DOWNLOAD ZIP DEPENDENTE
										case "ZD":

											try {

												// Faz o download do .zip
												depDao.baixarZip(matricula);
												System.out.println("\nDownload realizado!");

											} catch (IOException e) {
												System.out.println("\nErro na operação!");
											}

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
														depDao.baixarArquivo(matricula, codigoDependente);
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
															+ "\n2 - Adicionar filho(a)"
															+ "\n3 - adicionar genitor(pai)"
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
														cpf = sc.next();

														// Checa o CPF
														if (!dados.isCPF(cpf)) {
															System.out.println("\nCPF inválido!");
														}

													} while (!dadosDep.isCPF(cpf));

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
													sexo = dadosDep.selecionarSexo(codigoSexo);

													// DATA DE NASCIMENTO
													do {
														// ANO
														do {
															try {
																System.out.println(
																		"\nDigite o ano em que o cônjuge do colaborador nasceu:");
																ano = sc.nextInt();

																// Checa o ano
																if (!dadosDep.validarAno(ano)) {
																	System.out.println("\nData Inválida");
																}
															} catch (InputMismatchException e) {
																System.err.println("Digite somente números!!");
																sc.nextLine();
																ano = 0;
															}

														} while (!dadosDep.validarAno(ano));

														// MÊS
														do {
															try {
																System.out.println(
																		"\nDigite o mês em que o cônjuge do colaborador nasceu:");
																mes = sc.nextInt();

																// Checa o mês
																if (!dadosDep.validarMes(mes)) {
																	System.out.println("\nData Inválida");
																}
															} catch (InputMismatchException e) {
																System.err.println("Digite somente números!!");
																sc.nextLine();
																mes = 0;
															}
														} while (!dadosDep.validarMes(mes));

														// DIA
														do {
															try {
																System.out.println(
																		"\nDigite o dia em que o cônjuge do colaborador nasceu:");
																dia = sc.nextInt();

																// Checa o dia
																if (!dadosDep.validarDia(dia, mes)) {
																	System.out.println("\nData Inválida");
																}
															} catch (InputMismatchException e) {
																System.err.println("Digite somente números!!");
																sc.nextLine();
																dia = 0;
															}
														} while (!dadosDep.validarDia(dia, mes));

														// Valida a data
														if (!dadosDep.validarData(dia, mes, ano))
															System.out.println("\nData Inválida");

													} while (!dadosDep.validarData(dia, mes, ano));

													// Monta a data
													dataNascimento = dadosDep.montarData(dia, mes, ano);

													do {

														// CERTIDÃO DE CASAMENTO
														System.out.println(
																"\nEnvie a Certidão de Casamento do colaborador.\nDigite o nome completo do arquivo:");
														nomeArquivo = sc.next();

														try {

															// Envia os dados para as classes bean e cadastra o
															// dependente
															dadosDep = new DadosDependente(nome, cpf, sexo,
																	dataNascimento);
															dep = new Dependente(tipo, dadosDep, nomeDocumento,
																	nomeArquivo, docDao.importarArquivo(nomeArquivo));
															depDao.cadastrar(matricula, dep);

															System.out.println("\nDependente enviado!");

														} catch (OperacaoInvalidaException e) {
															System.out.println(e.getMessage());
														} catch (ItemCadastradoException e) {
															System.out.println(e.getMessage());
														} catch (IOException e) {
															System.out.println("\nDocumento não encontrado!");
															nomeArquivo = "errado";
														}

													} while (nomeArquivo.equals("errado"));

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
														cpf = sc.next();

														// Checa o CPF
														if (!dados.isCPF(cpf)) {
															System.out.println("\nCPF inválido!");
														}

													} while (!dadosDep.isCPF(cpf));

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
													sexo = dadosDep.selecionarSexo(codigoSexo);

													// DATA DE NASCIMENTO
													do {
														// ANO
														do {
															try {
																System.out.println(
																		"\nDigite o ano em que o filho do colaborador nasceu:");
																ano = sc.nextInt();

																// Checa o ano
																if (!dadosDep.validarAno(ano)) {
																	System.out.println("\nData Inválida");
																}
															} catch (InputMismatchException e) {
																System.err.println("Digite somente números!!");
																sc.nextLine();
																ano = 0;
															}

														} while (!dadosDep.validarAno(ano));

														// MÊS
														do {
															try {
																System.out.println(
																		"\nDigite o mês em que o filho do colaborador nasceu:");
																mes = sc.nextInt();

																// Checa o mês
																if (!dadosDep.validarMes(mes)) {
																	System.out.println("\nData Inválida");
																}
															} catch (InputMismatchException e) {
																System.err.println("Digite somente números!!");
																sc.nextLine();
																mes = 0;
															}
														} while (!dadosDep.validarMes(mes));

														// DIA
														do {
															try {
																System.out.println(
																		"\nDigite o dia em que o filho do colaborador nasceu:");
																dia = sc.nextInt();

																// Checa o dia
																if (!dadosDep.validarDia(dia, mes)) {
																	System.out.println("\nData Inválida");
																}
															} catch (InputMismatchException e) {
																System.err.println("Digite somente números!!");
																sc.nextLine();
																dia = 0;
															}
														} while (!dadosDep.validarDia(dia, mes));

														// Valida a data
														if (!dadosDep.validarData(dia, mes, ano))
															System.out.println("\nData Inválida");

													} while (!dadosDep.validarData(dia, mes, ano));

													// Monta a data
													dataNascimento = dadosDep.montarData(dia, mes, ano);

													do {

														// CERTIDÃO DE NASCIMENTO
														System.out.println("\nEnvie a Certidão de Nascimento do " + nome
																+ ".\nDigite o nome completo do arquivo:");
														nomeArquivo = sc.next();

														try {

															// Envia os dados para as classes bean e cadastra o
															// dependente
															dadosDep = new DadosDependente(nome, cpf, sexo,
																	dataNascimento);
															dep = new Dependente(tipo, dadosDep, nomeDocumento,
																	nomeArquivo, docDao.importarArquivo(nomeArquivo));
															depDao.cadastrar(matricula, dep);

															System.out.println("\nDependente enviado!");

														} catch (OperacaoInvalidaException e) {
															System.out.println(e.getMessage());
														} catch (ItemCadastradoException e) {
															System.out.println(e.getMessage());
														} catch (IOException e) {
															System.out.println("\nDocumento não encontrado!");
															nomeArquivo = "errado";
														}

													} while (nomeArquivo.equals("errado"));

													break;

												// PAI
												case "3":

													tipo = "GENITOR";

													// SEXO
													sexo = "MASCULINO";

													// NOME COMPLETO
													do {
														System.out.println(
																"\nDigite o nome completo do pai do colaborador:");
														nome = sc.next() + sc.nextLine();
														nome = nome.toUpperCase();
													} while (nome.isEmpty());

													// CPF
													do {
														System.out.println(
																"\nDigite o CPF do pai do colaborador (sem pontos nem traços - apenas números):\n"
																		+ "Exemplo de CPF: 27809562835");
														cpf = sc.next();

														// Checa o CPF
														if (!dados.isCPF(cpf)) {
															System.out.println("\nCPF inválido!");
														}

													} while (!dadosDep.isCPF(cpf));

													// DATA DE NASCIMENTO
													do {
														// ANO
														do {
															try {
																System.out.println(
																		"\nDigite o ano em que o pai do colaborador nasceu:");
																ano = sc.nextInt();

																// Checa o ano
																if (!dadosDep.validarAno(ano)) {
																	System.out.println("\nData Inválida");
																}
															} catch (InputMismatchException e) {
																System.err.println("Digite somente números!!");
																sc.nextLine();
																ano = 0;
															}

														} while (!dadosDep.validarAno(ano));

														// MÊS
														do {
															try {
																System.out.println(
																		"\nDigite o mês em que o pai do colaborador nasceu:");
																mes = sc.nextInt();

																// Checa o mês
																if (!dadosDep.validarMes(mes)) {
																	System.out.println("\nData Inválida");
																}
															} catch (InputMismatchException e) {
																System.err.println("Digite somente números!!");
																sc.nextLine();
																mes = 0;
															}
														} while (!dadosDep.validarMes(mes));

														// DIA
														do {
															try {
																System.out.println(
																		"\nDigite o dia em que o pai do colaborador nasceu:");
																dia = sc.nextInt();

																// Checa o dia
																if (!dadosDep.validarDia(dia, mes)) {
																	System.out.println("\nData Inválida");
																}
															} catch (InputMismatchException e) {
																System.err.println("Digite somente números!!");
																sc.nextLine();
																dia = 0;
															}
														} while (!dadosDep.validarDia(dia, mes));

														// Valida a data
														if (!dadosDep.validarData(dia, mes, ano))
															System.out.println("\nData Inválida");

													} while (!dadosDep.validarData(dia, mes, ano));

													// Monta a data
													dataNascimento = dadosDep.montarData(dia, mes, ano);

													do {

														// RG
														System.out.println(
																"\nEnvie o RG do pai do colaborador.\nDigite o nome completo do arquivo:");
														nomeArquivo = sc.next();

														try {

															// Envia os dados para as classes bean e cadastra o
															// dependente
															dadosDep = new DadosDependente(nome, cpf, sexo,
																	dataNascimento);
															dep = new Dependente(tipo, dadosDep, nomeDocumento,
																	nomeArquivo, docDao.importarArquivo(nomeArquivo));
															depDao.cadastrar(matricula, dep);

															System.out.println("\nDependente enviado!");

														} catch (OperacaoInvalidaException e) {
															System.out.println(e.getMessage());
														} catch (ItemCadastradoException e) {
															System.out.println(e.getMessage());
														} catch (IOException e) {
															System.out.println("\nDocumento não encontrado!");
															nomeArquivo = "errado";
														}

													} while (nomeArquivo.equals("errado"));

													break;

												// MÃE
												case "4":

													tipo = "GENITOR";

													// SEXO
													sexo = "FEMININO";

													// NOME COMPLETO
													do {
														System.out.println(
																"\nDigite o nome completo da mãe do colaborador:");
														nome = sc.next() + sc.nextLine();
														nome = nome.toUpperCase();
													} while (nome.isEmpty());

													// CPF
													do {
														System.out.println(
																"\nDigite o CPF da mãe do colaborador (sem pontos nem traços - apenas números):\n"
																		+ "Exemplo de CPF: 70850941253");
														cpf = sc.next();

														// Checa o CPF
														if (!dados.isCPF(cpf)) {
															System.out.println("\nCPF inválido!");
														}

													} while (!dadosDep.isCPF(cpf));

													// DATA DE NASCIMENTO
													do {
														// ANO
														do {
															try {
																System.out.println(
																		"\nDigite o ano em que a mãe do colaborador nasceu:");

																// Checa o ano
																ano = sc.nextInt();
																if (!dadosDep.validarAno(ano)) {
																	System.out.println("\nData Inválida");
																}
															} catch (InputMismatchException e) {
																System.err.println("Digite somente números!!");
																sc.nextLine();
																ano = 0;
															}

														} while (!dadosDep.validarAno(ano));

														// MÊS
														do {
															try {
																System.out.println(
																		"\nDigite o mês em que a mãe do colaborador nasceu:");

																// Checa o mês
																mes = sc.nextInt();
																if (!dadosDep.validarMes(mes)) {
																	System.out.println("\nData Inválida");
																}
															} catch (InputMismatchException e) {
																System.err.println("Digite somente números!!");
																sc.nextLine();
																mes = 0;
															}
														} while (!dadosDep.validarMes(mes));

														// DIA
														do {
															try {
																System.out.println(
																		"\nDigite o dia em que a mãe do colaborador nasceu:");

																// Checa o dia
																dia = sc.nextInt();
																if (!dadosDep.validarDia(dia, mes)) {
																	System.out.println("\nData Inválida");
																}
															} catch (InputMismatchException e) {
																System.err.println("Digite somente números!!");
																sc.nextLine();
																dia = 0;
															}
														} while (!dadosDep.validarDia(dia, mes));

														// Valida a data
														if (!dadosDep.validarData(dia, mes, ano))
															System.out.println("\nData Inválida");

													} while (!dadosDep.validarData(dia, mes, ano));

													// Monta a data
													dataNascimento = dadosDep.montarData(dia, mes, ano);

													do {

														// RG
														System.out.println(
																"\nEnvie o RG da mãe do colaborador.\nDigite o nome completo do arquivo:");
														nomeArquivo = sc.next();

														try {

															// Envia os dados para as classes bean e cadastra o
															// dependente
															dadosDep = new DadosDependente(nome, cpf, sexo,
																	dataNascimento);
															dep = new Dependente(tipo, dadosDep, nomeDocumento,
																	nomeArquivo, docDao.importarArquivo(nomeArquivo));
															depDao.cadastrar(matricula, dep);

															System.out.println("\nDependente enviado!");

														} catch (OperacaoInvalidaException e) {
															System.out.println(e.getMessage());
														} catch (ItemCadastradoException e) {
															System.out.println(e.getMessage());
														} catch (IOException e) {
															System.out.println("\nDocumento não encontrado!");
															nomeArquivo = "errado";
														}

													} while (nomeArquivo.equals("errado"));

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

														depDao.remover(matricula, codigoDependente);
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

									} catch (ItemNaoEncontradoException | AtualizacaoNaoRealizadaException e) {
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
							colaboradorDao.exportar();
						} catch (IOException e) {
							System.out.println("\nErro na operação!");
						}

						System.out.println("\nColaboradores exportados!");

						break;

//				<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>

					// CADASTRA NOVO COLABORADOR
					case "3":

						// DEPARTAMENTO

						// Mostra os departamentos

						// Carrega e mostra a lista de departamentos
						depto.setLista(deptoDao.listar());
						for (String item : depto.getLista())
							System.out.println(item);

						do {
							try {
								System.out.println("\nDigite o código do departamento do colaborador:");
								codigoDepto = sc.nextInt();
							} catch (InputMismatchException e) {
								System.err.println("Digite somente números!!");
								sc.nextLine();
								codigoDepto = 0;
							}

						} while (codigoDepto == 0);

						// APELIDO

						try {
							do {
								System.out.println("\nDigite o apelido do colaborador:");
								apelido = sc.next() + sc.nextLine();
								apelido = apelido.toUpperCase();
							} while (apelido.isEmpty());
						} catch (NullPointerException e) {
							System.out.println("\nOperação cancelada!:");
							break;
						}

						// EMAIL
						try {
							do {
								System.out.println("\nDigite o email do colaborador:");
								email = sc.next();
							} while (email.isEmpty());
						} catch (NullPointerException e) {
							System.out.println("\nOperação cancelada!:");
							break;
						}

						try {

							// Monta o departamento
							depto = deptoDao.pesquisar(codigoDepto);

							// Cria o colaborador
							colaborador = new Colaborador(depto, apelido, email);

							// Envia o colaborador
							colaboradorDao.cadastrar(colaborador);

							System.out.println("\nA matrícula do novo colaborador é "
									+ colaboradorDao.getMatricula(email)
									+ ".\nEssa também será a senha para o Colaborador acessar o sistema e realizar o seu cadastro.");

						} catch (ItemNaoEncontradoException e) {
							System.out.println(e.getMessage());
						}

						break;

//				<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>

					// SAIR
					case "4":
						System.out.println("\nSaindo...!\n");
						break;

					default:
						System.out.println("\nOpção Inválida!\n");

					}// Fim switch Menu Principal

				} while (!menuPrincipal.equals("4")); // Fim do looping do Menu Principal

			} // logado
			else
				System.out.println("\nLogin Inválido!\n");

		} catch (ClassNotFoundException |

				SQLException e) {
			System.out.println("\nOperação não realizada!");
			e.printStackTrace();
		}

		// Fecha o scanner
		sc.close();

	}// Main
}// Classe
