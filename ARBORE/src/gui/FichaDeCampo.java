package gui;

public class FichaDeCampo {

	private String data;
	private String local;
	private String areaTotal;
	private String municipio;
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getAreaTotal() {
		return areaTotal;
	}

	public void setAreaTotal(String areaTotal) {
		this.areaTotal = areaTotal;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String toString() {
		return getLocal()+" - "+getMunicipio();
	}
	

	/**
	 * Construtor
	 */
	public FichaDeCampo(String data, String local, String areaTotal, String municipio) {
		
		this.data = data;
		this.local = local;
		this.areaTotal = areaTotal;
		this.municipio = municipio;
		
	}

	
}
