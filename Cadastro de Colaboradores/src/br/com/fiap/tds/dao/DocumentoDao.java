package br.com.fiap.tds.dao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import br.com.fiap.tds.bean.Dados;
import br.com.fiap.tds.bean.Documento;
import br.com.fiap.tds.exception.AtualizacaoNaoRealizadaException;
import br.com.fiap.tds.exception.ItemCadastradoException;
import br.com.fiap.tds.exception.ItemNaoEncontradoException;
import br.com.fiap.tds.exception.OperacaoInvalidaException;
import br.com.fiap.tds.factory.ConnectionFactory;

/**
 * Classe respons�vel por acessar o banco de dados e realizar as opera��es
 * b�sicas (CRUD) dos documentos do colaborador
 * 
 * @author Rodrigo Chiarelli
 * @version 1.0
 */
public class DocumentoDao {

	// CRIA >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Cadastra os documentos do colaborador no banco de dados
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @param documento Objeto contendo o documento do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 * @throws ItemCadastradoException
	 * @throws OperacaoInvalidaException
	 */
	public void cadastrar(int matricula, Documento documento) throws ClassNotFoundException, SQLException,
			ItemNaoEncontradoException, ItemCadastradoException, OperacaoInvalidaException {

		if (isCadastrado(matricula, documento.getNome()))
			throw new ItemCadastradoException();

		if (documento.getNome().equals("CERTIFICADO DE RESERVISTA")) {
			if (!isMasculino(matricula))
				throw new OperacaoInvalidaException(
						"\nPara enviar o certificado de reservista o sexo precisa ser 'MASCULINO'!");

			if (!isIdadeReservista(matricula))
				throw new OperacaoInvalidaException(
						"\nPara enviar o certificado de reservista o ano precisa ser superior a 18 da data atual!");
		}

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"INSERT INTO T_XCAVE_DOCUMENTO (CD_MATRICULA, CD_DOCUMENTO, NM_DOCUMENTO, NM_ARQUIVO, BT_ARQUIVO) VALUES (?,SQ_T_XCAVE_DOCUMENTO.NEXTVAL,?,?,?)");

