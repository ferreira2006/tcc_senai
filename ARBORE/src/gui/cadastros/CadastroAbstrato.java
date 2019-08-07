package gui.cadastros;

import gui.BarraDeBotoesDeCadastro;
import gui.BarraDeStatus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import resources.Funcoes;

@SuppressWarnings("serial")
public abstract class CadastroAbstrato extends JInternalFrame implements ActionListener, KeyListener{
	private Color corPadrao = new Color(162, 205, 90);
	protected BarraDeBotoesDeCadastro bbc;
	protected BarraDeStatus bs;
	
	public CadastroAbstrato(String titulo) {
		super(titulo, false, // Redimensionar
				true, // fechar
				true, // maximizar
				true);// minimizar

		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(tela.width / 2, tela.height / 2);
	//	pack();
		JPanel panel = new JPanel();
		panel.setBackground(new Color(238, 233, 191));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		Container container = getContentPane();
		container.add(panel);
		panel.setLayout(new BorderLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(true);
		
        bbc = new BarraDeBotoesDeCadastro(this);
        bs = new BarraDeStatus(false);
        
        Funcoes fnc = new Funcoes();
        fnc.addStatusFocusListener(bbc, bs.jttt);
        
		add(BorderLayout.NORTH, bbc);
		add(BorderLayout.SOUTH, bs);
		
	//	focarChave();
	}
	
	
	protected abstract boolean validarDados(Component c);
	protected abstract boolean lerDadosDoBanco();
	protected abstract void novoCadastro();
	protected abstract void salvarDadosNoBanco();
	protected abstract void mostrarNaTela();
	protected abstract void excluirDoBanco();
	protected abstract void limparTela();
	protected abstract void avancaTabela();
	protected abstract void retroTabela();
	
	private Component chaveComponent;
	protected Component getChaveComponent() { return chaveComponent; }
	protected void setChaveComponent(Component component) { this.chaveComponent = component; }
	
	protected void focarChave() {
	//	chaveComponent.setBackground(new Color(202, 255, 112));
		chaveComponent.requestFocus(); 
	}
	private FocusListener statusFocusListener;
	public void addAlteraFoco(final JTextField campo){
		
			statusFocusListener = new FocusListener() {
				public void focusGained(FocusEvent fe) {
					campo.setBackground(new Color(202, 255, 112));
				}
				public void focusLost(FocusEvent fe) {
					campo.setBackground(Color.WHITE);
				}
			};
		
		campo.addFocusListener(statusFocusListener);
	}
	
	public void addFocusListnerContainer(Container container) {
		for (int index = 0; index < container.getComponentCount(); index++) {
			final Component component = container.getComponent(index);
			if (component instanceof JTextField) {
					statusFocusListener = new FocusListener() {
						public void focusGained(FocusEvent fe) {
							component.setBackground(new Color(202, 255, 112));
							
							((JTextField) component).selectAll();
							((JTextField) component).setSelectionColor(Color.WHITE);
							((JTextField) component).setSelectedTextColor(Color.BLACK);
						}
						public void focusLost(FocusEvent fe) {
							component.setBackground(Color.WHITE);
						}
					};
			}
			component.addFocusListener(statusFocusListener);
		}
	}

	public boolean mensagem(Component parent, String texto, Component foco) {
		Toolkit.getDefaultToolkit().beep();
		JOptionPane.showMessageDialog(parent, texto, "Atenção", JOptionPane.INFORMATION_MESSAGE);
		if (foco!=null) foco.requestFocus();
		return true;
	}
	
	public boolean mensagemNovoCadastro(JInternalFrame frame) {
		return mensagem(frame, "Cadastro NOVO!", null);
	}
	
	public boolean mensagemErroCpf(JInternalFrame frame, JComponent field) {
		return !mensagem(frame, "<html><b>CPF Inválido - Verifique!</b></html>", field);
	}
	
	public boolean mensagemErroInformeCampo(JInternalFrame frame, JComponent field) {
		return !mensagem(frame, field.getToolTipText(), field);
	}
	
	public boolean mensagemSenhaNaoConfere(JInternalFrame frame, JComponent field) {
		return !mensagem(frame, "As Senhas não são iguais", field);
	}
	
	public boolean mensagemCadastroGravado(JInternalFrame frame) {
		return mensagem(frame, "<html><b>Cadastro Gravado com Sucesso</b></html>", chaveComponent);
	}

	public boolean mensagemCadastroExcluido(JInternalFrame frame) {
		return mensagem(frame, "<html><b>Cadastro Excluido com Sucesso</b></html>", chaveComponent);
	}

	public boolean perguntaExcluiCadastro(JInternalFrame frame, String codigo) {
		Object[] options = {"Excluir", "Cancelar"}; 
		Toolkit.getDefaultToolkit().beep();
		boolean result = JOptionPane.showOptionDialog(frame, "<html><b>Deseja excluir o cadastro "+codigo+" ?</b></html>", "Atenção", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]) == 0; 
		return result;
	}

