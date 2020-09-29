package br.com.fiap.tds.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.tds.bean.DadosDependente;
import br.com.fiap.tds.bean.Dependente;
import br.com.fiap.tds.exception.ItemCadastradoException;
import br.com.fiap.tds.exception.OperacaoInvalidaException;
import br.com.fiap.tds.exception.ItemNaoEncontradoException;
import br.com.fiap.tds.factory.ConnectionFactory;

/**
 * Classe responsável por acessar o banco de dados e realizar as operações
 * básicas (CRUD) de um dependente
 * 
 * @author Rodrigo Chiarelli
 * @version 1.0
 */
public class DependenteDao {

	// CRIA >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Cadastra o dependente
	 * 
	 * @param matricula  Matrícula do colaborador
	 * @param Dependente Objeto contendo um dependente
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws OperacaoInvalidaException
	 * @throws ItemCadastradoException
	 * @throws ItemNaoEncontradoException
	 */
	public void cadastrar(int matricula, Dependente dependente) throws ClassNotFoundException, SQLException,
			OperacaoInvalidaException, ItemCadastradoException, ItemNaoEncontradoException {

		if (dependente.getTipo().equals("CÔNJUGE")) {
			if (!conjugeValido(matricula))
				throw new OperacaoInvalidaException("\nO estado civil deve ser 'CASADO'!");

			if (conjugeCadastrado(matricula))
				throw new ItemCadastradoException();
		}

		else if (dependente.getTipo().equals("FILHO")) {
			if (!filhoPermitido(matricula))
				throw new OperacaoInvalidaException(
						"\nA quantidade de dependentes deste tipo não pode ser superior à quantidade de filhos!");
		}

		else if (genitorCadastrado(matricula, dependente.getDadosDependente().getSexo()))
			throw new ItemCadastradoException();

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"INSERT INTO T_XCAVE_DEPENDENTE (CD_MATRICULA, CD_DEPENDENTE, TP_DEPENDENTE, NM_DEPENDENTE, "
						+ "NR_CPF, DS_SEXO, DT_NASCIMENTO, NM_DOCUMENTO, NM_ARQUIVO, BT_ARQUIVO) "
						+ "VALUES (?,SQ_T_XCAVE_DEPENDENTE.NEXTVAL,?,?,?,?,?,?,?,?)");

