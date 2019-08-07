package gui.cadastros;


import gui.relatorios.Grafico_Pessoas;
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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
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
import javax.swing.text.MaskFormatter;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import resources.AcoesDaTabela;
import resources.AlteraTipoCursor;
import resources.FormataDatas;
import resources.Funcoes;
import resources.Icons;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import db.BD;
import db.MunicipioDAO;
import db.PessoaDAO;
import db.PessoaVO;

@SuppressWarnings("serial")
public class CadastroDePessoa extends CadastroAbstrato{
	
	private JTextField codigo, nome, rg, endereco, numero, bairro, email;
	private JCheckBox elaborador;
	private JComboBox municipio;
	private JPanel abaCadastro, abaTabela, abaRelatorio, abaGrafico;
	private Funcoes funcoes;
	private JTable tabela;
	private PessoaDAO pessoaDAO;
	private PessoaVO pessoaVO;	
	private JTabbedPane tabbedPane;
	private AcoesDaTabela acoesDaTabela;
	private JRadioButton elab, prop, todos;
	public final String RELATORIO = "pessoas";	
	private Map<Object, Object> parametros;
	private JFormattedTextField cpf, telRes, telCom, telCel, dataNasc;
	private MaskFormatter dataMascara, cpfMascara, telefoneMascara;
	
	
	
