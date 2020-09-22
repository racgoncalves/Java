package br.com.fiap.tds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.tds.bean.Dados;
import br.com.fiap.tds.factory.ConnectionFactory;

public class DadosDao {

	public void setDados(String matricula, Dados dados) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"INSERT INTO T_XCAVE_DADOS (CD_MATRICULA, NR_CPF, NR_PIS, DS_SEXO,"
						+ " NM_NACIONALIDADE, NM_NATURALIDADE, DT_NASCIMENTO, DS_EST_CIVIL, QT_FILHOS, NM_ETNIA,"
						+ " TP_CAMISA, NM_ORIENT_SEXUAL, NM_RELIGIAO)" + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");

		// Coloca os valores na query
		stmt.setString(1, matricula);
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

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public Dados getDados(String matricula) throws ClassNotFoundException, SQLException {

		Dados dados = null;

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Criar o PreparedStatement
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT * FROM T_XCAVE_DADOS WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, matricula);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {

			dados = parse(resultado);

		}

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return dados;
	}

	private Dados parse(ResultSet resultado) throws SQLException {

		Dados dados = new Dados();

		dados.setCpf(resultado.getString("NR_CPF"));
		dados.setPis(resultado.getString("NR_PIS"));
		dados.setSexo(resultado.getString("DS_SEXO"));
		dados.setNacionalidade(resultado.getString("NM_NACIONALIDADE"));
		dados.setNaturalidade(resultado.getString("NM_NATURALIDADE"));
		dados.setDataNascimento(resultado.getString("DT_NASCIMENTO"));
		dados.setEstadoCivil(resultado.getString("DS_EST_CIVIL"));
		dados.setNumeroFilhos(resultado.getInt("QT_FILHOS"));
		dados.setEtnia(resultado.getString("NM_ETNIA"));
		dados.setTamanhoCamiseta(resultado.getString("TP_CAMISA"));
		dados.setOrientacao(resultado.getString("NM_ORIENT_SEXUAL"));
		dados.setReligiao(resultado.getString("NM_RELIGIAO"));

		return dados;

	}

	public void setCPF(String matricula, String cpf) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DADOS SET NR_CPF = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, cpf);
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void setPIS(String matricula, String pis) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DADOS SET NR_PIS = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, pis);
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void setSexo(String matricula, String sexo) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DADOS SET DS_SEXO = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, sexo);
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void setNacionalidade(String matricula, String nacionalidade) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DADOS SET NM_NACIONALIDADE = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, nacionalidade);
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void setNaturalidade(String matricula, String naturalidade) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DADOS SET NM_NATURALIDADE = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, naturalidade);
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void setData(String matricula, String data) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DADOS SET DT_NASCIMENTO = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, data);
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void setEstadoCivil(String matricula, String civil) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DADOS SET DS_EST_CIVIL = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, civil);
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void setFilhos(String matricula, int filhos) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DADOS SET QT_FILHOS = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setInt(1, filhos);
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	
	public void setEtnia(String matricula, String etnia) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DADOS SET NM_ETNIA = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, etnia);
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void setCamisa(String matricula, String camisa) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DADOS SET TP_CAMISA = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, camisa);
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void setOrientacao(String matricula, String orientacao) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DADOS SET NM_ORIENT_SEXUAL = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, orientacao);
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void setReligiao(String matricula, String religiao) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DADOS SET NM_RELIGIAO = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, religiao);
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

}// Classe
