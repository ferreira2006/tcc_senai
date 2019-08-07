package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.UIManager;

import db.BD;

import resources.AlteraTipoCursor;
import resources.Funcoes;
import resources.Icons;

@SuppressWarnings("serial")
public class JanelaPrincipal extends JFrame {
	private static JDesktopPane desktopPane;
	private Image fundo;
//	BarraDeMenuPrincipal bmp;
	protected BarraDeMenu bmp;
	private Funcoes funcoes = new Funcoes();
	private BarraDeStatus bs = new BarraDeStatus(true);
	private BarraDeBotaoPrincipal bbp;

	public JanelaPrincipal(final String titulo) {
		super(titulo);
		int inset = 50;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// setSize(tela.width, tela.height);// o form é redimesionado para o
		// tamanho da tela
		setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height	- inset * 2);
		setIconImage(Icons.getImage(Icons.JAVA));

		try {
			fundo = ImageIO.read(new URL(getClass().getResource("fundo.png"), "fundo.png"));
	
		} catch (Exception e) {
			e.printStackTrace();
		}

		setDesktopPane(new JDesktopPane() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (fundo != null) {
					g.drawImage(fundo, getWidth() / 4, getHeight() / 4, this.getWidth() / 2, this.getHeight() / 2, this);
					g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, getWidth() / 60));
					g.drawString(titulo.substring(0, 78), (this.getWidth() / 5), (this.getHeight() / 6)); // adiciona escrita à tela
					g.drawRect(getWidth() / 4, getHeight() / 4, this.getWidth() / 2, this.getHeight() / 2);
				} else {
					// g.drawString("Erro ao carregar a imagem de fundo",
					// getWidth()/2, getHeight()/2);
				}
			}
		});
		
		bmp = new BarraDeMenu(getDesktopPane());
		setJMenuBar(bmp);
		
		bbp = new BarraDeBotaoPrincipal(bmp);
		
		add(BorderLayout.NORTH, bbp);
		add(BorderLayout.CENTER, getDesktopPane());		
		add(BorderLayout.SOUTH, bs);
		
		funcoes.addStatusFocusListener(bbp, bs.jttt);
		new AlteraTipoCursor(bmp);
		
		// setResizable(false);

		setExtendedState(JFrame.MAXIMIZED_BOTH);// inicia o frame maximizado
		getDesktopPane().setBackground(new Color(162, 205, 90)); // cor RBG
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // Não deixa fechar o form pelo botao X
	//	setSize(800, 700); // TODO === REMOVER
		addWindowListener(new java.awt.event.WindowAdapter() {// Classe interna anonima
			public void windowClosing(WindowEvent e) {
				if (e.getID() == WindowEvent.WINDOW_CLOSING) { // fechando a janela
					new BarraDeMenu().sair(0);
				}
			}
		});

		
	}

	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			BD.start();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	//	new JanelaPrincipal("ARBOR - Software para  determinação de estágio sucessional da vegetação da Mata Atlântica - Intância de testes").setVisible(true);
		
	}

	public static void setDesktopPane(JDesktopPane desktopPane) {
		JanelaPrincipal.desktopPane = desktopPane;
	}

	public static JDesktopPane getDesktopPane() {
		return desktopPane;
	}
}