	public CadastroDePessoa() {
		
		super("Cadastro de Pessoas");
		 
		setFrameIcon(Icons.getIcon(Icons.PESSOA));
		
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
		
		tabbedPane.addTab("Cadastro de Pessoas", Icons.getIcon(Icons.PESSOA), abaCadastro);
		tabbedPane.addTab("Pessoas Cadastradas", Icons.getIcon(Icons.PESSOAS), abaTabela);
		tabbedPane.addTab("Relatório Pessoal", Icons.getIcon(Icons.RELATORIO), abaRelatorio);
		tabbedPane.addTab("Gráfico por Qualificação", Icons.getIcon(Icons.CHART32), abaGrafico);
		
		
		add(BorderLayout.CENTER, tabbedPane);
		
		pack();
	
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
		
		if (todos.isSelected()) {
			query =
				"SELECT pessoa.chave, pessoa.nome, pessoa.rg, pessoa.cpf, pessoa.endereco, pessoa.numero, pessoa.bairro," +					
				" municipio.nome AS municipio, pessoa.telRes, pessoa.telCom, pessoa.telCel, pessoa.email FROM pessoa," + 
				" municipio WHERE pessoa.municipio = municipio.chave ORDER BY pessoa.nome";
		}else {
			if (prop.isSelected()) {
				query =
					"SELECT pessoa.chave, pessoa.nome, pessoa.rg, pessoa.cpf, pessoa.endereco, pessoa.numero, pessoa.bairro," +					
					" municipio.nome AS municipio, pessoa.telRes, pessoa.telCom, pessoa.telCel, pessoa.email FROM pessoa," + 
					" municipio WHERE elaborador = '0' AND pessoa.municipio = municipio.chave ORDER BY pessoa.nome";
			}else {
				query =
					"SELECT pessoa.chave, pessoa.nome, pessoa.rg, pessoa.cpf, pessoa.endereco, pessoa.numero, pessoa.bairro," +					
					" municipio.nome AS municipio, pessoa.telRes, pessoa.telCom, pessoa.telCel, pessoa.email FROM pessoa," + 
					" municipio WHERE elaborador = '1' AND pessoa.municipio = municipio.chave ORDER BY pessoa.nome";
					
				
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
				
				jasperViewer.setTitle("ARBOR - PESSOAS CADASTRADAS");
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

	private JScrollPane montaTabela() {

			final JScrollPane sp = new JScrollPane(tabela,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

			DefaultTableModel model = new DefaultTableModel(new Object[][] {},
					new Object[] { "Chave", "Nome", "Documento", "Celular", "e-mail" }) {

				boolean[] canEdit = new boolean[] { false, false, false, false, false };

				// Não permite edição nas células
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return canEdit[columnIndex];
				}
			};

		//	for (PessoaVO vo : pessoaDAO.pegaLista(false)) {
		//		model.addRow(new String[] { vo.getChave(), vo.getNome(), vo.getRg(), vo.getTelCel(), vo.getEmail() });
		//	}
			
			Vector<PessoaVO> vector = pessoaDAO.pegaLista(false); // false = todos
			
			for (int i = 0; i < vector.size(); i++) {
				PessoaVO vo = vector.elementAt(i);
				
				model.addRow(new String[] { vo.getChave(), vo.getNome(), vo.getRg(), vo.getTelCel(), vo.getEmail() }); 
				}
			
			tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tabela.setModel(model);
			tabela.getTableHeader().setReorderingAllowed(false);
			tabela.getTableHeader().setResizingAllowed(false);
			tabela.setSelectionBackground(super.getCorPadrao());

			tabela.setBorder(BorderFactory.createEmptyBorder());
			sp.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(super.getCorPadrao()), " Pessoas Cadastradas: "));
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
							tabela.scrollRectToVisible(tabela.getCellRect(tabela.getSelectedRow(), 0, true));
						}						
					});
			
			sp.setPreferredSize(dimensaoPadrao(this));
			if (tabela.getRowCount() > 0) {
				tabela.setRowSelectionInterval(0, 0);
			}
			return sp;
	
	}
		
	private void defineRenderers() {
			
			DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
			rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
			DefaultTableCellRenderer colunaModel = new DefaultTableCellRenderer();
			
			TableColumnModel modeloDaColuna = tabela.getColumnModel();
			modeloDaColuna.getColumn(0).setCellRenderer(rendererCentro); // alin. centro
																			
																			
			modeloDaColuna.getColumn(0).setMaxWidth(50); // codigo largura fixa
			modeloDaColuna.getColumn(3).setMaxWidth(150); // celular largura fixa
			
			colunaModel.setHorizontalAlignment(SwingConstants.CENTER);		
			colunaModel.setBackground(super.getCorPadrao());			
			modeloDaColuna.getColumn(0).setCellRenderer(colunaModel);
		

	}
		
	public void carregarTabela(JTable tbl) {

		DefaultTableModel modelo = (DefaultTableModel) tbl.getModel();
		modelo.setRowCount(0);

		for (PessoaVO vo : pessoaDAO.pegaLista(false)) {
			modelo.addRow(new String[] { vo.getChave(), vo.getNome(), vo.getRg(), vo.getTelCel(), vo.getEmail() });
		}
		tbl.setModel(modelo);
	}
		
	public void avancaTabela() {
		acoesDaTabela.avancaTabela();
	}

	public void retroTabela() {
		acoesDaTabela.retroTabela();
	}
		
	private JPanel montaCadastro() {
		
		codigo = new JTextField(5);
		codigo.setEnabled(false);
		nome = new JTextField(25);
		nome.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent arg0) {				
				nome.selectAll();
			}});
		
		try {
			cpfMascara = new MaskFormatter("###.###.###-##");
			telefoneMascara = new MaskFormatter("(##)####-####");
			dataMascara = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		dataNasc = new JFormattedTextField(dataMascara);
		dataNasc.setColumns(8);
		rg = new JTextField(15);
		cpf = new JFormattedTextField(cpfMascara);
		cpf.setColumns(11);
		endereco = new JTextField(25);
		numero = new JTextField(5);
		bairro = new JTextField(20);		
		MunicipioDAO municipioDAO = new MunicipioDAO();
		municipio = new JComboBox(municipioDAO.pegaLista());
		municipio.setSelectedIndex(255);
		telRes = new JFormattedTextField(telefoneMascara);
		telRes.setColumns(11);
		telCom = new JFormattedTextField(telefoneMascara);
		telCom.setColumns(11);
		telCel = new JFormattedTextField(telefoneMascara);
		telCel.setColumns(11);
		email = new JTextField(25);
		elaborador = new JCheckBox();
	
		
		JPanel painel = new JPanel(new GridBagLayout());
		
		painel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(super.getCorPadrao()), " Dados do Usuário: "));

		GridBagConstraints gbc = new GridBagConstraints();		
		
	adicionar(painel, "Chave:", "O sistema gera automaticamente o código", codigo, 	1, 2, 1, gbc);
	adicionar(painel, "Nome:", "Informe o nome para cadastro", nome, 				3, 4, 1, gbc);
	adicionar(painel, "Data Nasc.:", "Informe a data de nascimento", dataNasc, 		1, 2, 2, gbc);
	adicionar(painel, "Doc. Identidade:", "Informe o nº do documento pessoal", rg, 	3, 4, 2, gbc);
	adicionar(painel, "CPF:", "Informe o CPF", cpf, 								1, 2, 3, gbc);
	adicionar(painel, "Endereço:", "Informe o nome da Rua", endereco,				3, 4, 3, gbc);
	adicionar(painel, "Núm.:", "Informe o nº da residência", numero,				1, 2, 4, gbc);
	adicionar(painel, "Bairro:", "Informe o Bairro de residência", bairro,  		3, 4, 4, gbc);
	adicionar(painel, "Município:", "Informe o Município de residência", municipio,	1, 2, 5, gbc);
	adicionar(painel, "Tel. Resid..:", "Informe o nº do tel. residencial",  telRes,	3, 4, 5, gbc);
	adicionar(painel, "Tel. Comer.:", "Informe o nº do tel. comercial",  telCom,	1, 2, 6, gbc);
	adicionar(painel, "Tel. Celul.:", "Informe o nº do tel. comercial",  telCel,	3, 4, 6, gbc);
	adicionar(painel, "E-mail:", "Informe um e-mail para contato",  email,			1, 2, 7, gbc);	
	adicionar(painel, "Elaborador:", "Marcado = usuário do sistema", elaborador,	3, 4, 7, gbc);
			
		funcoes.addStatusFocusListener(painel, bs.jttt);
	//	painel.setPreferredSize(dimensaoPadrao(this)); // panel maior que o padrao
		
		addFocusListnerContainer(painel);
		
		return painel;
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
	
	private JPanel montaRelatorio(){
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(" Opções de Relatório: "));
		panel.setLayout(new GridLayout(0,1));
		ButtonGroup gb = new ButtonGroup();
		elab = new JRadioButton("Somente Elaboradores");		
		prop = new JRadioButton("Somente Proprietários");
		todos = new JRadioButton("Todas as pessoas Cadastradas");
		todos.setSelected(true);
		
		gb.add(todos);
		gb.add(elab);
		gb.add(prop);
		
		panel.add(elab);
		panel.add(prop);
		panel.add(todos);
		
		return panel;
	}

	protected void limparTela() {
		codigo.setText("");
		nome.setText("");
		dataNasc.setValue(null);
		rg.setText("");
		cpf.setValue(null);
		endereco.setText("");
		numero.setText("");
		bairro.setText("");
		municipio.setSelectedIndex(255);
		telCom.setValue(null);
		telRes.setValue(null);
		telCel.setValue(null);
		email.setText("");		
		elaborador.setSelected(false);
		nome.requestFocus();
	}

	protected boolean validarDados(Component c) {
		boolean result = true;
	 
	// nome
		if ("".equals(nome.getText().trim())) { //se o campo sem espaços excedentes é vazio
			return mensagemErroInformeCampo(this, nome);//retorna mensagem de erro
		}

		// data
	//	if ("".equals(dataNasc.getText())) {
	//		return mensagemErroInformeCampo(this, dataNasc);
	//	}

	//	// rg
	//	if ("".equals(rg.getText())) {
	//		return mensagemErroInformeCampo(this, rg);
	//	}

		// cpf
	//	if ("".equals(cpf.getText().trim())) {
	//		return mensagemErroInformeCampo(this, cpf);
	//	}
		
		// endereco
		if ("".equals(endereco.getText().trim())) {
			return mensagemErroInformeCampo(this, endereco);
		}
		// num
	//	if ("".equals(numero.getText().trim())) {
	//		return mensagemErroInformeCampo(this, numero);
	//	}
		// bairro
	//	if ("".equals(bairro.getText().trim())) {
	//		return mensagemErroInformeCampo(this, bairro);
	//	}
		// cidade
		//if ("".equals(municipio.getText().trim())) {
		//	return mensagemErroInformeCampo(this, municipio);
		//}
		// telres
	//	if ("".equals(telRes.getText().trim())) {
	//		return mensagemErroInformeCampo(this, telRes);
	//	}
		// telcom
	//	if ("".equals(telCom.getText().trim())) {
	//		return mensagemErroInformeCampo(this, telCom);
	//	}
		// telcel
	//	if ("".equals(telCel.getText().trim())) {
	//		return mensagemErroInformeCampo(this, telCel);
	//	}
		// email
	//	if ("".equals(email.getText().trim())) {
	//		return mensagemErroInformeCampo(this, email);
	//	}
		// elaborador
	//	if ("".equals(elaborador.getText().trim())) {
	//		return mensagemErroInformeCampo(this, elaborador);
	//	}

		return result;
	}
	
	private void inicializar() {
		pessoaDAO = new PessoaDAO();
		pessoaVO = new PessoaVO();
		abaCadastro = new JPanel();
        abaTabela = new JPanel();
        abaRelatorio = new JPanel();
        abaGrafico = new JPanel();
    	funcoes = new Funcoes();
    	tabbedPane = new JTabbedPane();
    	tabela = new JTable();
    	acoesDaTabela = new AcoesDaTabela(tabela);
    	
    	abaGrafico.setLayout(new BorderLayout());
    	atualizaGrafico();
    	
    	try {	    	
	    	MaskFormatter mk = new MaskFormatter("##/##/####");
	    	dataNasc = new JFormattedTextField(mk);
	    	dataNasc.setColumns(8);		    	
	    		        
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
	}
	
	protected void atualizaGrafico() {
		try {
			abaGrafico.removeAll();
			abaGrafico.add(new Grafico_Pessoas().montaGrafico());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void excluirDoBanco() {
	//	if (validarDados(codigo)) {
		if (!codigo.getText().equals("")){
			if (lerDadosDoBanco()) {
				if (!perguntaExcluiCadastro(this, codigo.getText()))
					return;
				if (pessoaDAO.excluir(codigo.getText())) {					
					mensagemCadastroExcluido(this);					
					limparTela();					
				} else {
					erroBancoDeDados(this);
				}
				carregarTabela(tabela);
			}
		}else{
			JOptionPane.showMessageDialog(this, "Selecione uma pessoa na aba /bPessoas Cadastradas para exclusão");
		}
	//	}	
			nome.requestFocus();
			atualizaGrafico();
	}

	protected void mostrarNaTela() {
		codigo.setText(pessoaVO.getCodigo());
		nome.setText(pessoaVO.getNome());
		dataNasc.setText(new FormataDatas().mostraDataMySql(pessoaVO.getDataNasc()));
		rg.setText(pessoaVO.getRg());
		cpf.setText(pessoaVO.getCpf());
		endereco.setText(pessoaVO.getEndereco());
		numero.setText(pessoaVO.getNumero());
		bairro.setText(pessoaVO.getBairro());
		municipio.setSelectedIndex(pessoaVO.getMunicipio()-1); // o combo inicia com 0
		telRes.setText(pessoaVO.getTelRes());
		telCom.setText(pessoaVO.getTelCom());
		telCel.setText(pessoaVO.getTelCel());
		email.setText(pessoaVO.getEmail());
		
		if (pessoaVO.isElaborador()) {
			elaborador.setSelected(true);
		}else{
			elaborador.setSelected(false);
		}
		nome.requestFocus();
	}

	protected void salvarDadosNoBanco()	{
		
		pessoaVO = new PessoaVO();
		
		if (validarDados(null)) {
			pessoaVO.setNome(nome.getText());
			pessoaVO.setDataNasc(new FormataDatas().salvaDataBanco(dataNasc.getText()));
			pessoaVO.setRg(rg.getText());
			pessoaVO.setCpf(cpf.getText());
			pessoaVO.setEndereco(endereco.getText());
			pessoaVO.setNumero(numero.getText());
			pessoaVO.setBairro(bairro.getText());
			pessoaVO.setMunicipio(municipio.getSelectedIndex()+1); // o combo inicia com 0
			pessoaVO.setTelRes(telRes.getText());
			pessoaVO.setTelCom(telCom.getText());
			pessoaVO.setTelCel(telCel.getText());
			pessoaVO.setEmail(email.getText());			
			
			boolean isElaborador = false;
			if (elaborador.isSelected()) {
				isElaborador = true;
			}else{
				isElaborador = false;
			}			
			pessoaVO.setElaborador(isElaborador);

			try {
				if (pessoaDAO.salvar(pessoaVO, codigo.getText())) {
					mensagemCadastroGravado(this);
					limparTela();
					carregarTabela(tabela);
				} else {
					erroBancoDeDados(this);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Erro ao pegar chave em codigo.getText()");
			}
		}
		atualizaGrafico();
	}
	
	protected boolean lerDadosDoBanco() {
	if (tabela.getRowCount() > 0) {
		try {
			pessoaVO = (PessoaVO) pessoaDAO.ler(acoesDaTabela.pegaChave());
			if (pessoaVO != null) {
				mostrarNaTela();
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			erroBancoDeDados(this);
			return false;
		}
	}

	return pessoaVO != null;
	}	

	@Override
	protected void novoCadastro() {
		limparTela();
		tabbedPane.setSelectedComponent(abaCadastro);
		nome.requestFocus();
	}
}