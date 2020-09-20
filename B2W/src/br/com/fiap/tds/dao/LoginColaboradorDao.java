package br.com.fiap.tds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.tds.factory.ConnectionFactory;

public class LoginColaboradorDao {

	public void setLoginColaborador(String email) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement("INSERT INTO T_XCAVE_LOGIN"
				+ " (CD_MATRICULA, DS_EMAIL, ST_CADASTRO)" + " VALUES (SQ_T_XCAVE_MATRICULA.NEXTVAL, ?, ?)");

		// Coloca os valores na query
		stmt.setString(1, email);
		stmt.setInt(2, 0);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public String getMatricula(String email) throws ClassNotFoundException, SQLException {

		String matricula = "";

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement("SELECT CD_MATRICULA FROM T_XCAVE_LOGIN WHERE DS_EMAIL = ?");

		// Coloca os valores na query
		stmt.setString(1, email);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {
			matricula = resultado.getString("CD_MATRICULA");
		}

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return matricula;

	}

	public String getEmail(String matricula) throws ClassNotFoundException, SQLException {

		String email = "";

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement("SELECT DS_EMAIL FROM T_XCAVE_LOGIN WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, matricula);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {
			email = resultado.getString("DS_EMAIL");
		}

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return email;

	}

	public void setEmail(String matricula, String email) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_LOGIN SET DS_EMAIL = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, email);
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public int getStatus(String email) throws ClassNotFoundException, SQLException {

		int status = 0;

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement("SELECT ST_CADASTRO FROM T_XCAVE_LOGIN WHERE DS_EMAIL = ?");

		// Coloca os valores na query
		stmt.setString(1, email);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {
			status = resultado.getInt("ST_CADASTRO");
		}

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return status;

	}

	public void setStatus(String matricula, int status) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_LOGIN SET ST_CADASTRO = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setInt(1, status);
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

}// Classe
