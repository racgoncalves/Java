package br.com.fiap.tds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.tds.factory.ConnectionFactory;

public class LoginRHDao {

	public boolean isUsuario(String usuario) throws ClassNotFoundException, SQLException {

		boolean user = false;

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("SELECT NM_USUARIO FROM T_XCAVE_LOGIN_RH WHERE NM_USUARIO = ?");
		
		// Coloca os valores na query
		stmt.setString(1, usuario);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {
			user = true;
		}

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return user;

	}

	public boolean isSenha(String senha, String usuario) throws ClassNotFoundException, SQLException {

		boolean pass = false;

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("SELECT NM_SENHA FROM T_XCAVE_LOGIN_RH WHERE NM_SENHA = ? AND NM_USUARIO = ?");

		// Coloca os valores na query
		stmt.setString(1, senha);
		stmt.setString(2, usuario);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {
			pass = true;
		}

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return pass;

	}

}// Classe
