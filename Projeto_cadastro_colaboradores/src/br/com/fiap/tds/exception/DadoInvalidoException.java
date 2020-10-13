package br.com.fiap.tds.exception;

@SuppressWarnings("serial")
public class DadoInvalidoException extends Exception {

	public DadoInvalidoException() {
		super("\nDado inválido!");
	}
	
	public DadoInvalidoException(String mensagem) {
		super(mensagem);
	}
	
}