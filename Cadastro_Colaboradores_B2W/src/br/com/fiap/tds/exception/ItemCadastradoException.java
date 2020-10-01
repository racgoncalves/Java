package br.com.fiap.tds.exception;

@SuppressWarnings("serial")
public class ItemCadastradoException extends Exception {

	public ItemCadastradoException() {
		super("\nJ� existe um item deste tipo cadastrado no banco de dados!");
	}

	public ItemCadastradoException(String mensagem) {
		super(mensagem);
	}
	
}
