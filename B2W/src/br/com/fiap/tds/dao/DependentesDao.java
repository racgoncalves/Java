package br.com.fiap.tds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.fiap.tds.bean.Dependentes;
import br.com.fiap.tds.factory.ConnectionFactory;

public class DependentesDao {

	public Dependentes getDependentes(String matricula) throws ClassNotFoundException, SQLException {

		Dependentes dependente = new Dependentes();
		List<byte[]> listaConjuge = new ArrayList<byte[]>();
		List<byte[]> listaFilho = new ArrayList<byte[]>();
		List<byte[]> listaGenitor = new ArrayList<byte[]>();
		HashMap<String, List<byte[]>> mapaDependentes = new HashMap<String, List<byte[]>>();
		String tipo = "";

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM T_XCAVE_DEPENDENTE WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, matricula);

		// Obter o resultado da pesquisa -> ResultSet
		ResultSet resultado = stmt.executeQuery();

		while (resultado.next()) {

			tipo = resultado.getString("TP_DEPENDENTE");

			if (tipo.equals("CONJUGE")) {
				listaConjuge.add(resultado.getBytes("IM_CERT_CASAMENTO"));
				mapaDependentes.put(tipo, listaConjuge);
			}

			else if (tipo.equals("FILHO")) {
				listaFilho.add(resultado.getBytes("IM_CERT_NASCIMENTO"));
				mapaDependentes.put(tipo, listaFilho);
			}

			else if (tipo.equals("GENITOR")) {
				listaGenitor.add(resultado.getBytes("IM_RG_GENITOR"));
				mapaDependentes.put(tipo, listaGenitor);
			}

		}

		// Fechar a conexão
		stmt.close();
		conexao.close();

		dependente.setMapaDependentes(mapaDependentes);

		return dependente;
	}

	public void setCertidaoCasamento(String matricula, Dependentes dependente)
			throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"INSERT INTO T_XCAVE_DEPENDENTE (CD_MATRICULA, CD_DEPENDENTE, TP_DEPENDENTE, IM_CERT_CASAMENTO) VALUES (?,?,'CONJUGE',?)");

		// Coloca os valores na query
		stmt.setString(1, matricula);
		stmt.setInt(2, geraSequencia(matricula));
		stmt.setBytes(3, dependente.getCertidaoCasamento());

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void delCertidaoCasamento(String matricula) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"DELETE FROM T_XCAVE_DEPENDENTE WHERE CD_MATRICULA = ? AND TP_DEPENDENTE = 'CONJUGE'");

		// Coloca os valores na query
		stmt.setString(1, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void setCertidaoNascimento(String matricula, Dependentes dependente)
			throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"INSERT INTO T_XCAVE_DEPENDENTE (CD_MATRICULA, CD_DEPENDENTE, TP_DEPENDENTE, IM_CERT_NASCIMENTO) VALUES (?,?,'FILHO',?)");

		// Coloca os valores na query
		stmt.setString(1, matricula);
		stmt.setInt(2, geraSequencia(matricula));
		stmt.setBytes(3, dependente.getCertidaoNascimentoFilho());

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void delCertidaoNascimento(String matricula) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("DELETE FROM T_XCAVE_DEPENDENTE WHERE CD_MATRICULA = ? AND TP_DEPENDENTE = 'FILHO'");

		// Coloca os valores na query
		stmt.setString(1, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void setRgGenitor(String matricula, Dependentes dependente) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"INSERT INTO T_XCAVE_DEPENDENTE (CD_MATRICULA, CD_DEPENDENTE, TP_DEPENDENTE, IM_RG_GENITOR) VALUES (?,?,'GENITOR',?)");

		// Coloca os valores na query
		stmt.setString(1, matricula);
		stmt.setInt(2, geraSequencia(matricula));
		stmt.setBytes(3, dependente.getRgGenitor());

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void delRgGenitor(String matricula) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"DELETE FROM T_XCAVE_DEPENDENTE WHERE CD_MATRICULA = ? AND TP_DEPENDENTE = 'GENITOR'");

		// Coloca os valores na query
		stmt.setString(1, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public int geraSequencia(String matricula) throws ClassNotFoundException, SQLException {

		int seq = 0;

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM T_XCAVE_DEPENDENTE WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, matricula);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {
			seq++;
		}

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return seq += 1;
	}

}// Classe
