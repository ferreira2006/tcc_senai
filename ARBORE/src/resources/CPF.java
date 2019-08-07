package resources;

public class CPF extends CRF {
	
	public CPF() { super(); }
	public CPF(String codigo) {
		super(codigo);
	}

	private final int[] PESO_CPF  = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 }; // 10
	
	/** Método public para validação de CPF */
	public boolean valida() {
		if ((codigo == null) || (codigo.length() != 11)) return false;

		Integer digito1 = calcularDigito(codigo.substring(0, 9), PESO_CPF);
		Integer digito2 = calcularDigito(codigo.substring(0, 9) + digito1, PESO_CPF);
		return codigo.equals(codigo.substring(0, 9) + digito1.toString()
				+ digito2.toString());
	}
	public String digito() {
		if ((codigo == null) || (codigo.length() != 9)) return null;

		Integer digito1 = calcularDigito(codigo.substring(0, 9), PESO_CPF);
		Integer digito2 = calcularDigito(codigo.substring(0, 9) + digito1, PESO_CPF);
		return ""+digito1+digito2;
	}
	
	public static void main(String[] args) {
		CPF cpf = new CPF("73656453934");
		if (cpf.valida()) {
			System.out.println("CPF ok");
		} else {
			System.err.println("ERRO NO CPF");
		}
		
		CPF cpf2 = new CPF();
		System.out.println(cpf2.valida("02502515904"));
		
		CPF cpf3 = new CPF("025025159");
		System.out.println("025025159 "+cpf3.digito());
	}
}
