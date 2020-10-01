package br.com.fiap.tds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.tds.bean.ContaBanco;
import br.com.fiap.tds.exception.AtualizacaoNaoRealizadaException;
import br.com.fiap.tds.exception.ItemNaoEncontradoException;
import br.com.fiap.tds.factory.ConnectionFactory;

/**
 * Classe responsável por acessar o banco de dados e realizar as operações
 * básicas (CRUD) dos dados bancários do colaborador
 * 
 * @author Rodrigo Chiarelli
 * @version 1.0
 */
public class ContaBancoDao {

	// CRIA >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Cadastra os dados bancários no banco de dados
	 * 
	 * @param matricula  Matrícula do colaborador
	 * @param contaBanco Objeto contendo os dados bancários
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void cadastrar(int matricula, ContaBanco contaBanco) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"INSERT INTO T_XCAVE_CONTA_BANCO (CD_MATRICULA, NR_AGENCIA, NR_DIGITO_AGENCIA, NR_CONTA, NR_DIGITO_CONTA) "
						+ "VALUES (?,?,?,?,?)");

		// Coloca os valores na query
		stmt.setInt(1, matricula);
		stmt.setInt(2, contaBanco.getAgencia());
		stmt.setInt(3, contaBanco.getDigitoAgencia());
		stmt.setInt(4, contaBanco.getConta());
		stmt.setInt(5, contaBanco.getDigitoConta());

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	// PESQUISA >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Recupera os dados bancários
	 * 
	 * @param matricula Matrícula do colaborador
	 * @return ContaBanco Objeto contendo os dados bancários
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public ContaBanco pesquisar(int matricula)
			throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {

		ContaBanco conta = null;

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Criar o PreparedStatement
		PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM T_XCAVE_CONTA_BANCO WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setInt(1, matricula);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {

			conta = parse(resultado);

		}

		// Valida se encontrou os dados
		if (conta == null)
			throw new ItemNaoEncontradoException();

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return conta;
	}

	// ATUALIZA

	/**
	 * Atualiza os dados bancários
	 * 
	 * @param matricula  Matrícula do colaborador
	 * @param contaBanco Objeto contendo os dados bancários
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws AtualizacaoNaoRealizadaException
	 */
	public void atualizar(int matricula, ContaBanco contaBanco)
			throws ClassNotFoundException, SQLException, AtualizacaoNaoRealizadaException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"UPDATE T_XCAVE_CONTA_BANCO SET NR_AGENCIA = ?, NR_DIGITO_AGENCIA = ?, NR_CONTA = ?, NR_DIGITO_CONTA = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setInt(1, contaBanco.getAgencia());
		stmt.setInt(2, contaBanco.getDigitoAgencia());
		stmt.setInt(3, contaBanco.getConta());
		stmt.setInt(4, contaBanco.getDigitoConta());
		stmt.setInt(5, matricula);

		// Executar a query
		int qtd = stmt.executeUpdate();

		// Valida se atualizou o dado
		if (qtd == 0)
			throw new AtualizacaoNaoRealizadaException();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	/**
	 * Atualiza os dados da agência do banco
	 * 
	 * @param matricula     Matrícula do colaborador
	 * @param agencia       Número da agência
	 * @param digitoAgencia Dígito do número da agência
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws AtualizacaoNaoRealizadaException
	 */
	public void setAgencia(int matricula, int agencia, int digitoAgencia)
			throws ClassNotFoundException, SQLException, AtualizacaoNaoRealizadaException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"UPDATE T_XCAVE_CONTA_BANCO SET NR_AGENCIA = ?, NR_DIGITO_AGENCIA = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setInt(1, agencia);
		stmt.setInt(2, digitoAgencia);
		stmt.setInt(3, matricula);

		// Executar a query
		int qtd = stmt.executeUpdate();

		// Valida se atualizou o dado
		if (qtd == 0)
			throw new AtualizacaoNaoRealizadaException();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	/**
	 * Atualiza os dados da conta do banco
	 * 
	 * @param matricula   Matrícula do colaborador
	 * @param conta       Número da conta
	 * @param digitoConta Dígito do número da conta
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws AtualizacaoNaoRealizadaException
	 */
	public void setConta(int matricula, int conta, int digitoConta)
			throws ClassNotFoundException, SQLException, AtualizacaoNaoRealizadaException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"UPDATE T_XCAVE_CONTA_BANCO SET NR_CONTA = ?, NR_DIGITO_CONTA = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setInt(1, conta);
		stmt.setInt(2, digitoConta);
		stmt.setInt(3, matricula);

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
	 * Preenche a classe bean dos dados bancários
	 * 
	 * @param resultado ResultSet com a pesquisa realizada
	 * @return ContaBanco Objeto com os dados bancários
	 * @throws SQLException
	 */
	private ContaBanco parse(ResultSet resultado) throws SQLException {

		ContaBanco conta = new ContaBanco();

		conta.setAgencia(resultado.getInt("NR_AGENCIA"));
		conta.setDigitoAgencia(resultado.getInt("NR_DIGITO_AGENCIA"));
		conta.setConta(resultado.getInt("NR_CONTA"));
		conta.setDigitoConta(resultado.getInt("NR_DIGITO_CONTA"));

		return conta;

	}

}// Classe
