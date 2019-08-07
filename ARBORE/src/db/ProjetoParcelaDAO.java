package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class ProjetoParcelaDAO extends CadastroDAO {

	public boolean excluir(String chave) {
		try {
			String sql = "DELETE FROM projetoparcela WHERE chave = ' "+chave+" ' ";
			return BD.update(sql) != -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
		public boolean excluir(String projeto, String parcela) {
		try {
			String sql = "DELETE FROM projetoparcela WHERE projetoparcela.chaveProjeto = '"+ projeto +"' AND projetoparcela.chaveParcela = '"+ parcela +"'";
			return BD.update(sql) != -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ProjetoParcelaVO ler(String chave) throws SQLException {
		ProjetoParcelaVO vo = null;

			String sql = "SELECT * FROM projetoparcela WHERE chave='"+chave+"'";
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				vo = new ProjetoParcelaVO();				
				vo.setChave(rs.getString("chave"));
				vo.setChaveProjeto(rs.getString("chaveProjeto"));
				vo.setChaveParcela(rs.getString("chaveParcela"));
			}
			rs.close();
		return vo;
	}

	public ProjetoParcelaVO pegaParcela(String chave) throws SQLException {
		ProjetoParcelaVO vo = null;

			String sql = "SELECT * FROM projetoparcela WHERE chave = '"+chave+"'";
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				vo = new ProjetoParcelaVO();				
				vo.setChave(rs.getString("chave"));
				vo.setChaveProjeto(rs.getString("chaveProjeto"));
				vo.setChaveParcela(rs.getString("chaveParcela"));
			}
			rs.close();
		return vo;
	}
	
	public boolean salvar(String projeto, String parcela) throws SQLException {
		
		Statement st = BD.con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM projetoparcela WHERE chaveProjeto = '"+ projeto +"' AND chaveParcela = '" + parcela + "'");
		
		if (rs.next()) { //Se existe, ele sobreescreve o registro 
		String sql = "UPDATE projetoparcela SET chaveProjeto=?, chaveParcela=? WHERE chaveProjeto = '"+ projeto +"' AND chaveParcela = '" + parcela + "'";
		try {
			PreparedStatement ps = BD.con.prepareStatement(sql);
		//	ps.setString(1, estagioVO.getChave());
			ps.setString(1, projeto);
			ps.setString(2, parcela);

			ps.executeUpdate();
			ps.close();
			rs.close();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}				
		}else{
		String sql = "INSERT INTO projetoparcela (chaveProjeto, chaveParcela) VALUES (?, ?)";
		
		try { 
			PreparedStatement ps = BD.con.prepareStatement(sql);
		//	ps.setString(1, pessoaVO.getChave()); // chave é auto-incremento
			ps.setString(1, projeto);
			ps.setString(2, parcela);
						
			ps.executeUpdate();
			ps.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		}
	}
	
	
	public Vector<ProjetoParcelaVO> pegaLista() {
		
		Vector<ProjetoParcelaVO> vector = new Vector<ProjetoParcelaVO>();
		
		String sql = "SELECT * FROM projetoparcela";
		
		try {
			
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				
				ProjetoParcelaVO vo = new ProjetoParcelaVO();
				vo.setChave(rs.getString("chave"));
				vo.setChaveProjeto(rs.getString("chaveProjeto"));
				vo.setChaveParcela(rs.getString("chaveParcela"));
															
				vector.add(vo);
			}
			return vector;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean salvar(CadastroVO vo, String chave) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
public Vector<ProjetoParcelaVO> pegaParcelas(String projeto) {
		
		Vector<ProjetoParcelaVO> vector = new Vector<ProjetoParcelaVO>();
		
		String sql = "SELECT j.chaveParcela FROM projetoparcela j, parcela c, projeto p " +
				"WHERE j.chaveProjeto = p.chave AND j.chaveParcela = c.chave AND p.chave = ' "+ projeto+ "'";
		
		try {
			
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				
				ProjetoParcelaVO vo = new ProjetoParcelaVO();
			//	vo.setChave(rs.getString("chave"));
			//	vo.setChaveProjeto(rs.getString("chaveProjeto"));
				vo.setChaveParcela(rs.getString("chaveParcela"));
															
				vector.add(vo);
			}
			return vector;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
