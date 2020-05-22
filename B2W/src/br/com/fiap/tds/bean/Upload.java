package br.com.fiap.tds.bean;

public class Upload {
	
	private String docRg;
	private String docTrabalho;
	private String docResidencia;
	private String docEleitor;
	private String docEscolaridade;
	private String docReservista;
	private String docConjuge;
	private String docFilhos;
	private String docPais;
	
	public Upload () {}
	
	//Gets e Sets
	public String getDocRg() {
		return docRg;
	}

	public void setDocRg(String docRg) {
		this.docRg = docRg;
	}

	public String getDocTrabalho() {
		return docTrabalho;
	}

	public void setDocTrabalho(String docTrabalho) {
		this.docTrabalho = docTrabalho;
	}

	public String getDocResidencia() {
		return docResidencia;
	}

	public void setDocResidencia(String docResidencia) {
		this.docResidencia = docResidencia;
	}

	public String getDocEleitor() {
		return docEleitor;
	}

	public void setDocEleitor(String docEleitor) {
		this.docEleitor = docEleitor;
	}

	public String getDocEscolaridade() {
		return docEscolaridade;
	}

	public void setDocEscolaridade(String docEscolaridade) {
		this.docEscolaridade = docEscolaridade;
	}

	public String getDocReservista() {
		return docReservista;
	}

	public void setDocReservista(String docReservista) {
		this.docReservista = docReservista;
	}

	public String getDocConjuge() {
		return docConjuge;
	}

	public void setDocConjuge(String docConjuge) {
		this.docConjuge = docConjuge;
	}

	public String getDocFilhos() {
		return docFilhos;
	}

	public void setDocFilhos(String docFilhos) {
		this.docFilhos = docFilhos;
	}

	public String getDocPais() {
		return docPais;
	}

	public void setDocPais(String docPais) {
		this.docPais = docPais;
	}

	//Zera todos os uploads
	public void zeraUpload() {
		this.docRg = "";
		this.docTrabalho = "";
		this.docResidencia = "";
		this.docEleitor = "";
		this.docEscolaridade = "";
		this.docReservista = "";
		this.docConjuge = "";
		this.docFilhos = "";
		this.docPais = "";
	}

