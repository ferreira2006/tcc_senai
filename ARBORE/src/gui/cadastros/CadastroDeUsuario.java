package gui.cadastros;

import gui.relatorios.Grafico_Usuarios;
import gui.relatorios.JasperFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import resources.AcoesDaTabela;
import resources.AlteraTipoCursor;
import resources.Funcoes;
import resources.Icons;
import db.BD;
import db.SenhaDAO;
import db.SenhaVO;

@SuppressWarnings("serial")
public class CadastroDeUsuario extends CadastroAbstrato{

	private JTextField codigo, nome, eMail, usuario, dica, senha, repSenha;
	private JCheckBox supervisor;	
	private Funcoes funcoes;
	private JTable tabela;
	private SenhaDAO senhaDAO;
	private SenhaVO senhaVO;	
	private Dimension dimensaoPadrao;
	private JPanel abaCadastro, abaTabela, abaRelatorio, abaGrafico;
	private JTabbedPane tabbedPane;
	private AcoesDaTabela acoesDaTabela;
	private JRadioButton superv, normal, todos;
	public final String RELATORIO = "usuarios";	
	private Map<Object, Object> parametros;
	
	public CadastroDeUsuario() {
		
		super("Cadastro de Usuários");
		 
		setFrameIcon(Icons.getIcon(Icons.USUARIO));
		
		inicializar();
		
		personalizaAba(tabbedPane);
		new AlteraTipoCursor(abaTabela);
		
		abaCadastro.add(montaCadastro());
		abaTabela.add(montaTabela());
		abaRelatorio.add(montaRelatorio());
		
		abaGrafico.setLayout(new BorderLayout());
		atualizaGrafico();
		
		JButton emitir = new JButton("Emissão");
		emitir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				emitirRelatorio();
			}
		});
		abaRelatorio.add(emitir);
		
		tabbedPane.addTab("Cadastro de Usuários", Icons.getIcon(Icons.PESSOA), abaCadastro);
		tabbedPane.addTab("Usuários cadastrados", Icons.getIcon(Icons.PESSOAS), abaTabela);
		tabbedPane.addTab("Relatório Pessoal", Icons.getIcon(Icons.RELATORIO), abaRelatorio);
		tabbedPane.addTab("Gráfico por Permissões", Icons.getIcon(Icons.CHART32), abaGrafico);
		
		add(BorderLayout.CENTER, tabbedPane);		
		
		pack();
	//	focarChave();
	}

		private void atualizaGrafico() {
		try {
			abaGrafico.removeAll();
			abaGrafico.add(new Grafico_Usuarios().montaGrafico());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

		private JScrollPane montaTabela() {

			final JScrollPane sp = new JScrollPane(tabela,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

			DefaultTableModel model = new DefaultTableModel(new Object[][] {},
					new Object[] { "Chave", "Usuário", "Nome", "E-Mail"}) {

				boolean[] canEdit = new boolean[] { false, false, false, false };

				// Não permite edição nas células
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return canEdit[columnIndex];
				}
			};

		//	for (SenhaVO vo : senhaDAO.pegaLista()) {
		//		model.addRow(new String[] { String.valueOf(vo.getChave()), vo.getNome() });
		//	}
			////////////////////
			
			Vector<SenhaVO> vector = senhaDAO.pegaLista();
			
			for (int i = 0; i < vector.size(); i++) {
				SenhaVO vo = vector.elementAt(i);
					model.addRow(new String[] { String.valueOf(vo.getChave()), vo.getUsuario(), vo.getNome(), vo.getEmail() });
		
			}
			
			//////////////////////////
			
			tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tabela.setModel(model);
			tabela.getTableHeader().setReorderingAllowed(false);
			tabela.getTableHeader().setResizingAllowed(false);
			tabela.setSelectionBackground(super.getCorPadrao());

			tabela.setBorder(BorderFactory.createEmptyBorder());
			sp.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(super.getCorPadrao()), " Usuários Cadastrados: "));
			defineRenderers();
			tabela.addMouseListener(new MouseAdapter() {
				@Override
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
			
			sp.setPreferredSize(dimensaoPadrao);
			tabela.setRowSelectionInterval(0, 0);
			
			return sp;
	
	}
		
		private void defineRenderers() { 
			
			DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
			rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
			DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
			rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
			DefaultTableCellRenderer rendererEsquerda = new DefaultTableCellRenderer();
			rendererEsquerda.setHorizontalAlignment(SwingConstants.LEFT);
			DefaultTableCellRenderer colunaModel = new DefaultTableCellRenderer();
			
			TableColumnModel modeloDaColuna = tabela.getColumnModel();
			modeloDaColuna.getColumn(0).setCellRenderer(rendererCentro); // alin. centro
																			
			modeloDaColuna.getColumn(1).setCellRenderer(rendererEsquerda); // alin. esquerdo
																			
			modeloDaColuna.getColumn(0).setMaxWidth(50); // largura fixa

			colunaModel.setHorizontalAlignment(SwingConstants.CENTER);
		
			colunaModel.setBackground(new Color(162, 205, 90));
			modeloDaColuna.getColumn(0).setCellRenderer(colunaModel);
		

	}
		public void carregarTabela(JTable tbl) { 

		DefaultTableModel modelo = (DefaultTableModel) tbl.getModel();
		modelo.setRowCount(0);

		Vector<SenhaVO> vector = senhaDAO.pegaLista();
		
		for (int i = 0; i < vector.size(); i++) {
			SenhaVO vo = vector.elementAt(i);
				modelo.addRow(new String[] { String.valueOf(vo.getChave()), vo.getUsuario(), vo.getNome(), vo.getEmail()});
	
		}
		tbl.setModel(modelo);
	}

		
	public void avancaTabela() { acoesDaTabela.avancaTabela(); }

	public void retroTabela() { acoesDaTabela.retroTabela(); }
		
	private JPanel montaCadastro() {
		
		codigo = new JTextField(5);
		codigo.setEnabled(false);
		nome = new JTextField(20);
		eMail = new JTextField(20);
		usuario = new JTextField(15);
	//	usuario.addFocusListener(new FocusAdapter(){
	//		public void focusGained(FocusEvent arg0) {				
	//			usuario.selectAll();
	//		}});
		setChaveComponent(nome);
		
		senha = new JPasswordField(10);
		repSenha = new JPasswordField(10);
		dica = new JTextField(25);
		supervisor = new JCheckBox();
		
		JPanel painel = new JPanel(new GridBagLayout());
		
		painel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(super.getCorPadrao()), " Dados do Usuário: "));

		GridBagConstraints gbc = new GridBagConstraints();		
		
		adicionar(painel, "Chave:", "O código será gerado automaticamente pelo Sistema", codigo, 1, 2, 1, gbc);
		adicionar(painel, "Nome:", "Informe o nome para do usuário", nome, 1, 2, 2, gbc);
		adicionar(painel, "e-mail:", "Informe um e-mail para contato", eMail, 1, 2, 3, gbc);
		adicionar(painel, "Usuário:", "Informe o nome para do usuário", usuario, 1, 2, 4, gbc);
		adicionar(painel, "Senha:", "Informe uma senha para este usuário", senha, 1, 2, 5, gbc);
		adicionar(painel, "Repita a senha:", "Informe novamente a senha", repSenha, 1, 2, 6, gbc);
		adicionar(painel, "Dica:", "Digite uma dica de senha", dica, 1, 2, 7, gbc);
		adicionar(painel, "Supervisor:", "Pode adicionar e excluir usuários", supervisor, 1, 2, 8, gbc);
			
		funcoes.addStatusFocusListener(painel, bs.jttt);
		painel.setPreferredSize(dimensaoPadrao);
		
		addFocusListnerContainer(painel);
		
		return painel;
	}

	protected void limparTela() {
		codigo.setText("");
		nome.setText("");
		eMail.setText("");
		usuario.setText("");
		senha.setText("");
		dica.setText("");
		repSenha.setText("");
		usuario.requestFocus();
		supervisor.setSelected(false);
	//	usuario.requestFocus();
		focarChave();
	}
	
	protected boolean validarDados(Component c) {
		boolean result = true;
	 
		// nome
		if ("".equals(nome.getText().trim())) { //se o campo sem espaços excedentes é vazio
			return mensagemErroInformeCampo(this, nome);//retorna mensagem de erro
		}
		
		// email
		if ("".equals(eMail.getText())) {
			return mensagemErroInformeCampo(this, eMail);
		}
		
		// usuario
		if ("".equals(usuario.getText())) {
			return mensagemErroInformeCampo(this, usuario);
		}
		
		// senha
		if ("".equals(senha.getText())) {
			return mensagemErroInformeCampo(this, senha);
		}

		// Repete senha
		if ("".equals(repSenha.getText())) {
			return mensagemErroInformeCampo(this, repSenha);
		}
		if (!repSenha.getText().equals(senha.getText())) {
			return mensagemSenhaNaoConfere(this, repSenha);
		}

		// dica
		if ("".equals(dica.getText().trim())) {
			return mensagemErroInformeCampo(this, dica);
		}

		return result;
	}
	
	private void inicializar() {		
		senhaDAO = new SenhaDAO();
		senhaVO = new SenhaVO();		
    	funcoes = new Funcoes();
    	dimensaoPadrao = new Dimension(this.getWidth()-50, this.getHeight()-50);
    	abaCadastro = new JPanel();
        abaTabela = new JPanel();
        abaRelatorio = new JPanel();
        abaGrafico = new JPanel();
    	tabbedPane = new JTabbedPane();
    	tabela = new JTable();
    	acoesDaTabela = new AcoesDaTabela(tabela);
	}

	protected void excluirDoBanco() {
	//	if (validarDados(codigo)) {
		if (!codigo.getText().equals("")){
			if (lerDadosDoBanco()) {
				if (!perguntaExcluiCadastro(this, codigo.getText()))
					return;
				if (senhaDAO.excluir(codigo.getText())) {					
					mensagemCadastroExcluido(this);					
					limparTela();					
				} else {
					erroBancoDeDados(this);
				}
				carregarTabela(tabela);
			}
		}else{
			JOptionPane.showMessageDialog(this, "Selecione um usuário na aba /bUsuários Cadastrados para exclusão");
		}
	//	}	
		
		usuario.requestFocus();
		atualizaGrafico();
	}

	protected void mostrarNaTela() {
		codigo.setText(senhaVO.getCodigo());
		nome.setText(senhaVO.getNome());
		eMail.setText(senhaVO.getEmail());
		usuario.setText(senhaVO.getUsuario());
	//	senha.setText(senhaVO.getSenha());
		dica.setText(senhaVO.getDica());
		if (senhaVO.isSupervisor()) {
			supervisor.setSelected(true);
		}else{
			supervisor.setSelected(false);
		}
	//	usuario.requestFocus();
		focarChave();
	}

	protected void salvarDadosNoBanco()	{
		
		String senhaCripto = null;
		senhaVO = new SenhaVO();
		try { // criptografa a senha digitada
			senhaCripto = criptografaString(senha.getText());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		if (validarDados(null)) {
			senhaVO.setNome(nome.getText());
			senhaVO.setEmail(eMail.getText());
			senhaVO.setUsuario(usuario.getText());
			senhaVO.setSenha(senhaCripto);
			senhaVO.setDica(dica.getText());
			boolean isSuper = false;
			if (supervisor.isSelected()) {
				isSuper = true;
			}else{
				isSuper = false;
			}			
			senhaVO.setSupervisor(isSuper);

			try {
				if (senhaDAO.salvar(senhaVO, codigo.getText())) {
					mensagemCadastroGravado(this);
					limparTela();
					carregarTabela(tabela);
				} else {
					erroBancoDeDados(this);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("erro ao pegar chave em codigo.getText()");
			}
		}		
		atualizaGrafico();
	}
	
	protected boolean lerDadosDoBanco() {
	if (tabela.getRowCount() > 0) {
		try {
			senhaVO = (SenhaVO) senhaDAO.ler(acoesDaTabela.pegaChave());
			if (senhaVO != null) {
				mostrarNaTela();
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			erroBancoDeDados(this);
			return false;
		}
	}

	return senhaVO != null;
	}	

	@Override
	protected void novoCadastro() {
		limparTela();
		tabbedPane.setSelectedComponent(abaCadastro);
	//	usuario.requestFocus();
		focarChave(); 
	}
	/** Método para criptografar strings Criptografia tipo MD5 */
	public static String criptografaString (String string) throws NoSuchAlgorithmException {
	    MessageDigest md = MessageDigest.getInstance("MD5");   
	    BigInteger hash = new BigInteger(1, md.digest(string.getBytes()));   
	    String stringCriptografada = hash.toString(16);   
	    if (stringCriptografada.length() %2 != 0)   
	        stringCriptografada = "0" + stringCriptografada;   
	    return stringCriptografada;   
	}
	
	private JPanel montaRelatorio(){
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(" Opções de Relatório: "));
		panel.setLayout(new GridLayout(0,1));
		ButtonGroup gb = new ButtonGroup();
		superv = new JRadioButton("Supervisores do sistema");		
		normal = new JRadioButton("Usuários normais do sistema");
		todos = new JRadioButton("Todas os usuários Cadastradas");
		todos.setSelected(true);
		
		gb.add(todos);
		gb.add(superv);
		gb.add(normal);
		
		panel.add(superv);
		panel.add(normal);
		panel.add(todos);
		
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String query = "";
		
		if (todos.isSelected()) {
			query = "SELECT senha.chave, senha.nome, senha.eMail, senha.usuario"
					+ " FROM senha ORDER BY senha.usuario";
		} else {
			if (superv.isSelected()) {
				query = "SELECT senha.chave, senha.nome, senha.eMail, senha.usuario"
						+ " FROM senha WHERE senha.supervisor = '1' ORDER BY senha.usuario";
			} else {
				query = "SELECT senha.chave, senha.nome, senha.eMail, senha.usuario"
						+ " FROM senha WHERE senha.supervisor = '0' ORDER BY senha.usuario";
			}
		}
				
		try {
			rs = (ResultSet) stm.executeQuery( query );
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		  
		
		try {
			//JasperReport jasperReport = JasperManager.loadReport(Resources.getJasper(reportName));
			JasperReport jasperReport = (JasperReport)JRLoader.loadObject(jasperFile);

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JRResultSetDataSource(rs));
			
			if (jasperPrint.getPages().size()>0) {
				JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
				
				//jasperViewer.setTitle("Relatório do "+titulo);
				
				jasperViewer.setTitle("ARBOR - RELAÇÂO DE USUÁRIOS");
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
