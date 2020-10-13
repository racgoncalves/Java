package br.com.fiap.tds.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.tds.bean.Login;
import br.com.fiap.tds.bean.Colaborador;
import br.com.fiap.tds.exception.AtualizacaoNaoRealizadaException;
import br.com.fiap.tds.exception.ItemNaoEncontradoException;
import br.com.fiap.tds.factory.ConnectionFactory;

/**
 * Classe responsável por acessar o banco de dados, realiza as operações básicas
 * (CRUD) dos dados do colaborador
 * 
 * @author Rodrigo Chiarelli
 * @version 4.0
 */
public class ColaboradorDao {

	// CADASTRAR >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Cadastra os dados do colaborador no banco de dados
	 * 
	 * @param colaborador Objeto contendo o dados do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void cadastrar(Colaborador colab) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"INSERT INTO T_XCAVE_COLABORADOR (CD_MATRICULA, NM_COLABORADOR, NR_CPF, NR_PIS, DS_SEXO,"
						+ " NM_NACIONALIDADE, NM_NATURALIDADE, DT_NASCIMENTO, DS_EST_CIVIL, QT_FILHOS, NM_ETNIA,"
						+ " TP_CAMISETA, NR_AGENCIA, NR_DIGITO_AGENCIA, NR_CONTA, NR_DIGITO_CONTA, NM_ORIENT_SEXUAL,"
						+ " NM_RELIGIAO)" + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		// Coloca os valores na query
		stmt.setInt(1, colab.getLogin().getMatricula());
		stmt.setString(2, colab.getNome());
		stmt.setString(3, colab.getCpf());
		stmt.setString(4, colab.getPis());
		stmt.setString(5, colab.getSexo());
		stmt.setString(6, colab.getNacionalidade());
		stmt.setString(7, colab.getNaturalidade());
		stmt.setString(8, colab.getDataNascimento());
		stmt.setString(9, colab.getEstadoCivil());
		stmt.setInt(10, colab.getFilhos());
		stmt.setString(11, colab.getEtnia());
		stmt.setString(12, colab.getCamiseta());
		stmt.setString(13, colab.getAgencia());
		stmt.setString(14, colab.getDigitoAgencia());
		stmt.setString(15, colab.getConta());
		stmt.setString(16, colab.getDigitoConta());
		stmt.setString(17, colab.getOrientacao());
		stmt.setString(18, colab.getReligiao());

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	// PESQUISAR >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Recupera os dados do colaborador
	 * 
	 * @param matricula Matrícula do colaborador
	 * @return colaborador Objeto contendo o dados do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public Colaborador pesquisar(int matricula)
			throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {

		Colaborador colaborador = null;

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Criar o PreparedStatement
		PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM T_XCAVE_COLABORADOR WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setInt(1, matricula);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {

			colaborador = parse(resultado);

		}

		// Valida se encontrou os dados
		if (colaborador == null)
			throw new ItemNaoEncontradoException("\nO colaborador não foi encontrado");

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return colaborador;
	}

