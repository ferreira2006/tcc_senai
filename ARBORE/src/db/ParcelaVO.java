package db;

public class ParcelaVO extends CadastroVO {
	
	private String coord1;
	private String coord2;
	private String coord3;
	private String coord4;
	private double area;
	private double areaBasal;
	private int vegetacao;
	private int bioma;	
	private int epifita;
	private int trepadeira;
	private int serrapilheira;
	private String muistasEspeciesPioneiras;
	private String subBosque;
	
	public String getCodigo() {
		return super.getChave();
	}
	
	public void setCodigo(String codigo) {
		super.setChave(codigo);
	}

	public String getCoord1() {
		return coord1;
	}
	
	public void setCoord1(String coord1) {
		this.coord1 = coord1;
	}
	
	public String getCoord2() {
		return coord2;
	}
	
	public void setCoord2(String coord2) {
		this.coord2 = coord2;
	}
	
	public String getCoord3() {
		return coord3;
	}
	
	public void setCoord3(String coord3) {
		this.coord3 = coord3;
	}
	
	public String getCoord4() {
		return coord4;
	}
	
	public void setCoord4(String coord4) {
		this.coord4 = coord4;
	}
	
	public double getArea() {
		return area;
	}
	
	public void setArea(double area) {
		this.area = area;
	}
	
	public int getVegetacao() {
		return vegetacao;
	}
	
	public void setVegetacao(int vegetacao) {
		this.vegetacao = vegetacao;
	}
	
	public int getBioma() {
		return bioma;
	}
	
	public void setBioma(int bioma) {
		this.bioma = bioma;
	}
	
	public double getAreaBasal() {
		return areaBasal;
	}
	
	public void setAreaBasal(double areaBasal) {
		this.areaBasal = areaBasal;
	}
	
	public int getEpifita() {
		return epifita;
	}
	
	public void setEpifita(int epifita) {
		this.epifita = epifita;
	}
	
	public int getTrepadeira() {
		return trepadeira;
	}
	
	public void setTrepadeira(int trepadeira) {
		this.trepadeira = trepadeira;
	}
	
	public int getSerrapilheira() {
		return serrapilheira;
	}
	
	public void setSerrapilheira(int serrapilheira) {
		this.serrapilheira = serrapilheira;
	}
	
	public String isMuistasEspeciesPioneiras() {
		return muistasEspeciesPioneiras;
	}
	
	public void setMuistasEspeciesPioneiras(String muistasEspeciesPioneiras) {
		this.muistasEspeciesPioneiras = muistasEspeciesPioneiras;
	}
	
	public String isSubBosque() {
		return subBosque;
	}
	
	public void setSubBosque(String subBosque) {
		this.subBosque = subBosque;
	}
	
	public String toString() { return super.getChave() + " - " + area; }
}
