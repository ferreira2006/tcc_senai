package gui.cadastros;

import gui.BarraDeMenu;
import gui.relatorios.Grafico_Projetos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
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
import javax.swing.text.MaskFormatter;

import resources.AcoesDaTabela;
import resources.FormataDatas;
import resources.Funcoes;
import resources.Icons;
import db.MunicipioDAO;
import db.MunicipioVO;
import db.PessoaDAO;
import db.PessoaVO;
import db.ProjetoDAO;
import db.ProjetoVO;

@SuppressWarnings("serial")
public class CadastroDeProjeto extends CadastroAbstrato {
	private JTextField projeto, endereco, numero, bairro, coord1, coord2, coord3, coord4, areaTotal, areaDegradada;
	private JFormattedTextField data;
	private JComboBox proprietario, elaborador, municipios;
	private ButtonGroup bg, gbApp;
	private JRadioButton laudoSim, laudoNao, AppSim, AppNao;
	private JButton cadastraPessoa, atualizar;
	private Funcoes funcoes;
	private JPanel abaNovo, abaProjetos, abaGrafico, panelCadastro, pessoas, localData;
	private JTable tabelaProjeto;	
	private ProjetoDAO projetoDAO;
	private ProjetoVO projetoVO;
	private PessoaDAO proprietarioDAO;
	private PessoaVO proprietarioVO;
	private PessoaVO elaboradorVO;
	private PessoaDAO elaboradorDAO;
	private MunicipioDAO municipioDAO;
	private MunicipioVO municipioVO;
	private JTabbedPane tabbedPane;
	private AcoesDaTabela acoesDaTabelaProjeto;
	private String hoje;
	private GridBagConstraints gbc;

	public CadastroDeProjeto() {		
		super("Novo Projeto");
		setFrameIcon(Icons.getIcon(Icons.FOLHA));
		
		setIconifiable(false);
		setMaximizable(false);		
		
		inicializar();
		
		personalizaAba(tabbedPane);
		
		abaNovo.add(montaCadastro());
		
		abaProjetos.setLayout(new GridLayout(0,1));        
        abaProjetos.add(montaTabela());
       
        
        tabbedPane.addTab("Cadastro de Projetos", Icons.getIcon(Icons.NOVO), abaNovo);
        tabbedPane.addTab("Projetos Cadastrados", Icons.getIcon(Icons.NOVO), abaProjetos);        
        tabbedPane.addTab("Projetos por Municípios", Icons.getIcon(Icons.CHART32), abaGrafico);
        
     
		add(BorderLayout.CENTER, tabbedPane);			
		pack();
		
		
	}

	public void atualizaCombos() {
		pessoas.remove(proprietario);
		pessoas.remove(elaborador);
		
		proprietario = new JComboBox(proprietarioDAO.pegaLista(false)); // false não sel. elaboradores
		adicionar(pessoas, "Proprietário:", "Selecione o proprietário", proprietario, 1, 2, 1, gbc);
		elaborador = new JComboBox(elaboradorDAO.pegaLista(true)); // false não sel. elaboradores
		adicionar(pessoas, "Elaborador:", "Selecione o elaborador", elaborador, 1, 2, 2, gbc);
	}
	
	private void inicializar() {
		
		tabelaProjeto = new JTable();
		funcoes = new Funcoes();
		municipioDAO = new MunicipioDAO();
		municipioVO = new MunicipioVO();
		projetoDAO = new ProjetoDAO();
		projetoVO = new ProjetoVO();
		elaboradorVO = new PessoaVO();
		elaboradorDAO = new PessoaDAO();
		proprietarioVO = new PessoaVO();
		proprietarioDAO = new PessoaDAO();
		tabbedPane = new JTabbedPane();
		abaNovo = new JPanel();
        abaProjetos = new JPanel();
        abaGrafico = new JPanel();
        acoesDaTabelaProjeto = new AcoesDaTabela(tabelaProjeto);
     //   acoesDaTabelaParcela = new AcoesDaTabela(tabelaParcela);
        gbc = new GridBagConstraints();
		
        
        abaGrafico.setLayout(new BorderLayout());
        atualizaGrafico();        
	}

