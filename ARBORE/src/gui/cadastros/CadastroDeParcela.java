package gui.cadastros;


import gui.JanelaPrincipal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
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

import resources.FormataDatas;
import resources.Icons;
import db.MunicipioDAO;
import db.MunicipioVO;
import db.ParcelaArvoreDAO;
import db.ParcelaDAO;
import db.ParcelaVO;
import db.PessoaDAO;
import db.PessoaVO;
import db.ProjetoDAO;
import db.ProjetoParcelaDAO;
import db.ProjetoParcelaVO;
import db.ProjetoVO;

@SuppressWarnings("serial")
public class CadastroDeParcela extends JInternalFrame {
	
	private ProjetoDAO projetoDAO;
	private JPanel abaTabelaParcela;
	private JTabbedPane tabbedPane;
	protected JTable tabelaParcela, tabelaProjetos;
	private ParcelaDAO parcelaDAO;
	private JButton addParcela, excParcela, addArvore, excArvore, fechar;
	private ArvoresDaParcela tabelaArvores;
	private ProjetoParcelaDAO projetoParcelaDAO;
	private ProjetoParcelaVO projetoParcelaVO;
	private ParcelaArvoreDAO parcelaArvoreDAO;
	private PessoaDAO proprietarioDAO;
	private PessoaVO proprietarioVO;
	private PessoaVO elaboradorVO;
	private PessoaDAO elaboradorDAO;
	private MunicipioDAO municipioDAO;
	private MunicipioVO municipioVO;
	protected AdicionaArvore jifAdicionaArvore = null;
	
	
	public CadastroDeParcela() {
		// titulo, opções da janela
		super("Cadastro de Parcelas", false, true, true, true);
		 // muda o icone
		setFrameIcon(Icons.getIcon(Icons.PARCELA));
		//inicializa campos
		inicializar();

		
		abaTabelaParcela.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		adicionar(abaTabelaParcela, "", "Exibe os Projetos já cadastrados", montaTabelaProjetos(), 1, 2, 1, gbc);
		adicionar(abaTabelaParcela, "", "", panelBotoesParcela(), 1, 2, 2, gbc);
		adicionar(abaTabelaParcela, "", "", montaTabelaParcela(), 1, 2, 3, gbc);		
		adicionar(abaTabelaParcela, "", "", panelBotoesArvore(), 1, 2, 4, gbc);
		adicionar(abaTabelaParcela, "", "", tabelaArvores.montaTabelaArvoresDaParcela(pegaChaveParcela()), 1, 2, 5, gbc);
		adicionar(abaTabelaParcela, "", "", panelFechar(), 1, 2, 6, gbc);
		
		tabbedPane.addTab("Projetos, Parcelas e Árvores", Icons.getIcon(Icons.PARCELA), abaTabelaParcela);		
	//	tabbedPane.addTab("Grafico das Parcela", Icons.getIcon(Icons.CHART32), abaGrafico);
				
		add(BorderLayout.CENTER, tabbedPane);
		
		pack();
	}

	public JTable getTabelaParcela() {
		return tabelaParcela;
	}

	public void setTabelaParcela(JTable tabelaParcela) {
		this.tabelaParcela = tabelaParcela;
	}
	
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

