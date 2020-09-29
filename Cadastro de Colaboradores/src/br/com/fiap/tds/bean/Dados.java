package br.com.fiap.tds.bean;

import java.util.InputMismatchException;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Classe que representa os dados gerais de um colaborador
 * 
 * @author Rodrigo Chiarelli
 * @version 1.0
 */
public class Dados {

	private String nome;
	private String cpf;
	private String pis;
	private String sexo;
	private String nacionalidade;
	private String naturalidade;
	private String dataNascimento;
	private String estadoCivil;
	private int numeroFilhos;
	private String etnia;
	private String tamanhoCamiseta;
	private String orientacao;
	private String religiao;

	public Dados() {
	}

	public Dados(String nome, String cpf, String pis, String sexo, String nacionalidade, String naturalidade,
			String dataNascimento, String estadoCivil, int numeroFilhos, String etnia, String tamanhoCamiseta,
			String orientacao, String religiao) {

		this.nome = nome;
		this.cpf = cpf;
		this.pis = pis;
		this.sexo = sexo;
		this.nacionalidade = nacionalidade;
		this.naturalidade = naturalidade;
		this.dataNascimento = dataNascimento;
		this.estadoCivil = estadoCivil;
		this.numeroFilhos = numeroFilhos;
		this.etnia = etnia;
		this.tamanhoCamiseta = tamanhoCamiseta;
		this.orientacao = orientacao;
		this.religiao = religiao;
	}

	// Gets e Sets
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

	public int getNumeroFilhos() {
		return numeroFilhos;
	}

	public void setNumeroFilhos(int numeroFilhos) {
		this.numeroFilhos = numeroFilhos;
	}

	public String getEtnia() {
		return etnia;
	}

	public void setEtnia(String etnia) {
		this.etnia = etnia;
	}

	public String getTamanhoCamiseta() {
		return tamanhoCamiseta;
	}

