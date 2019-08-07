package gui;

import java.util.Iterator;
import java.util.Vector;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class FichaDeCampoJRDataSource implements JRDataSource {

	private Iterator<FichaDeCampo> iterator;

	private FichaDeCampo cursor;

	public FichaDeCampoJRDataSource(Vector<FichaDeCampo> fichaDeCampo) {
		super();
		iterator = fichaDeCampo.iterator();
	}

	public boolean next() throws JRException {
		boolean retorno = iterator.hasNext();
		if (retorno) {
			cursor = iterator.next();
		}
		return retorno;
	}

	public Object getFieldValue(JRField nome) throws JRException {
		FichaDeCampo fichaDeCampo = cursor;		
		
		if (nome.getName().equals("data")) {
			return fichaDeCampo.getData();
		}
		if (nome.getName().equals("local")) {
			return fichaDeCampo.getLocal();
		}
		if (nome.getName().equals("areaTotal")) {
			return fichaDeCampo.getAreaTotal();
		}
		if (nome.getName().equals("municipio")) {
			return fichaDeCampo.getMunicipio();
		}
		
		return null;
	}

}
