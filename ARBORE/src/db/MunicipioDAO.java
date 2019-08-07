package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class MunicipioDAO extends CadastroDAO {
	
	public boolean excluir(String chave) {
		try {
			String sql = "DELETE FROM municipio WHERE chave = ' "+chave+" ' ";
			return BD.update(sql) != -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public CadastroVO ler(String chave) throws SQLException {
		MunicipioVO vo = null;

		//try {
			String sql = "SELECT * FROM municipio WHERE chave='"+chave+"'";
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				vo = new MunicipioVO();
				vo.setChave(rs.getString("chave"));
				vo.setNome(rs.getString("nome"));
				vo.setUf(rs.getString("uf"));
			}
			rs.close();
		//} catch (SQLException e) {
		//	e.printStackTrace();
		//}
		return vo;
	}

	public boolean salvar(CadastroVO vo, String Chave) {
		MunicipioVO municipioVO = (MunicipioVO)vo;
		String sql;
		boolean existe;
		try {
			existe = ler(vo.getChave())!=null;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		if (existe) sql = "UPDATE municipio SET chave=?, nome=?, uf=? WHERE chave='"+municipioVO.getChave()+"'";
		else        sql = "INSERT INTO municipio (chave, nome, uf) VALUES (?,?,?)";
		try {
			PreparedStatement ps = BD.con.prepareStatement(sql);
			ps.setString(1, municipioVO.getChave());
			ps.setString(2, municipioVO.getNome());
			ps.setString(3, municipioVO.getUf());
			ps.executeUpdate();
			ps.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Vector<MunicipioVO> pegaLista() {
		Vector<MunicipioVO> vector = new Vector<MunicipioVO>();

		try {
			String sql = "SELECT * FROM municipio";
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				MunicipioVO vo = new MunicipioVO();
				vo.setChave(rs.getString("chave"));
				vo.setNome(rs.getString("nome"));
				vo.setUf(rs.getString("uf"));
							
				vector.add(vo);
			}
			return vector;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
