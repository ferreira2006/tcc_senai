package resources;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormataDatas {

	private DateFormat dfNormal = new SimpleDateFormat("dd/MM/yyyy");
	private DateFormat dfMySql = new SimpleDateFormat("yyyy-MM-dd");
	private Date dataMysql = null;

	// tansforma data normal para o formato mysql
	public String salvaDataBanco(String dataNormal) {

		try { dataMysql = dfNormal.parse(dataNormal); } 
		catch (Exception e1) { e1.printStackTrace(); }
		
		return dfMySql.format(dataMysql);
	}

	// tranforma data do mysql para o formato normal
	public String mostraDataMySql(String dataMySql) {

		try { dataMysql = dfMySql.parse(dataMySql); }
		catch (Exception e) { e.printStackTrace(); }

		return dfNormal.format(dataMysql);
	}

}
