package br.com.fiap.tds.exception;

@SuppressWarnings("serial")
public class AtualizacaoNaoRealizadaException extends Exception {

	public AtualizacaoNaoRealizadaException () {
		super("\nA atualização não foi realizada!");
	}
	
}