	private JPanel panelFechar() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(new Color(162, 205, 90)));
		panel.setPreferredSize(new Dimension(600, 40));
		
		fechar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			
				dispose();
				
			}			
		});
		
		panel.add(fechar);
		
		return panel;
	}
	
	private JPanel panelBotoesParcela() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.setBorder(BorderFactory.createLineBorder(new Color(162, 205, 90)));
		panel.setPreferredSize(new Dimension(600, 40));
		
		JLabel label = new JLabel("Use os botões para adicionar ou excluir parcelas:");
		label.setForeground(Color.BLUE);
		
		panel.add(label);
		panel.add(addParcela);
		panel.add(excParcela);
		
		addParcela.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (!pegaChaveProjetos().equals("")) {
					adicionarParcela(pegaChaveProjetos());
					
				}else{
					JOptionPane.showMessageDialog(null, "Informe o projeto");
				}
				
			}
		});
		
		excParcela.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				excluirParcela();
			}			
		});
	
		return panel;
	}
	
	private JPanel panelBotoesArvore() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.setBorder(BorderFactory.createLineBorder(new Color(162, 205, 90)));
		panel.setPreferredSize(new Dimension(600, 40));
		
		JLabel label = new JLabel("Use os botões para adicionar ou excluir árvores: ");
		label.setForeground(Color.BLUE);
		
		panel.add(label);
		panel.add(addArvore);
		panel.add(excArvore);
		
		addArvore.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					if (parcelaArvoreDAO.numArvores(projetoParcelaVO.getChaveParcela()) < 4) {
						
						adicionarArvore();
					}else{
						JOptionPane.showMessageDialog(null, "Esta parcela já possuí 4 árvores!");
					}
				} catch (Exception e1) { e1.printStackTrace(); } 
				
			}

					
		});
		
		excArvore.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				excluirArvore();
			}

						
		});

		return panel;
	}
	
	private void adicionarParcela(String projeto) {
		
		AdicionaParcela jifAdicionaParcela = null;
		Object[] allFrames = JanelaPrincipal.getDesktopPane().getAllFrames();
		for (Object object : allFrames) {
			if (object instanceof AdicionaParcela) {
				jifAdicionaParcela = (AdicionaParcela)object;
				break;
			}
		}
		
		if (jifAdicionaParcela == null) {
			jifAdicionaParcela = new AdicionaParcela(projeto);
			JanelaPrincipal.getDesktopPane().add(jifAdicionaParcela);
		}
		if (jifAdicionaParcela.isIcon()) {
			try {
				jifAdicionaParcela.setIcon(false);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
		jifAdicionaParcela.setVisible(true);
		jifAdicionaParcela.toFront();
		try {
			jifAdicionaParcela.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		
	}	
	
	private void excluirParcela() {

		int resposta = JOptionPane.showConfirmDialog(null, "Confirma a Exclusão");

		if (resposta == 0) {

			if (projetoParcelaDAO.excluir(pegaChaveParcela())) {
				JOptionPane.showMessageDialog(null, "Parcela Excluída!");
				carregarTabelaParcela();
				
			} else {
				JOptionPane.showMessageDialog(null, "Erro!");
			}

		} else {

		}
	}	
	
	private void adicionarArvore() {
		
		
			jifAdicionaArvore = new AdicionaArvore(tabelaParcela, pegaChaveParcela());
			JanelaPrincipal.getDesktopPane().add(jifAdicionaArvore);

		if (jifAdicionaArvore.isIcon()) {
			try {
				jifAdicionaArvore.setIcon(false);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
		jifAdicionaArvore.setVisible(true);
		jifAdicionaArvore.toFront();
		
		try {
			jifAdicionaArvore.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private void excluirArvore() {
		int resposta = JOptionPane.showConfirmDialog(null, "Confirma a Exclusão");

		if (resposta == 0) {

			if (parcelaArvoreDAO.excluir(pegaChaveArvore())) {
				JOptionPane.showMessageDialog(null, "Árvore Excluída!");
				
				
				try {
					projetoParcelaVO = projetoParcelaDAO.pegaParcela(pegaChaveParcela());
		//			System.out.println(projetoParcelaVO.getChaveParcela());
					tabelaArvores.carregarTabela(projetoParcelaVO.getChaveParcela());
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "Erro!");
			}

		} else {

		}

	}

	private String pegaChaveArvore() {
		Object chave = null;
		// Pega o valor da célula da coluna CÓDIGO na linha selecionada
		if (tabelaArvores.getSelectedRow() >= 0
				&& tabelaArvores.getSelectedRow() <= tabelaArvores.getRowCount()) {
			chave = tabelaArvores.getModel().getValueAt(tabelaArvores.getSelectedRow(), 0);
		}
		return (String) chave;
	}


	protected String pegaChaveParcela() {
		Object chave = null;
		// Pega o valor da célula da coluna CÓDIGO na linha selecionada
		if (tabelaParcela.getSelectedRow() >= 0 && tabelaParcela.getSelectedRow() <= tabelaParcela.getRowCount()) {
			chave = tabelaParcela.getModel().getValueAt(tabelaParcela.getSelectedRow(), 0);
		}
		
		return (String) chave;
	}
	
	protected String pegaChaveProjetos() {
		Object chave = null;
		// Pega o valor da célula da coluna CÓDIGO na linha selecionada
		if (tabelaProjetos.getSelectedRow() >= 0 && tabelaProjetos.getSelectedRow() <= tabelaProjetos.getRowCount()) {
			chave = tabelaProjetos.getModel().getValueAt(tabelaProjetos.getSelectedRow(), 0);
		}
		
		return (String) chave;
	}

	private void inicializar() {
		
		parcelaArvoreDAO = new ParcelaArvoreDAO();
		proprietarioDAO = new PessoaDAO();
		proprietarioVO = new PessoaVO();
		elaboradorVO = new PessoaVO();
		elaboradorDAO = new PessoaDAO();
		municipioDAO = new MunicipioDAO();
		municipioVO = new MunicipioVO();
		
		tabelaProjetos = new JTable();
		
	tabelaProjetos.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						
						carregarTabelaParcela();
						
						tabelaParcela.scrollRectToVisible(tabelaParcela.getCellRect(tabelaParcela.getSelectedRow(), 0, true));
					}					
				});
		
        abaTabelaParcela = new JPanel();
    	tabbedPane = new JTabbedPane();
    	tabelaParcela = new JTable();
    	parcelaDAO = new ParcelaDAO();
    	projetoDAO = new ProjetoDAO();
    	tabelaArvores = new ArvoresDaParcela();
    	
    	addParcela = new JButton("Parcela", Icons.getIcon(Icons.ADICIONAR16));
    	excParcela = new JButton("Parcela", Icons.getIcon(Icons.EXCLUIR16));
    	addArvore = new JButton("Árvore", Icons.getIcon(Icons.ADICIONAR16));
    	excArvore = new JButton("Árvore", Icons.getIcon(Icons.EXCLUIR16));
    	
    	fechar = new JButton("Fechar", Icons.getIcon(Icons.FECHAR16));
    	
    	
    	projetoParcelaDAO = new ProjetoParcelaDAO();
    	
    	
	}

	private JScrollPane montaTabelaParcela() {
		
		
		
		final JScrollPane sp = new JScrollPane(tabelaParcela);

		DefaultTableModel model = new DefaultTableModel(new Object[][] {},
				new Object[] { "", "Área", "Coord 01", "Coord 02", "Coord 03", "Coord 04" }) {

			boolean[] canEdit = new boolean[] { false, false, false, false, false, false };

			// Não permite edição nas células
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		};
		
		for (ParcelaVO parcelaVO : parcelaDAO.pegaLista(pegaChaveProjetos())){
			model.addRow(new String[] { parcelaVO.getChave(), ""+parcelaVO.getArea(), parcelaVO.getCoord1(), parcelaVO.getCoord2(), parcelaVO.getCoord3(), parcelaVO.getCoord4()  });
		}		
		
		tabelaParcela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaParcela.setModel(model);
		tabelaParcela.getTableHeader().setReorderingAllowed(false);
		tabelaParcela.getTableHeader().setResizingAllowed(false);
		tabelaParcela.setSelectionBackground(new Color(162, 205, 90));
		
		tabelaParcela.setBorder(BorderFactory.createEmptyBorder());
		sp.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(162, 205, 90)), " Parcelas do Projeto: "));
		defineRenderersArvore();
		
		
		tabelaParcela.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				try {
					projetoParcelaVO = projetoParcelaDAO.pegaParcela(pegaChaveParcela());
		//			System.out.println(projetoParcelaVO.getChaveParcela());
					tabelaArvores.carregarTabela(projetoParcelaVO.getChaveParcela());
				} catch (SQLException e2) {
					e2.printStackTrace();
				}						
				
				
				
				
			}
		});
		
		tabelaParcela.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						
						tabelaArvores.limpaTabela();
					
						
						// scroll acompanha a linha selecionada
			//			tabelaParcela.scrollRectToVisible(tabelaParcela.getCellRect(tabelaParcela.getSelectedRow(), 0, true));
					}					
				});
		
		
		
		sp.setPreferredSize(new Dimension(600, 120));
		
		return sp;
		
	}

	public ArvoresDaParcela getTabelaArvores() {
		return tabelaArvores;
	}

	public void setTabelaArvores(ArvoresDaParcela tabelaArvores) {
		this.tabelaArvores = tabelaArvores;
	}

	private void defineRenderersArvore() {
			
			DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
			rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
			
			DefaultTableCellRenderer colunaModel = new DefaultTableCellRenderer();
			colunaModel.setForeground(new Color(162, 205, 90));
			
			DefaultTableCellRenderer colunaVermelha = new DefaultTableCellRenderer();
			
			TableColumnModel modeloDaColuna = tabelaParcela.getColumnModel();
			
			colunaModel.setHorizontalAlignment(SwingConstants.CENTER);

			modeloDaColuna.getColumn(0).setMaxWidth(50); // largura fixa
		
			colunaModel.setBackground(new Color(162, 205, 90));
			
			modeloDaColuna.getColumn(0).setCellRenderer(colunaModel);
			
			
			colunaVermelha.setForeground(Color.RED);
			
			modeloDaColuna.getColumn(2).setCellRenderer(colunaVermelha);
			
	}
	
	public void carregarTabelaParcela() {

			
		DefaultTableModel modelo = (DefaultTableModel) tabelaParcela.getModel();
		modelo.setRowCount(0);


		for (ParcelaVO parcelaVO : parcelaDAO.pegaLista(pegaChaveProjetos())){
			modelo.addRow(new String[] { parcelaVO.getChave(), ""+parcelaVO.getArea(), parcelaVO.getCoord1(), parcelaVO.getCoord2(), parcelaVO.getCoord3(), parcelaVO.getCoord4()  });
		}

		tabelaParcela.setModel(modelo);
		
		
	}
	
	protected JScrollPane montaTabelaProjetos() {

		final JScrollPane sp = new JScrollPane(tabelaProjetos,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		DefaultTableModel model = new DefaultTableModel(new Object[][] {},
				new Object[] { "Projeto", "Data", "Cidade", "Proprietário", "Elaborador", "Status" }) {

			boolean[] canEdit = new boolean[] { false, false, false, false,false, false };

			// Não permite edição nas células
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		};

		String data;
		FormataDatas fm = new FormataDatas();
		
		for (ProjetoVO vo : projetoDAO.pegaLista()) {
			String avaliacao = "AV";
			if (!vo.isAvaliacao()) { avaliacao = "NV"; }
			try {
				municipioVO = (MunicipioVO) municipioDAO.ler("" + vo.getMunicipio());
				proprietarioVO = (PessoaVO) proprietarioDAO.ler("" + vo.getProprietario());
				elaboradorVO = (PessoaVO) elaboradorDAO.ler("" + vo.getElaborador());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			data = fm.mostraDataMySql(vo.getData()); 
			model.addRow(new String[] { vo.getCodigo(), data, municipioVO.getNome(), proprietarioVO.getNome(), elaboradorVO.getNome(), avaliacao });
		}
		
		tabelaProjetos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaProjetos.setModel(model);
		tabelaProjetos.getTableHeader().setReorderingAllowed(false);
		tabelaProjetos.getTableHeader().setResizingAllowed(false);
		tabelaProjetos.setSelectionBackground(new Color(162, 205, 90));

		tabelaProjetos.setBorder(BorderFactory.createEmptyBorder());
		sp.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(162, 205, 90)), "Projetos Cadastrados: "));
		defineRenderersProjeto();
		tabelaProjetos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tabelaArvores.limpaTabela();
				carregarTabelaParcela();
			}
		});
		
		tabelaProjetos.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
					//	lerDadosDoBanco();
						// scroll acompanha a linha selecionada
						tabelaArvores.limpaTabela();
						carregarTabelaParcela();
						tabelaProjetos.scrollRectToVisible(tabelaProjetos.getCellRect(tabelaProjetos.getSelectedRow(), 0, true));
					}						
				});
		
		sp.setPreferredSize(new Dimension(600, 120));
		if (tabelaProjetos.getRowCount() > 0) {
			tabelaProjetos.setRowSelectionInterval(0, 0);
		}		
		
		return sp;
}
	private void defineRenderersProjeto() {
		
		DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
		rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
		DefaultTableCellRenderer colunaModel = new DefaultTableCellRenderer();
		
		TableColumnModel modeloDaColuna = tabelaProjetos.getColumnModel();
		modeloDaColuna.getColumn(0).setCellRenderer(rendererCentro); // alin. centro																			
		modeloDaColuna.getColumn(5).setCellRenderer(rendererCentro); // alin. centro
																		
		modeloDaColuna.getColumn(0).setMaxWidth(50); // largura fixa
		modeloDaColuna.getColumn(1).setMaxWidth(75); // largura fixa
		modeloDaColuna.getColumn(5).setMaxWidth(50); // largura fixa

		colunaModel.setHorizontalAlignment(SwingConstants.CENTER);
	
		colunaModel.setBackground(new Color(162, 205, 90));
		modeloDaColuna.getColumn(0).setCellRenderer(colunaModel);
}

public void carregarTabelaProjeto() {

	DefaultTableModel modelo = (DefaultTableModel) tabelaProjetos.getModel();
	modelo.setRowCount(0);

	for (ProjetoVO vo : projetoDAO.pegaLista()) {
		String avaliacao = "AV";
		if (!vo.isAvaliacao()) { avaliacao = "NV"; }
		
		try {
			municipioVO = (MunicipioVO) municipioDAO.ler(""+vo.getMunicipio());
			proprietarioVO = (PessoaVO) proprietarioDAO.ler(""+vo.getProprietario());
			elaboradorVO = (PessoaVO) elaboradorDAO.ler(""+vo.getElaborador());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		modelo.addRow(new String[] { vo.getCodigo(), ""+vo.getData(), municipioVO.getNome(), proprietarioVO.getNome(), elaboradorVO.getNome(), avaliacao });
	}
		tabelaProjetos.setModel(modelo);

	}

}
