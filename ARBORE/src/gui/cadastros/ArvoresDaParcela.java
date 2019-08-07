package gui.cadastros;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import db.ArvoreDAO;
import db.ArvoreVO;
import db.ParcelaArvoreDAO;
import db.ParcelaArvoreVO;

@SuppressWarnings("serial")
public class ArvoresDaParcela extends JTable{
//	private JTable tabela = new JTable();
	private ArvoreDAO arvoreDAO;
	private ParcelaArvoreDAO parcelaArvoreDAO;

	public ArvoresDaParcela() {

		inicializar();

	}

	private void inicializar() {
//		tabela = new JTable();
		arvoreDAO = new ArvoreDAO();
		parcelaArvoreDAO = new ParcelaArvoreDAO();

	}

	protected JScrollPane montaTabelaArvoresDaParcela(String parcela) {

		final JScrollPane sp = new JScrollPane(this);

		DefaultTableModel model = new DefaultTableModel(new Object[][] {},
				new Object[] { "", "Nome Popular", "CAP", "DAP", "Altura", "CAS", "Distância", "Área Basal" }) {

			boolean[] canEdit = new boolean[] { false, false, false, false, false,
					false, false, false };

			// Não permite edição nas células
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		};		

		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(model);
		this.getTableHeader().setReorderingAllowed(false);
		this.getTableHeader().setResizingAllowed(false);
		this.setSelectionBackground(new Color(162, 205, 90));
		this.setBorder(BorderFactory.createEmptyBorder());
		sp.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(162, 205, 90)), " Árvores da Parcela: "));
		
		sp.setPreferredSize(new Dimension(600, 120));
		
		defineRenderers();
		
		carregarTabela(parcela);

		return sp;
	}

	private void defineRenderers() {

		DefaultTableCellRenderer colunaModel = new DefaultTableCellRenderer();
		colunaModel.setBackground(new Color(162, 205, 90));
		colunaModel.setForeground(new Color(162, 205, 90));
		
		TableColumnModel modeloDaColuna = this.getColumnModel();		
		modeloDaColuna.getColumn(0).setMaxWidth(65); // largura fixa
		modeloDaColuna.getColumn(2).setMaxWidth(65); 
		modeloDaColuna.getColumn(3).setMaxWidth(65); 
		modeloDaColuna.getColumn(4).setMaxWidth(65); 
		modeloDaColuna.getColumn(5).setMaxWidth(65); 
		modeloDaColuna.getColumn(6).setMaxWidth(70);
		modeloDaColuna.getColumn(7).setMaxWidth(70);
		modeloDaColuna.getColumn(0).setCellRenderer(colunaModel);

	}

	public void carregarTabela(String parcela) {

		DefaultTableModel modelo = (DefaultTableModel) this.getModel();
		modelo.setRowCount(0);

		Vector<ParcelaArvoreVO> vectorPA = parcelaArvoreDAO.pegaLista(parcela);
		

		for (int i = 0; i < vectorPA.size(); i++) {
			ParcelaArvoreVO voPA = vectorPA.elementAt(i);
			ArvoreVO aVO = null;
			try {
				aVO = (ArvoreVO) arvoreDAO.ler(String.valueOf(voPA.getChaveArvore()));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			modelo.addRow(new String[] { voPA.getChave(), "" + aVO.getNomeComum(), "" + 
							voPA.getCap(), "" + voPA.getDap(), "" + voPA.getAltura(), "" + 
							voPA.getCas(), "" +  voPA.getDistancia(), "" + voPA.getAreaBasal() });			 
		}		
		
		
		if (this.getRowCount() > 0) {
			this.setRowSelectionInterval(0, 0);
		}
		
		this.setModel(modelo);
		
	}
	
	protected void limpaTabela(){
		DefaultTableModel modelo = (DefaultTableModel) this.getModel();
		modelo.setRowCount(0);
		this.setModel(modelo);
		
	}

}
