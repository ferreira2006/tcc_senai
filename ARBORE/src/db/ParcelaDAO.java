package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class ParcelaDAO extends CadastroDAO {
	
	public boolean excluir(String chave) {
		try {
			String sql = "DELETE FROM parcela WHERE chave = ' "+chave+" ' ";
			return BD.update(sql) != -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public CadastroVO ler(String chave) throws SQLException {
		ParcelaVO vo = null;

			String sql = "SELECT * FROM parcela WHERE chave='"+chave+"'";
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				vo = new ParcelaVO();				
				
				vo.setCoord1(rs.getString("coord1"));
				vo.setCoord2(rs.getString("coord2"));
				vo.setCoord3(rs.getString("coord3"));
				vo.setCoord4(rs.getString("coord4"));
				vo.setArea(rs.getDouble("area"));
				vo.setAreaBasal(rs.getDouble("areaBasal"));
				vo.setVegetacao(rs.getInt("vegetacao"));
				vo.setBioma(rs.getInt("bioma"));				
				vo.setEpifita(rs.getInt("epifita"));
				vo.setTrepadeira(rs.getInt("trepadeira"));
				vo.setSerrapilheira(rs.getInt("serrapilheira"));
				vo.setMuistasEspeciesPioneiras(rs.getString("muitasEspPioneiras"));
				vo.setSubBosque(rs.getString("subBosque"));
				
			}
			rs.close();
		return vo;
	}
	
	public boolean salvar(CadastroVO vo, String chave) throws SQLException {
		
		ParcelaVO parcelaVO = (ParcelaVO)vo;
		
	//	Statement st = BD.con.createStatement();
	//	ResultSet rs = st.executeQuery("SELECT * FROM parcela WHERE chave = '"+chave+"'");
		
		String sql = null;
		
	//	if (rs.next()) { //Se existe, ele sobreescreve o registro 
	//		sql = "UPDATE parcela SET coord1=?, coord2=?, coord3=?, coord4=?, area=?, vegetacao=?, bioma=?, areaBasal=?, altura=?, cap=?, dap=?, cas=?, epifita=?, trepadeira=?, serrapilheira=?, muistasEspPioneiras=?, subBosque=? WHERE chave='"+chave+"'";
			
	//	}else {
			sql = "INSERT INTO parcela (coord1, coord2, coord3, coord4, area, areaBasal, vegetacao, bioma, epifita, trepadeira, serrapilheira, muitasEspPioneiras, subBosque) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	//	}		
		try {
			PreparedStatement ps = BD.con.prepareStatement(sql);
			ps.setString(1, parcelaVO.getCoord1());
			ps.setString(2, parcelaVO.getCoord2());
			ps.setString(3, parcelaVO.getCoord3());
			ps.setString(4, parcelaVO.getCoord4());
			ps.setDouble(5, parcelaVO.getArea());
			ps.setDouble(6, parcelaVO.getAreaBasal());
			ps.setInt(7, parcelaVO.getVegetacao());
			ps.setInt(8, parcelaVO.getBioma());			
			ps.setInt(9, parcelaVO.getEpifita());
			ps.setInt(10, parcelaVO.getTrepadeira());
			ps.setInt(11, parcelaVO.getSerrapilheira());
			ps.setString(12, parcelaVO.isMuistasEspeciesPioneiras());
			ps.setString(13, parcelaVO.isSubBosque());
			
			ps.executeUpdate();
			ps.close();
	//		rs.close();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}				 
	}
	
	public boolean isMEP(String chave) throws SQLException { // muitas especies pioneiras
		String sql = "SELECT * FROM parcela WHERE Chave = " + chave +" AND muitasEspPioneiras = '1'";
		
		Statement st = BD.con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		boolean resultado = false;
		if (rs.next()) {
			rs.close();
			resultado = true;
		}
		
		return resultado;				
	}
	
	public boolean isSubBosque(String chave) throws SQLException { // muitas especies pioneiras
		String sql = "SELECT * FROM parcela WHERE Chave = " + chave +" AND subBosque = '1'";
		
		Statement st = BD.con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		boolean resultado = false;
		if (rs.next()) {
			rs.close();
			resultado = true;
		}
		
		return resultado;				
	}
	
	public Vector<ParcelaVO> pegaLista() {
		
		Vector<ParcelaVO> vector = new Vector<ParcelaVO>();
		
		String sql = "SELECT * FROM parcela";
	//	String sql = "SELECT * FROM senha";
		
		try {
			
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				
				ParcelaVO vo = new ParcelaVO();
				vo.setChave(rs.getString("chave"));
				vo.setCoord1(rs.getString("coord1"));
				vo.setCoord2(rs.getString("coord2"));
				vo.setCoord3(rs.getString("coord3"));
				vo.setCoord4(rs.getString("coord4"));
				vo.setArea(rs.getDouble("area"));
				vo.setAreaBasal(rs.getDouble("areaBasal"));
				vo.setVegetacao(rs.getInt("vegetacao"));
				vo.setBioma(rs.getInt("bioma"));								
				vo.setEpifita(rs.getInt("epifita"));
				vo.setTrepadeira(rs.getInt("trepadeira"));
				vo.setSerrapilheira(rs.getInt("serrapilheira"));
				vo.setMuistasEspeciesPioneiras(rs.getString("muitasEspPioneiras"));
				vo.setSubBosque(rs.getString("subBosque"));
											
				vector.add(vo);
			}
			return vector;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Vector<ParcelaVO> pegaLista(String projeto) {
		
		Vector<ParcelaVO> vector = new Vector<ParcelaVO>();
		
	//	String sql = "SELECT * FROM parcela";
		String sql = "SELECT * FROM projetoparcela x, projeto p, parcela c WHERE p.chave = x.chaveProjeto and c.chave = x.chaveParcela AND p.chave = '"+projeto+"'";
		
		try {
			
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				
				ParcelaVO vo = new ParcelaVO();
				vo.setChave(rs.getString("chave"));
				vo.setCoord1(rs.getString("coord1"));
				vo.setCoord2(rs.getString("coord2"));
				vo.setCoord3(rs.getString("coord3"));
				vo.setCoord4(rs.getString("coord4"));
				vo.setArea(rs.getDouble("area"));
				vo.setAreaBasal(rs.getDouble("areaBasal"));
				vo.setVegetacao(rs.getInt("vegetacao"));
				vo.setBioma(rs.getInt("bioma"));						
				vo.setEpifita(rs.getInt("epifita"));
				vo.setTrepadeira(rs.getInt("trepadeira"));
				vo.setSerrapilheira(rs.getInt("serrapilheira"));
				vo.setMuistasEspeciesPioneiras(rs.getString("muitasEspPioneiras"));
				vo.setSubBosque(rs.getString("subBosque"));
											
				vector.add(vo);
			}
			return vector;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Vector<ParcelaVO> ultimaParcela() throws SQLException {

		Vector<ParcelaVO> vector = new Vector<ParcelaVO>();
		
		String sql = "SELECT * FROM parcela";
	//	String sql = "SELECT * FROM senha";
		
		try {
			
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				
				ParcelaVO vo = new ParcelaVO();
				vo.setChave(rs.getString("chave"));
				vo.setCoord1(rs.getString("coord1"));
				vo.setCoord2(rs.getString("coord2"));
				vo.setCoord3(rs.getString("coord3"));
				vo.setCoord4(rs.getString("coord4"));
				vo.setArea(rs.getDouble("area"));
				vo.setAreaBasal(rs.getDouble("areaBasal"));
				vo.setVegetacao(rs.getInt("vegetacao"));
				vo.setBioma(rs.getInt("bioma"));								
				vo.setEpifita(rs.getInt("epifita"));
				vo.setTrepadeira(rs.getInt("trepadeira"));
				vo.setSerrapilheira(rs.getInt("serrapilheira"));
				vo.setMuistasEspeciesPioneiras(rs.getString("muitasEspPioneiras"));
				vo.setSubBosque(rs.getString("subBosque"));
											
				vector.add(vo);
			}
			return vector;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
