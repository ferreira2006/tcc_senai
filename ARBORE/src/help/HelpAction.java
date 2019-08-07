package help;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

import javax.help.HelpBroker;
import javax.help.HelpSet;

public class HelpAction implements ActionListener {

	public void actionPerformed(ActionEvent ae) {
		File arquivo = new File("src\\help\\help_set.hs");
		try {
			URL hsURL = arquivo.toURI().toURL();
			HelpSet helpset = new HelpSet(getClass().getClassLoader(), hsURL);
			HelpBroker hb = helpset.createHelpBroker();
			hb.setDisplayed(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