	public boolean erroBancoDeDados(JInternalFrame frame) {
		return mensagem(frame, "<html><b>Ocorreu um erro no banco de dados. Contate o supervisor</b></html>", null);
	}
	
	public void actionPerformed(ActionEvent ae) {
		String ac = ae.getActionCommand();
			 if (ac==BarraDeBotoesDeCadastro.NOVO   ) {				 
				 novoCadastro();
			 }
		else if (ac==BarraDeBotoesDeCadastro.SALVAR   ) {
			salvarDadosNoBanco();
		}
//		else if (ac==BarraDeBotoesDeCadastro.LIMPAR   ) {
//			limparTela(); 
//		}
		else if (ac==BarraDeBotoesDeCadastro.EXCLUIR  ) {
			excluirDoBanco(); 
		}
	//	else if (ac==BarraDeBotaoCadastro.RELATORIO) imprimirDados(); 
		else if (ac==BarraDeBotoesDeCadastro.FECHAR   ) {
			fechar();
		}
		else if (ac==BarraDeBotoesDeCadastro.ANTERIOR   ){
			retroTabela();
		}
		else if (ac==BarraDeBotoesDeCadastro.PROXIMO   ) {
			avancaTabela();
		}
	}
	
	public void fechar() {
		this.setVisible(false);
		this.dispose();
		//	this = null;
	}
	
	public void setCorPadrao(Color corPadrao) {
		this.corPadrao = corPadrao;
	}
	
	public Color getCorPadrao() {
		return corPadrao;
	}
	// Método adicionar(), adiciona componentes com GridBagLayout
	public void adicionar(Container container, String rotulo, String tooltip,
			JComponent componente, int x1, int x2, int y, GridBagConstraints gbc) {
			gbc.insets = new Insets(5,5,5,5);
			gbc.anchor = GridBagConstraints.EAST;
			gbc.gridx = x1;	gbc.gridy = y;
			container.add(new JLabel(rotulo, JLabel.RIGHT), gbc);
			gbc.gridx = x2;	gbc.gridy = y;
			gbc.anchor = GridBagConstraints.WEST;
			componente.setToolTipText(tooltip);
			container.add(componente, gbc);
	}
	protected void personalizaAba(JTabbedPane jtp){
		jtp.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		jtp.setForeground(new Color(162, 205, 90));		
	}
	public void keyPressed(KeyEvent ke) {
		if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
			Component c = ke.getComponent();
			if (!(c instanceof JButton)) {
				if (validarDados(c)) {
		//			if (c == chaveComponent) lerDadosDoBanco();
					c.transferFocus();
				}
			}
		}
	}
	

	public void keyTyped(KeyEvent ke) {}

	public void keyReleased(KeyEvent ke) {}
	
	protected Dimension dimensaoPadrao(JComponent componente){
		return new Dimension(componente.getWidth() - 50, componente.getHeight() - 50);
	}
	
}
