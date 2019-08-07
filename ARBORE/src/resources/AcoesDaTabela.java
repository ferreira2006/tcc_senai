package resources;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AcoesDaTabela {
	private static JTable tabela;

	public static JTable getTabela() {
		return tabela;
	}

	public static void setTabela(JTable tabela) {
		AcoesDaTabela.tabela = tabela;
	}

	public AcoesDaTabela(JTable tbl) {
		AcoesDaTabela.tabela = tbl;

	}

	public String pegaChave() {
		Object chave = null;
		// Pega o valor da célula da coluna CÓDIGO na linha selecionada
		if (tabela.getSelectedRow() >= 0
				&& tabela.getSelectedRow() <= tabela.getRowCount()) {
			chave = tabela.getModel().getValueAt(tabela.getSelectedRow(), 0);
		}
		return (String) chave;
	}

	public void avancaTabela() {
		if (tabela.getSelectedRow() < tabela.getRowCount()-1) {
			int selecionada = tabela.getSelectedRow() + 1;
			tabela.setRowSelectionInterval(selecionada, selecionada);
		}
	}

	public void retroTabela() {
		if (tabela.getSelectedRow() > 0) {
			int selecionada = tabela.getSelectedRow() - 1;
			tabela.setRowSelectionInterval(selecionada, selecionada);
		}
	}
	
	public void carregarTabela() {

		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setRowCount(0);

	//	for (ArvoreVO vo : arvoreDAO.pegaLista()) {
	//		modelo.addRow(new String[] { vo.getChave(), vo.getNomeComum(), vo.getNomeCientifico(), vo.getFamilia() });
	//	}
	//	tbl.setModel(modelo);
}
}	
