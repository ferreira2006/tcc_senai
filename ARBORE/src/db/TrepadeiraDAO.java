package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class TrepadeiraDAO extends CadastroDAO {
	
	public boolean excluir(String chave) {
		try {
			String sql = "DELETE FROM trepadeira WHERE chave = ' "+chave+" ' ";
			return BD.update(sql) != -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public TrepadeiraVO ler(String chave) throws SQLException {
		TrepadeiraVO vo = null;

			String sql = "SELECT * FROM trepadeira WHERE chave='"+chave+"'";
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				vo = new TrepadeiraVO();				
				vo.setChave(rs.getString("chave"));
				vo.setDescricao(rs.getString("descricao"));								
			}
			rs.close();
		return vo;
	}

	public boolean salvar(CadastroVO vo, String chave) throws SQLException {
		
		TrepadeiraVO trepadeiraVO = (TrepadeiraVO)vo;
		
		Statement st = BD.con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM trepadeira WHERE chave = '"+chave+"'");
		
		if (rs.next()) { //Se existe, ele sobreescreve o registro 
		String sql = "UPDATE trepadeira SET descricao=? WHERE chave='"+chave+"'";
		try {
			PreparedStatement ps = BD.con.prepareStatement(sql);
		//	ps.setString(1, senhaVO.getChave());
			ps.setString(1, trepadeiraVO.getDescricao());

			ps.executeUpdate();
			ps.close();
			rs.close();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}				
		}else{
		String sql = "INSERT INTO trepadeira (descricao) VALUES (?)";
		
		try { 
			PreparedStatement ps = BD.con.prepareStatement(sql);
		//	ps.setString(1, pessoaVO.getChave()); // chave é auto-incremento
			ps.setString(1, trepadeiraVO.getDescricao());
						
			ps.executeUpdate();
			ps.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		}
	}
	
	
	public Vector<TrepadeiraVO> pegaLista() {
		
		Vector<TrepadeiraVO> vector = new Vector<TrepadeiraVO>();
		
		String sql = "SELECT * FROM trepadeira ORDER BY descricao";
		
		try {
			
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				
				TrepadeiraVO vo = new TrepadeiraVO();
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
