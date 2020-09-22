package br.com.fiap.tds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.tds.bean.ContaBanco;
import br.com.fiap.tds.factory.ConnectionFactory;

public class ContaBancoDao {
	
	public ContaBanco getContaBanco(String matricula) throws ClassNotFoundException, SQLException {

		ContaBanco conta = null;

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Criar o PreparedStatement
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT * FROM T_XCAVE_CONTA_BANCO WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, matricula);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {

			conta = parse(resultado);

		}

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return conta;
	}
	
	private ContaBanco parse(ResultSet resultado) throws SQLException {

		ContaBanco conta = new ContaBanco();

		conta.setAgencia(resultado.getString("NR_AGENCIA"));
		conta.setDigitoAgencia(resultado.getString("NR_DIGITO_AGENCIA"));
		conta.setConta(resultado.getString("NR_CONTA"));
		conta.setDigitoConta(resultado.getString("NR_DIGITO_CONTA"));

		return conta;

	}
	
	public void setContaBanco(String matricula, ContaBanco contaBanco) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"INSERT INTO T_XCAVE_CONTA_BANCO (CD_MATRICULA, NR_AGENCIA, NR_DIGITO_AGENCIA, NR_CONTA, NR_DIGITO_CONTA) "
						+ "VALUES (?,?,?,?,?)");

		// Coloca os valores na query
		stmt.setString(1, matricula);
		stmt.setString(2, contaBanco.getAgencia());
		stmt.setString(3, contaBanco.getDigitoAgencia());
		stmt.setString(4, contaBanco.getConta());
		stmt.setString(5, contaBanco.getDigitoConta());

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}
	
	public void setAgencia(String matricula, String agencia, String digitoAgencia) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_CONTA_BANCO SET NR_AGENCIA = ?, NR_DIGITO_AGENCIA = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, agencia);
		stmt.setString(2, digitoAgencia);
		stmt.setString(3, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void setConta(String matricula, String conta, String digitoConta) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_CONTA_BANCO SET NR_CONTA = ?, NR_DIGITO_CONTA = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, conta);
		stmt.setString(2, digitoConta);
		stmt.setString(3, matricula);
		
		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

}// Classe