	public void setTamanhoCamiseta(String tamanhoCamiseta) {
		this.tamanhoCamiseta = tamanhoCamiseta;
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

	// Mostra todos os dados do colaborador (Terminal RH)
	public String toString() {

		return "\n4 - Nome: " + nome + "\n5 - CPF: " + montarCPF(cpf) + "\n6 - PIS: " + montarPIS(pis) + "\n7 - Sexo: "
				+ sexo + "\n8 - Nacionalidade: " + nacionalidade + "\n9 - Naturalidade: " + naturalidade
				+ "\n10 - Data de Nascimento: " + dataNascimento + "\n11 - Estado Civil: " + estadoCivil
				+ "\n12 - Número de Filhos: " + numeroFilhos + "\n13 - Etnia: " + etnia + "\n14 - Tamanho da Camiseta: "
				+ tamanhoCamiseta + "\n15 - Orientação Sexual: " + orientacao + "\n16 - Religião: " + religiao;

	}

	// Mostra todos os dados do colaborador (Terminal Colaborador)
	public String toStringColaborador() {

		return "\n1 - Nome: " + nome + "\n2 - CPF: " + montarCPF(cpf) + "\n3 - PIS: " + montarPIS(pis) + "\n4 - Sexo: "
				+ sexo + "\n5 - Nacionalidade: " + nacionalidade + "\n6 - Naturalidade: " + naturalidade
				+ "\n7 - Data de Nascimento: " + dataNascimento + "\n8 - Estado Civil: " + estadoCivil
				+ "\n9 - Número de Filhos: " + numeroFilhos + "\n10 - Etnia: " + etnia + "\n11 - Tamanho da Camiseta: "
				+ tamanhoCamiseta + "\n12 - Orientação Sexual: " + orientacao + "\n13 - Religião: " + religiao;

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
	public String selecionarTamanhoCamiseta(int codigoTamanhoCamiseta) {
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

	// Monta o CPF
	public String montarCPF(String cpf) {
		return cpf.substring(0, 3).concat(".").concat(cpf.substring(3, 6)).concat(".").concat(cpf.substring(6, 9))
				.concat("-").concat(cpf.substring(9));
	}

	// Valida o CPF
	public boolean isCPF(String CPF) {

		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
				|| CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
				|| CPF.equals("99999999999") || (CPF.length() != 11))
			return false;

		char dig10, dig11;
		int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11)) {
				dig10 = '0';
			}

			else {
				dig10 = (char) (r + 48);
			} // converte no respectivo caractere numerico

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11)) {
				dig11 = '0';
			}

			else {
				dig11 = (char) (r + 48);
			}

			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
				return true;
			}

			else {
				return false;
			}
		} catch (InputMismatchException e) {
			return false;
		}
	}

	// Monta o PIS
	public String montarPIS(String pis) {
		return pis.substring(0, 3).concat(".").concat(pis.substring(3, 8)).concat(".").concat(pis.substring(8, 10))
				.concat("-").concat(pis.substring(10));
	}

	// Valida o PIS
	public boolean isPis(String pis) {

		if (pis.length() != 11)
			return false;

		String n = pis;

		int i;
		int digito;
		int coeficiente;
		int soma;
		int encontraDv;

		try {

			int dv = Integer.parseInt(String.valueOf(n.charAt(n.length() - 1)));

			soma = 0;
			coeficiente = 2;

			for (i = n.length() - 2; i >= 0; i--) {

				digito = Integer.parseInt(String.valueOf(n.charAt(i)));
				soma += digito * coeficiente;
				coeficiente++;
				if (coeficiente > 9) {
					coeficiente = 2;
				}
			}

			encontraDv = 11 - soma % 11;
			if (encontraDv >= 10) {
				encontraDv = 0;
			}
			return dv == encontraDv;

		} catch (InputMismatchException e) {
			return false;
		}
	}

	// Valida a data se for maior de 16 anos e menor de 100 anos
	public boolean validarData(int dia, int mes, int ano) {

		// Pega a data atual
		Date data = new Date(System.currentTimeMillis());

		// Formata a data atual em ano
		SimpleDateFormat formatarAno = new SimpleDateFormat("yyyy");

		// Formata a data atual em mês
		SimpleDateFormat formatarMes = new SimpleDateFormat("MM");

		// Formata a data atual em dia
		SimpleDateFormat formatarDia = new SimpleDateFormat("dd");

		// Transforma os valores em números
		int anoAtual = Integer.parseInt(formatarAno.format(data));
		int mesAtual = Integer.parseInt(formatarMes.format(data));
		int diaAtual = Integer.parseInt(formatarDia.format(data));

		// Compara as datas
		if (ano > (anoAtual - 16))
			return false;
		else if (ano == (anoAtual - 16) && mes > mesAtual)
			return false;
		else if (ano == (anoAtual - 16) && mes == mesAtual && dia > diaAtual)
			return false;
		else if (ano < (anoAtual - 100))
			return false;

		return true;
	}

	// Lista acontendo os dias de cada mês
	private int listaMes[] = { 31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	// Valida o ano e define dias de fevereiro
	public boolean validarAno(int ano) {
		if (ano > 0) {
			if (ano % 400 == 0) {
				this.listaMes[1] = 29;
			} else if (ano % 100 != 0 && ano % 4 == 0) {
				this.listaMes[1] = 29;
			} else {
				this.listaMes[1] = 28;
			}
			return true;
		} else {
			return false;
		}
	}

	// Valida o mês
	public boolean validarMes(int mes) {
		return mes > 0 && mes <= 12;
	}

	// Valida o dia
	public boolean validarDia(int dia, int mes) {
		if (validarMes(mes)) {
			return dia > 0 && dia <= listaMes[mes - 1];
		} else {
			return false;
		}
	}

	// Monta a data
	public String montarData(int dia, int mes, int ano) {
		String.valueOf(dia);
		String.valueOf(mes);
		String.valueOf(ano);

		return dia + "/" + mes + "/" + ano;
	}

	// Arruma a data que vem do servidor
	public String arrumarData(String data) {
		String ano = data.substring(0, 4);
		String mes = data.substring(5, 7);
		String dia = data.substring(8, 10);

		return dia + "/" + mes + "/" + ano;
	}

}
