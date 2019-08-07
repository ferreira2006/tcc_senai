package gui.relatorios;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JInternalFrame;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JRViewer;

@SuppressWarnings("serial")
public class RelatorioInternoExemplo extends JInternalFrame{
	
	public RelatorioInternoExemplo() {
		super("Relatpirio Interno", false, true, true, true);
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		try {
			String arquivo = JasperFillManager.fillReportToFile("src\\resources\\relatorios\\fichaDeCampo.jasper", map);
			JRViewer viewer = new JRViewer(arquivo, false);
			
			getContentPane().add(viewer);
			
			this.setSize(850, 625);
			this.setVisible(true);
			
		} catch (Exception e) { e.printStackTrace(); }
		
	}
}
