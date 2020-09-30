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

import br.com.fiap.tds.bean.Colaborador;
import br.com.fiap.tds.bean.Departamento;
import br.com.fiap.tds.exception.AtualizacaoNaoRealizadaException;
import br.com.fiap.tds.exception.ItemNaoEncontradoException;
import br.com.fiap.tds.factory.ConnectionFactory;

/**
 * Classe responsável por acessar o banco de dados e realizar as operações
 * básicas (CRUD) do colaborador
 * 
 * @author Rodrigo Chiarelli
 * @version 1.0
 */
public class ColaboradorDao {

	// CRIA >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Cadastra um colaborador no banco de dados
	 * 
	 * @param colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void cadastrar(Colaborador colaborador) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement("INSERT INTO T_XCAVE_COLABORADOR"
				+ " (CD_MATRICULA, CD_DEPARTAMENTO, NM_APELIDO, DS_EMAIL, ST_CADASTRO)"
				+ " VALUES (SQ_T_XCAVE_MATRICULA.NEXTVAL, ?, ?, ?, ?)");

		// Coloca os valores na query
		stmt.setInt(1, colaborador.getDepartamento().getCodigo());
		stmt.setString(2, colaborador.getApelido());
		stmt.setString(3, colaborador.getEmail());
		stmt.setInt(4, 0);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	// PESQUISA >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Pesquisa um colaborador pela matrícula
	 * 
	 * @param matricula Matrícula do colaborador
	 * @return Colaborador Objeto com os dados principais do colaborador
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
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT * FROM T_XCAVE_COLABORADOR C, T_XCAVE_DEPARTAMENTO D WHERE C.CD_MATRICULA = ? AND D.CD_DEPARTAMENTO = C.CD_DEPARTAMENTO");

		// Coloca os valores na query
		stmt.setInt(1, matricula);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {

			colaborador = parse(resultado);

		}

