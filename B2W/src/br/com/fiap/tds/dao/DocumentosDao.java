package br.com.fiap.tds.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.tds.bean.Documentos;
import br.com.fiap.tds.factory.ConnectionFactory;

public class DocumentosDao {

	public Documentos getDocumentos(String matricula) throws ClassNotFoundException, SQLException {

		Documentos doc = null;

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM T_XCAVE_DOCUMENTOS WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, matricula);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {
			doc = parse(resultado);
		}

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return doc;
	}
	
	private Documentos parse(ResultSet resultado) throws SQLException {
		
		Documentos doc = new Documentos();
		
		doc.setRg(resultado.getBytes("IM_RG"));
		doc.setCarteiraTrabalho(resultado.getBytes("IM_CARTEIRA_TRABALHO"));
		doc.setResidencia(resultado.getBytes("IM_COMP_RESIDENCIA"));
		doc.setTituloEleitor(resultado.getBytes("IM_TITULO_ELEITOR"));
		doc.setEscolaridade(resultado.getBytes("IM_COMP_ESCOLARIDADE"));

		if (resultado.getBytes("IM_RESERVISTA") != null)
			doc.setReservista(resultado.getBytes("IM_RESERVISTA"));
		
		return doc;
		
	}
	
	public void setDocumentos(String matricula, Documentos documentos) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"INSERT INTO T_XCAVE_DOCUMENTOS (CD_MATRICULA, IM_RG, IM_CARTEIRA_TRABALHO, IM_COMP_RESIDENCIA, IM_TITULO_ELEITOR,"
						+ " IM_COMP_ESCOLARIDADE) VALUES (?,?,?,?,?,?)");

		// Coloca os valores na query
		stmt.setString(1, matricula);
		stmt.setBytes(2, documentos.getRg());
		stmt.setBytes(3, documentos.getCarteiraTrabalho());
		stmt.setBytes(4, documentos.getResidencia());
		stmt.setBytes(5, documentos.getTituloEleitor());
		stmt.setBytes(6, documentos.getEscolaridade());

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public byte[] lerDocumento(String nome) throws IOException {

		File arquivo = new File(nome + ".pdf");

		byte[] copia = Files.readAllBytes(arquivo.toPath());

		return copia;

	}

	public void setRg(String matricula, Documentos documentos) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DOCUMENTOS SET IM_RG = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setBytes(1, documentos.getRg());
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void setCarteiraTrabalho(String matricula, Documentos documentos)
			throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DOCUMENTOS SET IM_CARTEIRA_TRABALHO = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setBytes(1, documentos.getCarteiraTrabalho());
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void setComprovanteResidencia(String matricula, Documentos documentos)
			throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DOCUMENTOS SET IM_COMP_RESIDENCIA = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setBytes(1, documentos.getResidencia());
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void setTituloEleitor(String matricula, Documentos documentos) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DOCUMENTOS SET IM_TITULO_ELEITOR = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setBytes(1, documentos.getTituloEleitor());
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void setComprovanteEscolaridade(String matricula, Documentos documentos)
			throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DOCUMENTOS SET IM_COMP_ESCOLARIDADE = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setBytes(1, documentos.getEscolaridade());
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	public void setCertificadoReservista(String matricula, Documentos documentos)
			throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DOCUMENTOS SET IM_RESERVISTA = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setBytes(1, documentos.getReservista());
		stmt.setString(2, matricula);

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

}// Classe
