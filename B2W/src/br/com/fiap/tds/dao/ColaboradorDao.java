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
import br.com.fiap.tds.factory.ConnectionFactory;

public class ColaboradorDao {
	
	public Colaborador getColaborador(String matricula) throws ClassNotFoundException, SQLException {

		Colaborador colaborador = null;

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Criar o PreparedStatement
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT * FROM T_XCAVE_COLABORADOR WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, matricula);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {

			colaborador = parse(resultado);

		}

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return colaborador;
	}
	
	private Colaborador parse(ResultSet resultado) throws SQLException {

		Colaborador colaborador = new Colaborador();
		
		colaborador.setMatricula(resultado.getString("CD_MATRICULA"));
		colaborador.setNome(resultado.getString("NM_COLABORADOR"));
		colaborador.setEmail(resultado.getString("DS_EMAIL"));
		colaborador.setSalario(resultado.getDouble("VL_SALARIO"));

		return colaborador;

	}

	public void setColaborador(Colaborador colaborador) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement("INSERT INTO T_XCAVE_COLABORADOR"
				+ " (CD_MATRICULA, NM_COlABORADOR, DS_EMAIL, VL_SALARIO, ST_CADASTRO)" + " VALUES (SQ_T_XCAVE_MATRICULA.NEXTVAL, ?, ?, ?, ?)");

		// Coloca os valores na query
		stmt.setString(1, colaborador.getNome());
		stmt.setString(2, colaborador.getEmail());
		stmt.setDouble(3, colaborador.getSalario());
		stmt.setInt(4, 0);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void deletarColaborador(String matricula) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmtA = conexao.prepareStatement("DELETE FROM T_XCAVE_DEPENDENTE WHERE CD_MATRICULA = ?");
		PreparedStatement stmtB = conexao.prepareStatement("DELETE FROM T_XCAVE_CONTA_BANCO WHERE CD_MATRICULA = ?");
		PreparedStatement stmtC = conexao.prepareStatement("DELETE FROM T_XCAVE_DADOS WHERE CD_MATRICULA = ?");
		PreparedStatement stmtD = conexao.prepareStatement("DELETE FROM T_XCAVE_COLABORADOR WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmtA.setString(1, matricula);
		stmtB.setString(1, matricula);
		stmtC.setString(1, matricula);
		stmtD.setString(1, matricula);

		// Executar a query
		stmtA.executeUpdate();
		stmtB.executeUpdate();
		stmtC.executeUpdate();
		stmtD.executeUpdate();

		// Fechar a conexão
		stmtA.close();
		stmtB.close();
		stmtC.close();
		stmtD.close();
		conexao.close();

	}
	
	public List<String> getColaboradores() throws ClassNotFoundException, SQLException {

		List<String> lista = new ArrayList<String>();

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Criar o objeto para executar um comando SQL
		Statement stmt = conexao.createStatement();

		// Realizar a pesquisa de todos os funcionários
		ResultSet resultado = stmt.executeQuery(
				"SELECT DISTINCT * FROM T_XCAVE_COLABORADOR");

		while (resultado.next()) {

			Colaborador colaborador = parse(resultado);

			lista.add(colaborador.toString());
		}

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return lista;
	}

	public void exportaColaboradores() throws ClassNotFoundException, SQLException, IOException {

		// Escrever o arquivo
		FileWriter outputStream = new FileWriter("Colaboradores.txt");
		PrintWriter arquivoEscrita = new PrintWriter(outputStream);

		for (String lista : getColaboradores()) {

			arquivoEscrita.println(lista);

		}

		// Fechar os recursos
		arquivoEscrita.close();
		outputStream.close();
	}
	

	public String getMatricula(String email) throws ClassNotFoundException, SQLException {

		String matricula = "";

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement("SELECT CD_MATRICULA FROM T_XCAVE_COLABORADOR WHERE DS_EMAIL = ?");

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
	
	public void setEmail(String matricula, String email) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_COLABORADOR SET DS_EMAIL = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, email);
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}
	
	public void setNome(String matricula, String nome) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_COLABORADOR SET NM_COLABORADOR = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, nome);
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
		PreparedStatement stmt = conexao.prepareStatement("SELECT ST_CADASTRO FROM T_XCAVE_COLABORADOR WHERE DS_EMAIL = ?");

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
				.prepareStatement("UPDATE T_XCAVE_COLABORADOR SET ST_CADASTRO = ? WHERE CD_MATRICULA = ?");

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
