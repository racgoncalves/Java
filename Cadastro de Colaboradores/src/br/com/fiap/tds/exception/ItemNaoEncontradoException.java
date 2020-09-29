package br.com.fiap.tds.exception;

@SuppressWarnings("serial")
public class ItemNaoEncontradoException extends Exception {

	public ItemNaoEncontradoException () {
		super("\nO item n�o foi encontrado!");
	}
	
	public ItemNaoEncontradoException (String mensagem) {
		super(mensagem);
	}
	
}
