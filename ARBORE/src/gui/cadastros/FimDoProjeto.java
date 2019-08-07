package gui.cadastros;


import gui.relatorios.Grafico_FimProjeto;
import gui.relatorios.JasperFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
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
import resources.FormataDatas;
import resources.Icons;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import db.BD;
import db.EstagioDAO;
import db.EstagioVO;
import db.PessoaDAO;
import db.PessoaVO;
import db.ProjetoDAO;
import db.ProjetoParcelaDAO;
import db.ProjetoParcelaVO;
import db.ProjetoVO;

@SuppressWarnings("serial")
public class FimDoProjeto extends JInternalFrame{
	
	private JComboBox projeto, projetoRel, estagio;
	private ProjetoVO projetoVO;
	private ProjetoDAO projetoDAO;
	
	private JPanel abaCadastro, abaTabela, abaGrafico;
	private JTable tabela;
	private JTabbedPane tabbedPane;
	public final String RELATORIO = "relatorioFinal";
	public final String RELATORIO2 = "relatorioAnalise";
	private Map<Object, Object> parametros;
	private Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 16);

	public FimDoProjeto() {

		super("Finalização do Projeto", true, true, true, true);

		setFrameIcon(Icons.getIcon(Icons.ARVORE));

		inicializar();
		
		abaCadastro.setLayout(new BorderLayout());		
		abaCadastro.add(montaCadastro()); // default no Centro
	
		abaTabela.setLayout(new BorderLayout());
		abaTabela.add(BorderLayout.CENTER, montaTabela());
		
		tabbedPane.addTab("Tabela dos Projetos", Icons.getIcon(Icons.NOVO), abaTabela);
		tabbedPane.addTab("Determinação de Estágio", Icons.getIcon(Icons.FIM), abaCadastro);		
		tabbedPane.addTab("Projetos e Estágios", Icons.getIcon(Icons.CHART32), abaGrafico);
		tabbedPane.addTab("Relatório Final", Icons.getIcon(Icons.RELATORIO32), emitirRelFinal());
		
		add(BorderLayout.CENTER, tabbedPane);		
		
		pack();
	}

	private JScrollPane montaTabela() {

		final JScrollPane sp = new JScrollPane(tabela,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		DefaultTableModel model = new DefaultTableModel(new Object[][] {},
				new Object[] { "Projeto", "Data", "Elaborador", "Proprietário", "Estagio"}) {

			boolean[] canEdit = new boolean[] { false, false, false, false, false};

			// Não permite edição nas células
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		};
		
		
		EstagioDAO estagioDAO = new EstagioDAO();		
		EstagioVO estagioVO = null;
		PessoaDAO pessoaDAO = new PessoaDAO();
		PessoaVO proprietarioVO = null;
		PessoaVO elaboradorVO = null;
		
		for (ProjetoVO projetoVO : projetoDAO.pegaLista()) {
			try {
				estagioVO = estagioDAO.ler(""+projetoVO.getEstagio());
				proprietarioVO = (PessoaVO) pessoaDAO.ler(""+projetoVO.getProprietario());
				elaboradorVO = (PessoaVO) pessoaDAO.ler(""+projetoVO.getElaborador());
			} catch (SQLException e1) {	e1.printStackTrace(); }
			model.addRow(new String[] { projetoVO.getChave(), new FormataDatas().mostraDataMySql(projetoVO.getData()), elaboradorVO.getNome(), proprietarioVO.getNome(), estagioVO.getDescricao()});
		}

		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabela.setModel(model);
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.getTableHeader().setResizingAllowed(false);
		tabela.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
		tabela.setSelectionBackground(new Color(162, 205, 90));
		sp.setBorder(BorderFactory.createTitledBorder(BorderFactory
				.createLineBorder(new Color(162, 205, 90)), " INFORMAÇÕES FINAIS DOS PROJETOS: ", SwingConstants.LEFT, SwingConstants.CENTER, new Font(Font.SANS_SERIF, Font.BOLD, 14), new Color(162, 205, 90)));
		
		
		defineRenderers();
		
		tabela.addMouseListener(new MouseAdapter() {
		
			public void mouseClicked(MouseEvent e) {
				
				projeto.setSelectedIndex(tabela.getSelectedRow());
				
				int selecao = 4;
				
				String teste = pegaEstagio();
				
				if (teste.equals("INICIAL")) {
					selecao = 0;
				} else {
					if (teste.equals("MÉDIO")) {
						selecao = 1;
					} else {
						if (teste.equals("AVANÇADO")) {
							selecao = 2;
						} else {
							selecao = 3;
						}
					}
				}
				
				estagio.setSelectedIndex(selecao);
				
			}
		});

		tabela.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						
						// scroll acompanha a linha selecionada
						tabela.scrollRectToVisible(tabela.getCellRect(tabela.getSelectedRow(), 0, true));
					}
				}); 

		sp.setPreferredSize(new Dimension(450, 300));
		
		if (tabela.getRowCount() > 0) {
			tabela.setRowSelectionInterval(0, 0);
		}
		
		return sp;
	}
	
	protected String pegaEstagio() {
		Object chave = null;
		if (tabela.getSelectedRow() >= 0 && tabela.getSelectedRow() <= tabela.getRowCount()) {
			chave = tabela.getModel().getValueAt(tabela.getSelectedRow(), 4);
		}
		return (String) chave;
	}
	
	private void defineRenderers() {

		DefaultTableCellRenderer colunaModel = new DefaultTableCellRenderer();
		TableColumnModel modeloDaColuna = tabela.getColumnModel();
		
		DefaultTableCellRenderer colunaVermelha = new DefaultTableCellRenderer();
		TableColumnModel modeloVermelho = tabela.getColumnModel();


		modeloDaColuna.getColumn(0).setMaxWidth(50); // largura fixa

		colunaModel.setBackground(new Color(162, 205, 90));
		modeloDaColuna.getColumn(0).setCellRenderer(colunaModel);
		
		colunaVermelha.setForeground(Color.RED);
		modeloVermelho.getColumn(4).setCellRenderer(colunaVermelha);
		

	}

	public void carregarTabela() {

		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setRowCount(0);
		
		EstagioDAO estagioDAO = new EstagioDAO();		
		EstagioVO estagioVO = null;
		PessoaDAO pessoaDAO = new PessoaDAO();
		PessoaVO proprietarioVO = null;
		PessoaVO elaboradorVO = null;
		
		for (ProjetoVO projetoVO : projetoDAO.pegaLista()) {
			try {
				estagioVO = estagioDAO.ler(""+projetoVO.getEstagio());
				proprietarioVO = (PessoaVO) pessoaDAO.ler(""+projetoVO.getProprietario());
				elaboradorVO = (PessoaVO) pessoaDAO.ler(""+projetoVO.getElaborador());
			} catch (SQLException e1) {	e1.printStackTrace(); }
			modelo.addRow(new String[] { projetoVO.getChave(), new FormataDatas().mostraDataMySql(projetoVO.getData()), elaboradorVO.getNome(), proprietarioVO.getNome(), estagioVO.getDescricao()});
		}

		if (tabela.getRowCount() > 0) {
			tabela.setRowSelectionInterval(0, 0);
		}
	}

	private JPanel montaCadastro() {		
		
		projeto = new JComboBox(new ProjetoDAO().pegaLista());
		projeto.addItem("");
		projeto.setSelectedIndex(projeto.getItemCount()-1);
		projeto.setFont(fonte);
		estagio = new JComboBox(new EstagioDAO().pegaLista());
		estagio.setFont(fonte);
		estagio.addItem("");
		estagio.setSelectedIndex(estagio.getItemCount()-1);
					
		projeto.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				estagio.setSelectedIndex(projetoDAO.pegaEstagio(""+projeto.getSelectedItem())-1);
			//	System.out.println(projetoDAO.pegaEstagio(""+projeto.getSelectedItem()));
				
				Vector<ProjetoParcelaVO> vector = new ProjetoParcelaDAO().pegaParcelas(""+projeto.getSelectedItem());
				
				for (int i = 0; i < vector.size(); i++) {
				//	ProjetoParcelaVO vo = vector.elementAt(i).getChaveParcela();
					
					System.out.println(vector.elementAt(i).getChaveParcela()); 
					}
				
				//TODO = finalizar
				
				//TODO = finalizar
				
				//TODO = finalizar
				
				//TODO = finalizar
				
				
			}			
		}); 
		
		JPanel painel1 = new JPanel(new GridLayout(0,1));
		painel1.setBorder(BorderFactory.createTitledBorder(BorderFactory
				.createLineBorder(new Color(162, 205, 90)), " DETERMINAÇÃO DE ESTÁGIO: ", SwingConstants.LEFT, SwingConstants.CENTER, new Font(Font.SANS_SERIF, Font.BOLD, 14), new Color(162, 205, 90)));	
		 
		JPanel painel = new JPanel(new GridBagLayout());

		painel.setBorder(BorderFactory.createTitledBorder(BorderFactory
				.createLineBorder(new Color(162, 205, 90)), " Selecione o projeto e o estágio e clique em [Gravar]: ", SwingConstants.LEFT, SwingConstants.CENTER, new Font(Font.SANS_SERIF, Font.PLAIN, 12), new Color(162, 205, 90)));
		
		JButton relatorio = new JButton("Relatório");
		relatorio.setFont(fonte);
		relatorio.setMnemonic('R');
		relatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!"".equals(projeto.getSelectedItem())) {
					resultados();
				}else{
					JOptionPane.showMessageDialog(null, "Selecione um projeto");
					projeto.requestFocus();
				}
			}
		});
		
		JButton atualizar = new JButton("Gravar");
		atualizar.setFont(fonte);
		atualizar.setMnemonic('G');
		atualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					salvarDadosNoBanco();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		GridBagConstraints gbc = new GridBagConstraints();
		JLabel pro, est, aviso;
		pro = new JLabel("Projeto");
		pro.setFont(fonte);
		est = new JLabel("Estágio");
		est.setFont(fonte);
		aviso = new JLabel("<html><h3>Com base nos parâmetros levantados, considerando os resultados obtidos,<br>" + 
				                 "levando ainda em consideração as análises subjetivas que a legislação elenca,<br>" +
				                 "faça a associção entre Pojetos e Estágios usando as duas listagens abaixo.<br></h3></html>", JLabel.CENTER);
		aviso.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 14));
		aviso.setForeground(Color.BLUE);
		
		adicionar(painel, "", "", pro, 1, 2, 3, gbc);
		adicionar(painel, "", "", est, 3, 4, 3, gbc);
		adicionar(painel, "", "Informe o nº do Projeto", projeto, 1, 2, 4, gbc);		
		adicionar(painel, "", "Informe o Estágio de Regeneração", estagio, 3, 4, 4, gbc);	
		adicionar(painel, "", "Clique para atualizar as alterações", atualizar, 5, 6, 4, gbc);
	//	String frase = "Resultados finais:";
	//	adicionar(painel, "Resultados: ", frase, relatorio, 5, 6, 2, gbc);
		
		JPanel relatorioPanel = new JPanel();
		relatorioPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory
				.createLineBorder(new Color(162, 205, 90)), " Resultados para o projeto selecionado abaixo: ", SwingConstants.LEFT, SwingConstants.CENTER, new Font(Font.SANS_SERIF, Font.PLAIN, 12), new Color(162, 205, 90)));
		relatorioPanel.setLayout(new GridBagLayout());
	//	relatorioPanel.add(new JLabel(""));
	//	relatorioPanel.add(new JLabel(""));
		relatorioPanel.add(relatorio);
		
		painel1.add(relatorioPanel);		
		painel1.add(aviso);
		painel1.add(painel);
		
		
		return painel1;
	}
	
	private void emitirRelatorio(String projeto) {
		
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
		String query = "SELECT p.`chave`,  p.`data`, p.`endereco`, p.`numero`, p.`bairro`," +
				" m.`nome` AS municipio, p.`areaTotal`, p.`areaDegradada`, h.`nome`, e.`descricao`," +
				" c.`chave`AS parcela, c.`area`, x.`nomePopular`, v.`cap` AS v_cap, v.`dap` AS v_dap," +
				" v.`altura` AS v_altura, v.`cas` AS v_cas, v.`distancia` AS v_distancia," +
				" v.`areaBasal` AS v_areaBasal, b.descricao AS bioma, f.descricao AS epifita," +
				" g.descricao AS vegetacao, s.descricao AS serrapilheira, t.descricao AS trepadeira," +
				" c.muitasEspPioneiras, c.subBosque FROM `projeto` p, `municipio` m, `pessoa` h," +
				" `estagio` e, `projetoparcela` j, `parcela` c, `parcelaarvore` v, `arvore` x," +
				" bioma b, epifita f, vegetacao g, serrapilheira s, trepadeira t " +
				"WHERE p.chave = '" + projeto + "'" +
				"AND p.municipio = m.chave AND p.proprietario = h.chave AND p.estagio = e.chave " +
				"AND p.chave = j.chaveProjeto AND c.chave = j.chaveParcela " +
				"AND v.chaveParcela = c.chave AND v.chaveArvore = x.chave AND b.chave = c.bioma " +
				"AND f.chave = c.epifita AND g.chave = c.vegetacao AND s.chave = c.serrapilheira " +
				"AND t.chave = c.trepadeira"; 	
		
		
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
				
				jasperViewer.setTitle("ARBOR - Emissão de Relatórios");
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
		projeto.setSelectedIndex(projeto.getItemCount()-1);
		estagio.setSelectedIndex(estagio.getItemCount()-1);
	}


	private void inicializar() {
		projetoVO = new ProjetoVO();
		projetoDAO = new ProjetoDAO();
		abaCadastro = new JPanel();
		abaTabela = new JPanel();
		abaGrafico = new JPanel();	
		tabbedPane = new JTabbedPane();
		tabela = new JTable();
		
		abaGrafico.setLayout(new BorderLayout());
		try {
			abaGrafico.add(new Grafico_FimProjeto().montaGrafico());
		} catch (SQLException e) { e.printStackTrace(); }
		
	}

	protected void salvarDadosNoBanco() throws SQLException {
		
	if (!projeto.getSelectedItem().toString().equals("") && !estagio.getSelectedItem().toString().equals("")) {
		projetoVO = (ProjetoVO) new ProjetoDAO().ler(projeto.getSelectedItem().toString());		
		projetoVO.setEstagio(estagio.getSelectedIndex()+1);
		
		if (projetoDAO.salvar(projetoVO, projeto.getSelectedItem().toString())) {
			JOptionPane.showMessageDialog(null, "Atualização realzada com sucesso!");				
			carregarTabela();
		} else {
			JOptionPane.showMessageDialog(null, "Erro! Contate o supervisor do sistema.");
		}
		
	}else {
		JOptionPane.showMessageDialog(null, "Projeto ou estágio não selecionado!");
	}

		atualizaGrafico();

	}
	
	private void atualizaGrafico() {
		abaGrafico.removeAll();
		try {
			abaGrafico.add(new Grafico_FimProjeto().montaGrafico());
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	private JPanel emitirRelFinal() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory
				.createLineBorder(new Color(162, 205, 90)), " RELATÓRIO FINAL DO PROJETO: ", SwingConstants.LEFT, SwingConstants.CENTER, new Font(Font.SANS_SERIF, Font.BOLD, 14), new Color(162, 205, 90)));
		JButton relatorio = new JButton("Relatório Final");
		relatorio.setFont(fonte);
		relatorio.setMnemonic('R');
		relatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				emitirRelatorio(projetoRel.getSelectedItem().toString());
			}
		});
		projetoRel = new JComboBox(new ProjetoDAO().pegaLista());
		projetoRel.setFont(fonte);
		panel.add(projetoRel);
		panel.add(relatorio);
		
		return panel;
	}
	private void resultados() {
		
		DataInputStream jasperFile = JasperFrame.getJasper(RELATORIO2);		
		if (parametros==null) parametros = new HashMap<Object, Object>();
		//	parametros.put("projeto", projeto.getSelectedItem().toString());
		//	parametros.put("dt1", dataInicial.getText());  
		//	parametros.put("dt2", dataFinal.getText() );
		
		
		
		Statement stm = null;
		ResultSet rs = null;
		try {
			stm = (Statement) BD.con.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String query = 
			/*	"SELECT p.`chave`,  p.`data`, p.`endereco`, p.`numero`, p.`bairro`," +
				" m.`nome` AS municipio, p.`areaTotal`, p.`areaDegradada`, h.`nome`, e.`descricao`," +
				" c.`chave`AS parcela, c.`area`, x.`nomePopular`, v.`cap` AS v_cap, v.`dap` AS v_dap," +
				" v.`altura` AS v_altura, v.`cas` AS v_cas, v.`distancia` AS v_distancia," +
				" v.`areaBasal` AS v_areaBasal, b.descricao AS bioma, f.descricao AS epifita," +
				" g.descricao AS vegetacao, s.descricao AS serrapilheira, t.descricao AS trepadeira," +
				" c.muitasEspPioneiras, c.subBosque FROM `projeto` p, `municipio` m, `pessoa` h," +
				" `estagio` e, `projetoparcela` j, `parcela` c, `parcelaarvore` v, `arvore` x," +
				" bioma b, epifita f, vegetacao g, serrapilheira s, trepadeira t " +
				"WHERE p.chave = $P{projeto}" +
				"AND p.municipio = m.chave AND p.proprietario = h.chave AND p.estagio = e.chave " +
				"AND p.chave = j.chaveProjeto AND c.chave = j.chaveParcela " +
				"AND v.chaveParcela = c.chave AND v.chaveArvore = x.chave AND b.chave = c.bioma " +
				"AND f.chave = c.epifita AND g.chave = c.vegetacao AND s.chave = c.serrapilheira " +
				"AND t.chave = c.trepadeira"; */
			
			"SELECT projeto.chave,  projeto.data, projeto.endereco, projeto.numero, " +
			"projeto.bairro,municipio.nome AS municipio, projeto.areaTotal, projeto.areaDegradada, " +
			"pessoa.nome, estagio.descricao, parcela.chave AS parcela, parcela.area, " +
			"arvore.nomePopular, parcelaarvore.cap AS v_cap, parcelaarvore.dap AS v_dap, " +
			"parcelaarvore.altura AS v_altura, parcelaarvore.cas AS v_cas, parcelaarvore.distancia " +
			"AS v_distancia, parcelaarvore.areaBasal AS v_areaBasal, bioma.descricao AS bioma, " +
			"epifita.descricao AS epifita, vegetacao.descricao AS vegetacao, serrapilheira.descricao " +
			"AS serrapilheira, trepadeira.descricao AS trepadeira, parcela.muitasEspPioneiras, " +
			"parcela.subBosque FROM projeto, municipio, pessoa, estagio, projetoparcela, parcela, " +
			"parcelaarvore, arvore, bioma, epifita, vegetacao, serrapilheira, trepadeira WHERE " +
			"projeto.chave = '"+ projeto.getSelectedItem().toString()+ "'" +
			" AND projeto.municipio = municipio.chave AND " +
			"projeto.proprietario = pessoa.chave AND projeto.estagio = estagio.chave AND " +
			"projeto.chave = projetoparcela.chaveProjeto AND parcela.chave = " +
			"projetoparcela.chaveParcela AND parcelaarvore.chaveParcela = parcela.chave AND " +
			"parcelaarvore.chaveArvore = arvore.chave AND bioma.chave = parcela.bioma AND " +
			"epifita.chave = parcela.epifita AND vegetacao.chave = parcela.vegetacao AND " +
			"serrapilheira.chave = parcela.serrapilheira AND trepadeira.chave = parcela.trepadeira"; 
	
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
				
				jasperViewer.setTitle("ARBOR - Resultados Finais Do Projeto "+projeto.getSelectedItem().toString());
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
