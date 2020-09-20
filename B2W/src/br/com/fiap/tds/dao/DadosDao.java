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

import br.com.fiap.tds.bean.Dados;
import br.com.fiap.tds.factory.ConnectionFactory;

public class DadosDao {

	public void setDados(String matricula, Dados dados) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"INSERT INTO T_XCAVE_COLABORADOR (CD_MATRICULA, NM_COLABORADOR, NR_CPF, NR_PIS, DS_SEXO,"
						+ " NM_PAIS, NM_ESTADO, NM_CIDADE, DT_NASCIMENTO, DS_EST_CIVIL, QT_FILHOS, NM_ETNIA,"
						+ " TP_CAMISA, NM_ORIENT_SEXUAL, NM_RELIGIAO)" + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		// Coloca os valores na query
		stmt.setString(1, matricula);
		stmt.setString(2, dados.getNome());
		stmt.setString(3, dados.getCpf());
		stmt.setString(4, dados.getPis());
		stmt.setString(5, dados.getSexo());
		stmt.setString(6, dados.getPais());
		stmt.setString(7, dados.getEstado());
		stmt.setString(8, dados.getCidadeNascimento());
		stmt.setString(9, dados.getDataNascimento());
		stmt.setString(10, dados.getEstadoCivil());
		stmt.setInt(11, dados.getNumeroFilhos());
		stmt.setString(12, dados.getEtnia());
		stmt.setString(13, dados.getTamanhoCamiseta());
		stmt.setString(14, dados.getOrientacao());
		stmt.setString(15, dados.getReligiao());

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void setConta(String matricula, Dados dados) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmtBradesco = conexao.prepareStatement(
				"INSERT INTO T_XCAVE_CONTA_BANCO (CD_MATRICULA, NR_AGENCIA, NR_DIGITO_AGENCIA, NR_CONTA, NR_DIGITO_CONTA) "
						+ "VALUES (?,?,?,?,?)");

		// Coloca os valores na query
		stmtBradesco.setString(1, matricula);
		stmtBradesco.setString(2, dados.getAgencia());
		stmtBradesco.setString(3, dados.getDigitoAgencia());
		stmtBradesco.setString(4, dados.getConta());
		stmtBradesco.setString(5, dados.getDigitoConta());

		// Executar a query
		stmtBradesco.executeUpdate();

