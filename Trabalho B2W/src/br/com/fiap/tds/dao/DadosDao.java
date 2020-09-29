package br.com.fiap.tds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.tds.bean.Dados;
import br.com.fiap.tds.exception.AtualizacaoNaoRealizadaException;
import br.com.fiap.tds.exception.ItemNaoEncontradoException;
import br.com.fiap.tds.factory.ConnectionFactory;

/**
 * Classe respons�vel por acessar o banco de dados Realiza as opera��es b�sicas
 * (CRUD) dos dados gerais do colaborador
 * 
 * @author Rodrigo Chiarelli
 * @version 1.0
 */
public class DadosDao {

	// CRIA >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Cadastra os dados gerais de um colaborador no banco de dados
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @param dados     Objeto contendo os dados gerais do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void cadastrar(int matricula, Dados dados) throws ClassNotFoundException, SQLException {

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("INSERT INTO T_XCAVE_DADOS (CD_MATRICULA, NM_COLABORADOR, NR_CPF, NR_PIS, DS_SEXO,"
						+ " NM_NACIONALIDADE, NM_NATURALIDADE, DT_NASCIMENTO, DS_EST_CIVIL, QT_FILHOS, NM_ETNIA,"
						+ " TP_CAMISETA, NM_ORIENT_SEXUAL, NM_RELIGIAO)" + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		// Coloca os valores na query
		stmt.setInt(1, matricula);
		stmt.setString(2, dados.getNome());
		stmt.setString(3, dados.getCpf());
		stmt.setString(4, dados.getPis());
		stmt.setString(5, dados.getSexo());
		stmt.setString(6, dados.getNacionalidade());
		stmt.setString(7, dados.getNaturalidade());
		stmt.setString(8, dados.getDataNascimento());
		stmt.setString(9, dados.getEstadoCivil());
		stmt.setInt(10, dados.getNumeroFilhos());
		stmt.setString(11, dados.getEtnia());
		stmt.setString(12, dados.getTamanhoCamiseta());
		stmt.setString(13, dados.getOrientacao());
		stmt.setString(14, dados.getReligiao());

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conex�o
		stmt.close();
		conexao.close();

	}

	// PESQUISA >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Recupera os dados gerais do colaborador
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @return Dados Objeto contendo os dados
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public Dados pesquisar(int matricula) throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {

		Dados dados = null;

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Criar o PreparedStatement
		PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM T_XCAVE_DADOS WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setInt(1, matricula);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {

			dados = parse(resultado);

		}

		// Valida se encontrou os dados
		if (dados == null)
			throw new ItemNaoEncontradoException();

		// Fechar a conex�o
		stmt.close();
		conexao.close();

		return dados;
	}

	/**
	 * Verifica se os dados j� foram cadastrados
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean isCadastrado(int matricula) throws ClassNotFoundException, SQLException {

		boolean x = false;

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Criar o PreparedStatement
		PreparedStatement stmt = conexao
				.prepareStatement("SELECT CD_MATRICULA FROM T_XCAVE_DADOS WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setInt(1, matricula);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {

			x = true;

		}

		// Fechar a conex�o
		stmt.close();
		conexao.close();

		return x;
	}

	/**
	 * Recupera o n�mero de filhos
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @return int N�mero de filhos
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public int getFilhos(int matricula) throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {

		int i = 0;
		int aux = 0;

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Criar o PreparedStatement
		PreparedStatement stmt = conexao.prepareStatement("SELECT QT_FILHOS FROM T_XCAVE_DADOS WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setInt(1, matricula);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {

			i = resultado.getInt(1);
			aux++;

		}

		// Valida se encontrou o dado
		if (aux == 0)
			throw new ItemNaoEncontradoException();

		// Fechar a conex�o
		stmt.close();
		conexao.close();

		return i;
	}

	// ATUALIZA >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Atualiza os dados gerais do colaborador
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @param dados     Objeto contendo os dados gerais
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws AtualizacaoNaoRealizadaException
	 */
	public void atualizar(int matricula, Dados dados)
			throws ClassNotFoundException, SQLException, AtualizacaoNaoRealizadaException {

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"UPDATE T_XCAVE_DADOS SET CD_MATRICULA = ?, NM_COLABORADOR = ?, NR_CPF = ?, NR_PIS = ?, DS_SEXO = ?, "
						+ "NM_NACIONALIDADE = ?, NM_NATURALIDADE = ?, DT_NASCIMENTO = ?, DS_EST_CIVIL = ?, QT_FILHOS = ?, "
						+ "NM_ETNIA = ?, TP_CAMISETA = ?, NM_ORIENT_SEXUAL = ?, NM_RELIGIAO = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, dados.getNome());
		stmt.setString(2, dados.getCpf());
		stmt.setString(3, dados.getPis());
		stmt.setString(4, dados.getSexo());
		stmt.setString(5, dados.getNacionalidade());
		stmt.setString(6, dados.getNaturalidade());
		stmt.setString(7, dados.getDataNascimento());
		stmt.setString(8, dados.getEstadoCivil());
		stmt.setInt(9, dados.getNumeroFilhos());
		stmt.setString(10, dados.getEtnia());
		stmt.setString(11, dados.getTamanhoCamiseta());
		stmt.setString(12, dados.getOrientacao());
		stmt.setString(13, dados.getReligiao());
		stmt.setInt(14, matricula);

		// Executar a query
		int qtd = stmt.executeUpdate();

		// Valida se atualizou o dado
		if (qtd == 0)
			throw new AtualizacaoNaoRealizadaException();

		// Fechar a conex�o
		stmt.close();
		conexao.close();

	}

	/**
	 * Atualiza o nome
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @param nome      Nome do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws AtualizacaoNaoRealizadaException
	 */
	public void setNome(int matricula, String nome)
			throws ClassNotFoundException, SQLException, AtualizacaoNaoRealizadaException {

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DADOS SET NM_COLABORADOR = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, nome);
		stmt.setInt(2, matricula);

		// Executar a query
		int qtd = stmt.executeUpdate();

		// Valida se atualizou o dado
		if (qtd == 0)
			throw new AtualizacaoNaoRealizadaException();

		// Fechar a conex�o
		stmt.close();
		conexao.close();

	}

