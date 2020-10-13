package br.com.fiap.tds.bo;

import java.io.IOException;
import java.sql.SQLException;

import br.com.fiap.tds.bean.Colaborador;
import br.com.fiap.tds.bean.Documento;
import br.com.fiap.tds.dao.ColaboradorDao;
import br.com.fiap.tds.dao.DocumentoDao;
import br.com.fiap.tds.exception.AtualizacaoNaoRealizadaException;
import br.com.fiap.tds.exception.DadoInvalidoException;
import br.com.fiap.tds.exception.ItemCadastradoException;
import br.com.fiap.tds.exception.ItemNaoEncontradoException;
import br.com.fiap.tds.validation.ReservistaValidation;

/**
 * Classe responsável pela lógica de negócio e validações dos documentos do
 * colaborador
 * 
 * @author Rodrigo Chiarelli
 * @version 4.0
 *
 */
public class DocumentoBo {

	// Classes Dao
	private DocumentoDao docDao = new DocumentoDao();
	private ColaboradorDao colabDao = new ColaboradorDao();

	// Classe Validation
	private ReservistaValidation reservistaVal = new ReservistaValidation();

	/**
	 * Valida o documento e aciona a camada de Dao para cadastrá-lo
	 * 
	 * @param doc Objeto contendo o documento
	 * @throws ClassNotFoundException
	 * @throws DadoInvalidoException
	 * @throws SQLException
	 * @throws ItemCadastradoException
	 * @throws ItemNaoEncontradoException
	 */
	public void cadastrar(Documento doc) throws ClassNotFoundException, DadoInvalidoException, SQLException,
			ItemNaoEncontradoException, ItemCadastradoException {

		validarCadastro(doc);
		docDao.cadastrar(doc);
	}

	/**
	 * Valida os dados do colaborador e aciona a camada de Dao para atualizá-lo
	 * 
	 * @param doc Objeto contendo o documento
	 * @throws ClassNotFoundException
	 * @throws DadoInvalidoException
	 * @throws SQLException
	 * @throws AtualizacaoNaoRealizadaException
	 * @throws ItemCadastradoException
	 * @throws ItemNaoEncontradoException
	 */
	public void atualizar(Documento doc) throws ClassNotFoundException, DadoInvalidoException, SQLException,
			AtualizacaoNaoRealizadaException, ItemNaoEncontradoException, ItemCadastradoException {

		validarAtualizacao(doc);
		docDao.atualizar(doc);
	}

	/**
	 * Cria um .zip com todos os documentos do colaborador
	 * 
	 * @param matricula Matrícula do colaborador
	 * @return Documentos Objeto contendo todos os documentos do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 * @throws IOException
	 */
	public void baixarZip(int matricula)
			throws ClassNotFoundException, SQLException, ItemNaoEncontradoException, IOException {
		docDao.baixarZip(matricula);
	}

	/**
	 * Recupera um documento
	 * 
	 * @param matricula Matrícula do colaborador
	 * @param nome      Nome do documento
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 * @throws IOException
	 */
	public void baixarArquivo(int matricula, String nome)
			throws ClassNotFoundException, SQLException, ItemNaoEncontradoException, IOException {
		docDao.baixarArquivo(matricula, nome);
	}

	/**
	 * Verifica se o documento já foi cadastrado
	 * 
	 * @param matricula Matrícula do colaborador
	 * @param nome      Nome do documento
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public boolean isCadastrado(int matricula, String nome)
			throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {
		return docDao.isCadastrado(matricula, nome);
	}

	/**
	 * Verifica se os documentos já foram cadastrados
	 * 
	 * @param matricula Matrícula do colaborador
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public boolean isTodosCadastrados(int matricula)
			throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {
		return docDao.isTodosCadastrados(matricula);
	}

	/**
	 * Importa um arquivo
	 * 
	 * @param nome Nome do arquivo de entrada
	 * @return byte[] Cópia do arquivo
	 * @throws IOException
	 */
	public byte[] importarArquivo(String nome) throws IOException {
		return docDao.importarArquivo(nome);
	}

	private void validarCadastro(Documento doc) throws ClassNotFoundException, SQLException, ItemNaoEncontradoException,
			DadoInvalidoException, ItemCadastradoException {

		if (doc.getNome() == null || doc.getNome().isEmpty())
			throw new DadoInvalidoException("\nNome do documento é obrigatório!");

		if (doc.getNome().length() > 60)
			throw new DadoInvalidoException("\nNome do documento pode ter no máximo 60 caracteres!");

		if (doc.getNomeArquivo() == null || doc.getNomeArquivo().isEmpty())
			throw new DadoInvalidoException("\nNome do arquivo é obrigatório!");

		if (doc.getNomeArquivo().length() > 60)
			throw new DadoInvalidoException("\nNome do arquivo pode ter no máximo 60 caracteres!");

		if (doc.getArquivo() == null)
			throw new DadoInvalidoException("\nArquivo é obrigatório!");

		if (docDao.isCadastrado(doc.getLogin().getMatricula(), doc.getNome()))
			throw new ItemCadastradoException();

		if (doc.getNome().equals("CERTIFICADO DE RESERVISTA")) {

			Colaborador colaborador = colabDao.pesquisar(doc.getLogin().getMatricula());

			if (!colaborador.getSexo().equals("MASCULINO"))
				throw new DadoInvalidoException(
						"\nPara enviar o certificado de reservista o sexo precisa ser 'MASCULINO'!");

			if (!reservistaVal.isMaiorDezoito(colaborador.getDataNascimento()))
				throw new DadoInvalidoException(
						"\nPara enviar o certificado de reservista precisa ter mais de 18 anos!");
		}

	}

	private void validarAtualizacao(Documento doc) throws ClassNotFoundException, SQLException,
			ItemNaoEncontradoException, DadoInvalidoException, ItemCadastradoException {

		if (doc.getNome() == null || doc.getNome().isEmpty())
			throw new DadoInvalidoException("\nNome do documento é obrigatório!");

		if (doc.getNome().length() > 60)
			throw new DadoInvalidoException("\nNome do documento pode ter no máximo 60 caracteres!");

		if (doc.getNomeArquivo() == null || doc.getNomeArquivo().isEmpty())
			throw new DadoInvalidoException("\nNome do arquivo é obrigatório!");

		if (doc.getNomeArquivo().length() > 60)
			throw new DadoInvalidoException("\nNome do arquivo pode ter no máximo 60 caracteres!");

		if (doc.getArquivo() == null)
			throw new DadoInvalidoException("\nArquivo é obrigatório!");

		if (doc.getNome().equals("CERTIFICADO DE RESERVISTA")) {

			Colaborador colaborador = colabDao.pesquisar(doc.getLogin().getMatricula());

			if (!colaborador.getSexo().equals("MASCULINO"))
				throw new DadoInvalidoException(
						"\nPara enviar o certificado de reservista o sexo precisa ser 'MASCULINO'!");

			if (!reservistaVal.isMaiorDezoito(colaborador.getDataNascimento()))
				throw new DadoInvalidoException(
						"\nPara enviar o certificado de reservista precisa ter mais de 18 anos!");
		}

	}

}// Classe