	private void atualizaGrafico() {
		abaGrafico.removeAll();
		try {
			abaGrafico.add(new Grafico_Projetos().montaGrafico());
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	private JPanel montaCadastro() {
		
		panelCadastro = new JPanel();
		panelCadastro.setLayout(new GridBagLayout());
		
		localData = new JPanel();
		JPanel coordenadas = new JPanel();
		pessoas = new JPanel();

		// local e data
		localData.setBorder(BorderFactory.createTitledBorder(" Localização: "));
		localData.setLayout(new GridBagLayout());
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5, 5, 5, 5);		

		projeto = new JTextField(10);
		projeto.setEditable(false);
		
		try {	    	
	    	MaskFormatter mk = new MaskFormatter("##/##/####");
	    	data = new JFormattedTextField(mk);
	    	data.setColumns(8);		    	
	    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        hoje = sdf.format(new Date());   
	        data.setValue(hoje);	        
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		endereco = new JTextField(20);
		numero =  new JTextField(5);
		bairro = new JTextField(20);
		municipios = new JComboBox(municipioDAO.pegaLista());
		municipios.setSelectedIndex(256-1); // JComboBox inicia com 0
		areaTotal = new JTextField(5);
		areaDegradada = new JTextField(5);
				
		adicionar(localData, "Projeto:", "Número gerado automaticamente", projeto, 1, 2, 1, gbc);
		adicionar(localData, "Data:", "Informe a data do Projeto", data, 3, 4, 1, gbc);
		adicionar(localData, "Endereço:", "Informe o nome da rua (Rodovia - Estrada)", endereco, 1, 2, 2, gbc);
		adicionar(localData, "Número:", "Informe o nº (km) da residência", numero, 3, 4, 2, gbc);
		adicionar(localData, "Bairro:", "Informe o nome do Bairro (comunidade)", bairro, 1, 2, 3, gbc);
		adicionar(localData, "Municipio:", "Selecione o município", municipios, 1, 2, 4, gbc);
		adicionar(localData, "Área Total (m²):", "Informe o tamanho da propriedade", areaTotal, 1, 2, 5, gbc);
		adicionar(localData, "Área Degradada (m²):", "Selecione o município", areaDegradada, 3, 4, 5, gbc);

		// coordenadas do projeto
		coordenadas.setBorder(BorderFactory.createTitledBorder(" Coordenadas do Projeto: "));
		coordenadas.setLayout(new GridBagLayout());		
		gbc.fill = GridBagConstraints.BOTH;
		
		coord1 = new JTextField(10);
		coord2 = new JTextField(10);
		coord3 = new JTextField(10);
		coord4 = new JTextField(10);
		
	
		adicionar(coordenadas, "Coord.1:", "Informe a coordenada 01 do Projeto", coord1, 1, 2, 1, gbc);
		adicionar(coordenadas, "Coord.2:", "Informe a coordenada 02 do Projeto", coord2, 3, 4, 1, gbc);
		adicionar(coordenadas, "Coord.3:", "Informe a coordenada 03 do Projeto", coord3, 1, 2, 2, gbc);
		adicionar(coordenadas, "Coord.4:", "Informe a coordenada 04 do Projeto", coord4, 3, 4, 2, gbc);
		

		// pessoas envolvidas
		pessoas.setBorder(BorderFactory .createTitledBorder(" Pessoas Envolvidas: "));
		pessoas.setLayout(new GridBagLayout());

		proprietario = new JComboBox(proprietarioDAO.pegaLista(false)); // false não sel. elaboradores
		proprietario.setEnabled(false);
		cadastraPessoa = new JButton("Cadastro");
		
		atualizar = new JButton("Atualizar");
		
		cadastraPessoa.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				proprietario.setEnabled(false);
				elaborador.setEnabled(false);
				cadastraPessoa.setEnabled(false);
				atualizar.setForeground(Color.RED);
				BarraDeMenu.cadastraPessoa();				
			}
		});
		
