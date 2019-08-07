package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class BD {
	private static final String HOST =  "127.0.0.1";
	private static final String PORTA = "3306"; // usando porta
	private static final String BANCO = "tcc";
	private static final String USUARIO   = "root";
	private static final String SENHA     = "root";

	public static Connection con;
	
	public static void start() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql:" + "//" + HOST + ":" + PORTA + "/" + BANCO, USUARIO, SENHA); // usando porta
	}

	public static void shutdown() throws SQLException {
		Statement st = con.createStatement();
	//	st.execute("SHUTDOWN"); // comando do hsqldb??
		st.close();
		con.close();
	}

	public static void dump(ResultSet rs) throws SQLException {
		ResultSetMetaData meta   = rs.getMetaData();
		int               colmax = meta.getColumnCount();
		int               i;
		Object            o = null;

		for (; rs.next(); ) {
			for (i = 0; i < colmax; ++i) {
				o = rs.getObject(i + 1);
				System.out.print(o.toString() + " ");
			}
			System.out.println(" ");
		}
	}

	public static synchronized void queryDump(String sql) throws SQLException {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		dump(rs);
		st.close();
	}

	public static synchronized int update(String sql) throws SQLException {
		Statement st = con.createStatement();
		int rows = st.executeUpdate(sql);
		if (rows == -1) System.err.println("Erro executando sql: " + sql);
		st.close();
		return rows;
	}
	
	public static void main(String[] args) {
		// conecta
		try {
			BD.start();
			System.out.println("Conexão estabelecida");
		} catch (Exception e) {
			System.err.println("ERRO conectando base de dados");
			e.printStackTrace();
			return;
		}

	/*	// Cria a base de dados
		try {
			BD.update(Sqls.getSql(Sqls.BASEDADOS));
		} catch (SQLException e) {
			System.err.println("ERRO criando a base de dados");
			e.printStackTrace();
		} */

		// desconecta
		try {
			BD.shutdown(); // erro de sql ao desconectar
		} catch (SQLException e) {
			System.err.println("ERRO conectando base de dados");
			e.printStackTrace();
			return;
		}
	}

}

