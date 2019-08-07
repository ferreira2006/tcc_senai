package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import resources.Icons;

@SuppressWarnings("serial")
public class Calculadora extends JInternalFrame implements ActionListener{
	private JButton calcular, limpar, fechar;
	private JTextField capTF, dapTF, areaBasal;
	private JRadioButton cap, dap;
	ButtonGroup bg;
	
	public Calculadora() {
		super("Calculadora", false, // resizable
				true, // closable
				true, // maximizable
				true);// iconifiable
		
		setFrameIcon(Icons.getIcon(Icons.FOLHA));
		
		inicializar();		
		montaCalculadora();
		
		setResizable(false);
		this.getRootPane().setDefaultButton(calcular);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		
	}
	
	private void inicializar() {
		calcular = new JButton("Calcular");
		limpar = new JButton("Limpar");
		fechar = new JButton("Fechar");		
		capTF = new JTextField(6);
		dapTF = new JTextField(6);
		dapTF.setEditable(false);
		areaBasal = new JTextField(6);
		bg = new ButtonGroup();
		cap = new JRadioButton("CAP", true);
		cap.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent arg0) {
				if (cap.isSelected()) {
					dapTF.setEditable(false);
					capTF.setEditable(true);
					capTF.requestFocus();
					limpar();
				}else{
					dapTF.setEditable(true);
					capTF.setEditable(false);
					dapTF.requestFocus();
				}
			}
			
		});
		
		dap = new JRadioButton("DAP");		
		bg.add(cap); bg.add(dap);
		
	}
	
	private void montaCalculadora() {		
		this.add(BorderLayout.CENTER, Formulario());
		this.add(BorderLayout.SOUTH, montaBarraBotoes());		
	}

	private JPanel Formulario() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createEtchedBorder());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5);
		
		gbc.gridx = 1; gbc.gridy = 1;
		panel.add(new JLabel("Calcular por:"), gbc);
		gbc.gridx = 2; gbc.gridy = 1;
		panel.add(cap, gbc);
		gbc.gridx = 3; gbc.gridy = 1;
		panel.add(dap, gbc);
		
		gbc.gridx = 1; gbc.gridy = 2;
		panel.add(new JLabel("CAP:"), gbc);
		gbc.gridx = 2; gbc.gridy = 2;
		panel.add(capTF, gbc);
		gbc.gridx = 3; gbc.gridy = 2;
		panel.add(new JLabel("cm"), gbc);
		
		gbc.gridx = 1; gbc.gridy = 3;		
		panel.add(new JLabel("DAP:"), gbc);
		gbc.gridx = 2; gbc.gridy = 3;
		panel.add(dapTF, gbc);
		gbc.gridx = 3; gbc.gridy = 3;
		panel.add(new JLabel("cm"), gbc);
		
		gbc.gridx = 1; gbc.gridy = 4;
		panel.add(new JLabel("Área Basal: ", JLabel.RIGHT), gbc);
	
		gbc.gridx = 2; gbc.gridy = 4;		
		panel.add(areaBasal, gbc);
		gbc.gridx = 3; gbc.gridy = 4;
		panel.add(new JLabel("m²"), gbc);

		capTF.requestFocus();
		
		return panel;
	}

	private JPanel montaBarraBotoes() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEtchedBorder());
				
		calcular.setMnemonic('C');
		calcular.addActionListener(this);
		
		limpar.setMnemonic('L');
		limpar.addActionListener(this);
		
		fechar.setMnemonic('F');
		fechar.addActionListener(this);
		
		
		panel.add(calcular);
		panel.add(limpar);
		panel.add(fechar);
		
		return panel;
	}

	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == calcular) {
			try {
				calcular();
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "O Campo só aceita números");
				capTF.requestFocus();
			}
			
		}
		
		if (ae.getSource() == limpar) {
			limpar();			
		}
		
		if (ae.getSource() == fechar) {
			dispose();
		}
	}

	private void limpar() {
		capTF.setText("");
		dapTF.setText("");
		areaBasal.setText("");
		cap.setSelected(true);
		capTF.requestFocus();
		
	}

	private void calcular() {

		if (cap.isSelected()) {
			if (!capTF.getText().equals("") && !capTF.getText().equals("0")) {
				dapTF.setText("" + calculaDap(Double.parseDouble(capTF.getText())));
				areaBasal.setText("" + calculaAB(Double.parseDouble(dapTF.getText())));
			}
		}

		if (dap.isSelected()) {
			if (!dapTF.getText().equals("") && !dapTF.getText().equals("0")) {
				areaBasal.setText("" + calculaAB(Double.parseDouble(dapTF.getText())));
				capTF.setText("" + calculaCap(Double.parseDouble(dapTF.getText())));
			}
		}
	}
	
	private double calculaDap(double cap) {
		double resultado;
		
		resultado = cap / Math.PI;
		
		return resultado;
	}
	
	private double calculaAB(double dap) {
		double resultado;
		
		resultado = 0.8 * (dap * dap);
		
		return resultado;
	}
	
	private double calculaCap(double dap) {
		double resultado;
		
		resultado = dap * Math.PI;
		
		return resultado;
	}
	
	public static void main(String[] args) {
		new Calculadora().setVisible(true);
	}

}
