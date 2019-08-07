package db;

public class EstagioVO extends CadastroVO {
	
	private String descricao;
	
	public String getCodigo() {
		return getChave();
	}
	public void setCodigo(String codigo) {
		setChave(codigo);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String toString() { return getDescricao(); }
}
