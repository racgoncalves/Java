package br.com.fiap.tds.view;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.com.fiap.tds.bean.Dados;
import br.com.fiap.tds.bean.Dependentes;
import br.com.fiap.tds.bean.Documentos;
import br.com.fiap.tds.dao.DadosDao;
import br.com.fiap.tds.dao.DependentesDao;
import br.com.fiap.tds.dao.DocumentosDao;
import br.com.fiap.tds.dao.LoginColaboradorDao;
import br.com.fiap.tds.dao.LoginRHDao;

public class TerminalRH {

	public static void main(String args[]) {

		boolean login = true;
		boolean encontrou = true;
		int i = 0;
		String matricula = "";
		String usuario = "";
		String senha = "";
		String menuPrincipal = "";
		String menuPesquisar = "";
		String menuEditar = "";
		String menuUpload = "";
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
		String cidadeNascimento = "";
		String estado = "";
		String pais = "";
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
		int nFilhosDependentes = 0;
		String aux = "";

		Scanner sc = new Scanner(System.in);

		// <<<<<<<<<<<<<<<<<<<<<<<< SISTEMA P/ RECURSOS HUMANOS >>>>>>>>>>>>>>>>>>>>>>>>

		System.out.println("\nSistema de Recursos Humanos");

		// <<<<<<<<<<<<<<< LOGIN RH >>>>>>>>>>>>>>>

		System.out.println("\nPara testar utilize:\nUsuário = usuario\nSenha = fiap123");

		LoginRHDao loginRH = new LoginRHDao();

		Dados dados = new Dados();

		try {
			do {

				System.out.println("\nDigite o usuário:");
				usuario = sc.next();

				if (!loginRH.isUsuario(usuario)) {
					System.out.println("\nUsuário não encontrado!");
				}

			} while (!loginRH.isUsuario(usuario));

			do {

				System.out.println("\nDigite a senha:");
				senha = sc.next();

				if (!loginRH.isSenha(senha, usuario)) {
					System.out.println("\nSenha incorreta!");
				}

			} while (!loginRH.isSenha(senha, usuario));
		} catch (Exception e) {
			System.out.println("\nNão foi possível realizar o login!");
			login = false;
			e.printStackTrace();
		}

		if (login) {

			System.out.println("\nLogin efetuado com sucesso!");

			// <<<<< MENU RECURSOS HUMANOS >>>>>>
			do {
				do {
					System.out.println("\nMenu Principal\n" + "\n1 - Pesquisar/Editar/Remover o colaborador"
							+ "\n2 - Exportar os dados de todos os colaboradores"
							+ "\n3 - Cadastrar email e senha de um novo colaborador" + "\n4 - Sair do programa"
							+ "\n\nEscolha qual operação deseja executar:");
					menuPrincipal = sc.next();
				} while (menuPrincipal.isEmpty());

				LoginColaboradorDao loginDao = new LoginColaboradorDao();
				DadosDao dadosDao = new DadosDao();
				DocumentosDao docDao = new DocumentosDao();
				Documentos doc = new Documentos();
				DependentesDao depDao = new DependentesDao();
				Dependentes dep = new Dependentes();
				HashMap<String, List<byte[]>> mapaDependentes = new HashMap<String, List<byte[]>>();
				List<byte[]> lista = new ArrayList<byte[]>();

				switch (menuPrincipal) {

//			<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>

				// PESQUISA O COLABORADOR
				case "1":
					do {
						encontrou = true;

						do {
							System.out.println("\n1 - Pesquisar pela matrícula" + "\n2 - Pesquisar pelo email"
									+ "\n3 - Pesquisar pelo CPF" + "\n4 - Retornar ao menu principal"
									+ "\nEscolha qual operação deseja executar:");
							menuPesquisar = sc.next();
						} while (menuPesquisar.isEmpty());

						try {

							switch (menuPesquisar) {

							// PESQUISA MATRICULA
							case "1":
								try {

									do {
										System.out.println("\nDigite a matrícula do colaborador:");
										matricula = sc.next();
									} while (matricula.isEmpty());
								} catch (NullPointerException e) {
									break;
								}

								dados = dadosDao.getColaborador(matricula);

								if (dados == null) {
									System.out.println("\nColaborador não encontrado");
									encontrou = false;
								}

								break;

							// PESQUISA EMAIL
							case "2":
								try {
									do {
										System.out.println("\nDigite o email do colaborador:");
										email = sc.next();
									} while (email.isEmpty());
								} catch (NullPointerException e) {
									break;
								}

								matricula = loginDao.getMatricula(email);

								if (matricula.isEmpty()) {
									System.out.println("\nEmail não encontrado!");
									encontrou = false;
								}

								break;

							// PESQUISA CPF
							case "3":
								try {
									do {
										System.out
												.println("\nDigite seu CPF (sem pontos nem traços - apenas números):");
										cpf = sc.next();

										if (cpf.length() != 11) {
											System.out.println("\nO CPF tem 11 dígitos!");
										} else if (!cpf.matches("[0-9]+")) {
											System.out.println("\nDigite somente números!");
										} else if (!dados.isCPF(cpf)) {
											System.out.println("\nEste CPF não existe!");
										}

									} while (!dados.isCPF(cpf));

									cpf = cpf.substring(0, 3).concat(".").concat(cpf.substring(3, 6)).concat(".")
											.concat(cpf.substring(6, 9)).concat("-").concat(cpf.substring(9));

								} catch (NullPointerException e) {
									break;
								}

								matricula = dadosDao.getMatricula(cpf);

								if (matricula.isEmpty()) {
									System.out.println("\nCPF não encontrado!");
									encontrou = false;
								}

								break;

							// SAIR
							case "4":
								encontrou = false;
								break;

							default:
								encontrou = false;
								System.out.println("\nOpção não existente");
							}

						} catch (ClassNotFoundException | SQLException e1) {
							System.out.println("\nNão foi possível completar a operação!");
							e1.printStackTrace();
						}

//					<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>

						// ENCONTROU O COLABORADOR
						if (encontrou) {

							try {

								// MENU EDIÇÂO
								do {
									System.out.println("\nMatrícula: " + matricula + "\n\nDados do Colaborador:\n"
											+ dadosDao.getColaborador(matricula) + "\n17 - Email: "
											+ loginDao.getEmail(matricula)
											+ "\n\nPara alterar algum dado digite o seu respectivo código."
											+ "\nPara fazer o download dos documentos do COLABORADOR digite 'DC'."
											+ "\nPara fazer o download dos documentos de seus DEPENDENTES digite 'DD'."
											+ "\nPara fazer o UPLOAD de algum documento do COLABORADOR digite 'UC'."
											+ "\nPara fazer o UPLOAD dos documentos de algum DEPENDENTE digite 'UD'."
											+ "\nPara REMOVER o COLABORADOR digite 'RC'."
											+ "\nPara REMOVER algum DEPENDENTE digite 'RD'."
											+ "\nPara NÃO realizar nenhuma operação e voltar ao menu anterior digite 'N'.");

									menuEditar = sc.next().toUpperCase();

									switch (menuEditar) {

									// NOME COMPLETO
									case "1":

										do {
											System.out.println("\nDigite seu nome completo:");
											nome = sc.next() + sc.nextLine();
											nome = nome.toUpperCase();
										} while (nome.isEmpty());

										dadosDao.setNome(matricula, nome);

										System.out.println("\nDado atualizado com sucesso!");

										break;

									// CPF
									case "2":

										do {
											System.out.println(
													"\nDigite seu CPF (sem pontos nem traços - apenas números):\n"
															+ "Exemplo de CPF: 04620829463");
											cpf = sc.next();

											if (cpf.length() != 11) {
												System.out.println("\nO CPF tem 11 dígitos!");
											} else if (!cpf.matches("[0-9]+")) {
												System.out.println("\nDigite somente números!");
											} else if (!dados.isCPF(cpf)) {
												System.out.println("\nEste CPF não existe!");
											}

										} while (!dados.isCPF(cpf));

										cpf = cpf.substring(0, 3).concat(".").concat(cpf.substring(3, 6)).concat(".")
												.concat(cpf.substring(6, 9)).concat("-").concat(cpf.substring(9));

										dadosDao.setCPF(matricula, cpf);

										System.out.println("\nDado atualizado com sucesso!");

										break;

									// PIS
									case "3":

										do {
											System.out.println(
													"\nDigite seu PIS (sem pontos nem traços - apenas números):\n"
															+ "Exemplo de PIS: 17033259504");
											pis = sc.next();

											if (pis.length() != 11) {
												System.out.println("\nO PIS/PASEP tem 11 dígitos!");
											} else if (!pis.matches("[0-9]+")) {
												System.out.println("\nDigite somente números!");
											} else if (!dados.isPis(pis)) {
												System.out.println("\nEste PIS/PASEP não existe!");
											}

										} while (!dados.isPis(pis));

										pis = pis.substring(0, 3).concat(".").concat(pis.substring(3, 8)).concat(".")
												.concat(pis.substring(8, 10)).concat("-").concat(pis.substring(10));

										dadosDao.setPIS(matricula, pis);

										System.out.println("\nDado atualizado com sucesso!");

										break;

									// SEXO
									case "4":

										System.out.println(
												"\nDigite seu sexo de nascimento ('F' para FEMININO, 'M' para MASCULINO ou 'O' para OUTRO):");
										codigoSexo = sc.next();
										codigoSexo = codigoSexo.toUpperCase();
										if (!codigoSexo.matches("[F]") && !codigoSexo.matches("[M]")
												&& !codigoSexo.matches("[O]")) {
											System.out.println("\nValor Incorreto!");
											codigoSexo = "";
										}

										sexo = dados.carregaSexo(codigoSexo);

										dadosDao.setSexo(matricula, sexo);

										System.out.println("\nDado atualizado com sucesso!");

										break;

									// PAÍS
									case "5":

										do {
											System.out.println("\nDigite seu país de nascimento:");
											pais = sc.next() + sc.nextLine();
											pais = pais.toUpperCase();
										} while (pais.isEmpty());

										dadosDao.setPais(matricula, pais);

										System.out.println("\nDado atualizado com sucesso!");

										break;

									// ESTADO
									case "6":

										do {
											System.out.println("\nDigite seu estado de nascimento:");
											estado = sc.next() + sc.nextLine();
											estado = estado.toUpperCase();
										} while (estado.isEmpty());

										dadosDao.setEstado(matricula, estadoCivil);

										System.out.println("\nDado atualizado com sucesso!");

										break;

									// CIDADE DE NASCIMENTO
									case "7":

										do {
											System.out.println("\nDigite sua cidade de nascimento:");
											cidadeNascimento = sc.next() + sc.nextLine();
											cidadeNascimento = cidadeNascimento.toUpperCase();
										} while (cidadeNascimento.isEmpty());

										dadosDao.setCidade(matricula, cidadeNascimento);

										System.out.println("\nDado atualizado com sucesso!");

										break;

									// DATA DE NASCIMENTO
									case "8":

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

										dadosDao.setData(matricula, dataNascimento);

										System.out.println("\nDado atualizado com sucesso!");

										break;

									// ESTADO CIVIL
									case "9":

										do {
											try {
												System.out.println("\n1 - SOLTEIRO\n" + "2 - CASADO\n"
														+ "3 - SEPARADO\n" + "4 - DIVORCIADO\n" + "5 - VIÚVO\n"
														+ "Digite o código do seu estado civil:");
												codigoEstadoCivil = sc.nextInt();
											} catch (InputMismatchException e) {
												System.err.println("Digite somente números!!");
												sc.nextLine();
												codigoEstadoCivil = 0;
											}
										} while (codigoEstadoCivil < 1 || codigoEstadoCivil > 5);

										estadoCivil = dados.carregaEstadoCivil(codigoEstadoCivil);

										dadosDao.setEstadoCivil(matricula, estadoCivil);

										System.out.println("\nDado atualizado com sucesso!");

										break;

									// NÚMERO FILHOS
									case "10":

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

										dadosDao.setFilhos(matricula, numeroFilhos);

										System.out.println("\nDado atualizado com sucesso!");

										break;

									// AGÊNCIA BRADESCO
									case "11":

										do {
											System.out.println(
													"\nDigite o número da sua agência (sem o dígito verificador):\n"
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

										dadosDao.setAgencia(matricula, agenciaNumero);
										dadosDao.setDigitoAgencia(matricula, agenciaDigito);

										System.out.println("\nDado atualizado com sucesso!");

										break;

									// CONTA BRADESCO
									case "12":

										do {
											System.out.println(
													"\nDigite o número da sua conta (sem o dígito verificador):\n"
															+ "Obs: O número da conta tem até 7 dígitos");
											contaNumero = sc.next();

											if (contaNumero.length() > 7 || contaNumero.length() < 1) {
												System.out.println("\nValor Incorreto!");
											}

											else if (!contaNumero.matches("[0-9]+")) {
												System.out.println("\nDigite somente números!");
											}

										} while (contaNumero.length() > 7 || contaNumero.length() < 1
												|| !contaNumero.matches("[0-9]+"));

										do {
											System.out.println("\nDigite o dígito verificador da sua conta:");
											contaDigito = sc.next();
											if (!contaDigito.matches("[0-9]")) {
												System.out.println("\nValor Incorreto!");
											}

										} while (!contaDigito.matches("[0-9]"));

										dadosDao.setConta(matricula, contaNumero);
										dadosDao.setDigitoConta(matricula, contaDigito);

										System.out.println("\nDado atualizado com sucesso!");

										break;

									// ETNIA
									case "13":

										do {
											try {
												System.out.println("\n1 - BRANCO\n" + "2 - NEGRO\n" + "3 - PARDO\n"
														+ "4 - INDÍGENA\n" + "Digite o código da sua etnia:");
												codigoEtnia = sc.nextInt();
											} catch (InputMismatchException e) {
												System.err.println("Digite somente números!!");
												sc.nextLine();
												codigoEtnia = 0;
											}
										} while (codigoEtnia < 1 || codigoEtnia > 4);

										etnia = dados.carregaEtnia(codigoEtnia);

										dadosDao.setEtnia(matricula, etnia);

										System.out.println("\nDado atualizado com sucesso!");

										break;

									// TAMANHO CAMISETA
									case "14":

										do {
											try {
												System.out.println("\n1 - PP\n" + "2 - P\n" + "3 - M\n" + "4 - G\n"
														+ "5 - GG\n" + "Digite o código do tamanho da sua Camiseta:");
												codigoTamanhoCamiseta = sc.nextInt();
											} catch (InputMismatchException e) {
												System.err.println("Digite somente números!!");
												sc.nextLine();
												codigoTamanhoCamiseta = 0;
											}
										} while (codigoTamanhoCamiseta < 1 || codigoTamanhoCamiseta > 5);

										tamanhoCamiseta = dados.carregaTamanhoCamiseta(codigoTamanhoCamiseta);

										dadosDao.setCamisa(matricula, tamanhoCamiseta);

										System.out.println("\nDado atualizado com sucesso!");

										break;

									// ORIENTAÇÃO SEXUAL
									case "15":
										do {
											System.out.println(
													"\nQual a sua orientação sexual? (Digite N para não informar)");
											orientacao = sc.next() + sc.nextLine();
											orientacao = orientacao.toUpperCase();
											if (orientacao.equals("N")) {
												orientacao = "NÃO INFORMADO";
											}
										} while (orientacao.isEmpty());

										dadosDao.setOrientacao(matricula, orientacao);

										System.out.println("\nDado atualizado com sucesso!");

										break;

									// RELIGIÃO
									case "16":

										do {
											System.out.println("\nQual a sua religião? (Digite N para não informar)");
											religiao = sc.next() + sc.nextLine();
											religiao = religiao.toUpperCase();
											if (religiao.equals("N")) {
												religiao = "NÃO INFORMADO";
											}
										} while (religiao.isEmpty());

										dadosDao.setReligiao(matricula, religiao);

										System.out.println("\nDado atualizado com sucesso!");

										break;

									// EMAIL
									case "17":

										do {
											System.out.println("\nQual o seu email?");
											email = sc.next();
										} while (email.isEmpty());

										loginDao.setEmail(matricula, email);

										System.out.println("\nDado atualizado com sucesso!");

										break;

//								<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>

									// DOWNLOAD DOCUMENTOS COLABORADOR
									case "DC":

										doc = docDao.getDocumentos(matricula);

										if (doc != null) {

											// RG

											File arquivoRg = new File("rg.pdf");
											FileOutputStream saidaRg = new FileOutputStream(arquivoRg);
											saidaRg.write(doc.getRg());
											saidaRg.flush();
											saidaRg.close();

											// CARTEIRA DE TRABALHO
											File arquivoCarteira = new File("carteiraTrabalho.pdf");
											FileOutputStream saidaCarteira = new FileOutputStream(arquivoCarteira);
											saidaCarteira.write(doc.getCarteiraTrabalho());
											saidaCarteira.flush();
											saidaCarteira.close();

											// COMPROVANTE DE RESIDENCIA
											File arquivoResidencia = new File("comprovanteResidencia.pdf");
											FileOutputStream saidaResidencia = new FileOutputStream(arquivoResidencia);
											saidaResidencia.write(doc.getResidencia());
											saidaResidencia.flush();
											saidaResidencia.close();

											// TÍTULO DE ELEITOR
											File arquivoEleitor = new File("tituloEleitor.pdf");
											FileOutputStream saidaEleitor = new FileOutputStream(arquivoEleitor);
											saidaEleitor.write(doc.getTituloEleitor());
											saidaEleitor.flush();
											saidaEleitor.close();

											// COMPROVANTE DE ESCOLARIDADE
											File arquivoEscolaridade = new File("comprovanteEscolaridade.pdf");
											FileOutputStream saidaEscolaridade = new FileOutputStream(
													arquivoEscolaridade);
											saidaEscolaridade.write(doc.getEscolaridade());
											saidaEscolaridade.flush();
											saidaEscolaridade.close();

											// CERTIFICADO DE RESERVISTA
											if (doc.getReservista() != null) {
												File arquivoReservista = new File("certificadoReservista.pdf");
												FileOutputStream saidaReservista = new FileOutputStream(
														arquivoReservista);
												saidaReservista.write(doc.getReservista());
												saidaReservista.flush();
												saidaReservista.close();
											}

										}

										else
											System.out.println("\nDocumentos não encontrados!");

										break;

//								<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>

									// DOWNLOAD DOCUMENTOS DEPENDENTE
									case "DD":

										dep = depDao.getDependentes(matricula);
										mapaDependentes = dep.getMapaDependentes();

										// CERTIDÃO CASAMENTO
										try {

											if (mapaDependentes.get("CONJUGE").size() > 0) {

												lista = mapaDependentes.get("CONJUGE");
//												System.out.println(lista.size());

												File arquivoCasamento = new File("certidaoCasamento.pdf");
												FileOutputStream saidaCasamento = new FileOutputStream(
														arquivoCasamento);
												saidaCasamento.write(lista.get(0));
												saidaCasamento.flush();
												saidaCasamento.close();
											}

										} catch (NullPointerException e) {
										}

										// CERTIDÃO NASCIMENTO FILHO
										i = 0;

										try {

											if (mapaDependentes.get("FILHO").size() > 0) {

												lista = mapaDependentes.get("FILHO");
//												System.out.println(lista.size());
												do {

													File arquivoNascimento = new File(
															"certidaoNascimentoFilho" + (i + 1) + ".pdf");
													FileOutputStream saidaNasimento = new FileOutputStream(
															arquivoNascimento);
													saidaNasimento.write(lista.get(i));
													saidaNasimento.flush();
													saidaNasimento.close();

													i++;

												} while (i < lista.size());

											}

										} catch (NullPointerException e) {
										}

										// RG GENITOR
										i = 0;

										try {

											if (mapaDependentes.get("GENITOR").size() > 0) {

												lista = mapaDependentes.get("GENITOR");
//												System.out.println(lista.size());
												do {

													File arquivoGenitor = new File("rgGenitor" + (i + 1) + ".pdf");
													FileOutputStream saidaGenitor = new FileOutputStream(
															arquivoGenitor);
													saidaGenitor.write(lista.get(i));
													saidaGenitor.flush();
													saidaGenitor.close();

													i++;

												} while (i < lista.size());

											}

										} catch (NullPointerException e) {
										}
										break;

//									<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>

									// UPLOAD DOCUMENTOS COLABORADOR
									case "UC":
										do {

											do {
												System.out.println("\n1 - RG" + "\n2 - Carteira de Trabalho"
														+ "\n3 - Comprovante de Residência" + "\n4 - Título de Eleitor"
														+ "\n5 - Comprovante de Escolaridade"
														+ "\n6 - Certificado de Reservista" + "\n7 - Retornar ao menu"
														+ "\nEscolha qual operação deseja executar:");
												menuUpload = sc.next();
											} while (menuUpload.isEmpty());

											switch (menuUpload) {

											// RG
											case "1":
												System.out.println(
														"\nEnvie o seu RG.\nDigite o nome do arquivo sem o '.pdf':");

												nomeArquivo = sc.next();
												rg = docDao.lerDocumento(nomeArquivo);
												doc.setRg(rg);
												docDao.setRg(matricula, doc);
												System.out.println("\nDocumento enviado!");

												break;

											// CARTEIRA DE TRABALHO
											case "2":
												System.out.println(
														"\nEnvia a sua Carteira de Trabalho.\nDigite o nome do arquivo sem o '.pdf':");

												nomeArquivo = sc.next();
												carteiraTrabalho = docDao.lerDocumento(nomeArquivo);
												doc.setCarteiraTrabalho(carteiraTrabalho);
												docDao.setCarteiraTrabalho(matricula, doc);
												System.out.println("\nDocumento enviado!");

												break;

											// COMPROVANTE DE RESIDÊNCIA
											case "3":
												System.out.println(
														"\nEnvie o seu Comprovante de Residência.\nDigite o nome do arquivo sem o '.pdf':");

												nomeArquivo = sc.next();
												residencia = docDao.lerDocumento(nomeArquivo);
												doc.setResidencia(residencia);
												docDao.setComprovanteResidencia(matricula, doc);
												System.out.println("\nDocumento enviado!");

												break;

											// TÍTULO DE ELEITOR
											case "4":
												System.out.println(
														"\nEnvie o seu Título de Eleitor.\nDigite o nome do arquivo sem o '.pdf':");

												nomeArquivo = sc.next();
												tituloEleitor = docDao.lerDocumento(nomeArquivo);
												doc.setTituloEleitor(tituloEleitor);
												docDao.setTituloEleitor(matricula, doc);
												System.out.println("\nDocumento enviado!");

												break;

											// COMPROVANTE DE ESCOLARIDADE
											case "5":
												System.out.println(
														"\nEnvie o seu Comprovante de Escolaridade.\nDigite o nome do arquivo sem o '.pdf':");

												nomeArquivo = sc.next();
												escolaridade = docDao.lerDocumento(nomeArquivo);
												doc.setEscolaridade(escolaridade);
												docDao.setComprovanteEscolaridade(matricula, doc);
												System.out.println("\nDocumento enviado!");

												break;

											// CERTIFICADO DE RESERVISTA
											case "6":
												System.out.println(
														"\nEnvie o seu Certificado de Reservista.\nDigite o nome do arquivo sem o '.pdf':");

												nomeArquivo = sc.next();
												reservista = docDao.lerDocumento(nomeArquivo);
												doc.setReservista(reservista);
												docDao.setCertificadoReservista(matricula, doc);
												System.out.println("\nDocumento enviado!");

												break;

											// SAIR
											case "7":
												break;

											default:
												System.out.println("\nOpção não existente");

											}

										} while (!menuUpload.equals("7"));

										break;

//									<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>

									// UPLOAD DOCUMENTOS DEPENDENTE
									case "UD":
										do {

											do {
												System.out.println("\n1 - Certidão de Casamento"
														+ "\n2 - Certidão de Nascimento dos Filhos" + "\n3 - RG Genitor"
														+ "\n4 - Retornar ao menu"
														+ "\nEscolha qual operação deseja executar:");
												menuUpload = sc.next();
											} while (menuUpload.isEmpty());

											switch (menuUpload) {

											// CERTIDÃO DE CASAMENTO
											case "1":

												System.out.println(
														"\nEnvie a sua Certidão de Casamento.\nDigite o nome do arquivo sem o '.pdf':");

												nomeArquivo = sc.next();
												certidaoCasamento = docDao.lerDocumento(nomeArquivo);

												dep.setCertidaoCasamento(certidaoCasamento);
												dep.setTipo("CONJUGE");
												depDao.delCertidaoCasamento(matricula);
												depDao.setCertidaoCasamento(matricula, dep);
												System.out.println("\nDocumento enviado!");

												break;

											// CERTIDÃO DE NASCIMENTO FILHOS
											case "2":

												i = 0;

												do {

													try {
														System.out.println(
																"\nDigite a quantidade de filhos dependentes:");
														nFilhosDependentes = sc.nextInt();

														if (nFilhosDependentes < 0)
															System.out.println(
																	"\nDigite um valor positivo ou 0 para sair!");

													} catch (InputMismatchException e) {
														System.err.println("Digite somente números!!");
														sc.nextLine();
														nFilhosDependentes = -1;
													}

												} while (nFilhosDependentes < 0);

												if (nFilhosDependentes > 0) {

													depDao.delCertidaoNascimento(matricula);

													do {

														i++;

														System.out.println(
																"\nEnvie a Certidão de Nascimento de seu filho nº " + i
																		+ ".\nDigite o nome do arquivo sem o '.pdf':");

														nomeArquivo = sc.next();
														certidaoNascimentoFilho = docDao.lerDocumento(nomeArquivo);

														dep.setCertidaoNascimentoFilho(certidaoNascimentoFilho);
														dep.setTipo("FILHO");
														depDao.setCertidaoNascimento(matricula, dep);

														System.out.println("\nDocumento enviado!");

													} while (i < nFilhosDependentes);

												}

												break;

											// DOCUMENTOS DOS GENITORES
											case "3":

												do {
													try {
														System.out.println(
																"\nSeu pai e/ou sua mãe são seus dependentes no imposto de renda?");
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

												if (respostaPais == 1 || respostaPais == 2 || respostaPais == 3)
													depDao.delRgGenitor(matricula);

												// PAI
												if (respostaPais == 1 || respostaPais == 3) {
													System.out.println(
															"\nEnvie o RG de seu pai.\nDigite o nome do arquivo sem o '.pdf':");

													nomeArquivo = sc.next();
													rgGenitor = docDao.lerDocumento(nomeArquivo);
													dep.setRgGenitor(rgGenitor);
													dep.setTipo("GENITOR");
													depDao.setRgGenitor(matricula, dep);
													System.out.println("\nDocumento enviado!");
												}

												// MÃE
												if (respostaPais == 2 || respostaPais == 3) {
													System.out.println(
															"\nEnvie o RG de sua mãe.\nDigite o nome do arquivo sem o '.pdf':");
													nomeArquivo = sc.next();
													rgGenitor = docDao.lerDocumento(nomeArquivo);
													dep.setRgGenitor(rgGenitor);
													dep.setTipo("GENITOR");
													depDao.setRgGenitor(matricula, dep);
													System.out.println("\nDocumento enviado!");
												}

												break;

											// SAIR
											case "4":
												break;

											default:
												System.out.println("\nOpção não existente");
											}

										} while (!menuUpload.equals("4"));

										break;

//									<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>

									// REMOVE COLABORADOR
									case "RC":
										// Mostra os valores
										System.out.println(
												"\nTem certeza que deseja remover o colaborador? (Digite 'S' para SIM)");
										aux = sc.next();
										if (aux.equalsIgnoreCase("s")) {

											// Remove a chave com seus valores
											dadosDao.deletarDados(matricula);
											System.out.println("\nColaborador removido do sistema");
										}

										break;

//									<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>

									// REMOVE DEPENDENTE
									case "RD":
										// Mostra os valores
										System.out.println(
												"\nDigite o tipo do dependente a ser removido ('CONJUGE','FILHO','GENITOR') ou 'N' para sair:");
										aux = sc.next();
										aux = aux.toUpperCase();

										switch (aux) {

										case "CONJUGE":
											depDao.delCertidaoCasamento(matricula);
											System.out.println("\nDependente removido!");
											break;

										case "FILHO":
											depDao.delCertidaoNascimento(matricula);
											System.out.println("\nDependente removido!");
											break;

										case "GENITOR":
											depDao.delRgGenitor(matricula);
											System.out.println("\nDependente removido!");
											break;

										case "N":
											break;

										default:
											System.out.println("\nOpção não existente");

										}

										break;

//									<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>

									// SAIR
									case "N":
										break;

									default:
										System.out.println("\nOpção não existente");
									}

								} while (!menuEditar.equalsIgnoreCase("n"));

							} catch (Exception e) {
								System.out.println("\nNão foi possível completar a operação!");
								e.printStackTrace();
							}
						}

					} while (!menuPesquisar.equals("4"));

					break;

//				<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>

				// EXPORTA TODOS OS COLABORADORES
				case "2":
					try {

						dadosDao.exportaColaboradores();

						System.out.println("\nColaboradores exportados!");

					} catch (Exception e) {
						System.out.println("\nNão foi possível completar a operação!");
						e.printStackTrace();
					}

					break;

//				<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>

				// CADASTRA EMAIL E SENHA DE NOVO COLABORADOR
				case "3":
					try {
						do {
							System.out.println("\nDigite o email do colaborador:");
							email = sc.next();
						} while (email.isEmpty());
					} catch (NullPointerException e) {
						break;
					}

					try {

						// Cria o colaborador

						loginDao.setLoginColaborador(email);

						System.out.println("\nA matrícula do novo colaborador é " + loginDao.getMatricula(email)
								+ ".\nEssa também será a senha para o Colaborador acessar o sistema e realizar o seu cadastro.");

					} catch (Exception e) {
						System.out.println("\nNão foi possível completar a operação!");
						e.printStackTrace();
					}

					break;

//				<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>

				// SAIR
				case "4":
					System.out.println("\nSaindo...!\n");
					break;

				default:
					System.out.println("\nOpção Inválida!\n");

				}

			} while (!menuPrincipal.equals("4"));

		}

		sc.close();

	}

}// Classe
