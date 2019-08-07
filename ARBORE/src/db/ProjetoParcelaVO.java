package db;

public class ProjetoParcelaVO extends CadastroVO {
	
	private String chaveProjeto;
	private String chaveParcela;

	public String getChaveProjeto() {
		return chaveProjeto;
	}

	public void setChaveProjeto(String projeto) {
		this.chaveProjeto = projeto;
	}

	public String getChaveParcela() {
		return chaveParcela;
	}

	public void setChaveParcela(String parcela) {
		this.chaveParcela = parcela;
	}

	public String toString() { return getChave(); }
}
