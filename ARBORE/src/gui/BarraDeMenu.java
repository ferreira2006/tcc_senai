package gui;

import gui.cadastros.CadastroDeArvore;
import gui.cadastros.CadastroDeParcela;
import gui.cadastros.CadastroDePessoa;
import gui.cadastros.CadastroDeProjeto;
import gui.cadastros.CadastroDeUsuario;
import gui.cadastros.FimDoProjeto;
import gui.relatorios.JasperFrame;
import gui.relatorios.RelatorioFrame;
import help.HelpAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.io.DataInputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import resources.Icons;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import db.BD;

@SuppressWarnings("serial")
public class BarraDeMenu extends JMenuBar implements ActionListener {
	private static JDesktopPane desktopPane;
	private JMenu projeto, ferramentas, relatorio, cadastro, ajuda;
	private JMenuItem novo, finalizar, sair; // itens do menu Arquivo
	private JMenuItem calculadora, fichaCampo, especiesIndicadoras; // menu
																	// ferramentas
	private JMenuItem relatorios; // itens do menu Relatório
	private JMenuItem pessoas, parcelas; // itens do Cadastro
	static JMenuItem usuarios = new JMenuItem("Cadastro de Usuários");
	private JMenuItem arvores;
	private JMenuItem topicos, sobre; // itens do Ajuda
	public final String RELATORIO = "especiesIndicadoras";
	private Map<Object, Object> parametros;

	public BarraDeMenu() {
		montaBarra();
	}

	public BarraDeMenu(JDesktopPane jdp) {
		BarraDeMenu.desktopPane = jdp;
		montaBarra();
	}

