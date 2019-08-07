package gui;

import gui.cadastros.CadastroAbstrato;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import resources.AlteraTipoCursor;
import resources.Icons;

@SuppressWarnings("serial")
public class BarraDeBotoesDeCadastro extends JPanel {
	public static final String NOVO = "Novo";
	public static final String SALVAR = "Salvar";
//	public static final String LIMPAR = "Limpar";
	public static final String EXCLUIR = "Excluir";
	public static final String RELATORIO = "Relatorio";
	public static final String FECHAR = "Fechar";
	public static final String PROXIMO = "Próximo";
	public static final String ANTERIOR = "Anterior";
	
	public BarraDeBotoesDeCadastro(CadastroAbstrato cadastro) {
		super(new SpringLayout());
		setBorder(BorderFactory.createEtchedBorder());
		setLayout(new GridLayout(1,0,1,1));
		
		criarBotao(cadastro, "Cria um novo cadastro", 'N', NOVO, Icons.NOVO);
		criarBotao(cadastro, "Salva o registro atual", 'S', SALVAR, Icons.SALVAR);
//		criarBotao(cadastro, "Limpa o formulário", 'L', LIMPAR, Icons.LIMPAR);
		criarBotao(cadastro, "Exclui o cadastro selecionado", 'E', EXCLUIR, Icons.EXCLUIR);
		criarBotao(cadastro, "Vai para o Registro Anterior", 'A', ANTERIOR, Icons.ANTERIOR);
		criarBotao(cadastro, "Vai para o Próximo Registro", 'P', PROXIMO, Icons.PROXIMO);
		criarBotao(cadastro, "Fecha o foumulário", 'F', FECHAR, Icons.FECHAR);
		
		new AlteraTipoCursor(this);

	}
	
	private void criarBotao(JInternalFrame cadastro, String jtt, char key, String nome, String ico){
		JButton btn = new JButton(Icons.getIcon(ico));
		btn.setToolTipText(jtt);
		btn.setMnemonic(key);
		btn.setActionCommand(nome);
	//	btn.setPreferredSize(new Dimension(40,40));
		btn.addActionListener((ActionListener) cadastro);
		btn.addKeyListener((KeyListener) cadastro);
		add(btn);
	}
}
