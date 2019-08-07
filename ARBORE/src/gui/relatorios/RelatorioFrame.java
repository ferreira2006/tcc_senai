package gui.relatorios;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

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
import db.ProjetoDAO;

@SuppressWarnings("serial")
public class RelatorioFrame extends JasperFrame implements ActionListener{
	
	public final String RELATORIO = "projetos";	
	
	private Map<Object, Object> parametros;
	private JPanel centro, projeto, pesquisa;
	private JComboBox numProjeto;
	private JFormattedTextField dataInicial, dataFinal;
	private JRadioButton especifico, porData, geral;

	public RelatorioFrame(String titulo) {
		super(titulo);
		setFrameIcon(Icons.getIcon(Icons.FOLHA));
		add(BorderLayout.EAST, opcoes());
		add(BorderLayout.WEST, criaFormulario());
		
		super.consultar.addActionListener(this);
		super.fechar.addActionListener(this);				
		
		super.funcoes.addStatusFocusListener(projeto, super.bs.jttt);
		super.funcoes.addStatusFocusListener(pesquisa, super.bs.jttt);
		super.funcoes.addStatusFocusListener(super.opcoes, super.bs.jttt);
	
		this.getRootPane().setDefaultButton(super.consultar);
		this.pack();
		
	}

	private JPanel opcoes() {
		JPanel panel = new JPanel();
		JPanel borda = new JPanel();
		borda.setBorder(BorderFactory.createEtchedBorder());
		
		panel.setBorder(BorderFactory.createTitledBorder("Opções:"));
		panel.setLayout(new BoxLayout(panel, SwingConstants.VERTICAL));
		ButtonGroup gb = new ButtonGroup();
		especifico = new JRadioButton("Projeto por Número");
		porData = new JRadioButton("Projeto por Período");				
		geral = new JRadioButton("Todos os Projetos");
		geral.setSelected(true);
		gb.add(especifico);
		gb.add(porData);
		gb.add(geral);
		
		especifico.addActionListener(this);
		porData.addActionListener(this);
		geral.addActionListener(this);
		
		panel.add(especifico);
		panel.add(porData);
		panel.add(geral);
		
		borda.add(panel);
		
		return borda;
	}

	protected JPanel criaFormulario() {
		centro = new JPanel();
		centro.setBorder(BorderFactory.createEtchedBorder());
		centro.setLayout(new GridLayout(0,1));
		
		projeto = new JPanel();
		projeto.setBorder(BorderFactory.createTitledBorder("Projeto Específico:"));
		projeto.add(new JLabel("Projeto nº:"));
		ProjetoDAO projetoDAO = new ProjetoDAO();
		numProjeto = new JComboBox(projetoDAO.pegaLista());
		numProjeto.setToolTipText("Projetos cadastrados");
		projeto.add(numProjeto);		
		
		
		try {
			MaskFormatter dataMascara = new MaskFormatter("##/##/####");
			dataInicial = new JFormattedTextField(dataMascara);
	    	dataInicial.setColumns(8);
	    	dataFinal = new JFormattedTextField(dataMascara);
	    	dataFinal.setColumns(8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		pesquisa = new JPanel();		
		pesquisa.setBorder(BorderFactory.createTitledBorder("Projetos por Período:"));
		pesquisa.add(new JLabel("Data Inicial:"));
		
		dataInicial.setToolTipText("Informe uma data de início");
		pesquisa.add(dataInicial);
		pesquisa.add(new JLabel(" "));		
		
		pesquisa.add(new JLabel("Data Final:"));
		
		dataFinal.setToolTipText("Informe uma data final");
		pesquisa.add(dataFinal);
		
		numProjeto.setEnabled(false);
		dataInicial.setEnabled(false);
		dataFinal.setEnabled(false);
		
		centro.add(projeto);
		centro.add(pesquisa);
		
		return centro;
	}

	public void actionPerformed(ActionEvent a) {
		if (a.getSource() == consultar) {

			DataInputStream jasperFile = getJasper(RELATORIO);
			
			
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
			
			if (geral.isSelected()) {
				query =
					"SELECT projeto.chave, projeto.data, projeto.endereco, projeto.numero, projeto.bairro, projeto.areaDegradada, municipio.nome as munic, estagio.descricao, pessoa.nome" +
					" FROM projeto, municipio, estagio, pessoa WHERE "+ 
					" projeto.municipio = municipio.chave AND projeto.estagio = estagio.chave AND pessoa.chave = projeto.proprietario" +
					" ORDER BY projeto.data";
			}else {
				if (especifico.isSelected()) {
					query =
						"SELECT projeto.chave, projeto.data, projeto.endereco, projeto.numero, projeto.bairro, projeto.areaDegradada, municipio.nome as munic, estagio.descricao, pessoa.nome" +
						" FROM projeto, municipio, estagio, pessoa WHERE projeto.chave = " + numProjeto.getSelectedItem().toString() + 
						" AND projeto.municipio = municipio.chave AND projeto.estagio = estagio.chave AND pessoa.chave = projeto.proprietario";
				}else {
					query =
						"SELECT projeto.chave, projeto.data, projeto.endereco, projeto.numero, projeto.bairro, projeto.areaDegradada, municipio.nome as munic, estagio.descricao, pessoa.nome"+
						" FROM projeto, municipio, estagio, pessoa WHERE"+
						" projeto.municipio = municipio.chave AND projeto.estagio = estagio.chave AND pessoa.chave = projeto.proprietario"+
						" AND projeto.data BETWEEN '"+ new FormataDatas().salvaDataBanco(dataInicial.getText()) + "' AND '"+ new FormataDatas().salvaDataBanco(dataFinal.getText()) +"'" +
						" ORDER BY projeto.data";
						
					
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
		
		if (a.getSource() == fechar) {
			dispose();
		}
		if (a.getSource() == especifico) {
			numProjeto.setEnabled(true);
			dataInicial.setEnabled(false);
			dataFinal.setEnabled(false);
			numProjeto.requestFocus();
		}
		if (a.getSource() == porData) {
			numProjeto.setEnabled(false);
			dataInicial.setEnabled(true);
			dataFinal.setEnabled(true);
			dataInicial.requestFocus();
		}
		if (a.getSource() == geral) {
			numProjeto.setEnabled(false);
			dataInicial.setEnabled(false);
			dataFinal.setEnabled(false);
		}
	}
}
