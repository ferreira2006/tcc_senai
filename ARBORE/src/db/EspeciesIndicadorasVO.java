package db;

public class EspeciesIndicadorasVO extends CadastroVO {
	
	private String nome;
	private String uf;
	
	
	
	public String getCodigo() {
		return getChave();
	}
	public void setCodigo(String codigo) {
		setChave(codigo);
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getUf() {
		return uf;
	}



	public void setUf(String uf) {
		this.uf = uf;
	}



	public String toString() { return "MunicipioVo.ToString: " + getChave() + '-' + nome + '-' +uf; }
}