		// Coloca os valores na query
		stmt.setInt(1, matricula);
		stmt.setString(2, dependente.getTipo());
		stmt.setString(3, dependente.getDadosDependente().getNome());
		stmt.setString(4, dependente.getDadosDependente().getCpf());
		stmt.setString(5, dependente.getDadosDependente().getSexo());
		stmt.setString(6, dependente.getDadosDependente().getDataNascimento());
		stmt.setString(7, dependente.getNomeDocumento());
		stmt.setString(8, dependente.getNomeArquivo());
		stmt.setBytes(9, dependente.getArquivo());

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	// PESQUISA >>>>>>>>>>>>>>>>>>>>>>>>>>

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
		PreparedStatement stmt = conexao
				.prepareStatement("SELECT CD_DEPENDENTE, TP_DEPENDENTE, NM_DEPENDENTE, NR_CPF, DS_SEXO, DT_NASCIMENTO, "
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
	 * Cria um .zip com os documentos de todos os dependentes do colaborador
	 * 
	 * @param matricula Matrícula do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 * @throws IOException
	 */
	public void baixarZip(int matricula)
			throws ClassNotFoundException, SQLException, ItemNaoEncontradoException, IOException {

		DocumentoDao docDao = new DocumentoDao();
		List<String> listaNomes = new ArrayList<String>();
		List<byte[]> listaArquivos = new ArrayList<byte[]>();

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("SELECT NM_ARQUIVO, BT_ARQUIVO FROM T_XCAVE_DEPENDENTE WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setInt(1, matricula);

		// Obter o resultado da pesquisa -> ResultSet
		ResultSet resultado = stmt.executeQuery();

		while (resultado.next()) {
			listaNomes.add(resultado.getString("NM_ARQUIVO"));
			listaArquivos.add(resultado.getBytes("BT_ARQUIVO"));
		}

		if (listaNomes.size() == 0 || listaArquivos.size() == 0)
			throw new ItemNaoEncontradoException();

		// Fechar a conexão
		stmt.close();
		conexao.close();

		docDao.exportarZip("dependentes.zip", listaNomes, listaArquivos);

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
			throw new ItemNaoEncontradoException();

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
	public boolean conjugeCadastrado(int matricula) throws ClassNotFoundException, SQLException {

		boolean x = false;

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// conjugeCadastrado e conjugeInvalido num select apenas

//		// Cria a query para executar no banco
//		PreparedStatement stmt = conexao.prepareStatement(
//				"SELECT CD_DEPENDENTE FROM T_XCAVE_DEPENDENTE DP, T_XCAVE_DADOS DD WHERE DP.CD_MATRICULA = ? AND DP.TP_DEPENDENTE = 'CÔNJUGE' AND DD.CD_MATRICULA = ? AND DD.DS_EST_CIVIL <> ?");
//
//		// Coloca os valores na query
//		stmt.setInt(1, matricula);
//		stmt.setInt(2, matricula);
//		stmt.setString(3, "CASADO");

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
	 * @param sexo      Sexo do genitor
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public boolean conjugeValido(int matricula)
			throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {

		boolean x = false;
		int aux = 0;

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("SELECT DS_EST_CIVIL FROM T_XCAVE_DADOS WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setInt(1, matricula);

		// Obter o resultado da pesquisa -> ResultSet
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {
			aux++;
			if (resultado.getString(1).equals("CASADO"))
				x = true;
		}

		// Valida se encontrou o dado
		if (aux == 0)
			throw new ItemNaoEncontradoException();

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return x;

	}

	/**
	 * Verifica se todos os filhos do colaborador já foram cadastrados como
	 * dependentes
	 * 
	 * @param matricula Matrícula do colaborador
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public boolean filhoPermitido(int matricula)
			throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {

		boolean x = false;

		int qtFilhos = 0;
		int filhosDep = 0;
		int aux = 0;

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

//		// Tudo num único SELECT

//		// Cria a query para executar no banco
//		PreparedStatement stmt = conexao.prepareStatement(
//				"SELECT * FROM T_XCAVE_DEPENDENTE DP, T_XCAVE_DADOS DD WHERE DP.CD_MATRICULA = ? AND DP.TP_DEPENDENTE = 'FILHO' AND DD.CD_MATRICULA = ? AND DD.QT_FILHOS = (SELECT COUNT(IM_CERT_NASCIMENTO) FROM T_XCAVE_DEPENDENTE WHERE CD_MATRICULA = ?)");
//
//		// Coloca os valores na query
//		stmt.setInt(1, matricula);
//		stmt.setInt(2, matricula);
//		stmt.setInt(3, matricula);

		// Select Separados

		// Pega o nº de filhos

		// Cria a query para executar no banco
		PreparedStatement stmtA = conexao
				.prepareStatement("SELECT QT_FILHOS FROM T_XCAVE_DADOS WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmtA.setInt(1, matricula);

		// Obter o resultado da pesquisa -> ResultSet
		ResultSet resultado = stmtA.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {
			qtFilhos = resultado.getInt(1);
			aux++;
		}

		// Valida se encontrou o dado
		if (aux == 0)
			throw new ItemNaoEncontradoException();

		// Conta o nº de filhos cadastrados como dependente

		// Cria a query para executar no banco
		PreparedStatement stmtB = conexao.prepareStatement(
				"SELECT COUNT(TP_DEPENDENTE) FROM T_XCAVE_DEPENDENTE WHERE CD_MATRICULA = ? AND TP_DEPENDENTE = 'FILHO'");

		// Coloca os valores na query
		stmtB.setInt(1, matricula);

		// Obter o resultado da pesquisa -> ResultSet
		resultado = stmtB.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {
			filhosDep = resultado.getInt(1);
		}

		// Fechar a conexão
		stmtA.close();
		stmtB.close();
		conexao.close();

		// Realiza a comparação
		if (filhosDep < qtFilhos)
			x = true;

		return x;

	}

	/**
	 * Verifica se o colaborador já possui um genitor do mesmo sexo cadastrado
	 * 
	 * @param matricula Matrícula do colaborador
	 * @param sexo      Sexo do genitor
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean genitorCadastrado(int matricula, String sexo) throws ClassNotFoundException, SQLException {

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

	// REMOVE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

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
			throw new ItemNaoEncontradoException();

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

		Dependente dependente = new Dependente();
		DadosDependente dadosDependente = new DadosDependente();

		dadosDependente.setNome(resultado.getString("NM_DEPENDENTE"));
		dadosDependente.setCpf(resultado.getString("NR_CPF"));
		dadosDependente.setSexo(resultado.getString("DS_SEXO"));
		dadosDependente.setDataNascimento(dadosDependente.arrumarData(resultado.getString("DT_NASCIMENTO")));

		dependente.setCodigoDependente(resultado.getInt("CD_DEPENDENTE"));
		dependente.setTipo(resultado.getString("TP_DEPENDENTE"));
		dependente.setDadosDependente(dadosDependente);
		dependente.setNomeDocumento(resultado.getString("NM_DOCUMENTO"));

		return dependente;
	}

}// Classe
