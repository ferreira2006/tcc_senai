package gui;

import java.util.Vector;

import net.sf.jasperreports.engine.JRDataSource;

public class FichaDeCampoJRDataSourceFactory {

	private JRDataSource data;
	private FichaDeCampo ficha;

	public JRDataSource createDatasource(FichaDeCampoImprimir classe) {
		if (data == null) {
			
			
			ficha = new FichaDeCampo(classe.dataTF.getText(), 
					classe.localTF.getText(), 
					classe.areaTotalTF.getText(),
					classe.municipioTF.getText()
					);
			
			Vector<FichaDeCampo> fichaDeCampo = new Vector<FichaDeCampo>();
			fichaDeCampo.add(ficha);
			
			// c = new Cliente("João Paulo", "111.111.111-11", "A002");
			// cliente.add(c);
			// c = new Cliente("José da Silva", "222.222.222-22", "A003");
			// cliente.add(c);
			
			data = new FichaDeCampoJRDataSource(fichaDeCampo);
		}
		return data;
	}
}
