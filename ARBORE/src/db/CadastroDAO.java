package db;

import java.sql.SQLException;

public abstract class CadastroDAO {
	
	public abstract CadastroVO ler(String chave) throws SQLException;
	
	public abstract boolean salvar(CadastroVO vo, String chave) throws SQLException;
	
	public abstract boolean excluir(String chave);

}