	private void montaBarra() {
		// inicializa os menus
		projeto = new JMenu("Projetos");
		ferramentas = new JMenu("Ferramentas");
		relatorio = new JMenu("Relatórios");
		cadastro = new JMenu("Cadastros");
		ajuda = new JMenu("Ajuda");

		// monta menu Projetos
		novo = new JMenuItem("Novo Projeto", null); // null é icone
		novo.setToolTipText("Cria um novo projeto");
		novo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2,
				InputEvent.CTRL_MASK));
		novo.addActionListener(this);
		novo.setMnemonic(KeyEvent.VK_N);
		finalizar = new JMenuItem("Finalizar Projeto", null); // null é icone
		finalizar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				InputEvent.CTRL_MASK));
		finalizar.addActionListener(this);
		finalizar.setMnemonic(KeyEvent.VK_F);
		sair = new JMenuItem("Sair", null); // null é icone
		sair.addActionListener(this);
		sair.setMnemonic(KeyEvent.VK_S);

		// adiciona itens ao menu Projeto
		projeto.add(novo);
		projeto.add(finalizar);
		projeto.add(new JSeparator());
		projeto.add(sair);

		// monta menu Editar
		calculadora = new JMenuItem("Calculadora", null); // null é icone
		// KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_X,
		// InputEvent.CTRL_MASK);
		calculadora.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				InputEvent.ALT_MASK));
		calculadora.addActionListener(this);
		calculadora.setMnemonic(KeyEvent.VK_C);
		fichaCampo = new JMenuItem("Ficha de Campo", null); // null é icone
		fichaCampo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				InputEvent.ALT_MASK));
		fichaCampo.addActionListener(this);
		fichaCampo.setMnemonic(KeyEvent.VK_F);

		// adiciona itens ao menu Editar
		ferramentas.add(calculadora);
		ferramentas.add(fichaCampo);

		// monta menu Relatório
		relatorios = new JMenuItem("Relatórios dos Projetos", null); // null é
																		// icone
		relatorios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				InputEvent.ALT_MASK));
		relatorios.addActionListener(this);
		relatorios.setMnemonic(KeyEvent.VK_R);
		especiesIndicadoras = new JMenuItem("Espécies Indicadoras", null); // null
																			// é
																			// icone
		especiesIndicadoras.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_I, InputEvent.ALT_MASK));
		especiesIndicadoras.addActionListener(this);
		especiesIndicadoras.setMnemonic(KeyEvent.VK_I);

		// adiciona itens ao menu Relatório
		relatorio.add(relatorios);
		relatorio.add(new JSeparator());
		relatorio.add(especiesIndicadoras);

		Icon pessoaIco = new ImageIcon("src\\resources\\imagens\\pessoas16.png"); // TODO
																					// =
																					// usar
																					// classe
																					// Icon
		Icon arvoreIco = new ImageIcon("src\\resources\\imagens\\arvore16.png");
		Icon parcelaIco = new ImageIcon("src\\resources\\imagens\\globo16.png");
		Icon usuarioIco = new ImageIcon(
				"src\\resources\\imagens\\usuario16.png");

		// monta menu Cadastro
		pessoas = new JMenuItem("Cadastro de Pessoas", pessoaIco); // null é
																	// icone
		pessoas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_MASK));
		pessoas.addActionListener(this);
		pessoas.setMnemonic(KeyEvent.VK_O);
		parcelas = new JMenuItem("Cadastro de Parcelas", parcelaIco); // null é
																		// icone
		parcelas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				InputEvent.CTRL_MASK));
		parcelas.addActionListener(this);
		parcelas.setMnemonic(KeyEvent.VK_R);
		usuarios.setIcon(usuarioIco);
		usuarios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,
				InputEvent.CTRL_MASK));
		usuarios.addActionListener(this);
		usuarios.setEnabled(false);
		usuarios.setMnemonic(KeyEvent.VK_U);
		arvores = new JMenuItem("Cadastro de Árvores", arvoreIco); // null é
																	// icone
		arvores.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				InputEvent.CTRL_MASK));
		arvores.addActionListener(this);
		arvores.setMnemonic(KeyEvent.VK_A);

		// adiciona itens ao menu Cadastro
		cadastro.add(pessoas);
		cadastro.add(parcelas);
		cadastro.add(usuarios);
		cadastro.add(arvores);

		// monta menu Ajuda
		topicos = new JMenuItem("Tópicos de Ajuda", null); // null é icone
		topicos.addActionListener(new HelpAction());
		topicos.setMnemonic(KeyEvent.VK_T);
		sobre = new JMenuItem("Sobre o ARBORE", null); // null é icone
		sobre.addActionListener(this);
		sobre.setMnemonic(KeyEvent.VK_B);

		// adiciona itens ao menu Ajuda
		ajuda.add(topicos);
		ajuda.add(sobre);

		// adiciona os menus à barra
		this.add(projeto);
		this.add(ferramentas);
		this.add(relatorio);
		this.add(cadastro);
		this.add(ajuda);
	}

	public void actionPerformed(ActionEvent ae) {

		// Menu Projeto
		if (ae.getSource() == novo) {
			novo();
		}

		if (ae.getSource() == finalizar) {
			fimProjeto();
		}

		if (ae.getSource() == sair) {
			sair(0);
		}
		// Menu Editar
		if (ae.getSource() == calculadora) {
			exibirCalculadora();
		}
		if (ae.getSource() == fichaCampo) {
			emitirFicha();
		}

		// Menu Relatório
		if (ae.getSource() == relatorios) {
			relatorio();
		}

		if (ae.getSource() == especiesIndicadoras) {
			exibeEspeciesIndicadoras();
		}

		// Menu Cadastro
		if (ae.getSource() == pessoas) {
			cadastraPessoa();
		}
		if (ae.getSource() == parcelas) {
			cadastraParcela();
		}
		if (ae.getSource() == usuarios) {
			cadastraUsuario();
		}
		if (ae.getSource() == arvores) {
			cadastraArvore();
		}

		if (ae.getSource() == sobre) {
			sobre();
		}

	}

	protected void novo() {
		CadastroDeProjeto jifNovoProjeto = null;
		Object[] allFrames = desktopPane.getAllFrames();
		for (Object object : allFrames) {
			if (object instanceof CadastroDeProjeto) {
				jifNovoProjeto = (CadastroDeProjeto) object;
				break;
			}
		}

		if (jifNovoProjeto == null) {
			jifNovoProjeto = new CadastroDeProjeto();
			desktopPane.add(jifNovoProjeto);
		}
		if (jifNovoProjeto.isIcon()) {
			try {
				jifNovoProjeto.setIcon(false);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
		jifNovoProjeto.setVisible(true);
		jifNovoProjeto.toFront();
		try {
			jifNovoProjeto.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}

	}

	protected void sair(int num) {
		String[] textMessages = { "Sim", "Não", "Cancelar" };
		if (JOptionPane.showOptionDialog(null, "Deseja realmente sair?",
				"Atenção", JOptionPane.YES_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, textMessages, null) == 0) {
			try {
				BD.shutdown(); // erro ao desconectar
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.exit(num);
		}
	}

	protected void exibirCalculadora() {
		Calculadora calculadora = null;

		Object[] allFrames = desktopPane.getAllFrames();
		for (Object object : allFrames) {
			if (object instanceof Calculadora) {
				calculadora = (Calculadora) object;
				break;
			}
		}

		if (calculadora == null) {
			calculadora = new Calculadora();
			desktopPane.add(calculadora);
		}
		if (calculadora.isIcon()) {
			try {
				calculadora.setIcon(false);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
		calculadora.setVisible(true);
		calculadora.toFront();
		try {
			calculadora.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	protected void emitirFicha() {
		FichaDeCampoImprimir ficha = null;

		Object[] allFrames = desktopPane.getAllFrames();
		for (Object object : allFrames) {
			if (object instanceof FichaDeCampoImprimir) {
				ficha = (FichaDeCampoImprimir) object;
				break;
			}
		}

		if (ficha == null) {
			ficha = new FichaDeCampoImprimir();
			desktopPane.add(ficha);
		}
		if (ficha.isIcon()) {
			try {
				ficha.setIcon(false);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
		ficha.setVisible(true);
		ficha.toFront();
		try {
			ficha.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	protected void relatorio() {
		RelatorioFrame jifRelatorio = null;
		Object[] allFrames = desktopPane.getAllFrames();
		for (Object object : allFrames) {
			if (object instanceof RelatorioFrame) {
				jifRelatorio = (RelatorioFrame) object;
				break;
			}
		}

		if (jifRelatorio == null) {
			jifRelatorio = new RelatorioFrame("Relatórios Específicos");
			desktopPane.add(jifRelatorio);
		}
		if (jifRelatorio.isIcon()) {
			try {
				jifRelatorio.setIcon(false);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
		jifRelatorio.setVisible(true);
		jifRelatorio.toFront();
		try {
			jifRelatorio.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}

	}

	protected void exibeEspeciesIndicadoras() {

		DataInputStream jasperFile = JasperFrame.getJasper(RELATORIO);
		if (parametros == null)
			parametros = new HashMap<Object, Object>();
		// parametros.put("titulo", "titulo do relatorio");
		// parametros.put("dt1", dataInicial.getText());
		// parametros.put("dt2", dataFinal.getText() );

		Statement stm = null;
		ResultSet rs = null;
		try {
			stm = (Statement) BD.con.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		String query = "SELECT especiesindicadoras.`nomeComum` AS especiesindicadoras_nomeComum, " +
				"vegetacao.`descricao` AS vegetacao_descricao,  estagio.`descricao` " +
				"AS estagio_descricao FROM `vegetacao` vegetacao INNER JOIN `especiesindicadoras` " +
				"especiesindicadoras ON vegetacao.`chave` = especiesindicadoras.`vegetacao` " +
				"INNER JOIN `estagio` estagio ON especiesindicadoras.`estagio` = estagio.`chave` " +
				"ORDER BY estagio.`descricao`";

		try {
			rs = (ResultSet) stm.executeQuery(query);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			// JasperReport jasperReport =
			// JasperManager.loadReport(Resources.getJasper(reportName));
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(jasperFile);

			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parametros, new JRResultSetDataSource(rs));

			if (jasperPrint.getPages().size() > 0) {
				JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);

				// jasperViewer.setTitle("Relatório do "+titulo);

				jasperViewer.setTitle("ARBOR - Emissão de Relatórios");
				jasperViewer.setIconImage(Icons.getImage(Icons.FOLHA));
				jasperViewer.setExtendedState(JFrame.MAXIMIZED_BOTH);
				jasperViewer.setVisible(true);
				jasperViewer
						.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
			} else {
				JOptionPane.showMessageDialog(null,
						"Nenhum registro foi encontrado", null,
						JOptionPane.WARNING_MESSAGE);
			}
		} catch (JRException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"ERRO na criacao do relatorio: " + e.getMessage(), null,
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) { // ConnectException
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERRO interno: "
					+ e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void cadastraPessoa() {
		CadastroDePessoa jifCadastroPessoa = null;
		Object[] allFrames = desktopPane.getAllFrames();
		for (Object object : allFrames) {
			if (object instanceof CadastroDePessoa) {
				jifCadastroPessoa = (CadastroDePessoa) object;
				break;
			}
		}

		if (jifCadastroPessoa == null) {
			jifCadastroPessoa = new CadastroDePessoa();
			desktopPane.add(jifCadastroPessoa);
		}
		if (jifCadastroPessoa.isIcon()) {
			try {
				jifCadastroPessoa.setIcon(false);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
		jifCadastroPessoa.setVisible(true);
		jifCadastroPessoa.toFront();
		try {
			jifCadastroPessoa.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}

	}

	public static void cadastraParcela() {
		CadastroDeParcela jifCadastroParcela = null;
		Object[] allFrames = desktopPane.getAllFrames();
		for (Object object : allFrames) {
			if (object instanceof CadastroDeParcela) {
				jifCadastroParcela = (CadastroDeParcela) object;
				break;
			}
		}

		if (jifCadastroParcela == null) {
			jifCadastroParcela = new CadastroDeParcela();
			desktopPane.add(jifCadastroParcela);
		}
		if (jifCadastroParcela.isIcon()) {
			try {
				jifCadastroParcela.setIcon(false);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
		jifCadastroParcela.setVisible(true);
		jifCadastroParcela.toFront();
		try {
			jifCadastroParcela.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}

	}

	protected void cadastraUsuario() {
		CadastroDeUsuario jifCadastroDeUsuario = null;
		Object[] allFrames = desktopPane.getAllFrames();
		for (Object object : allFrames) {
			if (object instanceof CadastroDeUsuario) {
				jifCadastroDeUsuario = (CadastroDeUsuario) object;
				break;
			}
		}

		if (jifCadastroDeUsuario == null) {
			jifCadastroDeUsuario = new CadastroDeUsuario();
			desktopPane.add(jifCadastroDeUsuario);
		}
		if (jifCadastroDeUsuario.isIcon()) {
			try {
				jifCadastroDeUsuario.setIcon(false);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
		jifCadastroDeUsuario.setVisible(true);
		jifCadastroDeUsuario.toFront();
		try {
			jifCadastroDeUsuario.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}

	}

	protected void cadastraArvore() {
		CadastroDeArvore jifCadastroArvore = null;
		Object[] allFrames = desktopPane.getAllFrames();
		for (Object object : allFrames) {
			if (object instanceof CadastroDeArvore) {
				jifCadastroArvore = (CadastroDeArvore) object;
				break;
			}
		}

		if (jifCadastroArvore == null) {
			jifCadastroArvore = new CadastroDeArvore();
			desktopPane.add(jifCadastroArvore);
		}
		if (jifCadastroArvore.isIcon()) {
			try {
				jifCadastroArvore.setIcon(false);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
		jifCadastroArvore.setVisible(true);
		jifCadastroArvore.toFront();
		try {
			jifCadastroArvore.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	private void sobre() {
		new Sobre().setVisible(true);
	}

	public void setStatus(boolean status) {
		usuarios.setEnabled(status);
	}

	public static void fimProjeto() {
		FimDoProjeto jifFimDoProjeto = null;
		Object[] allFrames = desktopPane.getAllFrames();
		for (Object object : allFrames) {
			if (object instanceof FimDoProjeto) {
				jifFimDoProjeto = (FimDoProjeto) object;
				break;
			}
		}

		if (jifFimDoProjeto == null) {
			jifFimDoProjeto = new FimDoProjeto();
			desktopPane.add(jifFimDoProjeto);
		}
		if (jifFimDoProjeto.isIcon()) {
			try {
				jifFimDoProjeto.setIcon(false);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
		jifFimDoProjeto.setVisible(true);
		jifFimDoProjeto.toFront();
		try {
			jifFimDoProjeto.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}
}