		// Valida se encontrou o colaborador
		if (colaborador == null)
			throw new ItemNaoEncontradoException();

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return colaborador;
	}

	/**
	 * Recupera um colaborador pelo e-mail
	 * 
	 * @param email E-mail do colaborador
	 * @return Colaborador Objeto com os dados principais do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public Colaborador pesquisar(String email) throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {

		Colaborador colaborador = null;

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT * FROM T_XCAVE_COLABORADOR C, T_XCAVE_DEPARTAMENTO D WHERE C.DS_EMAIL = ? AND D.CD_DEPARTAMENTO = C.CD_DEPARTAMENTO");

		// Coloca os valores na query
		stmt.setString(1, email);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {

			colaborador = parse(resultado);

		}

		// Valida se encontrou o colaborador
		if (colaborador == null)
			throw new ItemNaoEncontradoException();

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
		ResultSet resultado = stmt.executeQuery(
				"SELECT DISTINCT * FROM T_XCAVE_COLABORADOR C, T_XCAVE_DEPARTAMENTO D WHERE C.CD_DEPARTAMENTO = D.CD_DEPARTAMENTO ORDER BY CD_MATRICULA");

		while (resultado.next()) {

			Colaborador colaborador = parse(resultado);

			lista.add(colaborador.toStringLista());
		}

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return lista;
	}

	/**
	 * Recupera um apelido pela matrícula do colaborador
	 * 
	 * @param matricula Matrícula do colaborador
	 * @return String Apelido do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public String getApelido(int matricula) throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {

		String apelido = "";

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("SELECT NM_APELIDO FROM T_XCAVE_COLABORADOR WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setInt(1, matricula);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {
			apelido = resultado.getString(1);
		}

		// Valida se encontrou o apelido
		if (apelido == "")
			throw new ItemNaoEncontradoException();

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return apelido;

	}

	/**
	 * Recupera a matricula de um colaborador pelo e-mail
	 * 
	 * @param email E-mail do colaborador
	 * @return int Matrícula do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public int getMatricula(String email) throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {

		int matricula = 0;

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("SELECT CD_MATRICULA FROM T_XCAVE_COLABORADOR WHERE DS_EMAIL = ?");

		// Coloca os valores na query
		stmt.setString(1, email);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {

			matricula = resultado.getInt("CD_MATRICULA");

		}

		// Valida se encontrou o colaborador
		if (matricula == 0)
			throw new ItemNaoEncontradoException();

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return matricula;

	}

	/**
	 * Recupera um status pelo email do colaborador
	 * 
	 * @param email E-mail do colaborador
	 * @return booelan Status do cadastro do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public boolean getStatus(String email) throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {

		boolean status = false;
		int x = 0;

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("SELECT ST_CADASTRO FROM T_XCAVE_COLABORADOR WHERE DS_EMAIL = ?");

		// Coloca os valores na query
		stmt.setString(1, email);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {
			status = resultado.getBoolean(1);
			x++;
		}

		// Valida se encontrou o status
		if (x == 0)
			throw new ItemNaoEncontradoException();

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return status;

	}

	// ATUALIZA >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Atualiza um colaborador no banco de dados
	 * 
	 * @param colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws AtualizacaoNaoRealizadaException
	 */
	public void atualizar(Colaborador colaborador)
			throws ClassNotFoundException, SQLException, AtualizacaoNaoRealizadaException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"UPDATE T_XCAVE_COLABORADOR SET CD_DEPARTAMENTO = ?, NM_APELIDO = ?, DS_EMAIL = ? WHERE CD_MATRICULA = ?)");

		// Coloca os valores na query
		stmt.setInt(1, colaborador.getDepartamento().getCodigo());
		stmt.setString(2, colaborador.getApelido());
		stmt.setString(3, colaborador.getEmail());
		stmt.setInt(4, colaborador.getMatricula());

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
	 * Atualiza o departamento de um colaborador
	 * 
	 * @param matricula Matrícula do colaborador
	 * @param codigo    Código do departamento
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws AtualizacaoNaoRealizadaException
	 */
	public void setDepartamento(int matricula, int codigo)
			throws ClassNotFoundException, SQLException, AtualizacaoNaoRealizadaException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_COLABORADOR SET CD_DEPARTAMENTO = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setInt(1, codigo);
		stmt.setInt(2, matricula);

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
	 * Atualiza o apelido de um colaborador
	 * 
	 * @param matricula Matrícula do colaborador
	 * @param apelido   Apelido do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws AtualizacaoNaoRealizadaException
	 */
	public void setApelido(int matricula, String apelido)
			throws ClassNotFoundException, SQLException, AtualizacaoNaoRealizadaException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_COLABORADOR SET NM_APELIDO = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, apelido);
		stmt.setInt(2, matricula);

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
	 * Atualiza o email de um colaborador
	 * 
	 * @param matricula Matrícula do colaborador
	 * @param email     E-mail do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws AtualizacaoNaoRealizadaException
	 */
	public void setEmail(int matricula, String email)
			throws ClassNotFoundException, SQLException, AtualizacaoNaoRealizadaException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_COLABORADOR SET DS_EMAIL = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, email);
		stmt.setInt(2, matricula);

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
	 * Atualiza o status do cadastro de um colaborador
	 * 
	 * @param matricula Matrícula do colaborador
	 * @param status    Status do cadastro de um colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws AtualizacaoNaoRealizadaException
	 */
	public void setStatusCadastro(int matricula, int status)
			throws ClassNotFoundException, SQLException, AtualizacaoNaoRealizadaException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_COLABORADOR SET ST_CADASTRO = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setInt(1, status);
		stmt.setInt(2, matricula);

		// Executar a query
		int qtd = stmt.executeUpdate();

		// Valida se atualizou o dado
		if (qtd == 0)
			throw new AtualizacaoNaoRealizadaException();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	// REMOVE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Remove um colaborador
	 * 
	 * @param matricula Matrícula do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public void remover(int matricula) throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement("DELETE FROM T_XCAVE_COLABORADOR WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setInt(1, matricula);

		// Executar a query
		int qtd = stmt.executeUpdate();

		// Valida se removeu alguma linha no banco de dados
		if (qtd == 0)
			throw new ItemNaoEncontradoException();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	// OUTROS >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Preenche a classe bean do colaborador
	 * 
	 * @param resultado ResultSet com a pesquisa realizada
	 * @return Colaborador Objeto com os dados principais do colaborador
	 * @throws SQLException
	 */
	private Colaborador parse(ResultSet resultado) throws SQLException {

		Colaborador colaborador = new Colaborador();
		Departamento depto = new Departamento(resultado.getInt("CD_DEPARTAMENTO"),
				resultado.getString("NM_DEPARTAMENTO"));

		colaborador.setMatricula(resultado.getInt("CD_MATRICULA"));
		colaborador.setDepartamento(depto);
		colaborador.setApelido(resultado.getString("NM_APELIDO"));
		colaborador.setEmail(resultado.getString("DS_EMAIL"));
		colaborador.setCadastrado(resultado.getBoolean("ST_CADASTRO"));

		return colaborador;

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
		FileWriter outputStream = new FileWriter("Colaboradores.txt");
		PrintWriter arquivoEscrita = new PrintWriter(outputStream);

		for (String lista : listar()) {

			arquivoEscrita.println(lista);

		}

		// Fechar os recursos
		arquivoEscrita.close();
		outputStream.close();
	}

}// Classe