	/**
	 * Atualiza o cpf
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @param cpf       CPF do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws AtualizacaoNaoRealizadaException
	 */
	public void setCpf(int matricula, String cpf)
			throws ClassNotFoundException, SQLException, AtualizacaoNaoRealizadaException {

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement("UPDATE T_XCAVE_DADOS SET NR_CPF = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, cpf);
		stmt.setInt(2, matricula);

		// Executar a query
		int qtd = stmt.executeUpdate();

		// Valida se atualizou o dado
		if (qtd == 0)
			throw new AtualizacaoNaoRealizadaException();

		// Fechar a conex�o
		stmt.close();
		conexao.close();

	}

	/**
	 * Atualiza o pis
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @param pis       PIS do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws AtualizacaoNaoRealizadaException
	 */
	public void setPis(int matricula, String pis)
			throws ClassNotFoundException, SQLException, AtualizacaoNaoRealizadaException {

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement("UPDATE T_XCAVE_DADOS SET NR_PIS = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, pis);
		stmt.setInt(2, matricula);

		// Executar a query
		int qtd = stmt.executeUpdate();

		// Valida se atualizou o dado
		if (qtd == 0)
			throw new AtualizacaoNaoRealizadaException();

		// Fechar a conex�o
		stmt.close();
		conexao.close();

	}

	/**
	 * Atualiza o sexo
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @param sexo      Sexo do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws AtualizacaoNaoRealizadaException
	 */
	public void setSexo(int matricula, String sexo)
			throws ClassNotFoundException, SQLException, AtualizacaoNaoRealizadaException {

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DADOS SET DS_SEXO = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, sexo);
		stmt.setInt(2, matricula);

		// Executar a query
		int qtd = stmt.executeUpdate();

		// Valida se atualizou o dado
		if (qtd == 0)
			throw new AtualizacaoNaoRealizadaException();

		// Fechar a conex�o
		stmt.close();
		conexao.close();

	}

	/**
	 * Atualiza a nacionalidade
	 * 
	 * @param matricula     Matr�cula do colaborador
	 * @param nacionalidade Nacionalidade do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws AtualizacaoNaoRealizadaException
	 */
	public void setNacionalidade(int matricula, String nacionalidade)
			throws ClassNotFoundException, SQLException, AtualizacaoNaoRealizadaException {

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DADOS SET NM_NACIONALIDADE = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, nacionalidade);
		stmt.setInt(2, matricula);

		// Executar a query
		int qtd = stmt.executeUpdate();

		// Valida se atualizou o dado
		if (qtd == 0)
			throw new AtualizacaoNaoRealizadaException();

		// Fechar a conex�o
		stmt.close();
		conexao.close();

	}

