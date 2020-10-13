package br.com.fiap.tds.bo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.tds.bean.Colaborador;
import br.com.fiap.tds.bean.Dependente;
import br.com.fiap.tds.dao.ColaboradorDao;
import br.com.fiap.tds.dao.DependenteDao;
import br.com.fiap.tds.exception.DadoInvalidoException;
import br.com.fiap.tds.exception.ItemCadastradoException;
import br.com.fiap.tds.exception.ItemNaoEncontradoException;
import br.com.fiap.tds.validation.CpfValidation;
import br.com.fiap.tds.validation.DataValidation;

/**
 * Classe respons�vel pela l�gica de neg�cio e valida��es dos dependentes do
 * colaborador
 * 
 * @author Rodrigo Chiarelli
 * @version 4.0
 *
 */
public class DependenteBo {

	// Classes Dao
	private ColaboradorDao colabDao = new ColaboradorDao();
	private DependenteDao depDao = new DependenteDao();

	// Classes Validation
	private CpfValidation cpfVal = new CpfValidation();
	private DataValidation dataVal = new DataValidation();

	/**
	 * Valida os dados do dependente e aciona a camada de Dao para cadastr�-lo
	 * 
	 * @param dep Objeto contendo o dependente
	 * @throws ClassNotFoundException
	 * @throws DadoInvalidoException
	 * @throws SQLException
	 * @throws ItemCadastradoException
	 * @throws ItemNaoEncontradoException
	 */
	public void cadastrar(Dependente dep) throws ClassNotFoundException, DadoInvalidoException, SQLException,
			ItemNaoEncontradoException, ItemCadastradoException {

		validar(dep);
		depDao.cadastrar(dep);
	}

	/**
	 * Recupera os dados de todos os dependentes do colaborador
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @return List<String> Lista com todos os dependentes do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<String> listar(int matricula) throws ClassNotFoundException, SQLException {
		return depDao.listar(matricula);
	}

	/**
	 * Recupera o documento de um dependente
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @param codigo    C�digo do dependente
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 * @throws IOException
	 */
	public void baixarArquivo(int matricula, int codigo)
			throws ClassNotFoundException, SQLException, ItemNaoEncontradoException, IOException {
		depDao.baixarArquivo(matricula, codigo);
	}
	
	/**
	 * Remove um dependente
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @param codigo    C�digo do dependente
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public void remover(int matricula, int codigo)
			throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {
		depDao.remover(matricula, codigo);
	}

	/**
	 * M�todo que valida de acordo com as regras os dados de um dependente
	 * 
	 * @param dep Objeto contendo o dependente
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 * @throws DadoInvalidoException
	 * @throws ItemCadastradoException
	 */
	private void validar(Dependente dep) throws ClassNotFoundException, SQLException, ItemNaoEncontradoException,
			DadoInvalidoException, ItemCadastradoException {

		if (dep.getNome() == null || dep.getNome().isEmpty())
			throw new DadoInvalidoException("\nNome � obrigat�rio!");

		if (dep.getNome().length() > 60)
			throw new DadoInvalidoException("\nNome pode ter no m�ximo 60 caracteres!");

		if (dep.getCpf() == null || dep.getCpf().isEmpty())
			throw new DadoInvalidoException("\nCPF � obrigat�rio!");

		if (!cpfVal.isCpf(dep.getCpf()))
			throw new DadoInvalidoException("\nCPF inv�lido!");

		if (dep.getSexo() == null || dep.getSexo().isEmpty())
			throw new DadoInvalidoException("\nSexo � obrigat�rio!");

		if (!dep.getSexo().equals("FEMININO") && !dep.getSexo().equals("MASCULINO"))
			throw new DadoInvalidoException("\nSexo inv�lido!");

		if (!dataVal.validarData(dep.getDataNascimento()))
			throw new DadoInvalidoException("\nData inv�lida!");

		if (!dataVal.validarDataDependente(dep.getDataNascimento()))
			throw new DadoInvalidoException("\nO colaborador deve ter entre 16 e 100 anos!");

		if (dep.getTipo().equals("C�NJUGE")) {

			Colaborador colaborador = colabDao.pesquisar(dep.getLogin().getMatricula());

			if (!colaborador.getEstadoCivil().equals("CASADO"))
				throw new DadoInvalidoException("\nO estado civil deve ser 'CASADO'!");

			if (depDao.conjugeIsCadastrado(dep.getLogin().getMatricula()))
				throw new ItemCadastradoException();
		}

		if (dep.getTipo().equals("FILHO")) {

			Colaborador colaborador = colabDao.pesquisar(dep.getLogin().getMatricula());

			if (depDao.getFilhosDependente(dep.getLogin().getMatricula()) >= colaborador.getFilhos())
				throw new DadoInvalidoException(
						"\nA quantidade de dependentes deste tipo n�o pode ser superior � quantidade de filhos!");
		}

		if (dep.getTipo().equals("GENITOR")) {

			if (depDao.genitorIsCadastrado(dep.getLogin().getMatricula(), dep.getSexo()))
				throw new ItemCadastradoException();
		}

		if (dep.getDocumento().getNome() == null || dep.getDocumento().getNome().isEmpty())
			throw new DadoInvalidoException("\nNome do documento � obrigat�rio!");

		if (dep.getDocumento().getNome().length() > 60)
			throw new DadoInvalidoException("\nNome do documento pode ter no m�ximo 60 caracteres!");

		if (dep.getDocumento().getNomeArquivo() == null || dep.getDocumento().getNomeArquivo().isEmpty())
			throw new DadoInvalidoException("\nNome do arquivo � obrigat�rio!");

		if (dep.getDocumento().getNomeArquivo().length() > 60)
			throw new DadoInvalidoException("\nNome do arquivo pode ter no m�ximo 60 caracteres!");

		if (dep.getDocumento().getArquivo() == null)
			throw new DadoInvalidoException("\nArquivo � obrigat�rio!");

	}

}// Classe
