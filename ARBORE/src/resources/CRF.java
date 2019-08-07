package resources;

public abstract class CRF {
	protected String codigo;
	
	public CRF() {}
	public CRF(String codigo) {
		this.codigo = codigo;
	}
	
	public abstract boolean valida();
	/** Método public para validação de CPF ou CNPJ */
	public boolean valida(String codigo) {
		this.codigo = codigo;
		return valida();
	}	
	
	/** Método private auxiliar aos métodos de validação de CPF e CNPJ */
	protected int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}
}
