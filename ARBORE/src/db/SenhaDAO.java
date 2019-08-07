package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class SenhaDAO extends CadastroDAO{

	public boolean excluir(String chave) {
		try {
			String sql = "DELETE FROM senha WHERE chave = ' "+chave+" ' ";
			return BD.update(sql) != -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public CadastroVO ler(String chave) throws SQLException {
		SenhaVO vo = null;

			String sql = "SELECT * FROM senha WHERE chave='"+chave+"'";
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				vo = new SenhaVO();				
				vo.setChave(rs.getString("chave"));
				vo.setNome(rs.getString("nome"));
				vo.setEmail(rs.getString("eMail"));
				vo.setUsuario(rs.getString("usuario"));
				vo.setSenha(rs.getString("pass"));
				vo.setDica(rs.getString("dica"));
				vo.setSupervisor(rs.getBoolean("supervisor"));
				
			}
			rs.close();
		return vo;
	}

	public boolean salvar(CadastroVO vo, String chave) throws SQLException {
		
		SenhaVO senhaVO = (SenhaVO)vo;
		
		Statement st = BD.con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM senha WHERE chave = '"+chave+"'");
		
		String sql = null;
		
		if (rs.next()) { //Se existe, ele sobreescreve o registro 
			sql = "UPDATE senha SET nome=?, eMail=?, usuario=?, pass=?, dica=?, supervisor=? WHERE chave='"+chave+"'";
		}else {
			sql = "INSERT INTO senha (nome, eMail, usuario, pass, dica, supervisor) VALUES (?, ?, ?, ?, ? ,?)";
		}		
		try {
			PreparedStatement ps = BD.con.prepareStatement(sql);
		//	ps.setString(1, senhaVO.getChave());
			ps.setString(1, senhaVO.getNome());
			ps.setString(2, senhaVO.getEmail());
			ps.setString(3, senhaVO.getUsuario());
			ps.setString(4, senhaVO.getSenha());
			ps.setString(5, senhaVO.getDica());
			ps.setBoolean(6, senhaVO.isSupervisor());
			
			ps.executeUpdate();
			ps.close();
			rs.close();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}				 
		
		
	}
	
	public boolean login(String usuario, String password) throws SQLException {
		String sql = "SELECT * FROM senha WHERE usuario = '"+usuario+"'"+" AND pass = '"+password+"'";
		
		Statement st = BD.con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		boolean resultado = false;
		if (rs.next()) {
			rs.close();
			resultado = true;
		}
		
		return resultado;				
	}
	
	public boolean isSupervisor(String usuario) throws SQLException {
		String sql = "SELECT * FROM senha WHERE usuario = '"+usuario+"'"+" AND supervisor = '1'";
		
		Statement st = BD.con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		boolean resultado = false;
		if (rs.next()) {
			rs.close();
			resultado = true;
		}
		
		return resultado;				
	}
	
	public CadastroVO dicaSenha(String usuario) throws SQLException {
		SenhaVO vo = null;
		 
		String sql = "select dica from senha where usuario = '" + usuario + " ' ";
		
		Statement st = BD.con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		if (rs.next()) {
			vo = new SenhaVO();
			vo.setDica(rs.getString("dica"));
		}
		rs.close();
		return vo;
	}
	
public Vector<SenhaVO> pegaLista() {
		
		Vector<SenhaVO> vector = new Vector<SenhaVO>();
		
		String sql = "SELECT * FROM senha ORDER BY usuario";
	//	String sql = "SELECT * FROM senha";
		
		try {
			
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			int x = 0;
			while (rs.next()) {
				x ++;
				SenhaVO vo = new SenhaVO();
				vo.setChave(rs.getString("chave"));
				vo.setNome(rs.getString("nome"));
				vo.setEmail(rs.getString("eMail"));
				vo.setUsuario(rs.getString("usuario"));
				vo.setSenha(rs.getString("pass"));
				vo.setDica(rs.getString("dica"));
				vo.setSupervisor(rs.getBoolean("supervisor"));
											
				vector.add(vo);
			}
			return vector;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
public int total(boolean superv){
	int total = 0;
	String sql = null;
	
	if (superv) {
		sql = "SELECT COUNT(*) FROM senha WHERE supervisor = '1'";
	}else {
		sql = "SELECT COUNT(*) FROM senha WHERE supervisor = '0'";
	}
	
	Statement st;
	ResultSet rs;
	
		try {
			st = BD.con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				total = rs.getInt(1);
				// para usar rs.getString(nome) - usar AS nome no query
			}
		} catch (SQLException e) { e.printStackTrace(); }
			
		
	return total;
	}
	
}
