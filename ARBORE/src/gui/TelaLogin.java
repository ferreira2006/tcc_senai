package gui;

import gui.cadastros.CadastroDeUsuario;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import resources.Icons;

import db.BD;
import db.SenhaDAO;
import db.SenhaVO;
/**
 * @author Marcos
 *	Classe TelaLogin
 *	Esta classe é responsavel pela validação do usuário para liberação de acesso ao sistema.
 *	O usuário deve informar o login e a senha para logar no sistema.
 *	O software fará pesquisa no banco de dados (Tabela de usuarios) para validação.
 *	TelaLogin é subclasse de JFrame, porem adota preferencias próprias.
 *	A classe implementa a interface ActionListener para dar funcionalidades aos botões 
 */

@SuppressWarnings("serial")
public final class TelaLogin extends JFrame implements ActionListener {
	
	private JPanel panelLogin, panelBotoes;
	private JButton ok, limpar, sair;
	private JTextField login;
	private JPasswordField senha;
	private SenhaVO senhaVO;
	private SenhaDAO senhaDAO;
	private JanelaPrincipal jan;
	
	public TelaLogin() {		
		/**
		 * Construtor padrão da classe TelaLogin
		 * ao ser executado aciona o método criaTela().
		 */
		criaTela();
		
		setIconImage(Icons.getImage(Icons.CHAVE));
		add(BorderLayout.NORTH, panelLogin);
		add(BorderLayout.SOUTH, panelBotoes);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	//	setAlwaysOnTop(true);
		setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getRootPane().setDefaultButton(ok);
		pack();
		setLocationRelativeTo(null);
	//	ok.requestFocus(); 
	}

	private void criaTela() {
		/**
		 * Método criaTela()
		 * Este método cria e distribui os componentes
		 * na tela.
		 */
		setTitle("Login");
		
		panelLogin = new JPanel();
		panelLogin.setBorder(BorderFactory.createTitledBorder("Informe login e senha:"));
		panelLogin.setLayout(new GridLayout(0,2,10,10));
		panelLogin.add(new JLabel("Login: ", SwingConstants.RIGHT));
		login = new JTextField(10);		
		panelLogin.add(login);

		panelLogin.add(new JLabel("Senha: ", SwingConstants.RIGHT));
		senha = new JPasswordField(10);
		panelLogin.add(senha);

		panelBotoes = new JPanel();
		panelBotoes.setBorder(BorderFactory.createEtchedBorder());

		ok = new JButton("Ok");
		ok.setMnemonic('O');
		ok.addActionListener(this);
		panelBotoes.add(ok);

		limpar = new JButton("Limpar");
		limpar.setMnemonic('L');
		limpar.addActionListener(this);
		panelBotoes.add(limpar);

		sair = new JButton("Sair");
		sair.setMnemonic('S');
		sair.addActionListener(this);
		panelBotoes.add(sair);
		
	}

	public void actionPerformed(ActionEvent a) {
		/**
		 * Existem três botões, os quais chamam métodos específicos.
		 * O botão Ok dispara o método login();
		 * O botão Limpar executa o metodo limpar();
		 * O botão Sair executa o método sair();  
		 */
		if (a.getSource() == ok) 	{ login(); }
		if (a.getSource() == limpar){ limpar();}
		if (a.getSource() == sair) 	{ sair();  }
	}

	private void login() {
		/**
		 * O método login() valida a entrada do usuário no sistema
		 * Trabalha trocando informações com o pacote de dados.
		 * Oferece ao usuário consulta de Lembrete de senha.
		 */
		senhaDAO = new SenhaDAO();
		
		String str = new String(senha.getPassword());
		
		if (!"".equals(login.getText()) && !"".equals(str)) {
			
			boolean pesquisaNoBanco = false;
			try {
				String pass = new String(senha.getPassword());
				pesquisaNoBanco = senhaDAO.login(login.getText(), CadastroDeUsuario.criptografaString(pass));
			} catch (Exception e) {
				e.printStackTrace();
			} // pesquisa se o usuário existe
			
			if (pesquisaNoBanco == true) {
				
				// se existe usuario e senha é feito o login - chama a janela principal
				
				inicializa(login.getText());
				
			} else {// se não existe o sistema informa o erro e oferece o lembrete de senha
				
				Object[] options = { "Lembrete", "Fechar" };
				int pergunta = JOptionPane.showOptionDialog(null, "Usuário ou senha incorretos.\n Tente novamente ou veja seu\nlembrete de senha clicando\nno botão correspondente.\n", "ATENÇÃO", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
				
				if (pergunta == 0) { // se o usuário pedir o lembrete é aberto um inputBox pedindo o nome de usuário
					
					String resultado = JOptionPane.showInputDialog(null, "Informe o usuário", "Lembrete", JOptionPane.INFORMATION_MESSAGE).trim();
					login.requestFocus();
					
					if (!"".equals(resultado)){						
						String lembrete = "";
						try {
							
							senhaVO = (SenhaVO) senhaDAO.dicaSenha(resultado);
							
						} catch (SQLException e) {
							e.printStackTrace();
						//	JOptionPane.showMessageDialog(null, "Erro no BD");
						}						
						
						if (senhaVO != null) {
							lembrete = senhaVO.getDica();
							Object[] opcoes = { "Fechar" };
							JOptionPane.showOptionDialog(null, "Sua dica de Senha é: "+lembrete, "Lembrete de Senha", JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, opcoes, opcoes[0]);
							login.requestFocus();
						}else {
						
							JOptionPane.showMessageDialog(null, "Não existe um lembrete!\nContate o supervisor do sistema.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
							login.requestFocus();
						}						
					}
				}else{					
					login.requestFocus();
				}
			}
			
			
		}else {
			JOptionPane.showMessageDialog(null, "Login e Senha\n são obrigatórios");
			// Analisa qual está em branco e põe o foco nele
			if ("".equals(login.getText())) {
				login.requestFocus();
			}else {
				senha.requestFocus();
			}
		}
		
	}

	private void sair() {
		/**
		 * O método sair() possibilita o encerramento do aplicativo.
		 */
		System.exit(0);

	}

	private void limpar() {
		/**
		 * O método limpar() limpa os campos login e senha e 
		 * põe so foco no campo senha.
		 */
		login.setText("");
		senha.setText("");
		login.requestFocus();
	}
	
	private void inicializa(String usuario) {
		jan = new JanelaPrincipal(
				"ARBORE - Determinação do estágio de regeneração" +
				" da vegetação da Mata Atlântica - Usuário logado: " + usuario.toUpperCase());
		jan.setVisible(true);
		
		try { // analisa se o usuário é supervisor, se é o cad de usuario fica enabled
			if (senhaDAO.isSupervisor(login.getText())){
				jan.bmp.setStatus(true);
			}
		} catch (SQLException e) { e.printStackTrace();	}
				
		this.dispose();
	}
	
	public static void main(String[] args) {
		/**
		 * O metodo main() nesta classe atribui um lookAndFeel a janela, com as caracteristicas do
		 * sistema operacional que esta em execuçao e executa a instanciação da classe TelaLogin,
		 * dando inicio ao aplicativo. 
		 */
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			BD.start();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		new TelaLogin().setVisible(true);
	}
}