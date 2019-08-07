package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import resources.Icons;

@SuppressWarnings("serial")
public class FichaDeCampoImprimir extends JInternalFrame implements ActionListener {

	private JButton imprimir, limpar, sair;
	JTextField localTF;
	JTextField areaTotalTF;
	JTextField municipioTF;
	JFormattedTextField dataTF;
	private String hoje;
	
	
	public FichaDeCampoImprimir() {
		super("Ficha de Campo", false, // resizable
				true, // closable
				true, // maximizable
				true);// iconifiable
		
		setFrameIcon(Icons.getIcon(Icons.FOLHA));
		
		inicializar();
		
		add(BorderLayout.CENTER, montaFormulario());
		add(BorderLayout.SOUTH, montaBarraBotoes());
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getRootPane().setDefaultButton(imprimir);
		pack();
	}
	
	private void inicializar() {
		
		 imprimir = new JButton("Imprimir");
		 imprimir.addActionListener(this);
		 
		 limpar = new JButton("Limpar");
		 limpar.addActionListener(this);
		 
		 sair = new JButton("Sair");
		 sair.addActionListener(this);
		
		 try {	    	
		    	MaskFormatter mk = new MaskFormatter("##/##/####");
		    	dataTF = new JFormattedTextField(mk);
		    	dataTF.setColumns(8);		    	
		    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		        hoje = sdf.format(new Date());   
		        dataTF.setValue(hoje);	        
		    	
			} catch (Exception e) {
				e.printStackTrace();
			}
		 
		 
		 
		 localTF = new JTextField(15);
		 areaTotalTF = new JTextField(10);
		 municipioTF = new JTextField(15);
		 	 
	}
	
	private JPanel montaFormulario() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Nova Ficha de Campo:"));		
		panel.setLayout(new BorderLayout());
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(0,2,6,6));
		
		panel2.add(new JLabel("Data:", JLabel.RIGHT));
		panel2.add(dataTF);
		panel2.add(new JLabel("Local:", JLabel.RIGHT));
		panel2.add(localTF);		
		panel2.add(new JLabel("Município:", JLabel.RIGHT));
		panel2.add(municipioTF);
		panel2.add(new JLabel("Área Total:", JLabel.RIGHT));
		panel2.add(areaTotalTF);
																		
		panel.add(BorderLayout.CENTER, panel2);		
		
		return panel;
	}

	private JPanel montaBarraBotoes() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEtchedBorder());
		
		panel.add(imprimir);
		panel.add(limpar);
		panel.add(sair);
		
		return panel;
	}
	public static void gerarRelatorio(JRDataSource source) throws JRException {

		Map<Object, Object> map = new HashMap<Object, Object>();
	//	JasperFillManager.fillReportToFile("src/relatorio/OrdemDeServico.jasper", map, source);
	//	JasperViewer.viewReport("src/relatorio/OrdemDeServico.jrprint", false);
		
		JasperPrint impressao = JasperFillManager.fillReport( "src\\resources\\relatorios\\fichaDeCampo.jasper", map, source);		
		JasperViewer jasperViewer = new JasperViewer(impressao, false);
		
		
		jasperViewer.setTitle("Ficha de Campo");
		jasperViewer.setVisible(true);
				
		
		jasperViewer.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
	
	}
	
	

	public static void main(String[] args) {
		new FichaDeCampoImprimir().setVisible(true);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == imprimir) {			
			geraRelatorio();
		}
		if (e.getSource() == sair) {
			dispose();
		}
		if (e.getSource() == limpar) {
			limpar();
		}		
	}

	private void geraRelatorio() {
		
		this.setVisible(false);
		
		try {
			gerarRelatorio(new FichaDeCampoJRDataSourceFactory().createDatasource(this));
		} catch (JRException e) {
			e.printStackTrace();
		}		
	}
	
	private void limpar() {
		dataTF.setText("");
		localTF.setText("");
		areaTotalTF.setText("");
		municipioTF.setText("");
		dataTF.requestFocus();
	}

}