		// Coloca os valores na query
		stmt.setInt(1, matricula);
		stmt.setString(2, documento.getNome());
		stmt.setString(3, documento.getNomeArquivo());
		stmt.setBytes(4, documento.getArquivo());

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conex�o
		stmt.close();
		conexao.close();

	}

	// PESQUISA >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Cria um .zip com todos os documentos do colaborador
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @return Documentos Objeto contendo todos os documentos do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 * @throws IOException
	 */
	public void baixarZip(int matricula)
			throws ClassNotFoundException, SQLException, ItemNaoEncontradoException, IOException {

		List<String> listaNomes = new ArrayList<String>();
		List<byte[]> listaArquivos = new ArrayList<byte[]>();

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("SELECT NM_ARQUIVO, BT_ARQUIVO FROM T_XCAVE_DOCUMENTO WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setInt(1, matricula);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		while (resultado.next()) {

			listaNomes.add(resultado.getString("NM_ARQUIVO"));
			listaArquivos.add(resultado.getBytes("BT_ARQUIVO"));
		}

		if (listaNomes.size() == 0 || listaArquivos.size() == 0)
			throw new ItemNaoEncontradoException();

		// Fechar a conex�o
		stmt.close();
		conexao.close();

		exportarZip("documentos.zip", listaNomes, listaArquivos);

	}

	/**
	 * Recupera um documento do colaborador
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @param nome      Nome do documento
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 * @throws IOException
	 */
	public void baixarArquivo(int matricula, String nome)
			throws ClassNotFoundException, SQLException, ItemNaoEncontradoException, IOException {

		int aux = 0;

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT NM_ARQUIVO, BT_ARQUIVO FROM T_XCAVE_DOCUMENTO WHERE CD_MATRICULA = ? AND NM_DOCUMENTO = ?");

		// Coloca os valores na query
		stmt.setInt(1, matricula);
		stmt.setString(2, nome);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {
			exportarArquivo(resultado.getString("NM_ARQUIVO"), resultado.getBytes("BT_ARQUIVO"));
			aux++;
		}

		if (aux == 0)
			throw new ItemNaoEncontradoException();

		// Fechar a conex�o
		stmt.close();
		conexao.close();

	}

	/**
	 * Verifica se o documento j� foi cadastrado
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public boolean isCadastrado(int matricula, String nome)
			throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {

		boolean x = false;

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Criar o PreparedStatement
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT CD_DOCUMENTO FROM T_XCAVE_DOCUMENTO WHERE CD_MATRICULA = ? AND NM_DOCUMENTO = ?");

		// Coloca os valores na query
		stmt.setInt(1, matricula);
		stmt.setString(2, nome);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {
			x = true;
		}

		// Fechar a conex�o
		stmt.close();
		conexao.close();

		return x;
	}

	/**
	 * Verifica se os documentos j� foram cadastrados
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public boolean isTodosCadastrados(int matricula)
			throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {

		boolean x = false;

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Criar o PreparedStatement
		PreparedStatement stmt = conexao
				.prepareStatement("SELECT COUNT(CD_DOCUMENTO) FROM T_XCAVE_DOCUMENTO WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setInt(1, matricula);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {
			if (isMasculino(matricula)) {
				if (resultado.getInt(1) == 6)
					x = true;
			} else {
				if (resultado.getInt(1) == 5)
					x = true;
			}
		}

		// Fechar a conex�o
		stmt.close();
		conexao.close();

		return x;
	}

	/**
	 * Verifica se o colaborador � do sexo masculino
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public boolean isMasculino(int matricula) throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {

		boolean x = false;
		String sexo = "";

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Criar o PreparedStatement
		PreparedStatement stmt = conexao.prepareStatement("SELECT DS_SEXO FROM T_XCAVE_DADOS WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setInt(1, matricula);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {

			sexo = resultado.getString(1);

			if (sexo.equals("MASCULINO"))
				x = true;
		}

		// Valida se encontrou o sexo
		if (sexo.isEmpty())
			throw new ItemNaoEncontradoException();

		// Fechar a conex�o
		stmt.close();
		conexao.close();

		return x;
	}

	/**
	 * Verifica se o colaborador tem idade para ser reservista
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public boolean isIdadeReservista(int matricula)
			throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {

		String dataNascimento = "";
		Documento doc = new Documento();
		Dados dados = new Dados();

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Criar o PreparedStatement
		PreparedStatement stmt = conexao
				.prepareStatement("SELECT DT_NASCIMENTO FROM T_XCAVE_DADOS WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setInt(1, matricula);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {
			dataNascimento = resultado.getString(1);
			dataNascimento = dados.arrumarData(dataNascimento);
		}

		// Valida se encontrou a data de nascimento
		if (dataNascimento.isEmpty())
			throw new ItemNaoEncontradoException();

		// Fechar a conex�o
		stmt.close();
		conexao.close();

		// Valida se o ano de nascimento do colaborador � superior a 18 anos
		return doc.isMaiorDezoito(dataNascimento);

	}

	// ATUALIZA >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Atualiza o documento
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @param documento Objeto contendo o documento
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 * @throws AtualizacaoNaoRealizadaException
	 */
	public void atualizar(int matricula, Documento documento)
			throws ClassNotFoundException, SQLException, IOException, AtualizacaoNaoRealizadaException {

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"UPDATE T_XCAVE_DOCUMENTO SET NM_ARQUIVO = ?, BT_ARQUIVO ? WHERE CD_MATRICULA = ? AND NM_DOCUMENTO = ?");

		// Coloca os valores na query
		stmt.setString(1, documento.getNomeArquivo());
		stmt.setBytes(2, documento.getArquivo());
		stmt.setInt(3, matricula);
		stmt.setString(4, documento.getNome());

		// Executar a query
		int qtd = stmt.executeUpdate();

		// Valida se atualizou o dado
		if (qtd == 0)
			throw new AtualizacaoNaoRealizadaException();

		// Fechar a conex�o
		stmt.close();
		conexao.close();

	}

	// OUTROS >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Importa um arquivo
	 * 
	 * @param nome Nome do arquivo de entrada
	 * @return byte[] C�pia do arquivo
	 * @throws IOException
	 */
	public byte[] importarArquivo(String nome) throws IOException {

		// Cria um objeto que representa o arquivo
		File file = new File(nome);

		// Cria uma c�pia do arquivo representado pelo objeto
		byte[] arquivo = Files.readAllBytes(file.toPath());

		// Retorna a c�pia
		return arquivo;

	}

	/**
	 * Exporta um arquivo
	 * 
	 * @param nome    Nome do arquivo de sa�da
	 * @param arquivo Objeto contendo o arquivo
	 * @throws IOException
	 */
	public void exportarArquivo(String nome, byte[] arquivo) throws IOException {

		// Cria um objeto que representa o arquivo
		File file = new File(nome);
		// Cria a sa�da do arquivo
		FileOutputStream saida = new FileOutputStream(file);
		// Escreve o arquivo na sa�da
		saida.write(arquivo);
		// Garante toda a escrita
		saida.flush();
		// Fecha a sa�da
		saida.close();
	}

	/**
	 * Exporta um zip com v�rios arquivos
	 * 
	 * @param arqSaida Nome do arquivo de sa�da
	 * @param nomes    Objeto contendo uma lista com os nomes dos arquivos
	 * @param arquivos Objeto contendo uma lista de arquivos
	 * @throws IOException
	 */
	public void exportarZip(String arqSaida, List<String> nomes, List<byte[]> arquivos) throws IOException {

		// Cria a sa�da do arquivo
		FileOutputStream destino = new FileOutputStream(new File(arqSaida));
		// Cria a sa�da do zip
		ZipOutputStream saida = new ZipOutputStream(new BufferedOutputStream(destino));

		for (int i = 0; i < nomes.size(); i++) {
			// Cria um objeto que representa o arquivo
			File file = new File(nomes.get(i));
			// Pega o arquivo da lista
			byte[] arquivo = arquivos.get(i);
			// Cria uma nova entrada no zip com o nome do objeto
			ZipEntry entry = new ZipEntry(file.getName());
			// Coloca a entrada no zip
			saida.putNextEntry(entry);
			// Escreve o arquivo na sa�da
			saida.write(arquivo);
			// Garante toda a escrita
			saida.flush();
		}
		// Fecha a sa�da
		saida.close();
	}

}// Classe
