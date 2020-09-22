package br.com.fiap.tds.bean;

import java.util.InputMismatchException;

public class Dados {

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

	public Dados() {}

	public Dados(String cpf, String pis, String sexo, String nacionalidade, String naturalidade,
			String dataNascimento, String estadoCivil, int numeroFilhos, String etnia, String tamanhoCamiseta,
			String orientacao, String religiao) {

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
	
	// Mostra todos os dados gravados
		public String toString() {

			return "\n4 - CPF: " + cpf + "\n5 - PIS: " + pis + "\n6 - Sexo: " + sexo
					+ "\n7 - Nacionalidade: " + nacionalidade + "\n8 - Naturalidade: " + naturalidade
					+ "\n9 - Data de Nascimento: " + dataNascimento + "\n10 - Estado Civil: " + estadoCivil
					+ "\n11 - Número de filhos: " + numeroFilhos + "\n12 - Etnia: " + etnia
					+ "\n13 - Tamanho da Camiseta: " + tamanhoCamiseta + "\n14 - Orientação Sexual: " + orientacao
					+ "\n15 - Religião: " + religiao;

		}

	// Carrega sexo
	public String carregaSexo(String codigoSexo) {
		switch (codigoSexo) {
		case "F":
			return "FEMININO";
		case "M":
			return "MASCULINO";
		default:
			return "NÃO INFORMADO";
		}
	}

	// Carrega estado civil
	public String carregaEstadoCivil(int codigoEstadoCivil) {
		switch (codigoEstadoCivil) {
		case 1:
			return "SOLTEIRO";
		case 2:
			return "CASADO";
		case 3:
			return "SEPARADO";
		case 4:
			return "DIVORCIADO";
		default:
			return "VIÚVO";
		}
	}

	// Carrega etnia
	public String carregaEtnia(int codigoEtnia) {
		switch (codigoEtnia) {
		case 1:
			return "BRANCO";
		case 2:
			return "NEGRO";
		case 3:
			return "PARDO";
		default:
			return "INDÍGENA";
		}
	}

	// Carrega tamanho camiseta
	public String carregaTamanhoCamiseta(int codigoTamanhoCamiseta) {
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

	// CPF
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

	// PIS
	public boolean isPis(String pisOrPasep) {

		String n = pisOrPasep;

		int i;
		int digito;
		int coeficiente;
		int soma;
		int encontraDv;
		int dv = Integer.parseInt(String.valueOf(n.charAt(n.length() - 1)));

		soma = 0;
		coeficiente = 2;
		try {
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

	// DATA

	private int listaMes[] = { 31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public int[] getListaMes() {
		return listaMes;
	}

	public void setListaMes(int[] listaMes) {
		this.listaMes = listaMes;
	}

	// Ano
	public boolean validaAno(int ano) {
		if (ano > 1920 && ano < 2006) {
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

	// Mês
	public boolean validaMes(int mes) {
		return mes > 0 && mes <= 12;
	}

	// Dia
	public boolean validaDia(int dia, int mes) {
		if (validaMes(mes)) {
			return dia > 0 && dia <= listaMes[mes - 1];
		} else {
			return false;
		}
	}

	// Monta Data
	public String montaData(int dia, int mes, int ano) {
		String.valueOf(dia);
		String.valueOf(mes);
		String.valueOf(ano);

		return dia + "/" + mes + "/" + ano;
	}

}
