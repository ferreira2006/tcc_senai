package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class ArvoreDAO extends CadastroDAO {
	
	public boolean excluir(String chave) {
		try {
			String sql = "DELETE FROM arvore WHERE chave = ' "+chave+" ' ";
			return BD.update(sql) != -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public CadastroVO ler(String chave) throws SQLException {
		ArvoreVO vo = null;

			String sql = "SELECT * FROM arvore WHERE chave='"+chave+"'";
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				vo = new ArvoreVO();				
				vo.setChave(rs.getString("chave"));
				vo.setNomeComum(rs.getString("nomePopular"));
				vo.setNomeCientifico(rs.getString("nomeCientifico"));
				vo.setFamilia(rs.getString("familia"));
				
			}
			rs.close();
		return vo;
	}

	public boolean salvar(CadastroVO vo, String chave) throws SQLException {
		
		ArvoreVO arvoreVO = (ArvoreVO)vo;
		
		Statement st = BD.con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM arvore WHERE chave = '"+chave+"'");
		
		if (rs.next()) { //Se existe, ele sobreescreve o registro 
		String sql = "UPDATE arvore SET nomePopular=?, nomeCientifico=?, familia=? WHERE chave='"+chave+"'";
		try {
			PreparedStatement ps = BD.con.prepareStatement(sql);
		//	ps.setString(1, senhaVO.getChave());
			ps.setString(1, arvoreVO.getNomeComum());
			ps.setString(2, arvoreVO.getNomeCientifico());
			ps.setString(3, arvoreVO.getFamilia());

			ps.executeUpdate();
			ps.close();
			rs.close();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}				
		}else{
		String sql = "INSERT INTO arvore (nomePopular, nomeCientifico, familia) VALUES (?, ?, ?)";
		
		try { 
			PreparedStatement ps = BD.con.prepareStatement(sql);
		//	ps.setString(1, pessoaVO.getChave()); // chave é auto-incremento
			ps.setString(1, arvoreVO.getNomeComum());
			ps.setString(2, arvoreVO.getNomeCientifico());
			ps.setString(3, arvoreVO.getFamilia());
			
			ps.executeUpdate();
			ps.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		}
	}

public Vector<ArvoreVO> pegaLista(boolean sort) {
		
		Vector<ArvoreVO> vector = new Vector<ArvoreVO>();
		
		String sql = "";
		
		if (sort) {
			sql = "SELECT * FROM arvore ORDER BY nomePopular";
		}else {
			sql = "SELECT * FROM arvore";
		}
		
		try {
			
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				
				ArvoreVO vo = new ArvoreVO();
				vo.setChave(rs.getString("chave"));
				vo.setNomeComum(rs.getString("nomePopular"));
				vo.setNomeCientifico(rs.getString("nomeCientifico"));
				vo.setFamilia(rs.getString("familia"));
											
				vector.add(vo);
			}
			return vector;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

public Vector<ArvoreVO> maisDe5() {
	
	// para saber quais as familias tem 5 ou mais arvores
	
	ArvoreVO arvoreVO;
	Statement st1, st2;
	ResultSet rs1, rs2;
	
	// crai vetor de arvoreVO
	Vector<ArvoreVO> vector = new Vector<ArvoreVO>();
	
	// query para saber quais femilias existem
	String sql = "SELECT DISTINCT familia FROM arvore";
	
	try {		
		st1 = BD.con.createStatement();
		rs1 = st1.executeQuery(sql);
		
		// se houver algum resultado
		while (rs1.next()) {
			
			// cria uma nova instancia de ArvoreVO
			arvoreVO = new ArvoreVO();
			
			// seta o campo familia com o proximo resultado
			arvoreVO.setFamilia(rs1.getString("familia"));
										
			// prepara pesquisa para saber qtas arvores existem desta familia
			String query = "SELECT DISTINCT familia FROM arvore WHERE (SELECT COUNT(familia) FROM arvore WHERE familia = '"
						+ rs1.getString("familia") + "') >= 5";
				st2 = BD.con.createStatement();
				rs2 = st2.executeQuery(query);
				 
				while (rs2.next()) {
					// existindo 5 ou mais arvores a familia é adicionada ao vetor
					vector.add(arvoreVO);
				}
		}
		return vector;
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
}
public int familia(String familia){
	
	// para descobrir quantas arvores existem de uma determinada familia
	
	int total = 0;
	String sql = "SELECT COUNT(familia) FROM arvore WHERE familia = '"+familia+"' " +
			"AND ((SELECT COUNT(familia) FROM arvore WHERE familia = '"+familia+"') >= 5)" ;
	
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

	public Vector<ArvoreVO> pegaLista(String parcela) {
	
	Vector<ArvoreVO> vector = new Vector<ArvoreVO>();
	
	String sql = "SELECT * FROM parcelaarvore x, parcela p, arvore a WHERE p.chave = x.chaveParcela and a.chave = x.chaveArvore AND p.chave = '"+ parcela+"'";
	
	try {
		
		Statement st = BD.con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			
			ArvoreVO vo = new ArvoreVO();
			vo.setChave(rs.getString("chave"));
			vo.setNomeComum(rs.getString("nomePopular"));
			vo.setNomeCientifico(rs.getString("nomeCientifico"));
			vo.setFamilia(rs.getString("familia"));
										
			vector.add(vo);
		}
		return vector;
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
}
	
	public CadastroVO pegaArvore(String nome) throws SQLException {
		ArvoreVO vo = null;

			String sql = "SELECT * FROM arvore WHERE nomePopular = '"+ nome +"'";
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				vo = new ArvoreVO();				
				vo.setChave(rs.getString("chave"));
				vo.setNomeComum(rs.getString("nomePopular"));
				vo.setNomeCientifico(rs.getString("nomeCientifico"));
				vo.setFamilia(rs.getString("familia"));
				
			}
			rs.close();
		return vo;
	}
}
