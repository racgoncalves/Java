package br.com.fiap.tds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.tds.factory.ConnectionFactory;

/**
 * Classe respons�vel por acessar o banco de dados e realizar as opera��es de
 * login nos sistemas xcave
 * 
 * @author Rodrigo Chiarelli
 * @version 1.0
 */
public class LoginDao {

	/**
	 * Checa o login do colaborador para o sistema xcave de cadastro de
	 * colaboradores
	 * 
	 * @param email E-mail do colaborador
	 * @param senha Senha (matr�cula) do colaborador
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean colaborador(String email, int senha) throws ClassNotFoundException, SQLException {

		boolean logado = false;

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT CD_MATRICULA FROM T_XCAVE_COLABORADOR WHERE DS_EMAIL = ? AND CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, email);
		stmt.setInt(2, senha);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {
			logado = true;
		}

		// Fechar a conex�o
		stmt.close();
		conexao.close();

		return logado;

	}

	/**
	 * Checa o login funcion�rio do departamento de recursos humanos para o sistema
	 * xcave de recursos humanos
	 * 
	 * @param usuario Usu�rio do funcion�rio do RH
	 * @param senha   Senha do funcion�rio do RH
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean rh(String usuario, String senha) throws ClassNotFoundException, SQLException {

		boolean logado = false;

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("SELECT NM_USUARIO FROM T_XCAVE_LOGIN_RH WHERE NM_USUARIO = ? AND NM_SENHA = ?");

		// Coloca os valores na query
		stmt.setString(1, usuario);
		stmt.setString(2, senha);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {
			logado = true;
		}

		// Fechar a conex�o
		stmt.close();
		conexao.close();

		return logado;

	}

}// Classe
