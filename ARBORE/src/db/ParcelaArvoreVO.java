package db;

public class ParcelaArvoreVO extends CadastroVO {
	
	private int chaveParcela;
	private int chaveArvore;
	private double cap;
	private double dap;
	private double altura;
	private double cas;
	private double distancia;
	private double areaBasal;
	
	public String getCodigo() {
		return super.getChave();
	}
	public void setCodigo(String codigo) {
		super.setChave(codigo);
	}
	
	public int getChaveParcela() {
		return chaveParcela;
	}
	public void setChaveParcela(int chaveParcela) {
		this.chaveParcela = chaveParcela;
	}
	public int getChaveArvore() {
		return chaveArvore;
	}
	public void setChaveArvore(int chaveArvore) {
		this.chaveArvore = chaveArvore;
	}
	public double getCap() {
		return cap;
	}
	public void setCap(double cap) {
		this.cap = cap;
	}
	public double getDap() {
		return dap;
	}
	public void setDap(double dap) {
		this.dap = dap;
	}
	public double getAltura() {
		return altura;
	}
	public void setAltura(double altura) {
		this.altura = altura;
	}
	public double getCas() {
		return cas;
	}
	public void setCas(double cas) {
		this.cas = cas;
	}
	public double getDistancia() {
		return distancia;
	}
	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}
	
	public double getAreaBasal() {
		return areaBasal;
	}
	public void setAreaBasal(double areaBasal) {
		this.areaBasal = areaBasal;
	}
	
	public String toString() { return getChaveParcela() +" - "+ getChaveArvore(); }
}