		elaborador = new JComboBox(proprietarioDAO.pegaLista(true)); //sel. somente elaboradoes
		elaborador.setEnabled(false);
		atualizar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "O sistema atualizou as caixas de seleção");
				proprietario.setEnabled(true);
				elaborador.setEnabled(true);
				atualizar.setForeground(Color.BLACK);
				atualizar.setFont(cadastraPessoa.getFont());
				atualizaCombos();				
				cadastraPessoa.setEnabled(true);
				proprietario.requestFocus();
			}
		});
		laudoSim = new JRadioButton("Sim", true);
		destacaComponente(laudoSim);		
		laudoNao = new JRadioButton("Não");
		destacaComponente(laudoNao);
		bg = new ButtonGroup();
		bg.add(laudoSim); bg.add(laudoNao);
		
		AppSim = new JRadioButton("Sim");
		destacaComponente(AppSim);		
		AppNao = new JRadioButton("Não", false);
		destacaComponente(AppNao);
		gbApp = new ButtonGroup();
		gbApp.add(AppSim); gbApp.add(AppNao);
		
		
		adicionar(pessoas, "Proprietário:", "Selecione o proprietário", proprietario, 1, 2, 1, gbc);
		adicionar(pessoas, "", "Clique para cadastrar uma pessoa", cadastraPessoa, 3, 4, 1, gbc);
		adicionar(pessoas, "Elaborador:", "Selecione o elaborador", elaborador, 1, 2, 2, gbc);
		adicionar(pessoas, "", "Clique para atualizar a listagem de pessoas", atualizar, 3, 4, 2, gbc);
		adicionar(pessoas, "O local possibilita a realização do laudo?", "", laudoSim, 2, 3, 3, gbc);
		gbc.gridx = 4;	gbc.gridy = 3;
		pessoas.add(laudoNao, gbc);
		
		adicionar(pessoas, "Houve degradação em APP?:", "", AppSim, 2, 3, 4, gbc);
		gbc.gridx = 4;	gbc.gridy = 4;
		pessoas.add(AppNao, gbc);
		
		adicionar(panelCadastro, "", "", localData, 1, 2, 1, gbc);
		adicionar(panelCadastro, "", "", coordenadas, 1, 2, 2, gbc);
		adicionar(panelCadastro, "", "", pessoas, 1, 2, 3, gbc);

		funcoes.addStatusFocusListener(localData, bs.jttt);
		funcoes.addStatusFocusListener(coordenadas, bs.jttt);
		funcoes.addStatusFocusListener(pessoas, bs.jttt);
		
		addFocusListnerContainer(localData);
		addFocusListnerContainer(coordenadas);
		addFocusListnerContainer(pessoas);
		
		return panelCadastro;
	}
	private void destacaComponente(JComponent componente) {
		componente.setFont(new Font(Font.SERIF, Font.BOLD, 12));
		componente.setForeground(Color.BLUE);		
	}

	protected JScrollPane montaTabela() {

		final JScrollPane sp = new JScrollPane(tabelaProjeto,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		DefaultTableModel model = new DefaultTableModel(new Object[][] {},
				new Object[] { "Chave", "Data", "Cidade", "Proprietário", "Elaborador", "Status" }) {

			boolean[] canEdit = new boolean[] { false, false, false, false,false, false };

			// Não permite edição nas células
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		};

		String data;
		FormataDatas fd = new FormataDatas();
		
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
			data = fd.mostraDataMySql(vo.getData());
			model.addRow(new String[] { vo.getCodigo(), data, municipioVO.getNome(), proprietarioVO.getNome(), elaboradorVO.getNome(), avaliacao });
		}
		
		tabelaProjeto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaProjeto.setModel(model);
		tabelaProjeto.getTableHeader().setReorderingAllowed(false);
		tabelaProjeto.getTableHeader().setResizingAllowed(false);
		tabelaProjeto.setSelectionBackground(super.getCorPadrao());

		tabelaProjeto.setBorder(BorderFactory.createEmptyBorder());
		sp.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(super.getCorPadrao()), "Projetos Cadastrados: "));
		defineRenderers();
		tabelaProjeto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
	//			lerDadosDoBanco();  // mesma função do listener debaixo
			}
		});
		
		tabelaProjeto.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						lerDadosDoBanco();
						// scroll acompanha a linha selecionada
						tabelaProjeto.scrollRectToVisible(tabelaProjeto.getCellRect(tabelaProjeto.getSelectedRow(), 0, true));
					}						
				});
		
		sp.setPreferredSize(new Dimension(600, 120));
		if (tabelaProjeto.getRowCount() > 0) {
			tabelaProjeto.setRowSelectionInterval(0, 0);
		}		
		
		return sp;

}

		public JTable getTabelaProjeto() {
		return tabelaProjeto;
	}

	public void setTabelaProjeto(JTable tabelaProjeto) {
		this.tabelaProjeto = tabelaProjeto;
	}

		private void defineRenderers() {
			
			DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
			rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
			DefaultTableCellRenderer colunaModel = new DefaultTableCellRenderer();
			
			TableColumnModel modeloDaColuna = tabelaProjeto.getColumnModel();
			modeloDaColuna.getColumn(0).setCellRenderer(rendererCentro); // alin. centro																			
			modeloDaColuna.getColumn(5).setCellRenderer(rendererCentro); // alin. centro
																			
			modeloDaColuna.getColumn(0).setMaxWidth(50); // largura fixa
			modeloDaColuna.getColumn(1).setMaxWidth(75); // largura fixa
			modeloDaColuna.getColumn(5).setMaxWidth(50); // largura fixa

			colunaModel.setHorizontalAlignment(SwingConstants.CENTER);
		
			colunaModel.setBackground(super.getCorPadrao());
			modeloDaColuna.getColumn(0).setCellRenderer(colunaModel);
	}

	public void carregarTabela(JTable tbl) {

		DefaultTableModel modelo = (DefaultTableModel) tbl.getModel();
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
		tbl.setModel(modelo);
	//	if (tbl.getRowCount() > 0) {
	//		tbl.setRowSelectionInterval(0, 0);
	//	}
		
	}
			
	public void avancaTabela() {
		acoesDaTabelaProjeto.avancaTabela();
	
	}

	public void retroTabela() {
		acoesDaTabelaProjeto.retroTabela();
	}
	
	protected boolean lerDadosDoBanco() {
		if (tabelaProjeto.getRowCount() > 0) {
			try {
				projetoVO = (ProjetoVO) projetoDAO.ler(acoesDaTabelaProjeto.pegaChave());
				if (projetoVO != null) {
					mostrarNaTela();
				} 
			} catch (SQLException e) {
				e.printStackTrace();
				erroBancoDeDados(this);
				return false;
			}
		}

		return projetoVO != null;
	}

	@Override
	protected void excluirDoBanco() {
		//	if (validarDados(codigo)) {
		if (!projeto.getText().equals("")){
			if (lerDadosDoBanco()) {
				if (!perguntaExcluiCadastro(this, projeto.getText()))
					return;
				if (projetoDAO.excluir(projeto.getText())) {					
					mensagemCadastroExcluido(this);					
					limparTela();					
				} else {
					erroBancoDeDados(this);
				}
				carregarTabela(tabelaProjeto);
			}
		}else{
			JOptionPane.showMessageDialog(this, "Selecione um projeto na aba /nProjetos Cadastrados para exclusão");
		}
	//	}	
			data.requestFocus();
			atualizaGrafico();
	}

	@Override
	protected void limparTela() {
		projeto.setText("");
		data.setValue(hoje);
		endereco.setText("");
		numero.setText("");
		bairro.setText("");
		coord1.setText("");
		coord2.setText("");
		coord3.setText("");
		coord4.setText("");
		areaTotal.setText("");
		municipios.setSelectedIndex(256-1); // JComboBox inicia com 0
		laudoSim.setSelected(true);
		areaDegradada.setText("");
		AppNao.setSelected(true);
		data.requestFocus();
		proprietario.setEnabled(false);
		elaborador.setEnabled(false);
	}

	protected void mostrarNaTela() {
		
	//	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");   
	//	String myDate = sdf.format(projetoVO.getData());   
		
		projeto.setText(projetoVO.getChave());
		
				
		
		data.setValue(new FormataDatas().mostraDataMySql(projetoVO.getData()));
		endereco.setText(projetoVO.getEndereco());
		numero.setText(projetoVO.getNumero());
		bairro.setText(projetoVO.getBairro());
		municipios.setSelectedIndex(projetoVO.getMunicipio()-1); // o combo inicia com 0
		coord1.setText(projetoVO.getCoord1());
		coord2.setText(projetoVO.getCoord2());
		coord3.setText(projetoVO.getCoord3());
		coord4.setText(projetoVO.getCoord4());
		areaTotal.setText(""+projetoVO.getAreaTotal());
		if (projetoVO.isAvaliacao()) {
			laudoSim.setSelected(true);
		}else{
			laudoNao.setSelected(true);
		}
		areaDegradada.setText(""+projetoVO.getAreaDegradada());
		if (projetoVO.isDegradacaoEmApp()) {
			AppSim.setSelected(true);
		}else{
			AppNao.setSelected(true);
		}
		proprietario.setSelectedIndex(1);
		elaborador.setSelectedIndex(1);
		
		
			
		data.requestFocus();
	}

	@Override
	protected void novoCadastro() {
		tabbedPane.setSelectedComponent(abaNovo);		
		limparTela();
		proprietario.setEnabled(true);
		elaborador.setEnabled(true);
	}

	protected void salvarDadosNoBanco() {
		if (proprietario.isEnabled()) {
			projetoVO = new ProjetoVO();
			if (validarDados(null)) {

				projetoVO.setData(new FormataDatas().salvaDataBanco(data.getText()));
				projetoVO.setEndereco(endereco.getText());
				projetoVO.setNumero(numero.getText());
				projetoVO.setBairro(bairro.getText());
				projetoVO.setMunicipio(municipios.getSelectedIndex() + 1);
				projetoVO.setCoord1(coord1.getText());
				projetoVO.setCoord2(coord2.getText());
				projetoVO.setCoord3(coord3.getText());
				projetoVO.setCoord4(coord4.getText());
				projetoVO.setAreaTotal(Double.parseDouble(areaTotal.getText()));
				projetoVO.setAvaliacao(true);
				
				if (laudoNao.isSelected()) {
					projetoVO.setAvaliacao(false);
				}
				
				projetoVO.setEstagio(4);
				projetoVO.setAreaDegradada(Double.parseDouble(areaDegradada.getText()));
				
				if (AppSim.isSelected()) {
					projetoVO.setDegradacaoEmApp(true);
				} else {
					projetoVO.setDegradacaoEmApp(false);
				}

				PessoaVO proprietarioVO = (PessoaVO) proprietario.getSelectedItem();

				int codigoProprietario = 0;
				
				try {
					codigoProprietario = Integer.parseInt(proprietarioVO.getCodigo());
				} catch (Exception e) {
					e.printStackTrace();
				}

				projetoVO.setProprietario(codigoProprietario);

				PessoaVO elaboradorVO = (PessoaVO) elaborador.getSelectedItem();

				int codigoElaborador = 0;
				
				try {
					codigoElaborador = Integer.parseInt(elaboradorVO
							.getCodigo());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				projetoVO.setElaborador(codigoElaborador);
				projetoVO.setDataExclusao(null);

				try {
					if (projetoDAO.salvar(projetoVO, projeto.getText())) {
						mensagemCadastroGravado(this);
						limparTela();
						carregarTabela(tabelaProjeto);
					} else {
						erroBancoDeDados(this);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					System.out
							.println("erro ao pegar chave em codigo.getText()");
				}
			}
			atualizaGrafico();
		}
	}

	protected boolean validarDados(Component c) {
		boolean result = true;
		 
			// data
			if ("  /  /    ".equals(data.getText())) { //se o campo sem espaços excedentes é vazio
				return mensagemErroInformeCampo(this, data);//retorna mensagem de erro
			}

			// endereço
			if ("".equals(endereco.getText())) {
				return mensagemErroInformeCampo(this, endereco);
			}

			// numero
			if ("".equals(numero.getText())) {
				return mensagemErroInformeCampo(this, numero);
			}

			// bairro
			if ("".equals(bairro.getText().trim())) {
				return mensagemErroInformeCampo(this, bairro);
			}
			
			return result;
	}
	
	protected String pegaChave() {
		Object chave = null;
		// Pega o valor da célula da coluna CÓDIGO na linha selecionada
		if (tabelaProjeto.getSelectedRow() >= 0 && tabelaProjeto.getSelectedRow() <= tabelaProjeto.getRowCount()) {
			chave = tabelaProjeto.getModel().getValueAt(tabelaProjeto.getSelectedRow(), 0);
		}
		
		return (String) chave;
	}
}
