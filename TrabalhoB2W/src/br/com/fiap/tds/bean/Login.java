package br.com.fiap.tds.bean;

public class Login {
	
	private String usuario = "usuario";
	private String senha = "fiap123";
	
	
	//Gets e Sets
	private String getUsuario() {
		return usuario;
	}

	private void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	private String getSenha() {
		return senha;
	}

	private void setSenha(String senha) {
		this.senha = senha;
	}
	
	//Usuário
	public boolean testaUsuario(String usuario) {
		return this.usuario.equals(usuario);
	}
	
	//Senha
	public boolean testaSenha(String senha) {
		return this.senha.equals(senha);
	}

}
