package br.com.fiap.tds.bo;

import java.io.IOException;
import java.sql.SQLException;

import br.com.fiap.tds.bean.Colaborador;
import br.com.fiap.tds.dao.ColaboradorDao;
import br.com.fiap.tds.exception.AtualizacaoNaoRealizadaException;
import br.com.fiap.tds.exception.DadoInvalidoException;
import br.com.fiap.tds.exception.ItemNaoEncontradoException;
import br.com.fiap.tds.validation.ContaBancoValidation;
import br.com.fiap.tds.validation.CpfValidation;
import br.com.fiap.tds.validation.DataValidation;
import br.com.fiap.tds.validation.PisValidation;

/**
 * Classe responsável pela lógica de negócio e validações do colaborador
 * 
 * @author Rodrigo Chiarelli
 * @version 4.0
 *
 */
public class ColaboradorBo {

	// Classe Dao
	private ColaboradorDao colabDao = new ColaboradorDao();

	// Classes Validation
	private CpfValidation cpfVal = new CpfValidation();
	private PisValidation pisVal = new PisValidation();
	private DataValidation dataVal = new DataValidation();
	private ContaBancoValidation contaVal = new ContaBancoValidation();

	/**
	 * Valida os dados do colaborador e aciona a camada de Dao para cadastrá-lo
	 * 
	 * @param colaborador
	 * @throws ClassNotFoundException
	 * @throws DadoInvalidoException
	 * @throws SQLException
	 */
	public void cadastrar(Colaborador colab) throws ClassNotFoundException, DadoInvalidoException, SQLException {

		validar(colab);
		colabDao.cadastrar(colab);
	}

	/**
	 * Valida os dados do colaborador e aciona a camada de Dao para atualizá-lo
	 * 
	 * @param colaborador
	 * @throws ClassNotFoundException
	 * @throws DadoInvalidoException
	 * @throws SQLException
	 * @throws AtualizacaoNaoRealizadaException
	 */
	public void atualizar(Colaborador colab)
			throws ClassNotFoundException, DadoInvalidoException, SQLException, AtualizacaoNaoRealizadaException {

		validar(colab);
		colabDao.atualizar(colab);
	}

