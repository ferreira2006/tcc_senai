package gui.relatorios;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.Plot;
import org.jfree.data.general.DefaultPieDataset;

import db.ProjetoDAO;

@SuppressWarnings("serial")
public class Grafico_FimProjeto extends JPanel{
	private JFreeChart chart;
	private ProjetoDAO projetoDAO = new ProjetoDAO();
	private DefaultPieDataset dataset = new DefaultPieDataset();
	
	public Grafico_FimProjeto() throws SQLException {
		
			dataset.setValue("Inicial", projetoDAO.listaEstagio("1"));
			dataset.setValue("Médio", projetoDAO.listaEstagio("2")); 
			dataset.setValue("Avançado", projetoDAO.listaEstagio("3"));
			dataset.setValue("Não Avaliado", projetoDAO.listaEstagio("4")); 
		
		// criar o crafico...
		chart = ChartFactory.createPieChart3D("",
				dataset, // valores
				true, // legend?
				true, // tooltips?
				false // URLs?
				);
		chart.setBackgroundPaint(new Color(162, 205, 90));
		
		
		PiePlot3D plotagem = (PiePlot3D) chart.getPlot();
		plotagem.setNoDataMessage("Não há dados");
	  plotagem.setLabelFont(new Font(Font.SANS_SERIF, Font.ITALIC, 12));
		plotagem.setBackgroundPaint(new Color(162, 205, 90)); 
		plotagem.setLabelGap(0.02);
		plotagem.setOutlineVisible(false); // moldura do chart
		plotagem.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}\n(Total = {1})({2})"));
		plotagem.setLabelBackgroundPaint(Color.WHITE); // fundo legenda interna
		plotagem.setLabelOutlinePaint(Color.BLACK); // linha legenda interna
		plotagem.setLegendItemShape(Plot.DEFAULT_LEGEND_ITEM_BOX);
		plotagem.setBaseSectionOutlinePaint(Color.LIGHT_GRAY); // linhas do desenho

		// {0} = campo  {1} valor {2} porcentagem 
		
	}
	public JPanel montaGrafico(){
		return new ChartPanel(chart);
	}
}
