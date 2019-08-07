package db;

public class ArvoreVO extends CadastroVO {
	
	private String nomeComum;
	private String nomeCientifico;
	private String familia;	
	
	public String getNomeComum() {
		return nomeComum;
	}

	public void setNomeComum(String nomeComum) {
		this.nomeComum = nomeComum;
	}

	public String getNomeCientifico() {
		return nomeCientifico;
	}

	public void setNomeCientifico(String nomeCientifico) {
		this.nomeCientifico = nomeCientifico;
	}

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public String toString() { return nomeComum; }
}
