package resources;

import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class AlteraTipoCursor {

	
	public AlteraTipoCursor(Container container) {
		
			for (int index = 0; index < container.getComponentCount(); index++) {
		    	
				Component component = container.getComponent(index);
		    	
		    	if (component instanceof JButton) {
		    		component.setCursor(new Cursor(Cursor.HAND_CURSOR));
		    	}
		    	if (component instanceof JTable) {
		    		component.setCursor(new Cursor(Cursor.HAND_CURSOR));
		    	}
		    	if (component instanceof JMenu) {
		    		component.setCursor(new Cursor(Cursor.HAND_CURSOR));
		    	}
		    	if (component instanceof JTable) {
		    		component.setCursor(new Cursor(Cursor.HAND_CURSOR));
		    	}
		    	if (component instanceof JScrollPane) {
		    		component.setCursor(new Cursor(Cursor.HAND_CURSOR));
		    	}
		    	if (component instanceof JPanel) {
		    		component.setCursor(new Cursor(Cursor.HAND_CURSOR));
		    	}		    	
		    	if (component instanceof JTabbedPane) {
		    		component.setCursor(new Cursor(Cursor.HAND_CURSOR));
		    	}
			}
		}
}
