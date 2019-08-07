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

import db.MunicipioDAO;
import db.MunicipioVO;
import db.ProjetoDAO;
import db.ProjetoVO;

@SuppressWarnings("serial")
public class Grafico_Projetos extends JPanel{
	private JFreeChart chart;
	private ProjetoDAO projetoDAO = new ProjetoDAO();
	private ProjetoVO projetoVO = new ProjetoVO();
	private DefaultPieDataset dataset = new DefaultPieDataset();
	
	public Grafico_Projetos() throws SQLException {
		
		// seleciona familias com 5 ou mais
		Vector<ProjetoVO> vector = projetoDAO.pegaLista(); 		
		
		// repassa todos os itens do vetor		
		for (int i = 0; i < vector.size(); i++) {
			// pega o nome
			projetoVO = vector.elementAt(i);
			// soma qtos individuos desta familia existem
			Number totalMunicipio = projetoDAO.totalMunicipio(projetoVO.getMunicipio());
			MunicipioVO municipioVO = (MunicipioVO) new MunicipioDAO().ler(String.valueOf(projetoVO.getMunicipio()));
			// insere no dataset o nome e a quantidade
			dataset.setValue(municipioVO.getNome(), totalMunicipio); 
			}
		
		
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
