package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class BarraDeStatus extends JPanel {
	public JLabel jttt;
	private JLabel data;

	public BarraDeStatus(Boolean opcao) {  /* exibe a data e o dia da semana*/
		criaBarra(opcao);
	}

	private void criaBarra(Boolean opcao) {
		this.setBorder(BorderFactory.createLoweredBevelBorder());
	//	this.setLayout(new FlowLayout(SwingConstants.RIGHT));
		this.setLayout(new FlowLayout(SwingConstants.LEFT));
		
		data = new JLabel(new SimpleDateFormat("dd 'de' MMMM 'de' yyyy")
						.format(GregorianCalendar.getInstance().getTime())
						+ " | " + diaSemana());

		jttt = new JLabel(" ");
		
		data.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 12));		
		jttt.setFont(new Font(Font.SANS_SERIF, Font.BOLD , 12));				
		jttt.setForeground(Color.BLUE);
		
		this.add(jttt);
		
		if (opcao) {			
			data.setText(" | " + data.getText());
			this.add(data);
		}else {
			this.setLayout(new FlowLayout(FlowLayout.LEFT));
		}				
	}

	private String diaSemana() {
		Calendar calendario = new GregorianCalendar();
		int diaSem = calendario.get(Calendar.DAY_OF_WEEK);
		String diaSemana = null;
		switch (diaSem) { 
		case 1: { diaSemana = "Domingo"; break; }
		case 2: { diaSemana = "Segunda-feira"; break; }
		case 3: { diaSemana = "Terça-feira"; break; }
		case 4: { diaSemana = "Quarta-feira"; break; }
		case 5: { diaSemana = "Quinta-feira"; break; }
		case 6: { diaSemana = "Sexta-feira"; break; }
		case 7: { diaSemana = "Sábado"; break; }
		}
		return diaSemana;
	}
}