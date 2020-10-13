package br.com.fiap.tds.bo;

import java.sql.SQLException;

import br.com.fiap.tds.bean.Login;
import br.com.fiap.tds.dao.LoginDao;
import br.com.fiap.tds.exception.AtualizacaoNaoRealizadaException;
import br.com.fiap.tds.exception.DadoInvalidoException;
import br.com.fiap.tds.exception.ItemNaoEncontradoException;

/**
 * Classe responsável pela lógica de negócio e validações do Login
 * 
 * @author Rodrigo Chiarelli
 * @version 4.0
 *
 */
public class LoginBo {

	// Classe Dao
	private LoginDao loginDao = new LoginDao();

	/**
	 * Valida os dados do login e aciona a camada de Dao para cadastrá-lo
	 * 
	 * @param login
	 * @throws ClassNotFoundException
	 * @throws DadoInvalidoException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public void cadastrar(Login login)
			throws ClassNotFoundException, DadoInvalidoException, SQLException, ItemNaoEncontradoException {

		validar(login);
		loginDao.cadastrar(login);
	}

	/**
	 * Valida os dados do login e aciona a camada de Dao para atualizá-lo
	 * 
	 * @param login
	 * @throws ClassNotFoundException
	 * @throws DadoInvalidoException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 * @throws AtualizacaoNaoRealizadaException
	 */
	public void atualizar(Login login) throws ClassNotFoundException, DadoInvalidoException, SQLException,
			ItemNaoEncontradoException, AtualizacaoNaoRealizadaException {

		validar(login);
		loginDao.atualizar(login);
	}

	/**
	 * Recupera o login do colaborador
	 * 
	 * @param email E-mail do colaborador
	 * @param senha Senha do colaborador
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws DadoInvalidoException
	 */
	public Login pesquisar(String email, String senha)
			throws ClassNotFoundException, SQLException, DadoInvalidoException {
		return loginDao.pesquisar(email, senha);
	}
	
	/**
	 * Recupera um login pela matricula
	 * 
	 * @param matricula Matrícula do colaborador
	 * @return login Objeto com os dados de login
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public Login pesquisar(int matricula) throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {
		return loginDao.pesquisar(matricula);
	}
	
	/**
	 * Recupera um login pelo email
	 * 
	 * @param email E-mail de login
	 * @return login Objeto com os dados de login
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public Login pesquisar(String email) throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {
		return loginDao.pesquisar(email);
	}
	
	/**
	 * Remove um login e todos os dados do colaborador
	 * 
	 * @param matricula Matrícula do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public void remover(int matricula) throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {
		loginDao.remover(matricula);
	}

	/**
	 * Método que valida de acordo com as regras os dados de um login
	 * 
	 * @param login
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 * @throws DadoInvalidoException
	 */
	private void validar(Login login)
			throws ClassNotFoundException, SQLException, ItemNaoEncontradoException, DadoInvalidoException {

		if (login.getEmail() == null || login.getEmail().isEmpty())
			throw new DadoInvalidoException("\nEmail é obrigatório!");

		if (login.getEmail().length() > 60)
			throw new DadoInvalidoException("\nEmail pode ter no máximo 60 caracteres!");

		if (login.getSenha() == null || login.getSenha().isEmpty())
			throw new DadoInvalidoException("\nSenha é obrigatório!");

		if (login.getSenha().length() > 60)
			throw new DadoInvalidoException("\nSenha pode ter no máximo 60 caracteres!");

		if (login.getApelido() == null || login.getApelido().isEmpty())
			throw new DadoInvalidoException("\nApelido é obrigatório!");

		if (login.getApelido().length() > 60)
			throw new DadoInvalidoException("\nApelido pode ter no máximo 60 caracteres!");

	}

}// Classe
