package resources;

public class CNPJ extends CRF {
	public CNPJ() { super(); }
	public CNPJ(String codigo) {
		super(codigo);
	}

	private final int[] PESO_CNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 }; // 13
	
	/** Método public para validação de CNPJ */
	public boolean valida() {
		if ((codigo == null) || (codigo.length() != 14))
			return false;

		Integer digito1 = calcularDigito(codigo.substring(0, 12), PESO_CNPJ);
		Integer digito2 = calcularDigito(codigo.substring(0, 12) + digito1, PESO_CNPJ);
		return codigo.equals(codigo.substring(0, 12) + digito1.toString() + digito2.toString());
	}
	
	public static void main(String[] args) {
		CNPJ cnpj = new CNPJ("07370553000185");
		if (cnpj.valida()) {
			System.out.println("CNPJ ok");
		} else {
			System.err.println("ERRO NO CNPJ");
		}

		CNPJ cnpj2 = new CNPJ("07370553000184");
		if (cnpj2.valida()) {
			System.out.println("CNPJ ok");
		} else {
			System.err.println("ERRO NO CNPJ");
		}
	}
}