	/**
	 * Atualiza a naturalidade
	 * 
	 * @param matricula    Matr�cula do colaborador
	 * @param naturalidade Naturalidade do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws AtualizacaoNaoRealizadaException
	 */
	public void setNaturalidade(int matricula, String naturalidade)
			throws ClassNotFoundException, SQLException, AtualizacaoNaoRealizadaException {

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DADOS SET NM_NATURALIDADE = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, naturalidade);
		stmt.setInt(2, matricula);

		// Executar a query
		int qtd = stmt.executeUpdate();

		// Valida se atualizou o dado
		if (qtd == 0)
			throw new AtualizacaoNaoRealizadaException();

		// Fechar a conex�o
		stmt.close();
		conexao.close();

	}

	/**
	 * Atualiza a data de nascimento
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @param data      Data de nascimento do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws AtualizacaoNaoRealizadaException
	 */
	public void setDataNascimento(int matricula, String data)
			throws ClassNotFoundException, SQLException, AtualizacaoNaoRealizadaException {

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DADOS SET DT_NASCIMENTO = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, data);
		stmt.setInt(2, matricula);

		// Executar a query
		int qtd = stmt.executeUpdate();

		// Valida se atualizou o dado
		if (qtd == 0)
			throw new AtualizacaoNaoRealizadaException();

		// Fechar a conex�o
		stmt.close();
		conexao.close();

	}

	/**
	 * Atualiza o estado civil
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @param civil     Estado civil do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws AtualizacaoNaoRealizadaException
	 */
	public void setEstadoCivil(int matricula, String civil)
			throws ClassNotFoundException, SQLException, AtualizacaoNaoRealizadaException {

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DADOS SET DS_EST_CIVIL = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, civil);
		stmt.setInt(2, matricula);

		// Executar a query
		int qtd = stmt.executeUpdate();

		// Valida se atualizou o dado
		if (qtd == 0)
			throw new AtualizacaoNaoRealizadaException();

		// Fechar a conex�o
		stmt.close();
		conexao.close();

	}

	/**
	 * Atualiza o n� de filhos
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @param filhos    Quantidade de filhos do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws AtualizacaoNaoRealizadaException
	 */
	public void setFilhos(int matricula, int filhos)
			throws ClassNotFoundException, SQLException, AtualizacaoNaoRealizadaException {

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DADOS SET QT_FILHOS = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setInt(1, filhos);
		stmt.setInt(2, matricula);

		// Executar a query
		int qtd = stmt.executeUpdate();

		// Valida se atualizou o dado
		if (qtd == 0)
			throw new AtualizacaoNaoRealizadaException();

		// Fechar a conex�o
		stmt.close();
		conexao.close();

	}

	/**
	 * Atualiza a etnia
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @param etnia     Etnia do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws AtualizacaoNaoRealizadaException
	 */
	public void setEtnia(int matricula, String etnia)
			throws ClassNotFoundException, SQLException, AtualizacaoNaoRealizadaException {

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DADOS SET NM_ETNIA = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, etnia);
		stmt.setInt(2, matricula);

		// Executar a query
		int qtd = stmt.executeUpdate();

		// Valida se atualizou o dado
		if (qtd == 0)
			throw new AtualizacaoNaoRealizadaException();

		// Fechar a conex�o
		stmt.close();
		conexao.close();

	}

	/**
	 * Atualiza o tamanho da camiseta
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @param camiseta  Tamanho da camiseta do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws AtualizacaoNaoRealizadaException
	 */
	public void setTamanhoCamiseta(int matricula, String camiseta)
			throws ClassNotFoundException, SQLException, AtualizacaoNaoRealizadaException {

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DADOS SET TP_CAMISETA = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, camiseta);
		stmt.setInt(2, matricula);

		// Executar a query
		int qtd = stmt.executeUpdate();

		// Valida se atualizou o dado
		if (qtd == 0)
			throw new AtualizacaoNaoRealizadaException();

		// Fechar a conex�o
		stmt.close();
		conexao.close();

	}

	/**
	 * Atualiza a orienta��o sexual
	 * 
	 * @param matricula  Matr�cula do colaborador
	 * @param orientacao Orienta��o sexual do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws AtualizacaoNaoRealizadaException
	 */
	public void setOrientacao(int matricula, String orientacao)
			throws ClassNotFoundException, SQLException, AtualizacaoNaoRealizadaException {

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DADOS SET NM_ORIENT_SEXUAL = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, orientacao);
		stmt.setInt(2, matricula);

		// Executar a query
		int qtd = stmt.executeUpdate();

		// Valida se atualizou o dado
		if (qtd == 0)
			throw new AtualizacaoNaoRealizadaException();

		// Fechar a conex�o
		stmt.close();
		conexao.close();

	}

	/**
	 * Atualiza a religi�o
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @param religiao  Religi�o do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws AtualizacaoNaoRealizadaException
	 */
	public void setReligiao(int matricula, String religiao)
			throws ClassNotFoundException, SQLException, AtualizacaoNaoRealizadaException {

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DADOS SET NM_RELIGIAO = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, religiao);
		stmt.setInt(2, matricula);

		// Executar a query
		int qtd = stmt.executeUpdate();

		// Valida se atualizou o dado
		if (qtd == 0)
			throw new AtualizacaoNaoRealizadaException();

		// Fechar a conex�o
		stmt.close();
		conexao.close();

	}

	// OUTROS >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Preenche a classe bean dos dados gerais
	 * 
	 * @param resultado ResultSet com a pesquisa realizada
	 * @return dados Objeto com os dados gerais
	 * @throws SQLException
	 */
	private Dados parse(ResultSet resultado) throws SQLException {

		Dados dados = new Dados();

		dados.setNome(resultado.getString("NM_COLABORADOR"));
		dados.setCpf(resultado.getString("NR_CPF"));
		dados.setPis(resultado.getString("NR_PIS"));
		dados.setSexo(resultado.getString("DS_SEXO"));
		dados.setNacionalidade(resultado.getString("NM_NACIONALIDADE"));
		dados.setNaturalidade(resultado.getString("NM_NATURALIDADE"));
		dados.setDataNascimento(dados.arrumarData(resultado.getString("DT_NASCIMENTO")));
		dados.setEstadoCivil(resultado.getString("DS_EST_CIVIL"));
		dados.setNumeroFilhos(resultado.getInt("QT_FILHOS"));
		dados.setEtnia(resultado.getString("NM_ETNIA"));
		dados.setTamanhoCamiseta(resultado.getString("TP_CAMISETA"));
		dados.setOrientacao(resultado.getString("NM_ORIENT_SEXUAL"));
		dados.setReligiao(resultado.getString("NM_RELIGIAO"));

		return dados;

	}

}// Classe
