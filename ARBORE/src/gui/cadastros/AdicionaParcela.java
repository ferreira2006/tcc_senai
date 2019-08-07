package gui.cadastros;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import db.BiomaDAO;
import db.EpifitaDAO;
import db.ParcelaDAO;
import db.ParcelaVO;
import db.ProjetoParcelaDAO;
import db.SerrapilheiraDAO;
import db.TrepadeiraDAO;
import db.VegetacaoDAO;

@SuppressWarnings("serial")
public class AdicionaParcela extends CadastroDeParcela{
	
	private JComboBox vegetacao, serrapilheira, bioma, epifita, trepadeira;
	private JTextField coord1, coord2, coord3, coord4, area;
	private ButtonGroup espPioneiras, subBosque;
	private JRadioButton espPioSim, espPioNao, SubBosSim, SubBosNao;

	private BiomaDAO biomaDAO;
	private EpifitaDAO epifitaDAO;
	private SerrapilheiraDAO serrapilheiraDAO;
	private TrepadeiraDAO trepadeiraDAO;
	private VegetacaoDAO vegetacaoDAO;
	private JButton adicionar, fechar;
	private ParcelaVO pVO = null;
	private String projeto;
	
	public AdicionaParcela(String projeto) {
		super.setTitle("Adionar Parcela");
		
		this.projeto = projeto;
	
		inicializar();
		add(montaFormulario());
		
		pack();
		toFront();
		setVisible(true);
		setRequestFocusEnabled(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}

	private void inicializar() {
    	vegetacaoDAO = new VegetacaoDAO();
    	trepadeiraDAO = new TrepadeiraDAO();
    	serrapilheiraDAO = new SerrapilheiraDAO();
    	biomaDAO = new BiomaDAO();    	
    	epifitaDAO = new EpifitaDAO();
    	adicionar = new JButton("Adicionar");
    	fechar = new JButton("Fechar");
		
	}
	
	private JPanel montaFormulario() {
		JPanel panel = new JPanel();
		JPanel coordenadas = new JPanel();
		JPanel dados = new JPanel();
		JPanel addArvore = new JPanel();

		// panel coordenadas
		coordenadas.setBorder(BorderFactory
				.createTitledBorder("Coordenadas da Parcela:"));
		coord1 = new JTextField(10);
		coord1.setToolTipText("Informe a coordenada nº 01");
		coord2 = new JTextField(10);
		coord2.setToolTipText("Informe a coordenada nº 02");
		coord3 = new JTextField(10);
		coord3.setToolTipText("Informe a coordenada nº 03");
		coord4 = new JTextField(10);
		coord4.setToolTipText("Informe a coordenada nº 04");
		coordenadas.add(new JLabel("Coord 01: "));
		coordenadas.add(coord1);
		coordenadas.add(new JLabel("Coord 02: "));
		coordenadas.add(coord2);
		coordenadas.add(new JLabel("Coord 03: "));
		coordenadas.add(coord3);
		coordenadas.add(new JLabel("Coord 04: "));
		coordenadas.add(coord4);

		// panel dados
		dados.setBorder(BorderFactory.createTitledBorder("Dados da Parcela:"));
		dados.setLayout(new GridBagLayout());		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5,5,5,5);
		
		area = new JTextField(10);
		vegetacao = new JComboBox(vegetacaoDAO.pegaLista()); // deve receber lista do db
		vegetacao.setSelectedIndex(4);
		serrapilheira = new JComboBox(serrapilheiraDAO.pegaLista()); // deve receber lista do db
		serrapilheira.setSelectedIndex(2);
		bioma = new JComboBox(biomaDAO.pegaLista()); // deve receber lista do db
		bioma.setSelectedIndex(4);
		epifita = new JComboBox(epifitaDAO.pegaLista()); // deve receber lista do db
		epifita.setSelectedIndex(3);
		trepadeira = new JComboBox(trepadeiraDAO.pegaLista()); // deve receber lista do db
		trepadeira.setSelectedIndex(1);
		espPioneiras = new ButtonGroup();
		espPioSim = new JRadioButton("Sim");
		espPioSim.setSelected(false);
		espPioNao = new JRadioButton("Não");
		espPioNao.setSelected(true);
		espPioneiras.add(espPioSim);
		espPioneiras.add(espPioNao);
		subBosque = new ButtonGroup();
		SubBosSim = new JRadioButton("Sim");
		SubBosSim.setSelected(false);
		SubBosNao = new JRadioButton("Não");
		SubBosNao.setSelected(true);
		subBosque.add(SubBosSim);
		subBosque.add(SubBosNao);
		
		adicionar(dados, "Área:", "Informe a área da parcela em m²", area, 1, 2, 1, gbc);
		adicionar(dados, "Vegetação:", "Selecione o tipo de vegetação exitente na parcela", vegetacao, 3, 4, 1, gbc);
		adicionar(dados, "Serrapilheira:", "Selecione o tipo de aprsentação da serrapilheira", serrapilheira, 1, 2, 2, gbc);
		adicionar(dados, "Bioma:", "Selecione o tipo de bioma da parcela", bioma, 3, 4, 2, gbc);				
		adicionar(dados, "Epífitas:", "Selecione o tipo de apresentação das Epífitas", epifita, 1, 2, 3, gbc);
		adicionar(dados, "Trepadeiras:", "Selecione o tipo de apresentação das trepadeiras", trepadeira, 3, 4, 3, gbc);
		adicionar(dados, "Várias Esp. Pioneiras:", "Informe se existem muitas espécies pioneiras na parcela", espPioSim, 2, 3, 4, gbc);
		gbc.gridx = 4;	gbc.gridy = 4;
		dados.add(espPioNao, gbc);
		adicionar(dados, "SubBosque:", "", SubBosSim, 2, 3, 5, gbc);
		gbc.gridx = 4;	gbc.gridy = 5;
		dados.add(SubBosNao, gbc);
		

		// panel addArvore
		
		
		adicionar.setMnemonic('A');
		adicionar.setToolTipText("Adiciona uma parcela ao projeto");
		adicionar.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent arg0) {				
					salvaParcela();	
					salvaProjetoParcela();
			}

			

			
		});
		
		fechar.setMnemonic('F');
		fechar.setToolTipText("Fechar a janela");
		fechar.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent arg0) {				
				dispose();				
		}
	});
		
		
		addArvore.setBorder(BorderFactory.createTitledBorder("Adicionar esta Parcela ao Projeto:"));
		addArvore.add(adicionar);
		addArvore.add(fechar);
		
		
		
		
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setLayout(new GridBagLayout());			
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.gridx = 3; gbc.gridy = 1;
		panel.add(coordenadas, gbc);
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
				
		adicionar(panel, "", "", dados, 1, 2, 2, gbc);
		adicionar(panel, "", "", addArvore, 1, 2, 6, gbc);	
		
		return panel;
		
	}
	
	private void salvaProjetoParcela() {
		
		// pegar ultimo parcelaVO salvo
		
		Vector<ParcelaVO> vector = new ParcelaDAO().pegaLista();
		
		
			ParcelaVO vo = vector.lastElement();
		
		
		try {
			if (new ProjetoParcelaDAO().salvar( projeto, vo.getChave())) {
				
				JOptionPane.showMessageDialog(null, "Salvou Fim!!!");	
				
			//	getTabelaArvores().carregarTabela(getTabelaArvores(), vo.getChave())
				
				dispose();
				
			} else {
				
				JOptionPane.showMessageDialog(null, "Não Salvou!!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao pegar chave em codigo.getText()");
		}
		
	}
	
	private ParcelaVO salvaParcela() {
		
		pVO = new ParcelaVO();
		
		pVO.setCoord1(coord1.getText());
		pVO.setCoord2(coord2.getText());
		pVO.setCoord3(coord3.getText());
		pVO.setCoord4(coord4.getText());
		pVO.setArea(Double.parseDouble(area.getText()));
		pVO.setVegetacao(vegetacao.getSelectedIndex()+1);
		pVO.setBioma(bioma.getSelectedIndex()+1);
		pVO.setEpifita(epifita.getSelectedIndex()+1);
		pVO.setTrepadeira(trepadeira.getSelectedIndex()+1);
		pVO.setSerrapilheira(serrapilheira.getSelectedIndex()+1);
		
		if (espPioSim.isSelected()) {
			pVO.setMuistasEspeciesPioneiras("1");
		}else{
			pVO.setMuistasEspeciesPioneiras("0");
		}
		
		if (SubBosSim.isSelected()) {
			pVO.setSubBosque("1");
		}else{
			pVO.setSubBosque("0");
		}


		try {
			if (new ParcelaDAO().salvar(pVO, null)) {
				JOptionPane.showMessageDialog(null, "Salvou!!!");
			} else {
				JOptionPane.showMessageDialog(null, "Não Salvou!!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao pegar chave em codigo.getText()");
		}
		
		return pVO;
	}
	
	public void adicionar(Container container, String rotulo, String tooltip,
			JComponent componente, int x1, int x2, int y, GridBagConstraints gbc) {
			gbc.insets = new Insets(5,5,5,5);
			gbc.anchor = GridBagConstraints.EAST;
			gbc.gridx = x1;	gbc.gridy = y;
			container.add(new JLabel(rotulo, JLabel.RIGHT), gbc);
			gbc.gridx = x2;	gbc.gridy = y;
			gbc.anchor = GridBagConstraints.WEST;
			componente.setToolTipText(tooltip);
			container.add(componente, gbc);
	}
	
}
