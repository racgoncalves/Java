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
 * Classe responsável pela lógica de negócio e validações dos dependentes do
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
	 * Valida os dados do dependente e aciona a camada de Dao para cadastrá-lo
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
	 * @param matricula Matrícula do colaborador
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
	 * @param matricula Matrícula do colaborador
	 * @param codigo    Código do dependente
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
	 * @param matricula Matrícula do colaborador
	 * @param codigo    Código do dependente
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public void remover(int matricula, int codigo)
			throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {
		depDao.remover(matricula, codigo);
	}

	/**
	 * Método que valida de acordo com as regras os dados de um dependente
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
			throw new DadoInvalidoException("\nNome é obrigatório!");

		if (dep.getNome().length() > 60)
			throw new DadoInvalidoException("\nNome pode ter no máximo 60 caracteres!");

		if (dep.getCpf() == null || dep.getCpf().isEmpty())
			throw new DadoInvalidoException("\nCPF é obrigatório!");

		if (!cpfVal.isCpf(dep.getCpf()))
			throw new DadoInvalidoException("\nCPF inválido!");

		if (dep.getSexo() == null || dep.getSexo().isEmpty())
			throw new DadoInvalidoException("\nSexo é obrigatório!");

		if (!dep.getSexo().equals("FEMININO") && !dep.getSexo().equals("MASCULINO"))
			throw new DadoInvalidoException("\nSexo inválido!");

		if (!dataVal.validarData(dep.getDataNascimento()))
			throw new DadoInvalidoException("\nData inválida!");

		if (!dataVal.validarDataDependente(dep.getDataNascimento()))
			throw new DadoInvalidoException("\nO colaborador deve ter entre 16 e 100 anos!");

		if (dep.getTipo().equals("CÔNJUGE")) {

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
						"\nA quantidade de dependentes deste tipo não pode ser superior à quantidade de filhos!");
		}

		if (dep.getTipo().equals("GENITOR")) {

			if (depDao.genitorIsCadastrado(dep.getLogin().getMatricula(), dep.getSexo()))
				throw new ItemCadastradoException();
		}

		if (dep.getDocumento().getNome() == null || dep.getDocumento().getNome().isEmpty())
			throw new DadoInvalidoException("\nNome do documento é obrigatório!");

		if (dep.getDocumento().getNome().length() > 60)
			throw new DadoInvalidoException("\nNome do documento pode ter no máximo 60 caracteres!");

		if (dep.getDocumento().getNomeArquivo() == null || dep.getDocumento().getNomeArquivo().isEmpty())
			throw new DadoInvalidoException("\nNome do arquivo é obrigatório!");

		if (dep.getDocumento().getNomeArquivo().length() > 60)
			throw new DadoInvalidoException("\nNome do arquivo pode ter no máximo 60 caracteres!");

		if (dep.getDocumento().getArquivo() == null)
			throw new DadoInvalidoException("\nArquivo é obrigatório!");

	}

}// Classe