	/**
	 * Recupera os dados do colaborador
	 * 
	 * @param matricula Matrícula do colaborador
	 * @return colaborador Objeto contendo o dados do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public Colaborador pesquisar(int matricula)
			throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {
		return colabDao.pesquisar(matricula);
	}

	/**
	 * Exporta um arquivo .txt com todos os colaboradores
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void exportar() throws ClassNotFoundException, SQLException, IOException {
		colabDao.exportar();
	}

	/**
	 * Verifica se os dados do colaborador já foram cadastrados
	 * 
	 * @param matricula Matrícula do colaborador
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean isCadastrado(int matricula) throws ClassNotFoundException, SQLException {
		return colabDao.isCadastrado(matricula);
	}

	/**
	 * Método que valida de acordo com as regras os dados de um colaborador
	 * 
	 * @param colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws DadoInvalidoException
	 */
	private void validar(Colaborador colab) throws ClassNotFoundException, SQLException, DadoInvalidoException {

		if (colab.getNome() == null || colab.getNome().isEmpty())
			throw new DadoInvalidoException("\nNome é obrigatório!");

		if (colab.getNome().length() > 60)
			throw new DadoInvalidoException("\nNome pode ter no máximo 60 caracteres!");

		if (colab.getCpf() == null || colab.getCpf().isEmpty())
			throw new DadoInvalidoException("\nCPF é obrigatório!");

		if (!cpfVal.isCpf(colab.getCpf()))
			throw new DadoInvalidoException("\nCPF inválido!");

		if (colab.getPis() == null || colab.getPis().isEmpty())
			throw new DadoInvalidoException("\nPIS é obrigatório!");

		if (!pisVal.isPis(colab.getPis()))
			throw new DadoInvalidoException("\nPIS inválido!");

		if (colab.getSexo() == null || colab.getSexo().isEmpty())
			throw new DadoInvalidoException("\nSexo é obrigatório!");

		if (!colab.getSexo().equals("FEMININO") && !colab.getSexo().equals("MASCULINO"))
			throw new DadoInvalidoException("\nSexo inválido!");

		if (colab.getNacionalidade() == null || colab.getNacionalidade().isEmpty())
			throw new DadoInvalidoException("\nNacionalidade é obrigatório!");

		if (colab.getNacionalidade().length() > 60)
			throw new DadoInvalidoException("\nNacionalidade pode ter no máximo 60 caracteres!");

		if (colab.getNaturalidade() == null || colab.getNaturalidade().isEmpty())
			throw new DadoInvalidoException("\nNaturalidade é obrigatório!");

		if (colab.getNaturalidade().length() > 60)
			throw new DadoInvalidoException("\nNaturalidade pode ter no máximo 60 caracteres!");

		if (colab.getDataNascimento() == null || colab.getDataNascimento().isEmpty())
			throw new DadoInvalidoException("\nData de nascimento é obrigatório!");

		if (!dataVal.validarData(colab.getDataNascimento()))
			throw new DadoInvalidoException("\nData inválida!");

		if (!dataVal.validarDataColaborador(colab.getDataNascimento()))
			throw new DadoInvalidoException("\nO colaborador deve ter entre 16 e 100 anos!");

		if (colab.getEstadoCivil() == null || colab.getEstadoCivil().isEmpty())
			throw new DadoInvalidoException("\nEstado civil é obrigatório!");

		if (!colab.getEstadoCivil().equals("SOLTEIRO") && !colab.getEstadoCivil().equals("CASADO")
				&& !colab.getEstadoCivil().equals("DIVORCIADO") && !colab.getEstadoCivil().equals("VIÚVO"))
			throw new DadoInvalidoException("\nEstado civil inválido!");

		if (colab.getFilhos() < 0)
			throw new DadoInvalidoException("\nA quantidade de filhos não pode ser negativa!");

		if (colab.getEtnia() == null || colab.getEtnia().isEmpty())
			throw new DadoInvalidoException("\nEtnia é obrigatório!");

		if (!colab.getEtnia().equals("BRANCO") && !colab.getEtnia().equals("NEGRO") && !colab.getEtnia().equals("PARDO")
				&& !colab.getEtnia().equals("INDÍGENA") && !colab.getEtnia().equals("NÃO INFORMADO"))
			throw new DadoInvalidoException("\nEtnia inválida!");

		if (colab.getCamiseta() == null || colab.getCamiseta().isEmpty())
			throw new DadoInvalidoException("\nTamanho da camiseta é obrigatório!");

		if (!colab.getCamiseta().equals("P") && !colab.getCamiseta().equals("PP") && !colab.getCamiseta().equals("M")
				&& !colab.getCamiseta().equals("G") && !colab.getCamiseta().equals("GG"))
			throw new DadoInvalidoException("\nTamanho de camiseta inválido!");

		if (!contaVal.isAgencia(colab.getAgencia()))
			throw new DadoInvalidoException("\nAgência deve ter 4 números!");

		if (!contaVal.isDigito(colab.getDigitoAgencia()))
			throw new DadoInvalidoException("\nDígito é apenas 1 número!");

		if (!contaVal.isConta(colab.getConta()))
			throw new DadoInvalidoException("\nConta deve ter no mínimo 1 e no máximo 7 números!");

		if (!contaVal.isDigito(colab.getDigitoConta()))
			throw new DadoInvalidoException("\nDígito é apenas 1 número!");

		if (colab.getOrientacao() == null || colab.getOrientacao().isEmpty())
			colab.setOrientacao("NÃO INFORMADO");

		if (colab.getOrientacao().length() > 60)
			throw new DadoInvalidoException("\nOrientação sexual pode ter no máximo 60 caracteres!");

		if (colab.getReligiao() == null || colab.getReligiao().isEmpty())
			colab.setReligiao("NÃO INFORMADO");

		if (colab.getReligiao().length() > 60)
			throw new DadoInvalidoException("\nReligião pode ter no máximo 60 caracteres!");

	}

}// Classe
