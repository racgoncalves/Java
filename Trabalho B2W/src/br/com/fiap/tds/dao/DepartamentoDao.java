package br.com.fiap.tds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.tds.bean.Departamento;
import br.com.fiap.tds.exception.ItemNaoEncontradoException;
import br.com.fiap.tds.factory.ConnectionFactory;

/**
 * Classe responsável por acessar o banco de dados e realizar as operações de
 * pesquisa os departamentos
 * 
 * @author Rodrigo Chiarelli
 * @version 1.0
 */
public class DepartamentoDao {

	/**
	 * Recupera um departamento
	 * 
	 * @param codigo Código do departamento
	 * @return Departamento Objeto contendo os dados de um departamento
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public Departamento pesquisar(int codigo)
			throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {

		Departamento depto = null;
		String nome = "";

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("SELECT * FROM T_XCAVE_DEPARTAMENTO WHERE CD_DEPARTAMENTO = ?");

		// Coloca os valores na query
		stmt.setInt(1, codigo);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {
			nome = resultado.getString(1);
			depto = new Departamento(codigo, nome);
		}

		if (depto == null)
			throw new ItemNaoEncontradoException();

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return depto;

	}

	/**
	 * Recupera todos os departamentos cadastrados
	 * 
	 * @return List<String> Lista com todos os departamentos escritos
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<String> listar() throws ClassNotFoundException, SQLException {

		Departamento depto = new Departamento();
		List<String> lista = new ArrayList<String>();

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Criar o objeto para executar um comando SQL
		Statement stmt = conexao.createStatement();

		// Realizar a pesquisa de todos os departamentos
		ResultSet resultado = stmt.executeQuery("SELECT * FROM T_XCAVE_DEPARTAMENTO");

		while (resultado.next()) {

			depto.setCodigo(resultado.getInt("CD_DEPARTAMENTO"));
			depto.setNome(resultado.getString("NM_DEPARTAMENTO"));

			lista.add(depto.toString());

		}

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return lista;

	}

}
