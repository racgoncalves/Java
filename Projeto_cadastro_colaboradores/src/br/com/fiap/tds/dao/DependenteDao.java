package br.com.fiap.tds.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.tds.bean.Dependente;
import br.com.fiap.tds.bean.Documento;
import br.com.fiap.tds.bean.Login;
import br.com.fiap.tds.exception.DadoInvalidoException;
import br.com.fiap.tds.exception.ItemCadastradoException;
import br.com.fiap.tds.exception.ItemNaoEncontradoException;
import br.com.fiap.tds.factory.ConnectionFactory;

/**
 * Classe responsável por acessar o banco de dados e realizar as operações
 * básicas (CRUD) de um dependente
 * 
 * @author Rodrigo Chiarelli
 * @version 4.0
 */
public class DependenteDao {

	// CADASTRAR >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Cadastra o dependente
	 * 
	 * @param Dependente Objeto contendo um dependente
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws DadoInvalidoException
	 * @throws ItemCadastradoException
	 * @throws ItemNaoEncontradoException
	 */
	public void cadastrar(Dependente dep) throws ClassNotFoundException, SQLException, DadoInvalidoException,
			ItemCadastradoException, ItemNaoEncontradoException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"INSERT INTO T_XCAVE_DEPENDENTE (CD_MATRICULA, CD_DEPENDENTE, TP_DEPENDENTE, NM_DEPENDENTE, "
						+ "NR_CPF, DS_SEXO, DT_NASCIMENTO, NM_DOCUMENTO, NM_ARQUIVO, BT_ARQUIVO) "
						+ "VALUES (?,SQ_XCAVE_DEPENDENTE.NEXTVAL,?,?,?,?,?,?,?,?)");

		// Coloca os valores na query
		stmt.setInt(1, dep.getLogin().getMatricula());
		stmt.setString(2, dep.getTipo());
		stmt.setString(3, dep.getNome());
		stmt.setString(4, dep.getCpf());
		stmt.setString(5, dep.getSexo());
		stmt.setString(6, dep.getDataNascimento());
		stmt.setString(7, dep.getDocumento().getNome());
		stmt.setString(8, dep.getDocumento().getNomeArquivo());
		stmt.setBytes(9, dep.getDocumento().getArquivo());

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	// PESQUISAR >>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Recupera os dados de todos os dependentes do colaborador
	 * 
	 * @param matricula Matrícula do colaborador
	 * @return List<String> Lista com todos os dependentes do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<String> listar(int matricula) throws ClassNotFoundException, SQLException {

		List<String> listaDependentes = new ArrayList<String>();

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT CD_MATRICULA, CD_DEPENDENTE, TP_DEPENDENTE, NM_DEPENDENTE, NR_CPF, DS_SEXO, DT_NASCIMENTO, "
						+ "NM_DOCUMENTO FROM T_XCAVE_DEPENDENTE WHERE CD_MATRICULA = ? ORDER BY CD_DEPENDENTE");

		// Coloca os valores na query
		stmt.setInt(1, matricula);

		// Obter o resultado da pesquisa -> ResultSet
		ResultSet resultado = stmt.executeQuery();

		while (resultado.next()) {

			listaDependentes.add(parse(resultado).toString());

		}

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return listaDependentes;
	}

	/**
	 * Recupera o documento de um dependente
	 * 
	 * @param matricula Matrícula do colaborador
	 * @param codigo    Código do dependente
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 * @throws IOException
	 */
	public void baixarArquivo(int matricula, int codigo)
			throws ClassNotFoundException, SQLException, ItemNaoEncontradoException, IOException {

		DocumentoDao docDao = new DocumentoDao();
		int aux = 0;

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT NM_ARQUIVO, BT_ARQUIVO FROM T_XCAVE_DEPENDENTE WHERE CD_MATRICULA = ? AND CD_DEPENDENTE = ?");

		// Coloca os valores na query
		stmt.setInt(1, matricula);
		stmt.setInt(2, codigo);

		// Obter o resultado da pesquisa -> ResultSet
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {
			docDao.exportarArquivo(resultado.getString("NM_ARQUIVO"), resultado.getBytes("BT_ARQUIVO"));
			aux++;
		}

		if (aux == 0)
			throw new ItemNaoEncontradoException("\nO documento não foi encontrado!");

		// Fechar a conexão
		stmt.close();
		conexao.close();
	}

	/**
	 * Verifica se o colaborador já possui um cônjuge cadastrado
	 * 
	 * @param matricula Matrícula do colaborador
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean conjugeIsCadastrado(int matricula) throws ClassNotFoundException, SQLException {

		boolean x = false;

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Select separado

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT CD_DEPENDENTE FROM T_XCAVE_DEPENDENTE WHERE CD_MATRICULA = ? AND TP_DEPENDENTE = 'CÔNJUGE'");

		// Coloca os valores na query
		stmt.setInt(1, matricula);

		// Obter o resultado da pesquisa -> ResultSet
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

	/**
	 * Verifica se o colaborador já possui um genitor do mesmo sexo cadastrado
	 * 
	 * @param matricula Matrícula do colaborador
	 * @param sexo      Sexo do dependente
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean genitorIsCadastrado(int matricula, String sexo) throws ClassNotFoundException, SQLException {

		boolean x = false;

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT * FROM T_XCAVE_DEPENDENTE WHERE CD_MATRICULA = ? AND TP_DEPENDENTE = 'GENITOR' AND DS_SEXO = ?");

		// Coloca os valores na query
		stmt.setInt(1, matricula);
		stmt.setString(2, sexo);

		// Obter o resultado da pesquisa -> ResultSet
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

	/**
	 * Verifica a quantidade de filhos dependentes
	 * 
	 * @param matricula Matrícula do colaborador
	 * @return filhosDep Quantidade de filhos dependentes
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public int getFilhosDependente(int matricula)
			throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {

		int filhosDep = 0;

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT COUNT(TP_DEPENDENTE) FROM T_XCAVE_DEPENDENTE WHERE CD_MATRICULA = ? AND TP_DEPENDENTE = 'FILHO'");

		// Coloca os valores na query
		stmt.setInt(1, matricula);

		// Obter o resultado da pesquisa -> ResultSet
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {
			filhosDep = resultado.getInt(1);
		}

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return filhosDep;

	}

	// REMOVER >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Remove um dependente
	 * 
	 * @param matricula Matrícula do colaborador
	 * @param codigo    Código do dependente
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public void remover(int matricula, int codigo)
			throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("DELETE FROM T_XCAVE_DEPENDENTE WHERE CD_MATRICULA = ? AND CD_DEPENDENTE = ?");

		// Coloca os valores na query
		stmt.setInt(1, matricula);
		stmt.setInt(2, codigo);

		// Executar a query
		int qtd = stmt.executeUpdate();

		// Valida se removeu alguma linha no banco de dados
		if (qtd == 0)
			throw new ItemNaoEncontradoException("\nO dependente não foi encontrado!");

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	// OUTROS >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Preenche a classe bean dos dados gerais
	 * 
	 * @param resultado ResultSet com a pesquisa realizada
	 * @return dadosDependente Objeto com os dados do dependente
	 * @throws SQLException
	 */
	private Dependente parse(ResultSet resultado) throws SQLException {

		Dependente dep = new Dependente();
		Login login = new Login();
		Documento doc = new Documento();

		login.setMatricula(resultado.getInt("CD_MATRICULA"));

		doc.setNome(resultado.getString("NM_DOCUMENTO"));

		dep.setLogin(login);
		dep.setCodigo(resultado.getInt("CD_DEPENDENTE"));
		dep.setTipo(resultado.getString("TP_DEPENDENTE"));
		dep.setNome(resultado.getString("NM_DEPENDENTE"));
		dep.setCpf(resultado.getString("NR_CPF"));
		dep.setSexo(resultado.getString("DS_SEXO"));
		dep.setDataNascimento(dep.getDataBD(resultado.getString("DT_NASCIMENTO")));
		dep.setDocumento(doc);

		return dep;
	}

}// Classe
