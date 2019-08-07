package gui.relatorios;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.Plot;
import org.jfree.data.general.DefaultPieDataset;

import db.ArvoreDAO;
import db.ArvoreVO;

@SuppressWarnings("serial")
public class Grafico_Arvores extends JPanel{
	private JFreeChart chart;
	private ArvoreDAO arvoreDAO = new ArvoreDAO();
	private ArvoreVO arvoreVO = new ArvoreVO();
	private DefaultPieDataset dataset = new DefaultPieDataset();
	
	public Grafico_Arvores() throws SQLException {
		
		// seleciona familias com 5 ou mais
		Vector<ArvoreVO> vector = arvoreDAO.maisDe5(); 		
		
		// repassa todos os itens do vetor		
		for (int i = 0; i < vector.size(); i++) {
			// pega o nome
			arvoreVO = vector.elementAt(i);
			// soma qtos individuos desta familia existem
			Number numFamilia = arvoreDAO.familia(arvoreVO.getFamilia());
			// insere no dataset o nome e a quantidade
			dataset.setValue(arvoreVO.getFamilia(), numFamilia); 
			}
		
		
	// criar o  dataset...
	//	DefaultPieDataset dataset = new DefaultPieDataset();
	//	dataset.setValue("Usu�rio", 50.0);
	//	dataset.setValue("Supervisor", 50.0);		
	//	dataset.setValue("teste", rs.getRow());
		
		// criar o crafico...
		chart = ChartFactory.createPieChart3D("Fam�lias com 5 indiv�duos ou mais",
				dataset, // valores
				true, // legend?
				true, // tooltips?
				false // URLs?
				);
		chart.setBackgroundPaint(new Color(162, 205, 90));
		
		
		PiePlot3D plotagem = (PiePlot3D) chart.getPlot();
	//	plotagem.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2})"));
		plotagem.setNoDataMessage("N�o h� dados");
	//	plotagem.setBackgroundImage(Icons.getImage(Icons.JAVA));
	  plotagem.setLabelFont(new Font(Font.SANS_SERIF, Font.ITALIC, 12));
		plotagem.setBackgroundPaint(new Color(162, 205, 90)); 
	//	plotagem.setCircular(false);
		plotagem.setLabelGap(0.02);
		plotagem.setOutlineVisible(false); // moldura do chart
		plotagem.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}\n(Total = {1})({2})"));
		plotagem.setLabelBackgroundPaint(Color.WHITE); // fundo legenda interna
		plotagem.setLabelOutlinePaint(Color.BLACK); // linha legenda interna
	//	plotagem.setLabelShadowPaint(Color.WHITE); // sombra legenda interna
		plotagem.setLegendItemShape(Plot.DEFAULT_LEGEND_ITEM_BOX);
		plotagem.setBaseSectionOutlinePaint(Color.LIGHT_GRAY); // linhas do desenho
		
		
		
		// {0} = campo  {1} valor {2} porcentagem 
	}
	public JPanel montaGrafico(){
		return new ChartPanel(chart);
	}
}
