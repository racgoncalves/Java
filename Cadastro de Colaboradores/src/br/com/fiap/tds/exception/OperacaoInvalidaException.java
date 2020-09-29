package br.com.fiap.tds.exception;

@SuppressWarnings("serial")
public class OperacaoInvalidaException extends Exception {
	
	public OperacaoInvalidaException (String mensagem) {
		super(mensagem);
	}
	
}
