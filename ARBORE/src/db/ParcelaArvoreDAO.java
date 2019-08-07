package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class ParcelaArvoreDAO extends CadastroDAO {

	public boolean excluir(String chave) {
		try {
			String sql = "DELETE FROM parcelaarvore WHERE chave = ' "+chave+" ' ";
			return BD.update(sql) != -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ParcelaArvoreVO ler(String chave) throws SQLException {
		ParcelaArvoreVO vo = null;

			String sql = "SELECT * FROM parcelaarvore WHERE chave='"+chave+"'";
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				vo = new ParcelaArvoreVO();				
				vo.setChave(rs.getString("chave"));
				vo.setChaveParcela(rs.getInt("chaveParcela"));
				vo.setChaveArvore(rs.getInt("chaveArvore"));
				vo.setCap(rs.getDouble("cap"));
				vo.setDap(rs.getDouble("dap"));
				vo.setAltura(rs.getDouble("altura"));
				vo.setCas(rs.getDouble("cas"));
				vo.setDistancia(rs.getDouble("distancia"));
				vo.setAreaBasal(rs.getDouble("areaBasal"));
			}
			rs.close();
		return vo;
	}
	
	public int numArvores(String parcela) throws SQLException {
		int numero = 0;

			String sql = "SElECT COUNT(chaveArvore) FROM parcelaarvore WHERE chaveParcela = '"+ parcela +"'";
			Statement st = null;
			ResultSet rs = null;
			
				try {
					st = BD.con.createStatement();
					rs = st.executeQuery(sql);
					if (rs.next()) {
						numero = rs.getInt(1);
						// para usar rs.getString(nome) - usar AS nome no query
					}
				} catch (SQLException e) { e.printStackTrace(); }
			rs.close();
			st.close();
		return numero;
	}

	
	public int mediaAB(String parcela) throws SQLException {
		int numero = 0;

			String sql = "SELECT AVG(areaBasal) FROM parcelaarvore WHERE chaveParcela = '"+ parcela +"'";
			Statement st = null;
			ResultSet rs = null;
			
				try {
					st = BD.con.createStatement();
					rs = st.executeQuery(sql);
					if (rs.next()) {
						numero = rs.getInt(1);
						// para usar rs.getString(nome) - usar AS nome no query
					}
				} catch (SQLException e) { e.printStackTrace(); }
			rs.close();
			st.close();
		return numero;
	}
	
	public boolean salvar(CadastroVO vo, String parcela, String arvore) throws SQLException {
			
			ParcelaArvoreVO parcelaArvoreVO = (ParcelaArvoreVO)vo;
			
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM parcelaarvore WHERE chaveParcela = '"+ parcela +"' AND chaveArvore = '"+ arvore +"'");
			
			String sql = null;
			
		//	if (rs.next()) { //Se existe, ele sobreescreve o registro 
		//		sql = "UPDATE parcelaarvore SET chaveParcela=?, chaveArvore=?, cap=?, dap=?, altura=?, cas=?, distancia=?, areaBasal=? WHERE chaveParcela = '"+ parcela +"' AND chaveArvore = '"+ arvore +"'";
				
		//	}else {
				sql = "INSERT INTO parcelaarvore (chaveParcela, chaveArvore, cap, dap, altura, cas, distancia, areaBasal) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		//	}		
			try {
				PreparedStatement ps = BD.con.prepareStatement(sql);
				ps.setInt(1, parcelaArvoreVO.getChaveParcela());
				ps.setInt(2, parcelaArvoreVO.getChaveArvore());
				ps.setDouble(3, parcelaArvoreVO.getCap());
				ps.setDouble(4, parcelaArvoreVO.getDap());
				ps.setDouble(5, parcelaArvoreVO.getAltura());
				ps.setDouble(6, parcelaArvoreVO.getCas());
				ps.setDouble(7, parcelaArvoreVO.getDistancia());
				ps.setDouble(8, parcelaArvoreVO.getAreaBasal());
				
				ps.executeUpdate();
				ps.close();
				rs.close();
				return true;
				
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
	}
	
	
	public Vector<ParcelaArvoreVO> pegaLista() {
		
		Vector<ParcelaArvoreVO> vector = new Vector<ParcelaArvoreVO>();
		
		String sql = "SELECT * FROM parcelaarvore";
		
		try {
			
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				
				ParcelaArvoreVO vo = new ParcelaArvoreVO();
				
				vo.setChave(rs.getString("chave"));
				vo.setChaveParcela(rs.getInt("chaveParcela"));
				vo.setChaveArvore(rs.getInt("chaveArvore"));
				vo.setCap(rs.getDouble("cap"));
				vo.setDap(rs.getDouble("dap"));
				vo.setAltura(rs.getDouble("altura"));
				vo.setCas(rs.getDouble("cas"));
				vo.setDistancia(rs.getDouble("distancia"));
				vo.setAreaBasal(rs.getDouble("areaBasal"));
															
				vector.add(vo);
			}
			return vector;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public Vector<ParcelaArvoreVO> pegaLista(String parcela) {
		
		Vector<ParcelaArvoreVO> vectorPA = new Vector<ParcelaArvoreVO>();
		
		String sql = "SELECT * FROM parcelaarvore WHERE parcelaArvore.chaveParcela = '"+ parcela +"'";
		
		
		try {
			
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				
				ParcelaArvoreVO vo = new ParcelaArvoreVO();
				
				vo.setChave(rs.getString("chave"));
				vo.setChaveParcela(rs.getInt("chaveParcela"));
				vo.setChaveArvore(rs.getInt("chaveArvore"));
				vo.setCap(rs.getDouble("cap"));
				vo.setDap(rs.getDouble("dap"));
				vo.setAltura(rs.getDouble("altura"));
				vo.setCas(rs.getDouble("cas"));
				vo.setDistancia(rs.getDouble("distancia"));
				vo.setAreaBasal(rs.getDouble("areaBasal"));
											
				vectorPA.add(vo);
			}
			return vectorPA;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean salvar(CadastroVO vo, String chave) throws SQLException {
		
		return false;
	}

}
