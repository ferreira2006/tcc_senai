package db;

public class PessoaVO extends CadastroVO{
	
	private String nome;
	private String dataNasc;
	private String rg;
	private String cpf;
	private String endereco;
	private String numero;
	private String bairro;
	private int municipio;
	private String telRes;
	private String telCom;
	private String telCel;
	private String email;
	private boolean elaborador;	
	
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
	public String getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
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
	public String getTelRes() {
		return telRes;
	}
	public void setTelRes(String telRes) {
		this.telRes = telRes;
	}
	public String getTelCom() {
		return telCom;
	}
	public void setTelCom(String telCom) {
		this.telCom = telCom;
	}
	public String getTelCel() {
		return telCel;
	}
	public void setTelCel(String telCel) {
		this.telCel = telCel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isElaborador() {
		return elaborador;
	}
	public void setElaborador(boolean elaborador) {
		this.elaborador = elaborador;
	}
	@Override
	public String toString() {
		return nome +" - RG: "+ rg;
	}
	
	
}
