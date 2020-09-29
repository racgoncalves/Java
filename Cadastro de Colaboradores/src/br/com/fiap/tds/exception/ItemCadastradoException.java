package br.com.fiap.tds.exception;

@SuppressWarnings("serial")
public class ItemCadastradoException extends Exception {

	public ItemCadastradoException() {
		super("\nJá existe um item deste tipo cadastrado no banco de dados!");
	}

	public ItemCadastradoException(String mensagem) {
		super(mensagem);
	}
	
}
