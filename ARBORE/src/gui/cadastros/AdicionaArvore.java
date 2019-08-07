package gui.cadastros;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import db.ArvoreDAO;
import db.ArvoreVO;
import db.ParcelaArvoreDAO;
import db.ParcelaArvoreVO;
import db.ProjetoParcelaDAO;
import db.ProjetoParcelaVO;

@SuppressWarnings("serial")
public class AdicionaArvore extends CadastroDeParcela{
	private JTextField cap, dap, altura, distancia, cas;
	private JButton adicionar, fechar;
	private JComboBox nomeArvore;
	private ArvoreDAO arvoreDAO;
	private String parcelaChave;
	private ProjetoParcelaVO projetoParcelaVO;

	
	public AdicionaArvore(JTable tabelaParcela, String parcelaChave) {
		super.setTitle("Adionar árvore à Parcela");
		
		this.parcelaChave = parcelaChave;

		
		inicializar();
		add(montaFormulario());
		
		pack();
		setVisible(true);
		toFront();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private JPanel montaFormulario() {
		JPanel addArvore = new JPanel();
		addArvore.setBorder(BorderFactory.createTitledBorder("Adiciona Árvore:"));
		addArvore.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5,5,5,5);
		
		nomeArvore = new JComboBox(arvoreDAO.pegaLista(true));
	//	nomeArvore.setSelectedIndex(109);
	
		
		adicionar(addArvore, "Árvore:", "Informe o nome da árvore", nomeArvore, 1, 2, 2, gbc);
		adicionar(addArvore, "CAP (cm):", "Informe a circunferência à altura do peito", cap, 3, 4, 1, gbc);
		adicionar(addArvore, "Altura(m):", "Informe a altura da árvore", altura, 5, 6, 1, gbc);
		JLabel aviso = new JLabel("Para decimais use Ponto(.)");
		aviso.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		aviso.setForeground(Color.BLUE);
		adicionar(addArvore, "", "", aviso, 1, 2, 1, gbc);
		adicionar(addArvore, "DAP(cm):", "Diamêtro à altura do peito", dap, 3, 4, 2, gbc);
		adicionar(addArvore, "Distância(cm):", "Distância do ponto central", distancia, 7, 8, 1, gbc);	
		adicionar(addArvore, "CAS(cm):", "Informe a Circunferência da árvore à Altura ao Solo em cm", cas, 5, 6, 2, gbc);
		adicionar(addArvore, "", "Adiciona árvores à parcela", adicionar, 6, 7, 2, gbc);
		adicionar(addArvore, "", "Fecha a Janela", fechar, 8, 8, 2, gbc);
		
		adicionar.setMnemonic('A');
		adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource() == adicionar) {
					
					
					
					try {
						projetoParcelaVO = new ProjetoParcelaDAO().pegaParcela(parcelaChave);
			//			System.out.println(projetoParcelaVO.getChaveParcela());

						if (new ParcelaArvoreDAO().numArvores(projetoParcelaVO.getChaveParcela()) < 4) {

							salvarDados(parcelaChave);							

						} else {
							JOptionPane.showMessageDialog(null,
									"Esta parcela já possuí 4 árvores!");
						}
						
						
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					
					
						
				}
			}
		});
		
		fechar.setMnemonic('F');
		fechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
					dispose();				
			}
		});
		 
		 return addArvore;
	}
	
	// Método adicionar(), adiciona componentes com GridBagLayout
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

	private void inicializar() {   
		
		cap = new JTextField();
		cap.setColumns(5);
		dap = new JTextField();
		dap.setColumns(5);
		dap.setEditable(false);
		altura = new JTextField();
		altura.setColumns(5);
		distancia = new JTextField();
		distancia.setColumns(5);
		cas = new JTextField();
		cas.setColumns(5);
    	adicionar = new JButton("Adicionar");
    	fechar = new JButton("Fechar");
    	arvoreDAO = new ArvoreDAO();
    	
    	
	}
		  
	
	private void salvarDados(String parcela){
		
			
		if (!"".equals(cap.getText()) && !"".equals(altura.getText()) &&  !"".equals(distancia.getText()) && !"".equals(cas.getText())) {
		
			ParcelaArvoreVO parcelaArvoreVO = new ParcelaArvoreVO();
			
			ProjetoParcelaVO projetoParcelaVO = null;
			
			try {
				projetoParcelaVO = new ProjetoParcelaDAO().pegaParcela(parcela);

			} catch (SQLException e2) { e2.printStackTrace(); }
						
			ArvoreVO arvoreVO = null;
			try {
				arvoreVO = (ArvoreVO) new ArvoreDAO().pegaArvore(nomeArvore.getSelectedItem().toString());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			parcelaArvoreVO.setChaveParcela(Integer.parseInt(projetoParcelaVO.getChaveParcela()));
			parcelaArvoreVO.setChaveArvore(Integer.parseInt(arvoreVO.getChave()));
			parcelaArvoreVO.setCap(Double.parseDouble(cap.getText()));
			
			double dap = Double.parseDouble(cap.getText()) / Math.PI;
			
			parcelaArvoreVO.setDap(dap);
			parcelaArvoreVO.setAltura(Double.parseDouble(altura.getText()));
			parcelaArvoreVO.setCas(Double.parseDouble(cas.getText()));
			parcelaArvoreVO.setDistancia(Double.parseDouble(distancia.getText()));
			//calcular AB
			
			double ab = 0.8 * (Double.parseDouble(altura.getText()) * Double.parseDouble(altura.getText()));
			
			parcelaArvoreVO.setAreaBasal(ab);			
			
				try {					
					
					
			

				if (new ParcelaArvoreDAO()
						.salvar(parcelaArvoreVO, projetoParcelaVO
								.getChaveParcela(), arvoreVO.getChave())) {

					JOptionPane.showMessageDialog(null, "Salvo");

					// tabelaArvores.carregarTabela(parcelaChave);
					limpaCampos();
					setVisible(false);
					dispose();

				} else {
					JOptionPane.showMessageDialog(null, "Não Salvo");
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Erro ao pegar chave em codigo.getText()");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Informe Todos os dados");
		}
	}
		
	private void limpaCampos(){
		cap.setText("");
		dap.setText("");
		altura.setText("");
		distancia.setText("");
		cas.setText("");
	}
}
