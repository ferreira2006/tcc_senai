package gui.cadastros;


import gui.relatorios.Grafico_Arvores;
import gui.relatorios.JasperFrame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import resources.AcoesDaTabela;
import resources.AlteraTipoCursor;
import resources.Funcoes;
import resources.Icons;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import db.ArvoreDAO;
import db.ArvoreVO;
import db.BD;

@SuppressWarnings("serial")
public class CadastroDeArvore extends CadastroAbstrato {
	private JTextField codigo, nomePopular, nomeCientifico, familia;
	private JPanel abaCadastro, abaTabela, abaRelatorio, abaGrafico;
	private Funcoes funcoes;
	private JTable tabela;
	private ArvoreDAO arvoreDAO;
	private ArvoreVO arvoreVO;
	private JTabbedPane tabbedPane;
	private AcoesDaTabela acoesDaTabela;
	public final String RELATORIO = "arvores";	
	private Map<Object, Object> parametros;
	private JRadioButton pop, cie, fam;

	public CadastroDeArvore() {

		super("Cadastro de Árvores");

		setFrameIcon(Icons.getIcon(Icons.ARVORE));

		inicializar();

		personalizaAba(tabbedPane);
		new AlteraTipoCursor(abaTabela);

		abaCadastro.add(montaCadastro());
		abaTabela.add(montaTabela());
		abaRelatorio.add(montaRelatorio());
		
		JButton emitir = new JButton("Emissão");
		emitir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				emitirRelatorio();
			}
		});
		abaRelatorio.add(emitir);

		tabbedPane.addTab("Cadastro de Árvores", Icons.getIcon(Icons.ARVORE), abaCadastro);
		tabbedPane.addTab("Árvores cadastradas", Icons.getIcon(Icons.ARVORES32), abaTabela);
		tabbedPane.addTab("Relatório Individual", Icons.getIcon(Icons.FOLHA), abaRelatorio);
		tabbedPane.addTab("Gráfico por famílias", Icons.getIcon(Icons.CHART32), abaGrafico);

		add(BorderLayout.CENTER, tabbedPane);		
	//	setChaveComponent(nomePopular);
		focarChave();
		pack();
	}

	private JScrollPane montaTabela() {

		final JScrollPane sp = new JScrollPane(tabela,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		DefaultTableModel model = new DefaultTableModel(new Object[][] {},
				new Object[] { "Chave", "Nome Comum", "Nome Científico", "Família" }) {

			boolean[] canEdit = new boolean[] { false, false, false, false, false };

			// Não permite edição nas células
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		};

		for (ArvoreVO arvoreVO : arvoreDAO.pegaLista(false)) {
			model.addRow(new String[] { arvoreVO.getChave(), arvoreVO.getNomeComum(),
					arvoreVO.getNomeCientifico(), arvoreVO.getFamilia() });
		}

		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabela.setModel(model);
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.getTableHeader().setResizingAllowed(false);
		tabela.setSelectionBackground(super.getCorPadrao());
		tabela.setBorder(BorderFactory.createEmptyBorder());
		sp.setBorder(BorderFactory.createTitledBorder(BorderFactory
		.createLineBorder(super.getCorPadrao()), " Árvores Cadastradas: "));
		
		defineRenderers();
		
		tabela.addMouseListener(new MouseAdapter() {
		
			public void mouseClicked(MouseEvent e) {
				lerDadosDoBanco();
			}
		});

		tabela.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						lerDadosDoBanco();
						// scroll acompanha a linha selecionada
						tabela.scrollRectToVisible(tabela.getCellRect(tabela
								.getSelectedRow(), 0, true));
					}
				});

		sp.setPreferredSize(dimensaoPadrao(this));
		tabela.setRowSelectionInterval(0, 0);
		return sp;
	}

	private void defineRenderers() {

		DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
		rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);

		DefaultTableCellRenderer colunaModel = new DefaultTableCellRenderer();

		TableColumnModel modeloDaColuna = tabela.getColumnModel();

		modeloDaColuna.getColumn(0).setCellRenderer(rendererCentro); // alin. centro

		modeloDaColuna.getColumn(0).setMaxWidth(50); // largura fixa

		colunaModel.setHorizontalAlignment(SwingConstants.CENTER);
		colunaModel.setBackground(super.getCorPadrao());
		modeloDaColuna.getColumn(0).setCellRenderer(colunaModel);

	}

	public void carregarTabela() {

		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setRowCount(0);
		
		Vector<ArvoreVO> vector = arvoreDAO.pegaLista(false);
		
		for (int i = 0; i < vector.size(); i++) {
			ArvoreVO vo = vector.elementAt(i);
			modelo.addRow( new String[] { String.valueOf(vo.getChave()), vo.getNomeComum(),
						vo.getNomeCientifico(), vo.getFamilia() } ); 
			}

		/*
		for (ArvoreVO arvoreVO : arvoreDAO.pegaLista()) {
			modelo.addRow(new String[] { arvoreVO.getChave(),
					arvoreVO.getNomeComum(), arvoreVO.getNomeCientifico(),
					arvoreVO.getFamilia() });
		}
		*/
		
	}

	public void avancaTabela() { acoesDaTabela.avancaTabela(); }

	public void retroTabela() { acoesDaTabela.retroTabela(); }

	private JPanel montaCadastro() {

		codigo = new JTextField(5);
		codigo.setEnabled(false);
	//	codigo.setBackground(super.getCorPadrao());
		nomePopular = new JTextField(20);
		setChaveComponent(nomePopular);		
	//	nomePopular.addFocusListener(new FocusAdapter(){
	//		public void focusGained(FocusEvent arg0) {				
	//			nomePopular.selectAll();
	//		}});
		
		
		nomeCientifico = new JTextField(20);
		familia = new JTextField(20);
		
		JPanel painel = new JPanel(new GridBagLayout());

		painel.setBorder(BorderFactory.createTitledBorder(BorderFactory
				.createLineBorder(super.getCorPadrao()), " Dados da Árvore: "));

		GridBagConstraints gbc = new GridBagConstraints();

		adicionar(painel, "Chave:", "O código será gerado automaticamente pelo Sistema", codigo, 1, 2, 1, gbc);
		adicionar(painel, "Nome Comum:", "Informe o nome popular desta árvore", nomePopular, 1, 2, 2, gbc);
		adicionar(painel, "Nome Científico:", "Informe o nome científico desta árvore", nomeCientifico, 1, 2, 3, gbc);
		adicionar(painel, "Família:", "Informe a qual familía pertence", familia, 1, 2, 4, gbc);

		funcoes.addStatusFocusListener(painel, bs.jttt);
		painel.setPreferredSize(dimensaoPadrao(this));
		
		addFocusListnerContainer(painel);
		
		return painel;
	}

	// Método adicionar(), adiciona componentes com GridBagLayout
	public void adicionar(Container container, String rotulo, String tooltip,
		JComponent componente, int x1, int x2, int y, GridBagConstraints gbc) {
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.EAST;		
		gbc.gridx = x1;
		gbc.gridy = y;
		container.add(new JLabel(rotulo, JLabel.RIGHT), gbc);
		gbc.gridx = x2;
		gbc.gridy = y;
		gbc.anchor = GridBagConstraints.WEST;
		componente.setToolTipText(tooltip);
		container.add(componente, gbc);
	}

	protected void limparTela() {
		codigo.setText("");
		nomePopular.setText("");
		nomeCientifico.setText("");
		familia.setText("");
		nomePopular.requestFocus();
	}

	protected boolean validarDados(Component c) {
		boolean result = true;
		
		// nome
		if ("".equals(nomePopular.getText().trim())) { 
			return mensagemErroInformeCampo(this, nomePopular); 
		}

		// senha
		if ("".equals(nomeCientifico.getText())) {
			return mensagemErroInformeCampo(this, nomeCientifico);
		}

		// Repete senha
		if ("".equals(familia.getText())) {
			return mensagemErroInformeCampo(this, familia);
		}

		return result;
	}

	private void inicializar() {
		abaCadastro = new JPanel();
		abaTabela = new JPanel();
		abaRelatorio = new JPanel();
		abaGrafico = new JPanel();
		funcoes = new Funcoes();		
		tabbedPane = new JTabbedPane();
		tabela = new JTable();
		acoesDaTabela = new AcoesDaTabela(tabela);
		arvoreDAO = new ArvoreDAO();
		arvoreVO = new ArvoreVO();
		
		abaGrafico.setLayout(new BorderLayout());
		atualizaGrafico();
	}
	
	protected void atualizaGrafico() {
		try {
			abaGrafico.removeAll();
			abaGrafico.add(new Grafico_Arvores().montaGrafico());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void excluirDoBanco() {
		// if (validarDados(codigo)) {
		if (!codigo.getText().equals("")) {
			if (lerDadosDoBanco()) {
				if (!perguntaExcluiCadastro(this, codigo.getText()))
					return;
				if (arvoreDAO.excluir(codigo.getText())) {
					mensagemCadastroExcluido(this);
					limparTela();
				} else {
					erroBancoDeDados(this);
				}
				carregarTabela();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Selecione uma Árvore na aba /bÁrvore Cadastradas, para exclusão");
		}
		// }
		nomePopular.requestFocus();
		atualizaGrafico();
	}

	protected void mostrarNaTela() {
		codigo.setText(arvoreVO.getChave());
		nomePopular.setText(arvoreVO.getNomeComum());
		nomeCientifico.setText(arvoreVO.getNomeCientifico());
		familia.setText(arvoreVO.getFamilia());
		nomePopular.requestFocus();
	}

	protected void salvarDadosNoBanco() {

		if (validarDados(null)) {
			arvoreVO.setNomeComum(nomePopular.getText());
			arvoreVO.setNomeCientifico(nomeCientifico.getText());
			arvoreVO.setFamilia(familia.getText());

			try {
				if (arvoreDAO.salvar(arvoreVO, codigo.getText())) {
					mensagemCadastroGravado(this);
					limparTela();
					carregarTabela();
				} else {
					erroBancoDeDados(this);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		atualizaGrafico();
	}

	protected boolean lerDadosDoBanco() {
		if (tabela.getRowCount() > 0) {
			try {
				arvoreVO = (ArvoreVO) arvoreDAO.ler(acoesDaTabela.pegaChave());
				if (arvoreVO != null) {
					// senhaVO = (SenhaVO) senhaDAO.ler(""+senhaVO.getChave());
					mostrarNaTela();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				erroBancoDeDados(this);
				return false;
			}
		}

		return arvoreVO != null;
	}

	protected void novoCadastro() {
		tabbedPane.setSelectedComponent(abaCadastro);
		limparTela();
	}
	private JPanel montaRelatorio(){
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(" Opções de Relatório: "));
		panel.setLayout(new GridLayout(0,1));
		ButtonGroup gb = new ButtonGroup();
		pop = new JRadioButton("Árvores por Nome Popular");		
		cie = new JRadioButton("Árvores por Nome Científico");
		fam = new JRadioButton("Árvores por Família");
		pop.setSelected(true);
		
		gb.add(pop);
		gb.add(cie);
		gb.add(fam);
		
		panel.add(pop);
		panel.add(cie);
		panel.add(fam);
		
		return panel;
	}
	private void emitirRelatorio() {
		
		DataInputStream jasperFile = JasperFrame.getJasper(RELATORIO);
		
		
		if (parametros==null) parametros = new HashMap<Object, Object>();
		//	parametros.put("titulo", "titulo do relatorio");
		//	parametros.put("dt1", dataInicial.getText());  
		//	parametros.put("dt2", dataFinal.getText() );
		
		
		
		Statement stm = null;
		ResultSet rs = null;
		try {
			stm = (Statement) BD.con.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String query = "";
		
		if (pop.isSelected()) {
			query =
				"SELECT * FROM arvore ORDER BY nomePopular";
		}else {
			if (cie.isSelected()) {
				query =
					"SELECT * FROM arvore ORDER BY nomeCientifico";
			}else {
				query =
					"SELECT * FROM arvore ORDER BY familia";
			}
		}
		
		try {
			rs = (ResultSet) stm.executeQuery( query );
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
 
		
		  
		
		try {
			//JasperReport jasperReport = JasperManager.loadReport(Resources.getJasper(reportName));
			JasperReport jasperReport = (JasperReport)JRLoader.loadObject(jasperFile);

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JRResultSetDataSource(rs));
			
			if (jasperPrint.getPages().size()>0) {
				JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
				
				//jasperViewer.setTitle("Relatório do "+titulo);
				
				jasperViewer.setTitle("ARBOR - ÁRVORES CADASTRADAS");
				jasperViewer.setIconImage(Icons.getImage(Icons.FOLHA));
				jasperViewer.setExtendedState(JFrame.MAXIMIZED_BOTH);
				jasperViewer.setVisible(true);
				jasperViewer.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
			} else {
				JOptionPane.showMessageDialog(null, "Nenhum registro foi encontrado", null, JOptionPane.WARNING_MESSAGE);
			}
		}
		catch (JRException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERRO na criacao do relatorio: "+e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) { // ConnectException
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERRO interno: "+e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
