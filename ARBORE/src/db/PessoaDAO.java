package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class PessoaDAO extends CadastroDAO{

	@Override
	public boolean excluir(String chave) {
		try {
			String sql = "DELETE FROM pessoa WHERE chave = ' " + chave + " ' ";
			return BD.update(sql) != -1;
		} catch (SQLException e) {

			// Se existem referencias (foreign key) de um registro em outra
			// tabela o mysql não permite a exclusão.
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public CadastroVO ler(String chave) throws SQLException {
		PessoaVO vo = null;

		//try {
			String sql = "SELECT * FROM pessoa WHERE chave='"+chave+"'";
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				vo = new PessoaVO();				
				vo.setChave(rs.getString("chave"));
				vo.setNome(rs.getString("nome"));
				vo.setDataNasc(rs.getString("dataNasc"));
				vo.setRg(rs.getString("rg"));
				vo.setCpf(rs.getString("cpf"));
				vo.setEndereco(rs.getString("endereco"));
				vo.setNumero(rs.getString("numero"));
				vo.setBairro(rs.getString("bairro"));
				vo.setMunicipio(rs.getInt("municipio"));
				vo.setTelRes(rs.getString("telRes"));
				vo.setTelCom(rs.getString("telCom"));
				vo.setTelCel(rs.getString("telCel"));
				vo.setEmail(rs.getString("email"));
				
				String elab = rs.getString("elaborador");
				if (elab.equals("1")) {
					vo.setElaborador(true);
				}else {
					vo.setElaborador(false);
				}
				
			}
			rs.close();
		//} catch (SQLException e) {
		//	e.printStackTrace();
		//}
		return vo;
	}

	@Override
	public boolean salvar(CadastroVO vo, String chave) throws SQLException {
		
		PessoaVO pessoaVO = (PessoaVO) vo;

		Statement st = BD.con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM pessoa WHERE chave = '"+ chave + "'");

		String sql = null;

		if (rs.next()) { // Se existe, ele sobreescreve o registro

			sql = "UPDATE pessoa SET nome=?, dataNasc=?, rg=?, cpf=?, endereco=?, numero=?, bairro=?, municipio=?, telRes=?, telCom=?, telCel=?, email=?, elaborador=? WHERE chave='"+ chave + "'";

		} else {
			sql = "INSERT INTO pessoa (nome, dataNasc, rg, cpf, endereco, numero, bairro, municipio, telRes, telCom, telCel, email, elaborador) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		}

		try {
			PreparedStatement ps = BD.con.prepareStatement(sql);
			ps.setString(1, pessoaVO.getNome());
			ps.setString(2, pessoaVO.getDataNasc());
			ps.setString(3, pessoaVO.getRg());
			ps.setString(4, pessoaVO.getCpf());
			ps.setString(5, pessoaVO.getEndereco());
			ps.setString(6, pessoaVO.getNumero());
			ps.setString(7, pessoaVO.getBairro());
			ps.setInt(8, pessoaVO.getMunicipio());
			ps.setString(9, pessoaVO.getTelRes());
			ps.setString(10, pessoaVO.getTelCom());
			ps.setString(11, pessoaVO.getTelCel());
			ps.setString(12, pessoaVO.getEmail());
			ps.setBoolean(13, pessoaVO.isElaborador());

			ps.executeUpdate();
			ps.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Vector<PessoaVO> pegaLista(boolean elaborador) {
		
		Vector<PessoaVO> vector = new Vector<PessoaVO>();
		
		String sql;
		
		if (elaborador) {
			sql = "SELECT * FROM pessoa WHERE elaborador = '1' ORDER BY nome";
		} else {
			sql = "SELECT * FROM pessoa ORDER BY nome";
		}

		try {
			
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				
				PessoaVO vo = new PessoaVO();
				vo.setChave(rs.getString("chave"));
				vo.setNome(rs.getString("nome"));
				vo.setDataNasc(rs.getString("dataNasc"));
				vo.setRg(rs.getString("rg"));
				vo.setCpf(rs.getString("cpf"));
				vo.setEndereco(rs.getString("endereco"));
				vo.setNumero(rs.getString("numero"));
				vo.setBairro(rs.getString("bairro"));
				vo.setMunicipio(rs.getInt("municipio"));
				vo.setTelRes(rs.getString("telRes"));
				vo.setTelCom(rs.getString("telCom"));
				vo.setTelCel(rs.getString("telCel"));
				vo.setEmail(rs.getString("email"));
				String elab = rs.getString("elaborador");
				if (elab.equals("1")) {
					vo.setElaborador(true);
				}else {
					vo.setElaborador(false);
				}				
							
				vector.add(vo);
			}
			return vector;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public PessoaVO pegaListaVO(boolean elaborador, String chave) {
		PessoaVO vo = new PessoaVO();
		String sql;
		
		if (elaborador) {
			sql = "SELECT * FROM pessoa WHERE elaborador = '1' AND chave = '"+chave+"'";
		} else {
			sql = "SELECT * FROM pessoa WHERE chave = '"+chave+"'";
		}

		try {
			
			Statement st = BD.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
		
				vo.setChave(rs.getString("chave"));
				vo.setNome(rs.getString("nome"));
				vo.setDataNasc(rs.getString("dataNasc"));
				vo.setRg(rs.getString("rg"));
				vo.setCpf(rs.getString("cpf"));
				vo.setEndereco(rs.getString("endereco"));
				vo.setNumero(rs.getString("numero"));
				vo.setBairro(rs.getString("bairro"));
				vo.setMunicipio(rs.getInt("municipio"));
				vo.setTelRes(rs.getString("telRes"));
				vo.setTelCom(rs.getString("telCom"));
				vo.setTelCel(rs.getString("telCel"));
				vo.setEmail(rs.getString("email"));
				String elab = rs.getString("elaborador");
				if (elab.equals("1")) {
					vo.setElaborador(true);
				}else {
					vo.setElaborador(false);
				}				
			}
			return vo;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public int total(boolean elaborador){
		int total = 0;
		String sql = null;
		
		if (elaborador) {
			sql = "SELECT COUNT(*) FROM pessoa WHERE elaborador = '1'";
		}else {
			sql = "SELECT COUNT(*) FROM pessoa WHERE elaborador = '0'";
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
