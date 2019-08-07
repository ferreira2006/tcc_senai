package resources;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class Resources {
	//----------------------------------------------------------------------------------------------
	public static final String  ICONS_FOLDER = "resources/icons";
	public static final String    SQL_FOLDER = "resources/sql";
	public static final String JASPER_FOLDER = "resources/jasper";

	//----------------------------------------------------------------------------------------------
	private static ClassLoader loader = Resources.class.getClassLoader();

	public static DataInputStream getResource(String fileName) {
		DataInputStream dis = new DataInputStream(loader.getResourceAsStream(fileName));
		return dis;
	}
	
	public static String getStringFromDataInputStream(DataInputStream in) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String string = "";
			while (reader.ready()) {
				string += reader.readLine() + "\n";
			}
			return string;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (java.io.IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static DataInputStream getJasper(String fileName) {
		return getResource(JASPER_FOLDER + "/" + fileName + ".jasper"); 
	}
}
