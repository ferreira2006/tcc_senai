package gui;

import gui.cadastros.CadastroAbstrato;
import help.HelpAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import resources.AlteraTipoCursor;
import resources.Icons;

@SuppressWarnings("serial")
public class BarraDeBotaoPrincipal extends JToolBar implements ActionListener{
	
	private JButton novo, relatorio;
	private JButton pessoaBotao, parcelaBotao, arvoreBotao, fimProjeto,
			fichaDeCampo, calculadora, fechar, ajudaBotao;
	private BarraDeMenu bmp;
		
	
	public BarraDeBotaoPrincipal(BarraDeMenu bmp) {
		this.bmp = bmp;
		criaBarra(null);		
	}

	private JPanel criaBarra(CadastroAbstrato cadastro) {
		JPanel panel = new JPanel();
		
		novo = new JButton(Icons.getIcon(Icons.NOVO));
		novo.setToolTipText("Clique para criar um novo Projeto");
	//	novo.setActionCommand(FECHAR);
		novo.addActionListener(this);
		novo.addKeyListener(cadastro);
		
	    pessoaBotao = new JButton(Icons.getIcon(Icons.PESSOAS));   
	    pessoaBotao.setToolTipText("Clique para cadastrar Proprietários ou Elaboradores");
	    pessoaBotao.addActionListener(this);
	    
	    parcelaBotao = new JButton(Icons.getIcon(Icons.PARCELA));
	    parcelaBotao.setToolTipText("Clique para cadastrar Parcelas");
	    parcelaBotao.addActionListener(this);
	    
	    arvoreBotao = new JButton(Icons.getIcon(Icons.ARVORE));
	    arvoreBotao.setToolTipText("Clique para cadastrar Arvores");
	    arvoreBotao.addActionListener(this);
	    
	    fimProjeto = new JButton(Icons.getIcon(Icons.FIM));
	    fimProjeto.setToolTipText("Clique para Concluir o Projeto");
	    fimProjeto.addActionListener(this);
	    
	    fichaDeCampo = new JButton(Icons.getIcon(Icons.FICHA_CAMPO));
	    fichaDeCampo.setToolTipText("Clique para gerar ficha de campo");
	    fichaDeCampo.addActionListener(this);
	    
	    calculadora = new JButton(Icons.getIcon(Icons.CALC32));
	    calculadora.setToolTipText("Clique para usar a calculadora");
	    calculadora.addActionListener(this);
	    
	    relatorio = new JButton(Icons.getIcon(Icons.RELATORIO32));
	    relatorio.setToolTipText("Clique para emitir relatórios");
	    relatorio.addActionListener(this);
	    
	    fechar = new JButton(Icons.getIcon(Icons.FECHAR));
	    fechar.setToolTipText("Clique para sair do ARBOR");
	    fechar.addActionListener(this);
	    
	    ajudaBotao = new JButton(Icons.getIcon(Icons.AJUDA));
	    ajudaBotao.setToolTipText("Clique aqui para obter Ajuda");
	    ajudaBotao.addActionListener(new HelpAction());
	    
	    add(novo);
	    add(new JLabel("   "));
	//   add(new JSeparator(SwingConstants.VERTICAL)); // os icones se alinham a direita
		add(new JLabel("   "));
	    add(pessoaBotao);
	    add(parcelaBotao);
    	add(arvoreBotao);
    	add(fimProjeto);
	    add(relatorio);	    
	    add(fichaDeCampo);
	    add(calculadora);
	    add(new JSeparator(SwingConstants.VERTICAL));
	    add(fechar);
	    add(ajudaBotao);
	    
	    //Muda o tipo de cursor conforme o tipo de componente do container
	    new AlteraTipoCursor(this);
	    
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == novo) {
			novoProjeto();
		}
		if (ae.getSource() == pessoaBotao) {
			cadastroPessoa();
		}		
		if (ae.getSource() == pessoaBotao) {
			cadastroPessoa();
		}
		if (ae.getSource() == parcelaBotao) {
			cadastroParcela();
		}
		if (ae.getSource() == arvoreBotao) {
			cadastroArvore();
		}
		if (ae.getSource() == fimProjeto) {
			fimProjeto();
		}
		if (ae.getSource() == relatorio) {
			relatorio();
		}
		if (ae.getSource() == fichaDeCampo) {
			emitirFicha();
		}
		if (ae.getSource() == calculadora) {
			exibeCalculadora();
		}
		if (ae.getSource() == fechar) {
			fechar();
		}
	}
	

	private void fimProjeto() {
		BarraDeMenu.fimProjeto();
		
	}

	private void relatorio() {
		bmp.relatorio();
		
	}
	
	private void cadastroArvore() {
		bmp.cadastraArvore();
		
	}

	private void cadastroParcela() {
		BarraDeMenu.cadastraParcela();
		
	}

	private void cadastroPessoa() {
		BarraDeMenu.cadastraPessoa();
		
	}

	private void novoProjeto() {
		bmp.novo();
		
	}
	
	private void emitirFicha() {		
		bmp.emitirFicha();		
	}
	
	private void exibeCalculadora() {
		bmp.exibirCalculadora();	
		
	}
	private void fechar() {
		bmp.sair(0);	
		
	}

}
