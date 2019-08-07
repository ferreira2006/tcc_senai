package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class ProjetoDAO extends CadastroDAO {
	
	public boolean excluir(String chave) {
		try {
			String sql = "DELETE FROM projeto WHERE chave = ' "+chave+" ' ";
			return BD.update(sql) != -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public CadastroVO ler(String chave) throws SQLException {
		ProjetoVO vo = null;

			String sql = "SELECT * FROM projeto WHERE chave='"+chave+"'";
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				vo = new ProjetoVO();				
				vo.setChave(rs.getString("chave"));
				vo.setData(rs.getString("data"));
				vo.setEndereco(rs.getString("endereco"));
				vo.setNumero(rs.getString("numero"));
				vo.setBairro(rs.getString("bairro"));
				vo.setMunicipio(rs.getInt("municipio"));
				vo.setCoord1(rs.getString("coord1"));
				vo.setCoord2(rs.getString("coord2"));
				vo.setCoord3(rs.getString("coord3"));
				vo.setCoord4(rs.getString("coord4"));
				vo.setAreaTotal(rs.getDouble("areaTotal"));
				vo.setAvaliacao(rs.getBoolean("avaliacao"));
				vo.setEstagio(rs.getInt("estagio"));
				vo.setAreaDegradada(rs.getDouble("areaDegradada"));
				vo.setDegradacaoEmApp(rs.getBoolean("degradacaoEmApp"));
				vo.setProprietario(rs.getInt("proprietario"));
				vo.setElaborador(rs.getInt("elaborador"));
				vo.setDataExclusao(rs.getString("dataExclusao"));
				
			}
			rs.close();
		return vo;
	}

	public boolean salvar(CadastroVO vo, String chave) throws SQLException {
		
		ProjetoVO projetoVO = (ProjetoVO)vo;
		
		Statement st = BD.con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM projeto WHERE chave = '"+chave+"'");
		
		String sql = null;
		
		if (rs.next()) { //Se existe, ele sobreescreve o registro 
			sql = "UPDATE projeto SET data=?, endereco=?, numero=?, bairro=?, municipio=?, coord1=?, coord2=?, coord3=?, coord4=?,areaTotal=?, avaliacao=?, estagio=?, areaDegradada=?, degradacaoEmApp=?, proprietario=?, elaborador=?, dataExclusao=? WHERE chave='"+chave+"'";
		}else {
			sql = "INSERT INTO projeto (data, endereco, numero, bairro, municipio, coord1, coord2, coord3, coord4, areaTotal, avaliacao, estagio, areaDegradada, degradacaoEmApp, proprietario, elaborador, dataExclusao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		}
		
		try {
			PreparedStatement ps = BD.con.prepareStatement(sql);
		//	ps.setString(1, senhaVO.getChave());
			
			
	//		Date utilDate = new Date();
	//		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	//
	//		utilDate = sqlDate;
	//		sqlDate = (java.sql.Date) utilDate;
			
			ps.setString(1, projetoVO.getData());
			ps.setString(2, projetoVO.getEndereco());
			ps.setString(3, projetoVO.getNumero());
			ps.setString(4, projetoVO.getBairro());
			ps.setInt(5, projetoVO.getMunicipio());
			ps.setString(6, projetoVO.getCoord1());
			ps.setString(7, projetoVO.getCoord2());
			ps.setString(8, projetoVO.getCoord3());
			ps.setString(9, projetoVO.getCoord4());
			ps.setDouble(10, projetoVO.getAreaTotal());
			ps.setBoolean(11, projetoVO.isAvaliacao());
			ps.setInt(12, projetoVO.getEstagio());
			ps.setDouble(13, projetoVO.getAreaDegradada());
			ps.setBoolean(14, projetoVO.isDegradacaoEmApp());
			ps.setInt(15, projetoVO.getProprietario());
			ps.setInt(16, projetoVO.getElaborador());
			ps.setString(17, projetoVO.getDataExclusao());
			
			ps.executeUpdate();
			ps.close();
			rs.close();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}				
		
	}

	
	public Vector<ProjetoVO> pegaLista() {
		
		Vector<ProjetoVO> vector = new Vector<ProjetoVO>();
		
		String sql = "SELECT * FROM projeto";
	//	String sql = "SELECT * FROM projeto";
		
		try {
			
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				
				ProjetoVO vo = new ProjetoVO();
				vo.setChave(rs.getString("chave"));
				vo.setData(rs.getString("data"));
				vo.setEndereco(rs.getString("endereco"));
				vo.setNumero(rs.getString("numero"));
				vo.setBairro(rs.getString("bairro"));
				vo.setMunicipio(rs.getInt("municipio"));
				vo.setCoord1(rs.getString("coord1"));
				vo.setCoord2(rs.getString("coord2"));
				vo.setCoord3(rs.getString("coord3"));
				vo.setCoord4(rs.getString("coord4"));
				vo.setAreaTotal(rs.getDouble("areaTotal"));
				vo.setAvaliacao(rs.getBoolean("avaliacao"));
				vo.setEstagio(rs.getInt("estagio"));
				vo.setAreaDegradada(rs.getDouble("areaDegradada"));
				vo.setDegradacaoEmApp(rs.getBoolean("degradacaoEmApp"));
				vo.setProprietario(rs.getInt("proprietario"));
				vo.setElaborador(rs.getInt("elaborador"));
				vo.setDataExclusao(rs.getString("dataExclusao"));
											
				vector.add(vo);
			}
			return vector;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
public Vector<ProjetoVO> listaEstagio() {
		
		Vector<ProjetoVO> vector = new Vector<ProjetoVO>();
		
		String sql = "SELECT * FROM projeto";
		
		try {
			
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				
				ProjetoVO projetoVO = new ProjetoVO();
				
				
				projetoVO.setChave(rs.getString("chave"));
				projetoVO.setEstagio(rs.getInt("estagio"));
				
											
				vector.add(projetoVO);
			}
			return vector;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
public int listaEstagio(String estagio) {
	
	int total = 0;
	
	String sql = "SELECT COUNT(estagio) FROM projeto WHERE estagio = '"+estagio+"'";
	
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

public int totalMunicipio(int municipio) {
	
	int total = 0;
	
	String sql = "SELECT COUNT(municipio) FROM projeto WHERE municipio = '"+municipio+"'";
	
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

public int pegaEstagio(String projeto) {
	
	int estagio = 0;
	
	String sql = "SELECT estagio FROM projeto WHERE chave = '"+ projeto +"'";
	
	Statement st;
	ResultSet rs;
	
	try {
		st = BD.con.createStatement();
		rs = st.executeQuery(sql);
		if (rs.next()) {
			estagio = rs.getInt(1);
			// para usar rs.getString(nome) - usar AS nome no query
		}
	} catch (SQLException e) { e.printStackTrace(); }
		
return estagio;
}

}
