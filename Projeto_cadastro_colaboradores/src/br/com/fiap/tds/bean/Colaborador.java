package br.com.fiap.tds.bean;

/**
 * Classe que representa os dados de um colaborador
 * 
 * @author Rodrigo Chiarelli
 * @version 4.0
 */
public class Colaborador {

	private Login login;
	private String nome;
	private String cpf;
	private String pis;
	private String sexo;
	private String nacionalidade;
	private String naturalidade;
	private String dataNascimento;
	private String estadoCivil;
	private int filhos;
	private String etnia;
	private String camiseta;
	private String agencia;
	private String digitoAgencia;
	private String conta;
	private String digitoConta;
	private String orientacao;
	private String religiao;

	public Colaborador() {
	}

	public Colaborador(Login login, String nome, String cpf, String pis, String sexo, String nacionalidade,
			String naturalidade, String dataNascimento, String estadoCivil, int filhos, String etnia, String camiseta,
			String agencia, String digitoAgencia, String conta, String digitoConta, String orientacao,
			String religiao) {

		this.login = login;
		this.nome = nome;
		this.cpf = cpf;
		this.pis = pis;
		this.sexo = sexo;
		this.nacionalidade = nacionalidade;
		this.naturalidade = naturalidade;
		this.dataNascimento = dataNascimento;
		this.estadoCivil = estadoCivil;
		this.filhos = filhos;
		this.etnia = etnia;
		this.camiseta = camiseta;
		this.agencia = agencia;
		this.digitoAgencia = digitoAgencia;
		this.conta = conta;
		this.digitoConta = digitoConta;
		this.orientacao = orientacao;
		this.religiao = religiao;
	}

	public Colaborador(String nome, String cpf, String pis, String sexo, String nacionalidade, String naturalidade,
			String dataNascimento, String estadoCivil, int filhos, String etnia, String camiseta, String agencia,
			String digitoAgencia, String conta, String digitoConta, String orientacao, String religiao) {

		this.nome = nome;
		this.cpf = cpf;
		this.pis = pis;
		this.sexo = sexo;
		this.nacionalidade = nacionalidade;
		this.naturalidade = naturalidade;
		this.dataNascimento = dataNascimento;
		this.estadoCivil = estadoCivil;
		this.filhos = filhos;
		this.etnia = etnia;
		this.camiseta = camiseta;
		this.agencia = agencia;
		this.digitoAgencia = digitoAgencia;
		this.conta = conta;
		this.digitoConta = digitoConta;
		this.orientacao = orientacao;
		this.religiao = religiao;
	}

	// Gets e Sets
	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		this.pis = pis;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public int getFilhos() {
		return filhos;
	}

	public void setFilhos(int filhos) {
		this.filhos = filhos;
	}

	public String getEtnia() {
		return etnia;
	}

	public void setEtnia(String etnia) {
		this.etnia = etnia;
	}

	public String getCamiseta() {
		return camiseta;
	}

	public void setCamiseta(String camiseta) {
		this.camiseta = camiseta;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getDigitoAgencia() {
		return digitoAgencia;
	}

	public void setDigitoAgencia(String digitoAgencia) {
		this.digitoAgencia = digitoAgencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getDigitoConta() {
		return digitoConta;
	}

	public void setDigitoConta(String digitoConta) {
		this.digitoConta = digitoConta;
	}

	public String getOrientacao() {
		return orientacao;
	}

	public void setOrientacao(String orientacao) {
		this.orientacao = orientacao;
	}

	public String getReligiao() {
		return religiao;
	}

	public void setReligiao(String religiao) {
		this.religiao = religiao;
	}

	// Mostra todos os dados do colaborador
	public String toString() {

		return "\n\nMatrícula: " + login.getMatricula() + "\n1 - Nome: " + nome + "\n2 - CPF: " + mascaraCpf(cpf)
				+ "\n3 - PIS: " + mascaraPis(pis) + "\n4 - Sexo: " + sexo + "\n5 - Nacionalidade: " + nacionalidade
				+ "\n6 - Naturalidade: " + naturalidade + "\n7 - Data de Nascimento: " + dataNascimento
				+ "\n8 - Estado Civil: " + estadoCivil + "\n9 - Número de Filhos: " + filhos + "\n10 - Etnia: " + etnia
				+ "\n11 - Tamanho da Camiseta: " + camiseta + "\n12 - Agencia Bradesco: " + agencia + "-"
				+ digitoAgencia + "\n13 - Conta Bradesco: " + conta + "-" + digitoConta + "\n14 - Orientação Sexual: "
				+ orientacao + "\n15 - Religião: " + religiao;

	}

	// Seleciona o sexo
	public String selecionarSexo(String codigoSexo) {
		switch (codigoSexo) {
		case "F":
			return "FEMININO";
		default:
			return "MASCULINO";
		}
	}

	// Seleciona o estado civil
	public String selecionarEstadoCivil(int codigoEstadoCivil) {
		switch (codigoEstadoCivil) {
		case 1:
			return "SOLTEIRO";
		case 2:
			return "CASADO";
		case 3:
			return "DIVORCIADO";
		default:
			return "VIÚVO";
		}
	}

	// Seleciona a etnia
	public String selecionarEtnia(int codigoEtnia) {
		switch (codigoEtnia) {
		case 1:
			return "BRANCO";
		case 2:
			return "NEGRO";
		case 3:
			return "PARDO";
		case 4:
			return "INDÍGENA";
		default:
			return "NÃO INFORMADO";
		}
	}

	// Seleciona o tamanho da camiseta
	public String selecionarCamiseta(int codigoTamanhoCamiseta) {
		switch (codigoTamanhoCamiseta) {
		case 1:
			return "PP";
		case 2:
			return "P";
		case 3:
			return "M";
		case 4:
			return "G";
		default:
			return "GG";
		}
	}

	// Pega somente os números do CPF ou do PIS
	public String getNumeroCpfOrPis(String dado) {
		String numero = dado.replaceAll("[^0-9]", "");
		return numero;
	}

	// Máscara do CPF
	public String mascaraCpf(String cpf) {
		return cpf.substring(0, 3).concat(".").concat(cpf.substring(3, 6)).concat(".").concat(cpf.substring(6, 9))
				.concat("-").concat(cpf.substring(9));
	}

	// Máscara do PIS
	public String mascaraPis(String pis) {
		return pis.substring(0, 3).concat(".").concat(pis.substring(3, 8)).concat(".").concat(pis.substring(8, 10))
				.concat("-").concat(pis.substring(10));
	}

	// Pega a data que vem do banco de dados
	public String getDataBD(String data) {
		String ano = data.substring(0, 4);
		String mes = data.substring(5, 7);
		String dia = data.substring(8, 10);

		return dia + "/" + mes + "/" + ano;
	}

}// Classe
