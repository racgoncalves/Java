package br.com.fiap.tds.bean;

/**
 * Classe que representa um dependente
 * 
 * @author Rodrigo Chiarelli
 * @version 4.0
 */
public class Dependente {

	private Login login;
	private int codigo;
	private String tipo;
	private String nome;
	private String cpf;
	private String sexo;
	private String dataNascimento;
	private Documento documento;

	public Dependente() {
	}

	public Dependente(Login login, int codigo, String tipo, String nome, String cpf, String sexo, String dataNascimento,
			Documento documento) {

		this.login = login;
		this.codigo = codigo;
		this.tipo = tipo;
		this.nome = nome;
		this.cpf = cpf;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.documento = documento;
	}

	public Dependente(Login login, String tipo, String nome, String cpf, String sexo, String dataNascimento,
			Documento documento) {

		this.login = login;
		this.tipo = tipo;
		this.nome = nome;
		this.cpf = cpf;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.documento = documento;
	}

	// Gets e Sets
	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	// Mostra todos os dados do dependente
	public String toString() {
		return "\nCódigo: " + codigo + "\nTipo: " + tipo + "\nNome: " + nome + "\nCPF: " + mascaraCpf(cpf) + "\nSexo: "
				+ sexo + "\nData de Nascimento: " + dataNascimento + "\nNome do Documento: " + documento.getNome();
	}

	// Máscara do CPF
	public String mascaraCpf(String cpf) {
		return cpf.substring(0, 3).concat(".").concat(cpf.substring(3, 6)).concat(".").concat(cpf.substring(6, 9))
				.concat("-").concat(cpf.substring(9));
	}

	// Pega somente os números do CPF ou do PIS
	public String getNumeroCpfOrPis(String dado) {
		String numero = dado.replaceAll("[^0-9]", "");
		return numero;
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

	// Pega a data que vem do banco de dados
	public String getDataBD(String data) {
		String ano = data.substring(0, 4);
		String mes = data.substring(5, 7);
		String dia = data.substring(8, 10);

		return dia + "/" + mes + "/" + ano;
	}

}// Classe
