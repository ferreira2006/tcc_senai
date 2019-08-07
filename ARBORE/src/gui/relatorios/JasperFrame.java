package gui.relatorios;

import gui.BarraDeStatus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.DataInputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import resources.Funcoes;


@SuppressWarnings("serial")
public class JasperFrame extends JInternalFrame {
	
	public static final String JASPER_FOLDER = "resources/relatorios";
	private static ClassLoader loader = JasperFrame.class.getClassLoader();
	protected JButton consultar, fechar;
	protected BarraDeStatus bs = new BarraDeStatus(false);
	protected Funcoes funcoes = new Funcoes();
	JPanel opcoes;
	
	public JasperFrame(String titulo) {
		super(titulo, false, // resizable
				true, // closable
				false, // maximizable
				true);// iconifiable
		
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(tela.width / 2, tela.height / 2);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(238, 233, 191));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		Container container = getContentPane();
		container.add(panel);
		panel.setLayout(new BorderLayout());
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		this.add(BorderLayout.NORTH, addBarraBotao());
		this.add(BorderLayout.SOUTH, bs);
		
		
	}

	private JPanel addBarraBotao() {
		opcoes = new JPanel(new FlowLayout(SwingConstants.RIGHT));
		opcoes.setBorder(BorderFactory.createEtchedBorder());
		consultar = new JButton("Consultar");
		consultar.setMnemonic('C');
		consultar.setToolTipText("Clique para realizar a consulta com os parametros informados");
		fechar = new JButton("Fechar");
		fechar.setMnemonic('F');
		fechar.setToolTipText("Clique para fechar a janela");
		
		opcoes.add(consultar);
		opcoes.add(fechar);
		
		funcoes.addStatusFocusListener(opcoes, bs.jttt);
		
		return opcoes;
	}

	public static DataInputStream getResource(String fileName) {
		DataInputStream dis = new DataInputStream(loader.getResourceAsStream(fileName));
		return dis;
	}
	
	public static DataInputStream getJasper(String fileName) {
		return getResource(JASPER_FOLDER + "/" + fileName + ".jasper"); 
	}
}
