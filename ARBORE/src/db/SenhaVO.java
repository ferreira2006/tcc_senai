package db;


public class SenhaVO extends CadastroVO {
	private String nome;
	private String eMail;
	private String usuario;
	private String senha;
	private String dica;
	private boolean supervisor; 

	public String getCodigo() {
		return super.getChave();
	}
	public void setCodigo(String codigo) {
		super.setChave(codigo);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return eMail;
	}

	public void setEmail(String eMail) {
		this.eMail = eMail;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getDica() {
		return dica;
	}

	public void setDica(String dica) {
		this.dica = dica;
	}
	
	public boolean isSupervisor() {
		return supervisor;
	}
	public void setSupervisor(boolean supervisor) {
		this.supervisor = supervisor;
	}

	public String toString() {

		return nome;
	}

}
