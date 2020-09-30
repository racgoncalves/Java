package br.com.fiap.tds.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection() throws SQLException, ClassNotFoundException {

		// Registrar o driver do banco
		Class.forName("oracle.jdbc.driver.OracleDriver");

		// Obter a conexão com o banco de dados
		Connection conexao = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", "RM85716",
				"300488");

		return conexao;

	}

}
