package gui.relatorios;

import java.awt.Color;
import java.sql.SQLException;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.Plot;
import org.jfree.data.general.DefaultPieDataset;

import db.PessoaDAO;
import db.PessoaVO;

@SuppressWarnings("serial")
public class Grafico_Pessoas extends JPanel{
	JFreeChart chart;
	PessoaDAO pessoaDAO = new PessoaDAO();
	PessoaVO pessoaVO = new PessoaVO();
	
	public Grafico_Pessoas() throws SQLException {
	
	//	Vector<SenhaVO> vetor = senhaDAO.pegaLista();
		
	//	Object[] array = vetor.toArray();
		
		Number numElaboradores = pessoaDAO.total(true); 
		
		Number numProprietarios = pessoaDAO.total(false);
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		
	//	for (int i = 0; i < array.length; i++) {
			dataset.setValue("Elaboradores", numElaboradores);
			dataset.setValue("Proprietários", numProprietarios);
	//	}
		
	// criar o  dataset...
	//	DefaultPieDataset dataset = new DefaultPieDataset();
	//	dataset.setValue("Usuário", 50.0);
	//	dataset.setValue("Supervisor", 50.0);		
	//	dataset.setValue("teste", rs.getRow());
		
		// criar o crafico...
		chart = ChartFactory.createPieChart3D("",
				dataset, // valores
				true, // legend?
				true, // tooltips?
				false // URLs?
				);
		chart.setBackgroundPaint(new Color(162, 205, 90));
		
		PiePlot3D plotagem = (PiePlot3D) chart.getPlot();
	//	plotagem.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2})"));
		plotagem.setNoDataMessage("Não há dados");
	//	plotagem.setBackgroundImage(Icons.getImage(Icons.JAVA));
	//  plotagem.setLabelFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
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