	//Mostra todos os dados gravados
	public String toString() {
		
		//Casado e com filhos
		if(!this.docConjuge.isEmpty() && !this.docFilhos.isEmpty()) {
			docFilhos = docFilhos.replace(";","\n                                       ");
			//Homem
			if(!this.docReservista.isEmpty()) {
				//Pais não dependentes
				if(this.docPais.isEmpty()) {
					return "\nRG: " + docRg + "\nCarteira de Trabalho: " + docTrabalho + "\nComprovante de Residência: " + docResidencia +  "\nTítulo de Eleitor: " + docEleitor + "\nComprovante de Escolaridade: " + docEscolaridade + "\nCertificado de Reservista: " + docReservista + "\nCertidão de Nascimento do Cônjuge: " + docConjuge + "\nCertidão de Nascimento do(s) Filho(s): " + docFilhos;
				}
				//Pais dependentes
				else {
					return "\nRG: " + docRg + "\nCarteira de Trabalho: " + docTrabalho + "\nComprovante de Residência: " + docResidencia +  "\nTítulo de Eleitor: " + docEleitor + "\nComprovante de Escolaridade: " + docEscolaridade + "\nCertificado de Reservista: " + docReservista + "\nCertidão de Nascimento do Cônjuge: " + docConjuge + "\nCertidão de Nascimento do(s) Filho(s): " + docFilhos + "\nRG e CPF do Pai e/ou da Mãe: " + docPais;
				}
			}
			//Não Homem
			else {
				//Pais não dependentes
				if(this.docPais.isEmpty()) {
					return "\nRG: " + docRg + "\nCarteira de Trabalho: " + docTrabalho + "\nComprovante de Residência: " + docResidencia +  "\nTítulo de Eleitor: " + docEleitor + "\nComprovante de Escolaridade: " + docEscolaridade + "\nCertidão de Nascimento do Cônjuge: " + docConjuge + "\nCertidão de Nascimento do(s) Filho(s): " + docFilhos;
				}
				//Pais dependentes
				else {
					return "\nRG: " + docRg + "\nCarteira de Trabalho: " + docTrabalho + "\nComprovante de Residência: " + docResidencia +  "\nTítulo de Eleitor: " + docEleitor + "\nComprovante de Escolaridade: " + docEscolaridade + "\nCertidão de Nascimento do Cônjuge: " + docConjuge + "\nCertidão de Nascimento do(s) Filho(s): " + docFilhos + "\nRG e CPF do Pai e/ou da Mãe: " + docPais;
				}
			}
		}
		
		//Não casado e com filhos
		else if(this.docConjuge.isEmpty() && !this.docFilhos.isEmpty()) {
			docFilhos = docFilhos.replace(";","\n                                       ");
			//Homem
			if(!this.docReservista.isEmpty()) {
				//Pais não dependentes
				if(this.docPais.isEmpty()) {
					return "\nRG: " + docRg + "\nCarteira de Trabalho: " + docTrabalho + "\nComprovante de Residência: " + docResidencia +  "\nTítulo de Eleitor: " + docEleitor + "\nComprovante de Escolaridade: " + docEscolaridade + "\nCertificado de Reservista: " + docReservista + "\nCertidão de Nascimento do(s) Filho(s): " + docFilhos;
				}
				//Pais dependentes
				else {
					return "\nRG: " + docRg + "\nCarteira de Trabalho: " + docTrabalho + "\nComprovante de Residência: " + docResidencia +  "\nTítulo de Eleitor: " + docEleitor + "\nComprovante de Escolaridade: " + docEscolaridade + "\nCertificado de Reservista: " + docReservista + "\nCertidão de Nascimento do(s) Filho(s): " + docFilhos + "\nRG e CPF do Pai e/ou da Mãe: " + docPais;
				}
			}
			//Não Homem
			else {
				//Pais não dependentes
				if(this.docPais.isEmpty()) {
					return "\nRG: " + docRg + "\nCarteira de Trabalho: " + docTrabalho + "\nComprovante de Residência: " + docResidencia +  "\nTítulo de Eleitor: " + docEleitor + "\nComprovante de Escolaridade: " + docEscolaridade + "\nCertidão de Nascimento do(s) Filho(s): " + docFilhos;
				}
				//Pais dependentes
				else {
					return "\nRG: " + docRg + "\nCarteira de Trabalho: " + docTrabalho + "\nComprovante de Residência: " + docResidencia +  "\nTítulo de Eleitor: " + docEleitor + "\nComprovante de Escolaridade: " + docEscolaridade + "\nCertidão de Nascimento do(s) Filho(s): " + docFilhos + "\nRG e CPF do Pai e/ou da Mãe: " + docPais;
				}
			}
		}
		
		//Casado e sem filhos
		else if(!this.docConjuge.isEmpty() && this.docFilhos.isEmpty()) {
			//Homem
			if(!this.docReservista.isEmpty()) {
				//Pais não dependentes
				if(this.docPais.isEmpty()) {
					return "\nRG: " + docRg + "\nCarteira de Trabalho: " + docTrabalho + "\nComprovante de Residência: " + docResidencia +  "\nTítulo de Eleitor: " + docEleitor + "\nComprovante de Escolaridade: " + docEscolaridade + "\nCertificado de Reservista: " + docReservista + docEscolaridade + "\nCertidão de Nascimento do Cônjuge: " + docConjuge;
				}
				//Pais dependentes
				else {
					return "\nRG: " + docRg + "\nCarteira de Trabalho: " + docTrabalho + "\nComprovante de Residência: " + docResidencia +  "\nTítulo de Eleitor: " + docEleitor + "\nComprovante de Escolaridade: " + docEscolaridade + "\nCertificado de Reservista: " + docReservista + docEscolaridade + "\nCertidão de Nascimento do Cônjuge: " + docConjuge + "\nRG e CPF do Pai e/ou da Mãe: " + docPais;
				}
			}
			//Não Homem
			else {
				//Pais não dependentes
				if(this.docPais.isEmpty()) {
					return "\nRG: " + docRg + "\nCarteira de Trabalho: " + docTrabalho + "\nComprovante de Residência: " + docResidencia +  "\nTítulo de Eleitor: " + docEleitor + "\nComprovante de Escolaridade: " + docEscolaridade + "\nCertidão de Nascimento do Cônjuge: " + docConjuge;
				}
				//Pais dependentes
				else {
					return "\nRG: " + docRg + "\nCarteira de Trabalho: " + docTrabalho + "\nComprovante de Residência: " + docResidencia +  "\nTítulo de Eleitor: " + docEleitor + "\nComprovante de Escolaridade: " + docEscolaridade + "\nCertidão de Nascimento do Cônjuge: " + docConjuge + "\nRG e CPF do Pai e/ou da Mãe: " + docPais;
				}
			}
		}

		//Nâo casado e sem filhos
		//Homem
		else if(!this.docReservista.isEmpty()) {
			//Pais não dependentes
			if(this.docPais.isEmpty()) {
				return "\nRG: " + docRg + "\nCarteira de Trabalho: " + docTrabalho + "\nComprovante de Residência: " + docResidencia +  "\nTítulo de Eleitor: " + docEleitor + "\nComprovante de Escolaridade: " + docEscolaridade + "\nCertificado de Reservista: " + docReservista;
			}
			//Pais dependentes
			else {
				return "\nRG: " + docRg + "\nCarteira de Trabalho: " + docTrabalho + "\nComprovante de Residência: " + docResidencia +  "\nTítulo de Eleitor: " + docEleitor + "\nComprovante de Escolaridade: " + docEscolaridade + "\nCertificado de Reservista: " + docReservista + "\nRG e CPF do Pai e/ou da Mãe: " + docPais;
			}
		}
		//Não Homem
		else {
			//Pais não dependentes
			if(this.docPais.isEmpty()) {
				return "\nRG: " + docRg + "\nCarteira de Trabalho: " + docTrabalho + "\nComprovante de Residência: " + docResidencia +  "\nTítulo de Eleitor: " + docEleitor + "\nComprovante de Escolaridade: " + docEscolaridade;
			}
			//Pais dependentes
			else {
				return "\nRG: " + docRg + "\nCarteira de Trabalho: " + docTrabalho + "\nComprovante de Residência: " + docResidencia +  "\nTítulo de Eleitor: " + docEleitor + "\nComprovante de Escolaridade: " + docEscolaridade + "\nRG e CPF do Pai e/ou da Mãe: " + docPais;
			}
		}
	}
}
