package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import resources.Icons;

@SuppressWarnings("serial")
public final class Sobre extends JFrame implements ActionListener{
	private JButton ok;
	private JPanel sul, oeste, centro;
	
	public Sobre() {
		
		setIconImage(Icons.getImage(Icons.FOLHA));
		oeste = new JPanel();
		oeste.setLayout(new GridLayout(0,1));		
		oeste.add(new JLabel(Icons.getImageIcon(Icons.ARVORE)));
		oeste.add(new JLabel("ARBORE", SwingConstants.CENTER));
		oeste.setBorder(BorderFactory.createEtchedBorder());
		
		
		centro = new JPanel();
		centro.setLayout(new GridLayout(0,1));
		centro.add(new JLabel("<html>" +
				"<head><title>ARBORE - Sistema para Determinação" +
				"de Estágio de Regeneração Ambiental.</title></head>" +
				"<body>Elaborado por Marcos Ferreira, sob <br/>" +
				"a orientação de Robert Mauro Lang.<br/><br/>" +
				"Desenvolvido em JAVA com <br/>" +
				"Eclipse SDK Version: 3.4.2 <br/>" +
				"Contato: ferreira2006@bol.com.br" +
				"</body></html>"));
		centro.setBorder(BorderFactory.createEtchedBorder());
		
		
		
		ok = new JButton("OK");
		ok.setMnemonic('O');
		ok.addActionListener(this);
		
		sul = new JPanel();
		sul.setBorder(BorderFactory.createEtchedBorder());		
		
		sul.add(ok);
		
		add(BorderLayout.WEST, oeste);
		add(BorderLayout.CENTER, centro);
		add(BorderLayout.SOUTH, sul);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	//	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	//	setSize(screenSize.width/4, screenSize.height/4);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
	}
	
	public void actionPerformed(ActionEvent arg0) {	
		if (arg0.getSource() == ok) {
			fechar();
		}
	}

	private void fechar() {
		dispose();
	}
	public static void main(String[] args) {
		new Sobre().setVisible(true);
	}
}
