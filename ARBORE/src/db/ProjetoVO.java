package db;

public class ProjetoVO extends CadastroVO {
	
	private String data;
	private String endereco;
	private String numero;
	private String bairro;
	private int municipio;
	private String coord1;
	private String coord2;
	private String coord3;
	private String coord4;
	private double areaTotal;
	private boolean avaliacao;
	private int estagio;
	private double areaDegradada;
	private boolean degradacaoEmApp;
	private int proprietario;
	private int elaborador;
	private String dataExclusao;
	
	
	public String getCodigo() {
		return getChave();
	}
	public void setCodigo(String codigo) {
		setChave(codigo);
	}

	public String getData() {		
		return data;
	}
	public void setData(String dataMySql) {
		this.data = dataMySql;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public int getMunicipio() {
		return municipio;
	}
	public void setMunicipio(int municipio) {
		this.municipio = municipio;
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
	public double getAreaTotal() {
		return areaTotal;
	}
	public void setAreaTotal(double areaTotal) {
		this.areaTotal = areaTotal;
	}
	public boolean isAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(boolean avaliacao) {
		this.avaliacao = avaliacao;
	}
	public int getEstagio() {
		return estagio;
	}
	public void setEstagio(int estagio) {
		this.estagio = estagio;
	}
	public double getAreaDegradada() {
		return areaDegradada;
	}
	public void setAreaDegradada(double areaDegradada) {
		this.areaDegradada = areaDegradada;
	}
	public boolean isDegradacaoEmApp() {
		return degradacaoEmApp;
	}
	public void setDegradacaoEmApp(boolean degradacaoEmApp) {
		this.degradacaoEmApp = degradacaoEmApp;
	}
	public int getProprietario() {
		return proprietario;
	}
	public void setProprietario(int proprietario) {
		this.proprietario = proprietario;
	}
	public int getElaborador() {
		return elaborador;
	}
	public void setElaborador(int elaborador) {
		this.elaborador = elaborador;
	}
	public String getDataExclusao() {
		return dataExclusao;
	}
	public void setDataExclusao(String dataExclusao) {
		this.dataExclusao = dataExclusao;
	}
	public String toString() { return getChave(); }
}