	/**
	 * Recupera todos os colaboradores cadastrados
	 * 
	 * @return List<String> Lista com todos os colaboradores cadastrados
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<String> listar() throws ClassNotFoundException, SQLException {

		List<String> lista = new ArrayList<String>();

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Criar o objeto para executar um comando SQL
		Statement stmt = conexao.createStatement();

		// Realizar a pesquisa de todos os funcionários
		ResultSet resultado = stmt.executeQuery("SELECT DISTINCT * FROM T_XCAVE_COLABORADOR ORDER BY NM_COLABORADOR");

		while (resultado.next()) {

			Colaborador colaborador = parse(resultado);

			lista.add(colaborador.toString());
		}

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return lista;
	}

	/**
	 * Verifica se os dados do colaborador já foram cadastrados
	 * 
	 * @param matricula Matrícula do colaborador
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean isCadastrado(int matricula) throws ClassNotFoundException, SQLException {

		boolean x = false;

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Criar o PreparedStatement
		PreparedStatement stmt = conexao
				.prepareStatement("SELECT CD_MATRICULA FROM T_XCAVE_COLABORADOR WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setInt(1, matricula);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {

			x = true;

		}

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return x;
	}

	// ATUALIZAR >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Atualiza os dados do colaborador
	 * 
	 * @param colaborador Objeto contendo os dados do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws AtualizacaoNaoRealizadaException
	 */
	public void atualizar(Colaborador colab)
			throws ClassNotFoundException, SQLException, AtualizacaoNaoRealizadaException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"UPDATE T_XCAVE_COLABORADOR SET NM_COLABORADOR = ?, NR_CPF = ?, NR_PIS = ?, DS_SEXO = ?,"
						+ " NM_NACIONALIDADE = ?, NM_NATURALIDADE = ?, DT_NASCIMENTO = ?, DS_EST_CIVIL = ?, QT_FILHOS = ?,"
						+ " NM_ETNIA = ?, TP_CAMISETA = ?, NR_AGENCIA = ?, NR_DIGITO_AGENCIA = ?, NR_CONTA = ?,"
						+ " NR_DIGITO_CONTA = ?, NM_ORIENT_SEXUAL = ?, NM_RELIGIAO = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, colab.getNome());
		stmt.setString(2, colab.getCpf());
		stmt.setString(3, colab.getPis());
		stmt.setString(4, colab.getSexo());
		stmt.setString(5, colab.getNacionalidade());
		stmt.setString(6, colab.getNaturalidade());
		stmt.setString(7, colab.getDataNascimento());
		stmt.setString(8, colab.getEstadoCivil());
		stmt.setInt(9, colab.getFilhos());
		stmt.setString(10, colab.getEtnia());
		stmt.setString(11, colab.getCamiseta());
		stmt.setString(12, colab.getAgencia());
		stmt.setString(13, colab.getDigitoAgencia());
		stmt.setString(14, colab.getConta());
		stmt.setString(15, colab.getDigitoConta());
		stmt.setString(16, colab.getOrientacao());
		stmt.setString(17, colab.getReligiao());
		stmt.setInt(18, colab.getLogin().getMatricula());

		// Executar a query
		int qtd = stmt.executeUpdate();

		// Valida se atualizou o dado
		if (qtd == 0)
			throw new AtualizacaoNaoRealizadaException();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	// OUTROS >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Preenche a classe bean dos dados do colaborador
	 * 
	 * @param resultado ResultSet com a pesquisa realizada
	 * @return colaborador Objeto contendo os dados do colaborador
	 * @throws SQLException
	 */
	private Colaborador parse(ResultSet resultado) throws SQLException {

		Colaborador colab = new Colaborador();
		Login login = new Login();

		login.setMatricula(resultado.getInt("CD_MATRICULA"));

		colab.setLogin(login);
		colab.setNome(resultado.getString("NM_COLABORADOR"));
		colab.setCpf(resultado.getString("NR_CPF"));
		colab.setPis(resultado.getString("NR_PIS"));
		colab.setSexo(resultado.getString("DS_SEXO"));
		colab.setNacionalidade(resultado.getString("NM_NACIONALIDADE"));
		colab.setNaturalidade(resultado.getString("NM_NATURALIDADE"));
		colab.setDataNascimento(colab.getDataBD(resultado.getString("DT_NASCIMENTO")));
		colab.setEstadoCivil(resultado.getString("DS_EST_CIVIL"));
		colab.setFilhos(resultado.getInt("QT_FILHOS"));
		colab.setEtnia(resultado.getString("NM_ETNIA"));
		colab.setCamiseta(resultado.getString("TP_CAMISETA"));
		colab.setAgencia(resultado.getString("NR_AGENCIA"));
		colab.setDigitoAgencia(resultado.getString("NR_DIGITO_AGENCIA"));
		colab.setConta(resultado.getString("NR_CONTA"));
		colab.setDigitoConta(resultado.getString("NR_DIGITO_CONTA"));
		colab.setOrientacao(resultado.getString("NM_ORIENT_SEXUAL"));
		colab.setReligiao(resultado.getString("NM_RELIGIAO"));

		return colab;

	}

	/**
	 * Exporta um arquivo .txt com todos os colaboradores
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void exportar() throws ClassNotFoundException, SQLException, IOException {

		// Escrever o arquivo
		FileWriter outputStream = new FileWriter("dados_colaboradores.txt");
		PrintWriter arquivoEscrita = new PrintWriter(outputStream);

		for (String lista : listar()) {

			arquivoEscrita.println(lista);

		}

		// Fechar os recursos
		arquivoEscrita.close();
		outputStream.close();
	}

}// Classe
