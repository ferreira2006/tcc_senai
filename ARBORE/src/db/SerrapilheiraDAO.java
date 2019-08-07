package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class SerrapilheiraDAO extends CadastroDAO {
	
	public boolean excluir(String chave) {
		try {
			String sql = "DELETE FROM serrapilheira WHERE chave = ' "+chave+" ' ";
			return BD.update(sql) != -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public SerrapilheiraVO ler(String chave) throws SQLException {
		SerrapilheiraVO vo = null;

			String sql = "SELECT * FROM serrapilheira WHERE chave='"+chave+"'";
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				vo = new SerrapilheiraVO();				
				vo.setChave(rs.getString("chave"));
				vo.setDescricao(rs.getString("descricao"));								
			}
			rs.close();
		return vo;
	}

	public boolean salvar(CadastroVO vo, String chave) throws SQLException {
		
		SerrapilheiraVO serrapilheiraVO = (SerrapilheiraVO)vo;
		
		Statement st = BD.con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM serrapilheira WHERE chave = '"+chave+"'");
		
		if (rs.next()) { //Se existe, ele sobreescreve o registro 
		String sql = "UPDATE serrapilheira SET descricao=? WHERE chave='"+chave+"'";
		try {
			PreparedStatement ps = BD.con.prepareStatement(sql);
		//	ps.setString(1, senhaVO.getChave());
			ps.setString(1, serrapilheiraVO.getDescricao());

			ps.executeUpdate();
			ps.close();
			rs.close();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}				
		}else{
		String sql = "INSERT INTO serrapilheira (descricao) VALUES (?)";
		
		try { 
			PreparedStatement ps = BD.con.prepareStatement(sql);
		//	ps.setString(1, pessoaVO.getChave()); // chave é auto-incremento
			ps.setString(1, serrapilheiraVO.getDescricao());
						
			ps.executeUpdate();
			ps.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		}
	}
	
	
	public Vector<SerrapilheiraVO> pegaLista() {
		
		Vector<SerrapilheiraVO> vector = new Vector<SerrapilheiraVO>();
		
		String sql = "SELECT * FROM serrapilheira ORDER BY descricao";
		
		try {
			
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				
				SerrapilheiraVO vo = new SerrapilheiraVO();
				vo.setChave(rs.getString("chave"));
				vo.setDescricao(rs.getString("descricao"));
															
				vector.add(vo);
			}
			return vector;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
