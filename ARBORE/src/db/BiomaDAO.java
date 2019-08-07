package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class BiomaDAO extends CadastroDAO {

	public boolean excluir(String chave) {
		try {
			String sql = "DELETE FROM bioma WHERE chave = ' "+chave+" ' ";
			return BD.update(sql) != -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public BiomaVO ler(String chave) throws SQLException {
		BiomaVO vo = null;

			String sql = "SELECT * FROM bioma WHERE chave='"+chave+"'";
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				vo = new BiomaVO();				
				vo.setChave(rs.getString("chave"));
				vo.setDescricao(rs.getString("descricao"));								
			}
			rs.close();
		return vo;
	}

	public boolean salvar(CadastroVO vo, String chave) throws SQLException {
		
		BiomaVO biomaVO = (BiomaVO)vo;
		
		Statement st = BD.con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM bioma WHERE chave = '"+chave+"'");
		
		if (rs.next()) { //Se existe, ele sobreescreve o registro 
		String sql = "UPDATE bioma SET descricao=? WHERE chave='"+chave+"'";
		try {
			PreparedStatement ps = BD.con.prepareStatement(sql);
		//	ps.setString(1, senhaVO.getChave());
			ps.setString(1, biomaVO.getDescricao());

			ps.executeUpdate();
			ps.close();
			rs.close();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}				
		}else{
		String sql = "INSERT INTO bioma (descricao) VALUES (?)";
		
		try { 
			PreparedStatement ps = BD.con.prepareStatement(sql);
		//	ps.setString(1, pessoaVO.getChave()); // chave é auto-incremento
			ps.setString(1, biomaVO.getDescricao());
						
			ps.executeUpdate();
			ps.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		}
	}
	
	public Vector<BiomaVO> pegaLista() {
		
		Vector<BiomaVO> vector = new Vector<BiomaVO>();
		
		String sql = "SELECT * FROM bioma ORDER BY descricao";
		
		try {
			
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				
				BiomaVO vo = new BiomaVO();
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
