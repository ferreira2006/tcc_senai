package resources;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/** Classe com vários métodos úteis utilizados neste sistema:
 * - criptografaString (String string) 
 * - addStatusFocusListener(Container container, final JLabel label)
 * - validaCPF(String cpf)
 * - validaCNPJ(String cnpj)
 * */
public class Funcoes {
	private FocusListener statusFocusListener;
	private MouseListener statusMouseListener;
	private final int[] PESO_CPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
	private  final int[] PESO_CNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
	

	/** Método public que adiciona função de foco em JTextField e JButttons um container */
	public void addStatusFocusListener(Container container, final JLabel label) {
		for (int index = 0; index < container.getComponentCount(); index++) {
			Component component = container.getComponent(index);
			if (component instanceof JTextField || component instanceof JButton /* || component instanceof JMenuItem*/) {
				if (statusFocusListener == null) {
					statusFocusListener = new FocusListener() {
						public void focusGained(FocusEvent fe) {
							setStatusFromToolTipText(fe.getSource(), label);
						}
						public void focusLost(FocusEvent fe) {
							clearStatus(label);
						}
					};
				}
				if (statusMouseListener == null) {
					statusMouseListener = new MouseAdapter() {
						public void mouseEntered(MouseEvent me) {
							setStatusFromToolTipText(me.getSource(), label);
						}
						public void mouseExited(MouseEvent me) {
							clearStatus(label);
						}
					};
				}
			}
			component.addFocusListener(statusFocusListener);
			component.addMouseListener(statusMouseListener);
		}
	}
	/** Método private auxiliar do addStatusFocusListener() que atualiza o texto do JLabel
	 * informado na assinatura do método com o JToolTipText do objeto passado como
	 *  parametro pelo método */
	private void setStatusFromToolTipText(Object object, JLabel label) {
		String text = " ";
		if (object instanceof JTextField) {
			JTextField tf = (JTextField) object;
			text = tf.getToolTipText();
		}
		if (object instanceof JButton) {
			JButton bt = (JButton) object;
			text = bt.getToolTipText();
		}
	/*	if (object instanceof JMenuItem) {
			JMenuItem item = (JMenuItem) object;
			text = item.getToolTipText();
		}*/
		label.setText(text);
	}
	
	/** Método private auxiliar do addStatusFocusListener() que limpa o conteúdo de
	 * um JLabel */
	private void clearStatus(JLabel label) {
		label.setText(" ");
	}
	
	/** Método public para validação de CPF */
	public boolean validaCPF(String cpf) {
		if ((cpf == null) || (cpf.length() != 11))
			return false;

		Integer digito1 = calcularDigito(cpf.substring(0, 9), PESO_CPF);
		Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, PESO_CPF);
		return cpf.equals(cpf.substring(0, 9) + digito1.toString()
				+ digito2.toString());
	}

	/** Método public para validação de CNPJ */
	public boolean validaCNPJ(String cnpj) {
		if ((cnpj == null) || (cnpj.length() != 14))
			return false;

		Integer digito1 = calcularDigito(cnpj.substring(0, 12), PESO_CNPJ);
		Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, PESO_CNPJ);
		return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
	}
	/** Método private auxiliar aos métodos de validação de CPF e CNPJ */
	private int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}
	// Testes com metodos desta Classe
	public static void main(String[] args) {
		Funcoes fnc = new Funcoes();
		String cpf = "02030201979"; // cpf válido
		String cnpj = "01149953000189"; // cnpj válido
		String cpf2 = "02030201971"; // cpf inválido
		String cnpj2 = "01149953030189"; // cnpj inválido
		
		System.out.println(cpf + " CPF= " + fnc.validaCPF(cpf));
		System.out.println(cnpj + " CNPJ= " + fnc.validaCNPJ(cnpj));
		System.out.println(cpf2 + " CPF= " + fnc.validaCPF(cpf2));
		System.out.println(cnpj2 + " CNPJ= " + fnc.validaCNPJ(cnpj2));
	}
}