		// Fechar a conexão
		stmtBradesco.executeUpdate();
		conexao.close();

	}

	public Dados getColaborador(String matricula) throws ClassNotFoundException, SQLException {

		Dados dados = null;

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Criar o PreparedStatement
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT * FROM T_XCAVE_LOGIN L, T_XCAVE_COLABORADOR C, T_XCAVE_CONTA_BANCO B WHERE L.CD_MATRICULA = ? AND C.CD_MATRICULA = ? AND B.CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, matricula);
		stmt.setString(2, matricula);
		stmt.setString(3, matricula);

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

		dados.setNome(resultado.getString("NM_COLABORADOR"));
		dados.setCpf(resultado.getString("NR_CPF"));
		dados.setPis(resultado.getString("NR_PIS"));
		dados.setSexo(resultado.getString("DS_SEXO"));
		dados.setPais(resultado.getString("NM_PAIS"));
		dados.setEstado(resultado.getString("NM_ESTADO"));
		dados.setCidadeNascimento(resultado.getString("NM_CIDADE"));
		dados.setDataNascimento(resultado.getString("DT_NASCIMENTO"));
		dados.setEstadoCivil(resultado.getString("DS_EST_CIVIL"));
		dados.setNumeroFilhos(resultado.getInt("QT_FILHOS"));
		dados.setAgencia(resultado.getString("NR_AGENCIA"));
		dados.setDigitoAgencia(resultado.getString("NR_DIGITO_AGENCIA"));
		dados.setConta(resultado.getString("NR_CONTA"));
		dados.setgetDigitoConta(resultado.getString("NR_DIGITO_CONTA"));
		dados.setEtnia(resultado.getString("NM_ETNIA"));
		dados.setTamanhoCamiseta(resultado.getString("TP_CAMISA"));
		dados.setOrientacao(resultado.getString("NM_ORIENT_SEXUAL"));
		dados.setReligiao(resultado.getString("NM_RELIGIAO"));

		return dados;

	}

	public void deletarDados(String matricula) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmtA = conexao.prepareStatement("DELETE FROM T_XCAVE_DEPENDENTE WHERE CD_MATRICULA = ?");
		PreparedStatement stmtB = conexao.prepareStatement("DELETE FROM T_XCAVE_CONTA_BANCO WHERE CD_MATRICULA = ?");
		PreparedStatement stmtC = conexao.prepareStatement("DELETE FROM T_XCAVE_COLABORADOR WHERE CD_MATRICULA = ?");
		PreparedStatement stmtD = conexao.prepareStatement("DELETE FROM T_XCAVE_LOGIN WHERE CD_MATRICULA = ?");

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

	public String getMatricula(String cpf) throws ClassNotFoundException, SQLException {

		String matricula = "";

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Criar o PreparedStatement
		PreparedStatement stmt = conexao
				.prepareStatement("SELECT CD_MATRICULA FROM T_XCAVE_COLABORADOR WHERE NR_CPF = ?");

		// Coloca os valores na query
		stmt.setString(1, cpf);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next())
			matricula = resultado.getString("CD_MATRICULA");

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return matricula;

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

	public void setCPF(String matricula, String cpf) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_COLABORADOR SET NR_CPF = ? WHERE CD_MATRICULA = ?");

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
				.prepareStatement("UPDATE T_XCAVE_COLABORADOR SET NR_PIS = ? WHERE CD_MATRICULA = ?");

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
				.prepareStatement("UPDATE T_XCAVE_COLABORADOR SET DS_SEXO = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, sexo);
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void setPais(String matricula, String pais) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_COLABORADOR SET NM_PAIS = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, pais);
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void setEstado(String matricula, String estado) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_COLABORADOR SET NM_ESTADO = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, estado);
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void setCidade(String matricula, String cidade) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_COLABORADOR SET NM_CIDADE = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, cidade);
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
				.prepareStatement("UPDATE T_XCAVE_COLABORADOR SET DT_NASCIMENTO = ? WHERE CD_MATRICULA = ?");

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
				.prepareStatement("UPDATE T_XCAVE_COLABORADOR SET DS_EST_CIVIL = ? WHERE CD_MATRICULA = ?");

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
				.prepareStatement("UPDATE T_XCAVE_COLABORADOR SET QT_FILHOS = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setInt(1, filhos);
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void setAgencia(String matricula, String agencia) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_CONTA_BANCO SET NR_AGENCIA = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, agencia);
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void setDigitoAgencia(String matricula, String digito) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_CONTA_BANCO SET NR_DIGITO_AGENCIA = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, digito);
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void setConta(String matricula, String conta) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_CONTA_BANCO SET NR_CONTA = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, conta);
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void setDigitoConta(String matricula, String digito) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_CONTA_BANCO SET NR_DIGITO_CONTA = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, digito);
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
				.prepareStatement("UPDATE T_XCAVE_COLABORADOR SET NM_ETNIA = ? WHERE CD_MATRICULA = ?");

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
				.prepareStatement("UPDATE T_XCAVE_COLABORADOR SET TP_CAMISA = ? WHERE CD_MATRICULA = ?");

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
				.prepareStatement("UPDATE T_XCAVE_COLABORADOR SET NM_ORIENT_SEXUAL = ? WHERE CD_MATRICULA = ?");

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
				.prepareStatement("UPDATE T_XCAVE_COLABORADOR SET NM_RELIGIAO = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, religiao);
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
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
				"SELECT DISTINCT * FROM T_XCAVE_LOGIN L, T_XCAVE_COLABORADOR C, T_XCAVE_CONTA_BANCO B WHERE L.CD_MATRICULA = C.CD_MATRICULA AND B.CD_MATRICULA = C.CD_MATRICULA");

		while (resultado.next()) {

			Dados dados = parse(resultado);

			String email = resultado.getString("DS_EMAIL");

			lista.add(dados.toString() + "\n17 - Email: " + email);
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

}// Classe
